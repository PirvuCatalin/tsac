package ro.catalinpirvu.tsac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.catalinpirvu.tsac.model.Produs;

import java.util.List;

public interface ProdusRepository extends JpaRepository<Produs, Long> {
    List<Produs> findByNume(String nume);
}
