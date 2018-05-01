# Academy

A simple web application for enrolling and withdrawing students into/from classes. 

### Dependencies

* Java
* Mysql, jdbc
* Spring boot
* Maven

### Examples

List students:
```
http://localhost:8080/students
```

List classes:
```
http://localhost:8080/classes
```

List enrollments:
```
http://localhost:8080/enrolled
```

Get student info by id (id=`403c3a65-903e-3603-8477-457a0326bf15`):
```
http://localhost:8080/student?id=403c3a65-903e-3603-8477-457a0326bf15
```

Search courses by pattern:
```
http://localhost:8080/search?pattern=math
```

Enroll student (`88bfd3e8-1d13-3fb5-9928-e0b916ee2c9b`) to a class (`2363ebcd-d984-3556-b3d0-8534c5c250c8`):
```
http://localhost:8080/enroll?studentId=88bfd3e8-1d13-3fb5-9928-e0b916ee2c9b&classId=2363ebcd-d984-3556-b3d0-8534c5c250c8
```

Withdraw student (`88bfd3e8-1d13-3fb5-9928-e0b916ee2c9b`) from a class (`2363ebcd-d984-3556-b3d0-8534c5c250c8`):
```
http://localhost:8080/withdraw?studentId=88bfd3e8-1d13-3fb5-9928-e0b916ee2c9b&classId=2363ebcd-d984-3556-b3d0-8534c5c250c8
```
