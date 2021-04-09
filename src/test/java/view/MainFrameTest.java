package view;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class MainFrameTest {

	static MainFrame frame;
	
	@BeforeClass
	public static void beforeClass() {
		frame = new MainFrame();
	}
	
	@Test
	public void mainFrameTest() {
		assertNotNull(frame);
	}

}
