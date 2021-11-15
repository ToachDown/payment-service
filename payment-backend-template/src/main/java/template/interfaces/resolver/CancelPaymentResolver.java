package template.interfaces.resolver;

public interface CancelPaymentResolver<T, R>{

    R cancelTransaction(T request);

    String getType();
}
