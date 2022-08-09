package tondeuse;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tondeuse.params.Valeurs;
import tondeuse.traitements.ExceptionTondeuse;

/*
 * test de la classe principale et les cas possibles 
 */
public class MainTest {
	final String CHEMIN_FICHIER = "fichiersInput/";
	private final static PrintStream sortie_par_defaut_vers_la_console = System.out;
	private final static ByteArrayOutputStream output = new ByteArrayOutputStream();
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setOut(new PrintStream(output, true, "UTF-8"));
	}

	// test si le fichier innexistant

	@Test
	public void main_test_fichier_inexistant() throws ExceptionTondeuse, IOException {
		expectedEx.expect(ExceptionTondeuse.class);
		expectedEx.expectMessage(Valeurs.ERREUR_FICHIER_INEXISTANT);
		String[] arg = { "fichierinexistant" };
		Main.main(arg);
	}

	// tester si le fichier est vide

	@Test
	public void main_test_fichier_vide() throws ExceptionTondeuse, IOException {
		expectedEx.expect(ExceptionTondeuse.class);
		expectedEx.expectMessage(Valeurs.ERREUR_FICHIER_VIDE);

		String[] arg = { CHEMIN_FICHIER + "fichier_de_commande_vide.txt" };
		Main.main(arg);

	}

	// tester si la line 1 est incorrecte (taille de pelouse)

	@Test
	public void main_test_1_ligne() throws ExceptionTondeuse, IOException {
		expectedEx.expect(ExceptionTondeuse.class);
		expectedEx.expectMessage(Valeurs.ERREUR_PELOUSE);

		String[] arg = { CHEMIN_FICHIER + "fichier_de_commande_line1NOK.txt" };
		Main.main(arg);
	}

	// effectuer un test avec line 2 incorrect , orientation par exp M
	@Test
	public void main_test_2_lignes() throws ExceptionTondeuse, IOException {
		expectedEx.expect(ExceptionTondeuse.class);
		expectedEx.expectMessage(Valeurs.ERREUR_ORIENTATION);
		String[] arg = { CHEMIN_FICHIER + "fichier_de_commande_line2NOK.txt" };
		Main.main(arg);
	}

	// effectuer un test avec mouvements vide
	@Test
	public void main_test_mouvements_vide() throws ExceptionTondeuse, IOException {
		expectedEx.expect(ExceptionTondeuse.class);
		expectedEx.expectMessage(Valeurs.ERREUR_COMMANDE_VIDE);
		String[] arg = { CHEMIN_FICHIER + "fichier_de_commande_mouvementsEmpty.txt" };
		Main.main(arg);

	}
	// effectuer un test avec des mouvement invalide AGGDDSAAAAAADD

	@Test
	public void main_test_mouvements_invalide() throws ExceptionTondeuse, IOException {
		expectedEx.expect(ExceptionTondeuse.class);
		expectedEx.expectMessage(Valeurs.ERREUR_COMMANDE);
		String[] arg = { CHEMIN_FICHIER + "fichier_de_commande_movementsNOK.txt" };
		Main.main(arg);

	}

	// fichier test demmande dans l'exercice ,
	/*
	 * 5 5 1 2 N GAGAGAGAA 3 3 E AADAADADDA => tondeuse1: 1 3 N , tondeuse2: 5 1 E
	 */

	@Test
	public void main_test_fichier_ok() throws ExceptionTondeuse, IOException {
		String[] arg = { CHEMIN_FICHIER + "fichier_de_commande_OK.txt" };
		Main.main(arg);

		String string_de_resultats = "1 3 N" + System.lineSeparator() + "5 1 E" + System.lineSeparator();
		assertEquals(string_de_resultats, output.toString());
		assertThat(string_de_resultats).isNotNull();
		assertThat(string_de_resultats).contains("1 3 N").contains("5 1 E");

	}

	// console de sortie
	@AfterClass
	public static void tearDownAfterClass() {
		System.setOut(sortie_par_defaut_vers_la_console);
	}
}