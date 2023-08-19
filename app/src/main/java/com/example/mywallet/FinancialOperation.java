/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mywallet;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * @author salem
 */
public class FinancialOperation implements Serializable {
    double value;
    String comment;
    LocalDateTime dateTime;
    int PAY_TYPE = 101;
    int RECIVE_TYPE = 102;


    public double getValue() {
        return value;
    }

    public String getComment() {
        return comment;
    }

    public FinancialOperation(double value, String comment, LocalDateTime dateTime) {
        this.value = value;
        this.comment = comment;
        this.dateTime = dateTime;

    }
    int getType(){
        return 0;
    }






    @Override
    public String toString() {
        return "FinancialOperation{" + "value=" + value + ", comment=" + comment + '}';
    }


}
