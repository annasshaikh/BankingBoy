
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
        //if (choice == 786) 
          //syspanel();
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

                if (deposit > 0)
                  update_balance(Integer.toString(account_number), deposit, true);
                else {
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
                  if (withDrawl <= Integer.parseInt(detail(account_number, 2)))
                    update_balance(Integer.toString(account_number), withDrawl, false);
                  else{
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

  static void update_password(String acc, String password){}
  static void update_name(String acc, String name){
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
/* 
  static void syspanel(){
    int choice = 0;
    do{
      System.out.println("1) Print Full Data\n" +
                        "2) Print Specific Account\n" +
                        "3) Edit an Account\n" +
                        "4) Return");
      choice = input.nextInt();
    }while(choice > 5 || choice < 1);
    int account_number = 0;
    if (choice == 3 || choice == 2){
      account_number = input_account_number("Account Number: ");
      if (account_number == -1) 
        syspanel();
    }
    
    switch(choice){
      case 1 :
              System.out.println("Account Number\t|\tName\t|\tBalance (Rs.)"); 
              for (account_number = 0; (password[account_number] != null); account_number++)
                  System.out.println(account_number + "\t|\t"+ name[account_number] + "\t|\t" + balance[account_number]);
              break;
      case 2 :  System.out.println(account_number + "\t|\t"+ name[account_number] + "\t|\t" + balance[account_number]);
                break;
      case 3: System.out.println("Change 1) Password        OR      2) Name");
              int x = input.nextInt();
              if (x != 1 && x != 2){
                System.out.println("Wrong Input");
                break;
              }else if (x == 1){
                System.out.print("Old Password (" + password[account_number] +") \nNew: " );
                password[account_number] = input.next();
              }else{
                System.out.print("Old Name: (" + name[account_number] +") \nNew: " );
                name[account_number] = input.next();
              }
              break;
      case 4: return; 
    }
    syspanel();
  }
  */
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
