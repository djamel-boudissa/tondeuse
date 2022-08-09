package tondeuse.base;

import tondeuse.params.Valeurs;
/*
 * classe d'initialisation de tondeuse 
 */
public class Tondeuse {

	final private TailleDePelouse taille_de_pelouse;

	private int x;
	private int y;
	private char orientation;
	private String liste_des_mouvements;
	
	
	public Tondeuse(int x, int y, char orientation, TailleDePelouse TailleDePelouse,
			String liste_des_mouvements) {
		
	
         
		this.x = x;
		this.y = y;
		this.orientation = orientation;
		this.taille_de_pelouse = TailleDePelouse;
		this.liste_des_mouvements = liste_des_mouvements;

	}

	
	
	public int getX() {
		return x;
	}



	public int getY() {
		return y;
	}



	public char getOrientation() {
		return orientation;
	}
	
	
	@Override
	public String toString() {
		return "Tondeuse [taille_de_pelouse=" + taille_de_pelouse + ", X=" + x + ", Y=" + y
				+ ", orientation=" + orientation + "]";
	}
	

	public void avancer() {

		switch (orientation) {
		case Valeurs.NORD:
			if (y < taille_de_pelouse.getTaille_max_y()) {
				y++;
			}
			break;
		case Valeurs.SUD:
			if (y > 0) {
				y--;
			}
			break;
		case Valeurs.EST:
			if (x < taille_de_pelouse.getTaille_max_x()) {
				x++;
			}
			break;
		case Valeurs.OUEST:
			if (x > 0) {
				x--;
			}
			break;
		default:

		}
	}

	public void tournerVersLaDroite() {

		switch (orientation) {
		case Valeurs.NORD:
			orientation = Valeurs.EST;
			break;
		case Valeurs.SUD:
			orientation = Valeurs.OUEST;
			break;
		case Valeurs.EST:
			orientation = Valeurs.SUD;
			break;
		case Valeurs.OUEST:
			orientation = Valeurs.NORD;
			break;
		default:

		}
	}

	public void tournerVersLaGauche() {

		switch (orientation) {
		case Valeurs.NORD:
			orientation = Valeurs.OUEST;
			break;
		case Valeurs.SUD:
			orientation = Valeurs.EST;
			break;
		case Valeurs.EST:
			orientation = Valeurs.NORD;
			break;
		case Valeurs.OUEST:
			orientation = Valeurs.SUD;
			break;
		default:

		}
	}

	public void executerMouvementSuivant() {
		if (!liste_des_mouvements.isEmpty()) {
			switch (liste_des_mouvements.charAt(0)) {
			case Valeurs.AVANT:
				this.avancer();
				break;
			case Valeurs.DROITE:
				this.tournerVersLaDroite();
				break;
			case Valeurs.GAUCHE:
				this.tournerVersLaGauche();
				break;
			default:

			}
			liste_des_mouvements = liste_des_mouvements.substring(1);
		}
	}

}
