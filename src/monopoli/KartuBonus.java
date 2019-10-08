package monopoli;

public class KartuBonus {
  private String id;
  private String jenis;
  public String ket;
  private String aksi;

  public KartuBonus(String id, String jenis, String ket, String aksi) {
    this.setId(id);
    this.setJenis(jenis);
    this.setKet(ket);
    this.setAksi(aksi);
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

  public String getAksi() {
    return aksi;
  }

  public void setAksi(String aksi) {
    this.aksi = aksi;
  }

  public String getKet() {
    return ket;
  }

  public void setKet(String ket) {
    this.ket = ket;
  }

}
