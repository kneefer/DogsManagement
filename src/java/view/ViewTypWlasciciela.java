package view;

import boundary.TypWlascicielaFacade;
import entities.TypWlasciciela;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

@Named(value = "typWlascicielaView")
@ViewScoped
@ManagedBean
public class ViewTypWlasciciela implements Serializable {
    
    @EJB
    TypWlascicielaFacade typWlascicielaFacade;

    private TypWlasciciela selectedToDelete;
    private String nazwaNowegoTypuWlasciciela;
    private List<TypWlasciciela> typyWlasciciela;
    
    @PostConstruct
    public void init() {
        this.typyWlasciciela = typWlascicielaFacade.findAll();
    }
    
    public void usunTypWlasciciela(TypWlasciciela selectedToDelete) {
        typWlascicielaFacade.remove(selectedToDelete);
        this.typyWlasciciela = typWlascicielaFacade.findAll();
    }
    
    public void dodajTypWlasciciela() {
        typWlascicielaFacade.create(new TypWlasciciela(nazwaNowegoTypuWlasciciela));
        this.typyWlasciciela = typWlascicielaFacade.findAll();
        this.nazwaNowegoTypuWlasciciela = "";
    }
    
    public void onEditTypWlasciciela(RowEditEvent event) {
        TypWlasciciela rasa = (TypWlasciciela)event.getObject();        
        typWlascicielaFacade.edit(rasa);
        this.typyWlasciciela = typWlascicielaFacade.findAll();
    }
    
    
    // getters... setters...
    
    public TypWlasciciela getSelectedToDelete() {
        return selectedToDelete;
    }

    public void setSelectedToDelete(TypWlasciciela selectedToDelete) {
        this.selectedToDelete = selectedToDelete;
    }

    public String getNazwaNowegoTypuWlasciciela() {
        return nazwaNowegoTypuWlasciciela;
    }

    public void setNazwaNowegoTypuWlasciciela(String nazwaNowegoTypuWlasciciela) {
        this.nazwaNowegoTypuWlasciciela = nazwaNowegoTypuWlasciciela;
    }
    
    public List<TypWlasciciela> getTypyWlasciciela() {
        return typyWlasciciela;
    }

    public void setTypyWlasciciela(List<TypWlasciciela> typyWlasciciela) {
        this.typyWlasciciela = typyWlasciciela;
    }
}
