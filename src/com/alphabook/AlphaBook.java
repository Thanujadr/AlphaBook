package com.alphabook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AlphaBook {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("===WELCOME TO ALPHABOOK APPLICATION===");
		
		System.out.println("------------------------------------");
		System.out.println();
		
		while(true)
		{
		 System.out.println("\n   1. SIGNUP\n   2. LOGIN\n   3. EXIT\n\n");
		 System.out.println("==ENTER YOUR CHOICE==");
		 System.out.println("-----------------------------------");
		 int choice = sc.nextInt();
		 switch(choice)
		 {
		       case 1:System.out.println("==WELOCOME TO ALPHABOOK SIGNUP PAGE==\n");
		       Alpha_Signup a1 = new Signup();
		               a1. signup();
		             break;
		       case 2:System.out.println("\n\n==WELOCOME TO ALPHABOOK LOGIN PAGE==");
		       Alpha_Login a2=new Login();
			          a2. login();     
		       break;
		       case 3:
		    	   System.out.println("===THANK YOU FOR CHOOSING ALPHABOOK VISIT AGAIN===");
		    	   System.exit(0);
		       default:
		    	   System.out.println("==INVALID CHOICE==");
		 }
		}
	}
}