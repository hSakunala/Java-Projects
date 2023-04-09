import java.util.Scanner;
public class Sums {
   public static String sums(String s, long n)
   {
      for(int i=1;i<s.length();i++)
      {
         String str1= s.substring(0,i);
         String str2= s.substring(i);
         
         Long num1= Long.parseLong(str1);
         Long num2= Long.parseLong(str2);
         
         if(num1+num2==n)
            return str1+ "+" +str2;
         else if(num1< n && sums(str2,n-num1)!="false")
            return (str1+ "+" +sums(str2, n-num1));
      }
         return "false";
   }
      
   public static void main(String[] args)
   {
      Scanner input= new Scanner(System.in);
      String strNum= input.next();
      Long total= input.nextLong();
      System.out.println(sums(strNum,total));
      input.close();
   }
}