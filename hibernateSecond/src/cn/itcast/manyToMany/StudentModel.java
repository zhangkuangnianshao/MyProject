package cn.itcast.manyToMany;

import java.util.HashSet;
import java.util.Set;

public class StudentModel { 

	private Integer id;
	private String name;
	private Set<TeacherModel> tms = new HashSet<TeacherModel>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<TeacherModel> getTms() {
		return tms;
	}
	public void setTms(Set<TeacherModel> tms) {
		this.tms = tms;
	}
	@Override
	public String toString() {
		return "StudentModel [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
