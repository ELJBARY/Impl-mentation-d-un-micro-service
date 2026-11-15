package ma.enset.compteservice.entities;

import org.springframework.data.rest.core.config.Projection;

/** Vue sans date de création. Usage : /rest/comptes?projection=solde-type */
@Projection(name = "solde-type", types = Compte.class)
public interface CompteProjection2 {
    double getSolde();
    TypeCompte getType();
    EtatCompte getEtat();
}
