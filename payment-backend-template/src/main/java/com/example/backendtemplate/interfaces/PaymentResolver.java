package com.example.backendtemplate.interfaces;

import com.example.backendtemplate.model.RefundMessage;
import com.example.backendtemplate.model.RequestMessage;
import com.example.backendtemplate.model.ResponseMessage;
import com.example.backendtemplate.model.TransactionMessage;

public interface PaymentResolver<R extends RequestMessage, T extends RefundMessage, V extends TransactionMessage, G extends ResponseMessage> {

    G capturePayment(R request);

    G startPayment(R request);

    G updatePayment(R request);

    G refundPayment(T request);

    G cancelPayment(V request);

    G statusPayment(V request);

    String getType();
}
