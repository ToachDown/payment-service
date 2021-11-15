package template.interfaces.transformable;

public interface PaymentTransformable<T, K> {

    T transformDto(K dto);

    String getType();

}
