
package pkgpublic.health.management;

import java.util.Scanner;

public class medicine {
    Scanner sc = new Scanner(System.in);
    config conf = new config();

    public void med_rec(){
        boolean res = true;
        do{
            System.out.println("\n----------------------------------");
            System.out.println("MEDICINE RECORDS");
            System.out.println("1.ADD MEDICINE");
            System.out.println("2.VIEW MEDICINE");
            System.out.println("3.UPDATE MEDICINE");
            System.out.println("4.DELETE MEDICINE");
            System.out.println("5.EXIT");

            System.out.println("-------------------------------------");
            System.out.print("Enter Selection: ");
            int act = sc.nextInt();
            medicine md = new medicine();
            
            switch(act){
                case 1:
                    md.add_med();
                    break;
                case 2:
                    md.view_med();
                    break;
                case 3:
                    md.view_med();
                    md.up_med();
                    break;
                case 4:
                    md.view_med();
                    md.med_del();
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
    
    private void add_med(){
        System.out.println("\n");
        System.out.print("Enter Medicine Name:");
        String med_name = sc.next();
        sc.nextLine();
        System.out.print("Enter Medicine Description:");
        String desc = sc.nextLine();
        System.out.print("Enter Expiration Date(MM-DD-YYYY):");
        String dte = sc.next();
        
        String sql = "INSERT INTO tbl_med (med_name,med_desc,med_exp) VALUES "
                + "(?,?,?)";
        
        conf.addRecord(sql,med_name,desc,dte);
    }
    
    public void view_med(){
        String sql = "SELECT * FROM tbl_med";
        String[] header = {"ID","Name","Description","Exptiration Date"};
        String[] col  = {"med_id","med_name","med_desc","med_exp"};
        
        conf.viewRecords(sql, header, col);
    }
    
    private void up_med(){
        System.out.println("\n");
        
        System.out.print("Enter ID to update:");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT med_id FROM tbl_med WHERE med_id=?",id)==0){
         System.out.println("Selected Id doesnmt exist!!!");
         System.out.print("\nSelect Medicine Id Again: ");
         id = sc.nextInt();   
      }
        
        System.out.print("Enter Medicine Name:");
        String med_name = sc.next();
        sc.nextLine();
        System.out.print("Enter Medicine Description:");
        String desc = sc.nextLine();
        System.out.print("Enter Expiration Date(MM-DD-YYYY):");
        String dte = sc.next();
        
        String sql = "UPDATE tbl_med SET med_name = ?,med_desc = ?, med_exp = ? WHERE med_id = ?";
        conf.updateRecord(sql,med_name,desc,dte,id);
    }
    
    private void med_del(){
        System.out.println("\n");
        System.out.print("Enter ID to Delete:");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT med_id FROM tbl_med WHERE med_id=?",id)==0){
        System.out.println("Selected Id doesnmt exist!!!");
        System.out.print("\nSelect Medicine Id Again: ");
        id = sc.nextInt();
        }
        
        System.out.print("Are you sure you want to Delete this item?(yes/no):");
        String res = sc.next();
        
        String sql = "DELETE FROM tbl_med WHERE med_id = ?";
        if(res.equals("YES")|| res.equals("yes")|| res.equals("Yes")){
            conf.deleteRecord(sql,id);
      }
    }
}
