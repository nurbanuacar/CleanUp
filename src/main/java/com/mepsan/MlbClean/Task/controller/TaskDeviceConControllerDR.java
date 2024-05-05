/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Task.controller;

import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Dto.BetweenDateDto;
import com.mepsan.MlbClean.Dto.TaskDeviceConDto;
import com.mepsan.MlbClean.Dto.TaskStatusResponseDto;
import com.mepsan.MlbClean.Task.business.TaskDeviceConService;
import com.mepsan.MlbClean.Task.entity.TaskDeviceConEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/api/v2/taskinfo/")
public class TaskDeviceConControllerDR {

    @Autowired
    private TaskDeviceConService taskDeviceConService;

    @GetMapping("all")
    public DataResult<List<TaskDeviceConDto>> getAllTaskDeviceCon() {
        return taskDeviceConService.getAllTaskDeviceCon();
    }

    @PostMapping("save")
    public DataResult<TaskDeviceConDto> save(@RequestBody TaskDeviceConDto task, HttpServletRequest httpServletRequest) {
        int processId = Integer.parseInt((String) httpServletRequest.getAttribute("userId"));
        return taskDeviceConService.save(task, processId);
    }

    @GetMapping("device/{id}")
    public DataResult<List<TaskDeviceConDto>> getTaskByDeviceId(@PathVariable(name = "id") int deviceId) {
        return taskDeviceConService.getTaskDeviceConByDeviceId(deviceId);
    }

    @GetMapping("todaytaskstatus")
    public DataResult<TaskStatusResponseDto> getTodayTaskStatus() {
        System.out.println("****** dunun bugune oranı sonucu **** " + taskDeviceConService.dailyRateOfChange());
        return taskDeviceConService.getTodayTaskStatus();
    }

    @GetMapping("complete/{taskId}")
    public DataResult completeTask(@PathVariable(name = "taskId") int taskId, HttpServletRequest httpServletRequest) {
        String tempId = (String) httpServletRequest.getAttribute("userId");
        int updateId = Integer.parseInt(tempId);
        return taskDeviceConService.completeTask(taskId, updateId);
    }

    @PutMapping("update/{taskInfoId}")
    public DataResult<TaskDeviceConDto> update(@RequestBody TaskDeviceConDto taskDevice,
            @PathVariable(name = "taskInfoId") int taskInfoId, HttpServletRequest httpServletRequest) {

        int processId = Integer.parseInt((String) httpServletRequest.getAttribute("userId"));
        return taskDeviceConService.updateTaskInfo(taskDevice, taskInfoId, processId);
    }

    @GetMapping("delete/{id}")
    public DataResult deleteTaskDeviceCon(@PathVariable(name = "id") int taskInfoId, HttpServletRequest httpServletRequest) {
        int updateId = Integer.parseInt((String) httpServletRequest.getAttribute("userId"));
        return taskDeviceConService.deleteTaskDeviceCon(taskInfoId, updateId);
    }

    @PostMapping("betweendate")
    public DataResult<List<TaskDeviceConDto>> getBetweenDate(@RequestBody BetweenDateDto dateJson) {
        return taskDeviceConService.getTaskDeviceConByDateBetween(dateJson);
    }

    @GetMapping("daily")
    public DataResult<List<TaskDeviceConDto>> getDailyTask() {
        return taskDeviceConService.getTaskDeviceConDaily();
    }

    @GetMapping("weekly")
    public DataResult<List<TaskDeviceConDto>> getWeeklyTask() {
        return taskDeviceConService.getTaskDeviceConWeekly();
    }

    @GetMapping("monthly")
    public DataResult<List<TaskDeviceConDto>> getMonthlyTask() {
        return taskDeviceConService.getTaskDeviceConMonthly();
    }

    @GetMapping("completedtasks")
    public DataResult<List<TaskDeviceConDto>> getCompletedTasks() {
        return taskDeviceConService.getCompletedTaskDeviceCon();
    }
}
