package tondeuse.traitement;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import tondeuse.base.TailleDePelouse;
import tondeuse.base.Tondeuse;

@RunWith(JUnitParamsRunner.class)
public class MouvementsDeLaTondeuseTest {

	@Test
	@Parameters({ "1, 2, N, 1, 3", "1, 2, S, 1, 1", "1, 2, E, 2, 2", "1, 2, W, 0, 2" })
	public void devraitAvancerLaTondeuse(int longitude, int latitude, char orientation, int longitude_attendue,
			int latitude_attendue) {

		TailleDePelouse TailleDePelouse = new TailleDePelouse(5, 5);
		Tondeuse tondeuse = new Tondeuse(longitude, latitude, orientation, TailleDePelouse, "");
		tondeuse.avancer();
		assertEquals(longitude_attendue, tondeuse.getX());
		assertEquals(latitude_attendue, tondeuse.getY());

	}

	@Test
	@Parameters({ "6, 5, N, 6, 5", "0, 0, S, 0, 0", "6, 5, E, 6, 5", "0, 0, W, 0, 0" })
	public void devraitNePasAvancerLaTondeusePlusLoinQueLeBordDeLaPelouse(int longitude, int latitude, char orientation,
			int longitude_attendue, int latitude_attendue) {

		TailleDePelouse TailleDePelouse = new TailleDePelouse(6, 5);
		Tondeuse tondeuse = new Tondeuse(longitude, latitude, orientation, TailleDePelouse, "");
		tondeuse.avancer();
		assertEquals(longitude_attendue, tondeuse.getX());
		assertEquals(latitude_attendue, tondeuse.getY());

	}

	@Test
	@Parameters({ "1, 2, N, E", "1, 2, S, W", "1, 2, E, S", "1, 2, W, N", })
	public void devraitTournerLaTondeuseVersLaDroite(int longitude, int latitude, char orientation,
			char orientation_attendue) {

		TailleDePelouse TailleDePelouse = new TailleDePelouse(6, 5);
		Tondeuse tondeuse = new Tondeuse(longitude, latitude, orientation, TailleDePelouse, "");
		tondeuse.tournerVersLaDroite();
		assertEquals(orientation_attendue, tondeuse.getOrientation());

	}

	@Test
	@Parameters({ "1, 2, N, W", "1, 2, S, E", "1, 2, E, N", "1, 2, W, S", })
	public void devraitTournerLaTondeuseVersLaGauche(int longitude, int latitude, char orientation,
			char orientation_attendue) {

		TailleDePelouse TailleDePelouse = new TailleDePelouse(6, 5);
		Tondeuse tondeuse = new Tondeuse(longitude, latitude, orientation, TailleDePelouse, "");
		tondeuse.tournerVersLaGauche();
		assertEquals(orientation_attendue, tondeuse.getOrientation());

	}

	@Test
	@Parameters({ "1, 2, N, 2, 3, S" })
	public void devraitDeplacerEtTournerLaTondeuse(int longitude, int latitude, char orientation,
			int longitude_attendue, int latitude_attendue, char orientation_attendue) {

		TailleDePelouse TailleDePelouse = new TailleDePelouse(6, 5);
		Tondeuse tondeuse = new Tondeuse(longitude, latitude, orientation, TailleDePelouse, "");
		tondeuse.avancer();
		tondeuse.tournerVersLaDroite();
		tondeuse.avancer();
		tondeuse.tournerVersLaDroite();
		assertEquals(longitude_attendue, tondeuse.getX());
		assertEquals(latitude_attendue, tondeuse.getY());
		assertEquals(orientation_attendue, tondeuse.getOrientation());

	}

}
