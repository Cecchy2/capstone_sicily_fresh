package dariocecchinato.capstone_sicily_fresh.entities;

import dariocecchinato.capstone_sicily_fresh.enums.StatoOrdine;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CarrelloDettaglio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "carrello_id")
    private Carrello carrello;

    @ManyToOne
    @JoinColumn(name = "ricetta_id")
    private Ricetta ricetta;

    private int quantita;

    @Enumerated(EnumType.STRING)
    private StatoOrdine statoOrdine;

    public CarrelloDettaglio(Carrello carrello, Ricetta ricetta, int quantita, StatoOrdine statoOrdine) {
        this.carrello = carrello;
        this.ricetta = ricetta;
        this.quantita = quantita;
        this.statoOrdine = statoOrdine;
    }
}
