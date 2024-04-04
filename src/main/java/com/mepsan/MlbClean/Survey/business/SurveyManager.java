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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

        if (!surveys.isEmpty()) {
            List<SurveyDto> surveyDtos = new ArrayList<>();
            for (SurveyEntity survey : surveys) {
                SurveyDto surveyDto = new SurveyDto(survey.getId(), survey.getComment(), survey.getPerson(), survey.getRating(), survey.getSurveyDate(), survey.getDevice());
                surveyDtos.add(surveyDto);
            }
            return new SuccessDataResult<>("Anketler Başarılı Şekilde Listelendi.", surveyDtos);
        } else {
            return new ErrorDataResult<>("Hiçbir Anket Bulunamadı.");
        }
    }

    @Override
    public DataResult<SurveyDto> getSurveyById(int id) {
        Optional<SurveyEntity> survey = surveyRepository.findById(id);
        if (survey.isPresent()) {
            SurveyEntity existSurvey = survey.get();
            SurveyDto surveyDto = new SurveyDto(existSurvey.getId(), existSurvey.getComment(), existSurvey.getPerson(), existSurvey.getRating(), existSurvey.getSurveyDate(), existSurvey.getDevice());
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
                surveyDto.setDevice(survey.getDevice());
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
            surveyDto.setDevice(survey.getDevice());
            surveyDto.setPerson(survey.getPerson());
            surveyDto.setRating(survey.getRating());
            surveyDto.setSurveyDate(new Date());
            return new SuccessDataResult<>(surveyDto);
        } else {
            return new ErrorDataResult<>("Anket Kaydedilirken Bir Hata Oluştu");
        }
    }

    @Override
    public void deleteById(int id) {
        surveyRepository.deleteById(id);
    }

    @Override
    public DataResult deleteSurvey(int id, int updateId) {
        Optional<SurveyEntity> deleteSurvey = surveyRepository.findById(id);

        if (deleteSurvey.isPresent()) {
            surveyRepository.save(deleteSurvey.get());
            surveyRepository.deleteById(id);
            return new SuccessDataResult("Anket Başarılı Şekilde Silindi");
        } else {
            return new ErrorDataResult("Anket Silinirken Bir Hata Meydana Geldi");
        }
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
                surveyDto.setDevice(survey.getDevice());
                surveyDto.setPerson(survey.getPerson());
                surveyDto.setRating(survey.getRating());
                surveyDto.setSurveyDate(survey.getSurveyDate());
                surveyDtos.add(surveyDto);
            }
            return new SuccessDataResult<>(surveyDtos);
        } else {
            return new ErrorDataResult<>("Tarih Aralığında Anket Bulunamadı");
        }
    }

    @Override
    public DataResult<List<SurveyDto>> getSurveyByRating(int rating) {

        List<SurveyEntity> surveys = surveyRepository.findByRating(rating);
        List<SurveyDto> surveyDtos = new ArrayList<>();
        SurveyDto surveyDto = new SurveyDto();

        if (!surveys.isEmpty()) {
            for (SurveyEntity survey : surveys) {
                surveyDto.setId(survey.getId());
                surveyDto.setComment(survey.getComment());
                surveyDto.setDevice(survey.getDevice());
                surveyDto.setPerson(survey.getPerson());
                surveyDto.setRating(survey.getRating());
                surveyDto.setSurveyDate(survey.getSurveyDate());
                surveyDtos.add(surveyDto);
            }
            return new SuccessDataResult<>(surveyDtos);
        } else {
            return new ErrorDataResult<>("Anket Bulunamadı");
        }

    }

}
