/**
 * 
 */
package by.pvt.heldyieu.mobile.beans;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import by.pvt.heldyieu.mobile.enums.Category;
import by.pvt.heldyieu.mobile.enums.Gender;

/**
 * @author i.heldyieu
 */
public class Manager extends Person {

	private static final long serialVersionUID = -7203434436755432144L;
	private Category category;
	private Set<String> personalClients = new HashSet<String>(); 
	/**
	 * 
	 */
	public Manager() {
		super();
	}

	/**
	 * @param firstName - name for manager
	 * @param lastName - lastname for manager for manager
	 * @param gender - gender of manager
	 * @param birthday - date of birth for manager
	 * @param category - category for manager
	 */
	public Manager(String firstName, String lastName, Gender gender, GregorianCalendar birthday, Category category) {
		super(firstName, lastName, gender, birthday);
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

	/**
	 * @return the personalClients
	 */
	public Set<String> getPersonalClients() {
		return personalClients;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString()+", Категория - "+category.getCategory();
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result
				+ ((personalClients == null) ? 0 : personalClients.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Manager)) {
			return false;
		}
		Manager other = (Manager) obj;
		if (category != other.category) {
			return false;
		}
		if (personalClients == null) {
			if (other.personalClients != null) {
				return false;
			}
		} else if (!personalClients.equals(other.personalClients)) {
			return false;
		}
		return true;
	}

	public void addPersonalClient (String client){
		personalClients.add(client);
	}
}
