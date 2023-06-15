package com.alphabook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login implements Alpha_Login
{
  Scanner sc=new Scanner(System.in);
	@Override
	public void login() {
		String url="jdbc:mysql://localhost:3306/userdetails";
		String user="root";
		String pass="root";
		String query="select * from users";

	 try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	 
		Connection conn  =	DriverManager.getConnection(url, user, pass);
	    Statement ps = conn.createStatement();
	    ResultSet rs= ps.executeQuery(query);
		
		System.out.println("Enter your email : ");
		String email=sc.next();
		
		boolean flag=false;
		
		String password =null;
		String mail=null;
		while(rs.next())
		{
			if(email.equals(rs.getString("email")))	
			{
				flag=true;
				password=rs.getString("password");
				mail=rs.getString("email");
				break;
			}	
		}
		String pass1;
		boolean flag1=true;
		boolean f=true;
		if(flag)
		{
			System.out.println("Enter your password : ");
			 pass1=sc.next();
			if(pass1.equals(password))
			{
				flag1=false;
				System.out.println("\nLogin successfully   !!!\n");	
				while(true)
				{
					System.out.println("First name :"+rs.getString(1));
					System.out.println("Last name :"+rs.getString(2));
					System.out.println("Gender :"+rs.getString(3));
					System.out.println("Phone number :"+rs.getString(4));
					System.out.println("Email :"+rs.getString(5));
					
					System.out.println("\n\n Select one option to logout .......\n  1. logout");
					int ch=sc.nextInt();
					switch(ch) {
					case 1:return;
					}
					if(ch!=1)
					{
						continue;
					}
				}
			}
			else
			{
				f=false;
			}
		}	
		else
			{
				System.out.println("  User not found ......");	
			}
	int count=0;
	if(flag1==true && f==false) 
	{	
		while(count!=3)
		    {
				for(int i=0;i==0;i++)
				{
					count++;
				    System.out.println("Wrong password......");
				    System.out.println("Re-enter the password.....");
				    pass1=sc.next();
				    if(pass1.equals(password))
					{
						flag1=false;																								
						System.out.println("Login successfully   !!!\n\n\n");
						break;	
					}
				}
				 if(flag1==false)
				    {
				    	break;
				    }
			}	
	}
	 if(count==3)
	{
		System.out.println("Login Attempts Over      !!!");
		System.out.println("  1. RESET PASSWORD \n  2.CANCEL");
		System.out.println("-----------------------------------\n\n");
		System.out.println("Enter your choice......");
		int op=sc.nextInt();
		switch(op)
		{
		  case 1:{
			while(true)
			{
				System.out.println("\nEnter your registered phone number for authentication......\n");
			    String phnno=sc.next();
				if(phnno.equals(rs.getString(4)))
				{
					break;
				}
				else
				{
					continue;
				}
			}
			System.out.println("Enter new password......");
			String pwd=sc.next();
			String query2="update users set password=? where email=?";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url, user, pass);
			PreparedStatement pst=con.prepareStatement(query2);
			
			pst.setString(1, pwd);
			pst.setString(2, mail);
			pst.execute();
			System.out.println("Password has been updated successfully   !!!");
			break;
		}
		  case 2:
		{
			return;
		}
		default:System.out.println("Invalid choice !!!");
		}
	}
	} catch (ClassNotFoundException | SQLException e) {
		
		e.printStackTrace();
    }
	}
}