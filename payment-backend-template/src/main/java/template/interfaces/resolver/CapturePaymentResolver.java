package template.interfaces.resolver;

public interface CapturePaymentResolver<T, R> {

    R capturePayment(T request);

    String getType();
}
