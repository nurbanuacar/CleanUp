/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Task.business;

import com.mepsan.MlbClean.Core.StaticMethods;
import com.mepsan.MlbClean.Device.entity.DeviceEntity;
import com.mepsan.MlbClean.Device.repository.DeviceRepository;
import com.mepsan.MlbClean.Task.entity.TaskDeviceConEntity;
import com.mepsan.MlbClean.Task.entity.TaskEntity;
import com.mepsan.MlbClean.Task.repository.TaskDeviceConRepository;
import com.mepsan.MlbClean.Task.repository.TaskRepository;
import com.mepsan.MlbClean.User.entity.UserEntity;
import com.mepsan.MlbClean.User.repository.UserRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author nurbanu.acar
 */
@Service
public class TaskDeviceConManager implements TaskDeviceConService {

    @Autowired
    private TaskDeviceConRepository taskDeviceConRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    private Date date;

    @Override
    public List<TaskDeviceConEntity> getAllTaskDeviceCon() {
        return taskDeviceConRepository.findAll();
    }

    private void init() {
        date = new Date();
        System.out.println("*************** task device con manager init ******* " + date);
    }

    @Override
    public ResponseEntity<TaskDeviceConEntity> save(TaskDeviceConEntity taskDeviceConEntity) {
        ResponseEntity<TaskDeviceConEntity> response = null;
        TaskDeviceConEntity tempTaskDeviceConEntity = new TaskDeviceConEntity();
        int taskId = taskDeviceConEntity.getTask().getId();

        int deviceId = taskDeviceConEntity.getDevice().getId();



        Optional<TaskEntity> task = taskRepository.findById(taskId);

        Optional<DeviceEntity> device = deviceRepository.findById(deviceId);


        if (task.isPresent() && device.isPresent()) {
            tempTaskDeviceConEntity = taskDeviceConRepository.save(taskDeviceConEntity);
            if (tempTaskDeviceConEntity.getId() > 0) {
                response = ResponseEntity.ok(tempTaskDeviceConEntity);
            } else {
                response = ResponseEntity.badRequest().body(tempTaskDeviceConEntity);
            }
        }
        return response;
    }
    
    
    @Override
    public List<TaskDeviceConEntity> update(TaskDeviceConEntity taskDeviceConEntity, int id) {
        List<TaskDeviceConEntity> response = null;
        
        return response;
    }
    
    

    @Override
    public List<TaskDeviceConEntity> getTaskDeviceConByDeviceId(int deviceId) {
        return taskDeviceConRepository.findByDeviceId(deviceId);
        
    }
    
    
    @Override
    public List<TaskDeviceConEntity> getTaskDeviceConByTaskIdAndDeviceId(int taskId, int deviceId) {
    return taskDeviceConRepository.findByTaskIdAndDeviceId(taskId, deviceId);
}

    
//    @Override
//    public List<TaskDeviceConEntity> getTaskDeviceConByDateBetween(Date startDate, Date endDate) {
//        return taskDeviceConRepository.findAllByTaskDeviceConCompleteDateBetween(startDate, endDate);
//    }
//
//    @Override
//    public List<TaskDeviceConEntity> getTaskDeviceConDaily() {
//        Date yesterday = StaticMethods.getYesterdayDate();
//        return taskDeviceConRepository.findAllByTaskDeviceConCompleteDateBetween(date, yesterday);
//    }
//
//    @Override
//    public List<TaskDeviceConEntity> getTaskDeviceConWeekly() {
//       Date lastWeek = StaticMethods.getLastWeekDate();
//        return taskDeviceConRepository.findAllByTaskDeviceConCompleteDateBetween(date, lastWeek);
//    }
//
//    @Override
//    public List<TaskDeviceConEntity> getTaskDeviceConMonthly() {
//         Date lastMonth = StaticMethods.getLastMonthDate();
//        return taskDeviceConRepository.findAllByTaskDeviceConCompleteDateBetween(date, yesterday);
//    }

    @Override
    public List<TaskDeviceConEntity> getTaskDeviceConByDateBetween(Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TaskDeviceConEntity> getTaskDeviceConDaily() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TaskDeviceConEntity> getTaskDeviceConWeekly() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TaskDeviceConEntity> getTaskDeviceConMonthly() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
