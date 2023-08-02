package RestPackage02;

public class POJO_POST_RequestData {
	
	String name;
	String location;
	String phone;
	String courseArr[];
	
	
	public String getName() {		
		return name;
	}
	
	public void setName(String name) {		
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String[] getCourseArr() {
		return courseArr;
	}
	
	public void setCourseArr(String[] courseArr) {
		this.courseArr = courseArr;
	}
	
	

}
