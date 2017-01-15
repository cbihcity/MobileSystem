package by.pvt.heldyieu.mobile.tools;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import by.pvt.heldyieu.mobile.beans.interfaces.Constants;
import by.pvt.heldyieu.mobile.beans.tariffs.MobileTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.calls.CallsTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.calls.LimitedCallsTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.calls.UnlimitedCallsTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.internet.InternetTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.internet.LimitedInternetTariff;
import by.pvt.heldyieu.mobile.beans.tariffs.internet.UnlimitedInternetTariff;
import by.pvt.heldyieu.mobile.exceptions.InvalidValueException;

public class Operations implements Constants {
	private static Scanner input = new Scanner(System.in, "utf-8");
	private static List<String> namesClients = new ArrayList<String>();
	private static List<String> surnamesClients = new ArrayList<String>();
	
	/**
	 * Closed constructor <b>{@code Operations}</b>
	 */
	private Operations(){
	}
	
	/**
	 * Reads list of tariffs from file
	 * @param file - input file
	 * @param list - list of tariffs for returning
	 * @throws FileNotFoundException 
	 */
	public static void readFile(List<String> list, File file) throws FileNotFoundException {
		BufferedReader br = null;
		String temp = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
			while ((temp = br.readLine()) != null) {
				list.add(temp);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Файл не найден..." + file.getName()
					+ " Проверьте наличие этого файла в директории "
					+ INPUT_FOLDER);
			Logger.log(e);
			throw e;
		} catch (IOException e) {
			Logger.log(e);
			System.out.println("Произошла ошибка ввода-вывода" + file.getName()
					+ " Подробную информацию смотрите в файле log.txt");
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				System.err.println("Ошибка закрыти потока ввода : " + e);
				Logger.log(e);
			}
		}
	}

	/**
	 * Splits string to tokens
	 * 
	 * @param str - string
	 * @return list of tokens
	 */
	public static List<String> stringParser(String str) {
		List<String> tokens = new ArrayList<>();
		String [] temp = str.split(";");
		for (String string : temp) {
			tokens.add(string);
		}
		return tokens;
	}
	
	public static int inputNumber() {
		int choice = 0;
		while (true) {
			try {
				input = new Scanner(System.in, "utf-8");
				choice = input.nextInt();
				if (choice >= 0) {
					break;
				} else {
					System.out.println(INCORRECT_INPUT_VALUE_FOR_SCANNER);
				}
			} catch (InputMismatchException e) {
				System.out.println(MYSMATCH_TYPE_INPUT_VALUE_FOR_SCANNER);
				Logger.log(e);
			} 
		}
		return choice;
	}
	
	public static String inputString() {
		input = new Scanner(System.in, "utf-8");	
		return input.next();
	}

	/**
	 * Print all available tariffs at MobileTariff
	 * @param mapOfTariffs - list of available tariffs
	 */
	public static void printTariffs(Map<Integer, MobileTariff> mapOfTariffs) {
		Set<Map.Entry<Integer, MobileTariff>> set = mapOfTariffs.entrySet();
		Iterator<Map.Entry<Integer, MobileTariff>> it = set.iterator();
		while (it.hasNext()) {
			int temp = it.next().getKey();
			System.out.println("id="+temp + " : " + mapOfTariffs.get(temp).toString());
		}
		System.out.println(DELIMITER);
	}
	
	/**
	 * Read from file values of specific tariff and create its entites
	 * @param file - input file with values for specific tariff
	 * @param typeTariff - specific type of tariff
	 */
	public static void createTariffs(File file, String typeTariff) {
		List<String> strings = new ArrayList<>();
		try {
			Operations.readFile(strings, file);
		} catch (FileNotFoundException e) {
			System.out.println("При создании объекта "+typeTariff.replaceAll(".txt","")+" возникла ошибка."
					+ " Подробное описание ошибки в файле log.txt");
		}
		Iterator<String> itStrings = strings.iterator();
		while (itStrings.hasNext()) {
			List<String> tariffTokens = Operations.stringParser(itStrings
					.next());
			Iterator<String> itTokens = tariffTokens.iterator();
				switch (typeTariff) {
				case LIMITED_CALLS_TARIFF:
					createLimitedCallsTariff(itTokens);
					break;
				case LIMITED_INERNET_TARIFF:
					createLimitedInternetTariff(itTokens);
					break;
				case UNLIMITED_CALLS_TARIFF:
					createUnlimitedCallsTariff(itTokens);
					break;
				case UNLIMITED_INERNET_TARIFF:
					createUnlimitedInternetTariff(itTokens);
					break;
				default:
					System.out.println(MYSMATCH_TYPE_INPUT_VALUE_FOR_SCANNER);
				}
		}
	}
	
	/**
	 * Create new LimitedCallsTariff with specific values
	 * @param itTokens - list of parsed values for new tariff
	 */
	private static void createLimitedCallsTariff(Iterator<String> itTokens) {
		try {
			while (itTokens.hasNext()) {
				new LimitedCallsTariff(itTokens.next(),
						Double.parseDouble(itTokens.next()),
						Double.parseDouble(itTokens.next()),
						Integer.parseInt(itTokens.next()));
			}
		} catch (InvalidValueException | IllegalArgumentException
				| NoSuchElementException e) {
			Logger.log(e);
			System.out
					.println("При создании объекта LimitedCallsTariff возникла ошибка."
							+ " Подробное описание ошибки в файле log.txt");
		}
	}

	/**
	 * Create new LimitedInternetTariff with specific values
	 * @param itTokens - list of parsed values for new tariff
	 */
	private static void createLimitedInternetTariff(Iterator<String> itTokens) {
		try {
			while (itTokens.hasNext()) {
				new LimitedInternetTariff(itTokens.next(),
						Double.parseDouble(itTokens.next()),
						Double.parseDouble(itTokens.next()),
						Double.parseDouble(itTokens.next()));
			}
		} catch (InvalidValueException | IllegalArgumentException
				| NoSuchElementException e) {
			Logger.log(e);
			System.out
					.println("При создании объекта LimitedInternetTariff возникла ошибка."
							+ " Подробное описание ошибки в файле log.txt");
		}
	}

	/**
	 * Create new UnlimitedCallsTariff with specific values
	 * @param itTokens - list of parsed values for new tariff
	 */
	private static void createUnlimitedCallsTariff(Iterator<String> itTokens) {
		try {
			while (itTokens.hasNext()) {
				new UnlimitedCallsTariff(itTokens.next(),
						Double.parseDouble(itTokens.next()),
						Double.parseDouble(itTokens.next()),
						Integer.parseInt(itTokens.next()));
			}
		} catch (InvalidValueException | IllegalArgumentException
				| NoSuchElementException e) {
			Logger.log(e);
			System.out
					.println("При создании объекта UnlimitedCallsTariff возникла ошибка."
							+ " Подробное описание ошибки в файле log.txt");
		}
	}

	/**
	 * Create new UnlimitedInternetTariff with specific values
	 * @param itTokens - list of parsed values for new tariff
	 */
	private static void createUnlimitedInternetTariff(Iterator<String> itTokens) {
		try {
			while (itTokens.hasNext()) {
				new UnlimitedInternetTariff(itTokens.next(),
						Double.parseDouble(itTokens.next()),
						Double.parseDouble(itTokens.next()),
						Double.parseDouble(itTokens.next()));
			}
		} catch (InvalidValueException | IllegalArgumentException
				| NoSuchElementException e) {
			Logger.log(e);
			System.out
					.println("При создании объекта UnlimitedInternetTariff возникла ошибка."
							+ " Подробное описание ошибки в файле log.txt");
		}
	}

	public static void createRandomSubscribers(File file, File file2, Map<Integer, MobileTariff> mapOfTariffs) {
		Operations.createListsNamesAndSurnames(file, file2);
		for (MobileTariff tariff : mapOfTariffs.values()) {
			Operations.subscribeForTariff(tariff);
		}
	}

	private static void createListsNamesAndSurnames(File file, File file2) {
		List<String> tempNames = new ArrayList<>();
		List<String> tempClients = new ArrayList<>();
		try {
			Operations.readFile(tempNames, file);
			Operations.readFile(tempClients, file2);
		} catch (FileNotFoundException e) {
			System.out
					.println("При создании списка абонентов возникла ошибка. Подробное описание ошибки в файле log.txt");
		}
		Iterator<String> namesTokens = tempNames.iterator();
		Iterator<String> surnameTokens = tempClients.iterator();
		while (namesTokens.hasNext()) {
			namesClients = Operations.stringParser(namesTokens.next());
		}
		while (surnameTokens.hasNext()) {
			surnamesClients = Operations.stringParser(surnameTokens.next());
			}
	}
	
	private static void subscribeForTariff(MobileTariff tariff) {
		Random randomGenerator = new Random();
		StringBuilder clientInformation;
		String[] arrayNames = new String[namesClients.size()];
		arrayNames = namesClients.toArray(arrayNames);
		String[] arraySurnames = new String[surnamesClients.size()];
		arraySurnames = surnamesClients.toArray(arraySurnames);
		int randomNumberClients = randomGenerator.nextInt(50);
		try {
			for (int i = 0; i < randomNumberClients; i++) {
				clientInformation = new StringBuilder();
				String date = Operations.getRandomDate();
				tariff.subscribe(
						"MP" + (randomGenerator.nextInt(PASSPORT_VALUE_FOR_RANDOM) + PASSPORT_VALUE_MINIMUM_FOR_RANDOM),
						clientInformation.append(arraySurnames[randomGenerator.nextInt(arraySurnames.length)])
							.append(" ")
							.append(arrayNames[randomGenerator.nextInt(arrayNames.length)])
							.append(" ")
							.append(date));
			}
		} catch (IllegalArgumentException e) {
			Logger.log(e);
		}
	}
	
	public static String getRandomDate() {
		Random rand = new Random();
		int year = rand.nextInt(YEAR_VALUE_FOR_RANDOM)
				+ YEAR_VALUE_MINIMUM_FOR_RANDOM;
		int month = rand.nextInt(MONTH_VALUE_FOR_RANDOM);
		int day = rand.nextInt(DAY_VALUE_FOR_RANDOM);
		GregorianCalendar calendar = new GregorianCalendar(year, month, day);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	}
	
	public static String getDate() {
		Date date = Calendar.getInstance().getTime();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    return sdf.format(date);
	}

	public static void getClientsNumber(Map<Integer, MobileTariff> mapOfTariffs){
		int clientsNumber = 0; 
		for (MobileTariff tariff : mapOfTariffs.values()) {
			clientsNumber += tariff.getClientsNumbers();
		}
		System.out.println("Общая численность клиентов : "+clientsNumber);
	}
	
	/**
	 * Displays lists of tariffs sort by abonement price
	 * @param mapOfTariffs 	- list of available tariffs
	 * @param index - sorting index
	 */
	public static void sortServicesBasedOnAbonementPrice(Map<Integer, MobileTariff> mapOfTariffs){
		List<MobileTariff> list = new ArrayList<>(mapOfTariffs.values());
		Collections.sort(list, new MobileTariffComparator());
		Operations.reportAfterSort(list, mapOfTariffs);
	}
	
	/**
	 * Displays list
	 * @param list - list of sorted tariffs
	 * @param mapOfTariffs - list of available tariffs
	 */
	private static void reportAfterSort(List<? extends Object> list, Map<Integer, MobileTariff> mapOfTariffs){
		for(Object s : list){
			if(s instanceof MobileTariff){
				Set<Map.Entry<Integer, MobileTariff>> set = mapOfTariffs.entrySet(); 
				Iterator<Map.Entry<Integer, MobileTariff>> it = set.iterator();
				while (it.hasNext()) {
					Map.Entry<Integer, MobileTariff> entry = it.next();
					if (entry.getValue().equals((MobileTariff)s)){
						System.out.println("id=" + entry.getKey() + " - " + entry.getValue().toString());
						break;
					}
				}
			}
		}
	}

	public static void findSuitableTariff(
			Map<Integer, MobileTariff> mapOfTariffs, double parameter1, double parameter2, int typeOfSearch) {
		switch (typeOfSearch) {
		case SEARCH_BY_ABONPRICE_AND_OVERALL_COST:
			mapOfTariffs.forEach((key, value) -> {
				if (value.getAbonementPrice() <= parameter1
						&& value.getCost() <= parameter2) {
					System.out.println("id="+key + " - " + value.toString().substring(INDEX_FOR_SUBSTRING_TARIFFNAME));
				}
			});
			System.out.println(DELIMITER);
			break;
			
		case SEARCH_BY_CALL_COST_AND_FREE_MINUTES:
			mapOfTariffs.forEach((key, value) -> {
				if (value instanceof CallsTariff) {
					if (((CallsTariff)value).getcallsPrice()<=parameter1 && 
							((CallsTariff)value).getFreeMinutes()>=parameter2){
						System.out.println("id="+key + " - " + value.toString().substring(27));
					}
				}
			});
			System.out.println(DELIMITER);
			break;
			
		case SEARCH_BY_INTERNET_COST_AND_FREE_GB:
			mapOfTariffs.forEach((key, value) -> {
				if (value instanceof InternetTariff) {
					if (((InternetTariff)value).getInternetPrice()<=parameter1 && 
							((InternetTariff)value).getFreeGb()>=parameter2){
						System.out.println("id="+key + " - " + value.toString().substring(27));
					}
				}
			});
			System.out.println(DELIMITER);
			break;

		default:
			System.out.println(MYSMATCH_TYPE_INPUT_VALUE_FOR_SCANNER);
		}
	}
	
	public static void serializeObjects (Map<Integer, MobileTariff> mapOfTariffs, File file){
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(mapOfTariffs);
			System.out.println("Запись успешно произведена в файл \"" + file.getName() + "\"");
			System.out.println(mapOfTariffs.toString());
		} 
		catch (IOException e) {
			System.out.println("Ошибка записи. Невозможно создать файл \"" + file.getName() + "\"");
			Logger.log(e);
		} finally {
			try {
				if (out!=null) {
					out.close();
				}
			} catch (Exception e){
				System.err.println("Ошибка закрыти потока вывода : "+e);
				Logger.log(e);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Map<Integer, MobileTariff> deserializeObjects(File file){
		Map<Integer, MobileTariff> fromFile = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			fromFile = (Map<Integer, MobileTariff>) ois.readObject();
			System.out.println("Объект успешно восстановлен.");
		} 
		catch (FileNotFoundException e) {
			System.out.println("Файл не найден..." + file.getName());
			Logger.log(e);
		} 
		catch (IOException e) {
			Logger.log(e);
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Класс не найден...");
			Logger.log(e);
		} 
		finally {
			try {
				if (ois!=null) {
					ois.close();
				}
			} catch (Exception e){
				System.err.println("Ошибка закрыти потока ввода : "+e);
				Logger.log(e);
			}
		}
		return fromFile;
	}

	public static void subscribe(MobileTariff mobileTariff, String passport, StringBuilder clientInformation) {
		mobileTariff.subscribe(passport, clientInformation);
	}

	public static void unsubscribe(String passport, Map<Integer, MobileTariff> mapOfTariffs) {
		for (MobileTariff tariff : mapOfTariffs.values()) {
			if (tariff.getClients().containsKey(passport)) {
				tariff.unsubscribe(passport);
				System.out.println("Вы были успешно отключены от тарифного плана " + tariff.getTariffName());
			}
		}
		System.out.println(DELIMITER);
	}
	
	public static void printAllClients(Map<Integer, MobileTariff> mapOfTariffs) {
		for (MobileTariff tariff : mapOfTariffs.values()) {
			System.out.println(tariff.getTariffName()+":");
			tariff.printClients();
			System.out.println(DELIMITER);
			}
		}

	public static String checkPassportValue() {
		String passport;
		while (true) {
			System.out
					.println("Введите номер вашего паспорта в формате MPxxxxxxx :");
			passport = Operations.inputString();
			if (!passport.startsWith("MP")) {
				System.out
						.println(MYSMATCH_TYPE_INPUT_VALUE_FOR_SCANNER);
			} else {
				break;
			}
		}
		return passport;
	}

	public static void writeTariffsToFile(File filename, String passport, Map<Integer, MobileTariff> mapOfTariffs) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename),"utf-8")));
			for (MobileTariff tariff : mapOfTariffs.values()) {
				if (tariff.getClients().containsKey(passport)) {
					pw.println(tariff.toString());
				}
			}
			System.out.println("Запись успешно произведена в файл \"" + filename.getPath() + "\"");
		} 
		catch (IOException e) {
			System.out.println("Ошибка записи. Невозможно создать файл \"" + filename.getPath() + "\"");
			Logger.log(e);
		} finally {
			try {
				if (pw!=null) {
					pw.close();
				}
			} catch (Exception e){
				System.err.println("Ошибка закрыти потока вывода : "+e);
				Logger.log(e);
			}
		}
	}
}
