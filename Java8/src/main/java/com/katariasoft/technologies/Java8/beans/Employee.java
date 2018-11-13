package com.katariasoft.technologies.Java8.beans;

public class Employee {

	private String name;
	private byte age;
	private String designation;
	private double salary;
	private String location;
	private String designationBand;
	private String maritalStatus;
	private String sex;
	private long phoneNumber;

	public Employee(String name, byte age, String designation, double salary, String location, String designationBand,
			String maritalStatus, String sex, long phoneNumber) {
		super();
		this.name = name;
		this.age = age;
		this.designation = designation;
		this.salary = salary;
		this.location = location;
		this.designationBand = designationBand;
		this.maritalStatus = maritalStatus;
		this.sex = sex;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getAge() {
		return age;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDesignationBand() {
		return designationBand;
	}

	public void setDesignationBand(String designationBand) {
		this.designationBand = designationBand;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + ", designation=" + designation + ", salary=" + salary
				+ ", location=" + location + ", designationBand=" + designationBand + ", maritalStatus=" + maritalStatus
				+ ", sex=" + sex + ", phoneNumber=" + phoneNumber + "]";
	}

}
