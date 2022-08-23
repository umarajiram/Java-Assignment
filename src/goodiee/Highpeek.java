package goodiee;

import java.io.FileInputStream ;
import java.io.FileWriter ;
import java.util.ArrayList ;
import java.util.Collections ;
import java.util.Comparator ;
import java.util.Scanner ;

class Item
{
	String name ;
	int price ;

	public Item( String name , int price )
	{
		this.name = name ;
		this.price = price ;
	}

	public String toString( )
	{
		return this.name + ": " + this.price ;
	}
}

public class Highpeek
{
	public static void main (String[]args) throws Exception
	{
		FileInputStream fistm=new FileInputStream ( "sampleinput.txt" ) ;
		
		Scanner s1=new Scanner(fistm) ;
		int number_of_employees = Integer.parseInt (s1.nextLine( ).split ( ": " ) [ 1 ] ) ;
		s1.nextLine();
		s1.nextLine();

		ArrayList < Item > goodies_items=new ArrayList < Item > ( ) ;

		while ( s1.hasNextLine( ))
		{
			String current[] = s1.nextLine ( ).split ( ": " ) ;
			goodies_items.add(new Item (current [ 0 ] , Integer.parseInt(current [ 1 ] ) ) ) ;
		}
		s1.close ( ) ;

		Collections.sort( goodies_items , new Comparator < Item > ( )
		{
			public int compare( Item a , Item b )
			{
				return a.price - b.price ;
			}
		} ) ;

		int min_diff = goodies_items.get(goodies_items.size( ) - 1 ).price ;
		int min_index = 0 ;
		for ( int i = 0 ; i < goodies_items.size ( ) - number_of_employees + 1 ; i ++ )
		{
			int diff = goodies_items.get(number_of_employees + i - 1 ).price - goodies_items.get ( i ).price ;
			if (diff <= min_diff)
			{
				min_diff = diff ;
				min_index = i ;
			}
		}

		FileWriter f1=new FileWriter ( "sampleoutput5.txt" ) ;
		f1.write ( "The goodies selected for distribution are:\n\n" ) ;
		for ( int i = min_index ; i < min_index + number_of_employees ; i ++ )
		{
			f1.write (goodies_items.get ( i ).toString ( ) + "\n" ) ;
		}

		f1.write (
				"\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_diff
		) ;
		f1.close ( ) ;
	}
}