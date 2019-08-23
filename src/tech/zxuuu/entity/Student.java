package tech.zxuuu.entity;

/**
 * 学生类
 * @author z0gSh1u
 */

public class Student {

	private String cardNumber; // 一卡通号
	private String studentNumber; // 学号
	private String password; // 密码（MD5）
	private String name; // 姓名
	private String academy; // 学院代码

	@Override
	public String toString() {
		return "Student [cardNumber=" + cardNumber + ", studentNumber=" + studentNumber + ", password=" + password
				+ ", name=" + name + ", academy=" + academy + "]";
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public Student(String cardNumber, String password) {
		this.cardNumber = cardNumber;
		this.password = password;
	}
	
//	public Student(String cardNumber, String studentNumber, String password, String name) {
//		this.cardNumber = cardNumber;
//		this.studentNumber = studentNumber;
//		this.password = password;
//		this.name = name;
//		this.academy = studentNumber.substring(0, 2);
//	}
	
}