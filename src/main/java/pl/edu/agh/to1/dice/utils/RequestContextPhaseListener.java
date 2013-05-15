package pl.edu.agh.to1.dice.utils;

import org.primefaces.context.DefaultRequestContext;
import org.primefaces.util.Constants;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Author: Piotr Turek
 */
public class RequestContextPhaseListener implements PhaseListener {
    private static final long serialVersionUID = 1832618590862971753L;

    @Override
    public void afterPhase(PhaseEvent event) {

    }

    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();
        if (context.getAttributes().get(Constants.REQUEST_CONTEXT_ATTR) == null) {
            context.getAttributes().put(Constants.REQUEST_CONTEXT_ATTR, new DefaultRequestContext());
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

}
