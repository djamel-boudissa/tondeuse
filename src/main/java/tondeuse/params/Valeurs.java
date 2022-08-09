package tondeuse.params;

/*
 * initilaliser les constants 
 */
public abstract class Valeurs {
	public static final String[] TABLEAU_DE_STRING = {};
	
	public static final char NORD = 'N';
	public static final char SUD = 'S';
	public static final char EST = 'E';
	public static final char OUEST = 'W';
	public static final char AVANT = 'A';
	public static final char GAUCHE = 'G';
	public static final char DROITE = 'D';
	
	public static char[] orientations= {NORD,SUD,EST,OUEST};
	public static char[] commandes= {AVANT,GAUCHE,DROITE};
	
	// les messages d'erreurs 
	
	public static final String ERREUR_FICHIER_INEXISTANT = "Fichier input innexistant";
	public static final String ERREUR_PELOUSE = "taille de pelouse incorrect";

	public static final String ERREUR_FICHIER_VIDE = "fichier vide";

	public static final String ERREUR_ORIENTATION = "Orientation incorrect";
	public static final String ERREUR_COMMANDE = "Commande incorrect";

	public static final String ERREUR_COMMANDE_VIDE = "Commande vide ";

	public static final String ERREUR_CREATION_TONDEUSE = "Erreur de creation tondeuse";
	
}
