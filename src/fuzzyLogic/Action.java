package fuzzyLogic;

import java.util.HashMap;

public class Action {
	
	private final ReferenceProperty property;
	private HashMap<String, Float> coeffs;

	
	public Action(ReferenceProperty prop) throws InvalidProperty {
		this.property = prop;
		
		if (!this.property.check()) throw new InvalidProperty();
		
		for (String key : this.property.keySet()) {
			this.coeffs.put(key, (float) 0);
		}
	}
	
	public void applyRule(String key, float val) {
		this.coeffs.put(key, Math.max(this.coeffs.get(key), this.property.get(key).input(val)));
	}
	
	public float defuzzicate() {
		
		float sumVal = 0;
		float sumCoeff = 0;
		
		for (String key : this.property.keySet()) {
			float[] sum = this.property.get(key).output(this.coeffs.get(key));
			sumVal += sum[0];
			sumCoeff += sum[1];
		}
		
		return(sumVal / sumCoeff);
	}
	
	public void reset() {
		for (String key : this.coeffs.keySet()) this.coeffs.put(key, (float) 0);
	}
}
