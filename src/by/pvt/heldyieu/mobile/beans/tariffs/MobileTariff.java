/**
 * 
 */
package by.pvt.heldyieu.mobile.beans.tariffs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import by.pvt.heldyieu.mobile.beans.interfaces.Subscribable;
import by.pvt.heldyieu.mobile.beans.interfaces.Unsubscribable;
import by.pvt.heldyieu.mobile.exceptions.InvalidValueException;

/**
 * @author i.heldyieu
 * version 1.0
 */
public abstract class MobileTariff implements Subscribable, Unsubscribable {
	private static int count=0;
	private static final Map<Integer, MobileTariff> tariffs = new HashMap<Integer,MobileTariff>(); //список доступных тарифов
	private double abonementPrice;		//абонентская плата
	private String tariffName;			// название тарифного плана	
	private Map<String, String> clients = new HashMap<String, String>();
	private static Map<Double, String> abonementPriceSort = new TreeMap<Double, String>();  //остортированный список тарифов
	
	/**
	 * Creates new entity of the class <b>{@code MobileTariff}</b>
	 */
	public MobileTariff() {
		super();
	}

	/**
	 * Creates new entity of the class <b>{@code MobileTariff}</b> and initialize it
	 * @param tariffname 		- name of tariff
	 * @param abonementPrice 	- the price of tariff per month 
	 * @throws IllegalValueException - see in super constructor
	 */
	public MobileTariff(String tariffname, double abonementPrice) throws InvalidValueException {
		this.tariffName = tariffname;
		if (abonementPrice<0) {
			throw new InvalidValueException("Абонементская плата не может быть < 0. Объект класса "+this.getClass().getName()+" не создан.");
		} else {
			this.abonementPrice = abonementPrice;
		}
		addTariffInstance(this);
	}
	
	/**
	 * @return the tariffs
	 */
	public static Map<Integer, MobileTariff> getTariffs() {
		return tariffs;
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
	 * @return the clients
	 */
	public Map<String, String> getClients() {
		return clients;
	}

	/**
	 * @return the abonementPriceSort
	 */
	public static Map<Double, String> getAbonementPriceSort() {
		sortOnAbonementPrice();
		return abonementPriceSort;
	}
	
	public static List<MobileTariff> getDesireTariff(Map<Integer, MobileTariff> tariffs, double price){
		return findTariff(tariffs, price);
	}
	
	private void addTariffInstance(MobileTariff mobileTariff){
		tariffs.put(count++, mobileTariff);
	}

	private static void sortOnAbonementPrice(){
		for (int i = 0; i < tariffs.size(); i++) {
			abonementPriceSort.put(tariffs.get(i).getCost(), tariffs.get(i).getTariffName());
		}
	}
	
	private static List<MobileTariff> findTariff(Map<Integer, MobileTariff> tariffs, double price){
		List<MobileTariff> target = new ArrayList<MobileTariff>();
		for (int i = 0; i < tariffs.size(); i++) {
			if (tariffs.get(i).getCost()<=price) {
				target.add(tariffs.get(i));
			}
		}
		return target;
	}
	
	abstract public double getCost();
	abstract public int getClientsNumbers();
	
}