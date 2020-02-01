package com.pbo.monopoli.models;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.pbo.monopoli.utils.PetakConverter;
import com.pbo.monopoli.utils.ActionsHelper;

public class Monopoli {

  private Dadu dadu = new Dadu();
  private Papan papan;
  public int jumlahPemain;
  private List<Pemain> pemain = new ArrayList<Pemain>();
  private List<KartuBonus> listKartuBonus;
  private int pemainAktif = 0;
  private int jumlahMove = 0;
  private int jumlahMovePenjara = 0;
  private String[] choiceyt = {"y", "t"};

  public boolean statusGame = true;

  public void setup(int pemain) {
    this.setPapan();
    this.setKartuBonus();
    this.setPemain(pemain);

    this.jumlahPemain = pemain;
  }

  public List<Petak> getPapan() {
    return this.papan.getListPetak();
  }

  public void setPapan() {

    InputStream f;
    try {
      f = this.getClass().getResourceAsStream("/com/pbo/monopoli/resources/data_petak_v1.json");
      if (true) {
        try {
          this.papan = PetakConverter.fromJsonString(f);
        } catch (IOException e) {
          System.err.println(e);
          e.printStackTrace();
        }
      }
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
    }

    // List<Petak> petak = new ArrayList<Petak>();
    // for(int i = 0; i < 40; i++) {
    // petak.add(new Petak("A", "petak", "Amerika" + i, 6000.00, "aset"));

    // }
    // this.papan = new Papan(petak);
    // for (Petak p : this.getPapan())
    // System.out.println(p.getNamaPetak());
  }

  public void setKartuBonus() {
    ObjectMapper mapper = new ObjectMapper();
    InputStream f;
    try {
      f = this.getClass().getResourceAsStream("/com/pbo/monopoli/resources/kartu_bonus.json");
      if (true) {
        try {
          this.listKartuBonus = mapper.readValue(f, new TypeReference<List<KartuBonus>>() {
          });
        } catch (Exception e) {
          System.err.println(e);
          e.printStackTrace();
        }
      }
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
    }
  }

  public List<KartuBonus> getListKartuBonus() {
    return this.listKartuBonus;
  }

  public void setListKartuBonus(List<KartuBonus> listKartuBonus) {
    this.listKartuBonus = listKartuBonus;
  }

  public void setPemain(int jumlah) {
    
    for (int id = 0; id < jumlah; id++) {
      System.out.println("Nama pemain " + id + " ? ");
      String nama = ActionsHelper.getInputString("Nama: ");
      this.pemain.add(new Pemain("p" + id, nama));
    }

    // acakPemainPertama();

  }

  public List<Pemain> getPemains() {
    return pemain;
  }

  // ! DEPRECATED
  // public void acakPemainPertama() {
  // int acak = (int) (Math.random() * this.jumlahPemain + 1);
  // Collections.swap(pemain, 0, acak);
  // }

  public void options() {

    if (pemain.size() == 1) {
      ActionsHelper.gameWin(getPemain().getNama());
    }
    
    int posisiPemain = getPemain().getPosisi();

    System.out.println("==================================================");
    System.out.println("Giliran " + getPemain().getNama() + ": " );
    System.out.println(getPemain().getNama() + " Ada di posisi " + posisiPemain);
    System.out.println("Berada di kotak " + this.getPapan().get(posisiPemain).getNamaPetak());
    System.out.println("\n\t===========   OPTION   ===========");
    System.out.println(
      "\t||\t1. Cek Uang\t\t||\t\n" +
      "\t||\t2. Cek Asset\t\t||\t\n" + 
      "\t||\t3. Atau Quit?\t\t||\t\n" +
      "\t||\t4. Lanjut\t\t||\t"
      );
      
    System.out.println("\t==================================");
    System.out.println();
    
    int pilihanOpsi = ActionsHelper.getInputNumber(1, 4, "Silahkan masukan pilihan anda: ");
    System.out.println("-------------------------------------------");
    switch (pilihanOpsi) {
      case 1:
        System.out.println( "Duit di dompet ente ada segini: " + this.pemain.get(pemainAktif).getUang() );
        System.out.println("-------------------------------------------");
        System.out.print("Enter untuk melanjutkan...");
        try {
          System.in.read();
        } catch (IOException e) {
          System.out.println(e);
          e.printStackTrace();
        }
        options();
        break;
      
      case 2:
        checkJumlahAssets();
        enterToCon();
        options();
        break;
        
      case 3:
        pemain.remove(pemainAktif);
        System.out.println("ini jumlah pemain sekarang " + pemain.size());
        enterToCon();
        options();
        break;
        
      default:
        move();
        break;
    }
    System.out.println("==================================================");

  }

  public void move() {
    if (jumlahMove == 3) {
      System.out.println("Pemain "+getPemain().getNama()+" masuk penjara");
      this.penjara();
    } else {
      System.out.println();
      System.out.println("===============================================\n");
      System.out.println("Duid ente ada segini lagi gan: " + getPemain().getUang());
      int[] angka = this.dadu.kocok();
      System.out.println(Arrays.toString(angka));

      if (angka[0] == angka[1]) {
        System.out.println("Double!");
        this.getPemain().setPosisi((angka[0] + angka[1]));
        int posisiPemai = getPemain().getPosisi();

        System.out.println("Pemain " + getPemain().getNama() + " Ada di posisi " + posisiPemai);
        System.out.println("Berada di kotak " + this.getPapan().get(posisiPemai).getNamaPetak());

        this.getActions(posisiPemai);
        try {
          System.out.print("Maju lagi karena double....");
          System.in.read();
        } catch (Exception e) {
          System.out.println(e);
        }
        jumlahMove += 1;
        this.move();

      } else if (angka[0] != angka[1]) {
        getPemain().setPosisi((angka[0] + angka[1]));
        int posisiPemain = getPemain().getPosisi();

        System.out.println("Pemain " + this.pemainAktif + " Ada di posisi " + posisiPemain);
        System.out.println("Berada di kotak " + this.getPapan().get(posisiPemain).getNamaPetak());
        this.getActions(posisiPemain);
        this.setPemainAktif();
      }

    }

  }

  public int getPemainAktif() {
    return this.pemainAktif;
  }

  public Pemain getPemain() {
    if (pemainAktif == pemain.size()) {
      return this.pemain.get(0);
    }
    return this.pemain.get(this.pemainAktif);
  }

  public Pemain getPemain(String id) {
    for (int i = 0; i < pemain.size(); i++) {
      if ( pemain.get(i).getId().equals(id) ) {
        System.out.println("Pemain ditemukan: " + this.pemain.get(i).getNama());
        return this.pemain.get(i);
      }
    }
    System.out.println("Pemain tidak dapat ditemukan");
    return this.pemain.get(pemainAktif);
  }

  public void setPemainAktif() {
    enterToCon();
    if (this.pemainAktif == pemain.size() - 1) {
      this.pemainAktif = 0;
    } else {
      this.pemainAktif += 1;
    }

    this.jumlahMove = 0;
    ActionsHelper.clearScreen();
    options();
  }

  public void getActions(int posisiPemain) {
    this.checkStart();
    
    System.out.println("Ini aksinya yang di dapat: " + this.getPapan().get(posisiPemain).getActions() );
    String ya;
    switch (this.getPapan().get(posisiPemain).getActions()) {
      case "assets":
        if (!this.getPapan().get(posisiPemain).getPemilik().equals("game")) {
          System.out.println("Duit ente segini gan " + getPemain().getUang());
          if (this.getPapan().get(posisiPemain).getPemilik().equals(getPemain().getId())) {
            System.out.println("========== Apakah Ingin Tambah Asset? ============");
            ya = ActionsHelper.getChoiceString(choiceyt, "Pilihan anda (y / t) : ");
            if (ya.equals("y")) {
              String jenisAset;
              int jumlahAset = 0;

              int[] hargaBeli = this.getPapan().get(posisiPemain).getHargaBeli();

              System.out.println("( r (rumah) / h (hotel) )");
              String[] choicerh = {"r", "h"};
              jenisAset = ActionsHelper.getChoiceString(choicerh, "Pilihan anda (r / h) : ");
              System.out.println();

              if ( jenisAset.equals("r") ) {
                System.out.println("Harga beli rumah adalah " + hargaBeli[0]);
                jumlahAset = ActionsHelper.getInputNumber(1, 4, "Berapa jumlahnya? ");
                this.bayar((double) (hargaBeli[0] * jumlahAset) * -1);
                this.getPapan().get( posisiPemain ).setHarga((double) hargaBeli[0] * jumlahAset);
                System.out.println("Harga aset " + this.getPapan().get( posisiPemain ).getNamaPetak() + " adalah " + this.getPapan().get( posisiPemain ).getHarga());
                System.out.println("Duit ente segini gan " + getPemain().getUang());

              } else if( jenisAset.equals("h") ) {
                if ( this.getPapan().get( posisiPemain ).getAset().get("r") >= 4 ) {
                  System.out.print("Harga beli hotel adalah " + hargaBeli[1]);
                  jumlahAset = 1;
                  String pilih = ActionsHelper.getChoiceString(choiceyt, "Pilihan anda (y /t) : ");
                  System.out.println(); 
                  if ( pilih.equals("y") ) {
                    this.bayar((double) (hargaBeli[1] * jumlahAset) *-1);
                    System.out.println("Selamat anda telah membeli hotel");
                    this.getPapan().get( posisiPemain ).setHarga((double) hargaBeli[1] * jumlahAset);
                  }

                } else {
                  System.out.println("Maaf tidak bisa membeli hotel jika tidak memiliki 4 rumah di petak ini");
                  getActions(posisiPemain);
                }
              }

              this.getPapan().get(posisiPemain).tambahAset(jenisAset, jumlahAset);
              break;

            }
          } else if (!this.getPapan().get(posisiPemain).getPemilik()
              .equals(getPemain().getId())) {
                
            Double hargaSewaTotal = this.getPapan().get(posisiPemain).getHarga();

            System.out.println(
              "Ente ada di tanah " + getPemain( getPapan().get(posisiPemain).getPemilik() ).getNama() + " dan harus bayar"
            );
            System.out.println("Harga sewa petak ini adalah " + hargaSewaTotal);
            ya = ActionsHelper.getInputString("Bayar [y] : ");
            System.out.println();
            if ( !ya.equals("p") ) {
              this.bayar( (double) hargaSewaTotal, getPapan().get(posisiPemain).getPemilik() );
              this.bayar( (double) hargaSewaTotal * -1 );
              System.out.println( "Pemilik tanah: " + getPemain( getPapan().get(posisiPemain).getPemilik() ).getNama()+ " dapet duid segini: " + hargaSewaTotal );
            }
            System.out.println("Duit ente segini lagi sekarang gan " + getPemain().getUang());
            break;
          }

        } else {
          System.out.println("Beli gak gan? Harganya segini nih: " + this.getPapan().get(posisiPemain).getHarga());
          ya = ActionsHelper.getChoiceString(choiceyt, "Pilihan anda (y / t) : ");
          System.out.println();
          if ( ya.equals("y") ) {
            this.bayar((this.getPapan().get(posisiPemain).getHarga()) * -1);
            this.getPapan().get(posisiPemain).setPemilik(getPemain().getId());
            System.out.println("Tanah " + this.getPapan().get(posisiPemain).getNamaPetak() + " milik " + getPemain( this.getPapan().get(posisiPemain).getPemilik() ).getNama() );
          }
          System.out.println("Duit ente segini lagi sekarang gan " + getPemain().getUang());
        }

        break;
      case "asset":
        if (!this.getPapan().get(posisiPemain).getPemilik().equals("game")) {
          if (!this.getPapan().get(posisiPemain).getPemilik().equals(getPemain().getId())) {
            System.out.print(
                "Ente ada di tanah " + getPemain( getPapan().get(posisiPemain).getPemilik() ).getNama() + " dan harus bayar " + this.getPapan().get(posisiPemain).getHarga()
            );
            ya = ActionsHelper.getInputString("Bayar [y] : ");
            System.out.println();
            if ( !ya.equals("p") ) {
              this.bayar( (this.getPapan().get(posisiPemain).getHarga()) * -1 );
              System.out.println( "Pemilik tanah: " + getPemain( getPapan().get(posisiPemain).getPemilik() ).getNama()+ " dapet duid segini: " + this.getPapan().get(posisiPemain).getHarga() );
              this.bayar( this.getPapan().get(posisiPemain).getHarga(), getPemain().getId() );
            }
            System.out.println("Duit ente segini lagi sekarang gan " + getPemain().getUang());
            break;
          }
          break;
        }
        System.out.println("Duit ente segini gan " + getPemain().getUang());

        System.out.println("Beli gak gan? Harganya segini nih: " + this.getPapan().get(posisiPemain).getHarga());
        ya = ActionsHelper.getChoiceString(choiceyt, "Pilihan anda (y / t) : ");
        System.out.println();
        if ( ya.equals("y") ) {
          this.bayar((this.getPapan().get(posisiPemain).getHarga()) * -1);
          this.getPapan().get(posisiPemain).setPemilik(getPemain().getId());
        }
        System.out.println("Duit ente segini lagi sekarang gan " + getPemain().getUang());
        System.out.println("Tanah " + this.getPapan().get(posisiPemain).getNamaPetak() + " milik " + getPemain( this.getPapan().get(posisiPemain).getPemilik() ).getNama() );
        break;

      case "bayar":
        System.out.println("Ente Ada di Petak " + this.getPapan().get(posisiPemain).getNamaPetak()
            + " Dan Harus Bayar Segini " + this.getPapan().get(posisiPemain).getHarga());
        this.bayar(this.getPapan().get(posisiPemain).getHarga() * -1);
        System.out.println("Duit ente segini lagi sekarang gan " + getPemain().getUang());
        break;
      case "kartu":
        System.out.println("Dapat kartu bonus");
        this.ambilKartuBonus();
        System.out.println("Duit ente segini lagi sekarang gan " + getPemain().getUang());
        break;
      case "jail":
        System.out.println("Anda Masuk Penjara");
        this.penjara();
        break;
      case "start":
        System.out.println("Anda ada di start");
        System.out.println("Duit ente segini lagi sekarang gan " + getPemain().getUang());
        break;
      case "fly":
        System.out.println("== Anda ada di kotak parkir bebas, dan bebas untuk kemana aja :) ==");
        int kotakPilihanPemain = ActionsHelper.getInputNumber(0, 40, "Pilihan anda : ");
        this.pemain.get( this.pemainAktif ).setPosisiAbsolute( kotakPilihanPemain );
        System.out.println("Berada di kotak " + this.getPapan().get( kotakPilihanPemain ).getNamaPetak());
        this.getActions( getPemain().getPosisi() );
        this.setPemainAktif();
        break;
      default:
        System.out.println("this seems to be doesn't right");
    }
    System.out.println("");
    System.out.println("===============================================");
    System.out.println("\n");

    // this.setPemainAktif();
    // System.out.println(this.getPapan().get(pemainAktif).getActions());
    // System.out.println(this.getPapan().get(pemainAktif).getNamaPetak());
  }

  public void checkStart() {
    if (this.pemain.get( this.pemainAktif ).isHasPassesStart()) {
      System.out.println("Ente udh lewat start dan dapat cuan 20.000,00");
      this.pemain.get( this.pemainAktif ).setUang(20000.00);
      this.pemain.get( this.pemainAktif ).setHasPassesStart(false);
    }
  }

  public void penjara() {
    
    String ya;

    getPemain().setPosisiAbsolute(11);
    getPemain().setStatus("penjara");

    int[] angka = this.dadu.kocok();

    if (this.jumlahMovePenjara == 0) {
      setPemainAktif();
      move();
    } else if (this.jumlahMovePenjara == 3) {
      System.out.println("Ente 3 kali kesempatan ga lolos keluar penjara dengan bayar 5000");
      ya = ActionsHelper.getChoiceString(choiceyt, "Pilihan anda (y / t) : ");
      if ( !ya.equals("p") ) {
        getPemain().setUang(-5000.00);
      }
      setPemainAktif();
      getPemain().setStatus("main");
      this.move();
    } else if (angka[0] == angka[1]) {
      System.out.println(Arrays.toString(angka));
      System.out.println("Double !!!, dan berhasil keluar penjara");
      this.jumlahMovePenjara = 0;
      getPemain().setStatus("main");
      getPemain().setPosisi((angka[0] + angka[1]));
      System.out.println("pemain " + this.pemainAktif + " Ada di posisi " + getPemain().getPosisi());
      System.out
          .println("Berada di kotak " + this.getPapan().get(getPemain().getPosisi()).getNamaPetak());
      System.out.println("");
      this.getActions(pemainAktif);
      this.setPemainAktif();
    } else if (angka[0] != angka[1]) {
      System.out.println(Arrays.toString(angka));
      this.jumlahMovePenjara += 1;
      penjara();
    }
  }

  public void bayar(Double jumlah) {
    
    if (jumlah < 0) {

      if ( getPemain().getUang() < Math.abs(jumlah) ) {

        System.out.println(getPemain().getNama() + " Anda tidak memiliki uang yang cukup!");
        System.out.println("1. Anda ingin menghutang?\n2. Ingin bangkrut saja?");
        int pilihan = ActionsHelper.getInputNumber(1, 2, "Pilihan anda : ");
        System.out.println();

        if (pilihan == 1) {

          if (getPemain().getHutang() > 100000.00) {
            System.out.println(getPemain().getNama() + " Anda telah berhutang terlalu banyak, dengan berat hati kami harus mengeluarkan anda dari game");
            System.out.println("Semoga beruntung lain kali " + getPemain().getNama());
            pemain.remove(pemainAktif);
            enterToCon();
          } else {
            getPemain().setHutang( Math.abs(jumlah) );
            System.out.println("Anda berhutang sebesar " + getPemain().getHutang());
            getPemain().setUang(20000.00);
          }


        } else {

          System.out.println("Semoga beruntung lain kali " + getPemain().getNama());
          pemain.remove(pemainAktif);
          enterToCon();

        }

      } else {
        getPemain().setUang(jumlah);
      }

    } else {
      getPemain().setUang(jumlah);
      if (getPemain().getHutang() > 0.00) {
        System.out.println(
          getPemain().getNama() + 
          " Memiliki hutang sebesar " + 
          getPemain().getHutang()
        );
        if ( getPemain().getUang() > (getPemain().getHutang() / 3) ) {
          System.out.println(getPemain().getNama() + " Memiliki cukup uang untuk membayar 1/3 hutang anda \n game akan langsung mengurangi uang anda dengan 1/3 dari hutang anda");
          getPemain().setHutang(jumlah * -1);
          getPemain().setUang( (jumlah * -1) );
        }
      }
    }

  }

  public void bayar(Double jumlah, String id) {
    
    if (jumlah < 0) {

      if ( getPemain(id).getUang() < Math.abs(jumlah) ) {

        System.out.println(getPemain(id).getNama() + " Anda tidak memiliki uang yang cukup!");
        System.out.println("1. Anda ingin menghutang?\n 2. Ingin bangkrut saja?");
        int pilihan = ActionsHelper.getInputNumber(1, 2, "Pilihan anda : ");
        System.out.println();

        if (pilihan == 1) {

          if (getPemain(id).getHutang() > 100000.00) {
            System.out.println(getPemain(id).getNama() + " Anda telah berhutang terlalu banyak, dengan berat hati kami harus mengeluarkan anda dari game");
            System.out.println("Semoga beruntung lain kali " + getPemain(id).getNama());
            pemain.remove(pemainAktif);
            enterToCon();
          } else {
            getPemain(id).setHutang( Math.abs(jumlah) );
            System.out.println("Anda berhutang sebesar " + getPemain(id).getHutang());
            getPemain().setUang(20000.00);
          }

        } else {

          System.out.println("Semoga beruntung lain kali " + getPemain(id).getNama());
          pemain.remove(pemainAktif);
          this.pemainAktif -= 1;
          enterToCon();

        }

      } else {
        getPemain(id).setUang(jumlah);
      }

    } else {
      getPemain(id).setUang(jumlah);
    }

    if (getPemain(id).getHutang() > 0.00) {
      System.out.println(
        getPemain(id).getNama() + 
        " Memiliki hutang sebesar " + 
        getPemain(id).getHutang()
      );
      if ( getPemain(id).getUang() > (getPemain(id).getHutang() / 3) ) {
        System.out.println(getPemain(id).getNama() + " Memiliki cukup uang untuk membayar 1/3 hutang anda \n game akan langsung mengurangi uang anda dengan 1/3 dari hutang anda");
        getPemain(id).setHutang(jumlah * -1);
        getPemain(id).setUang( (jumlah * -1) );
      }
    }

  }

  public void ambilKartuBonus() {
    int kartu = (int) (Math.random() * listKartuBonus.size());
    getAksiKartuBonus(listKartuBonus.get(kartu));
    listKartuBonus.remove(pemainAktif);
  }

  public void getAksiKartuBonus(KartuBonus kartu) {
    System.out.println(kartu.getKet());
    switch (kartu.getAksi()) {
    case "bayar":
      this.bayar(kartu.getQty());
      break;
    case "pindah":
      getPemain().setPosisiAbsolute(kartu.getPosisi());
      getPemain().setUang(kartu.getQty());
      getActions(kartu.getPosisi());
      break;
    case "penjara":
      this.penjara();
      break;
    default:
      System.out.println("This seems to be doesn't right");
    }
  }

  public void checkJumlahAssets() {
    int ja = 0;
    for (Petak petak : getPapan()) {
      if (getPemain().getId().equals(petak.getPemilik())) {
        ja++;
      }
    }
      
    if (ja == 0) {
      System.out.println("Anda masih belum punya asset");
      System.out.println("-------------------------------------------");
    } else if (ja >= 0) {
      System.out.println( "List asset ente nih gan: ");
      int i = 1;
      for (Petak petak : getPapan()) {
        if (getPemain().getId().equals(petak.getPemilik())) {
          System.out.println(i + " " + petak.getNamaPetak() + " dan harganya " + petak.getHarga());
        }
        i++;
      }
      System.out.println("-------------------------------------------");
    }
}

  public void enterToCon() {
    System.out.print("Enter untuk melanjutkan...");
          
    try {
      System.in.read();
    } catch (IOException e) {
      System.out.println(e);
      e.printStackTrace();
    }
}

}
