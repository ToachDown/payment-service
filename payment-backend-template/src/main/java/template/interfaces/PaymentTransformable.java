package template.interfaces;

public interface PaymentTransformable<T, K> {

    T transformDto(K dto);

    String getType();

}
