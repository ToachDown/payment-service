package template.interfaces.resolver;

public interface StatusPaymentResolver<T, R> {

    R statusTransaction(T request);

    String getType();
}
