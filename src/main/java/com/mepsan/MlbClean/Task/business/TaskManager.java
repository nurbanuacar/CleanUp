/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Task.business;

import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Core.result.ErrorDataResult;
import com.mepsan.MlbClean.Core.result.SuccessDataResult;
import com.mepsan.MlbClean.Dto.TaskDto;
import com.mepsan.MlbClean.Task.entity.TaskEntity;
import com.mepsan.MlbClean.Task.repository.TaskRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
    public DataResult<List<TaskDto>> getAllTask() {
        List<TaskEntity> tasks = taskRepository.findAll();
        if (!tasks.isEmpty()) {
            List<TaskDto> taskDtos = new ArrayList<>();
            for (TaskEntity task : tasks) {
                TaskDto taskDto = new TaskDto(task.getId(), task.getName());
                taskDtos.add(taskDto);
            }
            return new SuccessDataResult<>("Görev Tanımları Başarılı Şekilde ", taskDtos);
        } else {
            return new ErrorDataResult<>("Hiçbir Görev Tanımı Bulunamadı");
        }
    }

    @Override
    public DataResult<TaskDto> getTaskById(int id) {
        Optional<TaskEntity> task = taskRepository.findById(id);

        if (task.isPresent()) {
            TaskDto taskDto = new TaskDto();
            taskDto.setName(task.get().getName());
            return new SuccessDataResult<>("Görev Tanımı Bulundu.", taskDto);
        } else {
            return new ErrorDataResult<>("Görev Tanımı Bulunamadı");
        }
    }

    @Override
    public DataResult<TaskDto> save(TaskEntity task, int processId) {
        task.setCreateId(processId);
        task.setUpdateId(processId);
        task.setUpdateTime(new Date());
        TaskEntity newTask = taskRepository.save(task);
        if (newTask.getId() > 0) {
            TaskDto taskDto = new TaskDto(newTask.getName());
            return new SuccessDataResult<>("Görev Tanımı Oluşturuldu.", taskDto);
        } else {
            return new ErrorDataResult<>("Görev Tanımı Oluşturulamadı.");
        }
    }

    @Override
    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }

    @Override
    public DataResult deleteTask(int id, int processId) {
        Optional<TaskEntity> task = taskRepository.findById(id);
        if (task.isPresent()) {
            task.get().setUpdateId(processId);
            task.get().setUpdateTime(new Date());
            taskRepository.save(task.get());
            taskRepository.deleteById(id);
            return new SuccessDataResult<>("Görev Başarılı Şekilde Silindi.");
        } else {
            return new ErrorDataResult<>("Görev Silinirken Bir Hata Oluştu.");
        }
    }

    @Override
    public DataResult<TaskDto> update(TaskDto task, int id, int updateId) {
        Optional<TaskEntity> existTask = taskRepository.findById(id);
        if (existTask.isPresent()) {
            existTask.get().setUpdateId(updateId);
            existTask.get().setUpdateTime(new Date());
            existTask.get().setName(task.getName());
            TaskEntity updatedTask = taskRepository.save(existTask.get());

            if (updatedTask != null) {
                TaskDto taskDto = new TaskDto(updatedTask.getName());
                return new SuccessDataResult<>("Görev Güncelleme Başarılı.", taskDto);
            } else {
                return new ErrorDataResult("Görev Güncellenemedi.");
            }
        } else {
            return new ErrorDataResult("Görev Bulunamadı.");
        }
    }

}
