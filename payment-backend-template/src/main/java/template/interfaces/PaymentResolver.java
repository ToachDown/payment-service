package template.interfaces;

public interface PaymentResolver<T, R> {

    R startTransaction (T request);

    R updatePayment (T request);

    R captureTransaction (T request);

    String getType();
}
