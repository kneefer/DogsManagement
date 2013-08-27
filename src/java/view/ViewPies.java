package view;

import boundary.PiesFacade;
import boundary.RasaFacade;
import boundary.WlascicielFacade;
import entities.Pies;
import entities.Rasa;
import entities.Wlasciciel;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import lazyLoading.LazyDataLoading;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@Named(value = "piesView")
@ViewScoped
@ManagedBean
public class ViewPies implements Serializable {

    @EJB
    private PiesFacade piesFacade;
    @EJB
    private RasaFacade rasaFacade;
    @EJB
    private WlascicielFacade wlascicielFacade;
    
    private Pies selectedToDelete;
    private LazyDataModel<Pies> piesModel;

    @PostConstruct
    public void init(){        
        piesModel = new LazyDataLoading<Pies>() {
            
            @Override
            public List<Pies> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters){
                this.setRowCount(piesFacade.count(filters));
                return piesFacade.getResultList(first, pageSize, sortField, sortOrder, filters);
            }
            
            @Override
            public Object getRowKey(Pies user) {
                return user.getId().toString();
            }
            
            @Override
            public Pies getRowData(String rowKey) {
                if(this.getDatasource() == null)
                    return null;
               for(Pies user : getDatasource()) {
                   if(user.getId().toString().equals(rowKey))
                   return user;
               }
               return null;
            }
        };
        
        piesModel.setRowCount(piesFacade.count(new HashMap<String, String> ()));
    }
   
    public void usunPsa(Pies selectedToDelete) {
        
        Rasa rasaUsuwanegoPsa;
        Wlasciciel wlascicielUsuwanegoPsa;
        
        Collection<Pies> psy;
        
        try {
            rasaUsuwanegoPsa = rasaFacade.findByIdRasy(selectedToDelete.getIRasa().getId());
            psy = rasaUsuwanegoPsa.getPiesCollection();
           
            psy.remove(selectedToDelete);
            rasaUsuwanegoPsa.setPiesCollection(psy);
            
            rasaFacade.edit(rasaUsuwanegoPsa);
        } catch (NullPointerException ex) {}
        
        try {
            wlascicielUsuwanegoPsa = wlascicielFacade.findByIdWlasciciela(selectedToDelete.getIWlasciciel().getId());
            psy = wlascicielUsuwanegoPsa.getPiesCollection();
            
            psy.remove(selectedToDelete);
            wlascicielUsuwanegoPsa.setPiesCollection(psy);
            
            wlascicielFacade.edit(wlascicielUsuwanegoPsa);
        } catch (NullPointerException ex) {}

        piesFacade.remove(selectedToDelete);
    }
    
    public void dodajPsa() {
        piesFacade.create(new Pies("Nienazwany pies!"));
    }
    
    public void onEditPies(RowEditEvent event) {
        Pies nowyPies = (Pies)event.getObject();
        Pies staryPies = piesFacade.findByIdPsa(nowyPies.getId());
        
        Rasa staraRasa = null;
        Rasa nowaRasa = null;
                
        try {
            staraRasa = rasaFacade.findByIdRasy(staryPies.getIRasa().getId());
        } catch (NullPointerException ex) {}
        
        try {
            nowaRasa = rasaFacade.findByIdRasy(nowyPies.getIRasa().getId());
        } catch (NullPointerException ex) {}
        
        Wlasciciel staryWlasciciel = null;
        Wlasciciel nowyWlasciciel = null;
        
        try {
            staryWlasciciel = wlascicielFacade.findByIdWlasciciela(staryPies.getIWlasciciel().getId());
        } catch (NullPointerException ex) {}
        
        try{
            nowyWlasciciel = wlascicielFacade.findByIdWlasciciela(nowyPies.getIWlasciciel().getId());
        } catch (NullPointerException ex) {}       
        
        Collection<Pies> psy;
        
        /* RASA RELATED COLLECTION UPDATE */
        if (staraRasa != null) {
            psy = staraRasa.getPiesCollection();
            psy.remove(staryPies);
            
            staraRasa.setPiesCollection(psy);
            rasaFacade.edit(staraRasa);
        }
        
        if (nowaRasa != null) {
            psy = nowaRasa.getPiesCollection();
            psy.add(nowyPies);
            
            nowaRasa.setPiesCollection(psy);
            rasaFacade.edit(nowaRasa);
        }
        
        /* WŁAŚCICIEL RELATED COLLECTION UPDATE */
        if (staryWlasciciel != null) {
            psy = staryWlasciciel.getPiesCollection();
            psy.remove(staryPies);
            
            staryWlasciciel.setPiesCollection(psy);
            wlascicielFacade.edit(staryWlasciciel);
        }
        
        if (nowyWlasciciel != null) {
            psy = nowyWlasciciel.getPiesCollection();
            psy.add(nowyPies);
            
            nowyWlasciciel.setPiesCollection(psy);
            wlascicielFacade.edit(nowyWlasciciel);
        }
        
        piesFacade.edit(nowyPies); 
    }
    
    // getters... setters...
    
    public LazyDataModel<Pies> getPiesModel() {
        return piesModel;
    }

    public void setPiesModel(LazyDataModel<Pies> piesModel) {
        this.piesModel = piesModel;
    }
    
    public Pies getSelectedToDelete() {
        return selectedToDelete;
    }

    public void setSelectedToDelete(Pies selectedToDelete) {
        this.selectedToDelete = selectedToDelete;
    }
}
