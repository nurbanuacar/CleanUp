/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Survey.business;

import com.fasterxml.jackson.databind.JsonNode;
import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Core.result.ErrorDataResult;
import com.mepsan.MlbClean.Core.result.SuccessDataResult;
import com.mepsan.MlbClean.Dto.SurveyDto;
import com.mepsan.MlbClean.Survey.controller.SurveyController;
import com.mepsan.MlbClean.Survey.entity.SurveyEntity;
import com.mepsan.MlbClean.Survey.repository.SurveyRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public DataResult<List<SurveyDto>> getAllSurvey() {
        List<SurveyEntity> surveys = surveyRepository.findAll();
        List<SurveyDto> surveyDtos = new ArrayList<>();
        SurveyDto surveyDto = new SurveyDto();

        if (!surveys.isEmpty()) {
            for (SurveyEntity survey : surveys) {
                surveyDto.setId(survey.getId());
                surveyDto.setComment(survey.getComment());
                surveyDto.setDeviceId(survey.getDeviceId());
                surveyDto.setPerson(survey.getPerson());
                surveyDto.setRating(survey.getRating());
                surveyDto.setSurveyDate(survey.getSurveyDate());
                surveyDtos.add(surveyDto);
            }
            return new SuccessDataResult<>(surveyDtos);
        } else {
            return new ErrorDataResult<>("Hiçbir Anket Bulunamadı.");
        }
    }

    @Override
    public DataResult<SurveyDto> getSurveyById(int id) {
        Optional<SurveyEntity> survey = surveyRepository.findById(id);
        SurveyDto surveyDto = new SurveyDto();
        if (survey.isPresent()) {
            surveyDto.setComment(survey.get().getComment());
            surveyDto.setDeviceId(survey.get().getDeviceId());
            surveyDto.setId(survey.get().getId());
            surveyDto.setPerson(survey.get().getPerson());
            surveyDto.setRating(survey.get().getRating());
            surveyDto.setSurveyDate(survey.get().getSurveyDate());
            return new SuccessDataResult<>(surveyDto);
        } else {
            return new ErrorDataResult<>("Anket Bulunamadı.");
        }
    }

    @Override
    public DataResult<List<SurveyDto>> getSurveyByDeviceId(int deviceId) {
        List<SurveyEntity> surveys = surveyRepository.findByDeviceId(deviceId);
        List<SurveyDto> surveyDtos = new ArrayList<>();
        SurveyDto surveyDto = new SurveyDto();

        if (!surveys.isEmpty()) {
            for (SurveyEntity survey : surveys) {
                surveyDto.setId(survey.getId());
                surveyDto.setComment(survey.getComment());
                surveyDto.setDeviceId(survey.getDeviceId());
                surveyDto.setPerson(survey.getPerson());
                surveyDto.setRating(survey.getRating());
                surveyDto.setSurveyDate(survey.getSurveyDate());
                surveyDtos.add(surveyDto);
            }
            return new SuccessDataResult<>(surveyDtos);
        } else {
            return new ErrorDataResult<>("Cihaza Ait Hiçbir Anket Bulunamadı.");
        }
    }

    @Override
    public DataResult<SurveyDto> save(SurveyEntity survey) {
        
        SurveyEntity newSurvey = surveyRepository.save(survey);

        if (newSurvey.getId() > 0) {
            SurveyDto surveyDto = new SurveyDto();
            surveyDto.setId(survey.getId());
            surveyDto.setComment(survey.getComment());
            surveyDto.setDeviceId(survey.getDeviceId());
            surveyDto.setPerson(survey.getPerson());
            surveyDto.setRating(survey.getRating());
            surveyDto.setSurveyDate(survey.getSurveyDate());
            return new SuccessDataResult<>(surveyDto);
        } else {
            return new ErrorDataResult<>("Anket Kaydedilirken Bir Hata Oluştu");
        }
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
    public DataResult<List<SurveyDto>> getSurveyByDateBetween(JsonNode json) {
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

        List<SurveyEntity> surveys = surveyRepository.findAllBySurveyDateBetween(startDate, endDate);
        List<SurveyDto> surveyDtos = new ArrayList<>();
        SurveyDto surveyDto = new SurveyDto();

        if (!surveys.isEmpty()) {
            for (SurveyEntity survey : surveys) {
                surveyDto.setId(survey.getId());
                surveyDto.setComment(survey.getComment());
                surveyDto.setDeviceId(survey.getDeviceId());
                surveyDto.setPerson(survey.getPerson());
                surveyDto.setRating(survey.getRating());
                surveyDto.setSurveyDate(survey.getSurveyDate());
                surveyDtos.add(surveyDto);
            }
            return new SuccessDataResult<>(surveyDtos);
        }else{
            return new ErrorDataResult<>("Tarih Aralığında Anket Bulunamadı");
        }
    }

    @Override
    public DataResult<List<SurveyDto>> getSurveyByRatingIn(int startRating) {
        
        List<SurveyEntity> surveys = surveyRepository.findByRatingIn(startRating);
        List<SurveyDto> surveyDtos = new ArrayList<>();
        SurveyDto surveyDto = new SurveyDto();

        if (!surveys.isEmpty()) {
            for (SurveyEntity survey : surveys) {
                surveyDto.setId(survey.getId());
                surveyDto.setComment(survey.getComment());
                surveyDto.setDeviceId(survey.getDeviceId());
                surveyDto.setPerson(survey.getPerson());
                surveyDto.setRating(survey.getRating());
                surveyDto.setSurveyDate(survey.getSurveyDate());
                surveyDtos.add(surveyDto);
            }
            return new SuccessDataResult<>(surveyDtos);
        }else{
            return new ErrorDataResult<>("Anket Bulunamadı");
        }
        
    }

}
