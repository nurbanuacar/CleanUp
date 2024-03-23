/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Survey.business;

import com.fasterxml.jackson.databind.JsonNode;
import com.mepsan.MlbClean.Survey.controller.SurveyController;
import com.mepsan.MlbClean.Survey.entity.SurveyEntity;
import com.mepsan.MlbClean.Survey.repository.SurveyRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author nurbanu.acar
 */
@Service
public class SurveyManager implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public List<SurveyEntity> getAllSurvey() {
        return surveyRepository.findAll();
    }

    @Override
    public Optional<SurveyEntity> getSurveyById(int id) {
        return surveyRepository.findById(id);
    }

    @Override
    public List<SurveyEntity> getSurveyByDeviceId(int deviceId) {
        return surveyRepository.findByDeviceId(deviceId);
    }

    @Override
    public SurveyEntity save(SurveyEntity survey) {
        return surveyRepository.save(survey);
    }

    @Override
    public ResponseEntity<SurveyEntity> deleteById(int id) {
        ResponseEntity response = null;
        if (surveyRepository.existsById(id)) {
            surveyRepository.deleteById(id);
            response = (ResponseEntity) ResponseEntity.noContent();
        } else {
            response = (ResponseEntity) ResponseEntity.notFound();
        }
        return response;
    }

    @Override
    public List<SurveyEntity> getSurveyByDateBetween(JsonNode json) {
        Date startDate = new Date();
        Date endDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");

        try {
            startDate = formatter.parse(json.get("startDate").asText());
            endDate = formatter.parse(json.get("endDate").asText());
        } catch (ParseException ex) {
            System.out.println("---- getSurveyByDateBetween CATCH ----------- " + ex);
            Logger.getLogger(SurveyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return surveyRepository.findAllBySurveyDateBetween(startDate, endDate);
    }

    @Override
    public List<SurveyEntity> getSurveyByRatingIn(Collection ratings) {
        return surveyRepository.findByRatingIn(ratings);
    }

}
