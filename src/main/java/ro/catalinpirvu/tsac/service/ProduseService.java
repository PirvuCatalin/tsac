package ro.catalinpirvu.tsac.service;

import org.springframework.stereotype.Service;
import ro.catalinpirvu.tsac.model.Produs;
import ro.catalinpirvu.tsac.repository.ProdusRepository;

import java.util.List;

@Service
public class ProduseService {
    private final ProdusRepository produsRepository;

    public ProduseService(ProdusRepository produsRepository) {
        this.produsRepository = produsRepository;
    }

    public boolean creeazaProdus(String nume, Float cost) {
        Produs produs = new Produs();
        produs.setNume(nume);
        produs.setCost(cost);
        produsRepository.save(produs);
        return true;
    }

    public Produs cautaProdus(String nume) {
        List<Produs> produs = produsRepository.findByNume(nume);
        if (produs.isEmpty()) {
            throw new IllegalArgumentException("Nu a putut fi gasit produsul cu acest nume.");
        }
        if (produs.size() > 1) {
            throw new IllegalArgumentException("Numele se refera la mai multe produse!");
        }
        return produs.get(0);
    }

    public boolean stergeProdus(String nume) {
        List<Produs> produse = produsRepository.findByNume(nume);
        if (produse.isEmpty()) {
            return false;
        }
        produsRepository.deleteAll(produse);
        return true;
    }
}
