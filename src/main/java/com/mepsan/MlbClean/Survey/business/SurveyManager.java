/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Survey.business;

import com.fasterxml.jackson.databind.JsonNode;
import com.mepsan.MlbClean.Core.StaticMethods;
import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Core.result.ErrorDataResult;
import com.mepsan.MlbClean.Core.result.SuccessDataResult;
import com.mepsan.MlbClean.Device.entity.DeviceEntity;
import com.mepsan.MlbClean.Device.repository.DeviceRepository;
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

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public DataResult<List<SurveyDto>> getAllSurvey() {
        List<SurveyEntity> surveys = surveyRepository.findAll();

        if (!surveys.isEmpty()) {
            List<SurveyDto> surveyDtos = new ArrayList<>();
            for (SurveyEntity survey : surveys) {
                try {
                    SurveyDto surveyDto = new SurveyDto(
                            survey.getId(),
                            survey.getComment(),
                            survey.getPerson(),
                            survey.getRating(),
                            StaticMethods.dateToString(survey.getSurveyDate()),
                            survey.getDevice());
                    surveyDtos.add(surveyDto);
                } catch (ParseException ex) {
                    Logger.getLogger(SurveyManager.class.getName()).log(Level.SEVERE, null, ex);
                    return new ErrorDataResult<>("Tarih Dönüşümü Yapılamadı.");
                }
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
            try {
                SurveyEntity existSurvey = survey.get();
                SurveyDto surveyDto = new SurveyDto(
                        existSurvey.getId(),
                        existSurvey.getComment(),
                        existSurvey.getPerson(),
                        existSurvey.getRating(),
                        StaticMethods.dateToString(existSurvey.getSurveyDate()),
                        existSurvey.getDevice());
                return new SuccessDataResult<>(surveyDto);
            } catch (ParseException ex) {
                Logger.getLogger(SurveyManager.class.getName()).log(Level.SEVERE, null, ex);
                return new ErrorDataResult<>("Tarih Dönüşümü Yapılamadı.");
            }
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
                try {
                    surveyDto.setId(survey.getId());
                    surveyDto.setComment(survey.getComment());
                    surveyDto.setDevice(survey.getDevice());
                    surveyDto.setPerson(survey.getPerson());
                    surveyDto.setRating(survey.getRating());
                    surveyDto.setSurveyDate(StaticMethods.dateToString(survey.getSurveyDate()));
                    surveyDtos.add(surveyDto);
                } catch (ParseException ex) {
                    Logger.getLogger(SurveyManager.class.getName()).log(Level.SEVERE, null, ex);
                    return new ErrorDataResult<>("Tarih Dönüşümü Yapılamadı.");
                }
            }
            return new SuccessDataResult<>(surveyDtos);
        } else {
            return new ErrorDataResult<>("Cihaza Ait Hiçbir Anket Bulunamadı.");
        }
    }

    @Override
    public DataResult<SurveyDto> save(SurveyDto surveyDto) {

        int deviceId = surveyDto.getDevice().getId();
        try {
            Optional<DeviceEntity> device = deviceRepository.findById(deviceId);
            if (device.isPresent()) {
                Date surveyDate = StaticMethods.convertStringToDate(surveyDto.getSurveyDate());
                SurveyEntity survey = new SurveyEntity(
                        surveyDto.getComment(),
                        surveyDto.getPerson(),
                        surveyDto.getRating(),
                        surveyDate,
                        surveyDto.getDevice());

                SurveyEntity newSurvey = surveyRepository.save(survey);

                if (newSurvey.getId() > 0) {
                    SurveyDto newSurveyDto = new SurveyDto(
                            newSurvey.getId(),
                            newSurvey.getComment(),
                            newSurvey.getPerson(),
                            newSurvey.getRating(),
                            newSurvey.getSurveyDate().toString(),
                            newSurvey.getDevice());
                    return new SuccessDataResult<>("Anket Başarıyla Kaydedildi.", newSurveyDto);
                } else {
                    return new ErrorDataResult<>("Anket Kaydedilirken Bir Hata Oluştu.");
                }
            } else {
                return new ErrorDataResult<>("Cihaz Bulunamadı.");
            }
        } catch (ParseException ex) {
            Logger.getLogger(SurveyManager.class.getName()).log(Level.SEVERE, null, ex);
            return new ErrorDataResult<>("Tarih Dönüşümü Yapılamadı.");
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
                surveyDto.setSurveyDate(survey.getSurveyDate().toString());
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
                surveyDto.setSurveyDate(survey.getSurveyDate().toString());
                surveyDtos.add(surveyDto);
            }
            return new SuccessDataResult<>(surveyDtos);
        } else {
            return new ErrorDataResult<>("Anket Bulunamadı");
        }

    }

}
