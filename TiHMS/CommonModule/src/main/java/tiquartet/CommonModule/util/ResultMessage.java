package tiquartet.CommonModule.util;

import java.io.Serializable;

public class ResultMessage implements Serializable{
	 public boolean result;
	 public String failInfo;
	 public String message;
	 
	 public ResultMessage(boolean isSuccessful){
		 result=isSuccessful;
	 }
	 
	 public ResultMessage(boolean isSuccessful,String failInfo,String message){
		 result=isSuccessful;
		 this.failInfo=failInfo;
		 this.message=message;
	 }
}
