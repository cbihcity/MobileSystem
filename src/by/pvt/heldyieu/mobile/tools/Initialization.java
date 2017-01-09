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
//		Operations.printTariffs(mapOfTariffs);
//		
//		System.out.println("==========================================");
//		
//		for (MobileTariff tariff : mapOfTariffs.values()) {
//			System.out.println(tariff.getTariffName()+":");
//			tariff.printClients();
//			System.out.println("==========================================");
//		}
//		
//		Operations.getClientsNumber(mapOfTariffs);
//		
//		Operations.sortServicesBasedOnAbonementPrice(mapOfTariffs);
		
		//Operations.findSuitableTariff(mapOfTariffs, SEARCH_BY_ABONPRICE_AND_OVERALL_COST);
		

	}

	public static void menu() {
		while (true) {
			System.out.println("Выберите пользователя:\n"
							+ "1. Клиент\n"
							+ "2. Менеджер\n"
							+ "0. Выход\n"
							+ DELIMITER+"\n");
			switch (Operations.inputnumber()) {
			case 1:
				main:
				while (true) {
					System.out.println("1. Показать список доступных тарифов\n"
							+ "2. Поиск тарифов соответствующему заданному диапозону параметров\n"
							+ "3. Подключится к указанному тарифу\n"
							+ "0. Выход к выбору пользователя\n"
							+ DELIMITER);
					switch (Operations.inputnumber()) {
					case 1:
						Operations.printTariffs(mapOfTariffs);
						break;
						
					case 2:
						out:
						while (true) {
							System.out.println("1. Поиск по абоненсткой плате и общей стоимости тарифа\n"
									+ "2. Поиск по стоимости звонков и количеству свободных минут\n"
									+ "3. Поиск по стоимости интернета и количеству интернет-трафика\n"
									+ "0. Выход к выбору пользователя\n"
									+ DELIMITER);
							switch (Operations.inputnumber()) {
							
							case SEARCH_BY_ABONPRICE_AND_OVERALL_COST:
								System.out.println("Введите стоимость абонентской платы, которая не должна превышать:");
								double abonprice = (double) Operations.inputnumber();
								System.out.println("Введите общую стоимость тарифного плана, которая не должна превышать:");
								double cost = (double) Operations.inputnumber();
								Operations.findSuitableTariff(mapOfTariffs, abonprice, cost, SEARCH_BY_ABONPRICE_AND_OVERALL_COST);
								break out;
							
							case SEARCH_BY_CALL_COST_AND_FREE_MINUTES:
								System.out.println("Введите стоимость звонков, которая не должна превышать:");
								double calls = (double) Operations.inputnumber();
								System.out.println("Введите количество бесплатных минут, которое не должно превышать:");
								double minutes = (double) Operations.inputnumber();
								Operations.findSuitableTariff(mapOfTariffs, calls, minutes, SEARCH_BY_CALL_COST_AND_FREE_MINUTES);
								break out;
								
							case SEARCH_BY_INTERNET_COST_AND_FREE_GB:
								System.out.println("Введите стоимость интернета, которая не должна превышать:");
								double priceInternet = (double) Operations.inputnumber();
								System.out.println("Введите количество интернет трафика, которое не должно превышать:");
								double freeGb = (double) Operations.inputnumber();
								Operations.findSuitableTariff(mapOfTariffs, priceInternet, freeGb, SEARCH_BY_INTERNET_COST_AND_FREE_GB);
								break out;

							case 0:
								break main;
								
							default:
								System.out.println(INCORRECT_INPUT_VALUE_FOR_SCANNER);
							}
						}
						break;
						
					case 3:
						break;
						
					case 0:
						break main;
						
					default:
						System.out.println(INCORRECT_INPUT_VALUE_FOR_SCANNER);
					}
				}
				break;
				
			case 2:
				break;
				
			case 0:
				System.out.println("Работа завершена.\n"+DELIMITER);
				System.exit(0);

			default:
				System.out.println(INCORRECT_INPUT_VALUE_FOR_SCANNER);
			}
		}
	}
}