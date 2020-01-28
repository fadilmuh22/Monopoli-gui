package monopoli;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Monopoli {
  private Dadu dadu = new Dadu();
  private Papan papan;
  private List<KartuBonus> listKartuBonus = new ArrayList<KartuBonus>();
  private List<Pemain> pemain = new ArrayList<Pemain>();
  private int pemainAktif = 0;
  private int jumlahMove = 0;
  private int jumlahMovePenjara = 0;
  public int jumlahPemain;

  public void setup(int pemain) {
    setPapan();
    setPemain(pemain);
    this.jumlahPemain = pemain;
  }

  public List<Pemain> getPemain() {
  	return pemain;
  }

  public void setPemain(int jumlah) {
    for (int id = 0; id < jumlah + 1; id++) {
      this.pemain.add(new Pemain("p" + id));
    }

//    acakPemainPertama();

  }

  public void acakPemainPertama() {
    int acak = (int) (Math.random() * this.jumlahPemain + 1);
    Collections.swap(pemain, 0, acak);
  }

  public void move(int pemainAktif) {  
    if (jumlahMove == 3) {
      System.out.println("Punteun ente masuk penjara");
      penjara(pemainAktif);
    }

   if (this.pemain.get(pemainAktif).getStatus().equals("penjara")) {
     penjara(pemainAktif);
   } else {
       System.out.println("Giliran pemain: " + this.pemainAktif);
       int[] angka = this.dadu.kocok();
       System.out.println(Arrays.toString(angka));
       
       if (angka[0] == angka[1]) {
            System.out.println("Double!");
            
            this.pemain.get(pemainAktif).setPosisi((angka[0] + angka[1]));
            int posisiPemai = this.pemain.get(pemainAktif).getPosisi();
            
            System.out.println("pemain " + this.pemainAktif + " Ada di posisi " + posisiPemai);
            System.out.println("Berada di kotak " + this.getPapan().get(posisiPemai).getNamaPetak());
            
            this.getAksi(posisiPemai);
            this.move(pemainAktif);
            jumlahMove += 1;
            
       } else if (angka[0] != angka[1]) {
           
            this.pemain.get(pemainAktif).setPosisi((angka[0] + angka[1]));
            int posisiPemain = this.pemain.get(pemainAktif).getPosisi();

            System.out.println("pemain " + this.pemainAktif + " Ada di posisi " + posisiPemain);
            System.out.println("Berada di kotak " + this.getPapan().get(posisiPemain).getNamaPetak());
            this.getAksi(posisiPemain);
            this.setPemainAktif();
       }   
       
   }
    
   
    
  }

  public void getAksi(int pemainAktif) {
    Scanner sc = new Scanner(System.in);
    String ya;
    switch (this.getPapan().get(this.pemainAktif).getAksi()) {
        case "aset":
            System.out.println("Duit ente segini gan " + this.pemain.get(this.pemainAktif).getUang());
            if (this.getPapan().get(this.pemainAktif).getPemilik() == this.getPemain().get(this.pemainAktif).getId()) {
                System.out.println("Apakah Ingin Tambah Asset? ");
                ya = sc.nextLine();
                if (ya.equals("y")) {
                  this.getPapan().get(this.pemainAktif).tambahAset("r", 1);
                }
              } else {
                System.out.println("Beli gak gan? Harganya segini nih: " + this.getPapan().get(this.pemainAktif).getHarga());
                ya = sc.nextLine();
                if (ya.equals("y")) {
                  this.bayar(this.pemainAktif, (this.getPapan().get(this.pemainAktif).getHarga()) * -1 );
                }
                System.out.println("Duit ente segini lagi sekarang gan " + this.pemain.get(this.pemainAktif).getUang());
              }
          return;
        case "bayar":
          this.bayar(this.pemainAktif, this.getPapan().get(this.pemainAktif).getHarga());
          return;
        case "kartu":
          this.ambilKartuBonus();
        default:
          System.out.println("this seems to be doesn't right");
    }
//    this.setPemainAktif();
//    System.out.println(this.getPapan().get(pemainAktif).getAksi());
//      System.out.println(this.getPapan().get(pemainAktif).getNamaPetak());
  }

  public void penjara(int pemainAktif) {
    Scanner sc = new Scanner(System.in);
    String ya;
    
    this.pemain.get(pemainAktif).setPosisi(11); 
    this.pemain.get(pemainAktif).setStatus("penjara"); 
     
    int[] angka = this.dadu.kocok();
    
    if (this.jumlahMovePenjara == 3) {
      System.out.println("Ente 3 kali kesempatan ga lolos keluar penjara dengan bayar 5000 (y/t)");
      ya = sc.nextLine();
      if (ya.equals("y")) {
        this.pemain.get(pemainAktif).setUang(-5000.00);
      }
      setPemainAktif();
      move(this.pemainAktif);
    } else if (angka[0] == angka[1]) {
      this.jumlahMovePenjara = 0;
      this.pemain.get(pemainAktif).setStatus("main");
      this.pemain.get(pemainAktif).setPosisi((angka[0] + angka[1]));
      this.getAksi(pemainAktif);
      setPemainAktif();
    } else if (angka[0] != angka[1]) {
      penjara(pemainAktif);
      this.jumlahMovePenjara += 1;
    }
  }
  
  public void bayar(int pemainAktif, Double jumlah) {
    this.pemain.get(pemainAktif).setUang(jumlah);
  }

  public void ambilKartuBonus() {
    int pemainAktif = (int) (Math.random() * listKartuBonus.size() + 1);
    listKartuBonus.get(pemainAktif);
    listKartuBonus.remove(pemainAktif);
  }
  
  public int getPemainAktif() {
      return this.pemainAktif;
  }

  public void setPemainAktif() {
    if (this.pemainAktif == this.jumlahPemain - 1) {
      this.pemainAktif = 0;
    } else {
      this.pemainAktif += 1;
    }
    
    this.jumlahMove = 0;
  }

  public Map<Integer, Petak> getPapan() {
    return papan.getListPetak();
  }

  public void setPapan() {
    Map<Integer, Petak> petak = new HashMap<Integer, Petak>();
    for(int i = 0; i < 36; i++) {
        petak.put(i, new Petak("A", "petak", "Amerika" + i, 6000.00, "aset"));

    }
    this.papan = new Papan(petak);
      System.out.println(this.getPapan().get(0).getNamaPetak());
  }
  
  public List<KartuBonus> getListKartuBonus() {
    return listKartuBonus;
  }

  public void setListKartuBonus(List<KartuBonus> listKartuBonus) {
    this.listKartuBonus = listKartuBonus;
  }
  
  

}
