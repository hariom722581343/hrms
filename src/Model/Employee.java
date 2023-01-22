package Model;

import java.util.Date;
import java.util.List;

import Exception.InvalidLeaveRequest;

public class Employee {
    private String name;
    private int id;
    private Department department;
    private String contact;
    private List<Leave> leaves;

    //getters and setters
	/**
	 * @param name
	 * @param id
	 * @param department
	 * @param contact
	 */
	public Employee(String name, int id, Department department, String contact) {
		this.name = name;
		this.id = id;
		this.department = department;
		this.contact = contact;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * 
	 */
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", id=" + id + ", contact=" + contact + "]";
	}

	    //getters and setters

	    public void updateProfile(String name, String contact) {
	        this.name = name;
	        this.contact = contact;
	    }

	    public void applyLeave(Leave l) throws InvalidLeaveRequest {
	        // check if leave request is valid
	        if (l.getStartDate().before(new Date()) || l.getEndDate().before(new Date())) {
	            throw new InvalidLeaveRequest("Start or end date cannot be before today.");
	        }
	        if (l.getStartDate().after(l.getEndDate())) {
	            throw new InvalidLeaveRequest("End date cannot be before start date.");
	        }
	        if (hasOverlappingLeave(l)) {
	            throw new InvalidLeaveRequest("Overlapping leave.");
	        }
	        // if not, throw InvalidLeaveRequest
	        leaves.add(l);
	    }

	    private boolean hasOverlappingLeave(Leave l) {
	        for (Leave leave : leaves) {
	            if ((l.getStartDate().after(leave.getStartDate()) && l.getStartDate().before(leave.getEndDate()))
	                    || (l.getEndDate().after(leave.getStartDate()) && l.getEndDate().before(leave.getEndDate()))) {
	                return true;
	            }
	        }
	        return false;
	    }
	}

