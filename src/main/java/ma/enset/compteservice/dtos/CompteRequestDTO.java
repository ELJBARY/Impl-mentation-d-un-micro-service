package ma.enset.compteservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.compteservice.entities.EtatCompte;
import ma.enset.compteservice.entities.TypeCompte;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteRequestDTO {
    private Double solde;   // objet : null = "non fourni" (mise à jour partielle)
    private TypeCompte type;
    private EtatCompte etat;
}
