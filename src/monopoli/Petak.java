package monopoli;

import java.util.Map;
import java.util.HashMap;

public class Petak {
  private String id;
  private String jenis;
  private String komplek;
  private String namaPetak;
  private Double harga = 0.00;
  private String pemilik;
  private String aksi;
  private Map<String, Integer> aset = new HashMap<String, Integer>();


  public Petak(String komplek, String jenis, String namaPetak, Double harga, String aksi) { //Map<String, Integer> aset
    this.setKomplek(komplek);
    this.setJenis(jenis);
    this.setNamaPetak(namaPetak);
    this.setHarga(harga);
    this.setAksi(aksi);
//    this.setAset(aset);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

public Map<String, Integer> getAset() {
    return aset;
  }

  public void setAset(Map<String, Integer> aset) {
    this.aset = aset;
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

  public Double getHarga() {
    return harga;
  }

  public void setHarga(Double harga) {
    this.harga = harga;
  }

  public String getNamaPetak() {
    return namaPetak;
  }

  public void setNamaPetak(String namaPetak) {
    this.namaPetak = namaPetak;
  }

  public String getKomplek() {
	  return komplek;
  }

  public void setKomplek(String komplek) {
    this.komplek = komplek;
  }

  public String getJenis() {
    return jenis;
  }

  public void setJenis(String jenis) {
    this.jenis = jenis;
  }

  public String getAksi() {
    return aksi;
  }


  public void setAksi(String aksi) {
    this.aksi = aksi;
  }
  
}