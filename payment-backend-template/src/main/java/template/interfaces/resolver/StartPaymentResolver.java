package template.interfaces.resolver;

public interface StartPaymentResolver<T, R> {

    R startTransaction(T request);

    String getType();
}
