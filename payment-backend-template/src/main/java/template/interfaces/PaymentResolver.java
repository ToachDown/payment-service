package template.interfaces;

import template.exception.ApiException;
import template.model.RefundMessage;
import template.model.RequestMessage;
import template.model.ResponseMessage;
import template.model.TransactionMessage;

public interface PaymentResolver<R extends RequestMessage, T extends RefundMessage, V extends TransactionMessage, G extends ResponseMessage> {

    G capturePayment(R request) throws ApiException;

    G startPayment(R request) throws ApiException;

    G updatePayment(R request) throws ApiException;

    G refundPayment(T request) throws ApiException;

    G cancelPayment(V request) throws ApiException;

    G statusPayment(V request) throws ApiException;

    String getType();
}
