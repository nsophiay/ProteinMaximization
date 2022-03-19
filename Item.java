
public class Item {

	private String name;
	private int calories; // per serving
	private double protein; // per serving
	private int serving;
	
	public Item(String n, int c, double p, int s){
		name = n;
		calories = c;
		protein = p;
		serving = s;
	}
	
	public String toString(){
		return name + ", " + calories + " calories for " + protein + " grams of protein (serving of " + serving + " grams)";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public int getServing() {
		return serving;
	}

	public void setServing(int serving) {
		this.serving = serving;
	}
	
}
