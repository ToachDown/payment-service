package template.interfaces.resolver;

public interface UpdatePaymentResolver<T, R> {

    R updatePayment(T request);

    String getType();
}
