package fuzzyLogic;

import java.util.HashMap;

class InvalidProperty extends Exception {

	private static final long serialVersionUID = 1L;
	
}

class referenceProperty extends HashMap<String, value> {

	private static final long serialVersionUID = 1L;
	
 
	boolean check() throws InvalidProperty {
		// vérifie que la somme des valeurs numériques ne dépasse pas 1
		
		for (value v : this.values()) {
			for (float x : v.getThresholds()) {
				float y = 0;
				for (value nv : this.values()) {
					y += nv.input(x);
				}
				if (y > 1) throw new InvalidProperty();
			}
		}
		return true;
	}
	
	// TODO : public float getGameValue (connection avec l'API du jeu) ==> dans cette classe ??
	
}
