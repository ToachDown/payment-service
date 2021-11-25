package template.interfaces;

import template.model.RequestMessage;
import template.model.ResponseMessage;

public interface PaymentUpdater<R extends RequestMessage, T extends ResponseMessage> {

    R changeStateWithResponse(R request, T response);

    R changeState(R request, String state);

    String getType();
}
