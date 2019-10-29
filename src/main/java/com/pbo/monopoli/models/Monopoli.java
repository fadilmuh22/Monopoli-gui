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

public class Monopoli {
  private Dadu dadu = new Dadu();
  private Papan papan;
  private List<Pemain> pemain = new ArrayList<Pemain>();
  private List<KartuBonus> listKartuBonus;
  private int pemainAktif = 0;
  private int jumlahMove = 0;
  private int jumlahMovePenjara = 0;
  public int jumlahPemain;

  public void setup(int pemain) {
    setPapan();
    setKartuBonus();
    setPemain(pemain);

    this.jumlahPemain = pemain;
  }

  public List<Petak> getPapan() {
    return papan.getListPetak();
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
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
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
      f = this.getClass().getResourceAsStream("/com/pbo/monopoli/kartu_bonus.json");
      if (true) {
        try {
          this.listKartuBonus = mapper.readValue(f, new TypeReference<List<KartuBonus>>() {
          });
        } catch (Exception e) {
          System.err.println(e);
          e.printStackTrace();
        }
      }
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

  public List<KartuBonus> getListKartuBonus() {
    return listKartuBonus;
  }

  public void setListKartuBonus(List<KartuBonus> listKartuBonus) {
    this.listKartuBonus = listKartuBonus;
  }

  public List<Pemain> getPemain() {
    return pemain;
  }

  public void setPemain(int jumlah) {
    for (int id = 0; id < jumlah; id++) {
      this.pemain.add(new Pemain("p" + id));
    }

    // acakPemainPertama();

  }

  // public void acakPemainPertama() {
  // int acak = (int) (Math.random() * this.jumlahPemain + 1);
  // Collections.swap(pemain, 0, acak);
  // }

  public void move() {
    if (jumlahMove == 3) {
      System.out.println("Punteun ente masuk penjara");
      this.penjara();
    }

    if (this.pemain.get(this.pemainAktif).getStatus().equals("penjara")) {
      this.penjara();
    } else {
      System.out.println("===============================================\n");
      System.out.println("Giliran pemain: " + this.pemainAktif);
      int[] angka = this.dadu.kocok();
      System.out.println(Arrays.toString(angka));

      if (angka[0] == angka[1]) {
        System.out.println("Double!");
        this.pemain.get(this.pemainAktif).setPosisi((angka[0] + angka[1]));
        int posisiPemai = this.pemain.get(this.pemainAktif).getPosisi();

        System.out.println("Pemain " + this.pemainAktif + " Ada di posisi " + posisiPemai);
        System.out.println("Berada di kotak " + this.getPapan().get(posisiPemai).getNamaPetak());

        this.getActions(posisiPemai);
        this.move();
        jumlahMove += 1;

      } else if (angka[0] != angka[1]) {
        this.pemain.get(this.pemainAktif).setPosisi((angka[0] + angka[1]));
        int posisiPemain = this.pemain.get(this.pemainAktif).getPosisi();

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

  public void setPemainAktif() {
    try {
      System.in.read();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if (this.pemainAktif == this.jumlahPemain - 1) {
      this.pemainAktif = 0;
    } else {
      this.pemainAktif += 1;
    }

    this.jumlahMove = 0;
  }

  public void getActions(int posisiPemain) {
    this.checkStart();
    Scanner sc = new Scanner(System.in);

    String ya;
    switch (this.getPapan().get(posisiPemain).getActions()) {
      case "assets":
        if (!this.getPapan().get(posisiPemain).getPemilik().equals("game")) {
          System.out.println("Duit ente segini gan " + this.pemain.get(this.pemainAktif).getUang());
          if (this.getPapan().get(posisiPemain).getPemilik().equals(this.getPemain().get(this.pemainAktif).getId())) {
            System.out.println("Apakah Ingin Tambah Asset? ");
            ya = sc.nextLine();
            if (ya.equals("y")) {
              String jenisAset;
              int jumlahAset = 1;

              int[] hargaBeli = this.getPapan().get(posisiPemain).getHargaBeli();

              System.out.println("( r (rumah) / h (hotel) )");
              jenisAset = sc.nextLine();
              if ( jenisAset.equals("r") ) {
                System.out.println("Harga beli rumah adalah " + hargaBeli[0]);
                System.out.println("Berapa jumlahnya? ");
                jumlahAset = sc.nextInt();
              } else if( jenisAset.equals("h") ) {
                if ( this.getPapan().get( posisiPemain ).getAset().get("r") >= 4 ) {
                  System.out.println("Harga beli hotel adalah " + hargaBeli[1]);
                  System.out.println("Berapa jumlahnya? ");
                  jumlahAset = sc.nextInt();
                } else {
                  System.out.println("Maaf tidak bisa membeli hotel jika tidak memiliki 4 rumah di petak ini");
                }
              }

              this.getPapan().get(posisiPemain).tambahAset(jenisAset, jumlahAset);

              this.bayar((double) hargaBeli[jumlahAset]);

            }
          } else if (!this.getPapan().get(posisiPemain).getPemilik()
              .equals(this.getPemain().get(this.pemainAktif).getId())) {
                
            int[] hargaSewaRumah = this.getPapan().get(posisiPemain).getHargaSewaRumah();
            int hargaSewaHotel = this.getPapan().get(posisiPemain).getHargaSewaHotel();
            int hargaSewaAsetHotel = 0;
            if (this.getPapan().get( posisiPemain ).getAset().get("h") > 0) {
              hargaSewaAsetHotel = hargaSewaHotel * this.getPapan().get( posisiPemain ).getAset().get("h");
            }
            int hargaSewaTotal = hargaSewaRumah[ this.getPapan().get( posisiPemain ).getAset().get("r") ] + hargaSewaAsetHotel;

            System.out.println(
                "Ente ada di tanah orang dan harus bayar segini nih: " + this.getPapan().get(posisiPemain).getHarga());
            System.out.println("Harga sewa petak ini adalah " + hargaSewaTotal);
            ya = sc.nextLine();
            if (!ya.equals("p")) {

              this.bayar( (double) hargaSewaTotal * -1 );
            }
            System.out.println("Duit ente segini lagi sekarang gan " + this.pemain.get(this.pemainAktif).getUang());
            break;
          }

        }

        System.out.println("Beli gak gan? Harganya segini nih: " + this.getPapan().get(posisiPemain).getHarga());
        ya = sc.nextLine();
        if (ya.equals("y")) {
          this.bayar((this.getPapan().get(posisiPemain).getHarga()) * -1);
          this.getPapan().get(posisiPemain).setPemilik(this.getPemain().get(this.pemainAktif).getId());
        }
        System.out.println("Duit ente segini lagi sekarang gan " + this.pemain.get(this.pemainAktif).getUang());

        break;
      case "asset":
        if (!this.getPapan().get(posisiPemain).getPemilik().equals("game")) {
          if (!this.getPapan().get(posisiPemain).getPemilik().equals(this.getPemain().get(this.pemainAktif).getId())) {
            System.out.println(
                "Ente ada di tanah orang dan harus bayar segini nih: " + this.getPapan().get(posisiPemain).getHarga());
            ya = sc.nextLine();
            if (ya.equals("y")) {
              this.bayar((this.getPapan().get(posisiPemain).getHarga()) * -1);
            }
            System.out.println("Duit ente segini lagi sekarang gan " + this.pemain.get(this.pemainAktif).getUang());
            break;
          }
          break;
        }
        System.out.println("Duit ente segini gan " + this.pemain.get(this.pemainAktif).getUang());

        System.out.println("Beli gak gan? Harganya segini nih: " + this.getPapan().get(posisiPemain).getHarga());
        ya = sc.nextLine();
        if (ya.equals("y")) {
          this.bayar((this.getPapan().get(posisiPemain).getHarga()) * -1);
          this.getPapan().get(posisiPemain).setPemilik(this.getPemain().get(this.pemainAktif).getId());
        }
        System.out.println("Duit ente segini lagi sekarang gan " + this.pemain.get(this.pemainAktif).getUang());
        break;

      case "bayar":
        System.out.println("Ente Ada di Petak " + this.getPapan().get(posisiPemain).getNamaPetak()
            + " Dan Harus Bayar Segini " + this.getPapan().get(posisiPemain).getHarga());
        this.bayar(this.getPapan().get(posisiPemain).getHarga() * -1);
        break;
      case "kartu":
        System.out.println("Dapat kartu bonus");
        this.ambilKartuBonus();
        break;
      case "jail":
        System.out.println("Anda Masuk Penjara");
        this.penjara();
        break;
      case "start":
        System.out.println("Anda ada di start");
        break;
      case "fly":
        System.out.println("Anda ada di kotak parkir bebas, dan bebas untuk kemana aja :)");
        int kotakPilihanPemain = sc.nextInt();
        this.pemain.get( this.pemainAktif ).setPosisiAbsolute( kotakPilihanPemain );
        this.getActions( this.pemain.get(this.pemainAktif).getPosisi() );
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
    if (this.getPemain().get( this.pemainAktif ).isHasPassesStart()) {
      System.out.println("Ente udh lewat start dan dapat cuan 20.000,00");
      this.getPemain().get( this.pemainAktif ).setUang(20000.00);
      this.getPemain().get( this.pemainAktif ).setHasPassesStart(false);
    }
  }

  public void penjara() {
    Scanner sc = new Scanner(System.in);
    String ya;

    this.pemain.get(pemainAktif).setPosisiAbsolute(11);
    this.pemain.get(pemainAktif).setStatus("penjara");

    int[] angka = this.dadu.kocok();

    if (this.jumlahMovePenjara == 3) {
      System.out.println("Ente 3 kali kesempatan ga lolos keluar penjara dengan bayar 5000 (y/t)");
      ya = sc.nextLine();
      if (ya.equals("y")) {
        this.pemain.get(pemainAktif).setUang(-5000.00);
      }
      setPemainAktif();
      this.move();
    } else if (angka[0] == angka[1]) {
      System.out.println(Arrays.toString(angka));
      System.out.println("Double !!!, dan berhasil kelaur penjara");
      this.jumlahMovePenjara = 0;
      this.pemain.get(pemainAktif).setStatus("main");
      this.pemain.get(pemainAktif).setPosisi((angka[0] + angka[1]));
      System.out.println("pemain " + this.pemainAktif + " Ada di posisi " + this.pemain.get(pemainAktif).getPosisi());
      System.out
          .println("Berada di kotak " + this.getPapan().get(this.pemain.get(pemainAktif).getPosisi()).getNamaPetak());
      System.out.println("");
      this.getActions(pemainAktif);
      this.setPemainAktif();
    } else if (angka[0] != angka[1]) {
      System.out.println(Arrays.toString(angka));
      this.penjara();
      this.jumlahMovePenjara += 1;
    }
  }

  public void bayar(Double jumlah) {
    this.pemain.get(this.pemainAktif).setUang(jumlah);
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
      this.pemain.get(this.pemainAktif).setPosisi(kartu.getPosisi());
      this.pemain.get(this.pemainAktif).setUang(kartu.getQty());
      break;
    case "penjara":
      this.penjara();
      break;
    default:
      System.out.println("This seems to be doesn't right");
    }
  }

}
