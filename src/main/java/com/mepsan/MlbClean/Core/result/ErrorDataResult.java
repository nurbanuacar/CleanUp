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
public class ErrorDataResult<T> extends DataResult<T>{

    public ErrorDataResult() {
        super(false);
    }
    public ErrorDataResult(String message) {
        super(false,message);
    }
    public ErrorDataResult(String message, T data) {
        super(false,message,data);
    }
    public ErrorDataResult(T data) {
        super(false, data);
    }
    
}
