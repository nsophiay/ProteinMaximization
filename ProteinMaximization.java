
import java.util.ArrayList;
import java.util.Scanner;

public class ProteinMaximization {

	public static void main(String[] args) {

		Item[] items = {
				new Item("Yogurt", 60, 10.0, 175),
				new Item("Egg", 70, 7.0, 50),
				new Item("Deli meat, chicken breast", 90, 16.0, 100),
				new Item("Salmon", 228, 31.8, 125),
				new Item("Chicken breast", 260, 46.4, 150),
				new Item("Whey protein powder", 144, 36, 40),
				new Item("Extra firm tofu", 193, 23.2, 175),
				new Item("Cottage cheese 1%", 100, 14, 125),
				new Item("Plain skyr", 157, 28.6, 250)
		};

		Scanner input = new Scanner(System.in);

		System.out.println("Enter the number of calories you want to eat:");
		int capacity = input.nextInt();

		System.out.println("Do you have chicken available? (yes : no)");
		input.nextLine();
		String ani = input.nextLine();
		boolean animal = (ani.equals("yes")?true:false);

		if(!animal){
			items = remove(items, "Chicken breast");
			items = remove(items, "Chicken leg");
		}
		System.out.println("Do you have fish available? (yes : no)");
		ani = input.nextLine();
		animal = (ani.equals("yes")?true:false);

		if(!animal){
			items = remove(items, "Salmon");
			items = remove(items, "Tilapia");
		}


		Solution s = knapsack(capacity, items);
		System.out.println(s);
		//System.out.println("For a total of " + s.getMaxProtein() + "g in " + s.getFinalCalCount() + " calories");

	}

	public static Item[] remove(Item[] arr, String name){

		ArrayList<Item> temp = new ArrayList<Item>();

		for(int i = 0; i < arr.length; i++)
			temp.add(arr[i]);

		for(int i = 0; i < temp.size(); i++){
			if(temp.get(i).getName().equals(name))
				temp.remove(i);
		}

		Item[] result = new Item[temp.size()];

		for(int i = 0; i < temp.size(); i++)
			result[i] = temp.get(i);

		return result;
	}


	public static Solution knapsack(int capacity, Item[] items) {

		int NB_ITEMS = items.length;
		// we use a matrix to store the max value at each n-th item
		double[][] matrix = new double[NB_ITEMS + 1][capacity + 1];
		
		//---------Matrix---------//
		//   0 1 2 3 4 ... capacity
		// 0
		// 1
		// 2
		// 3
		// ..etc.
		//------------------------//
		
		// Initialize first line of matrix to zero, since the max amount of protein for zero items is zero
		for (int i = 0; i <= capacity; i++)
			matrix[0][i] = 0;

		// Iterate through each item, checking the max protein possible for when it's included and when it's not included
		for (int i = 1; i <= NB_ITEMS; i++) {
			
			for (int currentCapacity = 0; currentCapacity <= capacity; currentCapacity++) {
				
				int currentItem = i-1; // Since we started the loop at the second row, we have to subtract one to get the first item
				
				if (items[currentItem].getCalories() > currentCapacity) // Check if number of calories in just this item is over the currentCapacity
					matrix[i][currentCapacity] = matrix[i-1][currentCapacity]; // If so, the max protein value would just be that of the previous item, since we can't fit in the current item
				else
					matrix[i][currentCapacity] = Math.max(matrix[i-1][currentCapacity], matrix[i-1][currentCapacity - items[currentItem].getCalories()] +
							items[currentItem].getProtein());
					// matrix[i-1][cC] represents the max protein we can get WITHOUT the current item
					// matrix[i-1][cC - items[cI].calories] + item[cI].protein represents the max protein we can get if previous items AND the current item are included
			}
		}

		double maxProtein = matrix[NB_ITEMS][capacity];
		int calorieLimit = capacity;
		int calCount = 0;
		ArrayList<Item> itemsSolution = new ArrayList<>();

		// Find the items that were used to get the most optimal solution
		for (int i = NB_ITEMS; i > 0  &&  maxProtein > 0; i--) {
			if (maxProtein != matrix[i-1][calorieLimit]) {
				itemsSolution.add(items[i-1]);
				calCount += items[i-1].getCalories(); // Add included item's calories to the total calorie count
				// Remove that item's protein and calories
				maxProtein -= items[i-1].getProtein();
				calorieLimit -= items[i-1].getCalories();
			}
		}

		return new Solution(itemsSolution, matrix[NB_ITEMS][capacity], calCount);

	}

}
