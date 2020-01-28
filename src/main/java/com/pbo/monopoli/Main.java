package com.pbo.monopoli;

import com.pbo.monopoli.models.Monopoli;
import com.pbo.monopoli.utils.ActionsHelper;

public class Main {
    static boolean main = true;

    public static void main(String[] args) {
        Monopoli game = new Monopoli();
        int pemain;

        ActionsHelper.setTimeOut(lamaMain());

        pemain = ActionsHelper.getInputNumber(1, 4, "Berapa pemain?");
        game.setup(pemain);

        while (main) {
            game.options();
        }

    }

    public static int lamaMain() {
        System.out.println("\n\t===========   WARN!   ===========");
        for (int i = 1; i <= 6; i++) {
            System.out.print("\t||\t" + i + ". " + i * 5 + " menit\t\t||\t\n");
        }
        System.out.println("\t==================================");

        String text = "Pilihan anda: ";
        int pilih = ActionsHelper.getInputNumber(1, 5, text);
    
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
    
    
}
