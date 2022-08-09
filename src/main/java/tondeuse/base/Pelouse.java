package tondeuse.base;

import java.util.ArrayList;

import tondeuse.params.Valeurs;
import tondeuse.traitements.ExceptionTondeuse;
/*
 * classe d'initialisation de la pelouse 
 * 
 */


public class Pelouse {

	final private TailleDePelouse taille_pelouse;

	private ArrayList<Tondeuse> liste_des_tondeuses = new ArrayList<Tondeuse>();
	private int nombre_de_mouvements = 0;

	public Pelouse(int taille_max_x, int taille_max_y) {
		this.taille_pelouse = new TailleDePelouse(taille_max_x, taille_max_y);
	}

	public TailleDePelouse getTailleDePelouse() {
		return taille_pelouse;
	}

	public Tondeuse ajouterTondeuse(int position_x, int position_y, char orientation, String liste_des_mouvements) throws ExceptionTondeuse {
		
		if(position_x<=this.taille_pelouse.getTaille_max_x() && position_y<=this.taille_pelouse.getTaille_max_x())  
		{
			Tondeuse tondeuse = new Tondeuse(position_x, position_y, orientation, this.taille_pelouse, liste_des_mouvements);
			liste_des_tondeuses.add(tondeuse);

			int nombre_de_mouvements_pour_cette_tondeuse = liste_des_mouvements.length();
			nombre_de_mouvements = Math.max(nombre_de_mouvements,
					nombre_de_mouvements_pour_cette_tondeuse);

			return tondeuse;
		}else {
			throw new ExceptionTondeuse(Valeurs.ERREUR_CREATION_TONDEUSE);
		}
		
		
		
	}

	public void executerMouvementSuivantPourChaqueTondeuse() {
		for (Tondeuse tondeuse_a_avancer : liste_des_tondeuses) {
			tondeuse_a_avancer.executerMouvementSuivant();
			nombre_de_mouvements--;
		}
	}

	public void executerTousLesMouvementsRestantsDesTondeuses() {
		for (int i = nombre_de_mouvements; i > 0; i--) {
			executerMouvementSuivantPourChaqueTondeuse();
		}

	}

	public String[] getTableauPositionsDesTondeuses() {
		ArrayList<String> tableau_position_des_tondeuses = new ArrayList<String>();
		String position;
		int longitude;
		int latitude;
		char orientation;
		
		for (Tondeuse tondeuse_dont_il_faut_la_position: liste_des_tondeuses) {
			
			longitude = tondeuse_dont_il_faut_la_position.getX();
			latitude = tondeuse_dont_il_faut_la_position.getY();
			orientation = tondeuse_dont_il_faut_la_position.getOrientation();

			position = longitude + " " + latitude + " " + orientation;
			tableau_position_des_tondeuses.add(position);

		}
		
		return tableau_position_des_tondeuses.toArray(
				Valeurs.TABLEAU_DE_STRING);
	}

}
