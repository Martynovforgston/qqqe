package observer;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class EventManagerTest {

	private static EventManager events;
	private static SomeListener listener;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		events = new EventManager("someEvent");
		listener = new SomeListener();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		events.unsubscribe("someEvent", listener);
	}

	@Test
	public void test() {
		events.subscribe("someEvent", listener);
		events.notify("someEvent");
		
		assertTrue(listener.getValue() == 5);
	}

}

class SomeListener implements IEventListener {
	
	private int value;
	
	SomeListener() { value = 0; }

	@Override
	public void update(String eventType) { value = 5; }
	
	public int getValue() { return value; }
	
}
