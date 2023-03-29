package vn.provl.api.component.entity.attribute;

public interface Attribute<T> {

    public void set(T newValue);

    public void reset();

    public T get();

    public T getDefault();

    public AttributeType getType();

}
