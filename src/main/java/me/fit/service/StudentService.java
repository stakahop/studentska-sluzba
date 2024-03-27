package me.fit.service;

import java.util.HashSet;
import java.util.List;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.fit.exception.StudentException;
import me.fit.exception.StudentStatus;
import me.fit.model.Student;
import me.fit.model.Telefon;

@Dependent
public class StudentService {

	@Inject
	private EntityManager em;

	@Transactional
	public Student createStudent(Student s) throws StudentException {

		List<Student> students = getAllStudents();

		if (students.contains(s)) {
			throw new StudentException(StudentStatus.EXISTS.getLabel());
		}

		return em.merge(s);
	}

	@Transactional
	public List<Student> getAllStudents() {
		List<Student> students = em.createNamedQuery(Student.GET_ALL_STUDENTS, Student.class).getResultList();

		for (Student student : students) {
			List<Telefon> telefoni = getAllForStudent(student);
			student.setTelefoni(new HashSet<>(telefoni));
		}

		return students;
	}

	@Transactional
	public List<Telefon> getAllForStudent(Student s) {
		return em.createNamedQuery(Telefon.GET_ALL_FOR_STUDENT, Telefon.class).setParameter("id", s.getId())
				.getResultList();
	}

	@Transactional
	public List<Student> getStudentsByName(String name) {
		List<Student> students = em.createNamedQuery(Student.GET_STUDENTS_BY_NAME, Student.class)
				.setParameter("name", name).getResultList();
		
		for (Student student : students) {
			List<Telefon> telefoni = getAllForStudent(student);
			student.setTelefoni(new HashSet<>(telefoni));
		}
		
		return students;

	}

}
