/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Task.entity;

import com.mepsan.MlbClean.Device.entity.DeviceEntity;
import com.mepsan.MlbClean.User.entity.UserEntity;
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
import javax.persistence.Temporal;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 *
 * @author nurbanu.acar
 */
@Entity
@Table(name = "task_device_con", schema = "general")
@SQLDelete(sql = "UPDATE general.task_device_con SET deleted = true, d_time=now() WHERE id=?")
@Where(clause = "deleted=false")
public class TaskDeviceConEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private TaskEntity task;
    @ManyToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private DeviceEntity device;
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private UserEntity user;
    @Column(name = "is_check", columnDefinition = "boolean default false")
    private boolean isCheck;
    @Column(name = "completedate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date completeDate;
    @Column(name = "begindate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date beginDate;
    @Column(name = "enddate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(columnDefinition = "integer default 1")
//    @Description("Gunluk -> 1, Haftalik -> 2, Aylik -> 3")
    private int frequency;
    @Column(name = "frequency_array", columnDefinition = "varchar(15)")
    private String frequencyArray;
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
    private Date deleteTime;

    public TaskDeviceConEntity() {
    }

    public TaskDeviceConEntity(TaskEntity task, UserEntity user, DeviceEntity device, boolean isCheck, int frequency, String frequencyArray, Date completeDate, Date beginDate, Date endDate, int createId, Date createTime, int updateId, Date updateTime, boolean deleted, Date deleteTime) {
        this.task = task;
        this.device = device;
        this.user = user;
        this.isCheck = isCheck;
        this.completeDate = completeDate;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.frequency = frequency;
        this.frequencyArray = frequencyArray;
        this.createId = createId;
        this.createTime = new Date();
        this.updateId = updateId;
        this.updateTime = new Date();
        this.deleted = deleted;
        this.deleteTime = new Date();
    }

    public TaskDeviceConEntity(TaskEntity task, UserEntity user, DeviceEntity device, boolean isCheck, int frequency, String frequencyArray, Date completeDate, Date beginDate, Date endDate, int createId) {
        this.task = task;
        this.device = device;
        this.user = user;
        this.isCheck = isCheck;
        this.completeDate = completeDate;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.frequency = frequency;
        this.frequencyArray = frequencyArray;
        this.createId = createId;
    }

    @PrePersist
    protected void onCreate() {
        this.createTime = new Date();
        this.updateTime = new Date();
//        this.beginDate = new Date();
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

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public DeviceEntity getDevice() {
        return device;
    }

    public void setDevice(DeviceEntity device) {
        this.device = device;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public boolean isIsCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getFrequencyArray() {
        return frequencyArray;
    }

    public void setFrequencyArray(String frequencyArray) {
        this.frequencyArray = frequencyArray;
    }

    public Optional getCreateId() {
        return Optional.ofNullable(createId);
    }

    public void setCreateId(Integer createId) {
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

    public void setUpdateId(Integer updateId) {
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

}
