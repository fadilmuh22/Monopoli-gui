package com.pbo.monopoli;

import java.util.Scanner;
import com.pbo.monopoli.models.Monopoli;

public class Main {

    public static void main(String[] args) {
        Monopoli game = new Monopoli();
        boolean main = true;
        
        Scanner sc = new Scanner(System.in);
        int pemain;
        System.out.println("Berapa pemain? ");
        pemain = sc.nextInt();
        game.setup(pemain);
        int i = 0;
        while(i < 50) {
            game.move();
            i++;
        }
        
    }
    
}
