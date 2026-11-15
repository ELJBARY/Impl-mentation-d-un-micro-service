package ma.enset.compteservice.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CompteRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void listeContientLesComptesInitiaux() throws Exception {
        mvc.perform(get("/api/comptes")).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(org.hamcrest.Matchers.greaterThanOrEqualTo(3)));
    }

    @Test
    void crudComplet() throws Exception {
        String body = "{\"solde\":1500,\"type\":\"COURANT\",\"etat\":\"CREE\"}";
        String json = mvc.perform(post("/api/comptes").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.id").exists())
                .andReturn().getResponse().getContentAsString();
        String id = json.replaceAll(".*\"id\":(\\d+).*", "$1");

        mvc.perform(get("/api/comptes/" + id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.solde").value(1500));
        mvc.perform(put("/api/comptes/" + id).contentType(MediaType.APPLICATION_JSON)
                        .content("{\"solde\":2500,\"etat\":\"ACTIVE\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.solde").value(2500))
                .andExpect(jsonPath("$.etat").value("ACTIVE"));
        mvc.perform(delete("/api/comptes/" + id)).andExpect(status().isNoContent());
        mvc.perform(get("/api/comptes/" + id)).andExpect(status().isNotFound());
    }

    @Test
    void swaggerDocEstExposee() throws Exception {
        mvc.perform(get("/v3/api-docs")).andExpect(status().isOk())
                .andExpect(jsonPath("$.paths['/api/comptes']").exists());
    }
}
