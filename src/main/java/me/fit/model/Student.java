package me.fit.model;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@NamedQueries({ @NamedQuery(name = Student.GET_ALL_STUDENTS, query = "Select s from Student s"),
		@NamedQuery(name = Student.GET_STUDENTS_BY_NAME, query = "Select s from Student s where s.ime = :name") })
public class Student {

	public static final String GET_ALL_STUDENTS = "getAllStudents";
	public static final String GET_STUDENTS_BY_NAME = "getStudentsByName";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
	private Long id;

	private String ime;

	private String prezime;

	private Date datumRodjenja;

	private String jmbg;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Set<Telefon> telefoni;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private IPLog ipLog;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public Set<Telefon> getTelefoni() {
		return telefoni;
	}

	public void setTelefoni(Set<Telefon> telefoni) {
		this.telefoni = telefoni;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public IPLog getIpLog() {
		return ipLog;
	}

	public void setIpLog(IPLog ipLog) {
		this.ipLog = ipLog;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jmbg == null) ? 0 : jmbg.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (jmbg == null) {
			if (other.jmbg != null)
				return false;
		} else if (!jmbg.equals(other.jmbg))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", ime=" + ime + ", prezime=" + prezime + "]";
	}

}
