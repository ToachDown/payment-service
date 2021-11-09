package template.interfaces;

public interface PaymentResolver<T, R> {

    R startTransaction(T request);

    R updatePayment(T request);

    R captureTransaction(T request);

    R statusTransaction(String transactionId);

    String getType();
}
