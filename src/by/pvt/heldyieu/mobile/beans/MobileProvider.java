/*
 * 
 */
package by.pvt.heldyieu.mobile.beans;

import java.util.Map;
import by.pvt.heldyieu.mobile.beans.tariffs.MobileTariff;

/**
 * @author i.heldyieu
 */
public abstract class MobileProvider {
	private String companyName;
	private String address;
	private String phoneNumber;
	private String webSite;
	private String eMail;
	private Map<Integer,MobileTariff> listOfMobileTariffs;

	/**
	 * 
	 */
	public MobileProvider() {
		super();
	}

	/**
	 * @param companyName - name of company
	 * @param address - addres of company
	 * @param phoneNumber - phonenumber of company
	 * @param webSite - website of company
	 * @param eMail = email of company
	 * @param mobiletariff - list of tariffs of company
	 */
	public MobileProvider(String companyName, 
												String address,
												String phoneNumber, 
												String webSite, 
												String eMail, MobileTariff mobiletariff) {
		super();
		this.companyName = companyName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.webSite = webSite;
		this.eMail = eMail;
		listOfMobileTariffs= MobileTariff.getTariffs();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MobileProvider [companyName=" + companyName + ", address="
				+ address + ", phoneNumber=" + phoneNumber + ", webSite="
				+ webSite + ", eMail=" + eMail + ", listOfMobileTariffs="
				+ listOfMobileTariffs + "]";
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
		result = prime
				* result
				+ ((listOfMobileTariffs == null) ? 0 : listOfMobileTariffs
						.hashCode());
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((webSite == null) ? 0 : webSite.hashCode());
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
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MobileProvider)) {
			return false;
		}
		MobileProvider other = (MobileProvider) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (companyName == null) {
			if (other.companyName != null) {
				return false;
			}
		} else if (!companyName.equals(other.companyName)) {
			return false;
		}
		if (eMail == null) {
			if (other.eMail != null) {
				return false;
			}
		} else if (!eMail.equals(other.eMail)) {
			return false;
		}
		if (listOfMobileTariffs == null) {
			if (other.listOfMobileTariffs != null) {
				return false;
			}
		} else if (!listOfMobileTariffs.equals(other.listOfMobileTariffs)) {
			return false;
		}
		if (phoneNumber == null) {
			if (other.phoneNumber != null) {
				return false;
			}
		} else if (!phoneNumber.equals(other.phoneNumber)) {
			return false;
		}
		if (webSite == null) {
			if (other.webSite != null) {
				return false;
			}
		} else if (!webSite.equals(other.webSite)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the webSite
	 */
	public String getWebSite() {
		return webSite;
	}

	/**
	 * @param webSite
	 *            the webSite to set
	 */
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	/**
	 * @return the eMail
	 */
	public String geteMail() {
		return eMail;
	}

	/**
	 * @param eMail
	 *            the eMail to set
	 */
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

}
