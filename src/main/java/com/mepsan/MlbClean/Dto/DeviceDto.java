/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Dto;

/**
 *
 * @author nurbanu.acar
 */
public class DeviceDto {

    private int id;
    private String name;
    private String ip;
    private String floor;
    private String deviceName;

    public DeviceDto() {
    }

    public DeviceDto(int id, String name, String ip, String floor) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.floor = floor;
    }

    public DeviceDto(int id, String name, String ip, String floor, String deviceName) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.floor = floor;
        this.deviceName = deviceName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

}
