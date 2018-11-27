package fuzzyLogic;

import java.util.HashMap;

class InvalidProperty extends Exception {

	private static final long serialVersionUID = 1L;
	
}

public class Property {
	
	protected final HashMap<String, Value> map;
	protected final Getter getter;
	
	protected Property(Getter getter) {
		this.map = new HashMap<String, Value>();
		this.getter = getter;
	}
 
	public Property(String[] keys, Value[] values, Getter getter) {
		this.map = new HashMap<String, Value>();
		int n = keys.length;
		if (n != values.length)
			throw new java.lang.ArrayIndexOutOfBoundsException();
		
		for (int i = 0; i < n; i++)
			this.map.put(keys[i], values[i]);
		
		this.getter = getter;
	}


	boolean check() throws InvalidProperty {
		// vérifie que la somme des valeurs numériques ne dépasse pas 1
		
		for (Value v : this.map.values()) {
			for (float x : v.getThresholds()) {
				float y = 0;
				for (Value nv : this.map.values()) {
					y += nv.input(x);
				}
				if (y > 1) throw new InvalidProperty();
			}
		}
		return true;
	}
	
	Value getValue(String key) {
		return this.map.get(key);
	}
	
	float getGameValue() {
		return this.getter.get();
	}
	
	// TODO : public float getGameValue (connection avec l'API du jeu) ==> dans cette classe ??
	
}


class Getter {
	/**
	 * Classe pour récupérer les valeurs du jeu 
	 */
	Getter() {
		// TODO
	}
	
	float get() {
		// TODO
		return 0;
	}

}
