/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mepsan.MlbClean.Task.business;

import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Dto.TaskDto;
import com.mepsan.MlbClean.Task.entity.TaskEntity;
import java.util.List;

/**
 *
 * @author nurbanu.acar
 */
public interface TaskService {

    public DataResult<List<TaskDto>> getAllTask();

    public DataResult<TaskDto> getTaskById(int id);

    public DataResult<TaskDto> save(TaskEntity task, int processId);

    public DataResult<TaskDto> update(TaskDto task, int id, int updateId);

    public void deleteById(int id);
    
    public DataResult deleteTask(int id, int processId);
}
