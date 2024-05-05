/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mepsan.MlbClean.Task.business;

import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Dto.BetweenDateDto;
import com.mepsan.MlbClean.Dto.TaskDeviceConDto;
import com.mepsan.MlbClean.Dto.TaskStatusResponseDto;
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

    public DataResult<List<TaskDeviceConDto>> getAllTaskDeviceCon();

    public DataResult<TaskDeviceConDto> save(TaskDeviceConDto taskDeviceConDto, int processId); 

    public List<TaskDeviceConEntity> update(TaskDeviceConEntity taskDeviceConEntity, int id);

    public DataResult<TaskDeviceConDto> updateTaskInfo(TaskDeviceConDto taskDeviceConDto, int id, int updateId);

    public DataResult<List<TaskDeviceConDto>> getTaskDeviceConByDeviceId(int deviceId);

    public DataResult<TaskDeviceConEntity> getTaskDeviceConById(int id);

    /**
     * @author emirhan ☺☺☺☺ Hamza
     */
    public List<TaskDeviceConEntity> getTaskDeviceConByTaskIdAndDeviceId(int taskId, int deviceId);

    public DataResult<TaskStatusResponseDto> getTodayTaskStatus();

    public DataResult<Boolean> completeTask(int taskId, int updateId);
    
    public double dailyRateOfChange();

    public DataResult deleteTaskDeviceCon(int taskInfoId, int processId);
            
    public DataResult<List<TaskDeviceConDto>> getTaskDeviceConByDateBetween(BetweenDateDto dateJson);

    public DataResult<List<TaskDeviceConDto>> getTaskDeviceConDaily();

    public DataResult<List<TaskDeviceConDto>> getTaskDeviceConWeekly();

    public DataResult<List<TaskDeviceConDto>> getTaskDeviceConMonthly();
    
    public DataResult<List<TaskDeviceConDto>> getCompletedTaskDeviceCon();
}
