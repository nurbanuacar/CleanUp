/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Task.controller;

import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Dto.TaskDto;
import com.mepsan.MlbClean.Task.business.TaskService;
import com.mepsan.MlbClean.Task.entity.TaskEntity;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v2/task/")
public class TaskControllerDR {

    @Autowired
    private TaskService taskService;

    @GetMapping("all")
    public DataResult<List<TaskDto>> getAllTask() {
        return taskService.getAllTask();
    }

    @GetMapping("{id}")
    public DataResult<TaskDto> getTask(@PathVariable(name = "id") int id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("save")
    public DataResult<TaskDto> save(@RequestBody TaskEntity task, HttpServletRequest httpServletRequest) {
        int processId = Integer.parseInt((String) httpServletRequest.getAttribute("userId"));
        return taskService.save(task, processId);
    }

    @GetMapping("delete/{id}")
    public DataResult deleteTask(@PathVariable(name = "id") int id, HttpServletRequest httpServletRequest) {
        int processId = Integer.parseInt((String) httpServletRequest.getAttribute("userId"));
        return taskService.deleteTask(id, processId);
    }

    @PutMapping("update/{id}")
    public DataResult<TaskDto> update(@RequestBody TaskDto task, @PathVariable(name = "id") int id, HttpServletRequest httpServletRequest) {
        int processId = Integer.parseInt((String) httpServletRequest.getAttribute("userId"));
        return taskService.update(task, id, processId);
    }
}
