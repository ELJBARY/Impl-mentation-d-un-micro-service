package ma.enset.compteservice.mappers;

import ma.enset.compteservice.dtos.CompteRequestDTO;
import ma.enset.compteservice.dtos.CompteResponseDTO;
import ma.enset.compteservice.entities.Compte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompteMapper {

    public Compte fromRequest(CompteRequestDTO dto) {
        Compte compte = new Compte();
        BeanUtils.copyProperties(dto, compte);
        return compte;
    }

    public CompteResponseDTO toResponse(Compte compte) {
        CompteResponseDTO dto = new CompteResponseDTO();
        BeanUtils.copyProperties(compte, dto);
        return dto;
    }
}
