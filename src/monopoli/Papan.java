package monopoli;

import java.util.Map;
import java.util.HashMap;

public class Papan {
  private Map<Integer, Petak> listPetak = new HashMap<Integer, Petak>();
  
  public Papan(Map<Integer, Petak> lp) {
    this.listPetak.putAll(lp);
  }

  public Map<Integer, Petak> getListPetak() {
    return this.listPetak;
  }
}