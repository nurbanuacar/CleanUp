/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mepsan.MlbClean.Task.business;

import com.mepsan.MlbClean.Task.entity.TaskEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author nurbanu.acar
 */
public interface TaskService {

    public List<TaskEntity> getAllTask();

    public ResponseEntity<TaskEntity> getAllTaskRE();

    public Optional<TaskEntity> getTaskById(int id);

    public TaskEntity save(TaskEntity task);

    public TaskEntity update(TaskEntity task, int id);

    public ResponseEntity deleteById(int id);
}
