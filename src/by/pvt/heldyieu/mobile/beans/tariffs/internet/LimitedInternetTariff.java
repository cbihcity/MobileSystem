/**
 * 
 */
package by.pvt.heldyieu.mobile.beans.tariffs.internet;

import by.pvt.heldyieu.mobile.beans.tariffs.MobileTariff;
import by.pvt.heldyieu.mobile.exceptions.InvalidValueException;

/**
 * @author i.heldyieu
 */
public class LimitedInternetTariff extends InternetTariff {
	private static final long serialVersionUID = -2219850263435567609L;
	private double freeGb; // size of free internet traffic
	
	public LimitedInternetTariff() {
		super();
	}

	/**
	 * Creates new entity of the class <b>{@code LimitedInternetTariff}</b> and
	 * initialize it
	 * @param tariffname - @see MobileTariff
	 * @param abonementPrice - @see MobileTariff
	 * @param internetPrice - @see InternetTariff
	 * @param freeGb - size of free internet traffic
	 * @throws InvalidValueException - @see MobileTariff
	 */
	public LimitedInternetTariff(String tariffname, double abonementPrice,
			double internetPrice, double freeGb) throws InvalidValueException {
		super(tariffname, abonementPrice, internetPrice);
		if (freeGb < 0) {
			throw new InvalidValueException(
					"Количество свободного интернет траффика не может быть < 0. Объект класса "
							+ this.getClass().getName() + " не создан.", freeGb);
		} else {
			this.freeGb = freeGb;
			MobileTariff.addTariffInstance(this);
		}
	}
	
	

	/**
	 * @return the freeGb
	 */
	public double getFreeGb() {
		return freeGb;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(freeGb);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		LimitedInternetTariff other = (LimitedInternetTariff) obj;
		if (Double.doubleToLongBits(freeGb) != Double
				.doubleToLongBits(other.freeGb))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "; Количество бесплатного интернет траффика - "+getFreeGb()+"; Суммарная стоимость - " + getCost();
	}
	
	/**
	 * @return print the list of the clients
	 */
	public void printClients() {
		super.printClients();
	}

	/**
	 * subscribe clients to LimitedInternetTariff
	 * @param passport 	- unique id for client 
	 * @param clientInformation	- all information about client
	 */
	public void subscribe(String passport, StringBuilder clientInformation) {
		clients.put(passport, clientInformation);
	}

	/**
	 * unsubscribe clients from LimitedInternetTariff
	 * @param passport 	- unique id for client 
	 */
	public void unsubscribe(String passport) {
		clients.remove(passport);
	}

	/**
	 * @return number of clients of LimitedInternetTariff
	 */
	public int getClientsNumbers() {
		return clients.size();
	}

	/**
	 * @return total cost of LimitedInternetTariff
	 */
	public double getCost() {
		return getAbonementPrice() + getInternetPrice();
	}

	/**
	 * compare total cost of LimitedInternetTariff with another tariff
	 * @param anotherTariff - another tariff for compare 
	 */
	@Override
	public int compareTo(MobileTariff anotherTariff) {
		return Double.compare(this.getCost(), anotherTariff.getCost());
	}
}