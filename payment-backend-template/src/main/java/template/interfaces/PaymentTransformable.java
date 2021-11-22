package template.interfaces;

import template.exception.ApiException;

public interface PaymentTransformable<T, K> {

    T transformDto(K dto) throws ApiException;

    String getType();

}
