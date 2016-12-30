/**
 * 
 */
package by.pvt.heldyieu.mobile.beans;

import java.util.Calendar;

import by.pvt.heldyieu.mobile.enums.Category;

/**
 * @author i.heldyieu
 *
 */
public class Manager extends Person {
	private Category category;

	/**
	 * 
	 */
	public Manager() {
		super();
	}

	/**
	 * @param category
	 */
	public Manager(String firstName, String lastName, Calendar birthday, Category category) {
		super(firstName, lastName, birthday);
		this.category = category;
	}
	
	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Manager [category=" + category + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName()
				+ ", getBirthday()=" + getBirthday() + "]";
	}
	
}
