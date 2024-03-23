/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mepsan.MlbClean.Survey.business;

import com.fasterxml.jackson.databind.JsonNode;
import com.mepsan.MlbClean.Survey.entity.SurveyEntity;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author nurbanu.acar
 */
public interface SurveyService {

    public List<SurveyEntity> getAllSurvey();

    public Optional<SurveyEntity> getSurveyById(int id);

    public List<SurveyEntity> getSurveyByDeviceId(int deviceId);

    public List<SurveyEntity> getSurveyByRatingIn(Collection ratings);

    public List<SurveyEntity> getSurveyByDateBetween(JsonNode json);

    public SurveyEntity save(SurveyEntity survey);

    public ResponseEntity<SurveyEntity> deleteById(int id);
}
