package com.user.utils;

public class ServerUtils {
	 public static Integer getGivenRamdonNum(int num){
	        //int ���2147483647��Ϊ10λ�����Ա�����10Ϊ����
	        if (num<=1||num>=10){
	            return 0;
	        }
	        int num1 = (int)Math.pow(10d,Double.parseDouble(num+""));
	        int num2 = (int)Math.pow(10d,Double.parseDouble((num-1)+""));
	        return (int)(Math.random()*(num1-num2))+num2;
	 }
}
