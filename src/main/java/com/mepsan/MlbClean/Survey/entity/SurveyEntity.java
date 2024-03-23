/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Survey.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mepsan.MlbClean.Device.entity.DeviceEntity;
import java.util.Date;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 *
 * @author nurbanu.acar
 */
@Entity
@Table(name = "survey", schema = "general")
@SQLDelete(sql = "UPDATE general.survey SET deleted = true, d_time=now() WHERE id=?")
@Where(clause = "deleted=false")
public class SurveyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "varchar(400)")
    private String comment;
    @Column(columnDefinition = "varchar(20)")
    private String person;
    @Column
    private int rating;
    @Column(name = "surveydate")
    private Date surveyDate;
    @ManyToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private DeviceEntity deviceId;
    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted = Boolean.FALSE;
    @Column(name = "d_time")
    private Date deleteTime;

    public SurveyEntity() {
    }

    public SurveyEntity(String comment, String person, int rating, Date surveyDate, DeviceEntity deviceId, Date deleteTime) {
        this.comment = comment;
        this.person = person;
        this.rating = rating;
        this.surveyDate = surveyDate;
        this.deviceId = deviceId;
        this.deleteTime = deleteTime;
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

    public DeviceEntity getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(DeviceEntity deviceId) {
        this.deviceId = deviceId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

}
