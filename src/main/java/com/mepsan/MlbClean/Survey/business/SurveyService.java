/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mepsan.MlbClean.Survey.business;

import com.fasterxml.jackson.databind.JsonNode;
import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Dto.SurveyDto;
import com.mepsan.MlbClean.Survey.entity.SurveyEntity;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author nurbanu.acar
 * 
 * Burada herhangi bir user bilgisi alınmamıştır çünkü anketler anonimm gerçekleşecek.
 */
public interface SurveyService {

    public DataResult<List<SurveyDto>> getAllSurvey();

    public DataResult<SurveyDto> getSurveyById(int id);

    public DataResult<List<SurveyDto>> getSurveyByDeviceId(int deviceId);

    public DataResult<List<SurveyDto>> getSurveyByRatingIn(int startRating);

    public DataResult<List<SurveyDto>> getSurveyByDateBetween(JsonNode json);

    public DataResult<SurveyDto> save(SurveyEntity survey);

    public ResponseEntity<SurveyEntity> deleteById(int id);
}
