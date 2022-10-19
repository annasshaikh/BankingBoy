
import java.util.Scanner;


public class banking {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String[] name = new String[100];
    int[] balance = new int[100];
    String[] password = new String[100];
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
        System.out.println("Wrong Input");
      }
      
      int account_number;
      String user_password;

      switch (choice){
        
        case 1: //make Account code
                for (account_number = 0; (password[account_number] != null); account_number++); //Bring the first empty account number
                
                System.out.print("Name: ");
                name[account_number] = input.next();
                System.out.print("Password: ");
                password[account_number] = input.next(); 
                
                System.out.println("--------------------------------------------------------------");
                System.out.println("Welcome " + name[account_number] + " Your Account Number is " + account_number + "  (Please Remeber this)");
                System.out.println("--------------------------------------------------------------");
                
                break;
        case 2: // View Details Code
                System.out.print("Account Number: ");
                account_number = input.nextInt();
                //Give User 3 Tries
                for (int i = 3; i >= 1; i--) {
                  if (account_number < 0 && name[account_number]==null) {
                    System.out.println("Account Number does not exist, try again");
                    System.out.println("You have "+i+" tries remaining");
                    System.out.print("Account Number: ");
                    account_number = input.nextInt();
                  }
                }
                //after 3 wrong tries exit  
                if (account_number < 0 && name[account_number]==null){
                  System.out.println("You have reached your limit of tries");
                  break;
                }
                
                System.out.print("Password: ");
                user_password = input.next();

                if (user_password.equals(password[account_number])){               
                  System.out.println("--------------------------------------------------------------");
                  System.out.println("Name: " + name[account_number] + "\t Balance: " + balance[account_number]);
                  System.out.println("--------------------------------------------------------------");
                }else System.out.println("Wrong Password");

                break;
        case 3: // Deposit Code
                System.out.print("Enter your account No : ");
                account_number = input.nextInt();

                if (account_number < 0 && name[account_number]==null) {
                  System.out.println("Account Number does not exist");
                  break;
                }

                System.out.print("Plz enter the amount to deposit : ");
                int deposit = input.nextInt();
                if (deposit > 0)
                  balance[account_number] += deposit;
                else {
                  System.out.println("You cannot enter negative amount");
                  break;
                }
                
                System.out.println("--------------------------------------------------------------");
                System.out.println("Your amount is added succesfully, your new balance is Rs." + balance[account_number]);
                System.out.println("--------------------------------------------------------------");
                
                break;
        case 4: // Withdrawl code
                System.out.print("Enter your account No : ");
                account_number = input.nextInt();

                if (account_number < 0 && name[account_number]==null) {
                  System.out.println("Account Number does not exist");
                  break;
                }

                System.out.print("Password: ");
                user_password = input.next();

                if (user_password.equals(password[account_number])){
                  System.out.print("Plz enter the amount to Withdrawl: ");
                  int withDrawl = input.nextInt();
                  if (withDrawl > 0 && withDrawl <= balance[account_number])
                    balance[account_number] -= withDrawl;
                  else{
                    System.out.println("You have insufficent funds");
                    break;
                  }

                  System.out.println("--------------------------------------------------------------");
                  System.out.println("Your amount is Withdrawal succesfully, your new balance is Rs." + balance[account_number]);
                  System.out.println("--------------------------------------------------------------");
            
                }else System.out.println("Wrong Password");

                break;
        case 5: System.out.print("Enter Account No. (Transfer From) : ");
                account_number = input.nextInt();

                if (account_number < 0 && name[account_number]==null) {
                  System.out.println("Account Number does not exist");
                  break;
                }

                System.out.print("Password: ");
                user_password = input.next();

                if (user_password.equals(password[account_number])){
                  System.out.print("Enter the Account Number (Transfer to): ");
                  int account_number_to = input.nextInt();

                  if (account_number_to < 0 && name[account_number_to]==null) {
                    System.out.println("Account Number does not exist");
                    break;
                  }
                  System.out.print("Enter Balance: ");
                  int transfer_balance = input.nextInt();
                  if (transfer_balance > 0 && transfer_balance <= balance[account_number]){
                    balance[account_number] -= transfer_balance;
                    balance[account_number_to] += transfer_balance;
                  }else
                    System.out.println("You have insufficent funds");
                }
                break;

      }
      // Next - 3 Lines | Snipnet #C1 
      System.out.println("Press enter to continue");
      try{System.in.read();}
              catch(Exception e){}
    }
  }

}
