/**
 * 
 */
package by.pvt.heldyieu.mobile.tools;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import by.pvt.heldyieu.mobile.beans.interfaces.Constants;
import by.pvt.heldyieu.mobile.beans.tariffs.MobileTariff;

/**
 * @author HeroDishonest
 *
 */
public final class Initialization implements Constants {
	private static File file;
	private static File file2;
	private static Map<Integer, MobileTariff> mapOfTariffs = new HashMap<Integer, MobileTariff>();
	
	/**
	 * Closed constructor
	 */
	private Initialization() {
	}

	// public static void main(String[] args) {
	// try {
	// new LimitedInternetTariff("Лимитируемые интернет", 30.5, 4.5);
	// new UnlimitedInternetTariff("Нелимитируемый интернет", 41.5, 5.6);
	// new LimitedCallsTariff("Лимитируемые звонки", 19.5, 2.5);
	// new UnlimitedCallsTariff("Нелимитируемые звонки", 16.5, 5.6);
	// } catch (InvalidValueException e) {
	// // TODO Auto-generated catch block
	// System.out.println(e.getMessage());
	// }
	// MobileTariff[] tariffs = MobileTariff.getTariffs();
	// int sum=0;
	// Random rand = new Random();
	// for (int i = 0; i < tariffs.length; i++) {
	// tariffs[i].subscribe("MP"+(rand.nextInt(8999999)+1000000), "Толя",
	// "Толивич");
	// tariffs[i].subscribe("MP"+(rand.nextInt(8999999)+1000000), "Коля",
	// "Коливоич");
	// tariffs[i].subscribe("MP"+(rand.nextInt(8999999)+1000000), "Валера",
	// "Носовеов");
	// tariffs[i].subscribe("MP"+(rand.nextInt(8999999)+1000000), "Катя",
	// "Таня");
	// sum+=tariffs[i].getClientsNumbers();
	// System.out.println(tariffs[i].getTariffName()+":");
	// tariffs[i].printClients();
	// System.out.println(tariffs[i].toString());
	// System.out.println("=============================================");
	// }
	//
	// System.out.println(sum);
	// System.out.println(MobileTariff.getAbonementPriceSort().toString());
	// MobileTariff.getDesireTariff(tariffs, 25).forEach(System.out::println);;
	//
	// Manager manager = new Manager("Karl", "Karlovich",new
	// GregorianCalendar(1989,11,23),Category.TOPLEVEL);
	// System.out.println(manager.toString());
	// GregorianCalendar date = new GregorianCalendar(1970, 5, 12);
	// System.out.println(date.before(manager.getBirthday()));
	// }

	public static void initialize() {

		// Create new entities for LimitedCallsTariff tariffs from input files
		file = new File(INPUT_FOLDER + LIMITED_CALLS_TARIFF);
		Operations.createTariffs(file, LIMITED_CALLS_TARIFF);
		
		// Create new entities for LimitedInternetTariff tariffs from input files
		file = new File(INPUT_FOLDER + LIMITED_INERNET_TARIFF);
		Operations.createTariffs(file, LIMITED_INERNET_TARIFF);
		
		// Create new entities for UnlimitedCallsTariff tariffs from input files
		file = new File(INPUT_FOLDER + UNLIMITED_CALLS_TARIFF);
		Operations.createTariffs(file, UNLIMITED_CALLS_TARIFF);
		
		// Create new entities for UnlimitedInternetTariff tariffs from input files
		file = new File(INPUT_FOLDER + UNLIMITED_INERNET_TARIFF);
		Operations.createTariffs(file, UNLIMITED_INERNET_TARIFF);
		
		//Get all tariffs and store it in local variable for operations
		mapOfTariffs = MobileTariff.getTariffs();
		
		//Create list of random subscribers for all tariffs based on input files
		file = new File(INPUT_FOLDER + CLIENTS_FILE_NAMES);
		file2 = new File(INPUT_FOLDER + CLIENTS_FILE_SURNAMES);
		Operations.createRandomSubscribers(file, file2, mapOfTariffs);
		
		//print all tariffs
		Operations.printTariffs(mapOfTariffs);
		
		System.out.println("==========================================");
		
		for (MobileTariff tariff : mapOfTariffs.values()) {
			System.out.println(tariff.getTariffName()+":");
			tariff.printClients();
			System.out.println("==========================================");
		}
		
		Operations.getClientsNumber(mapOfTariffs);
		
		Operations.sortServicesBasedOnAbonementPrice(mapOfTariffs);

	}
}