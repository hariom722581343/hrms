package Model;

import java.util.Scanner;

import Exception.InvalidLeaveRequest;

public class HRMS {
    private static HRManager hrManager = new HRManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the HR Management System");
            System.out.println("1. Admin");
            System.out.println("2. Employee");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                displayAdminOptions();
            } else if (choice == 2) {
                displayEmployeeOptions();
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayAdminOptions() {
        while (true) {
            System.out.println("Admin options:");
            System.out.println("1. Add new department");
            System.out.println("2. View and update department");
            System.out.println("3. Register new employee and assign password");
            System.out.println("4. Transfer employee to other department");
            System.out.println("5. Grant or deny leave request");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter department name: ");
                String name = scanner.nextLine();
                hrManager.createDepartment(name);
                System.out.println("Department added successfully.");
            } else if (choice == 2) {
                System.out.print("Enter department id: ");
				int id = scanner.nextInt();
				scanner.nextLine();
				Department department = hrManager.getDepartment(id);
				if (department != null) {
				System.out.print("Enter new name: ");
				String name = scanner.nextLine();
				department.setName(name);
				hrManager.updateDepartment(department);
				System.out.println("Department updated successfully.");
				} else {
				System.out.println("Department not found.");
				}
				} else if (choice == 3) {
				System.out.print("Enter employee name: ");
				String name = scanner.nextLine();
				System.out.print("Enter department id: ");
				int deptId = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter employee password: ");
				String password = scanner.nextLine();
				Employee employee = new Employee(name, deptId, password);
				hrManager.registerEmployee(employee);
				System.out.println("Employee registered successfully.");
				} else if (choice == 4) {
				System.out.print("Enter employee id: ");
				int empId = scanner.nextInt();
                scanner.nextLine();
                Employee employee = hrManager.getEmployee(empId);
                if (employee != null) {
                    System.out.print("Enter new department id: ");
                    int deptId = scanner.nextInt();
                    scanner.nextLine();
                    hrManager.assignDepartment(employee, deptId);
                    System.out.println("Employee transferred successfully.");
                } else {
                    System.out.println("Employee not found.");
                }
            } else if (choice == 5) {
                System.out.print("Enter employee id: ");
                int empId = scanner.nextInt();
                scanner.nextLine();
                Employee employee = hrManager.getEmployee(empId);
                if (employee != null) {
                    Leave leave = employee.getPendingLeave();
                    if (leave != null) {
                        System.out.print("Approve leave? (y/n): ");
                        String approve = scanner.nextLine();
                        if (approve.equalsIgnoreCase("y")) {
                            hrManager.approveLeave(employee, leave);
                            System.out.println("Leave approved.");
                        } else {
                            hrManager.denyLeave(employee, leave);
                            System.out.println("Leave denied.");
                        }
                    } else {
                        System.out.println("No pending leave requests.");
                    }
                } else {
                    System.out.println("Employee not found.");
                }
            } else if (choice == 6) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayEmployeeOptions() {
        System.out.print("Enter employee id: ");
        int empId = scanner.nextInt();
        scanner.nextLine();
        Employee employee = hrManager.getEmployee(empId);
        if (employee != null) {
            while (true) {
                System.out.println("Employee options:");
                System.out.println("1. View profile");
                System.out.println("2. Update profile");
                System.out.println("3. Change password");
                System.out.println("4. Request leave");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    System.out.println(employee);
                } else if (choice == 2) {
                    System.out.print("Enter new name: ");
                    String name = scanner.nextLine();
                    employee.setName(name);
                    hrManager.updateEmployee(employee);
                    System.out.println("Profile updated successfully.");
                } else if (choice == 3) {
                    System.out.print("Enter new password: ");
                    String password = scanner.nextLine();
                    employee.setPassword(password);
                    System.out.println("Password changed successfully.");
                } else if (choice == 4) {
                    System.out.print("Enter start date (yyyy-MM-dd): ");
                    String startDate = scanner.nextLine();
                    System.out.print("Enter end date (yyyy-MM-dd): ");
                    String endDate = scanner.nextLine();
                    try {
                        hrManager.applyLeave(employee, new Leave(startDate, endDate));
                        System.out.println("Leave request submitted successfully.");
                    } catch (InvalidLeaveRequest e) {
                        System.out.println(e.getMessage());
                    }
                } else if (choice == 5) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Employee not found.");
        }
    }


}
