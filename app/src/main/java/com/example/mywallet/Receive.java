/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.mywallet;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author salem
 */
public class Receive extends FinancialOperation implements Serializable{

    public Receive(double value, String comment, LocalDateTime dateTime) {
        super(value, comment, dateTime);
    }

    @Override
    int getType() {
        return RECIVE_TYPE;
    }

    @Override
    public String toString() {
        return "Recive{" + super.toString() + '}' ;
    }


    
    
    
}
