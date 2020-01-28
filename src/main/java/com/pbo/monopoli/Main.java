package com.pbo.monopoli;

import java.util.Scanner;
import com.pbo.monopoli.models.Monopoli;

public class Main {
    static boolean main = true;

    public static void main(String[] args) {
        Monopoli game = new Monopoli();

        setTimeOut(lamaMain());

        Scanner sc = new Scanner(System.in);
        int pemain;
        System.out.println("Berapa pemain? ");
        pemain = sc.nextInt();
        game.setup(pemain);
        int i = 0;
        while (main) {
            game.options();
            i++;
        }

    }

    public static int lamaMain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\t===========   WARN!   ===========");
        for (int i = 1; i <= 6; i++) {
            System.out.print("\t||\t" + i + ". " + i * 5 + " menit\t\t||\t\n");
        }
        System.out.println("\t==================================");
        System.out.print("Pilihan anda: ");
        int pilih = sc.nextInt() * 5;

        System.out.println("Game di set dengan lama permainan " + pilih + " menit");

        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
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
                main = false;
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
