/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Task.controller;

import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Task.business.TaskService;
import com.mepsan.MlbClean.Task.entity.TaskEntity;
import com.mepsan.MlbClean.User.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nurbanu.acar
 */
@RestController
@RequestMapping("/api/task/")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("all")
    public List<TaskEntity> getAllTask() {
        return taskService.getAllTask();
    }

    @GetMapping("allRE")
    public ResponseEntity<TaskEntity> getAllTaskRE() {
        return taskService.getAllTaskRE();
    }

    @GetMapping("{id}")
    public Optional<TaskEntity> getTask(@PathVariable(name = "id") int id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("save")
    public TaskEntity save(@RequestBody TaskEntity task) {
        return taskService.save(task);
    }

    @GetMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") int id) {
        return taskService.deleteById(id);
    }

    @PutMapping("update/{id}")
    public TaskEntity update(@RequestBody TaskEntity task, @PathVariable(name = "id") int id) {

        Optional<TaskEntity> existTask = taskService.getTaskById(id);
        if (existTask.isPresent()) {
            existTask.get().setName(task.getName());
        }
        return taskService.save(existTask.get());
    }

}
