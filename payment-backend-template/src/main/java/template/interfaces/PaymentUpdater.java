package template.interfaces;

import template.exception.ApiException;
import template.model.RequestMessage;
import template.model.ResponseMessage;

public interface PaymentUpdater<R extends RequestMessage, T extends ResponseMessage> {

    R changeStateWithResponse(R request, T response) throws ApiException;

    R changeState(R request, String state) throws ApiException;

    String getType();
}