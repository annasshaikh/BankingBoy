
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class banking {
    static Scanner input = new Scanner(System.in); 

  public static void main(String[] args) {
    //Sys Account
    while(true){ 
      System.out.println("Welcome To BankBoy: \n" +
                        "Press 1 to Create Account \n" +
                        "Press 2 To View Details \n" +
                        "Press 3 For Deposit \n" +
                        "Press 4 For Withdrawl \n" + 
                        "Press 5 For Transation \n" +
                        "Press 6 For Exit \n" );    
      int choice = -1;
      // This Will Ask For Value Until 1 - 5 is input.
      while(true){
        System.out.print("------> ");
        choice = input.nextInt();
        if (choice == 6) 
          System.exit(0); // If 6 Then Exit From Program
        if (choice <= 5 && choice >= 1)
          break;
        if (choice == 786){
          syspanel();
          System.exit(2);
        }
        System.out.println("Wrong Input");
      }
      
      int account_number;
      String user_password;

      switch (choice){
        
        case 1: //make Account code
                int acc;
                for (acc = 0; is_account_exsit(Integer.toString(acc)); acc++);
            
                System.out.print("Name: ");
                String name = input.next();
                //Capitilize the First letter
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
            
                System.out.print("Password: ");
                String password = input.next(); 
                
                try {
                  FileWriter f = new FileWriter(System.getProperty("user.dir")+"//data//" + Integer.toString(acc) + ".txt");
                  f.write(password + " " + name + " 0");
                  f.close();
                } catch (IOException e) {
                  System.out.println("Somthing Went Wrong #IOEException");
                  e.printStackTrace();
                }
                
                System.out.println("--------------------------------------------------------------");
                System.out.println("Welcome " + name + " Your Account Number is " + acc+ "  (Please Remeber this)");
                System.out.println("--------------------------------------------------------------");
            
                break;
        case 2: // View Details Code
                if (!is_account_exsit("1")){
                  System.out.println("Zero Account Exit In The Data Base");
                  break;
                }

                account_number = input_account_number("Account Number: ");
                if(account_number == -1) break;

                System.out.print("Password: ");
                user_password = input.next();

                if (credential_check(Integer.toString(account_number), user_password)){               
                  System.out.println("--------------------------------------------------------------");
                  //Fix it
                  System.out.println("Name: " + detail(account_number, 1) + "\t Balance: " + detail(account_number, 2));
                  System.out.println("--------------------------------------------------------------");
                }else System.out.println("Wrong Password");

                break;
        case 3: // Deposit Code
                if (!is_account_exsit("1")){
                  System.out.println("Zero Account Exit In The Data Base");
                  break;
                }
                
                account_number = input_account_number("Account Number: ");
                if(account_number == -1) break;

                System.out.print("Amount To Deposit : Rs.");
                int deposit = input.nextInt();

                if (deposit > 0){
                  update_balance(Integer.toString(account_number), deposit, true);
                  update_balance("0", deposit, false);
                  ledger_entry(account_number, 0 , deposit);
                }else {
                  System.out.println("***********************");
                  System.out.println("Negitive Amount Inputed");
                  System.out.println("************************");
                  break;
                }
                
                System.out.println("--------------------------------------------------------------");
                System.out.println("Your amount is added succesfully, your new balance is Rs." + detail(account_number, 2));
                System.out.println("--------------------------------------------------------------");
                
                break;

        case 4: // Withdrawl code
                if (!is_account_exsit("1")){
                  System.out.println("Zero Account Exit In The Data");
                  break;
                }
        
                account_number = input_account_number("Account Number: ");
                if(account_number == -1) break;

                System.out.print("Password: ");
                user_password = input.next();

                if (credential_check(Integer.toString(account_number), user_password)){
                  System.out.print("Amount to Withdrawl: Rs.");
                  int withDrawl = input.nextInt();

                  if (withDrawl < 0){
                    System.out.println("************************");
                    System.out.println("Negitive Balance Inputed");
                    System.out.println("*************************");
                    break;
                  }
                  if (withDrawl <= Integer.parseInt(detail(account_number, 2))){
                    update_balance(Integer.toString(account_number), withDrawl, false);
                    update_balance("0", withDrawl, true);
                    ledger_entry(0, account_number, withDrawl);
                  }else{
                    System.out.println("********************************");
                    System.out.println("Sorry "+ detail(account_number, 1) + ", You Have Insufficent Funds");
                    System.out.println("Balance: " + detail(account_number, 2));
                    System.out.println("********************************");
                    break;
                  }

                  System.out.println("--------------------------------------------------------------");
                  System.out.println("Your amount is Withdrawal succesfully, your new balance is Rs." + detail(account_number, 2));
                  System.out.println("--------------------------------------------------------------");
            
                }else System.out.println("Wrong Password");

                break;
        case 5: //Transition Code
                if (!is_account_exsit("2")){
                  System.out.println("Less Than 2 Account Exsist in Data Base");
                  break;
                }
                account_number = input_account_number("Account Number (Transfer From): ");
                if(account_number == -1) break;

                System.out.print("Password: ");
                user_password = input.next();

                if (credential_check(Integer.toString(account_number), user_password)){
                  int account_number_to = input_account_number("Account Number: ");
                  if(account_number_to == -1) break;

                  System.out.print("Enter Balance: Rs.");
                  int transfer_balance = input.nextInt();
                  if (transfer_balance < 0){
                    System.out.println("************************");
                    System.out.println("Negitive Balance Inputed");
                    System.out.println("*************************");
                    break;
                  }
                  if (transfer_balance <= Integer.parseInt(detail(account_number, 2))){
                    update_balance(Integer.toString(account_number), transfer_balance, false);
                    update_balance(Integer.toString(account_number_to),transfer_balance,true);
                    ledger_entry(account_number_to, account_number, transfer_balance);
                    System.out.println("--------------------------------------------------------------");
                    System.out.println("Rs." + transfer_balance + " Transfer From " + detail(account_number, 1) + " To " + detail(account_number_to, 1));
                    System.out.println("--------------------------------------------------------------");
                  }else{
                    System.out.println("********************************");
                    System.out.println("Sorry "+ detail(account_number, 1) + ", You Have Insufficent Funds");
                    System.out.println("Balance: " + detail(account_number, 2));
                    System.out.println("********************************");
                  }
                }else{
                  System.out.println("Wrong Password");
                }
                break;

      }
      // Next - 3 Lines | Snipnet #C1 
      System.out.println("Press enter to continue");
      try{System.in.read();}
              catch(Exception e){}
    }
  }
  static void ledger_entry(int account_debit, int account_credit, int balance){
    String path = System.getProperty("user.dir")+"//data//ledger.txt";
    File f = new File(path);
    Scanner finput;
    String data = "";
    try {
      finput = new Scanner(f);
      while (finput.hasNextLine()){
        data += (finput.nextLine() +"\n");
      }
      f.delete();
    } catch (FileNotFoundException e1) {
      System.out.println("Something Went Wrong #ledger.txt not found");
      e1.printStackTrace();
    }
    FileWriter fw;
    try {
      fw = new FileWriter(path);
      fw.write(data);
      fw.write(account_debit + " " + account_credit + " " + balance + "\n");
      fw.close();
    } catch (IOException e) {
      System.out.println("Something Went wrong #IOELedger");
      e.printStackTrace();
    }
  }

  static void update_password(String acc, String password){
    String path = System.getProperty("user.dir")+"//data//" + acc + ".txt";
    File f = new File(path);
    String[] data = new String[3];
    try (Scanner finput = new Scanner(f)) {
      data = finput.nextLine().split(" ");
      data[0] = password;
    } catch (FileNotFoundException e) {
      System.out.println("SomeThing Went Wrong #FileNotFound");
      e.printStackTrace();
    }
    f.delete();
    FileWriter fw;
    try {
      fw = new FileWriter(path);
      for (String x : data)
        fw.write(x + " ");
      fw.write("\b");
      fw.close();
    } catch (IOException e) {
      System.out.println("Something Went Wrong #IOE");
      e.printStackTrace();
    }
  }
  static void update_name(String acc, String name){
    String path = System.getProperty("user.dir")+"//data//" + acc + ".txt";
    File f = new File(path);
    String[] data = new String[3];
    try (Scanner finput = new Scanner(f)) {
      data = finput.nextLine().split(" ");
      data[1] = name;
    } catch (FileNotFoundException e) {
      System.out.println("SomeThing Went Wrong #FileNotFound");
      e.printStackTrace();
    }
    f.delete();
    FileWriter fw;
    try {
      fw = new FileWriter(path);
      for (String x : data)
        fw.write(x + " ");
      fw.write("\b");
      fw.close();
    } catch (IOException e) {
      System.out.println("Something Went Wrong #IOE");
      e.printStackTrace();
    }
  }

  static void update_balance(String acc,int balance,boolean deposit){
    String path = System.getProperty("user.dir")+"//data//" + acc + ".txt";
    File f = new File(path);
    String[] data = new String[3];
    try (Scanner finput = new Scanner(f)) {
      data = finput.nextLine().split(" ");
      balance =  (deposit) ? balance + Integer.parseInt(data[2]) : Integer.parseInt(data[2]) - balance;
      data[2]  = Integer.toString(balance);
    } catch (FileNotFoundException e) {
      System.out.println("SomeThing Went Wrong #FileNotFound");
      e.printStackTrace();
    }
    f.delete();
    FileWriter fw;
    try {
      fw = new FileWriter(path);
      for (String x : data)
        fw.write(x + " ");
      fw.write("\b");
      fw.close();
    } catch (IOException e) {
      System.out.println("Something Went Wrong #IOE");
      e.printStackTrace();
    }
  }
  
  static String detail(int acc,int choice){
    String path = System.getProperty("user.dir")+"//data//" + Integer.toString(acc) + ".txt";
    File f = new File(path);
    String[] data = new String[3];
    try (Scanner finput = new Scanner(f)) {
      data = finput.nextLine().split(" ");
      return data[choice];
    } catch (FileNotFoundException e) {
      System.out.println("SomeThing Went Wrong #FileNotFound");
      e.printStackTrace();
    }
    return null;
  }

  static boolean is_account_exsit(String acc){
      File f = new File(System.getProperty("user.dir")+"//data");
      String[] listname = f.list();
      for (String x: listname){
          if (x.endsWith(".txt") && x.substring(0, x.indexOf(".txt")).equals(acc) ){
              return true;
          }
      }
      return false;
  }

  static boolean credential_check(String acc, String pass){
    File f = new File(System.getProperty("user.dir")+"//data//" + acc + ".txt");
    try (Scanner finput = new Scanner(f)) {
      String read_pass = finput.next();
      return read_pass.equals(pass);
    } catch (FileNotFoundException e) {
      System.out.println("SomeThing Went Wrong #FileNotFound");
      e.printStackTrace();
    }
    return false;
  }

  static void syspanel(){
    int choice = 0;
    do{
      System.out.println("1) Print Full Data\n" +
                        "2) Print Specific Account\n" +
                        "3) Edit an Account\n" +
                        "4) Print Ledger\n" +
                        "5) Reset System\n" +
                        "6) Return");
      choice = input.nextInt();
    }while(choice > 7 || choice < 1);
    int account_number = 0;
    if (choice == 3 || choice == 2){
      account_number = input_account_number("Account Number: ");
      if (account_number == -1) 
        syspanel();
    }

    
    switch(choice){
      case 1 :
              System.out.println("Account Number\t|\tPassword\t|\tName\t|\tBalance (Rs.)"); 
              for (account_number = 0; is_account_exsit(Integer.toString(account_number)); account_number++)
                System.out.println(account_number + "\t\t|\t"+detail(account_number, 0) +"\t|\t"+detail(account_number, 1) + "\t|\t" + detail(account_number, 2));
              break;

      case 2 : System.out.println(account_number + "\t|\t"+detail(account_number, 0) +"\t|\t"+detail(account_number, 1) + "\t|\t" + detail(account_number, 2));
               break;

      case 3: System.out.println("Change 1) Password        OR      2) Name");
              int x = input.nextInt();
              if (x != 1 && x != 2){
                System.out.println("Wrong Input");
                break;
              }else if (x == 1){
                System.out.print("Old Password (" + detail(account_number, 0) +") \nNew: " );
                update_password(Integer.toString(account_number), input.next());
              }else{
                System.out.print("Old Name (" + detail(account_number, 1) +") \nNew: " );
                update_name(Integer.toString(account_number), input.next());
              }
              break;
              
      case 4: String path = System.getProperty("user.dir")+"//data//ledger.txt";
              File f = new File(path);
              try (Scanner finput = new Scanner(f)) {
                System.out.println("Debit\tAmount\t|\tCredit\tAmount");
                while(finput.hasNextLine()){
                  String[] data = finput.nextLine().split(" ");
                  data[2] += "\b"; 
                  System.out.println(detail(Integer.parseInt(data[0]), 1) + " (" + data[0] + ")\t" + data[2] +"\t|\t" + detail(Integer.parseInt(data[1]), 1) + " (" + data[1] + ")\t" + data[2] );
                }
              } catch (FileNotFoundException e) {
                System.out.println("SomeThing Went Wrong #FileNotFound");
                e.printStackTrace();
              }
            break;
      case 5: System.out.println("Are You Sure, It will delete all data ? type yes");
              if (input.next().equals("yes")){
                reset();;
              }
              break;
      case 6: return; 
    }
    System.out.println("Press enter to continue");
      try{System.in.read();}
              catch(Exception e){}
    syspanel();
  }

  static void reset(){
      String path = System.getProperty("user.dir")+"//data";
      File f = new File(path);
      String[] listname = f.list();
      for (String x: listname){
          if (x.endsWith(".txt")){
              File temp = new File(path + "//" + x );
              temp.delete();
          }
      }
      FileWriter fa;
      try {
        fa = new FileWriter(path + "//0.txt");
        fa.write("admin CashAccount 0");
        fa.close();
      } catch (IOException e) {
        System.out.println("Something Went wrong #IOE-0.txt");
        e.printStackTrace();
      }
      try {
        fa = new FileWriter(path + "//ledger.txt");
        fa.close();
      } catch (IOException e) {
        System.out.println("Something Went wrong #IOE-ledger.txt");
        e.printStackTrace();
      }

  }

  // Give user 3 Attemp to Enter a Correct Account Number Else return -1;
  static int input_account_number(String Msg){ 
    for (int i = 3; i >=0; i--){
      System.out.print("\n" + Msg);
      int account_number = input.nextInt();
      if (account_number == 0) 
        System.out.println("ERROR 404 \tPERMISION DENIED\tERROR 404 ");
      if (account_number < 0){
        System.out.println("****************************************");
        System.out.println("Negative Account Number Input ( " + i + " Tries Remaining )");
        System.out.println("****************************************");
        continue;
      }
      if (!is_account_exsit(Integer.toString(account_number))){
        System.out.println("*****************************************");
        System.out.println("Non-Existing Account Number Input ( " + i + " Tries Remaining )");
        System.out.println("*****************************************");
        continue;
      }
      return account_number;
    }
    return -1;
  }
}
