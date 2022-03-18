
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProteinMaximization {

	public static void main(String[] args) {
		
		Item[] items = {
				new Item("Yogurt", 100, 10),
				new Item("Egg", 70, 7),
				new Item("Deli meat", 80, 16),
				new Item("Salmon", 100, 21),
				new Item("Chicken breast", 110, 31),
				new Item("Whey", 110, 25),
				new Item("Tofu", 130, 16),
				new Item("Greek yogurt", 100, 18),
				new Item("Cottage cheese", 110, 14),
				new Item("Skyr", 86, 16)
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
		
		
		Solution s = knapsack2(capacity, items);
		System.out.println("For a total of " + s.getMaxProtein() + "g in " + s.getFinalCalCount() + " calories");

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
	
	public static Solution knapsack(int capacity, Item[] x){
		
		int[] sol, mySol;
	       int i, myFinalSol;
	       ArrayList<Item> test = new ArrayList<Item>();

	       int[] M;                 // Data structure to store results
	       int   C;                 // Index to run through M[]

	       sol   = new int[x.length];
	       mySol = new int[x.length];

	       M = new int[capacity + 1];      // Create array

	       /* ---------------------------
	          Base cases
	          --------------------------- */
	       M[0] = 0;

	       /* ==============================================
	          The other values M[C]
	          ============================================== */
	       for ( C = 1; C <= capacity; C++ )
	       {
	          /* ---------------------------------------
	             Solve the appropriate smaller problems
	             --------------------------------------- */
	          for ( i = 0; i < x.length; i++ )
	          {
	             if ( C >= x[i].getCalories() )
	                sol[i] = M[ C-x[i].getCalories() ]; // Knapsack capacity reduced by w[i]
	                                      // because it has item i packed in
	                                      // it already
	             else
	                sol[i] = 0;        // Not enough space to  pack item i
	          }

	          /* ---------------------------------------------
	             Use the solutions to the smaller problems
	             to solve original problem
	             --------------------------------------------- */
	          for ( i = 0; i < x.length; i++ )
	          {
	             if ( C >= x[i].getCalories() ) {
	                mySol[i] = sol[i] + x[i].getProtein();   // Value is increased by v[i]
	                                            // because it has item i packed in
	                                            // it already
	             }
	             else {
	                mySol[i] = 0;        // Not enough space to  pack item i
	             }
	          }

	          /* *************************
	             Find the best (maximum)
	             ************************* */
	          M[C] = mySol[0];
	          for ( i = 1; i < x.length; i++ )
	             if ( mySol[i] > M[C] )
	                 M[C] = mySol[i];
	       }
	       
	       int calCount = 0;
	       for(Item a : test){
				System.out.println(a);
				calCount += a.getCalories();
	       }
	       Solution solution = new Solution(test, M[capacity], calCount);

	       //return M[capacity];
	       return solution;
		
	}
	
	public static Solution knapsack2(int capacity, Item[] items) {
		
		int NB_ITEMS = items.length;
		// we use a matrix to store the max value at each n-th item
		int[][] matrix = new int[NB_ITEMS + 1][capacity + 1];

		// first line is initialized to 0
		for (int i = 0; i <= capacity; i++)
		  matrix[0][i] = 0;

		// we iterate on items
		for (int i = 1; i <= NB_ITEMS; i++) {
		  // we iterate on each capacity
		  for (int j = 0; j <= capacity; j++) {
		    if (items[i - 1].getCalories() > j)
		      matrix[i][j] = matrix[i-1][j];
		    else
		      // we maximize value at this rank in the matrix
		      matrix[i][j] = Math.max(matrix[i-1][j], matrix[i-1][j - items[i-1].getCalories()] +
		                              items[i-1].getProtein());
		  }
		}
		
		int res = matrix[NB_ITEMS][capacity];
		int w = capacity;
		ArrayList<Item> itemsSolution = new ArrayList<>();

		for (int i = NB_ITEMS; i > 0  &&  res > 0; i--) {
		  if (res != matrix[i-1][w]) {
		    itemsSolution.add(items[i-1]);
		    // we remove items value and weight
		    res -= items[i-1].getProtein();
		    w -= items[i-1].getCalories();
		  }
		}
		
		int calCount = 0;
	    for(Item a : itemsSolution){
			System.out.println(a);
			calCount += a.getCalories();
	    }
		
		return new Solution(itemsSolution, matrix[NB_ITEMS][capacity], calCount);
		
	}

}
