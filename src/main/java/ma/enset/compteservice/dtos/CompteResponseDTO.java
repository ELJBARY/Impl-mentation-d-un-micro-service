package ma.enset.compteservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.compteservice.entities.EtatCompte;
import ma.enset.compteservice.entities.TypeCompte;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteResponseDTO {
    private Long id;
    private double solde;
    private LocalDate dateCreation;
    private TypeCompte type;
    private EtatCompte etat;
}
