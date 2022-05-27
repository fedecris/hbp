package health.hbp.service;

import health.hbp.dto.EdibleDTO;

import java.util.List;

public interface EdibleService {

    public List<EdibleDTO> getEdiblesUnderGivenSodiumSaturationPercent(float percent);

    public List<EdibleDTO> getEdiblesWithLessSodiumThanPotassium();
}
