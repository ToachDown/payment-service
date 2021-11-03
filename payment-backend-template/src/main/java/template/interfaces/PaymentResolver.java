package template.interfaces;

public interface PaymentResolver<T, R> {

    R getAnswer (T request);

    String getType();
}
