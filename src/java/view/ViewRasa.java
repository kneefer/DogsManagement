package view;

import boundary.RasaFacade;
import entities.Rasa;
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

@Named(value = "rasaView")
@ViewScoped
@ManagedBean
public class ViewRasa implements Serializable {

    @EJB
    private RasaFacade rasaFacade;
    
    private List<Rasa> rasy;

    private Rasa selectedToDelete;
    private String nazwaNowejRasy;

    private LazyDataModel<Rasa> rasaModel;

    @PostConstruct
    public void init(){      
        
        this.rasy = rasaFacade.findAll();
        
        rasaModel = new LazyDataLoading<Rasa>() {
            
            @Override
            public List<Rasa> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters){
                this.setRowCount(rasaFacade.count(filters));
                return rasaFacade.getResultList(first, pageSize, sortField, sortOrder, filters);
            }
            
            @Override
            public Object getRowKey(Rasa user) {
                return user.getId().toString();
            }
            
            @Override
            public Rasa getRowData(String rowKey) {
                if(this.getDatasource() == null)
                    return null;
               for(Rasa user : getDatasource()) {
                   if(user.getId().toString().equals(rowKey))
                   return user;
               }
               return null;
            }
        };
        
        rasaModel.setRowCount(rasaFacade.count(new HashMap<String, String> ()));
    }
   
    public void usunRase(Rasa selectedToDelete) {
        rasaFacade.remove(selectedToDelete);
        this.rasy = rasaFacade.findAll();
    }
    
    public void dodajRase() {
        rasaFacade.create(new Rasa(nazwaNowejRasy));
        this.rasy = rasaFacade.findAll();
        this.nazwaNowejRasy = "";
    }
    
    public void onEditRasa(RowEditEvent event) {
        Rasa rasa = (Rasa)event.getObject();        
        rasaFacade.edit(rasa);
        this.rasy = rasaFacade.findAll();
    }
    
    // getters... setters...
    
    public LazyDataModel<Rasa> getRasaModel() {
        return rasaModel;
    }

    public void setRasaModel(LazyDataModel<Rasa> rasaModel) {
        this.rasaModel = rasaModel;
    }
    
    public Rasa getSelectedToDelete() {
        return selectedToDelete;
    }

    public void setSelectedToDelete(Rasa selectedToDelete) {
        this.selectedToDelete = selectedToDelete;
    }
    
     public List<Rasa> getRasy() {
        return rasy;
    }

    public void setRasy(List<Rasa> rasy) {
        this.rasy = rasy;
    }
    
    public String getNazwaNowejRasy() {
        return nazwaNowejRasy;
    }

    public void setNazwaNowejRasy(String nazwaNowejRasy) {
        this.nazwaNowejRasy = nazwaNowejRasy;
    }
}
