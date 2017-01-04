/**
 * 
 */
package by.pvt.heldyieu.mobile.beans.tariffs.calls;

import by.pvt.heldyieu.mobile.exceptions.InvalidValueException;

/**
 * @author HeroDishonest
 *
 */
public class LimitedCallsTariff extends CallsTariff {

	/**
	 * 
	 */
	public LimitedCallsTariff() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param tariffname
	 * @param abonementPrice
	 * @param callsPrice
	 * @throws InvalidValueException
	 */
	public LimitedCallsTariff(String tariffname, double abonementPrice,
			double callsPrice) throws InvalidValueException {
		super(tariffname, abonementPrice, callsPrice);
	}

	/**
	 * @return the clients
	 */
	public void printClients() {
		super.printClients();
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
		result = prime * result + ((getClients() == null) ? 0 : clients.hashCode());
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
		if (!(obj instanceof LimitedCallsTariff)) {
			return false;
		}
		LimitedCallsTariff other = (LimitedCallsTariff) obj;
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
		return super.toString()+"; Суммарная стоимость - " + getCost();
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
		return getAbonementPrice() + getcallsPrice();
	}
}
