package core.settings;

public class PropertyGeneric<T> extends Property {
	
	public PropertyGeneric(String name, Object value) {
		super(name, value);
	}
	
	@SuppressWarnings("unchecked")
	public T getValue() {
		return (T)super.value;
	}
}
