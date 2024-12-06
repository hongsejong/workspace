package edu.kh.oarr.practice.model.vo;

public class Employee {
	
	private int number;
	private String name;
	private String team;
	private String level;
	private int money;
	
	public Employee() {}
	
	public Employee(int number , String name, String team, String level, int money) {
		this.number=number;
		this.name=name;
		this.team=team;
		this.level=level;
		this.money=money;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "사번 : " + number + ", 이름 : " + name + ", 부서 :" + team + ", 직급 :"+level+", 급여 :" + money;
	}

}
