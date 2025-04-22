select * from cat;
-----------------student-----------
create table student(
s_id number,
s_name varchar2(50)not null,
birth data,
s_phone number(15),
gender varchar2(6),
address varchar2(50),
constraint pk_stu primary key (s_id)
);
---------------------student famliy-----------
create table student_famliy(
f_id number,
f_name varchar2(50)not null,
f_relation varchar2(10)not null,
f_phone number(15),
gender varchar2(6),
address varchar2(50),
student_id number,
constraint pk_famliy primary key (f_id),
constraint fk_famliy foreign key (student_id) references student(s_id)
);
-----------------------employees-------------------
create table employees(
e_id number(4),
e_name varchar2(50)not null,
e_phone number(15),
birth date,
job varchar2(12),
sal number,
address varchar2(50),
constraint pk_emp primary key (e_id)
);
-----------------------department-------------------
create table department(
d_id number(4),
d_name varchar2(50)not null,
constraint pk_dept primary key (d_id)
);
-----------------------workin-------------------
create table work_in(
emp_id number,
dept_id number,
job varchar2(50)not null,
constraint pk_work primary key (emp_id,dept_id,job),
constraint fk_work_emp foreign key (emp_id) references employees(e_id),
constraint fk_work_dept foreign key (dept_id) references department(d_id)
);
---------------------------teacher-------------------------------
create table teacher(
t_id number(4),
t_name varchar2(50)not null,
t_phone number(15),
sal number,
address varchar2(50),
shift_start number,
shift_end number,
emp_id number,
constraint pk_teach primary key (t_id),
constraint fk_teach_emp foreign key (emp_id) references employees(e_id)
);
---------------------------class-----------------------------
create table class(
cla_id number(4),
cla_name varchar2(50)not null,
cla_floor varchar2(15),
constraint pk_class primary key (cla_id)
);
----------------------------course-----------------
create table course(
c_id number(4),
c_name varchar2(50)not null,
c_hours number(3),
start_date date,
end_date date,
teacher_id number,
class_id number,
dept_id number,
constraint pk_course primary key (c_id,teacher_id),
constraint fk_course_teach foreign key (teacher_id) references teacher(t_id),
constraint fk_course_class foreign key (class_id) references class(cla_id),
constraint fk_course_dept foreign key (dept_id) references department(d_id)
);
---------------------------study in------------------------
create table studey_in(
stu_id number,
course_id number,
teacher_id number,
time varchar2(50) not null,
constraint pk_study primary key (stu_id,course_id,teacher_id),
constraint fk_study_course foreign key (course_id,teacher_id) references course(c_id,teacher_id),
constraint fk_study_stu foreign key (stu_id) references student(s_id)
);

insert into student(s_id,s_name)
values (1,'mazen');
update student 
set s_name='musab'
where s_name = 'mazen';

alter table employees
add (dept_id number ,constraint fk_dept foreign key (dept_id) references department(d_id));
commit;
desc employees;
SELECT constraint_name , 
column_name
FROM user_cons_columns
WHERE table_name = 'EMPLOYEES' ;
drop column dept_id;

SELECT constraint_name , 
constraint_type
FROM user_constraints
WHERE table_name = 'EMPLOYEES';