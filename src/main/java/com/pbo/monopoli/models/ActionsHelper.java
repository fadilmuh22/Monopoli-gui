// package com.pbo.monopoli.models;

// import java.util.Scanner;

// public class ActionsHelper extends Monopoli{
//   private int pemainAktif;
//   private String aksi;

  
//   public int getPemainAktif() {
//     return pemainAktif;
//   }

//   public void setPemainAktif(int pemainAktif) {
//     this.pemainAktif = pemainAktif;
//   }

//   public String getActions() {
//     return aksi;
//   }

//   public void setActions(String aksi) {
//     this.aksi = aksi;
//   }

//   public void aksi(int pemainAktif, String aksi) {
//     Scanner sc = new Scanner(System.in);
//     setPemainAktif(pemainAktif);
//     setActions(aksi);
//     switch (super.getPapan().get(this.pemainAktif).getActions()) {
//         case "aset":
//         if (super.getPapan().get(this.pemainAktif).getPemilik() == super.getPemain().get(this.pemainAktif).getId()) {
//             System.out.println("Apakah Ingin Tambah Asset? ");
//             String ya;
//             ya = sc.nextLine();
//             if (ya.equals("y")) {
//               super.getPapan().get(this.pemainAktif).tambahAset("r", 1);
//             }
//           } else {
//             System.out.println("beli? ");
//             super.bayar(this.pemainAktif, super.getPapan().get(this.pemainAktif).getHarga());
//           }
//           return;
//         case "bayar":
//           super.bayar(this.pemainAktif, super.getPapan().get(this.pemainAktif).getHarga());
//           return;
//         case "kartu":
//           super.ambilKartuBonus();
//         default:
//           System.out.println("this seems to be doesn't right");
//     }
//   }
// }