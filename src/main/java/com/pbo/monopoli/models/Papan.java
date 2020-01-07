package com.pbo.monopoli.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.*;

import com.pbo.monopoli.models.Petak;

public class Papan {

  private List<Petak> listPetak = new ArrayList<Petak>();

  public Papan(@JsonProperty("data") List<Petak> lp) {
    this.listPetak.addAll(lp);
  }
  
  public void setPapan(List<Petak> lp) {
    this.listPetak.addAll(lp);
  }

  public List<Petak> getListPetak() {
    return this.listPetak;
  }
}