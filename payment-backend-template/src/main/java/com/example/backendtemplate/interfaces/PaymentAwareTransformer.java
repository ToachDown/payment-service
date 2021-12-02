package com.example.backendtemplate.interfaces;

public interface PaymentAwareTransformer<T, K> {

    T transformDto(K dto);

    String getType();

}
