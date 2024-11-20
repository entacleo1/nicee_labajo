
package pkgpublic.health.management;

import java.util.Scanner;

public class doctors {
    Scanner sc = new Scanner(System.in);
    config conf = new config();
    
    public void doc(){
        boolean res = true;
        
        do{
            System.out.println("\n----------------------------------");
            System.out.println("DOCTOR RECORDS");
            System.out.println("1.ADD DOCTOR");
            System.out.println("2.VIEW DOCTOR");
            System.out.println("3.UPDATE DOCTOR");
            System.out.println("4.DELETE DOCTOR");
            System.out.println("5.EXIT");

            System.out.println("-------------------------------------");
            System.out.print("Enter Selection: ");
            int act = sc.nextInt();
            doctors dc = new doctors();
            
            switch(act){
                case 1:
                    dc.add_doc();
                    break;
                case 2:
                    dc.doc_view();
                    break;
                case 3:
                    dc.doc_view();
                    dc.up_doc();
                    break;
                case 4:
                    dc.doc_view();
                    dc.doc_del();
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
    
    private void add_doc(){
        System.out.println("\n");
        System.out.print("Enter Doctor Name:");
        String fname = sc.next();
        System.out.print("Enter Doctor Last Name:");
        String last = sc.next();
        
        String age;
        
        while(true){
            System.out.print("Enter Doctor Age:");
            age = sc.next();
            
            if(age.matches("\\d+")){
                break;
            }else{
                System.out.println("Invalid Input");
            }
        }
        sc.nextLine();
        System.out.print("Enter Doctor Address:");
        String doc_add = sc.nextLine();
        
        String sql = "INSERT INTO tbl_doc(doc_name,doc_last,doc_age,doc_address) VALUES (?,?,?,?) ";
        
        conf.addRecord(sql,fname,last,age,doc_add); 
    }
    
    public void doc_view(){
        String sql = "SELECT * FROM tbl_doc";
        String[] header = {"ID","Name","Last Name","Age","Address"};
        String[] col = {"doc_id","doc_name","doc_last","doc_age","doc_address"};
        
        conf.viewRecords(sql, header, col);
    }
    
    private void up_doc(){
        System.out.println("\n");
        
         System.out.print("Enter ID to update:");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT doc_id FROM tbl_doc WHERE doc_id=?",id)==0){
         System.out.println("Selected Id doesnmt exist!!!");
         System.out.print("\nSelect Doctor Id Again: ");
         id = sc.nextInt();   
      }
        
        System.out.print("Enter Doctor Name:");
        String fname = sc.next();
        System.out.print("Enter Doctor Last Name:");
        String last = sc.next();
        
        String age;
        
        while(true){
            System.out.print("Enter Doctor Age:");
            age = sc.next();
            
            if(age.matches("\\d+")){
                break;
            }else{
                System.out.println("Invalid Input");
            }
        }
        sc.nextLine();
        System.out.print("Enter Doctor Address:");
        String doc_add = sc.nextLine();
        
        String sql = "UPDATE tbl_doc SET doc_name = ?,doc_last = ?,doc_age = ?,doc_address = ? WHERE doc_id = ?";
        conf.updateRecord(sql,fname,last,age,doc_add,id);
    }
    
    private void doc_del(){
        System.out.println("\n");
        System.out.print("Enter ID to Delete:");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT doc_id FROM tbl_doc WHERE doc_id=?",id)==0){
        System.out.println("Selected Id doesnmt exist!!!");
        System.out.print("\nSelect DOctor Id Again: ");
        id = sc.nextInt();
        }
        
        System.out.print("Are you sure you want to Delete this item?(yes/no):");
        String res = sc.next();
        
        String sql = "DELETE FROM tbl_doc WHERE doc_id = ?";
        if(res.equals("YES")|| res.equals("yes")|| res.equals("Yes")){
            conf.deleteRecord(sql,id);
      }
    }
}
