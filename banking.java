
import java.util.Scanner;


public class banking {
    static Scanner input = new Scanner(System.in);
    static String[] name = new String[100];
    static int[] balance = new int[100];
    static String[] password = new String[100];
  public static void main(String[] args) {
    int registered_account = 0;
    //sys account
    name[0] = "System";
    password[0] = "nimda";
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
          if (choice == 786);
          System.out.println("idk");
        System.out.println("Wrong Input");
      }
      
      int account_number;
      String user_password;

      switch (choice){
        
        case 1: //make Account code
                for (account_number = 0; (password[account_number] != null); account_number++); //Bring the first empty account number
                
                System.out.print("Name: ");
                name[account_number] = input.next();
                //Capitilize the First letter
                name[account_number] = name[account_number].substring(0, 1).toUpperCase() + name[account_number].substring(1);

                System.out.print("Password: ");
                password[account_number] = input.next(); 
                
                System.out.println("--------------------------------------------------------------");
                System.out.println("Welcome " + name[account_number] + " Your Account Number is " + account_number + "  (Please Remeber this)");
                System.out.println("--------------------------------------------------------------");
                registered_account++;
                break;
        case 2: // View Details Code
                if (registered_account == 0){
                  System.out.println("Zero Account Exit In The Data Base");
                  break;
                }

                account_number = input_account_number("Account Number: ");
                if(account_number == -1) break;

                System.out.print("Password: ");
                user_password = input.next();

                if (user_password.equals(password[account_number])){               
                  System.out.println("--------------------------------------------------------------");
                  System.out.println("Name: " + name[account_number] + "\t Balance: " + balance[account_number]);
                  System.out.println("--------------------------------------------------------------");
                }else System.out.println("Wrong Password");

                break;
        case 3: // Deposit Code
                if (registered_account == 0){
                  System.out.println("Zero Account Exit In The Data Base");
                  break;
                }
                
                account_number = input_account_number("Account Number: ");
                if(account_number == -1) break;

                System.out.print("Amount To Deposit : Rs.");
                int deposit = input.nextInt();

                if (deposit > 0)
                  balance[account_number] += deposit;
                else {
                  System.out.println("***********************");
                  System.out.println("Negitive Amount Inputed");
                  System.out.println("************************");
                  break;
                }
                
                System.out.println("--------------------------------------------------------------");
                System.out.println("Your amount is added succesfully, your new balance is Rs." + balance[account_number]);
                System.out.println("--------------------------------------------------------------");
                
                break;

        case 4: // Withdrawl code
                if (registered_account == 0){
                  System.out.println("Zero Account Exit In The Data");
                  break;
                }
        
                account_number = input_account_number("Account Number: ");
                if(account_number == -1) break;

                System.out.print("Password: ");
                user_password = input.next();

                if (user_password.equals(password[account_number])){
                  System.out.print("Amount to Withdrawl: Rs.");
                  int withDrawl = input.nextInt();

                  if (withDrawl < 0){
                    System.out.println("************************");
                    System.out.println("Negitive Balance Inputed");
                    System.out.println("*************************");
                    break;
                  }
                  if (withDrawl <= balance[account_number])
                    balance[account_number] -= withDrawl;
                  else{
                    System.out.println("********************************");
                    System.out.println("Sorry "+ name[account_number] + ", You Have Insufficent Funds");
                    System.out.println("Balance: " + balance[account_number]);
                    System.out.println("********************************");
                    break;
                  }

                  System.out.println("--------------------------------------------------------------");
                  System.out.println("Your amount is Withdrawal succesfully, your new balance is Rs." + balance[account_number]);
                  System.out.println("--------------------------------------------------------------");
            
                }else System.out.println("Wrong Password");

                break;
        case 5: //Transition Code
                if (registered_account < 2){
                  System.out.println("Less Than 2 Account Exsist in Data Base");
                  break;
                }
                account_number = input_account_number("Account Number (Transfer From): ");
                if(account_number == -1) break;

                System.out.print("Password: ");
                user_password = input.next();

                if (user_password.equals(password[account_number])){
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
                  if (transfer_balance <= balance[account_number]){
                    balance[account_number] -= transfer_balance;
                    balance[account_number_to] += transfer_balance;
                    System.out.println("--------------------------------------------------------------");
                    System.out.print("Rs." + transfer_balance + " Transfer From " + name[account_number] + " To " + balance[account_number_to]);
                    System.out.println("--------------------------------------------------------------");
                  }else
                    System.out.println("********************************");
                    System.out.println("Sorry "+ name[account_number] + ", You Have Insufficent Funds");
                    System.out.println("Balance: " + balance[account_number]);
                    System.out.println("********************************");
                }
                break;

      }
      // Next - 3 Lines | Snipnet #C1 
      System.out.println("Press enter to continue");
      try{System.in.read();}
              catch(Exception e){}
    }
  }
  // Give user 3 Attemp to Enter a Correct Account Number Else return -1;
  static int input_account_number(String Msg){ 
    for (int i = 3; i >=0; i--){
      System.out.print("\n" + Msg);
      int account_number = input.nextInt();

      if (account_number < 0){
        System.out.println("****************************************");
        System.out.println("Negative Account Number Input ( " + i + " Tries Remaining )");
        System.out.println("****************************************");
        continue;
      }
      if (name[account_number]==null){
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
