package tondeuse.base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
@RunWith(JUnitParamsRunner.class)

public class TondeuseTest {
	
	
	@Test
	@Parameters({"1, 2, N"})
	public void devraitCreerUneTondeuse(int longitude, int latitude, char orientation){
		
		TailleDePelouse TailleDePelouse = new TailleDePelouse(5, 5);
		Tondeuse tondeuse = new Tondeuse(longitude, latitude, orientation, TailleDePelouse, "");
		assertEquals(longitude, tondeuse.getX());
		assertEquals(latitude, tondeuse.getY());
		assertEquals(orientation, tondeuse.getOrientation());
		
	}
	
	
	
	

}
