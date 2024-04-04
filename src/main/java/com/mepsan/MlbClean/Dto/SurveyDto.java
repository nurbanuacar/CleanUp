/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Dto;

import com.mepsan.MlbClean.Device.entity.DeviceEntity;
import java.util.Date;

/**
 *
 * @author nurbanu.acar
 */
public class SurveyDto {

    private int id;
    private String comment;
    private String person;
    private int rating;
    private Date surveyDate;
    private DeviceEntity device;

    public SurveyDto() {
    }

    public SurveyDto(int id, String comment, String person, int rating, Date surveyDate, DeviceEntity device) {
        this.id = id;
        this.comment = comment;
        this.person = person;
        this.rating = rating;
        this.surveyDate = surveyDate;
        this.device = device;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(Date surveyDate) {
        this.surveyDate = surveyDate;
    }

    public DeviceEntity getDevice() {
        return device;
    }

    public void setDevice(DeviceEntity device) {
        this.device = device;
    }

}
