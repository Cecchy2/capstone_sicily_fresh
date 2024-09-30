package dariocecchinato.capstone_sicily_fresh.entities;

import dariocecchinato.capstone_sicily_fresh.enums.Difficolta;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ricetta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID uuid;
    private String titolo;
    private String descrizione;
    private String immaginePiatto;
    @Enumerated(EnumType.STRING)
    private Difficolta difficolta;
    private String tempo;
    private String valoriNutrizionali;

    @OneToMany(mappedBy = "ricetta")
    private List<PassaggioDiPreparazione> passaggi;

    @OneToMany(mappedBy = "ricetta")
    private List<RicettaIngrediente> ricettaIngredienti;

    @ManyToOne
    @JoinColumn(name = "fornitore_id")
    private Utente fornitore;

    @OneToMany(mappedBy = "ricetta")
    private List<DettaglioOrdine> dettagliOrdini;

    public Ricetta(String titolo, String descrizione, String immaginePiatto, Difficolta difficolta, String tempo, String valoriNutrizionali, List<PassaggioDiPreparazione> passaggi, List<RicettaIngrediente> ricettaIngredienti, Utente fornitore, List<DettaglioOrdine> dettagliOrdini) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.immaginePiatto = immaginePiatto;
        this.difficolta = difficolta;
        this.tempo = tempo;
        this.valoriNutrizionali = valoriNutrizionali;
        this.passaggi = passaggi;
        this.ricettaIngredienti = ricettaIngredienti;
        this.fornitore = fornitore;
        this.dettagliOrdini = dettagliOrdini;
    }
}
