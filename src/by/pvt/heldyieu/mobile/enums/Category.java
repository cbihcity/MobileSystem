package by.pvt.heldyieu.mobile.enums;

/**
 * @author i.heldyieu
 */
public enum Category {
	JUNIOR, MIDDLE, TOPLEVEL;
	
	public String getCategory(){
		switch(this){
			case JUNIOR:
				return "младший специалист";
			case MIDDLE:
				return "специалист";
			case TOPLEVEL:
				return "старший специалист";
			default:
				return "не определен";
		
		}
	}
}
