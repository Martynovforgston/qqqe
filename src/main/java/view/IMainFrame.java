package view;

import observer.IEventListener;

public interface IMainFrame {
	
	public void subscribe(String eventType, IEventListener listener);
	public void unsubscribe(String eventType, IEventListener listener);
	
	public void setResult(double price);
	public double getResult();
	
	public String getRegionName();
	public String getPromo();
	
	public int getCount1Leaf();
	public int getCount2Leaf();
	public int getCount3Leaf();
	public int getCountM2Floor();
	
	public boolean isBathroomOn();
}