/*
 * 
 */
package by.pvt.heldyieu.mobile.interfaces;

public interface Constants {
	String INPUT_FOLDER = "src\\by\\pvt\\heldyieu\\mobile\\files\\input\\";
	String OUTPUT_FOLDER = "src\\by\\pvt\\heldyieu\\mobile\\files\\output\\";
	String LOG_FILE_ROOT = "src\\by\\pvt\\heldyieu\\mobile\\logs\\log.txt";
	String SERIALIZABLE_TARIFFS_FILNAME = OUTPUT_FOLDER+"objects.bin";
	String LIMITED_CALLS_TARIFF = "LimitedCallsTariff.txt";
	String LIMITED_INERNET_TARIFF = "LimitedInternetTariff.txt";
	String UNLIMITED_CALLS_TARIFF = "UnlimitedCallsTariff.txt";
	String UNLIMITED_INERNET_TARIFF = "UnlimitedInternetTariff.txt";
	String CLIENTS_FILE_NAMES = "ClientsNames.txt";
	String CLIENTS_FILE_SURNAMES = "ClientsSurname.txt";
	String INCORRECT_INPUT_VALUE_FOR_SCANNER = "Неправильно введен номер пункта меню. Повторите ввод";
	String MYSMATCH_TYPE_INPUT_VALUE_FOR_SCANNER = "Неверный тип ввходных данных. Повторите ввод";
	String DELIMITER = "------------------------------------";
	int PASSPORT_VALUE_FOR_RANDOM = 8999999;
	int PASSPORT_VALUE_MINIMUM_FOR_RANDOM = 1000000;
	int SEARCH_BY_ABONPRICE_AND_OVERALL_COST = 1;
	int SEARCH_BY_CALL_COST_AND_FREE_MINUTES = 2;
	int SEARCH_BY_INTERNET_COST_AND_FREE_GB = 3;
	int INDEX_FOR_SUBSTRING_TARIFFNAME = 27;
	int YEAR_VALUE_FOR_RANDOM = 2;
	int YEAR_VALUE_MINIMUM_FOR_RANDOM = 2015;
	int MONTH_VALUE_FOR_RANDOM = 12;
	int DAY_VALUE_FOR_RANDOM = 32;
}