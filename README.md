
<h1>Human Resource Management System</h1>
Efficiently manage your organization's workforce with our HRMS-Clone - the ultimate solution for Human Resource Management.
 
# The Basic Prerequisites of this Applications are.
- [x] Should Have a MySQL Server and a Database Created.
- [x] Should have created following tables into the database WITh   SAME DATA TYPES.


- [X] TABLE DEPARTMENT


| Field     | Type        | Null | Key | Default | Extra          |
|-----------|-------------|------|-----|---------|----------------|
| dept_id   | int         | NO   | PRI | NULL    | auto_increment |
| dept_name | varchar(20) | NO   | UNI | NULL    |                |



<hr />

- [X] TABLE ADMIN


| Field         | Type        | Null | Key | Default | Extra          |
|---------------|-------------|------|-----|---------|----------------|
| admin_name    | varchar(256) | NO   |     | NULL    |                |




<hr />

- [x] TABLE EMPLOYEE


| Field       | Type        | Null | Key | Default           | Extra             |
|-------------|-------------|------|-----|-------------------|-------------------|
| emp_id      | int         | NO   | PRI | NULL              | auto_increment    |
| emp_name    | varchar(30) | NO   |     | NULL              |                   |
| emp_pass    | varchar(8)  | NO   | UNI | NULL              |                   |
| emp_dept_id | int         | YES  | MUL | NULL              |                   |
| emp_login   | datetime    | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |




<hr />

- [x] TABLE LEAVE_APPLICATION


| Field     | Type        | Null | Key | Default | Extra |
|-----------|-------------|------|-----|---------|-------|
| emp_id    | int         | YES  | UNI | NULL    |       |
| leave_day | varchar(10) | YES  |     | NULL    |       |
| status    | varchar(10) | YES  |     | Pending |       |



<hr />



# Description :-
Software To Monitor Your Employees details, Leave Status & Department Details.



<h3>Admin Roles are:</h3>

•	Admin can add new Departments.
</br>
•	Admin can view and update the Departments.
</br>
•	Admin can register new Employees and give them their password.
</br>
•	Admin can also transfer them to other departments.
</br>
•	Admin can grant or deny employee leave request.
</br>



<h3>Employee Roles are:</h3>
</br>
•	Employee can view and update his profile.
</br>
•	Employee can also change his password.
</br>
•	Employee can also request for leaves.
</br>


# Tech stack and Tools used 

- Java
- MySQL
- Git & GitHub




# Thank you for reading..
