import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


/* Starter code for PS8.
 */

public class QuizMovieRanker {

	public static void main(String[] args) {
                File file = new File("ratingsFinal.txt"); //../resource/asnlib/publicdata/ratings.tsv

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
		double rating = 1.0;
		Scanner input = new Scanner(System.in);
		
      boolean s= true;
      while (s) {
			System.out.println();
			System.out.println("Enter minimum vote threshold and number of records:");
			minVotes = input.nextInt();
			rating = input.nextDouble();
			if (minVotes * rating == 0)
				break;
/* Fill in code to determine the top numRecords movies that have at least
 * minVotes votes. For each record mr, in decreasing order of average rating,
 * execute a System.out.println(mr).
 * Do not sort the movie list!
 */
        int count = 0;
        for(int i = 0; i < rl.size(); i++) {
            if (minVotes <= rl.get(i).getVotes() && rl.get(i).getRating() <= rating) {
                System.out.println(rl.get(i));
                count++;
            }
        }

    System.out.println(count);
    
    s= false; 
		}
        input.close();
	}
}