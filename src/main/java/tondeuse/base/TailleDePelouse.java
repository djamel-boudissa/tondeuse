package tondeuse.base;
/*
 * definition de la taille de pelouse 
 */
public class TailleDePelouse {

	private final int taille_max_x;
	private final int taille_max_y;
	public int getTaille_max_x() {
		return taille_max_x;
	}
	public int getTaille_max_y() {
		return taille_max_y;
	}
	public TailleDePelouse(int taille_max_x, int taille_max_y) {
		
		this.taille_max_x = taille_max_x;
		this.taille_max_y = taille_max_y;
	}
	

}
