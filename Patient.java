
package pkgpublic.health.management;

import java.util.Scanner;


public class Patient {
    
    
    public void pRecord(){
        Scanner sc = new Scanner(System.in);
        String response;
        do{
        
        System.out.println("\n----------------------------------");
        System.out.println("PATIENTS RECORDS");
        System.out.println("1.ADD PATIENT");
        System.out.println("2.VIEW PATIENT");
        System.out.println("3.UPDATE CUSTOMER");
        System.out.println("4.DELETE CUSTOMER");
        System.out.println("5.EXIT");
        
        System.out.println("-------------------------------------");
        System.out.print("Enter Selection");
        
        int act = sc.nextInt();
        Patient pt = new Patient();
        switch (act){
            
            case 1:
                pt.addPatient();
               
                
                
             break;
            case 2:
              pt.viewPatient(); 
                
                
                
             break;
             
            case 3:
                pt.updatePatient();
                
                
                break;
                
                
                
                
                
                
                
                
                
                
                
            
        }
        
            System.out.println("Do you want to continue? (yes/no)");
            response = sc.next();
        }while(response.equalsIgnoreCase("yes"));
        
        
     
        
    }    
    
    public void addPatient(){
        
       Scanner sc = new Scanner(System.in);
        System.out.print("Patient Firstname: ");
        String fname =sc.next();
        System.out.print("Patient Lastname: ");
        String lname = sc.next();
        sc.nextLine();
        System.out.print("Patient Address: ");
        String address = sc.nextLine();
        System.out.print("Patient Email: ");
        String email = sc.nextLine();
        
     
      
        String qry = "INSERT INTO tbl_patient(p_fname,p_lname,p_address,p_email)VALUES(?, ? , ? ,?)";
        
        config conf = new config();
        
        conf.addRecord(qry, fname,lname,address,email);
       
    }
    
      private void viewPatient() {
        String qry = "SELECT * FROM tbl_patient";
        String[] hdrs = {"ID", "FName", "LName", "Address","Email"};
        String[]clms = {"p_id", "p_fname", "p_lname","p_address", "p_email"};
config conf = new config();
        conf.viewRecords(qry, hdrs,clms);
    }  
      
      
      private void updatePatient() {
            Scanner sc = new Scanner(System.in);
                   config conf = new config();
          System.out.println("Enter ID to Update: ");
          int id = sc.nextInt();
          
          while(conf.getSingleValue("SELECT p_id FROM tbl_patient WHERE p_id=?",id)==0){
              
              System.out.println("Selected Id doesnmt exist!!!");
              System.out.println("Select Customer Id Again: ");
              id = sc.nextInt();  
              
              
              
              
          }
          
          
          System.out.println("New Patient First Name: ");
          String fname =sc.next();
          System.out.print("New Patient Lastname: ");
        String lname = sc.next();
                sc.nextLine();
                System.out.print("New Patient Address: ");
        String address = sc.nextLine();
         System.out.print("New Patient Email: ");
        String email = sc.nextLine();
        
          
          
          
          
          
          
          
          
          
          
          
          String qry = "UPDATE tbl_patient SET ,p_fname = ?,p_lname = ?, p_address = ?, p_email= ?  WHERE p_id=?";
      
       conf.updateRecord(qry,fname, lname, address, email,id);
    
    
}

}