package UseCases;

import Model.Admin;
import Model.Department;
import Model.Employee;
import Model.HRManager;
import Model.Leave;

public class AdminUseCase extends Admin{
	    private HRManager hrManager;
	    public void Admin(HRManager hrManager) {
	        this.hrManager = hrManager;
	    }

	    public void createDepartment(String name) {
	        hrManager.createDepartment(name);
	    }

	    public void updateDepartment(int id, String name) {
	        Department department = hrManager.getDepartment(id);
	        if (department != null) {
	            department.setName(name);
	            hrManager.updateDepartment(department);
	        } else {
	            throw new IllegalArgumentException("Department not found.");
	        }
	    }

	    public void registerEmployee(String name, int deptId, String password) {
	        Employee employee = new Employee(name, deptId, password);
	        hrManager.registerEmployee(employee);
	    }

	    public void transferEmployee(int empId, int deptId) {
	        Employee employee = hrManager.getEmployee(empId);
	        if (employee != null) {
	            hrManager.assignDepartment(employee, deptId);
	        } else {
	            throw new IllegalArgumentException("Employee not found.");
	        }
	    }

	    public void approveLeave(int empId) {
	        Employee employee = hrManager.getEmployee(empId);
	        if (employee != null) {
	            Leave leave = employee.getPendingLeave();
	            if (leave != null) {
	                hrManager.approveLeave(employee, leave);
	            } else {
	                throw new IllegalArgumentException("No pending leave requests.");
	            }
	        } else {
	            throw new IllegalArgumentException("Employee not found.");
	        }
	    }

	    public void denyLeave(int empId) {
	        Employee employee = hrManager.getEmployee(empId);
	        if (employee != null) {
	            Leave leave = employee.getPendingLeave();
	            if (leave != null) {
	                hrManager.denyLeave(employee, leave);
	        } else {
	            throw new IllegalArgumentException("Employee not found.");
	        }
	    }
	}
	    
}

