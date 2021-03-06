/**
 * 
 */
package by.pvt.heldyieu.mobile.beans.tariffs.calls;

import by.pvt.heldyieu.mobile.beans.tariffs.MobileTariff;
import by.pvt.heldyieu.mobile.exceptions.InvalidValueException;

/**
 * @author i.heldyieu
 *
 */
public class LimitedCallsTariff extends CallsTariff {
	private static final long serialVersionUID = 3732899921232223661L;
	private int freeMinutes; // free minutes for this tariff
	
	public LimitedCallsTariff() {
		super();
	}

	/**
	 * Creates new entity of the class <b>{@code LimitedCallsTariff}</b> and
	 * initialize it
	 * @param tariffname - @see MobileTariff
	 * @param abonementPrice - @see MobileTariff
	 * @param callsPrice - @see CallsTariff
	 * @param freeMinutes - free minutes for this tariff
	 * @throws InvalidValueException - @see MobileTariff
	 */
	public LimitedCallsTariff(String tariffname, double abonementPrice,
			double callsPrice, int freeMinutes) throws InvalidValueException {
		super(tariffname, abonementPrice, callsPrice);
		if (freeMinutes < 0) {
			throw new InvalidValueException(
					"Количество свободных минут не может быть < 0. Объект класса "
							+ this.getClass().getName() + " не создан.", freeMinutes);
		} else {
			this.freeMinutes = freeMinutes;
			MobileTariff.addTariffInstance(this);
		}
	}
	
	/**
	 * @return the freeMinutes
	 */
	public int getFreeMinutes() {
		return freeMinutes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + freeMinutes;
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
		LimitedCallsTariff other = (LimitedCallsTariff) obj;
		if (freeMinutes != other.freeMinutes)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString()+"; Количество бесплатных минут - "+getFreeMinutes()+"; Суммарная стоимость - " + getCost();
	}
	
	/**
	 * return print the list of the clients
	 */
	public void printClients() {
		super.printClients();
	}

	/**
	 * subscribe clients to LimitedCallsTariff
	 * @param passport 	- unique id for client 
	 * @param clientInformation	- all information about client
	 */
	public void subscribe(String passport, StringBuilder clientInformation) {
		clients.put(passport, clientInformation);
	}
	
	/**
	 * unsubscribe clients from LimitedCallsTariff
	 * @param passport 	- unique id for client 
	 */
	public void unsubscribe(String passport) {
		clients.remove(passport);
	}

	/**
	 * @return number of clients of LimitedCallsTariff
	 */
	public int getClientsNumbers() {
		return clients.size();
	}

	/**
	 * @return total cost of LimitedCallsTariff
	 */
	public double getCost() {
		return getAbonementPrice() + getcallsPrice();
	}

	/**
	 * compare total cost of LimitedCallsTariff with another tariff
	 * @param anotherTariff - another tariff for compare 
	 */
	@Override
	public int compareTo(MobileTariff anotherTariff) {
		return Double.compare(this.getCost(), anotherTariff.getCost());
	}
}
