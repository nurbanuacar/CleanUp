/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Device.entity;

import com.mepsan.MlbClean.Survey.entity.SurveyEntity;
import com.mepsan.MlbClean.Task.entity.TaskDeviceConEntity;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 *
 * @author nurbanu.acar
 */
@Entity
@Table(name = "device", schema = "general")
@SQLDelete(sql = "UPDATE general.device SET deleted = true, d_time=now() WHERE id=?")
//@Where(clause = "deleted=false")
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "varchar(50)")
    private String name;
    @Column(columnDefinition = "varchar(20)")
    private String ip;
    @Column(columnDefinition = "varchar(20)")
    private String floor;
    @Column(name = "c_id")
    private Integer createId;
    @Column(name = "c_time", columnDefinition = "timestamp default now()")
    private Date createTime;
    @Column(name = "u_id")
    private Integer updateId;
    @Column(name = "u_time", columnDefinition = "timestamp default now()")
    private Date updateTime;
    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted = Boolean.FALSE;
    @Column(name = "d_time")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date deleteTime;
    @Column(name = "device_name",columnDefinition = "varchar(100)")
    private String deviceName;
    @OneToMany(mappedBy = "device")
    private List<TaskDeviceConEntity> taskDeviceCons;
    @OneToMany(mappedBy = "device")
    private List<SurveyEntity> surveys;

    public DeviceEntity() {
    }

    public DeviceEntity(String ip, String name, String floor, int createId, Date createTime, int updateId, Date updateTime, boolean deleted, Date deleteTime, String deviceName) {
        this.ip = ip;
        this.name = name;
        this.floor = floor;
        this.createId = createId;
        this.createTime = createTime;
        this.updateId = updateId;
        this.updateTime = updateTime;
        this.deleted = deleted;
        this.deleteTime = deleteTime;
        this.deviceName = deviceName;
    }

    @PrePersist
    protected void onCreate() {
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateTime = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Optional getCreateId() {
        return Optional.ofNullable(createId);
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Optional getUpdateId() {
        return Optional.ofNullable(updateId);
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

}
