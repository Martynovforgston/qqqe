package view;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class AboutFrameTest {

	static AboutFrame frame;
	
	@BeforeClass
	public static void beforeClass() {
		frame = new AboutFrame();
	}
	
	@Test
	public void aboutFrameTest() {
		assertNotNull(frame);
	}

}
