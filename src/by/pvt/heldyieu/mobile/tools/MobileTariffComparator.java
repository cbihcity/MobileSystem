/*
 * 
 */
package by.pvt.heldyieu.mobile.tools;

import by.pvt.heldyieu.mobile.beans.tariffs.MobileTariff;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author i.heldyieu 
 */
public class MobileTariffComparator implements Comparator<MobileTariff>, Serializable{
	private static final long serialVersionUID = 386056766694741488L;

	public MobileTariffComparator() {
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(MobileTariff o1, MobileTariff o2) {
		return Double.compare(o1.getAbonementPrice(), o2.getAbonementPrice());
	}
}
