/**
 * 
 */
package by.pvt.heldyieu.mobile.enums;

/**
 * @author i.heldyieu
 */

public enum Gender {
	FEMALE, MALE;
	public String getGender(){
		switch(this){
			case FEMALE:
				return "женщина";
			case MALE:
				return "мужчина";
			default:
				return "не определен";
		}
	}
}
