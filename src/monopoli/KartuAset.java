package monopoli;

import java.util.Map;
import java.util.HashMap;

public class KartuAset {
  private String id;
  private String jenis;
  private String komplek;
  private String namaPetak;
  private Double harga = 0.00;
  private Map<String, String> ListHargaBeli = new HashMap<String, String>();
  private Map<String, String> ListHargaSewa = new HashMap<String, String>();
  
  public KartuAset(String id, String komplek, String namaPetak, Double harga, Map<String, String> listHargaBeli, Map<String, String> listHargaSewa) {
    this.setId(id);
    this.setKomplek(komplek);
    this.setNamaPetak(namaPetak);
    this.setHarga(harga);
    this.setListHargaBeli(listHargaBeli);
    this.setListHargaSewa(listHargaSewa);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getJenis() {
    return jenis;
  }

  public void setJenis(String jenis) {
    this.jenis = jenis;
  }

  public String getKomplek() {
    return komplek;
  }

  public void setKomplek(String komplek) {
    this.komplek = komplek;
  }

  public String getNamaPetak() {
    return namaPetak;
  }

  public void setNamaPetak(String namaPetak) {
    this.namaPetak = namaPetak;
  }

  public Double getHarga() {
    return harga;
  }

  public void setHarga(Double harga) {
    this.harga = harga;
  }

  public Map<String, String> getListHargaBeli() {
    return ListHargaBeli;
  }

  public void setListHargaBeli(Map<String, String> listHargaBeli) {
    this.ListHargaBeli = listHargaBeli;
  }

  public Map<String, String> getListHargaSewa() {
    return ListHargaSewa;
  }

  public void setListHargaSewa(Map<String, String> listHargaSewa) {
    this.ListHargaSewa = listHargaSewa;
  }

}