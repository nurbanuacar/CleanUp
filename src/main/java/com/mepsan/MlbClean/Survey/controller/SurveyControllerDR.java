/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Survey.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Dto.SurveyDto;
import com.mepsan.MlbClean.Survey.business.SurveyService;
import com.mepsan.MlbClean.Survey.entity.SurveyEntity;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v2/survey/")
@CrossOrigin
public class SurveyControllerDR {

    @Autowired
    private SurveyService surveyService;

    @GetMapping("all")
    public DataResult<List<SurveyDto>> getAllSurvey() {
        return surveyService.getAllSurvey();
    }

    @GetMapping("{id}")
    public DataResult<SurveyDto> getSurveyById(@PathVariable int id) {
        return surveyService.getSurveyById(id);
    }

    @GetMapping("device/{id}")
    public DataResult<List<SurveyDto>> getSurveyByDeviceId(@PathVariable(name = "id") int deviceId) {
        return surveyService.getSurveyByDeviceId(deviceId);
    }

    @PostMapping("save")
    public DataResult<SurveyDto> save(@RequestBody SurveyEntity survey, HttpServletRequest request) {
        String id = (String) request.getAttribute("userId");
        int userId = Integer.parseInt(id);
        return surveyService.save(survey);
    }

    @PostMapping("datebetween")
    public DataResult<List<SurveyDto>> getSurveyByDateBetween(@RequestBody JsonNode json) {
        return surveyService.getSurveyByDateBetween(json);
    }
}
