import java.io.*;
import java.util.*;
public class lengthHomophoes{  
   public static void main(String[] args){
      UALDictionary< String, Pronunciation> PDict = new UALDictionary<String,Pronunciation>();
      OALDictionary<String, ArrayList> newDict = new OALDictionary<String,ArrayList>();
      File file = new File("cmudict.0.7a.txt");
      Scanner input = new Scanner(System.in);
       int len = input.nextInt();
       try {
          Scanner scanner = new Scanner(file);
          while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.substring(0, 3).equals(";;;"))
               continue;
            Pronunciation p = new Pronunciation(line);
            if ((p.getWord().length() < len) || (p.getWord().length() > len))
               continue;
            if ((p.getWord().length() == len))
            PDict.insert(p.getWord(), p);
          }
          scanner.close();
       } catch (FileNotFoundException e) {
            e.printStackTrace();
       }
       for (Pronunciation p : PDict.values()) {
         if (newDict.find(p.getPhonemes()) == null) {
            ArrayList<String> array = new ArrayList<String>();
            array.add(p.getWord());
            if (p.getWord().length() == len){
               newDict.insert(p.getPhonemes(), array);
            }
         }
         else{
            if (p.getWord().length() == len){
               newDict.find(p.getPhonemes()).add(p.getWord());
            }
         }
      }
      int doesnt = 0;
      for (ArrayList<String> array : newDict.values()){
         if (array.size() < 2){
            doesnt+=1;
         }
      }
      System.out.println(doesnt);
      input.close();
   }
}