/**
 * 
 */
package by.pvt.heldyieu.mobile.beans.tariffs.internet;

import java.util.HashMap;
import java.util.Map;

import by.pvt.heldyieu.mobile.exceptions.InvalidValueException;

/**
 * @author HeroDishonest
 *
 */
public class LimitedInternetTariff extends InternetTariff {
	private Map<String, String> clients = new HashMap<String, String>(); // список
																			// клиентов
																			// данного
																			// тарифного
																			// плана

	/**
	 * 
	 */
	public LimitedInternetTariff() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param tariffname
	 * @param abonementPrice
	 * @param internetPrice
	 * @throws InvalidValueException
	 */
	public LimitedInternetTariff(String tariffname, double abonementPrice,
			double internetPrice) throws InvalidValueException {
		super(tariffname, abonementPrice, internetPrice);
		// TODO Auto-generated constructor stub
	}

	public void getClients() {
		clients.forEach((passport, surname) -> System.out.println(passport + " " + surname));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((clients == null) ? 0 : clients.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		if (!(obj instanceof LimitedInternetTariff)) {
			return false;
		}
		LimitedInternetTariff other = (LimitedInternetTariff) obj;
		if (clients == null) {
			if (other.clients != null) {
				return false;
			}
		} else if (!clients.equals(other.clients)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "; Суммарная стоимость - " + getCost();
	}

	public void subscribe(String passport, String surname, String firstname) {
		clients.put(passport, surname + " " + firstname);
	}

	public void unsubscribe(String passport) {
		clients.remove(passport);
	}

	public int getClientsNumbers() {
		return clients.size();
	}

	public double getCost() {
		return getAbonementPrice() + getInternetPrice();
	}

}