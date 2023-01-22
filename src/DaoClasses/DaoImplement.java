package DaoClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import CustomColour.ConsoleColors;
import Model.Department;
import Model.Employee;
import Model.EmployeeLeave;


public class DaoImplement implements DaoInterfaces{
	 Scanner sc = new Scanner(System.in);
	@Override
	public String regEmployee(String name, String password , int dept_id) {
	
		String msg = ConsoleColors.RED_BOLD +"Insertion Unsuccessful...";
		
		
		try(Connection con = DaoConnection.provideConnection()){
			PreparedStatement ps = con.prepareStatement("select * from department where dept_id = ? ");
			ps.setInt(1, dept_id);
			
			ResultSet rs1 = ps.executeQuery();
			
			if(rs1.next()) {
			
			
			PreparedStatement ps = con.prepareStatement("insert into Employee(emp_name,emp_password,emp_dept_id) value(?,?,?)");
			ps.setString(1, name);
			ps.setString(2,password);
			ps.setInt(3, dept_id);
			
			
			
			int x = ps.executeUpdate();
			if(x>0) 
				msg = ConsoleColors.GREEN_BOLD +"Insertion Successful....";
			}else {
				msg = ConsoleColors.RED_BOLD+"Department Id Not Found";
			}
			
		}catch(SQLException e) {
			msg =  e.getMessage();
		}
		
		
		
		return msg;
	}

	@Override
	public String updateEmpName(String name, String password,String newName) {
		String msg = ConsoleColors.RED_BOLD +"Name Changed Unsuccessful...";
		
		
		try(Connection con = DaoConnection.provideConnection()){
			
			PreparedStatement ps = con.prepareStatement("select * from Employee where emp_name = ? and emp_password = ?");
			ps.setString(1, name);
			ps.setString(2,password);
			
			
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			
		 		
				PreparedStatement ps1 = con.prepareStatement("update Employee set emp_name = ? where emp_password = ?");
				ps1.setString(1, newName);
				ps1.setString(2, password);
				
				int x1  = ps1.executeUpdate();
				if(x1 > 0)
					msg = ConsoleColors.GREEN_BOLD +"Name Changed Successfully";	
			}else {
				msg = ConsoleColors.RED_BOLD +"Employee Not Registered Or Credentials Mismatch";
			}
			
			
			
		}catch(SQLException e) {
			msg = e.getMessage();
		}
		
		
		
		return msg;
	}

	@Override
	public String updateEmpPassword(String name, String password,String newPassword) {
	String msg = ConsoleColors.RED_BOLD +"Password Changed Unsuccessful...";
		
		
		try(Connection con = DaoConnection.provideConnection()){
			
			PreparedStatement ps = con.prepareStatement("select * from Employee where emp_name = ? and emp_password = ?");
			ps.setString(1, name);
			ps.setString(2,password);
			
			
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
				PreparedStatement ps = con.prepareStatement("update Employee set emp_password = ? where emp_password = ?");
				ps.setString(1, newPassword);
				ps.setString(2, password);
				
				int x1  = ps.executeUpdate();
				if(x1 > 0)
					msg = ConsoleColors.GREEN_BOLD +"Password Changed Successfully";	
			}else {
				msg = ConsoleColors.RED_BOLD +"Employee Not Registered Or Credentials Mismatch";
			}
			
			
			
		}catch(SQLException e) {
			msg = e.getMessage();
		}
		
		
		
		return msg;
	}



	@Override
	public Employee viewEmployee(String name, String password) {

		Employee  emp = null;
		
		
		try(Connection con = DaoConnection.provideConnection()){
			
			PreparedStatement ps = con.prepareStatement("select emp_id,emp_name,emp_password,emp_dept_id, dept_name from employee inner join department on employee.emp_dept_id = department.dept_id where employee.emp_password =?");
			ps.setString(1,password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			emp = new Employee( rs.getString("emp_name"),rs.getString("emp_password"), rs.getInt("emp_id"), rs.getInt("emp_dept_id"),rs.getString("dept_name"));
			
			}
			
		}catch(SQLException e) {
		    e.getMessage();
		}
		
		
		
		return emp;
	}

	@Override
	public String addNewDept() {
String msg = ConsoleColors.RED_BOLD +"Department Insertion Unsuccessful...";
		
		
		try(Connection con = DaoConnection.provideConnection()){
			
				  Scanner sc = new Scanner(System.in);
				  System.out.println(ConsoleColors.WHITE_UNDERLINED +"Enter Detarment ID");
				  int dept_id  = sc.nextInt();
				  
				  System.out.println(ConsoleColors.WHITE_UNDERLINED +"Enter Department  Name");
				  String dept_name = sc.next();
				  PreparedStatement ps = con.prepareStatement("insert into Department values(?,?)");
				  ps.setInt(1, dept_id);
				  ps.setString(2, dept_name);
				
				 int x1  = ps.executeUpdate();
				 if(x1 > 0)
					msg = ConsoleColors.GREEN_BOLD +"Department inserted Successfully";	
				
				
			}catch(SQLException e) {
				msg = e.getMessage();

		}
		
		
		
		return msg;

	}

	@Override
	public List<Department>  viewDept() {
        List<Department> dept = new ArrayList<>();
		
		
		try(Connection con = DaoConnection.provideConnection()){
			
			
				PreparedStatement ps = con.prepareStatement("select * from Department ");
				ResultSet rs1 = ps.executeQuery();
				while(rs1.next()) {
				Department d = new Department( rs1.getInt("dept_id"),rs1.getString("dept_name"));
			    dept.add(d);
				}
			
			}catch(SQLException e) {
			 e.getMessage();
		}
		
		
		
		return dept;
	}

	@Override
	public String updateDept(int dept_id) {
		
        String msg = ConsoleColors.RED_BOLD +"Department Updation Unsuccessful...";
		
		
		try(Connection con = DaoConnection.provideConnection()){
			
			PreparedStatement ps = con.prepareStatement("select * from Department where dept_id = ?");
			ps.setInt(1, dept_id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println(ConsoleColors.WHITE_UNDERLINED +"Enter New Department Name:");
				String dept_name  = sc.next();
				PreparedStatement ps = con.prepareStatement("update Department set dept_name  = ? where dept_id = ?");
				ps.setString(1, dept_name);
				ps.setInt(2, dept_id);
				
				int x1  = ps.executeUpdate();
				if(x1 > 0)
					msg = ConsoleColors.GREEN_BOLD +"Department Updated Successfully...";	
			}else {
				msg = ConsoleColors.RED_BOLD +"Department Not Found";
			}
			
			
			
		}catch(SQLException e) {
			 msg = e.getMessage();
		}
		
		
		
		return msg;
	}

	@Override
	public String transferDept(int emp_id, int dept_id) {
	
		 String msg = ConsoleColors.RED_BOLD +"Employee Transfer  Unsuccessful...";
			
			
			try(Connection con = DaoConnection.provideConnection()){
					PreparedStatement ps = con.prepareStatement("update Employee set emp_dept_id  = ? where emp_id = ?");
					ps.setInt(1,dept_id);
					ps.setInt(2, emp_id);
					
					int x1  = ps.executeUpdate();
					if(x1 > 0) {
						msg = ConsoleColors.GREEN_BOLD +"Department Updated Successfully...";	
					}else {
						msg =ConsoleColors.RED_BOLD + "Department ID or Employee Id Mismatch";
					}
				}catch(SQLException e) {
				 msg = e.getMessage();
			}
			
			return msg;
	}

	@Override
	public String leaveApply( String password) {
		 
		String msg = ConsoleColors.RED_BOLD +"Leave Apply Unsuccessful...";
			
			
			try(Connection con = DaoConnection.provideConnection()){
				
				PreparedStatement ps = con.prepareStatement("select * from Employee where  emp_password = ? ");
		
				ps.setString(1,password);
				
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					
					  Scanner sc = new Scanner(System.in);
					  System.out.println(ConsoleColors.WHITE_UNDERLINED +"Enter Number Of Days For Leave");
					  String leave_day = sc.next();
					PreparedStatement ps = con.prepareStatement("insert into leaveapllication(leave_day,emp_id) values(?,?)");
					ps.setString(1,leave_day);
					ps.setInt(2, rs.getInt("emp_id"));
					
					int x1  = ps.executeUpdate();
					if(x1 > 0)
						msg = ConsoleColors.GREEN_BOLD +"Leave Applied Successfully...";
					else {
						msg = ConsoleColors.RED_BOLD +"Not Eligible For Leave";
					}
	
				}else {
					msg = ConsoleColors.RED_BOLD +"Credentials Mismatch";
				}
					
				
			}catch(SQLException e) {
				 msg = e.getMessage();
			}
			
			return msg;
	}

	@Override
	public List<EmployeeLeave> viewEmpLeave() {
		List<EmployeeLeave> empLeave = new ArrayList<>();
		
		
		
		try(Connection con = DaoConnection.provideConnection()){
			
				PreparedStatement ps = con.prepareStatement("select employee.emp_id, employee.emp_name,leaveApllication.leave_day,leaveApllication.status from employee inner join leaveApllication on employee.emp_id = leaveApllication.emp_id;");				
			    
				ResultSet rs1 = ps.executeQuery();
				while(rs1.next()) {
					empLeave.add(new EmployeeLeave(rs1.getInt("emp_id"),rs1.getString("emp_name"), rs1.getString("leave_day"), rs1.getString("status")));
				}
			}catch(SQLException e) {
			 e.getMessage();
		}
		
		return empLeave;
	}

	@Override
	public String leavePermit( int emp_id, String Status) {
		String msg = ConsoleColors.RED_BOLD +"Error in Processing";
		try(Connection con = DaoConnection.provideConnection()){
	
				PreparedStatement ps = con.prepareStatement("update leaveApllication set status = ? where emp_id = ?  ");
				ps.setString(1,Status);
				ps.setInt(2,emp_id );
				
				int x = ps.executeUpdate();
				if(x > 0) {
					msg =ConsoleColors.GREEN_BOLD + "Leave updated";
				}else{
					msg = ConsoleColors.RED_BOLD +"No leave Application Found";
				}
			}catch(SQLException e) {
			msg = e.getMessage();
		}
		
		
		return msg;
	}

	@Override
	public EmployeeLeave getLeaveStatusById(String password) {

		EmployeeLeave empLeave = null;


		try(Connection con = DaoConnection.provideConnection()){
			PreparedStatement ps = con.prepareStatement("select * from  Employee where  emp_password = ?  ");
			
			ps.setString(1,password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				PreparedStatement ps = con.prepareStatement("select employee.emp_id, employee.emp_name, leaveApllication.leave_day,leaveApllication.status from employee inner join leaveApllication on employee.emp_id = leaveApllication.emp_id where employee.emp_id = ? ;");				
			    ps.setInt(1, rs.getInt("emp_id"));
				ResultSet rs1 = ps.executeQuery();
				while(rs1.next()) {
					empLeave = new EmployeeLeave(rs1.getInt("emp_id"),rs1.getString("emp_name"), rs1.getString("leave_day"), rs1.getString("status"));
				}
			
			}			
		}catch(SQLException e) {
			e.getMessage();
		}
		
		return empLeave;
		
	}

	@Override
	public int validateAdmin(String admin_name) {
		int number = 0;
		
		try(Connection con = DaoConnection.provideConnection()){
			PreparedStatement ps = con.prepareStatement("select * from  admin where admin_name = ?");
			ps.setString(1,admin_name);
	
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {			   
				    System.out.println(ConsoleColors.WHITE_UNDERLINED +"Please Enter Your Password");
				    String admin_password = sc.next();
				    
				    
					PreparedStatement ps = con.prepareStatement("select * from  admin where admin_password = ?");
					ps.setString(1,admin_password);

					
					ResultSet rs1 = ps.executeQuery();
					if(rs1.next()) {
					   number = 678;
					}else {
					
						number = 345;
					}
					
			}else {
			
				number = 123;
			}
			
			
			
			
		}catch(SQLException e) {
			e.getMessage();
		}
		
	
		return number;
	}

	@Override
	public List<Employee> getAllEmp() {
		List<Employee> emp = new ArrayList<>();

		try(Connection con = DaoConnection.provideConnection()){
			
				PreparedStatement ps = con.prepareStatement("select emp_id,emp_name,emp_password,emp_dept_id, dept_name from employee inner join department on employee.emp_dept_id = department.dept_id");				
				
			
				ResultSet rs1 = ps.executeQuery();
				

				while(rs1.next()) {

					   emp.add(new Employee(rs1.getString("emp_name"),rs1.getString("emp_password"), rs1.getInt("emp_id"), rs1.getInt("emp_dept_id"),rs1.getString("dept_name")));;
					   
				}
			}catch(SQLException e) {
			 e.getMessage();
		}
		
		
		return emp;
	}

	@Override
	public int validateEmployee(String emp_name) {
int number = 0;
		
		try(Connection con = DaoConnection.provideConnection()){
			PreparedStatement ps = con.prepareStatement("select * from  employee where emp_name = ?");
			ps.setString(1,emp_name);
	
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {			   
				    System.out.println(ConsoleColors.WHITE_UNDERLINED +"Please Confirm Your Password");
				    String admin_password = sc.next();
				    
				    
					PreparedStatement ps = con.prepareStatement("select * from  employee where emp_password = ?");
					ps.setString(1,admin_password);

					
					ResultSet rs1 = ps.executeQuery();
					if(rs1.next()) {
					   number = 678;
					}else {
					
						number = 345;
					}
					
			}else {
			
				number = 123;
			}
			
			
			
			
		}catch(SQLException e) {
			e.getMessage();
		}
		
	
		return number;
	}
}
