package ma.enset.compteservice.entities;

import org.springframework.data.rest.core.config.Projection;

/** Vue légère : id et solde. Usage : /rest/comptes?projection=mobile */
@Projection(name = "mobile", types = Compte.class)
public interface CompteProjection1 {
    Long getId();
    double getSolde();
}
