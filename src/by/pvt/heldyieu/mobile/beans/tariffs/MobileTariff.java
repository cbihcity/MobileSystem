package by.pvt.heldyieu.mobile.beans.tariffs;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import by.pvt.heldyieu.mobile.exceptions.InvalidValueException;
import by.pvt.heldyieu.mobile.interfaces.Constants;
import by.pvt.heldyieu.mobile.interfaces.Subscribable;
import by.pvt.heldyieu.mobile.interfaces.Unsubscribable;

/**
 * @author i.heldyieu 
 */
public abstract class MobileTariff implements Constants, Subscribable, Unsubscribable, Comparable<MobileTariff>, Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4444143939196197302L;
	private double abonementPrice;	 // abonement price
	private String tariffName; 		// name of mobiletariff
	protected Map<String, StringBuilder> clients = new HashMap<String, StringBuilder>(); // list of clients on exact mobiletariff
	private static int count = 0;	// count of created tariifs
	private static Map<Integer, MobileTariff> TARIFFS = new HashMap<Integer, MobileTariff>(); // list of available mobiletariffs

	public MobileTariff() {
		super();
	}

	/**
	 * Creates new entity of the class <b>{@code MobileTariff}</b> and
	 * initialize it
	 * 
	 * @param tariffname	- name of tariff
	 * @param abonementPrice	- the price of tariff per month
	 * @throws InvalidValueException	- throws exception if input value is incorrect
	 */
	public MobileTariff(String tariffname, double abonementPrice) throws InvalidValueException {
		this.tariffName = tariffname;
		if (abonementPrice < 0) {
			throw new InvalidValueException(
					"Абонементская плата не может быть < 0. Объект класса "
							+ this.getClass().getName() + " не создан.", abonementPrice);
		} else {
			this.abonementPrice = abonementPrice;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Название тарифного плана - " + getTariffName()
				+ "; Стоимость абонентской платы - " + getAbonementPrice();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(abonementPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((tariffName == null) ? 0 : tariffName.hashCode());
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
		MobileTariff other = (MobileTariff) obj;
		if (Double.doubleToLongBits(abonementPrice) != Double
				.doubleToLongBits(other.abonementPrice))
			return false;
		if (tariffName == null) {
			if (other.tariffName != null)
				return false;
		} else if (!tariffName.equals(other.tariffName))
			return false;
		return true;
	}

	/**
	 * @return the tariffs
	 */
	public final static Map<Integer, MobileTariff> getTariffs() {
		return MobileTariff.TARIFFS;
	}

	/**
	 * @return the abonementPrice
	 */
	public double getAbonementPrice() {
		return abonementPrice;
	}

	/**
	 * @return the tariffName
	 */
	public String getTariffName() {
		return tariffName;
	}
	
	/**
	 * return print the list of the clients
	 */
	public void printClients() {
		clients.forEach((passport, surname) -> System.out.println(passport + " " + surname));
	}
	
	/**
	 * @return the clients
	 */
	public Map<String, StringBuilder> getClients() {
		return clients;
	}

	protected static final void addTariffInstance(final MobileTariff mobileTariff) {
		TARIFFS.put(++count, mobileTariff);
	}
	
	//total cost of specific mobiletariff 
	abstract public double getCost();

	//return total number of clients of specific mobiletariff
	abstract public int getClientsNumbers();

}