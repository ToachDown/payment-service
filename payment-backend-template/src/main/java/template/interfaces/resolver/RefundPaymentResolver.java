package template.interfaces.resolver;

public interface RefundPaymentResolver<T, R> {

    R refundPayment(T request);

    String getType();
}
