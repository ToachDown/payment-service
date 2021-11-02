package template.interfaces;

public interface RequestDelegator<T, R> {

    R getAnswer (T request);

    String getType();
}
