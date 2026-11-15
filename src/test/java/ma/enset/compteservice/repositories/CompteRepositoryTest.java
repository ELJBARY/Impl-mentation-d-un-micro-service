package ma.enset.compteservice.repositories;

import ma.enset.compteservice.entities.Compte;
import ma.enset.compteservice.entities.EtatCompte;
import ma.enset.compteservice.entities.TypeCompte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CompteRepositoryTest {

    @Autowired
    private CompteRepository compteRepository;

    @BeforeEach
    void init() {
        compteRepository.deleteAll(); // le CommandLineRunner a pu pré-remplir la base
        compteRepository.save(Compte.builder().solde(5000).dateCreation(LocalDate.now())
                .type(TypeCompte.COURANT).etat(EtatCompte.CREE).build());
        compteRepository.save(Compte.builder().solde(12000).dateCreation(LocalDate.now())
                .type(TypeCompte.EPARGNE).etat(EtatCompte.ACTIVE).build());
        compteRepository.save(Compte.builder().solde(800).dateCreation(LocalDate.now())
                .type(TypeCompte.COURANT).etat(EtatCompte.SUSPENDU).build());
    }

    @Test
    void findAllReturnsAllComptes() {
        assertThat(compteRepository.findAll()).hasSize(3);
    }

    @Test
    void findByTypeFiltersComptes() {
        List<Compte> courants = compteRepository.findByType(TypeCompte.COURANT);
        assertThat(courants).hasSize(2).allMatch(c -> c.getType() == TypeCompte.COURANT);
    }

    @Test
    void findByEtatFiltersComptes() {
        assertThat(compteRepository.findByEtat(EtatCompte.ACTIVE)).hasSize(1);
    }

    @Test
    void findByIdReturnsCompte() {
        Compte saved = compteRepository.findAll().get(0);
        assertThat(compteRepository.findById(saved.getId())).isPresent()
                .get().extracting(Compte::getSolde).isEqualTo(saved.getSolde());
    }

    @Test
    void deleteRemovesCompte() {
        Compte saved = compteRepository.findAll().get(0);
        compteRepository.deleteById(saved.getId());
        assertThat(compteRepository.findAll()).hasSize(2);
    }
}
