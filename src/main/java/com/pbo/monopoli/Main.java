package com.pbo.monopoli;

import com.pbo.monopoli.models.Monopoli;
import com.pbo.monopoli.utils.ActionsHelper;

public class Main {

    public static void main(String[] args) {
        Monopoli game = new Monopoli();
        int pemain;

        ActionsHelper.setTimeOut(ActionsHelper.lamaMain());

        System.out.println("Berapa pemain? ");
        pemain = ActionsHelper.getInputNumber(2, 6, "Masukkan jumlah pemain: ");
        game.setup(pemain);

        while (true) {
            game.options();
        }

    }

}
