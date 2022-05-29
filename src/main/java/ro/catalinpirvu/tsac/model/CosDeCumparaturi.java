package ro.catalinpirvu.tsac.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CosDeCumparaturi {
    // un map de <produs, cantitate>
    private Map<Produs, Integer> produse = new HashMap<>();

    private Float costTotal = 0f;

    public void adaugaProdus(Produs produs, Integer cantitate) {
        this.produse.put(produs, cantitate);
        this.costTotal = this.costTotal + produs.getCost() * cantitate;
    }

    public boolean scoateProdus(Produs produs) {
        if (this.produse.containsKey(produs)) {
            Integer cantitate = this.produse.remove(produs);
            this.costTotal = this.costTotal - produs.getCost() * cantitate;
            return true;
        }
        return false;
    }
}
