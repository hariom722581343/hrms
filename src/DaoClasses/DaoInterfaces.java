package DaoClasses;

import java.util.List;

import Model.Department;
import Model.Employee;
import Model.EmployeeLeave;

public interface DaoInterfaces {
public String regEmployee(String name, String password ,int dept_id);
public String updateEmpName(String name, String password,String newName);
public String updateEmpPass(String name, String password, String newPassword);
public Employee viewEmployee(String name, String password);
public String addNewDept();
public List<Department>  viewDept();
public String updateDept(int dept_id);
public String transferDept(int emp_id, int dept_id);
public String leaveApply( String password);
public List<EmployeeLeave> viewEmpLeave();
public String leavePermit(int emp_id, String Status);
public EmployeeLeave getLeaveStatusById( String password);
public int validateAdmin(String admin_name);
public List<Employee> getAllEmp();
public int validateEmployee(String emp_name);
public String updateEmpPassword(String name, String password, String newPassword);
}
