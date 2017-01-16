/**
 * 
 */
package by.pvt.heldyieu.mobile.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import by.pvt.heldyieu.mobile.beans.Manager;
import by.pvt.heldyieu.mobile.beans.tariffs.MobileTariff;
import by.pvt.heldyieu.mobile.interfaces.Constants;

/**
 * @author i.heldyieu
 *
 */
public final class Initialization implements Constants {
	private static File file;
	static Map<Integer, MobileTariff> mapOfTariffs = new HashMap<Integer, MobileTariff>();
	
	
	/**
	 * Closed constructor
	 */
	private Initialization() {
	}

	public static void initializeObjects() {

		// Create new entities for LimitedCallsTariff tariffs from input file
		file = new File(INPUT_FOLDER + LIMITED_CALLS_TARIFF);
		Operations.createTariffs(file, LIMITED_CALLS_TARIFF);
		
		// Create new entities for LimitedInternetTariff tariffs from input file
		file = new File(INPUT_FOLDER + LIMITED_INERNET_TARIFF);
		Operations.createTariffs(file, LIMITED_INERNET_TARIFF);
		
		// Create new entities for UnlimitedCallsTariff tariffs from input file
		file = new File(INPUT_FOLDER + UNLIMITED_CALLS_TARIFF);
		Operations.createTariffs(file, UNLIMITED_CALLS_TARIFF);
		
		// Create new entities for UnlimitedInternetTariff tariffs from input file
		file = new File(INPUT_FOLDER + UNLIMITED_INERNET_TARIFF);
		Operations.createTariffs(file, UNLIMITED_INERNET_TARIFF);
		
		//Get all tariffs and store it in local variable for operations
		mapOfTariffs = MobileTariff.getTariffs();
		
		//Create list of random subscribers for all tariffs based on input files
		Operations.createRandomSubscribers(mapOfTariffs);
	}

	public static void menu() {
		Manager manager = new Manager();
		while (true) {
			System.out.println("Выберите пользователя:\n"
							+ "1. Клиент\n"
							+ "2. Менеджер\n"
							+ "0. Выход\n"
							+ DELIMITER+"\n");
			switch (Operations.inputNumber()) {
			case 1:
				mainClient:
				while (true) {
					System.out.println("1. Показать список доступных тарифов\n"
							+ "2. Поиск тарифов соответствующему заданному диапозону параметров\n"
							+ "3. Подключится к указанному тарифу\n"
							+ "4. Уйти с тарифного плана\n"
							+ "5. Записать в файл тарифные планы на которые вы подключены\n"
							+ "0. Выход к выбору пользователя\n"
							+ DELIMITER);
					switch (Operations.inputNumber()) {
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
							switch (Operations.inputNumber()) {
							
							case SEARCH_BY_ABONPRICE_AND_OVERALL_COST:
								System.out.println("Введите стоимость абонентской платы, которая не должна превышать:");
								double abonprice = (double) Operations.inputNumber();
								System.out.println("Введите общую стоимость тарифного плана, которая не должна превышать:");
								double cost = (double) Operations.inputNumber();
								Operations.findSuitableTariff(mapOfTariffs, abonprice, cost, SEARCH_BY_ABONPRICE_AND_OVERALL_COST);
								break out;
							
							case SEARCH_BY_CALL_COST_AND_FREE_MINUTES:
								System.out.println("Введите стоимость звонков, которая не должна превышать:");
								double calls = (double) Operations.inputNumber();
								System.out.println("Введите необходимое минимальное количество бесплатных минут :");
								double minutes = (double) Operations.inputNumber();
								Operations.findSuitableTariff(mapOfTariffs, calls, minutes, SEARCH_BY_CALL_COST_AND_FREE_MINUTES);
								break out;
								
							case SEARCH_BY_INTERNET_COST_AND_FREE_GB:
								System.out.println("Введите стоимость интернета, которая не должна превышать:");
								double priceInternet = (double) Operations.inputNumber();
								System.out.println("Введите необходимое минимальное количество интернет трафика :");
								double freeGb = (double) Operations.inputNumber();
								Operations.findSuitableTariff(mapOfTariffs, priceInternet, freeGb, SEARCH_BY_INTERNET_COST_AND_FREE_GB);
								break out;

							case 0:
								break mainClient;
								
							default:
								System.out.println(INCORRECT_INPUT_VALUE_FOR_SCANNER);
							}
						}
						break;
						
					case 3:
						StringBuilder clientInformation = new StringBuilder();
						String passport = Operations.checkPassportValue();
						System.out.println("Введите фамилию :");
						clientInformation.append(Operations.inputString()).append(" ");
						System.out.println("Введите имя :");
						clientInformation.append(Operations.inputString()).append(" ").append(Operations.getDate());
						System.out.println("Введите id тарифного плана, к которому вы хотите подключиться :");
						int id = Operations.inputNumber();
						if (id > 0 && id < mapOfTariffs.size()) {
							Operations.subscribe(mapOfTariffs.get(id), passport, clientInformation);
							manager.addPersonalClient(passport+" "+clientInformation);
							System.out.println("Вы успешно подключены к тарифу - "+mapOfTariffs.get(id).getTariffName()+"!");
						} else {
							System.out.println(MYSMATCH_TYPE_INPUT_VALUE_FOR_SCANNER);
						}
						break;
						
					case 4:
						passport = Operations.checkPassportValue();
						if (passport!=null) {
							Operations.unsubscribe(passport, mapOfTariffs);
						}
						break;
						
					case 5:
						passport = Operations.checkPassportValue();
						while (true) {
							System.out.println("Введите имя файла для сохранения в формате ИМЯ.txt");
							String filename = Operations.inputString();
							File file = new File(OUTPUT_FOLDER + filename);
							if (file.exists()) {
								System.out
										.println("Файл с таким именем уже существует. Выберите другое...");
								continue;
							}
							Operations.writeTariffsToFile(file, passport, mapOfTariffs);
							break;
							
						}
						break;
						
					case 0:
						break mainClient;
						
					default:
						System.out.println(INCORRECT_INPUT_VALUE_FOR_SCANNER);
					}
				}
				break;
				
			case 2:
				mainManager:
					while (true) {
						System.out.println("1. Вывести список подключенных вами клиентов\n"
								+ "2. Вывести общую численность абонентов\n"
								+ "3. Вывести список всех абонентов\n"
								+ "4. Вывести список тарифов отсортированных по абонентской стоимости\n"
								+ "5. Записать объекты в файл\n"
								+ "6. Прочитать объекты из файла\n"
								+ "0. Выход к выбору пользователя\n"
								+ DELIMITER);
						switch (Operations.inputNumber()) {
						case 1:
							ArrayList<String> list = new ArrayList<String>();
							list.addAll(manager.getPersonalClients());
							for (String string : list) {
								System.out.println(string);
							}
							System.out.println(DELIMITER);
							break;
							
						case 2:
							Operations.getClientsNumber(mapOfTariffs);
							System.out.println(DELIMITER);
							break;
							
						case 3:
							Operations.printAllClients(mapOfTariffs);
							System.out.println(DELIMITER);
							
							break;
							
						case 4:
							Operations.sortServicesBasedOnAbonementPrice(mapOfTariffs);
							System.out.println(DELIMITER);
							break;
							
						case 5:
							File output = new File(SERIALIZABLE_TARIFFS_FILNAME);
							Operations.serializeObjects(mapOfTariffs, output);
							System.out.println(DELIMITER);
							break;
							
						case 6:
							output = new File(SERIALIZABLE_TARIFFS_FILNAME);
							mapOfTariffs = Operations.deserializeObjects(output);
							System.out.println(DELIMITER);
							break;
							
						case 0:
							break mainManager;
							
						default:
							System.out.println(INCORRECT_INPUT_VALUE_FOR_SCANNER);
						}
					}
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