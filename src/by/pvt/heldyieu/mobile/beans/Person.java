/**
 * 
 */
package by.pvt.heldyieu.mobile.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import by.pvt.heldyieu.mobile.enums.Gender;

/**
 * @author i.heldyieu
 */
public abstract class Person implements Serializable {

	private static final long serialVersionUID = -5153137333831290066L;
	private String firstName;
	private String lastName;
	private Gender gender;
	private GregorianCalendar birthday;

	/**
	 * 
	 */
	public Person() {
		super();
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param birthday
	 */
	public Person(String firstName, String lastName, Gender gender, GregorianCalendar birthday) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthday = birthday;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName - the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the birthday
	 */
	public GregorianCalendar getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday - the birthday to set
	 */
	public void setBirthday(GregorianCalendar birthday) {
		this.birthday = birthday;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Менеджер - "+getFirstName()+" "+getLastName()+" "+getGender()+", дата рождения - "+getBirthday().get(Calendar.YEAR)+" "
																				+getBirthday().get(Calendar.MONTH)+" "
																				+getBirthday().get(Calendar.DATE);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender != other.gender)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
}
