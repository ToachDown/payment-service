package template.interfaces;

import template.exception.ApiFeignException;

public interface PaymentAwareTransformer<T, K> {

    T transformDto(K dto);

    String getType();

}
