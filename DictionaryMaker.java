import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import java.util.Iterator;

public class DictionaryMaker {

	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		BST<String> words = new BST<String>();
		Scanner fileReader;
		
		System.out.print("Please enter input file name: ");
		
		try
		{
			String file = scan.nextLine();
			fileReader = new Scanner(new File(file));
		}
		
		catch(FileNotFoundException e)
		{
			System.out.println("404! File Not Found!");
			return;
		}
		
		System.out.print("Please enter output file name: ");
		String output = scan.nextLine();
		
		while(fileReader.hasNext())
		{
			String word = fileReader.next();
			
			if(!words.find(word))
			{
				words.insert(word);
			}
		}
		
		Iterator<String> iter = words.iteratorIn();
		
		PrintWriter p;
		
		try
		{
			p = new PrintWriter(output);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("404! File Not Found!");
			return;
		}
		
		while(iter.hasNext())
		{
			p.println(iter.next());
		}
		
		p.close();
	}
	
}
