package fuzzyLogic;


class UnsortedThresholdException extends Exception {

	private static final long serialVersionUID = -6987234651347108843L;

}

class NotARimValueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3275971311728689417L;

}

class value {
	
	/** la classe valeur correspond à une valeur possible d'évaluation d'une propriété
	 * 
	 * par exemple : pour la propriété "health", on peut avoir les valeurs "critical", "low", "average" et "high"
	 * 
	 * @author pichot
	 *
	 **/
	
	enum rim {MIN, MID, MAX};
	
	private final float[] thresholds;
	private final rim isRim;
	
	
	public value(float[] thresholds) throws UnsortedThresholdException {
		
		if (thresholds.length != 4) throw new java.lang.ArrayIndexOutOfBoundsException();
		if (thresholds[0] > thresholds[1] || thresholds[1] > thresholds[2] || thresholds[2] > thresholds[3])
			throw new UnsortedThresholdException();
		
		this.thresholds = thresholds;
		
		if (thresholds[1] == 0) {
			this.isRim = rim.MIN;
		} else if (thresholds[2] == 2) {
			this.isRim = rim.MAX;
		} else {
			this.isRim = rim.MID;
		}
	}
	

	float[] getThresholds() {
		return this.thresholds;
	}
	
	boolean isRim() {
		return(this.isRim != rim.MID);
	}
	
	public value not() throws NotARimValueException, UnsortedThresholdException {
		if (this.isRim == rim.MID) {
			throw new NotARimValueException();
		} else if (this.isRim == rim.MIN) {
			float[] newThresholds = {this.thresholds[2], this.thresholds[3], 1, 1};
			return(new value(newThresholds));
		} else {
			float[] newThresholds = {0, 0, this.thresholds[0], this.thresholds[1]};
			return(new value(newThresholds));
		}
	}
	
	public float input(float x) {
		if (x <= this.thresholds[0]) {
			return(0);
		} else if (x < this.thresholds[1]) {
			return((x - this.thresholds[0]) / (this.thresholds[1] - this.thresholds[0]));
		} else if (x <= this.thresholds[2]) {
			return(1);
		}  else if (x < this.thresholds[3]) {
			return((this.thresholds[3] - x) / (this.thresholds[3] - this.thresholds[2]));
		} else {
			return(0);
		}
	}
	
	public float[] output(float y) {
		
		float t0 = this.thresholds[0];
		float t1 = this.thresholds[0] + y * (this.thresholds[1] - this.thresholds[0]);
		float t2 = this.thresholds[3] + y * (this.thresholds[2] - this.thresholds[3]);
		float t3 = this.thresholds[3];
		
		float len = t3 + t2 - t1 - t0;
		float bar = 0;
		
		bar += (2 * t1 + t0) * (t1 - t0) / 3;
		bar += (t2 + t1) * (t2 - t1);
		bar += (t3 + 2 * t2) * (t3 - t2) / 3;
		
		return(new float[]{bar/len, (y * len ) / 2});
	}

	
}
