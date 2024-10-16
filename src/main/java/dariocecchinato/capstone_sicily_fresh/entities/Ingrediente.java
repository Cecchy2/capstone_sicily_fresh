package dariocecchinato.capstone_sicily_fresh.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Ingrediente {
    @Id
    @Setter(AccessLevel.NONE)
    private UUID uuid;
    private String nome;
    private String descrizione;
    private String valoriNutrizionali;
    @OneToMany(mappedBy = "ingrediente")
    private List<RicettaIngrediente> ricettaIngredienti;

    public Ingrediente(String nome, String descrizione, String valoriNutrizionali) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.valoriNutrizionali = valoriNutrizionali;
    }
}
