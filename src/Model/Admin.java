package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Admin {
    private Connection conn;

    public Admin(Connection conn) {
        this.conn = conn;
    }

    public void addDepartment(Department department) throws SQLException {
        String sql = "INSERT INTO department (dept_id, dept_name) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, department.getDept_id());
            stmt.setString(2, department.getDept_name());
            stmt.executeUpdate();
        }
    }

    public List<Department> getAllDepartments() throws SQLException {
        String sql = "SELECT dept_id, dept_name FROM department";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            List<Department> departments = new ArrayList<>();
            while (rs.next()) {
                int dept_id = rs.getInt("dept_id");
                String dept_name = rs.getString("dept_name");
                departments.add(new Department(dept_id, dept_name));
            }
            return departments;
        }
    }

    public void registerEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee (name, pass, emp_id, dept_id, dept_name) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getPass());
            stmt.setInt(3, employee.getEmp_id());
            stmt.setInt(4, employee.getDept_id());
            stmt.setString(5, employee.getDept_name());
            stmt.executeUpdate();
            }
        }
   public void transferEmployee(Employee employee, int newDeptId) throws SQLException {
    String sql = "UPDATE employee SET dept_id = ? WHERE emp_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, newDeptId);
        stmt.setInt(2, employee.getEmp_id());
        stmt.executeUpdate();
    }
   }

    public List<EmployeeLeave> getAllLeaveDetails() throws SQLException {
    String sql = "SELECT emp_id, emp_name, leave_day, status FROM employee_leave";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        ResultSet rs = stmt.executeQuery();
        List<EmployeeLeave> leaves = new ArrayList<>();
        while (rs.next()) {
            int emp_id = rs.getInt("emp_id");
            String emp_name = rs.getString("emp_name");
            String leave_day = rs.getString("leave_day");
            String status = rs.getString("status");
            leaves.add(new EmployeeLeave(emp_id, emp_name, leave_day, status));
        }
        return leaves;
    }
    }

    public void updateLeaveStatus(int empId, String status) throws SQLException {
    String sql = "UPDATE employee_leave SET status = ? WHERE emp_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, status);
        stmt.setInt(2, empId);
        stmt.executeUpdate();
    }
    }

    public void updateDepartment(Department department) throws SQLException {
    String sql = "UPDATE department SET dept_name = ? WHERE dept_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, department.getDept_name());
        stmt.setInt(2, department.getDept_id());
        stmt.executeUpdate();
    }
    }

    public List<Employee> getAllEmployeeDetails() throws SQLException {
        String sql = "SELECT name, emp_id, dept_id, dept_name FROM employee";
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String pass = rs.getString("pass");
                int emp_id = rs.getInt("emp_id");
                int dept_id = rs.getInt("dept_id");
                String dept_name = rs.getString("dept_name");
                Employee employee = new Employee(name, pass, emp_id, dept_id, dept_name);
                employees.add(employee);
            }
        }
        return employees;
    }


    public void deleteDepartment(int deptId) throws SQLException {
        String sql = "DELETE FROM department WHERE dept_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, deptId);
            stmt.executeUpdate();
        }
    }

        public void deleteEmployee(int empId) throws SQLException {
        String sql = "DELETE FROM employee WHERE emp_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, empId);
        stmt.executeUpdate();
        }
        }
}

