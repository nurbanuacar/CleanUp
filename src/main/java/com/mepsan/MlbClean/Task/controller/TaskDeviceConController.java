/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Task.controller;

import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Task.business.TaskDeviceConService;
import com.mepsan.MlbClean.Task.entity.TaskDeviceConEntity;
import com.mepsan.MlbClean.User.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nurbanu.acar
 */
@RestController
@RequestMapping("/api/taskinfo/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskDeviceConController {

    @Autowired
    private TaskDeviceConService taskDeviceConService;

    @GetMapping("all")
    public List<TaskDeviceConEntity> getAllTaskDeviceCon() {
        return taskDeviceConService.getAllTaskDeviceCon();
    }

    @GetMapping("device/{id}")
    public List<TaskDeviceConEntity> getTaskByDeviceId(@PathVariable(name = "id") int deviceId) {
        return taskDeviceConService.getTaskDeviceConByDeviceId(deviceId);
    }

    @PostMapping("save")
    public ResponseEntity<TaskDeviceConEntity> save(@RequestBody TaskDeviceConEntity task) {
        return taskDeviceConService.save(task);
    }

    @GetMapping("todayTasks")
    public DataResult<Integer> getTodayTasks() {
        return taskDeviceConService.getTodayTasks();
    }

    @GetMapping("todayCompletedTasks")
    public DataResult<Integer> getTodayCompletedTasks() {
        return taskDeviceConService.getTodayCompletedTasks();
    }

//    @PutMapping("update/{taskId}/{deviceId}")
//    public ResponseEntity<TaskDeviceConEntity> update(@RequestBody TaskDeviceConEntity taskDevice,  @PathVariable(name = "taskId") int taskId,
//                                                   @PathVariable(name = "deviceId") int deviceId) {
//        Optional<TaskDeviceConEntity> existTaskDeviceCon = taskDeviceConService.getTaskDeviceConByTaskIdAndDeviceId(taskId, deviceId);
//    if (existTaskDeviceCon.isPresent()){
//        TaskDeviceConEntity existingTaskDevice = existTaskDeviceCon.get();
//        existingTaskDevice.setTask(taskDevice.getTask());
//        existingTaskDevice.setDevice(taskDevice.getDevice());
//        
//    }
//    return taskDeviceConService.save(existTaskDeviceCon.get());
//    }
    @PutMapping("update/{taskId}/{deviceId}")
    public ResponseEntity<TaskDeviceConEntity> update(@RequestBody TaskDeviceConEntity taskDevice,
            @PathVariable(name = "taskId") int taskId,
            @PathVariable(name = "deviceId") int deviceId) {

//        Integer frequencyValue = (Integer) task.getFrequency().orElse(0); // Eğer null ise 0 değerini kullan
//            existTask.get().setFrequency(frequencyValue);
        List<TaskDeviceConEntity> existTaskDeviceConOptionals = taskDeviceConService.getTaskDeviceConByTaskIdAndDeviceId(taskId, deviceId);

        List<TaskDeviceConEntity> existTaskDeviceConList = new ArrayList<>();
        for (TaskDeviceConEntity taskDeviceConEntity : existTaskDeviceConOptionals) {
            existTaskDeviceConList.add(taskDeviceConEntity);
        }

        if (!existTaskDeviceConList.isEmpty()) {
            TaskDeviceConEntity existingTaskDevice = existTaskDeviceConList.get(0);
            existingTaskDevice.setTask(taskDevice.getTask());
            existingTaskDevice.setDevice(taskDevice.getDevice());

            return taskDeviceConService.save(existingTaskDevice);
        }

        return ResponseEntity.notFound().build();
    }

}
