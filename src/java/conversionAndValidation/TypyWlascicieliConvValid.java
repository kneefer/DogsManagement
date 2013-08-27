package conversionAndValidation;

import boundary.TypWlascicielaFacade;
import entities.TypWlasciciela;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "typyWlascicieliConverter")
@ApplicationScoped
public class TypyWlascicieliConvValid implements Converter, Serializable {

    @EJB
    private TypWlascicielaFacade typWlascicielaFacade;
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof TypWlasciciela)) {
            return "-1";
        }
        return ((TypWlasciciela)value).getId().toString();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        TypWlasciciela typWlasciciela;
        
        if (submittedValue.equals("-1")) {
            return null;
        }
        
        try {
            typWlasciciela = typWlascicielaFacade.findByIdTypuWlasciciela(Integer.parseInt(submittedValue));
        } catch (NumberFormatException ex) {
            return null;
        }
        
        if (typWlasciciela == null) {
            return null;
        }
        
        return typWlasciciela;
    }
}
