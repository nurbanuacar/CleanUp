/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Task.controller;

import com.mepsan.MlbClean.Dto.TaskDto;
import com.mepsan.MlbClean.Task.business.TaskService;
import com.mepsan.MlbClean.Task.entity.TaskEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/task/")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("all")
    public List<TaskDto> getAllTask() {
        return taskService.getAllTask().getData();
    }

    @GetMapping("{id}")
    public TaskDto getTask(@PathVariable(name = "id") int id) {
        return taskService.getTaskById(id).getData();
    }

    @PostMapping("save")
    public TaskDto save(@RequestBody TaskEntity task) {
        return taskService.save(task, 1).getData();
    }

    @GetMapping("delete/{id}")
    public void delete(@PathVariable(name = "id") int id) {
        taskService.deleteById(id);
    }

    @PutMapping("update/{id}")
    public TaskDto update(@RequestBody TaskDto task, @PathVariable(name = "id") int id) {
        return taskService.update(task, id, 1).getData();
    }

}
