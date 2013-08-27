package view;

import boundary.WlascicielFacade;
import entities.Wlasciciel;
import java.io.Serializable;
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

@Named(value = "wlascicielView")
@ViewScoped
@ManagedBean
public class ViewWlasciciel implements Serializable {

    @EJB
    private WlascicielFacade wlascicielFacade;
    
    private List<Wlasciciel> wlasciciele;

    private Wlasciciel selectedToDelete;
    private LazyDataModel<Wlasciciel> wlascicielModel;
    private Wlasciciel nowyWlasciciel;

    @PostConstruct
    public void init(){  
        
        wlasciciele = wlascicielFacade.findAll();
        
        wlascicielModel = new LazyDataLoading<Wlasciciel>() {
            
            @Override
            public List<Wlasciciel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters){
                this.setRowCount(wlascicielFacade.count(filters));
                return wlascicielFacade.getResultList(first, pageSize, sortField, sortOrder, filters);
            }
            
            @Override
            public Object getRowKey(Wlasciciel user) {
                return user.getId().toString();
            }
            
            @Override
            public Wlasciciel getRowData(String rowKey) {
                if(this.getDatasource() == null)
                    return null;
               for(Wlasciciel user : getDatasource()) {
                   if(user.getId().toString().equals(rowKey))
                   return user;
               }
               return null;
            }
        };
        
        wlascicielModel.setRowCount(wlascicielFacade.count(new HashMap<String, String> ()));
    }
   
    public void usunWlasciciela(Wlasciciel selectedToDelete) {
        wlascicielFacade.remove(selectedToDelete);
        this.wlasciciele = wlascicielFacade.findAll();
    }
    
    public void dodajWlasciciela() {
        wlascicielFacade.create(nowyWlasciciel);
        this.wlasciciele = wlascicielFacade.findAll();
        this.nowyWlasciciel = new Wlasciciel();
    }
    
    public void onEditWlasciciel(RowEditEvent event) {
        Wlasciciel wlasciciel = (Wlasciciel)event.getObject();        
        wlascicielFacade.edit(wlasciciel);
        this.wlasciciele = wlascicielFacade.findAll();
    }
    
    // getters... setters...
    
    public LazyDataModel<Wlasciciel> getWlascicielModel() {
        return wlascicielModel;
    }

    public void setWlascicielModel(LazyDataModel<Wlasciciel> wlascicielModel) {
        this.wlascicielModel = wlascicielModel;
    }
    
    public Wlasciciel getSelectedToDelete() {
        return selectedToDelete;
    }

    public void setSelectedToDelete(Wlasciciel selectedToDelete) {
        this.selectedToDelete = selectedToDelete;
    }
    
    public List<Wlasciciel> getWlasciciele() {
        return wlasciciele;
    }

    public void setWlasciciele(List<Wlasciciel> wlasciciele) {
        this.wlasciciele = wlasciciele;
    }
    
    public Wlasciciel getNowyWlasciciel() {
        return nowyWlasciciel;
    }

    public void setNowyWlasciciel(Wlasciciel nowyWlasciciel) {
        this.nowyWlasciciel = nowyWlasciciel;
    }
}
