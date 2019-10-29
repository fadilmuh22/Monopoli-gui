package com.pbo.monopoli.models;

import java.util.HashMap;
import java.util.List;

public class Pemain {
  private String id;
  private Double uang = 150000.00;
  private Double hutang = 0.00;
  private int posisi = 0;
  private String status = "main";
  private boolean hasPassesStart = false;
  private HashMap<String, Integer> aset = new HashMap<String, Integer>();

  public Pemain(String id) {
    this.setId(id);
  }

  public boolean isHasPassesStart() {
    return hasPassesStart;
  }

  public void setHasPassesStart(boolean hasPassesStart) {
    this.hasPassesStart = hasPassesStart;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getPosisi() {
    return posisi;
  }

  public void setPosisi(int posisi) {
    if (this.posisi < 10) {
      this.posisi += posisi;
      if (this.posisi >= 10 ) {
        System.out.println("Lewat dari kotak 10");
        this.posisi += 1;
      }
    } else {
      this.posisi += posisi;
      if (this.posisi >= 40) {
        this.posisi -= 40;
        if (this.posisi > 0) {
          this.setHasPassesStart(true);
        }
      }
    }
    
  }

  public void setPosisiAbsolute(int posisiAbsolute) {
    this.posisi = posisiAbsolute;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Double getUang() {
    return uang;
  }

  public void setUang(Double uang) {
    this.uang += uang;
  }

  public HashMap<String, Integer> getAset() {
    return aset;
  }

  public void setAset(String jenis, int jumlah) {
    this.aset.put(jenis, jumlah);
  }

  public Double getHutang() {
    return hutang;
  }

  public void setHutang(Double hutang) {
    this.hutang = hutang;
  }
}