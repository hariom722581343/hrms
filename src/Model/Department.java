package Model;

import java.util.List;

public class Department {
    private String name;
    private int id;
    private List<Employee> employees;
    //getters and setters
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
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	/**
	 * @param name
	 * @param id
	 * @param employees
	 */
	public Department(String name, int id, List<Employee> employees) {
		this.name = name;
		this.id = id;
		this.employees = employees;
	}
	/**
	 * 
	 */
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Department [name=" + name + ", id=" + id + "]";
	}
    
}

