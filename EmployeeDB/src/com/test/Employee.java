package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Employee {
	
	static final String url="jdbc:mysql://localhost:3306/employee";
	static final String uname="root";
    static final String pass="sohamk";
	static Scanner sc=new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		int ch;
		do{
			System.out.println("\n***EMPLOYEE REGISTRATION***");
			System.out.println("1. Add Employee");
			System.out.println("2. View All Employee");
			System.out.println("3. Update Employee");
			System.out.println("4. Delete Employee");
			System.out.println("5. Exit\n");
			
			System.out.print("Enter your choice: ");
			ch=sc.nextInt();
			
			switch(ch) {
			case 1:
				addEmployee();
				break;
				
			case 2:
				viewEmployee();
			    break;
				
			case 3:
				updateEmployee();
				break;
				
			case 4:
				deleteEmployee();
				break;
			
			case 5:
				System.out.println("Exiting from Menu...");
				break;
				
			default:
				System.out.println("\nInvalid Input!!!");
				break;
			}			
		}while(ch!=5);
	}

	private static void deleteEmployee() throws SQLException{
		// TODO Auto-generated method stub
		Connection con = DriverManager.getConnection(url,uname,pass);
		
		int deleteChoice;
		do {
			System.out.println("\n***EMPLOYEE DELETION***");
			System.out.println("1. Delete Employee");
			System.out.println("2. Delete All Employee Data");
			System.out.println("3. Exit");
			
			System.out.print("Enter your choice: ");
			deleteChoice=sc.nextInt();
			
			switch(deleteChoice) {
			case 1:
				sc.nextLine();
				System.out.print("Enter ID of employee you want do delete: ");
				int id=sc.nextInt();
				sc.nextLine();
				
				String sql = "DELETE from emp WHERE id=?";
				
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setInt(1, id);
				
				int rows=ps.executeUpdate();
				if(rows>0) {
					System.out.println("Employee deleted successfullly!");
					break;
				}
				else {
					System.out.println("Failed to delete employee.");
					break;
				}
			
			case 2:
				char temp;
				System.out.print("Are you the admin? (Y/N) : ");
				temp=sc.next().charAt(0);
				if(temp=='y' || temp=='Y')
				{
					String dsql = "TRUNCATE table emp";
					
					PreparedStatement dps=con.prepareStatement(dsql);
					
					int drows=dps.executeUpdate();
					if(drows>=0) {
						System.out.println("Deletion Successful!");
					}
					else {
						System.out.println("Deletion Failed.");
					}
				}
				else if(temp=='n' || temp=='N')
				{
					System.out.print("Then you dont have the permission to do this.\n");
					break;
				}
				else
				{
					System.out.print("Choose between (Y/N)");
					break;
				}
			
			case 3:
				System.out.println("Exiting to main menu...");
				con.close();
				break;
			
			default:
				System.out.println("Invalid Choice!");
				break;
			}

		}while(deleteChoice!=3);
		
	}

	private static void updateEmployee() throws SQLException {
		// TODO Auto-generated method stub
		Connection con = DriverManager.getConnection(url,uname,pass);
		
		int updateChoice;
		do{
			System.out.println("\n***EMPLOYEE UPDATION***");
			System.out.println("1. Update Name");
			System.out.println("2. Update Department");
			System.out.println("3. Update Salary");
			System.out.println("4. Exit");
			
			System.out.print("Enter your choice: ");
			updateChoice=sc.nextInt();
			
			switch(updateChoice) {
			case 1:
				sc.nextLine();
				System.out.print("Enter ID of employee: ");
				int nid=sc.nextInt();
				sc.nextLine();
				
				System.out.print("Enter updated name: ");
				String uname=sc.nextLine();
				
				String sql = "UPDATE emp SET name=? WHERE id=?";
				
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, uname);
				ps.setInt(2, nid);
				
				int rows=ps.executeUpdate();
				if(rows>0) {
					System.out.println("Name updated successfullly!");
				}
				else {
					System.out.println("Failed to update name.");
				}
			
			case 2:
				sc.nextLine();
				System.out.print("Enter ID of employee: ");
				int did=sc.nextInt();
				sc.nextLine();
				
				System.out.print("Enter updated department: ");
				String dept=sc.nextLine();
				
				String dsql = "UPDATE emp SET department=? WHERE id=?";
				
				PreparedStatement dps=con.prepareStatement(dsql);
				dps.setString(1, dept);
				dps.setInt(2, did);
				
				int drows=dps.executeUpdate();
				if(drows>0) {
					System.out.println("Name updated successfullly!");
				}
				else {
					System.out.println("Failed to update name.");
				}
			
			case 3:
				sc.nextLine();
				System.out.print("Enter ID of employee: ");
				int sid=sc.nextInt();
				sc.nextLine();
				
				System.out.print("Enter updated salary: ");
				String sal=sc.nextLine();
				
				String ssql = "UPDATE emp SET salary=? WHERE id=?";
				
				PreparedStatement sps=con.prepareStatement(ssql);
				sps.setString(1, sal);
				sps.setInt(2, sid);
				
				int srows=sps.executeUpdate();
				if(srows>0) {
					System.out.println("Name updated successfullly!");
				}
				else {
					System.out.println("Failed to update name.");
				}
				
			case 4:
				System.out.println("Exiting from Menu...");
				con.close();
				break;
				
			default:
				System.out.println("\nInvalid Input!!!");
				break;
			}
		}while(updateChoice!=4);
	}

	private static void viewEmployee() throws SQLException{
		// TODO Auto-generated method stub
		Connection con = DriverManager.getConnection(url,uname,pass);
		Statement stmt=con.createStatement();
		String sql="select * from emp";
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next()) {
			int id=rs.getInt("id");
			String name=rs.getNString("name");
			String dept=rs.getNString("department");
			double salary=rs.getDouble("salary");
			System.out.println("\nID: "+id);
			System.out.println("Name: "+name);
			System.out.println("Department: "+dept);
			System.out.println("Salary: "+salary);
		}
		con.close();
	}

	private static void addEmployee() throws SQLException {
		// TODO Auto-generated method stub
		sc.nextLine();
		System.out.print("Enter your name: ");
		String name=sc.nextLine();
		
		System.out.print("Enter your department: ");
		String department=sc.nextLine();
		System.out.print("Enter your salary: ");
		double salary=sc.nextDouble();
		
		String sql=("insert into emp(name, department, salary)"+"values(?,?,?)");
		Connection con=DriverManager.getConnection(url,uname,pass);
		
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, department);
		ps.setDouble(3, salary);
				
		int rows=ps.executeUpdate();
		if(rows>0) {
			System.out.println("\nDate added successfullly!");
		}
		else {
			System.out.println("\nFailed to add data.");
		}
	}
}