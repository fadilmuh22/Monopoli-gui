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
        in.nextLine();

      } catch (Exception e) {
        System.out.println("Incorrect data type entered");
        System.out.println();
        in.nextLine();
      }
    } while ( choice.equals("none"));

    return choice;

  }

  public static void setTimeOut(int time) {
      new Thread( new Runnable() {
          public void run()  {
              try  { Thread.sleep( time ); }
              catch (InterruptedException ie)  {}
              clearScreen();
              gameEnd();
          }
      } ).start();
  }

  public static void gameEnd() {
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

  public static void clearScreen() {  
      System.out.print("\033[H\033[2J");  
      System.out.flush();
  }

}