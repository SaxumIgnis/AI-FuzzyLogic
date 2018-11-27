package fuzzyLogic;

import java.util.HashMap;

public class Rule {

	private final HashMap<Property, String> conditions;
	private final Action action;
	private final String decision;
	
	public Rule(HashMap<Property, String> conditions, Action action, String decision) {
		this.conditions = conditions;
		this.action = action;
		this.decision = decision;
	}
	
	public Rule(Property[] prop, String[] values, Action action, String decision) {
		this.conditions = new HashMap<Property, String>();
		this.action = action;
		this.decision = decision;

		int n = prop.length;
		if (n != values.length)
			throw new java.lang.ArrayIndexOutOfBoundsException();
		
		for (int i = 0; i < n; i++)
			this.conditions.put(prop[i], values[i]);
		
	}
	
	public void apply() {
		float val = 1;
		
		for (HashMap.Entry<Property, String> entry : this.conditions.entrySet())
		{
			val = Math.min(val, entry.getKey().getValue(entry.getValue()).input(entry.getKey().getGameValue()));
			// TODO : opérateur logique ET (min pour l'instant mais peut aussi être le produit ou autre)
		}
		this.action.applyRule(this.decision, val);
	}

}
