package fuzzyLogic;

import java.util.HashMap;

public class Action extends Property {
	
	private HashMap<String, Float> coeffs;

	
	public Action(String[] keys, Value[] values, Getter getter) throws InvalidProperty {
		super(keys, values, getter);
		
		if (!this.check()) throw new InvalidProperty();
		
		for (String key : this.map.keySet()) {
			this.coeffs.put(key, (float) 0);
		}
	}
	
	public void applyRule(String key, float val) {
		this.coeffs.put(key, Math.max(this.coeffs.get(key), this.map.get(key).input(val)));
		// TODO : opérateur logique OU (max pour l'instant mais on peut prendre somme moins produit ou autre)
	}
	
	public float defuzzicate() {
		
		float sumVal = 0;
		float sumCoeff = 0;
		
		for (String key : this.map.keySet()) {
			float[] sum = this.map.get(key).output(this.coeffs.get(key));
			sumVal += sum[0];
			sumCoeff += sum[1];
		}
		
		return(sumVal / sumCoeff);
	}
	
	public void reset() {
		for (String key : this.coeffs.keySet()) this.coeffs.put(key, (float) 0);
	}
}
