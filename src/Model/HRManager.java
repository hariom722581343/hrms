package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Exception.InvalidLeaveRequest;

public class HRManager {
    private Connection conn;

    public HRManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://hostname:3306/hr_management","root","1432");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void registerEmployee(Employee e, Department d) {
        //code to register employee and assign to specified department
        String query = "INSERT INTO employee (name, id, department_id, contact) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, e.getName());
            stmt.setInt(2, e.getId());
            stmt.setInt(3, d.getId());
            stmt.setString(4, e.getContact());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            // handle exception
        }
    }

    public void modifyEmployee(Employee e) {
        //code to edit employee information
        String query = "UPDATE employee SET name = ?, contact = ? WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, e.getName());
            stmt.setString(2, e.getContact());
            stmt.setInt(3, e.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            // handle exception
        }
    }

    public void applyLeave(Employee e, Leave l) throws InvalidLeaveRequest {
        String query = "INSERT INTO leave_request (employee_id, start_date, end_date, status) VALUES (?, ?, ?, 'pending')";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, e.getId());
            stmt.setDate(2, l.getStartDate());
            stmt.setDate(3, l.getEndDate());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            // handle exception
        }
    }

    public void approveLeave(Employee e, Leave l) {
        //code to approve or deny leave request
        String query = "UPDATE leave_request SET status = ? WHERE employee_id = ? AND start_date = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, l.getStatus());
            stmt.setInt(2, e.getId());
            stmt.setDate(3, l.getStartDate());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            // handle exception
        }
    }

    public void assignDepartment(Employee e, Department d) {
        //code to assign employee to specified department
        String query = "UPDATE employee SET department_id = ? WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, d.getId());
            stmt.setInt(2, e.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            // handle exception
        }
    }

    public void createDepartment(String name) {
        String query = "INSERT INTO departments (name) VALUES (?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Department created successfully");
            } else {
                System.out.println("Error creating department");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateDepartment(Department department) {
        String query = "UPDATE departments SET name = ? WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, department.getName());
            stmt.setInt(2, department.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Department updated successfully");
            } else {
                System.out.println("Error updating department");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Department getDepartment(int id) {
        String query = "SELECT * FROM departments WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int departmentId = rs.getInt("id");
                String departmentName = rs.getString("name");
             } else {
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
		return null;
    }
    
    public Department getDepartment(int id) {
        String query = "SELECT * FROM departments WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int departmentId = rs.getInt("id");
                String departmentName = rs.getString("name");
                return new Department(departmentId, departmentName);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void denyLeave(Employee employee, Leave leave) {
        String query = "UPDATE leaves SET status = 'denied' WHERE employee_id = ? AND start_date = ? AND end_date = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employee.getId());
            stmt.setString(2, leave.getStartDate());
            stmt.setString(3, leave.getEndDate());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Leave denied successfully");
            } else {
                System.out.println("Error denying leave");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }





}
