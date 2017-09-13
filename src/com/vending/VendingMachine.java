package com.vending;
import java.util.*;
public interface VendingMachine {
	public int selectItem_Getprice();
	public int insertMoney(int price);
	public void collectItemandchange(int change);
}




/*public void insertMoney(int[] coin);
public int getRefund();

public void reset();*/
