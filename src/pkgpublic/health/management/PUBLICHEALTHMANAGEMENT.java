
package pkgpublic.health.management;

import java.util.Scanner;


public class PUBLICHEALTHMANAGEMENT {
    
    
      

   public static void main(String[] args){
       
       Scanner sc = new Scanner(System.in);
       boolean exit = true;
       
       
       do{
           System.out.println("---------------------------------");
       System.out.println("WELCOME TO THE APP");
       System.out.println("1.Patient");
       System.out.println("2.Medicine");
       System.out.println("3.Medical Report Recording");
           System.out.println("4.EXIT");
       
       
       
       
       System.out.print("Enter Action:");
       int action = sc .nextInt();
       
       
       switch(action){
           case 1:
               Patient pt = new Patient();
                pt.pRecord();
                        
               break;
               
               
           case 4:
               System.out.print("Exit Selected...type 'yes' to continue:");
               String resp =sc.next();
               
               if(resp.equalsIgnoreCase("yes")){
               exit = false;
               
               break;
               
               
       }
               
       
       
           
       }
       
    }while(exit);
    
   }
}
