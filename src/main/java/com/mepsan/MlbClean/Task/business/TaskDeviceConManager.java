/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Task.business;

import com.mepsan.MlbClean.Core.StaticMethods;
import com.mepsan.MlbClean.Core.result.DataResult;
import com.mepsan.MlbClean.Core.result.ErrorDataResult;
import com.mepsan.MlbClean.Core.result.SuccessDataResult;
import com.mepsan.MlbClean.Device.entity.DeviceEntity;
import com.mepsan.MlbClean.Device.repository.DeviceRepository;
import com.mepsan.MlbClean.Dto.BetweenDateDto;
import com.mepsan.MlbClean.Dto.TaskDeviceConDto;
import com.mepsan.MlbClean.Dto.TaskStatusResponseDto;
import com.mepsan.MlbClean.Task.entity.TaskDeviceConEntity;
import com.mepsan.MlbClean.Task.entity.TaskEntity;
import com.mepsan.MlbClean.Task.repository.TaskDeviceConRepository;
import com.mepsan.MlbClean.Task.repository.TaskRepository;
import com.mepsan.MlbClean.User.entity.UserEntity;
import io.swagger.v3.core.util.Json;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author nurbanu.acar
 */
@Service
public class TaskDeviceConManager implements TaskDeviceConService {

    @Autowired
    private TaskDeviceConRepository taskDeviceConRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public DataResult<List<TaskDeviceConDto>> getAllTaskDeviceCon() {
        List<TaskDeviceConEntity> tasks = taskDeviceConRepository.findAll(Sort.by(Sort.Direction.DESC, "updateTime"));
        if (!tasks.isEmpty()) {
            List<TaskDeviceConDto> taskDtos = new ArrayList<>();
            for (TaskDeviceConEntity task : tasks) {
                TaskDeviceConDto taskDto;
                try {
                    if (task.isIsCheck() == true) {
                        taskDto = new TaskDeviceConDto(task.getId(), task.getTask(), task.getDevice(), task.getUser(), task.isIsCheck(), StaticMethods.dateToString(task.getCompleteDate()), StaticMethods.dateToString(task.getBeginDate()), StaticMethods.dateToString(task.getEndDate()), task.getFrequency(), task.getFrequencyArray());
                    } else {
                        taskDto = new TaskDeviceConDto(task.getId(), task.getTask(), task.getDevice(), task.getUser(), task.isIsCheck(), null, StaticMethods.dateToString(task.getBeginDate()), StaticMethods.dateToString(task.getEndDate()), task.getFrequency(), task.getFrequencyArray());
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(TaskDeviceConManager.class.getName()).log(Level.SEVERE, null, ex);
                    return new ErrorDataResult<>("Tarih Dönüşütürülürken Hata Oluştu.");
                }
                taskDtos.add(taskDto);
            }
            return new SuccessDataResult<>("Görevler Başarılı Şekilde Bulundu.", taskDtos);
        } else {
            return new ErrorDataResult<>("Atanmış Herhangi Bir Görev Bulunamadı.");
        }
    }

    @Override
    public DataResult<TaskDeviceConDto> save(TaskDeviceConDto taskDeviceConDto, int processId) {
        ResponseEntity<TaskDeviceConEntity> response = null;
        TaskDeviceConEntity tempTaskDeviceConEntity = new TaskDeviceConEntity();

        int taskId = taskDeviceConDto.getTask().getId();
        int deviceId = taskDeviceConDto.getDevice().getId();

        Optional<TaskEntity> task = taskRepository.findById(taskId);
        Optional<DeviceEntity> device = deviceRepository.findById(deviceId);

        if (task.isPresent() && device.isPresent()) {
            try {
//                Date completeDate = StaticMethods.convertStringToDate(taskDeviceConDto.getCompleteDate());
                Date beginDate = StaticMethods.convertStringToDate(taskDeviceConDto.getBeginDate());
                Date endDate = StaticMethods.convertStringToDate(taskDeviceConDto.getEndDate());
                TaskDeviceConEntity newTask = new TaskDeviceConEntity(taskDeviceConDto.getTask(),
                        null,
                        taskDeviceConDto.getDevice(),
                        taskDeviceConDto.isIsCheck(),
                        taskDeviceConDto.getFrequency(),
                        taskDeviceConDto.getFrequencyArray(),
                        null,
                        beginDate,
                        endDate,
                        processId);
                //            TaskDeviceConEntity newTask = new TaskDeviceConEntity(taskDeviceConDto.getTask(), null, taskDeviceConDto.getDevice(), taskDeviceConDto.isIsCheck(), StaticMethods.convertStringToDate(taskDeviceConDto.getCompleteDate()), StaticMethods.convertStringToDate(taskDeviceConDto.getBeginDate()), StaticMethods.convertStringToDate(taskDeviceConDto.getEndDate()), taskDeviceConDto.getFrequency(), taskDeviceConDto.getFrequencyArray(), processId);

                tempTaskDeviceConEntity = taskDeviceConRepository.save(newTask);
                if (tempTaskDeviceConEntity.getId() > 0) {
                    TaskDeviceConDto newTaskDeviceConDto = new TaskDeviceConDto(tempTaskDeviceConEntity.getId(),
                            tempTaskDeviceConEntity.getTask(),
                            tempTaskDeviceConEntity.getDevice(),
                            null,
                            tempTaskDeviceConEntity.isIsCheck(),
                            null,
                            tempTaskDeviceConEntity.getBeginDate().toString(),
                            tempTaskDeviceConEntity.getEndDate().toString(),
                            tempTaskDeviceConEntity.getFrequency(),
                            tempTaskDeviceConEntity.getFrequencyArray());
                    return new SuccessDataResult("Görev Atama Başarılı.", newTaskDeviceConDto);
                } else {
                    return new ErrorDataResult("Görev Atama Gerçekleştirilirken Hata Oluştu.");
                }
            } catch (ParseException ex) {
                return new ErrorDataResult<>("Tarih Dönüşümü Yapılamadı.");
            }
        } else {
            return new ErrorDataResult<>("Ilgili Görev veya Cihaz Bulunamadı.");
        }
    }

    @Override
    public DataResult<TaskDeviceConDto> updateTaskInfo(TaskDeviceConDto taskDeviceConDto, int id, int updateId) {
        Optional<TaskDeviceConEntity> existTask = taskDeviceConRepository.findById(id);
        if (existTask.isPresent()) {
            TaskDeviceConEntity taskToUpdate = existTask.get();
            System.out.println("**************** id " + taskToUpdate.getId());
            if (taskToUpdate != null) {
                for (Field field : taskDeviceConDto.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(taskDeviceConDto);
                        Field entityField;
                        if (value != null) {
                            entityField = TaskDeviceConEntity.class.getDeclaredField(field.getName());
                            entityField.setAccessible(true);
                            if (field.getType().equals(Optional.class)) {
                                Optional optionalValue = (Optional) value;
                                if (optionalValue.isPresent()) {
                                    entityField.set(taskToUpdate, optionalValue.get());
                                }
                            } else {
                                entityField.set(taskToUpdate, value);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                taskToUpdate.setUpdateId(updateId);
                taskToUpdate.setUpdateTime(new Date());
                taskToUpdate.setId(id);
                TaskDeviceConEntity updatedTask = taskDeviceConRepository.save(taskToUpdate);
                if (updatedTask != null) {
                    TaskDeviceConDto taskDto = null;
                    try {
                        if (updatedTask.isIsCheck()) {
                            taskDto = new TaskDeviceConDto(updatedTask.getId(), updatedTask.getTask(), updatedTask.getDevice(), updatedTask.getUser(), updatedTask.isIsCheck(), StaticMethods.dateToString(updatedTask.getCompleteDate()), StaticMethods.dateToString(updatedTask.getBeginDate()), StaticMethods.dateToString(updatedTask.getEndDate()), updatedTask.getFrequency(), updatedTask.getFrequencyArray());
                        } else {
                            taskDto = new TaskDeviceConDto(updatedTask.getId(), updatedTask.getTask(), updatedTask.getDevice(), updatedTask.getUser(), updatedTask.isIsCheck(), null, StaticMethods.dateToString(updatedTask.getBeginDate()), StaticMethods.dateToString(updatedTask.getEndDate()), updatedTask.getFrequency(), updatedTask.getFrequencyArray());
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(TaskDeviceConManager.class.getName()).log(Level.SEVERE, null, ex);
                        return new ErrorDataResult<>("Tarih Dönüşütürülürken Hata Oluştu.");
                    }
                    return new SuccessDataResult<>("Görev Atama Güncellemesi Başarılı.", taskDto);
                } else {
                    return new ErrorDataResult<>("Görev Güncellenemedi.");
                }
            } else {
                return new ErrorDataResult<>("Ilgili Görev veya Cihaz Bulunamadı.");
            }
        } else {
            return new ErrorDataResult<>("Görev Ataması Bulunamadı.");
        }
    }

    @Override
    public DataResult<TaskStatusResponseDto> getTodayTaskStatus() {
        TaskStatusResponseDto taskResponse = new TaskStatusResponseDto();
        List<TaskDeviceConEntity> allTodayTasks = taskDeviceConRepository.findByBeginDateBetween(StaticMethods.getStartOfToday(), StaticMethods.getEndOfToday());
        if (!allTodayTasks.isEmpty()) {
            List<TaskDeviceConEntity> todayTasks = new ArrayList<>();
            List<TaskDeviceConEntity> todayCompletedTasks = new ArrayList<>();

            //her bir kaydin hangi gunler tekrarlanacagini bul
            for (TaskDeviceConEntity task : allTodayTasks) {
//                String[] frequencyStr = task.getFrequencyArray().split(",");
//                int[] frequencies = new int[frequencyStr.length];
//                for (int i = 0; i < frequencyStr.length; i++) {
//                    frequencies[i] = Integer.parseInt(frequencyStr[i]);
//                }
//                if (frequencies[today - 1] == 1) {
                //bugunun gorevleri listesine ekle
                todayTasks.add(task);
                if (task.isIsCheck() == true) {
                    //bugunun tamamlanmis gorevleri listesine ekle
                    todayCompletedTasks.add(task);
                }
//                }
            }
            taskResponse.setTodayTasks(allTodayTasks.size());
            taskResponse.setTodayCompletedTasks(todayCompletedTasks.size());
            return new SuccessDataResult<>("Bugünün Görev Durumu Bulunmuştur.", taskResponse);
        } else {
            return new ErrorDataResult("Hiçbir Kayıt Bulunamadı.");
        }
    }

    @Override
    public double dailyRateOfChange() {
        TaskStatusResponseDto taskResponseToday = getTodayTaskStatus().getData();
        TaskStatusResponseDto taskResponseYesterday = new TaskStatusResponseDto();
        Double statusOfToday = Double.valueOf(String.valueOf(taskResponseToday.getTodayCompletedTasks())) / Double.valueOf(String.valueOf(taskResponseToday.getTodayTasks())) * 100;
        Double statusOfYesterday;
        List<TaskDeviceConEntity> allYesterdayTasks = taskDeviceConRepository.findByBeginDateBetween(StaticMethods.getStartOfYesterday(), StaticMethods.getEndOfYesterday());
        if (!allYesterdayTasks.isEmpty()) {
            List<TaskDeviceConEntity> yesterdayCompletedTasks = new ArrayList<>();

            for (TaskDeviceConEntity task : allYesterdayTasks) {
                if (task.isIsCheck() == true) {
                    yesterdayCompletedTasks.add(task);
                }
            }
            taskResponseYesterday.setTodayTasks(allYesterdayTasks.size());
            taskResponseYesterday.setTodayCompletedTasks(yesterdayCompletedTasks.size());

            int yesterdayCompleted = taskResponseYesterday.getTodayCompletedTasks();
            int yesterdayTasks = taskResponseYesterday.getTodayTasks();

            statusOfYesterday = Double.valueOf(String.valueOf(yesterdayCompleted)) / Double.valueOf(String.valueOf(yesterdayTasks)) * 100;
        } else {
            return statusOfToday;
        }
        System.out.println("============= status of daily task changing rate ================= " + (statusOfToday - statusOfYesterday));
        return statusOfToday - statusOfYesterday;
    }

    @Override
    public DataResult deleteTaskDeviceCon(int taskInfoId, int processId) {
        Optional<TaskDeviceConEntity> task = taskDeviceConRepository.findById(processId);
        if (task.isPresent()) {
            task.get().setUpdateId(processId);
            task.get().setUpdateTime(new Date());
            taskDeviceConRepository.save(task.get());
            taskDeviceConRepository.deleteById(taskInfoId);
            return new SuccessDataResult<>("Görev Ataması Silme Başarılı.");
        } else {
            return new ErrorDataResult<>("Görev Ataması Bulunamadı.");
        }
    }

    @Override
    public DataResult<TaskDeviceConEntity> getTaskDeviceConById(int id) {

        Optional<TaskDeviceConEntity> task = taskDeviceConRepository.findById(id);
        if (task.isPresent()) {
            return new SuccessDataResult("Görev Bulundu.", task);
        } else {
            return new ErrorDataResult("Görev Kaydı Bulunamadı");
        }
    }

    @Override
    public DataResult<List<TaskDeviceConDto>> getTaskDeviceConByDeviceId(int deviceId) {
        List<TaskDeviceConEntity> tasks = taskDeviceConRepository.findByDeviceId(deviceId);
        if (!tasks.isEmpty()) {
            List<TaskDeviceConDto> taskDtos = new ArrayList<>();
            for (TaskDeviceConEntity task : tasks) {
                TaskDeviceConDto taskDto = null;
                try {
                    if (task.isIsCheck()) {
                        taskDto = new TaskDeviceConDto(task.getId(), task.getTask(), task.getDevice(), task.getUser(), task.isIsCheck(), StaticMethods.dateToString(task.getCompleteDate()), StaticMethods.dateToString(task.getBeginDate()), StaticMethods.dateToString(task.getEndDate()), task.getFrequency(), task.getFrequencyArray());
                    } else {
                        taskDto = new TaskDeviceConDto(task.getId(), task.getTask(), task.getDevice(), task.getUser(), task.isIsCheck(), null, StaticMethods.dateToString(task.getBeginDate()), StaticMethods.dateToString(task.getEndDate()), task.getFrequency(), task.getFrequencyArray());
                    }

                } catch (ParseException ex) {
                    Logger.getLogger(TaskDeviceConManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                taskDtos.add(taskDto);
            }
            return new SuccessDataResult<>("Cihaza Ait Görevler Listelenmiştir.", taskDtos);
        } else {
            return new ErrorDataResult<>("Cihaza Ait Görev Bulunamadı.");
        }
//        return new SuccessDataResult("henuz kodlanma asamasinda");
    }

    @Override
    public List<TaskDeviceConEntity> getTaskDeviceConByTaskIdAndDeviceId(int taskId, int deviceId) {
        return taskDeviceConRepository.findByTaskIdAndDeviceId(taskId, deviceId);
    }

    ////////////////////////////////////
    //Bu method repodaki sıkıntıdan dolayı calısmıyor.
    @Override
    public DataResult<List<TaskDeviceConDto>> getTaskDeviceConByDateBetween(BetweenDateDto dateJson) {
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = StaticMethods.convertStringToDate(dateJson.getStartDate());
            endDate = StaticMethods.convertStringToDate(dateJson.getEndDate());
        } catch (ParseException ex) {
            Logger.getLogger(TaskDeviceConManager.class.getName()).log(Level.SEVERE, null, ex);
        }
//        List<TaskDeviceConEntity> tasksBetweenDate = null;//taskDeviceConRepository.findAllByTaskDeviceConCompleteDateBetween(startDate, endDate);
        List<TaskDeviceConEntity> tasksBetweenDate = taskDeviceConRepository.findByBeginDateBetween(startDate, endDate);
        if (!tasksBetweenDate.isEmpty()) {
            List<TaskDeviceConDto> taskDtos = new ArrayList<>();
            for (TaskDeviceConEntity task : tasksBetweenDate) {
                try {
                    TaskDeviceConDto taskDto;
                    if (task.isIsCheck()) {
                        taskDto = new TaskDeviceConDto(task.getId(),
                                task.getTask(),
                                task.getDevice(),
                                task.getUser(),
                                task.isIsCheck(),
                                StaticMethods.dateToString(task.getCompleteDate()),
                                StaticMethods.dateToString(task.getBeginDate()),
                                StaticMethods.dateToString(task.getEndDate()),
                                task.getFrequency(),
                                task.getFrequencyArray());
                    } else {
                        taskDto = new TaskDeviceConDto(task.getId(),
                                task.getTask(),
                                task.getDevice(),
                                task.getUser(),
                                task.isIsCheck(),
                                null,
                                StaticMethods.dateToString(task.getBeginDate()),
                                StaticMethods.dateToString(task.getEndDate()),
                                task.getFrequency(),
                                task.getFrequencyArray());
                    }
                    taskDtos.add(taskDto);
                } catch (ParseException ex) {
                    Logger.getLogger(TaskDeviceConManager.class.getName()).log(Level.SEVERE, null, ex);
                    return new ErrorDataResult<>("Tarih Dönüşütürülürken Hata Oluştu.");
                }
            }
            return new SuccessDataResult<>("Tarih Aralığına Ait Görevler Bulunmuştur.", taskDtos);
        } else {
            return new ErrorDataResult<>("İlgili Tarih Aralığında Herhangi Bir Kayıt Bulunamamıştır.");
        }
    }

    @Override
    public DataResult completeTask(int taskId, int updateId) {
        Optional<TaskDeviceConEntity> task = taskDeviceConRepository.findById(taskId);
        UserEntity user = new UserEntity();
        if (task.isPresent()) {
            task.get().setIsCheck(true);
            task.get().setCompleteDate(new Date());
            task.get().setUser(user);
            task.get().getUser().setId(updateId);
            task.get().setUpdateId(updateId);
            task.get().setUpdateTime(new Date());

            TaskDeviceConEntity updatedTask = taskDeviceConRepository.save(task.get());
            if (updatedTask.isIsCheck() == true) {
                return new SuccessDataResult<>("Görev Tamamlandı.");
            } else {
                return new ErrorDataResult<>("Görev Tamamlanırken Hata Oluştu.");
            }
        } else {
            return new ErrorDataResult("Görev Bulunamadı.");
        }
    }

    @Override
    public DataResult<List<TaskDeviceConDto>> getTaskDeviceConDaily() {
        Date yesterday = StaticMethods.getYesterdayDate();
        Date today = new Date();

        List<TaskDeviceConEntity> tasksBetweenDate = taskDeviceConRepository.findByBeginDateBetween(yesterday, today);
        List<TaskDeviceConDto> taskDtos = new ArrayList<>();
        if (!tasksBetweenDate.isEmpty()) {
            for (TaskDeviceConEntity task : tasksBetweenDate) {
                try {
                    TaskDeviceConDto taskDto;
                    if (task.isIsCheck()) {
                        taskDto = new TaskDeviceConDto(task.getId(),
                                task.getTask(),
                                task.getDevice(),
                                task.getUser(),
                                task.isIsCheck(),
                                StaticMethods.dateToString(task.getCompleteDate()),
                                StaticMethods.dateToString(task.getBeginDate()),
                                StaticMethods.dateToString(task.getEndDate()),
                                task.getFrequency(),
                                task.getFrequencyArray());
                    } else {
                        taskDto = new TaskDeviceConDto(task.getId(),
                                task.getTask(),
                                task.getDevice(),
                                task.getUser(),
                                task.isIsCheck(),
                                null,
                                StaticMethods.dateToString(task.getBeginDate()),
                                StaticMethods.dateToString(task.getEndDate()),
                                task.getFrequency(),
                                task.getFrequencyArray());
                    }
                    taskDtos.add(taskDto);
                } catch (ParseException ex) {
                    Logger.getLogger(TaskDeviceConManager.class.getName()).log(Level.SEVERE, null, ex);
                    return new ErrorDataResult<>("Tarih Dönüşütürülürken Hata Oluştu.");
                }
            }
            return new SuccessDataResult<>("Son Bir Güne Ait Görevler Bulunmuştur.", taskDtos);
        } else {
            return new ErrorDataResult<>("Son Bir Güne Ait Herhangi Bir Kayıt Bulunamamıştır.");
        }
    }

    @Override
    public DataResult<List<TaskDeviceConDto>> getTaskDeviceConWeekly() {
        Date lastWeek = StaticMethods.getLastWeekDate();
        Date today = new Date();

        List<TaskDeviceConEntity> tasksBetweenDate = taskDeviceConRepository.findByBeginDateBetween(lastWeek, today);
        if (!tasksBetweenDate.isEmpty()) {
            List<TaskDeviceConDto> taskDtos = new ArrayList<>();
            for (TaskDeviceConEntity task : tasksBetweenDate) {
                try {
                    TaskDeviceConDto taskDto;
                    if (task.isIsCheck()) {
                        taskDto = new TaskDeviceConDto(task.getId(),
                                task.getTask(),
                                task.getDevice(),
                                task.getUser(),
                                task.isIsCheck(),
                                StaticMethods.dateToString(task.getCompleteDate()),
                                StaticMethods.dateToString(task.getBeginDate()),
                                StaticMethods.dateToString(task.getEndDate()),
                                task.getFrequency(),
                                task.getFrequencyArray());
                    } else {
                        taskDto = new TaskDeviceConDto(task.getId(),
                                task.getTask(),
                                task.getDevice(),
                                task.getUser(),
                                task.isIsCheck(),
                                null,
                                StaticMethods.dateToString(task.getBeginDate()),
                                StaticMethods.dateToString(task.getEndDate()),
                                task.getFrequency(),
                                task.getFrequencyArray());
                    }
                    taskDtos.add(taskDto);
                } catch (ParseException ex) {
                    Logger.getLogger(TaskDeviceConManager.class.getName()).log(Level.SEVERE, null, ex);
                    return new ErrorDataResult<>("Tarih Dönüşütürülürken Hata Oluştu.");
                }
            }
            return new SuccessDataResult<>("Son Bir Haftaya Ait Görevler Bulunmuştur.", taskDtos);
        } else {
            return new ErrorDataResult<>("Son Bir Haftaya Ait Herhangi Bir Kayıt Bulunamamıştır.");
        }
    }

    @Override
    public DataResult<List<TaskDeviceConDto>> getTaskDeviceConMonthly() {
        Date lastMonth = StaticMethods.getLastMonthDate();
        System.out.println("******** last month date : " + lastMonth.toString());
        Date today = new Date();

        List<TaskDeviceConEntity> tasksBetweenDate = taskDeviceConRepository.findByBeginDateBetween(lastMonth, today);
        if (!tasksBetweenDate.isEmpty()) {
            List<TaskDeviceConDto> taskDtos = new ArrayList<>();
            for (TaskDeviceConEntity task : tasksBetweenDate) {
                try {
                    TaskDeviceConDto taskDto;
                    if (task.isIsCheck()) {

                        taskDto = new TaskDeviceConDto(task.getId(),
                                task.getTask(),
                                task.getDevice(),
                                task.getUser(),
                                task.isIsCheck(),
                                StaticMethods.dateToString(task.getCompleteDate()),
                                StaticMethods.dateToString(task.getBeginDate()),
                                StaticMethods.dateToString(task.getEndDate()),
                                task.getFrequency(),
                                task.getFrequencyArray());
                    } else {
                        taskDto = new TaskDeviceConDto(task.getId(),
                                task.getTask(),
                                task.getDevice(),
                                task.getUser(),
                                task.isIsCheck(),
                                null,
                                StaticMethods.dateToString(task.getBeginDate()),
                                StaticMethods.dateToString(task.getEndDate()),
                                task.getFrequency(),
                                task.getFrequencyArray());
                    }
                    taskDtos.add(taskDto);
                } catch (ParseException ex) {
                    Logger.getLogger(TaskDeviceConManager.class.getName()).log(Level.SEVERE, null, ex);
                    return new ErrorDataResult<>("Tarih Dönüşütürülürken Hata Oluştu.");
                }
            }
            return new SuccessDataResult<>("Son Bir Aya Ait Görevler Bulunmuştur.", taskDtos);
        } else {
            return new ErrorDataResult<>("Son Bir Aya Ait Herhangi Bir Kayıt Bulunamamıştır.");
        }
    }

    @Override
    public DataResult<List<TaskDeviceConDto>> getCompletedTaskDeviceCon() {
        List<TaskDeviceConEntity> tasks = taskDeviceConRepository.findByIsCheck(Boolean.TRUE);
        if (!tasks.isEmpty()) {
            List<TaskDeviceConDto> taskDtos = new ArrayList<>();
            for (TaskDeviceConEntity task : tasks) {
                TaskDeviceConDto taskDto;
                try {
                    if (task.isIsCheck() == true) {
                        taskDto = new TaskDeviceConDto(task.getId(), task.getTask(), task.getDevice(), task.getUser(), task.isIsCheck(), StaticMethods.dateToString(task.getCompleteDate()), StaticMethods.dateToString(task.getBeginDate()), StaticMethods.dateToString(task.getEndDate()), task.getFrequency(), task.getFrequencyArray());
                    } else {
                        taskDto = new TaskDeviceConDto(task.getId(), task.getTask(), task.getDevice(), task.getUser(), task.isIsCheck(), null, StaticMethods.dateToString(task.getBeginDate()), StaticMethods.dateToString(task.getEndDate()), task.getFrequency(), task.getFrequencyArray());
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(TaskDeviceConManager.class.getName()).log(Level.SEVERE, null, ex);
                    return new ErrorDataResult<>("Tarih Dönüşütürülürken Hata Oluştu.");
                }
                taskDtos.add(taskDto);
            }
            return new SuccessDataResult<>("Tamamlanmış Görevler Başarılı Şekilde Bulundu.", taskDtos);
        } else {
            return new ErrorDataResult<>("Tamamlanmış Herhangi Bir Görev Bulunamadı.");
        }
    }

    public String getDataFromBeginningWeekToToday() {
        Json jsonData = new Json();
        Calendar cal = Calendar.getInstance();
        Date beginningOfWeek = StaticMethods.findWeekStart();
        Date tempDate = beginningOfWeek;
        Date today = new Date();
        while (tempDate.before(today)) {
            try {
                //burada temp gune bir gun ekleyip yeniden temp gune atıyorum ki devam etsin
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String dateString = tempDate.toString();
                cal.setTime(sdf.parse(dateString));
                //tam burada bir gun eklemeden evvel atanmıs ve tamamlanmıs gorevleri cekmem gerekiyor.
                //sonrasında bunu gunluk gorevlerin verisini tutmak icin olusturmus oldugum jsona ilgili gunun isminin karsisina atamam gerekiyor
                cal.add(Calendar.DATE, 1);
                tempDate = cal.getTime();
            } catch (ParseException ex) {
                Logger.getLogger(TaskDeviceConManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (tempDate.before(today)) {
            System.out.println("temp date'im bugunden once dogru ");
            //jsona bugunu ekle devam et
        } else {

        }
        return null;
    }

    @Override
    public List<TaskDeviceConEntity> update(TaskDeviceConEntity taskDeviceConEntity, int id) {
        List<TaskDeviceConEntity> response = null;
        return response;
    }

}
