/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Dto;

import com.mepsan.MlbClean.Device.entity.DeviceEntity;
import com.mepsan.MlbClean.Task.entity.TaskEntity;
import com.mepsan.MlbClean.User.entity.UserEntity;
import java.util.Date;

/**
 *
 * @author nurbanu.acar
 */
public class TaskDeviceConDto {
    private int id;
    private TaskEntity task;
    private DeviceEntity device;
    private UserEntity user;
    private boolean isCheck;
    private String completeDate;
    private String beginDate;
    private String endDate;
    private int frequency;
    private String frequencyArray;

    public TaskDeviceConDto() {
    }

    public TaskDeviceConDto(int id, TaskEntity task, DeviceEntity device, UserEntity user, boolean isCheck, String completeDate, String beginDate, String endDate, int frequency, String frequencyArray) {
        this.id = id;
        this.task = task;
        this.device = device;
        this.user = user;
        this.isCheck = isCheck;
        this.completeDate = completeDate;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.frequency = frequency;
        this.frequencyArray = frequencyArray;
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

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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
    
    
}
