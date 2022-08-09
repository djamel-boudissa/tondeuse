package tondeuse.base;


import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import tondeuse.Main;
import tondeuse.params.Valeurs;
import tondeuse.traitements.ExceptionTondeuse;
import static org.fest.assertions.Assertions.assertThat;
@RunWith(JUnitParamsRunner.class)
public class PelouseTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	@Parameters({"5, 6"})
	public void devraitCreerUnePelouseNonCarree(int taille_est_ouest_x, int taille_nord_sud_y) {
		
		Pelouse pelouse = new Pelouse(taille_est_ouest_x, taille_nord_sud_y);
		TailleDePelouse TailleDePelouse = pelouse.getTailleDePelouse();
		assertEquals(taille_est_ouest_x, TailleDePelouse.getTaille_max_x());
		assertEquals(taille_nord_sud_y, TailleDePelouse.getTaille_max_y());
		
	}
	
	@Test
	@Parameters({"1, 2, N"})
	public void devraitCreerUneTondeuse(int longitude, int latitude, char orientation){
		
		TailleDePelouse TailleDePelouse = new TailleDePelouse(5, 5);
		Tondeuse tondeuse = new Tondeuse(longitude, latitude, orientation, TailleDePelouse, "");
		assertEquals(longitude, tondeuse.getX());
		assertEquals(latitude, tondeuse.getY());
		assertEquals(orientation, tondeuse.getOrientation());
		
	}
	
	@Test
	@Parameters({"1, 6, N"})
	public void devraitPasCreerUneTondeuse(int longitude, int latitude, char orientation) throws ExceptionTondeuse, IOException {
		expectedEx.expect(ExceptionTondeuse.class);
		expectedEx.expectMessage(Valeurs.ERREUR_CREATION_TONDEUSE);
		Pelouse pelouse = new Pelouse(5, 5);
		pelouse.ajouterTondeuse(longitude, latitude, orientation, "");
		
		
	
	}
	
	@Test
	@Parameters({"1, 2, N"})
	public void devraitCreerUneTondeuseDansUnePelouseCarree(int longitude, int latitude, char orientation) throws ExceptionTondeuse {
		
		Pelouse pelouse = new Pelouse(5, 5);
		pelouse.ajouterTondeuse(longitude, latitude, orientation, "");
		
	}
	

}
