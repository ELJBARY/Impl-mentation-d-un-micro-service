package ma.enset.compteservice.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SpringDataRestTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void collectionExposeeAvecIds() throws Exception {
        mvc.perform(get("/rest/comptes")).andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.comptes[0].id").exists());
    }

    @Test
    void projectionMobileNeRetourneQueIdEtSolde() throws Exception {
        mvc.perform(get("/rest/comptes/1?projection=mobile")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.solde").exists())
                .andExpect(jsonPath("$.type").doesNotExist());
    }

    @Test
    void projectionSoldeType() throws Exception {
        mvc.perform(get("/rest/comptes/1?projection=solde-type")).andExpect(status().isOk())
                .andExpect(jsonPath("$.type").exists())
                .andExpect(jsonPath("$.dateCreation").doesNotExist());
    }

    @Test
    void rechercheParType() throws Exception {
        mvc.perform(get("/rest/comptes/search/byType?t=EPARGNE")).andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.comptes[0].type").value("EPARGNE"));
    }
}
