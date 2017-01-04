/**
 * 
 */
package by.pvt.heldyieu.mobile.beans;

import java.util.GregorianCalendar;
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
	public Manager(String firstName, String lastName, GregorianCalendar birthday, Category category) {
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
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manager other = (Manager) obj;
		if (category != other.category)
			return false;
		return true;
	}

}
