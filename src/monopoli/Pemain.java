package monopoli;

public class Pemain {
  private String id;
  private Double uang = 150000.00;
  private String[] aset;
  private Double hutang = 0.00;
  private int posisi = 0;
  private String status = "main";

  public Pemain(String id) {
    this.setId(id);
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
    this.posisi = this.posisi + posisi;
    
    if (this.posisi >= 35) {
        this.posisi -= 35;
    }
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

  public String[] getAset() {
    return aset;
  }

  public void setAset(String[] aset) {
    this.aset = aset;
  }

  public Double getHutang() {
    return hutang;
  }

  public void setHutang(Double hutang) {
    this.hutang = hutang;
  }
}