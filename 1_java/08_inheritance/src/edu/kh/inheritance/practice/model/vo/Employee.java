package edu.kh.inheritance.practice.model.vo;

public class Employee extends Person{
	
	private int salraly; //급여
	private String dept; //부서
public Employee() {
}
public Employee(String name, int age, double height, double weight,int salraly, String dept) {
	super( age,  height,  weight);
	this.name=name;
	this.salraly = salraly;
	this.dept = dept;
}
@Override
public String toString() {
	return super.toString()+ " 급여 =" + salraly + ", 부서=" + dept + "]";
}
public int getSalraly() {
	return salraly;
}
public void setSalraly(int salraly) {
	this.salraly = salraly;
}
public String getDept() {
	return dept;
}
public void setDept(String dept) {
	this.dept = dept;
}


}

