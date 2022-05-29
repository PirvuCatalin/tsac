package ro.catalinpirvu.tsac.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Embeddable
public class Produs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String nume;

    @Column
    private Float cost;
}
