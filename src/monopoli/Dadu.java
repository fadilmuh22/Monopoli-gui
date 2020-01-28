package monopoli;

public class Dadu {
  private int[] angka = {0,0};

  public int[] kocok() {
    angka[0] = (int) (Math.random() * 6 + 1);
    angka[1] = (int) (Math.random() * 6 + 1);

    return angka;
  }

}