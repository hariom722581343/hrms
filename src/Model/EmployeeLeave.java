package Model;

public class EmployeeLeave {
	private int e_id;
	private String emp_name;
	private String leave_day;
	private String status;

	public int gete_id() {
		return e_id;
	}

	public void sete_id(int e_id) {
		this.e_id = e_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getLeave_day() {
		return leave_day;
	}

	public void setLeave_day(String leave_day) {
		this.leave_day = leave_day;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Leave Details [e_id=" + e_id + ", emp_name=" + emp_name + ", leave_day=" + leave_day + ", status="
				+ status + "]" ;
	}
	
		public EmployeeLeave(int e_id, String emp_name, String leave_day, String status) {
		super();
		this.e_id = e_id;
		this.emp_name = emp_name;
		this.leave_day = leave_day;
		this.status = status;
	}

}

//
