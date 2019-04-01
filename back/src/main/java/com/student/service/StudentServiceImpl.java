package com.student.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.model.Account;
import com.student.model.Student;

@Service("studentService")
@Transactional
public class StudentServiceImpl extends SqlSessionDaoSupport implements StudentService {

	private List<Student> students;
	/*
	 * private static List<Student> populateDummyStudents() { List<Student> students
	 * = new ArrayList<Student>(); students.add(new Student(1, "Sam", "Smith",
	 * "A")); students.add(new Student(2, "Tom", "Ye", "A")); return students; }
	 */

	public List<Student> findAllStudents() {
		return getSqlSession().selectList("findAllStudents");
	}

	public Student findById(int id) {
		students = findAllStudents();

		for (Student student : students) {
			if (student.getId() == id) {
				return student;
			}
		}
		return null;
	}

	public List<Student> findBySearchValue(String value) {
		//students = findAllStudents();
		/*
		ArrayList<Student> studentsFound = new ArrayList<Student>();

		for (Student student : students) {

			if (value.matches("-?\\d+(\\.\\d+)?")) {
				if (String.valueOf(student.getId()).contains(value)) {
					studentsFound.add(student);
				}
			} else {
				if (student.getFname().matches("(?i:.*"+value+".*)")|| student.getLname().matches("(?i:.*"+value+".*)")
						|| student.getSection().matches("(?i:.*"+value+".*)")) {
					studentsFound.add(student);
				}
			}
		}
		*/

		return getSqlSession().selectList("findBySearchValue", value);
	}

	public void saveStudent(Student student) {
		getSqlSession().insert("saveStudent", student);

	}

	public void updateStudent(Student student) {
		getSqlSession().update("updateStudent", student);

	}

	public void deleteStudentById(int id) {
		getSqlSession().delete("deleteStudentById", id);

	}

	public void deleteAllStudents() {
		students = findAllStudents();

		getSqlSession().delete("deleteAllStudents", students);
	}

	public boolean isStudentExist(Account account) {
		students = findAllStudents();

		for (Student student : students) {
			if (student.getUname().equals(account.getUname())  && student.getPass().equals(account.getPass())) {
			//if (student.getUname().equals(account.get("uname"))  && student.getPass().equals(account.get("pass"))) {
				System.out.println("Account FOUND");
				return true;
			}
		}
		
		return false;
		
	}

}
