package ro.catalinpirvu.tsac.service;

import org.springframework.stereotype.Service;
import ro.catalinpirvu.tsac.model.CosDeCumparaturi;
import ro.catalinpirvu.tsac.model.Produs;

import java.util.Map;

@Service
public class CosDeCumparaturiService {
    private final CosDeCumparaturi cosDeCumparaturi;
    private final ProduseService produseService;

    public CosDeCumparaturiService(ProduseService produseService) {
        this.produseService = produseService;
        this.cosDeCumparaturi = new CosDeCumparaturi();
    }

    public boolean adaugaProdus(String nume, Integer cantitate) {
        Produs produs;
        try {
            produs = produseService.cautaProdus(nume);
        } catch (IllegalArgumentException e) {
            return false;
        }
        this.cosDeCumparaturi.adaugaProdus(produs, cantitate);
        return true;
    }

    public boolean scoateProdus(String nume) {
        Produs produs;
        try {
            produs = produseService.cautaProdus(nume);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return this.cosDeCumparaturi.scoateProdus(produs);
    }

    public Float getCostTotal() {
        return this.cosDeCumparaturi.getCostTotal();
    }

    public Map<Produs, Integer> getComponentaCosDeCumparaturi() {
        return this.cosDeCumparaturi.getProduse();
    }
}
