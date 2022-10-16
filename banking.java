import java.util.Scanner;

import javax.sound.midi.Soundbank;

public class banking {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Welcome To BankBoy: \n" +
                      "Press 1 to Create Account \n" +
                      "Press 2 To View Details \n" +
                      "Press 3 For Deposit \n" +
                      "Press 4 For Withdrawl \n" + 
                      "Press 5 For Transation \n" );    
    int choice = -1;
    // This Will Ask For Value Until 1 - 5 is input.
    while(true){
      System.out.print("------> ");
      choice = input.nextInt();
      if (choice <= 5 || choice >= 1)
        break;
      System.out.println("Wrong Ïnput");
    }
  
  }
}
