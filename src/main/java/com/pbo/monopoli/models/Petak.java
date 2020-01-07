package com.pbo.monopoli.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
//"author": "ihsan_ganteng_and_family",

public class Petak {
  private String id;
  private String jenis;
  private String komplek;
  private String namaPetak;
  private Double harga = 0.00;
  private String pemilik = "game";
  private String aksi = "aset";
  private int[] hargaSewaRumah = {0};
  private int hargaSewaHotel = 0;
  private int[] hargaBeli = {0};
  private HashMap<String, Integer> aset = new HashMap<String, Integer>();

  public Petak (
    @JsonProperty("id_petak")
    String id,
    @JsonProperty("jenis")
    String jenis,
    @JsonProperty("komplek")
    String komplek, 
    @JsonProperty("nama")
    String namaPetak, 
    @JsonProperty("harga")
    Double harga,
    @JsonProperty("harga_sewa_rumah")
    int[] hargaSewaRumah,
    @JsonProperty("harga_sewa_hotel")
    int hargaSewaHotel,
    @JsonProperty("harga_beli")
    int[] hargaBeli,
    @JsonProperty("aksi")
    String aksi
  ) { // Map<String, Integer> aset
    this.setId(id);
    this.setJenis(jenis);
    this.setKomplek(komplek);
    this.setNamaPetak(namaPetak);
    this.setHarga(harga);
    this.setHargaSewaRumah(hargaSewaRumah);
    this.setHargaBeli(hargaBeli);
    this.setActions(aksi);
    this.hargaSewaHotel = hargaSewaHotel;
    this.aset.put("r", 0);
    this.aset.put("h", 0);
    // this.setAset(aset);
  }
  
  @JsonProperty("id_petak")
  public String getId() {
    return id;
  }

  @JsonProperty("id_petak")
  public void setId(String id) {
    this.id = id;
  }

  @JsonProperty("jenis")
  public String getJenis() {
    return jenis;
  }

  @JsonProperty("jenis")
  public void setJenis(String jenis) {
    this.jenis = jenis;
  }

  @JsonProperty("komplek")
  public String getKomplek() {
	  return komplek;
  }

  @JsonProperty("komplek")
  public void setKomplek(String komplek) {
    this.komplek = komplek;
  }

  @JsonProperty("nama")
  public String getNamaPetak() {
    return namaPetak;
  }

  @JsonProperty("nama")
  public void setNamaPetak(String namaPetak) {
    this.namaPetak = namaPetak;
  }
  
  @JsonProperty("harga")
  public Double getHarga() {
    return harga;
  }

  @JsonProperty("harga")
  public void setHarga(Double harga) {
    this.harga = harga;
  }

  @JsonProperty("harga_sewa_rumah")
  public int[] getHargaSewaRumah() {
    return hargaSewaRumah;
  }

  @JsonProperty("harga_sewa_rumah")
  public void setHargaSewaRumah(int[] hargaSewaRumah) {
    this.hargaSewaRumah = hargaSewaRumah;
  }

  public int getHargaSewaHotel() {
    return hargaSewaHotel;
  }

  public void setHargaSewaHotel(int hargaSewaHotel) {
    this.hargaSewaHotel = hargaSewaHotel;
  }

  @JsonProperty("harga_beli")
  public int[] getHargaBeli() {
    return hargaBeli;
  }

  @JsonProperty("harga_beli")
  public void setHargaBeli(int[] hargaBeli) {
    this.hargaBeli = hargaBeli;
  }


  @JsonProperty("aksi")
  public String getActions() {
    return aksi;
  }

  @JsonProperty("aksi")
  public void setActions(String aksi) {
    this.aksi = aksi;
  }

  public Map<String, Integer> getAset() {
    return aset;
  }

  public void setAset(String jenis, int jumlah) {
    this.aset.put(jenis, jumlah);
  }

  public void tambahAset(String jenis, int jumlah) {
    this.aset.put(jenis, jumlah);
  }

  public String getPemilik() {
    return pemilik;
  }

  public void setPemilik(String pemilik) {
    this.pemilik = pemilik;
  }
  
}