package things;

/**
 * This class creates Persons
 * @author claudiamacrae
 *
 */

public class Person {
	//Attributes
	private String name;
	private int age;
	private String address; 
	
	//Constructor
	/**
	 * This method creates a new person with the given name age and address
	 * @param name The name of the person
	 * @param age The age of the person
	 * @param address The address at which the person lives
	 */
	Person(String name, int age, String address){
		this.name = name;
		this.age = age;
		this.address = address;
	}
	//Methods
	/**
	 * The method returns the name of the person
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * This method sets the name of the person to the given name
	 * @param n name to be set
	 */
	public void setName(String n) {
		name = n;
	}
	
	/**
	 * This method returns the age of the person
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * This method sets the age of the person to a given age
	 * @param a the age to be set
	 */
	public void setAge(int a) {
		age = a;
	}
	
	/**
	 * This method returns the address of the person 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * This method sets the address of the person to the given address
	 * @param a the address to be set
	 */
	public void setAddress(String a) {
		address = a;
	}
	
	//Main method
	public static void main(String[] args) {
		Person tom = new Person("Tom", 24, "123 Blue Blvd");
		tom.setAge(19);
		System.out.println(tom.getAge());
	}
}
