package tondeuse;

import java.io.File;
import java.io.IOException;

import tondeuse.traitements.AfficheurResultats;
import tondeuse.traitements.ExceptionTondeuse;
import tondeuse.traitements.LecteurFichier;

/*
 * classe principale pour executer les fichiers commandes 
 */
public class Main {

	public static void main(String[] args) throws ExceptionTondeuse, IOException {

		// chemin par default de fichier commande , sinon sera etré comme argumant =>
		// Main <chemin_fichier>

		String inputfile = "fichiersInput/fichier_de_commande_OK.txt";
		if (args.length == 1) {
			inputfile = args[0];
		}

		File fichier_de_commande = new File(inputfile);

		// lire les fichier des comandes , et vérifier toutes les informations
		LecteurFichier lecteur_de_fichier = new LecteurFichier(fichier_de_commande.getAbsolutePath());

		// effectuer le mouvements
		lecteur_de_fichier.effectuerTousLesMouvementsDesTondeusesDeLaPelouse();
		String[] tableau_de_resultats = lecteur_de_fichier.getTableauPositionsDesTondeusesDeLaPelouse();

		// afficher résultat
		AfficheurResultats afficheur_de_resultats = new AfficheurResultats(tableau_de_resultats);
		afficheur_de_resultats.afficherLesResultats();

	}

}
