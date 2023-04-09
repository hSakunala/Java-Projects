import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;

/* Starter code for PS8.
 */

public class MovieRanker {

	public static void main(String[] args) {
                File file = new File("ratings.txt"); //../resource/asnlib/publicdata/ratings.tsv

		ArrayList<MovieRating> rl = new ArrayList<MovieRating>();

		try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tkns = line.split("\\t"); // tabs separate tokens
                MovieRating nr = new MovieRating(tkns[0], tkns[1], tkns[2]);
                rl.add(nr);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


		int minVotes = 1;
		int numRecords = 1;
		Scanner input = new Scanner(System.in);
		
      
      while (true) {
         MaxHeap<MovieRating> ratings = new MaxHeap<>();
			System.out.println();
			System.out.println("Enter minimum vote threshold and number of records:");
			minVotes = input.nextInt();
			numRecords = input.nextInt();
			if (minVotes * numRecords == 0)
				break;
/* Fill in code to determine the top numRecords movies that have at least
 * minVotes votes. For each record mr, in decreasing order of average rating,
 * execute a System.out.println(mr).
 * Do not sort the movie list!
 */
      
      
      for (MovieRating p : rl) {
         if (p.getVotes() >= minVotes)
            ratings.insert(p);
      }
      
      int i=0;
      while(i<numRecords){
         if (ratings.isEmpty())
         {break;}
         System.out.println(ratings.removemax());
         i++;
      }
      System.out.println();

            
		}
      input.close();
	}
}