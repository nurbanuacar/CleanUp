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
public class SuccessDataResult<T> extends DataResult<T> {

    public SuccessDataResult() {
        super(true);
    }

    public SuccessDataResult(String message) {
        super(true, message);
    }

    public SuccessDataResult(String message, T data) {
        super(true, message, data);
    }

    public SuccessDataResult(T data) {
        super(true, data);
    }
}
