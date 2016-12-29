
package by.pvt.heldyieu.mobile.beans;

import by.pvt.heldyieu.mobile.enums.Gender;

/**
 * @author i.heldyieu
 * This Class provides Clients (mobile customers) information such as:
 * <li>Surname : String</li>
 * <li>Passport : String</li>
 * <li>Gender : Gender<Enum></li>
 * Clients can subscribe on several tarrifs or can unsubscribe from one/all tarrifs.  
 * @see java.lang.Enum
 */
public class Client {
private String surname;		//
private String passport;
private Gender gender;
	public Client() {
		
	}
	public Client(String surname, String passport, Gender gender) {
		super();
		this.surname = surname;
		this.passport = passport;
		this.gender = gender;
	}
	public String getSurname() {
		return surname;
	}
	public String getPassport() {
		return passport;
	}
	public Gender getGender() {
		return gender;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result
				+ ((passport == null) ? 0 : passport.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (gender != other.gender)
			return false;
		if (passport == null) {
			if (other.passport != null)
				return false;
		} else if (!passport.equals(other.passport))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Client [surname=" + surname + ", passport=" + passport
				+ ", gender=" + gender + "]";
	}
	
		
	

}
