import java.util.ArrayList;

public class Solution {
	
	private ArrayList<Item> included;
	private double maxProtein;
	private int finalCalCount;
	
	public ArrayList<Item> getIncluded() {
		return included;
	}


	public void setIncluded(ArrayList<Item> included) {
		this.included = included;
	}

	
	public Solution(){
		included = new ArrayList<Item>();
		setMaxProtein(0.0);
		setFinalCalCount(0);
	}
	
	
	public Solution(ArrayList<Item> a, double m, int c){
		included = a;
		setMaxProtein(m);
		setFinalCalCount(c);
	}
	
	public Solution(double m, int c) {
		included = new ArrayList<Item>();
		setMaxProtein(m);
		setFinalCalCount(c);
	}
	
	public String toString(){
		String x = "";
		for(Item a : included)
			x += a.getName() + " (" + a.getCalories() + " calories and " + a.getProtein() + "g of protein)\n";
		
		String endSentence = "For a total of " + this.getMaxProtein() + "g in " + this.getFinalCalCount() + " calories";
		
		return x + endSentence;
	}


	public double getMaxProtein() {
		return maxProtein;
	}


	public void setMaxProtein(double maxProtein) {
		this.maxProtein = maxProtein;
	}


	public int getFinalCalCount() {
		return finalCalCount;
	}


	public void setFinalCalCount(int finalCalCount) {
		this.finalCalCount = finalCalCount;
	}
}
