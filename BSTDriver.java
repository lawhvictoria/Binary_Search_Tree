import java.util.Iterator;
import java.util.Scanner;

public class BSTDriver {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in); //scanner object
		
		BST<Integer> bst = new BST<Integer>(); //created as an empty tree
		
		System.out.println("Choose one of the following operations by entering provided letter: \n"
				+ "a add the element \n"
				+ "b delete the element \n"
				+ "f find the element \n"
				+ "e check if the tree is empty \n"
				+ "k make the tree empty \n"
				+ "n get the number of vertices in the tree \n"
				+ "m find the minimal element \n"
				+ "x find the maximal element \n"
				+ "p print the tree in pre-order using iterator \n"
				+ "i print the tree in in-order using iterator \n"
				+ "l print the tree in level-order using iterator \n"
				+ "t print the tree using printTree \n"
				+ "o output the tree using toString \n"
				+ "q quit the program \n");
		
		boolean cont = true;
		while(cont)
		{
			System.out.println("Please enter a menu choice");
			String choice = scan.next();
			
			switch(choice)
			{
			case "a":
				System.out.println("Please enter the item you would like to add");
				try{
					int add = scan.nextInt();
					bst.insert(add);
					System.out.println(add + " is added to the Binary Search Tree");
				}
				catch(Exception e)
				{
					System.out.println("Please enter an integer");
				}
				scan.nextLine();
				break;
				
			case "b":
					System.out.println("Please enter the item you would like to delete");
				try{
					int delete = scan.nextInt();
					bst.delete(delete);
					System.out.println(delete + " has been deleted from the Binary Search Tree");
				}
				catch(Exception MyException)
				{
					if(bst.size() == 0)
					{
						System.out.println("There is nothing in the tree");
					}
					else
					{
						System.out.println("The item is not in the tree");
					}
				}
				break;
				
			case "f":
				System.out.println("Please enter the item you would like to find");
				try{
					int find = scan.nextInt();
					System.out.println(bst.find(find));
				}
				catch(Exception MyException)
				{
					System.out.println("Please enter an integer!");
				}
				scan.nextLine();
				break;
				
			case "e":
				System.out.println(bst.isEmpty());
				break;
				
			case "k":
				bst.makeEmpty();
				System.out.println("The tree has been emptied");
				break;
				
			case "n":
				System.out.println("The number of vertices is " + bst.size());
				break;
				
			case "m":
				try{
					System.out.println("The smallest item in the tree is " + bst.findMinimum());
				}
				catch(Exception MyException)
				{
					System.out.println("There is nothing in the tree.");
				}
				break;
				
			case "x":
				try{
					System.out.println("The largest item in the tree is " + bst.findMaximum());
				}
				catch(Exception MyException)
				{
					System.out.println("There is nothing in the tree.");
				}
				break;
				
			case "p":
				Iterator<Integer> iter = bst.iteratorPre();
				while(iter.hasNext())
				{
					System.out.print(iter.next() + " ");
				}
				System.out.println();
				break;
				
			case "i":
				Iterator<Integer> iter2 = bst.iteratorIn();
                while (iter2.hasNext()) {
                    System.out.print(iter2.next() + " ");
                }
                System.out.println();
				break;
				
			case "l":
				Iterator<Integer> iter3 = bst.iteratorLevel();
                while (iter3.hasNext()) {
                    System.out.print(iter3.next() + " ");
                }
                System.out.println();
				break;
				
			case "t":
				try{
					bst.printTree();
				}
				catch(Exception MyException)
				{
					System.out.println("Tree is Empty");
				}
				break;
				
			case "o":
				bst.toString();
				System.out.println();
				break;
				
			case "q":
				System.out.println("Farewell");
				cont = false;
				break;
				
			default:
				System.out.println("Invalid Choice");
				break;
			}
		}
	}
}
