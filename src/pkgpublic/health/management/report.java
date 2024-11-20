
package pkgpublic.health.management;

import java.util.Scanner;

public class report {
    
    Scanner sc = new Scanner(System.in);
    config conf = new config();
    
    Patient pat = new Patient();
    doctors dc = new doctors();
    medicine md = new medicine();
    public void rep(){
        boolean res = true;
        
        do{
            System.out.println("\n----------------------------------");
            System.out.println("RECORDS");
            System.out.println("1.CONSULTAION");
            System.out.println("2.VIEW REPORTS");
            System.out.println("3.DELETE REPORTS");
            System.out.println("4.EXIT");

            System.out.println("-------------------------------------");
            System.out.print("Enter Selection: ");
            int act = sc.nextInt();
            
            report reps = new report();
            switch(act){
                case 1:
                    reps.cons();
                    break;
                case 2:
                    reps.view_rep();
                    break;
                case 3:
                    
                    break;
                case 4:
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
    
    private void cons(){
        System.out.println("\n");
        pat.viewPatient();
        System.out.print("Enter Patient ID :");
        int pt_id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT p_id FROM tbl_patient WHERE p_id=?",pt_id)==0){
         System.out.println("Selected Id doesnmt exist!!!");
         System.out.print("\nSelect Patient Id Again: ");
         pt_id = sc.nextInt();   
      }
        
        System.out.print("Enter Consultation Date(MM-DD-YYYY):");
        String cons_date = sc.next();
        
        dc.doc_view();
        System.out.print("Enter Doctor ID:");
        int doc_id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT doc_id FROM tbl_doc WHERE doc_id=?",doc_id)==0){
         System.out.println("Selected Id doesnmt exist!!!");
         System.out.print("\nSelect Doctor Id Again: ");
         doc_id = sc.nextInt();   
      }
        sc.nextLine();
        System.out.print("Enter Findings: ");
        String cons_fnd = sc.nextLine();
        
        md.view_med();
        System.out.print("Enter Medicine ID:");
        int med_id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT med_id FROM tbl_med WHERE med_id=?",med_id)==0){
         System.out.println("Selected Id doesnmt exist!!!");
         System.out.print("\nSelect Medicine Id Again: ");
         med_id = sc.nextInt();   
      }
        
        String sql = "INSERT INTO recs (p_id,cons_date,doc_id,rec_find,med_id) VALUES (?,?,?,?,?)";
        conf.addRecord(sql,pt_id,cons_date,doc_id,cons_fnd,med_id);
    }
    
    private void view_rep(){
        String sql = "SELECT r.rec_id,p.p_fname,p.p_lname,r.cons_date,d.doc_name,d.doc_last,r.rec_find,m.med_name \n" +
                    "FROM recs r\n" +
                    "INNER JOIN tbl_patient p ON r.p_id = p.p_id\n" +
                    "INNER JOIN tbl_doc d ON r.doc_id = d.doc_id\n" +
                    "INNER JOIN tbl_med m ON r.med_id = m.med_id";
        
        String[] header = {"ID","Patient Name", "Patient Last Name","Consultation Date","Doctor's Name","Doctor's Last Name", "Findings", "Medicine"};
        String[] col = {"rec_id","p_fname","p_lname","cons_date","doc_name","doc_last","rec_find","med_name"};
        
        conf.viewRecords(sql, header, col);
    }
    
}
