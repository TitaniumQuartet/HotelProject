package tiquartet.CommonModule.vo;

import java.io.Serializable;

import tiquartet.CommonModule.util.CreditRestore;

public class CreditVO implements Serializable{
    public CreditRestore changeType;
    public double change;
    public double balance;
    public long orderID;
    public long creditRecordID;
}
