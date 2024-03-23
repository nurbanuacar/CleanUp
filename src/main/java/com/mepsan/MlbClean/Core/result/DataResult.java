/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Core.result;

/**
 *
 * @author nurbanu.acar
 * @param <T>
 */
public class DataResult<T> extends Result {

    private T data;

    public DataResult(boolean success) {
        super(success);
    }

    public DataResult(boolean success, String message) {
        super(success, message);
    }

    public DataResult(boolean success, T data) {
        super(success);
        this.data = data;
    }

    public DataResult(boolean success, String message, T data) {
        super(success, message);
        this.data = data;
    }

    public T getData() {
        return data;
    }

}
