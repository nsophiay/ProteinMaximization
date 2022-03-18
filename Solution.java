import java.util.ArrayList;

public class Solution {
	
	private ArrayList<Item> included;
	private int maxProtein;
	private int finalCalCount;
	
	public ArrayList<Item> getIncluded() {
		return included;
	}


	public void setIncluded(ArrayList<Item> included) {
		this.included = included;
	}

	
	public Solution(){
		included = new ArrayList<Item>();
		setMaxProtein(0);
		setFinalCalCount(0);
	}
	
	
	public Solution(ArrayList<Item> a, int m, int c){
		included = a;
		setMaxProtein(m);
		setFinalCalCount(c);
	}
	
	public Solution(int m, int c) {
		included = new ArrayList<Item>();
		setMaxProtein(m);
		setFinalCalCount(c);
	}
	
	public String toString(){
		String x = "";
		for(Item a : included)
			x += a;
		return x;
	}


	public int getMaxProtein() {
		return maxProtein;
	}


	public void setMaxProtein(int maxProtein) {
		this.maxProtein = maxProtein;
	}


	public int getFinalCalCount() {
		return finalCalCount;
	}


	public void setFinalCalCount(int finalCalCount) {
		this.finalCalCount = finalCalCount;
	}
}
