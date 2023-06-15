package com.alphabook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Signup implements Alpha_Signup
{
     Scanner sc=new Scanner(System.in);
	@Override
	public void signup() {

		System.out.println("Enter Email address : ");
		String email=sc.next();
		boolean f=true;
		for(int i=0;i<email.length();i++)
		{
			if(email.charAt(i)=='@')
			{
				f=false;
				break;
			}
		}
		if(f)
		{
			boolean f1=true;
			while(true)
			{
				System.out.println("Enter valid email.....");
				email=sc.next();
				for(int i=0;i<email.length();i++)
				{
					if(email.charAt(i)=='@')
					{
						f1=false;
						break;
					}
				}
				if(!(f1))
				{
					break;
				}
			}
		}
		String url1="jdbc:mysql://localhost:3306/userdetails";
		String user1="root";
		String pass1="root";
		String query1="select email from users";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url1,user1,pass1);
			Statement st=con.createStatement();
			ResultSet r=st.executeQuery(query1);
			
			boolean flag=false;
			while(r.next())
			{
				if(email.equals(r.getString("email")))
				{
					flag=true;
					break;
				}
			}
			if(flag)
			{
				System.out.println("\n\nUser already exist please login to continue......");
				return;
			}	
		} catch (ClassNotFoundException | SQLException e1) {
		
			e1.printStackTrace();
		}
		
		System.out.println("Enter first name : ");
		String firstname=sc.next();
		System.out.println("Enter last name : ");
		String lastname=sc.next();
		String gender=null;
		while(true)
		{
			System.out.println("Select Gender : ");
			System.out.println("  1. Female \n  2. Male \n  3. Transgender");
			int ch = sc.nextInt();
			switch(ch)
			{
			  case 1:gender="Female";
			         break;
			  case 2:gender="Male";
			         break;
			  case 3:gender="Transgender";
			         break;
			  default:System.out.println("Invalid choice......");
			  break;
			}
			if(ch==1 || ch==2 || ch==3)
			{
				break;
			}
			else {
				continue;
			}
		}
		System.out.println("Enter contact number : ");
		String phnno=sc.next();
		
		     if(phnno.length()!=10)
				{
		    	 while(true)
		    	 {
			       System.out.println("Enter valid phone number : ");
			       phnno=sc.next();
			       if(phnno.length()==10)
			       {
			    	   break;
			       }
		    	 }
				}
		System.out.println("Set your password which satisfy below given rules : \n 1. It should consist of minimum 8 characters\n "
				+ "2. It should start with numeric value\n "
				+ "3. It should include atleast one Capital letter\n");
		String password=sc.next();
		
		    if(! checkpass(password))
		      {
		    	while(true) { 
		    	    password = sc.next();
			        if(checkpass(password))
			        {
			        	break;
			        }
		    	}   
		      }
			System.out.println(" \n\n1. SUBMIT\n 2. CANCEL");
			System.out.println("==Enter your option==");
			int choice=sc.nextInt();
			switch(choice)
			{
			case 1: 
			{
				String query="insert into users values(?,?,?,?,?,?)";
				
			 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			    Connection conn  =	DriverManager.getConnection(url1, user1, pass1);
			    PreparedStatement ps = conn.prepareStatement(query);
			    
			    ps.setString(1,firstname );
			    ps.setString(2, lastname);
			    ps.setString(3, gender);
			    ps.setString(4, phnno);
			    ps.setString(5, email);
			    ps.setString(6, password);
				
			    ps.execute();
			    System.out.println("Account created successfully... !!!");
			}
			 catch (ClassNotFoundException | SQLException e) {	
				e.printStackTrace();
			}
			}
			break;
			case 2:
			{
				return;
			}
			default:System.out.println("INAVLID CHOICE");
			}
	}
	public boolean checkpass(String pwd)
	{
		int spl=0;
		int upr=0;
		if(pwd.length()<8)
		{
			System.out.println("Password should include minimum 8 characters  !!!");
			return false;
		}
		else if(!(pwd.charAt(0)>='0' && pwd.charAt(0)<='9'))
		{
			System.out.println("Password should start with numberic value !!!");
			return false;
		}
		for(int i=1;i<pwd.length();i++)
		{	
			if((pwd.charAt(i)>='A')&&(pwd.charAt(i)<='Z'))
			{
				upr++;
			}
		}
		if(upr==0)
		{
			System.out.println("Password should include atleast one uppercase character  !!!");
			return false;
		}
		return true;
	}
}
