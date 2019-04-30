package cn.itcast.manyToMany;

import java.util.HashSet;
import java.util.Set;

public class TeacherModel {

	private Integer id;
	private String name;
	private Set<StudentModel> sms = new HashSet<StudentModel>();

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
	public Set<StudentModel> getSms() {
		return sms;
	}
	public void setSms(Set<StudentModel> sms) {
		this.sms = sms;
	}
	@Override
	public String toString() {
		return "TeacherModel [id=" + id + ", name=" + name + "]";
	}
	
	 
	

}
