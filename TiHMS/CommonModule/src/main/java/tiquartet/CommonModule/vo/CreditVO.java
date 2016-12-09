package tiquartet.CommonModule.vo;

import java.io.Serializable;

import tiquartet.CommonModule.util.CreditChange;

public class CreditVO implements Serializable{
    public CreditChange changeType;
    public double change;
    public double balance;
    public long orderID;
    public long creditRecordID;
}
