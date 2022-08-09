package tondeuse.traitement;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import tondeuse.base.Pelouse;
import tondeuse.base.TailleDePelouse;
import tondeuse.base.Tondeuse;
import tondeuse.traitements.ExceptionTondeuse;

@RunWith(JUnitParamsRunner.class)
public class MouvementDesTondeusesSurLaPelouseTest {

	@Test
	@Parameters({ "1, 2, N, GAGAGAGAA, 1, 3, N" })
	public void devraitPasserLaliste_des_mouvementsALaTondeuseEtLuiFaireTousExecuterLUnApresLAutre(int longitude,
			int latitude, char orientation, String liste_des_mouvements, int longitude_attendue, int latitude_attendue,
			char orientation_attendue) {

		TailleDePelouse TailleDePelouse = new TailleDePelouse(5, 5);
		Tondeuse tondeuse = new Tondeuse(longitude, latitude, orientation, TailleDePelouse, liste_des_mouvements);
		for (int i = 0; i < liste_des_mouvements.length(); i++) {
			tondeuse.executerMouvementSuivant();
		}
		assertEquals(1, tondeuse.getX());
		assertEquals(3, tondeuse.getY());
		assertEquals('N', tondeuse.getOrientation());

	}

	@Test
	@Parameters({ "1, 2, N, GAGAGAGAA, 1, 3, N" })
	public void devraitIgnorerLesMouvementsOrdonnesUneFoisArriveAuBoutDeLaListe(int longitude, int latitude,
			char orientation, String liste_des_mouvements, int longitude_attendue, int latitude_attendue,
			char orientation_attendue) {

		TailleDePelouse TailleDePelouse = new TailleDePelouse(5, 5);
		Tondeuse tondeuse = new Tondeuse(longitude, latitude, orientation, TailleDePelouse, liste_des_mouvements);
		for (int i = 0; i <= liste_des_mouvements.length(); i++) {
			tondeuse.executerMouvementSuivant();
		}
		assertEquals(longitude_attendue, tondeuse.getX());
		assertEquals(latitude_attendue, tondeuse.getY());
		assertEquals(orientation_attendue, tondeuse.getOrientation());

	}

	@Test
	@Parameters({ "1, 2, N, GAGAGAGAA, 1, 3, N" })
	public void devraitExecuterLesMouvementsDUneTondeuseDeLaPelouseJusquACeQUelleAitTermineeSesMouvements(int longitude,
			int latitude, char orientation, String liste_des_mouvements, int longitude_attendue, int latitude_attendue,
			char orientation_attendue) throws ExceptionTondeuse {

		Pelouse pelouse = new Pelouse(5, 5);
		Tondeuse tondeuse = pelouse.ajouterTondeuse(longitude, latitude, orientation, liste_des_mouvements);
		for (int i = 0; i <= liste_des_mouvements.length(); i++) {
			pelouse.executerMouvementSuivantPourChaqueTondeuse();
		}
		assertEquals(longitude_attendue, tondeuse.getX());
		assertEquals(latitude_attendue, tondeuse.getY());
		assertEquals(orientation_attendue, tondeuse.getOrientation());

	}

	public Object parametresDesTondeusesDansDesArrays() {
		int[] longitude = { 1, 3 };
		int[] latitude = { 2, 3 };
		char[] orientation = { 'N', 'E' };
		String[] liste_des_mouvements = { "GAGAGAGAA", "AADAADADDA" };
		int[] longitude_attendue = { 1, 5 };
		int[] latitude_attendue = { 3, 1 };
		char[] orientation_attendue = { 'N', 'E' };
		return new Object[][] { { longitude, latitude, orientation, liste_des_mouvements, longitude_attendue,
				latitude_attendue, orientation_attendue } };
	}

	@Test
	@Parameters(method = "parametresDesTondeusesDansDesArrays")
	public void devraitAjouterDesTondeusesALaPelouseEtExecuterTousLeursMouvements(int[] longitude, int[] latitude,
			char[] orientation, String[] liste_des_mouvements, int[] longitude_attendue, int[] latitude_attendue,
			char[] orientation_attendue) throws ExceptionTondeuse {

		int nombre_de_tondeuses = longitude.length;
		ArrayList<Tondeuse> liste_des_tondeuses_crees = new ArrayList<Tondeuse>();

		Pelouse pelouse = new Pelouse(5, 5);

		for (int i = 0; i < nombre_de_tondeuses; i++) {
			liste_des_tondeuses_crees
					.add(pelouse.ajouterTondeuse(longitude[i], latitude[i], orientation[i], liste_des_mouvements[i]));
		}

		pelouse.executerTousLesMouvementsRestantsDesTondeuses();

		for (int i = 0; i < nombre_de_tondeuses; i++) {

			assertEquals(longitude_attendue[i], liste_des_tondeuses_crees.get(i).getX());
			assertEquals(latitude_attendue[i], liste_des_tondeuses_crees.get(i).getY());
			assertEquals(orientation_attendue[i], liste_des_tondeuses_crees.get(i).getOrientation());
		}

	}

}
