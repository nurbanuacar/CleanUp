/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Task.business;

import com.mepsan.MlbClean.Task.entity.TaskEntity;
import com.mepsan.MlbClean.Task.repository.TaskRepository;
import com.mepsan.MlbClean.User.entity.UserEntity;
import java.util.ArrayList;
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
public class TaskManager implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskEntity> getAllTask() {
        return taskRepository.findAll();
    }
    
    @Override
    public ResponseEntity<TaskEntity> getAllTaskRE() {
        List<TaskEntity> taskList = new ArrayList<>();
        ResponseEntity response = null;
        taskList = taskRepository.findAll();
        if(!taskList.isEmpty()){
            response = ResponseEntity.ok(taskList);
        }else{
            response = ResponseEntity.badRequest().body("Tasks Not Found");
        }
        return response;
    }

    @Override
    public Optional<TaskEntity> getTaskById(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public TaskEntity save(TaskEntity task) {
        return taskRepository.save(task);
    }

    @Override
    public ResponseEntity deleteById(int id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);

            return ResponseEntity.ok("Task Silme Başarılı");
        } else {
            return ResponseEntity.badRequest().body("Task Bulunamadı");
        }
    }
    
    @Override
    public TaskEntity update(TaskEntity task, int id) {
        return taskRepository.save(task);
    }

}
