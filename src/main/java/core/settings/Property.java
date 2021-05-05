package core.settings;

public class Property {
	
	protected String name;
	protected Object value;
	
	public Property(String name, Object value)
	{
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Object getValue() {
		return this.value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}