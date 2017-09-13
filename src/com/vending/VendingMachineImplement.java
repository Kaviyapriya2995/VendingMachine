package com.vending;

import java.util.*;
import java.util.Map.Entry;

public class VendingMachineImplement implements VendingMachine {

	protected int[] coins;
	Map<String,Integer> cashInventory = new HashMap<String,Integer>();
	 Map<String,Integer> itemInventory = new HashMap<String,Integer>();  
		HashMap<String, Integer> input_map = new HashMap<String, Integer>();
	    private long totalSales;
	    Boolean flag = false;
	    Scanner in = new Scanner(System.in);
	    private String currentItem;
	    private int currentBalance=0; 
	    private int paid_amnt=0;
	    VendingMachineImplement()
	    {
	    	
	    }
	    
	    void addInventory()
	    {
	    	itemInventory.put("coke",5);
	    	itemInventory.put("pepsi",5);
	    	itemInventory.put("soda",15);
	    	cashInventory.put("one",10);
	    	cashInventory.put("five",20);
	    	cashInventory.put("ten",30);
	    	cashInventory.put("twentyfive",25);
	    	int sum=0;
	    	for (int i : cashInventory.values()) {
	    	    sum += i;
	    	}
	    	currentBalance=currentBalance+sum;
	    	System.out.println("Successfully filled");
	    }
	    public void reset(){
	        cashInventory.clear();
	        itemInventory.clear();
	        totalSales = 0;
	        currentItem = null;
	        currentBalance = 0;
	    	System.out.println("Successfully Reset");
	    } 
	    
	    public int selectItem_Getprice()
	    {
	    	int update=0;
	    	int price=0;
	    	int totalprice=0;
	    	for (Entry<String, Integer> entry: itemInventory.entrySet()) {
	    		
	    		 if (input_map.containsKey(entry.getKey())) {
	    			 	
	    			String temp= entry.getKey();
	    			Integer inventory_quan=entry.getValue();
	    			Integer needed_quan=input_map.get(temp);
	    		
	    			if(needed_quan<=inventory_quan )
	    			{
	    				update=inventory_quan-needed_quan;
	    				System.out.println("update"+update);
	    				if(temp=="coke")
	    					price=needed_quan*25;
	    				else if(temp=="pepsi")
	    					price=needed_quan*35;
	    				else
	    					price=needed_quan*45;
	    			}
	    			else
	    			{
	    				System.out.println("OUT OF STOCK");
	    				return -1;
	    			}
	    			itemInventory.put(temp, update);
	    			totalprice=totalprice+price;
	    		       
	    		    }
	    		 price=0;
	    	}
	    	totalSales=totalSales+totalprice;
	    	return totalprice;
	    }
	    
	    public int insertMoney(int price)
	    {
	    	HashMap<String,Integer>  coin = new HashMap<String,Integer> ();
	    
	    	int twentyfive=0;
	    	int five=0;
	    	int ten=0;
	    	int one=0;
	    
	    	   int rem_bal_user=0;
	    	   int rem_bal_mac=0;
	    	System.out.println("Make your payment(Acceptable denominations are : 1, 5, 10, 25)");
	    	
	    	System.out.println("Bill amount is "+price);
	
	        	System.out.println("How many 25 rupees ?");
	
	           twentyfive = in.nextInt();
	           paid_amnt=paid_amnt+25*twentyfive;
	       
	            coin.put("Twentyfive", twentyfive);
	            System.out.println("How many 10 rupees ?");
	        	
		           ten = in.nextInt();
		           paid_amnt=paid_amnt+10*ten;
		       
		            coin.put("Ten", ten);
		            System.out.println("How many 5 rupees ?");
		        	
			           five = in.nextInt();
			           paid_amnt=paid_amnt+5*five;
			       
			            coin.put("Five", five);
			            System.out.println("How many 1 rupees ?");
			        	
				           one = in.nextInt();
				           paid_amnt=paid_amnt+1*one;
				       
				            coin.put("One", one);
	            
				            if(price>paid_amnt)
				            {
				            	rem_bal_user=price-paid_amnt;
				            	System.out.println("Insufficient payment");
				            	return -1;
				        }
				            if(price<paid_amnt)
				            {
				            	rem_bal_mac=paid_amnt-price;
				            	if(rem_bal_mac>currentBalance)
				            	{
				            		//System.out.println("Machine does not have sufficient amount to return the change");
				            		return 2;
				            	}
				            }
				            else
				            {
				            	System.out.println("Amount paid is"+paid_amnt + "\n"+ "Collect your items");
				            }
				            	currentBalance=currentBalance+paid_amnt;
				      return rem_bal_mac;
	    }
	    
	    public void collectItemandchange(int change)
	    {
	    	for(Entry<String, Integer> entry: input_map.entrySet()) {

	            System.out.println("Item:"+entry.getKey()+" Quantity:"+entry.getValue());
	        }
	    	if(change!=0)
	    	{
	    		System.out.println("Collect the change"+change);
	    	}
	    	currentBalance=currentBalance-change;
	    }
	    
	    public void printAvailableitems()
	    {
	    	if(itemInventory!=null)
	    	{
	    		for(Entry<String, Integer> entry: itemInventory.entrySet()) {

		            System.out.println("Item:"+entry.getKey()+", Quantity:"+entry.getValue());
		        }
	    	}
	    	
	        
	    }
	    public int cancelandgetRefund()
	    {
	    	int update=0;
	    	int price=0;
	    	int totalprice=0;
	    	if(flag)
	    	{
	    		for (Entry<String, Integer> entry: itemInventory.entrySet()) {
		    		
		    		 if (input_map.containsKey(entry.getKey())) {
		    			 	
		    			String temp= entry.getKey();
		    			Integer inventory_quan=entry.getValue();
		    			Integer needed_quan=input_map.get(temp);
		    			
		    			
		    				update=inventory_quan+needed_quan;
		    				
		    			
		    			itemInventory.put(temp, update);
		    		
		    		       
		    		    }
		    		 price=0;
		    	}
		    	totalSales=totalSales- paid_amnt;
		    	currentBalance=currentBalance-paid_amnt;
		    	System.out.println("Collect your refund amount"+paid_amnt);
		    	return paid_amnt;
	    	}
	    	else
	    	{
	    		System.out.println("You dont have any order to cancel !! Please place the order");
	    	}
	    
	    	return 0;
	    }
	    public void getUserOrder()
	    {
	    	System.out.println("Available items with quantity");
			printAvailableitems();
	    	
	    	System.out.println("Select the items and enter quantity \n press q if you are done");
		
	    		flag=true;
		
			        for(;;){
			
			        	System.out.println("Enter the item : ");
			
			            String input = in.nextLine();
			
			            if (input.equalsIgnoreCase("q"))
			
			               break;
			
			            System.out.println("Quantity: ");
			            String str = in.nextLine();
			            int id = Integer.parseInt(str);
			
			            input_map.put(input, id);
			
			        }
			        int result= selectItem_Getprice();   
			      //  System.out.print("result: " + result);
			        if(result==-1)
			        {
			        	flag=false;
			        	return;
			        }
			        int change_rem=insertMoney(result);
			        if(change_rem==-1)
			        {
			        	System.out.println("Payment made by you is insufficient");
			        	return;
			        }
			        if(change_rem==2)
			        {
			        	System.out.println("Insufficient cash to give the change");
			        	return;
			        }
			        collectItemandchange(change_rem);
			       
	    }
	    public void userMenu()
	    {
	    	
	    	String choice=null;
	    	System.out.println("Welcome");
			

			do {
				System.out.println("a: Make order");
				System.out.println("b: Cancel order");
				System.out.println("q: Quit");
				System.out.println("Enter your choice");
				choice=in.nextLine();
				switch (choice){
				    case "a":
				    	getUserOrder();
				        break;
				    case "b":
				       cancelandgetRefund();
				        break;
				    case "q":
				    	System.out.println("Thank you");
				    	System.exit(0);
				    default:
				    	choice="q";
				    	System.out.println("Invalid choice");
				}
			}while(!choice.equalsIgnoreCase("q"));
	    }
	    
	    public void supplierMenu()
	    {
	    	String choice=null;
	    	

			do {
				System.out.println("Welcome Supplier");
				System.out.println("a: Fill machine ");
				System.out.println("b: Reset Machine");
				System.out.println("c: See machine status");
				System.out.println("q: Quit");
				choice=in.nextLine();
				switch (choice){
				    case "a":
				    	addInventory();
				        break;
				    case "b":
				       // cancelandRefund();
				    	reset();
				        break;
				    case "c":
				    	printAvailableitems();
					        break;
				    case "q":
				    	System.out.println("Thank you");
				    	System.exit(0);
				    default:
				    	choice="q";
				    	System.out.println("Invalid choice");
				}
			}while(!choice.equalsIgnoreCase("q"));
	    }
	public static void main(String args[])
	{
		VendingMachineImplement obj=new VendingMachineImplement();
		Scanner inp = new Scanner(System.in);
		String choice=null;
		System.out.println("Vending Machine");
		obj.addInventory();
		
		
		do {
			System.out.println("Welcome please tell us who u are ? ");
			System.out.println("a: User");
			System.out.println("b: Supplier");
			System.out.println("q: Quit");
			choice=inp.nextLine();
			switch (choice){
			    case "a":
			       obj.userMenu();
			        break;
			    case "b":
			        obj.supplierMenu();
			        break;
			    case "q":
			    	System.out.println("Thank you");
			    	return;
			}
		}while(!choice.equalsIgnoreCase("q"));
		
		
		
	}
}
