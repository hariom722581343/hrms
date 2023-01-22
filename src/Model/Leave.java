package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Leave {
    private int id;
    private int empId;
    private String startDate;
    private String endDate;
    private String reason;
    private String status;

    // constructor, getters and setters for id, empId, startDate, endDate, reason and status

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

	/**
	 * @param id
	 * @param empId
	 * @param startDate
	 * @param endDate
	 * @param reason
	 * @param status
	 */
	public Leave(int id, int empId, String startDate, String endDate, String reason, String status) {
		this.id = id;
		this.empId = empId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.status = status;
	}

	/**
	 * 
	 */
	public Leave() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Leave(String startDate, String endDate) {
	    this.startDate = startDate;
	    this.endDate = endDate;
	}

	public void applyLeave(Employee employee, String reason) {
	    String query = "INSERT INTO leaves (employee_id, start_date, end_date, reason) VALUES (?,?,?,?)";
	    try {
	        Connection conn = null;
			PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setInt(1, employee.getId());
	        stmt.setString(2, this.startDate);
	        stmt.setString(3, this.endDate);
	        stmt.setString(4, reason);
	        int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected == 1) {
	            System.out.println("Leave applied successfully");
	        } else {
	            System.out.println("Error applying leave");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Leave [id=" + id + ", empId=" + empId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", reason=" + reason + ", status=" + status + "]";
	}
    
}

