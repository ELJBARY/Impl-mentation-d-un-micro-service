package ma.enset.compteservice;

import ma.enset.compteservice.entities.Compte;
import ma.enset.compteservice.entities.EtatCompte;
import ma.enset.compteservice.entities.TypeCompte;
import ma.enset.compteservice.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class CompteserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompteserviceApplication.class, args);
    }

    // Étape 4 : test de la couche DAO au démarrage
    @Bean
    CommandLineRunner start(CompteRepository compteRepository) {
        return args -> {
            compteRepository.save(Compte.builder()
                    .solde(5000).dateCreation(new Date())
                    .type(TypeCompte.COURANT).etat(EtatCompte.CREE).build());
            compteRepository.save(Compte.builder()
                    .solde(12000).dateCreation(new Date())
                    .type(TypeCompte.EPARGNE).etat(EtatCompte.ACTIVE).build());
            compteRepository.save(Compte.builder()
                    .solde(800).dateCreation(new Date())
                    .type(TypeCompte.COURANT).etat(EtatCompte.SUSPENDU).build());

            System.out.println("===== Liste des comptes =====");
            compteRepository.findAll().forEach(c -> System.out.println(c));
            System.out.println("===== Comptes de type COURANT =====");
            compteRepository.findByType(TypeCompte.COURANT).forEach(c -> System.out.println(c));
        };
    }
}
