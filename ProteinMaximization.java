

import java.util.ArrayList;
import java.util.Scanner;

public class ProteinMaximization {

	public static void main(String[] args) {
		/*test*/
		
		Item[] items = {new Item("Chicken leg", 113, 14), new Item("Tilapia", 100, 20), new Item("Bagel", 260, 9), new Item("Yogurt", 100, 10), new Item("Go Lean cereal", 155, 9), new Item("Bread", 110, 6), new Item("Peanut butter", 90, 4), new Item("Almonds", 61, 2), new Item("Milk", 78, 5), new Item("Egg", 70, 7), new Item("Deli meat", 80, 16), new Item("Salmon", 100, 21), new Item("Chicken breast", 110, 31), new Item("Whey", 110, 25), new Item("Tofu", 130, 16), new Item("Greek yogurt", 100, 18), new Item("Cottage cheese", 110, 14)};

		System.out.println(knapsack(600, items));

		
		
		/*
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
		
		
		int calCount = 0;
		int numItems = items.length;
		
		
		Solution s = knapsack(capacity, items);
		for(Item a : s.getIncluded()){
				System.out.println(a);
				calCount += a.getCalories();
		}
		s.setFinalCalCount(calCount);
		System.out.println("For a total of " + s.getMaxProtein() + "g in " + s.getFinalCalCount() + " calories");
*/
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
	
	public static int knapsack(int capacity, Item[] x){
		
		int[] sol, mySol;
	       int i, myFinalSol;

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
	             if ( C >= x[i].getCalories() )
	                mySol[i] = sol[i] + x[i].getProtein();   // Value is increased by v[i]
	                                            // because it has item i packed in
	                                            // it already
	             else
	                mySol[i] = 0;        // Not enough space to  pack item i
	          }

	          /* *************************
	             Find the best (maximum)
	             ************************* */
	          M[C] = mySol[0];
	          for ( i = 1; i < x.length; i++ )
	             if ( mySol[i] > M[C] )
	                 M[C] = mySol[i];
	       }

	       return M[capacity];
		
	}

}
