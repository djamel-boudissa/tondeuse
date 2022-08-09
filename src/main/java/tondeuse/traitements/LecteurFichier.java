package tondeuse.traitements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import tondeuse.base.Pelouse;
import tondeuse.params.Valeurs;

/*
 * lecture de fichier commandes et traitemet de exceptions , erreurs, etc
 */
public class LecteurFichier {

	private ArrayList<String> tableau_des_lignes_du_fichier = new ArrayList<String>();
	private Pelouse pelouse;

	public LecteurFichier(String chemin_fichier) throws ExceptionTondeuse, IOException {

		File fichier = new File(chemin_fichier);
		if (!fichier.isFile()) {
			// fichier innexistant 
			throw new ExceptionTondeuse(Valeurs.ERREUR_FICHIER_INEXISTANT);
		} else {

			BufferedReader buffered_reader = new BufferedReader(new FileReader(chemin_fichier));

			String nouvelle_ligne = buffered_reader.readLine();
			if (nouvelle_ligne == null) {
				// fichier vide 
				buffered_reader.close();
				throw new ExceptionTondeuse(Valeurs.ERREUR_FICHIER_VIDE);
			} else {
				pelouse = lire_premier_ligne(nouvelle_ligne);

				tableau_des_lignes_du_fichier.add(nouvelle_ligne);

				String[] tableau_position_de_depart;
				int debutx;
				int debuty;
				char orientation;
				String liste_des_mouvements;

				while (buffered_reader.ready()) {
					nouvelle_ligne = buffered_reader.readLine();
					tableau_des_lignes_du_fichier.add(nouvelle_ligne);

					tableau_position_de_depart = nouvelle_ligne.split(" ");

					debutx = Integer.valueOf(tableau_position_de_depart[0]);
					debuty = Integer.valueOf(tableau_position_de_depart[1]);
					
					orientation = getDebutOrientation(tableau_position_de_depart[2].charAt(0));

					liste_des_mouvements = getListDesMouvement(buffered_reader.readLine());

					tableau_des_lignes_du_fichier.add(liste_des_mouvements);
					pelouse.ajouterTondeuse(debutx, debuty, orientation, liste_des_mouvements);

				}
				buffered_reader.close();

			}

		}

	}

	private String getListDesMouvement(String mouvements) throws ExceptionTondeuse {
		if (mouvements != null) {
			for (char c : mouvements.toCharArray()) {
				if (!inList(c, Valeurs.commandes))
					throw new ExceptionTondeuse(Valeurs.ERREUR_COMMANDE);
			}
			return mouvements;
		}

		else {
			throw new ExceptionTondeuse(Valeurs.ERREUR_COMMANDE_VIDE);
		}

	}

	private char getDebutOrientation(char chr) throws ExceptionTondeuse {

		if (!inList(chr, Valeurs.orientations))

			throw new ExceptionTondeuse(Valeurs.ERREUR_ORIENTATION);

		return chr;
	}

	private boolean inList(char chr, char[] list) {

		for (char c : list) {
			if (c == chr)
				return true;
		}
		return false;

	}

	private Pelouse lire_premier_ligne(String premier_ligne) throws ExceptionTondeuse {

		try {
			if (!premier_ligne.isEmpty() || premier_ligne == null) {
				String[] tableau_taille = premier_ligne.split(" ");
				int taille_max_x = Integer.parseInt(tableau_taille[0]);
				int taille_max_y = Integer.parseInt(tableau_taille[1]);
				if (taille_max_x > 0 && taille_max_y > 0)
					return new Pelouse(taille_max_x, taille_max_y);
				else
					throw new ExceptionTondeuse(Valeurs.ERREUR_PELOUSE);
			} else {
				throw new ExceptionTondeuse(Valeurs.ERREUR_PELOUSE);
			}

		} catch (NumberFormatException e) {
			
			throw new ExceptionTondeuse(Valeurs.ERREUR_PELOUSE);
		}

	}

	public String[] getTableauDesLignesDuFichier() {
		return tableau_des_lignes_du_fichier.toArray(Valeurs.TABLEAU_DE_STRING);
	}

	public String[] getTableauPositionsDesTondeusesDeLaPelouse() {
		return pelouse.getTableauPositionsDesTondeuses();
	}

	public Pelouse getPelouse() {
		return pelouse;
	}

	public void effectuerTousLesMouvementsDesTondeusesDeLaPelouse() {
		pelouse.executerTousLesMouvementsRestantsDesTondeuses();
	}

}
