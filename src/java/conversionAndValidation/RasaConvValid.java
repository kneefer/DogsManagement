package conversionAndValidation;

import boundary.RasaFacade;
import entities.Rasa;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "rasaConverter")
@ApplicationScoped
public class RasaConvValid implements Converter, Serializable {

    @EJB
    private RasaFacade rasaFacade;
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Rasa)) {
            return "-1";
        }
        return ((Rasa)value).getId().toString();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        Rasa rasa;
        
        if (submittedValue.equals("-1")) {
            return null;
        }
        
        try {
            rasa = rasaFacade.findByIdRasy(Integer.parseInt(submittedValue));
        } catch (NumberFormatException ex) {
            return null;
        }
        
        if (rasa == null) {
            return null;
        }
        
        return rasa;
    }
}
