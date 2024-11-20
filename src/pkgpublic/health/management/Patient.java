
package pkgpublic.health.management;

import java.util.Scanner;


public class Patient {
    Scanner sc = new Scanner(System.in);
    config conf = new config();
    public void pRecord(){
       
        boolean res = true;
        do{
        System.out.println("\n----------------------------------");
        System.out.println("PATIENTS RECORDS");
        System.out.println("1.ADD PATIENT");
        System.out.println("2.VIEW PATIENT");
        System.out.println("3.UPDATE CUSTOMER");
        System.out.println("4.DELETE CUSTOMER");
        System.out.println("5.EXIT");
        
        System.out.println("-------------------------------------");
        System.out.print("Enter Selection: ");
        
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
                pt.viewPatient();
                pt.updatePatient(); 
                break;
            case 4:
                pt.viewPatient();
                pt.patient_delete();
                break;
            case 5:
                System.out.print("\nDo you want to exit?(yes/no):");
                String resp = sc.next();
                
                if(resp.equals("yes")||resp.equals("Yes")){
                  res = false;
                }
                
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
        
        }while(res);
        
    }    
    
    public void addPatient(){
        System.out.println("\n");
       
        System.out.print("Patient Firstname: ");
        String fname =sc.next();
        System.out.print("Patient Lastname: ");
        String lname = sc.next();
        sc.nextLine();
        
        String age;
        while(true){
            System.out.print("Enter Patient Age:");
            age = sc.next();
            
            if(age.matches("\\d+")){
                break;
            }else{
                System.out.println("Invalid Input");
            }
            
        }
        sc.nextLine();
        System.out.print("Patient Address: ");
        String address = sc.nextLine();
       
        System.out.print("Patient Email: ");
        String email = sc.nextLine();
        
        String qry = "INSERT INTO tbl_patient(p_fname,p_lname,p_age,p_address,p_email)VALUES(?, ? , ? ,?,?)";
        conf.addRecord(qry, fname,lname,age,address,email);
       
    }
    
     public void viewPatient() {
        String qry = "SELECT * FROM tbl_patient";
        String[] hdrs = {"ID", "First Name", "Last Name","Age", "Address","Email"};
        String[]clms = {"p_id", "p_fname", "p_lname","p_age","p_address", "p_email"};
        conf.viewRecords(qry, hdrs,clms);
    }  
      
      
    private void updatePatient() {
        System.out.println("\n");
      System.out.print("\nEnter ID to Update: ");
      int id = sc.nextInt();

      while(conf.getSingleValue("SELECT p_id FROM tbl_patient WHERE p_id=?",id)==0){
          System.out.println("Selected Id doesnmt exist!!!");
          System.out.print("\nSelect Patient Id Again: ");
          id = sc.nextInt();   
      }
      System.out.print("\nNew Patient First Name: ");
      String fname =sc.next();
      System.out.print("New Patient Lastname: ");
      String lname = sc.next();
      sc.nextLine();
     
      String age;
        while(true){
            System.out.print("Enter Patient Age:");
            age = sc.next();
            
            if(age.matches("\\d+")){
                break;
            }else{
                System.out.println("Invalid Input");
            }
            
        }
      sc.nextLine();
      System.out.print("New Patient Address: ");
      String address = sc.nextLine();
      
      System.out.print("New Patient Email: ");
      String email = sc.nextLine();


     String qry = "UPDATE tbl_patient SET p_fname = ?,p_lname = ?,p_age = ?, p_address = ?, p_email= ?  WHERE p_id=?";
       conf.updateRecord(qry,fname, lname,age, address, email,id);
    }
    
    private void patient_delete(){
        System.out.println("\n");
        
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT p_id FROM tbl_patient WHERE p_id=?",id)==0){
          System.out.println("Selected Id doesnmt exist!!!");
          System.out.print("\nSelect Patient Id Again: ");
          id = sc.nextInt();   
      }
        String sql = "DELETE FROM tbl_patient WHERE p_id = ?";
        conf.deleteRecord(sql, id);  
    }

}