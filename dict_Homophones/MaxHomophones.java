import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/** Identify a common 5-letter English word that has the following property:
 * Removing the first letter or the second letter results in words with the
 * same pronunciation as the original word.
 */

public class MaxHomophones {
 
	public static void main(String[] args) {
		OALDictionary<String, Pronunciation> PDict = new OALDictionary<String, Pronunciation>();
      File file = new File("cmudict.0.7a.txt");
      
      Scanner input = new Scanner(System.in);
      int num= input.nextInt();
      input.close();
      
		try {
			Scanner scanner = new Scanner(file);
         while(scanner.hasNextLine() && num != 0){
				String line = scanner.nextLine();
				if (line.substring(0, 3).equals(";;;"))
               continue;          
        		Pronunciation p = new Pronunciation(line);
     			PDict.insert(p.getPhonemes(), p);
            num--;
         }

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
            
      UALDictionary<String, ArrayList<Pronunciation>> homophones = new UALDictionary<>();
      ArrayList<String> found = new ArrayList<>();
      int size= 0;
      
      
      for (Pronunciation p : PDict.values()) {
			String w= p.getPhonemes();
			if (!found.contains(w)) {
            ArrayList<Pronunciation> word = (ArrayList<Pronunciation>) PDict.findAll(w);
            if(word.size() >= size) {
               homophones.insert(w,word);
               found.add(w);
               if(word.size()> size)
                  size= word.size();
            }
         }
      }
      
      System.out.println(size);
      
      for (ArrayList<Pronunciation> p : homophones.values()) {
         if(p.size() == size){
            p.forEach(s -> System.out.println(s.getWord()));
            System.out.println("");   
         }    
      }
      
   }
}

