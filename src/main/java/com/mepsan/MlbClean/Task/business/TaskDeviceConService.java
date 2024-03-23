/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mepsan.MlbClean.Task.business;

import com.mepsan.MlbClean.Task.entity.TaskDeviceConEntity;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author nurbanu.acar
 */
public interface TaskDeviceConService {
    public List<TaskDeviceConEntity> getAllTaskDeviceCon();
    public ResponseEntity<TaskDeviceConEntity> save(TaskDeviceConEntity taskDeviceConEntity);
    public List<TaskDeviceConEntity> update(TaskDeviceConEntity taskDeviceConEntity, int id);
    public List<TaskDeviceConEntity> getTaskDeviceConByDeviceId(int deviceId);
    /**
    *@author emirhan ☺☺☺☺ Hamza 
    */ 
    public List<TaskDeviceConEntity> getTaskDeviceConByTaskIdAndDeviceId(int taskId, int deviceId);
    
    public List<TaskDeviceConEntity> getTaskDeviceConByDateBetween(Date startDate, Date endDate);
    public List<TaskDeviceConEntity> getTaskDeviceConDaily();
    public List<TaskDeviceConEntity> getTaskDeviceConWeekly();
    public List<TaskDeviceConEntity> getTaskDeviceConMonthly();
}
