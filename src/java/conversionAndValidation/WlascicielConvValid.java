package conversionAndValidation;

import boundary.WlascicielFacade;
import entities.Wlasciciel;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "wlascicielConverter")
@ApplicationScoped
public class WlascicielConvValid implements Converter, Serializable {

    @EJB
    private WlascicielFacade wlascicielFacade;
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Wlasciciel)) {
            return "-1";
        }
        return ((Wlasciciel)value).getId().toString();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        Wlasciciel wlasciciel;
        
        if (submittedValue.equals("-1")) {
            return null;
        }
        
        try {
            wlasciciel = wlascicielFacade.findByIdWlasciciela(Integer.parseInt(submittedValue));
        } catch (NumberFormatException ex) {
            return null;
        }
        
        if (wlasciciel == null) {
            return null;
        }
        
        return wlasciciel;
    }
}
