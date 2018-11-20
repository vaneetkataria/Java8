package com.katariasoft.technologies.Java8.jodatime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class JodaTimeUseCases {

	private static final String localDate = "localDate";
	private static final String localTime = "localTime";
	private static final String localDateTime = "localDateTime";

	public static void main(String args[]) {

		String testCase = "localDate";
		switch (testCase) {
		case localDate:
			localDateUseCases();
			break;
		case localTime:
			break;
		case localDateTime:
			break;
		default:
			break;
		}

	}

	public static void localDateUseCases() {
		System.out.println("Min Date is :" + LocalDate.MIN);
		System.out.println("Max Date is :" + LocalDate.MAX);
		System.out.println("Current Date is :" + LocalDate.now());
		System.out.println("Available zone Ids are :" + ZoneId.getAvailableZoneIds());
		System.out.println("Current Date in America marigot is :" + LocalDate.now(ZoneId.of("America/Marigot")));
		System.out.println(
				"Current Date in a country running in +12:30 timezone is :" + LocalDate.now(ZoneId.of("+12:30")));
		System.out.println("11/05/2019 Date is :" + LocalDate.of(2019, 5, 11));
		System.out.println("11/05/2019 Date is :" + LocalDate.of(2019, Month.MAY, 11));
		System.out.println("Date on 10th epoch day is :" + LocalDate.ofEpochDay(10));
		System.out.println("Date on 10th epoch day is :" + LocalDate.ofYearDay(5, 1));
		System.out.println(
				"12/12/2012 date parsed with LocalDate parse method is :" + LocalDate.parse("2012-10-11").toString());
		System.out.println("2018/11/20 date parsed with LocalDate parse method is :"
				+ LocalDate.parse("Tue , 2018/11/20", DateTimeFormatter.ofPattern("E , uuuu/MM/dd")).toString());
		// ##Examples which use non static methods .
		LocalDate myBirthDay = LocalDate.of(1990, Month.DECEMBER, 30);
		LocalDate myMothersBirthDay = LocalDate.of(1990, Month.DECEMBER, 30);
		LocalDate newYear1991 = LocalDate.of(1991, Month.JANUARY, 01);
		LocalDate deepawali1990 = LocalDate.of(1990, Month.NOVEMBER, 10);
		LocalDate mySisterBirthDay = LocalDate.of(1993, Month.SEPTEMBER, 19);
		System.out.println("My Borthday was at start of the Day: " + myBirthDay.atStartOfDay());
		System.out.println("My Borthday was 08:57 the Day: " + myBirthDay.atTime(LocalTime.of(8, 57)));
		System.out.println("My Borthday was 08:57 the Day: " + myBirthDay.atTime(8, 57, 10, 20000));
		System.out.println("My Birthday formatted as E,dd-MMM-uuuu is: "
				+ myBirthDay.format(DateTimeFormatter.ofPattern("EEEE,dd-MMMM-uuuu")));
		System.out.println("Day of month on my birthday is :" + myBirthDay.getDayOfMonth());
		System.out.println("Day of week on my birthday is :" + myBirthDay.getDayOfWeek());
		System.out.println("Day of year on my birthday is :" + myBirthDay.getDayOfYear());
		System.out.println("era on my birthday is :" + myBirthDay.getEra());
		System.out.println("month on my birthday is :" + myBirthDay.getMonth());
		System.out.println("month value on my birthday is :" + myBirthDay.getMonthValue());
		System.out.println("year on my birthday is :" + myBirthDay.getYear());
		System.out.println("Comparing My Birtyhday to new Year 1991:" + myBirthDay.compareTo(newYear1991));
		System.out.println("Is My birthday after new year:" + myBirthDay.isAfter(newYear1991));
		System.out.println("Is my birthday after deepawali:" + myBirthDay.isAfter(deepawali1990));
		System.out.println("Is My Birthday before deepawali :" + myBirthDay.isBefore(deepawali1990));
		System.out.println("Is My Birthday before New Year:" + myBirthDay.isBefore(newYear1991));
		System.out.println("Is me and my mothers birthday on the same day:" + myBirthDay.isEqual(myMothersBirthDay));
		System.out.println("Is my birth year is leap:" + myBirthDay.isLeapYear());
		System.out.println("Length of my birth month is :" + myBirthDay.lengthOfMonth());
		System.out.println("Length of my birth year is :" + myBirthDay.lengthOfYear());
		System.out.println(
				"My Sister's birth which is Date after 5 year , 12 months , 1 week and 1 day after my borthday is:"
						+ myBirthDay.plusDays(20).plusMonths(8).plusYears(2));
		System.out.println("My Birthday derived from my sister's birthday is :"
				+ mySisterBirthDay.minusDays(20).minusMonths(8).minusYears(2));
		System.out.println("Epoch day on my birthday is: " + myBirthDay.toEpochDay());
		System.out.println("7668th day from wpoch is :" + LocalDate.ofEpochDay(7668));
		System.out.println("Period between my and my sister's birth is :" + myBirthDay.until(mySisterBirthDay));
		myBirthDay = myBirthDay.withYear(1991).withMonth(10).withDayOfMonth(20);
		System.out.println("My Birthday altered to 20/10/1991 is: " + myBirthDay);
		myBirthDay = myBirthDay.withYear(1990).withMonth(12).withDayOfMonth(30);
		System.out.println("My Birthday altered to 30/12/1990 again is: " + myBirthDay);
	}

}
