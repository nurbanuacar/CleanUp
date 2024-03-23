/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Survey.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.mepsan.MlbClean.Survey.business.SurveyService;
import com.mepsan.MlbClean.Survey.entity.SurveyEntity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nurbanu.acar
 */
@RestController
@RequestMapping("/api/survey/")
@CrossOrigin
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping("all")
    public List<SurveyEntity> getAllSurvey() {
        return surveyService.getAllSurvey();
    }

    @GetMapping("{id}")
    public Optional<SurveyEntity> getSurveyById(@PathVariable int id) {
        return surveyService.getSurveyById(id);
    }

//    @GetMapping("device/{id}")
//    public List<SurveyEntity> getSurveyByDeviceId(@PathVariable(name = "id") int deviceId){
//        return surveyService.getSurveyByDeviceId(deviceId);
//    }
    @GetMapping("device/{id}")
    public List<SurveyEntity> getSurveyByDeviceId(@PathVariable(name = "id") int deviceId) {
        return surveyService.getSurveyByDeviceId(deviceId);
    }

    @PostMapping("datebetween")
    public List<SurveyEntity> getSurveyByDateBetween(@RequestBody JsonNode json) {
        return surveyService.getSurveyByDateBetween(json);
    }

    @PostMapping("save")
    public SurveyEntity save(@RequestBody SurveyEntity survey) {
        return surveyService.save(survey);
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<SurveyEntity> delete(@PathVariable int id) {
        return surveyService.deleteById(id);
    }

//    @GetMapping("delete/{id}")
//    public void delete(@PathVariable(name = "id") int id) {
//        surveyService.deleteById(id);
//    }
//    
}
