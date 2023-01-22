package UseCases;

import Model.Department;
import Model.Employee;
import Model.Leave;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Exception.InvalidDepartment;
import Exception.PasswordMismatch;

public class EmployeeUseCase extends Employee{
	try {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection conn = DriverManager.getConnection("jdbc:mysql://hostname:3306/hr_management", "root", "1432");
	} catch (ClassNotFoundException ex) {
	    ex.printStackTrace();
	} catch (SQLException e1) {
	    e1.printStackTrace();
	}
	public void viewProfile() {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	        conn = DriverManager.getConnection("jdbc:mysql://hostname:3306/hr_management", "root", "1432");
	        stmt = conn.prepareStatement("SELECT name, id, department, contact FROM employees WHERE id = ?");
	        stmt.setInt(1, this.getId());
	        rs = stmt.executeQuery();
	        while(rs.next()) {
	            System.out.println("Name: " + rs.getString("name"));
	            System.out.println("ID: " + rs.getInt("id"));
	            System.out.println("Department: " + rs.getString("department"));
	            System.out.println("Contact: " + rs.getString("contact"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	public void registerEmployee(Employee e, Department d, String password) {
	    String insertEmployee = "INSERT INTO employee (name, id, department_id, contact) VALUES (?, ?, ?, ?)";
	    String insertPassword = "INSERT INTO password (employee_id, password) VALUES (?, ?)";
	    try {
	        Connection conn;
			PreparedStatement stmt = conn.prepareStatement(insertPassword);
	        stmt.setInt(1, e.getId());
	        stmt.setString(2, password);
	        stmt.executeUpdate();
	        } catch (SQLException ex) {
	            // handle exception
	        }
	    }
	public void updateProfile(String name, String contact) {
	    String query = "UPDATE employee SET name = ?, contact = ? WHERE id = ?";
	    try {
	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setString(1, name);
	        stmt.setString(2, contact);
	        stmt.setInt(3, getId());
	        stmt.executeUpdate();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	public void changePassword(String oldPassword, String newPassword) throws PasswordMismatch {
	    String query = "UPDATE employees SET password = ? WHERE id = ?";
	    try {
	        Connection conn;
			PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setString(1, newPassword);
	        stmt.setInt(2, getId());
	        int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected == 1) {
	            this.password = newPassword;
	            System.out.println("Password changed successfully");
	        } else {
	            System.out.println("Error changing password");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void transferEmployee(Employee e, Department d) throws InvalidDepartment {
	    // check if department exists
	    boolean isValidDepartment = checkDepartment(d);
	    if (!isValidDepartment) {
	        throw new InvalidDepartment("The department provided does not exist");
	    }
	    String query = "UPDATE employees SET department_id = ? WHERE id = ?";
	    try {
	        Connection conn;
			PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setInt(1, d.getId());
	        stmt.setInt(2, e.getId());
	        int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected == 1) {
	            e.setDepartment(d);
	            System.out.println("Employee transferred successfully");
	        } else {
	            System.out.println("Error transferring employee");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	private boolean checkDepartment(Department d) {
	    String query = "SELECT id FROM departments WHERE id = ?";
	    try {
	        Connection conn;
			PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setInt(1, d.getId());
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return true;
	        } else {
	            return false;
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return false;
	    }
	}

	public void approveLeave(Leave l, boolean approve) {
	    String status = approve ? "approved" : "denied";
	    String query = "UPDATE leave_request SET status = ? WHERE id = ?";
	    try {
	        Connection conn;
			PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setString(1, status);
	        stmt.setInt(2, l.getId());
	        stmt.executeUpdate();
	    } catch (SQLException ex) {
	        // handle exception
	    }
	}

	public void createDepartment(String name) {
	    String query = "INSERT INTO department (name) VALUES (?)";
	    try {
	        Connection conn;
			PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setString(1, name);
	        stmt.executeUpdate();
	    } catch (SQLException ex) {
	        // handle exception
	    }
	}

}
