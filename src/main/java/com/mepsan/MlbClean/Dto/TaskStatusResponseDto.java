/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Dto;

/**
 *
 * @author nurbanu.acar
 */
public class TaskStatusResponseDto {

    private int todayTasks;
    private int todayCompletedTasks;

    public TaskStatusResponseDto() {
    }

    public TaskStatusResponseDto(int todayTasks, int todayCompletedTasks) {
        this.todayTasks = todayTasks;
        this.todayCompletedTasks = todayCompletedTasks;
    }

    public int getTodayTasks() {
        return todayTasks;
    }

    public void setTodayTasks(int todayTasks) {
        this.todayTasks = todayTasks;
    }

    public int getTodayCompletedTasks() {
        return todayCompletedTasks;
    }

    public void setTodayCompletedTasks(int todayCompletedTasks) {
        this.todayCompletedTasks = todayCompletedTasks;
    }

}
