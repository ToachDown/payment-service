package com.example.paymentbackendtemplate.service;

import com.example.backendtemplate.interfaces.PaymentResolver;
import com.example.backendtemplate.interfaces.PaymentUpdater;
import com.example.backendtemplate.model.RefundMessage;
import com.example.backendtemplate.model.RequestMessage;
import com.example.backendtemplate.model.ResponseMessage;
import com.example.backendtemplate.model.TransactionMessage;
import com.example.paymentbackendtemplate.exception.custom.StarterNotResolvedException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RequestCommander {

    private final Map<String, PaymentResolver<
            RequestMessage,
            RefundMessage,
            TransactionMessage,
            ResponseMessage>> paymentResolverMap;
    private final Map<String, PaymentUpdater<RequestMessage, ResponseMessage>> paymentStateChangerMap;

    @SuppressWarnings("SpringJavaInjectonPointsAutowiringInspection")
    public RequestCommander(final Map<String, PaymentResolver<RequestMessage, RefundMessage, TransactionMessage, ResponseMessage>> paymentResolverMap,
                            final Map<String, PaymentUpdater<RequestMessage, ResponseMessage>> paymentStateChangerMap) {
        this.paymentResolverMap = paymentResolverMap;
        this.paymentStateChangerMap = paymentStateChangerMap;
    }

    public ResponseMessage startTransaction(RequestMessage request) {
        final PaymentResolver<RequestMessage, RefundMessage, TransactionMessage, ResponseMessage> resolver =
                paymentResolverMap.get(request.getType());
        if(resolver == null) {
            throw new StarterNotResolvedException();
        }
        return resolver.startPayment(request);
    }

    public ResponseMessage updatePayment(RequestMessage request) {
        final PaymentResolver<RequestMessage, RefundMessage, TransactionMessage, ResponseMessage> resolver =
                paymentResolverMap.get(request.getType());
        if(resolver == null) {
            throw new StarterNotResolvedException();
        }
        return resolver.updatePayment(request);
    }

    public ResponseMessage captureTransaction(RequestMessage request) {
        final PaymentResolver<RequestMessage, RefundMessage, TransactionMessage, ResponseMessage> resolver =
                paymentResolverMap.get(request.getType());
        if(resolver == null) {
            throw new StarterNotResolvedException();
        }
        return resolver.capturePayment(request);
    }

    public ResponseMessage statusTransaction(TransactionMessage request) {
        final PaymentResolver<RequestMessage, RefundMessage, TransactionMessage, ResponseMessage> resolver =
                paymentResolverMap.get(request.getType());
        if(resolver == null) {
            throw new StarterNotResolvedException();
        }
        return resolver.statusPayment(request);
    }

    public ResponseMessage cancelTransaction(TransactionMessage request) {
        final PaymentResolver<RequestMessage, RefundMessage, TransactionMessage, ResponseMessage> resolver =
                paymentResolverMap.get(request.getType());
        if(resolver == null) {
            throw new StarterNotResolvedException();
        }
        return resolver.cancelPayment(request);
    }

    public ResponseMessage refundPayment(RefundMessage request) {
        final PaymentResolver<RequestMessage, RefundMessage, TransactionMessage, ResponseMessage> resolver =
                paymentResolverMap.get(request.getType());
        if(resolver == null) {
            throw new StarterNotResolvedException();
        }
        return resolver.refundPayment(request);
    }

    public RequestMessage changePaymentStateWithResponse(RequestMessage request, ResponseMessage response) {
        final PaymentUpdater<RequestMessage, ResponseMessage> updater = paymentStateChangerMap.get(request.getType());
        if(updater == null) {
            throw new StarterNotResolvedException();
        }
        return updater.changeStateWithResponse(request, response);
    }

    public RequestMessage changePaymentState(RequestMessage request, String state) {
        final PaymentUpdater<RequestMessage, ResponseMessage> updater = paymentStateChangerMap.get(request.getType());
        if(updater == null) {
            throw new StarterNotResolvedException();
        }
        return updater.changeState(request, state);
    }
}
