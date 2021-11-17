package template.interfaces;

import template.model.RefundMessage;
import template.model.RequestMessage;
import template.model.ResponseMessage;
import template.model.TransactionMessage;

public interface PaymentResolver<R extends RequestMessage, T extends RefundMessage, V extends TransactionMessage, G extends ResponseMessage> {

    G capturePayment(R request);

    G startPayment(R request);

    G updatePayment(R request);

    G refundPayment(T request);

    G cancelPayment(V request);

    G statusPayment(V request);

    String getType();
}
