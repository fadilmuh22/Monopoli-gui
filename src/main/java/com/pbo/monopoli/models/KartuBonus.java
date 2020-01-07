package com.pbo.monopoli.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KartuBonus {
  private int id, posisi;
  private Double qty;
  private String jenis, ket, aksi;

  public KartuBonus (
    @JsonProperty("id") int id, 
    @JsonProperty("jenis") String jenis, 
    @JsonProperty("ket") String ket,
    @JsonProperty("aksi") String aksi, 
    @JsonProperty("qty") double qty, 
    @JsonProperty("posisi") int posisi
  ) {
    this.setId(id);
    this.setJenis(jenis);
    this.setKet(ket);
    this.setAksi(aksi);
    this.setQty(qty);
    this.setPosisi(posisi);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getJenis() {
    return jenis;
  }

  public void setJenis(String jenis) {
    this.jenis = jenis;
  }

  public String getKet() {
    return ket;
  }

  public void setKet(String ket) {
    this.ket = ket;
  }

  public String getAksi() {
    return aksi;
  }

  public void setAksi(String aksi) {
    this.aksi = aksi;
  }

  public double getQty() {
    return qty;
  }

  public void setQty(double qty) {
    this.qty = qty;
  }

  public int getPosisi() {
    return posisi;
  }

  public void setPosisi(int posisi) {
    this.posisi = posisi;
  }

}
