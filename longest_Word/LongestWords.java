import java.io.FileNotFoundException;
import java.io.File;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Collections;

public class LongestWords {
       
   public static ArrayList<String> getWords(String word) {
      ArrayList<String> wordList = new ArrayList<String>();
      Queue<CState> q = new LinkedList<CState>();    
      
      CState start = new CState("", word);    
      q.add(start);    
      while (!q.isEmpty()) {      
         CState next = q.poll(); // dequeue      
         if (next.suff().length() > 0) {        
            String newPrefix = next.pre() + next.suff().charAt(0);        
            String newSuffix = next.suff().substring(1);
            
            wordList.add(newPrefix);
            
            CState n1 = new CState(newPrefix, newSuffix);        
            CState n2 = new CState(next.pre(), newSuffix);        
            q.add(n1);        
            q.add(n2);
         }
      }
      return wordList;
   }
      
   
   
   public static void main(String[] args) {
      UALDictionary<String, WordPair> UALDict= new UALDictionary<>();
      File file = new File("words.txt");
      Scanner inFile = null;
      
      try {
         inFile= new Scanner(file);
         while (inFile.hasNextLine()) {
            String line = inFile.nextLine();
            WordPair pair= new WordPair(line);
            UALDict.insert(pair.getSorted(),pair);
         }
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      inFile.close();
      
      Scanner scan= new Scanner(System.in);
      String word= scan.nextLine();
      
      ArrayList<String> wordList= new ArrayList<>();
      ArrayList<String> anagList= getWords(word);
      
      for (int a=0; a<anagList.size();a++) {
         WordPair wPair= new WordPair(anagList.get(a));
         for (WordPair w : UALDict.findAll(wPair.getSorted())) {
            wordList.add(w.getUnsorted());
         }
      }
      wordList.sort((second,first) -> Integer.compare(first.length(), second.length()));
      
      ArrayList<String> sList = new ArrayList<>();
      for (int b=0; b< wordList.size(); b++) {
         if (wordList.get(b).length() == wordList.get(0).length())
            sList.add(wordList.get(b));
         else
            break;
      }
      scan.close();
      
      Collections.sort(sList);
      
      for(String counter: sList){
			System.out.println(counter);
		}
   }
}