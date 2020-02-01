package com.pbo.monopoli.utils;

import java.util.Scanner;

public class ActionsHelper {
  private static Scanner in = new Scanner(System.in);

  public static int getInputNumber(int min, int max, String text) {
    int choice = -1;

    do {
      try {
        System.out.print(
            text
        );
        choice = in.nextInt();
        System.out.println();
        // System.out.println("Incorrect value entered" + " " + choice + max);

        if ((choice < min) || (choice > max && max != -1)) {
        // System.out.println("Incorrect value entered" + (choice < min) + (choice > max && max != 0));
            System.out.println("Incorrect value entered");
            System.out.println();
            in.nextLine();
        }  
      } catch (Exception e) {
        System.out.println("Incorrect data type entered");
        System.out.println();
        in.nextLine();
      }
    } while ((choice < min) || (choice > max && max != -1));

    return choice;

  }

  public static String getInputString( String text ) {
    String choice = "none";

    do {
      try {
        System.out.print(
            text
        );

        choice = in.next();
        System.out.println();

      } catch (Exception e) {
        System.out.println("Incorrect data type entered");
        System.out.println();
        in.nextLine();
      }
    } while ( choice.equals("none"));

    return choice;

  }
  
  public static String getChoiceString( String[] choices, String text ) {
    String choice = "none";
    boolean valid = false;

    do {
      try {
        System.out.print(
            text
        );

        choice = in.next();
        System.out.println();

        valid = checkChoiceString(choices, choice);

        if (!valid) {
          System.out.println("Incorrect value entered");
          System.out.println();
          in.nextLine();
        }
        
        // if ( !choice.equals(choices[0]) && !choice.equals(choices[1]) ) {
        //   // System.out.println("Incorrect value entered" + (choice < min) + (choice > max && max != 0));
        //   System.out.println("Incorrect value entered");
        //   System.out.println();
        //   in.nextLine();
        // } 

      } catch (Exception e) {
        System.out.println("Incorrect data type entered");
        System.out.println();
        in.nextLine();
      }
    } while ( !valid || choice.equals("none") );

    return choice;

  }

  public static boolean checkChoiceString(String[] choices, String choice) {
    boolean valid = false;
    for ( int i = 0; i < choices.length; i++ ) {
      if (i == 0) {
        valid = valid || !choices[i].equals(choice);
      } else {
        valid = valid && !choices[i].equals(choice);
      }
    }
    return !valid;
  }

  public static int lamaMain() {
    System.out.println("\n\t===========   WARN!   ===========");
    for (int i = 1; i <= 6; i++) {
        System.out.print("\t||\t" + i + ". " + i * 5 + " menit\t\t||\t\n");
    }
    System.out.println("\t==================================");

    String text = "Pilihan anda: ";
    int pilih = ActionsHelper.getInputNumber(1, 6, text) * 5;

    System.out.println("Game di set dengan lama permainan " + pilih + " menit");

    try {
        Thread.sleep(2 * 1000);
    } catch (InterruptedException e) {
        System.out.println(e);
        e.printStackTrace();
    }

    System.out.print("\033[H\033[2J");  
    System.out.flush();
    
    return pilih * 60 * 1000;
  }


  public static void setTimeOut(int time) {
      new Thread( new Runnable() {
          public void run()  {
              try  { Thread.sleep( time ); }
              catch (InterruptedException ie)  {}
              gameEnd();
          }
      } ).start();
  }

  public static void gameEnd() {
      clearScreen();
      System.out.println("\n\t===========   WARN!   ===========");
      System.out.print(
      "\t||\t!!GAME ENDS!!\t\t||\t\n" + 
      "\t||\t!!GAME ENDS!!\t\t||\t\n" + 
      "\t||\t!!GAME ENDS!!\t\t||\t\n" + 
      "\t||\t!!GAME ENDS!!\t\t||\t\n"
      );
      System.out.println("\t==================================");

      System.exit(1);
  }

  public static void gameWin(String nama) {
    clearScreen();
    System.out.println("\n\t===========   "+ nama +"   ===========");
    System.out.print(
    "\t||\t!!YOU'ARE WIN!!\t\t||\t\n" + 
    "\t||\t!!YOU'ARE WIN!!\t\t||\t\n" + 
    "\t||\t!!YOU'ARE WIN!!\t\t||\t\n" + 
    "\t||\t!!YOU'ARE WIN!!\t\t||\t\n"
    );
    System.out.println("\t==================================");

    System.exit(1);
}

  public static void clearScreen() {  
      System.out.print("\033[H\033[2J");  
      System.out.flush();
  }

}