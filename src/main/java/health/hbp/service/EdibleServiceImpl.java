package health.hbp.service;

import health.hbp.dto.EdibleDTO;
import health.hbp.mapper.EdibleMapper;
import health.hbp.model.Edible;
import health.hbp.repository.EdibleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class EdibleServiceImpl implements EdibleService {

    private final EdibleRepository edibleRepository;

    @Inject
    private EdibleMapper mapper;

    /** Retorna los alimentos cuyo saturacion de sodio se encuenrte por debajo de cierto porcentaje */
    public List<EdibleDTO> getEdiblesUnderGivenSodiumSaturationPercent(float percent) {
        List<EdibleDTO> edibleDTOs = new ArrayList<>();
        edibleRepository.findAll().forEach(edible -> edibleDTOs.add(mapper.edibleToEdibleDTO(edible)));
        return edibleDTOs.stream().parallel().filter(edible -> edible.getSaturationSodium()!=null && edible.getSaturationSodium()<percent).collect(Collectors.toList());
    }

    /** Retorna los alimentos cuyo saturacion de sodio sea menor que de potasio */
    public List<EdibleDTO> getEdiblesWithLessSodiumThanPotassium() {
        List<EdibleDTO> edibleDTOs = new ArrayList<>();
        List<Edible> edibles = edibleRepository.findAll().stream().parallel().filter(
                                                    edible -> edible.getFacts()!=null &&
                                                    edible.getFacts().getSodium()!=null &&
                                                    edible.getFacts().getPotassium()!=null &&
                                                    edible.getFacts().getSodium()<edible.getFacts().getPotassium()
                                                ).collect(Collectors.toList());
        edibles.forEach(edible -> edibleDTOs.add(mapper.edibleToEdibleDTO(edible)));
        return edibleDTOs;
    }
}
