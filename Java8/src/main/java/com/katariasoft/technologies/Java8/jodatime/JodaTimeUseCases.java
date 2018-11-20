package com.katariasoft.technologies.Java8.jodatime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.function.Consumer;

public class JodaTimeUseCases {

	private static Consumer<String> messageConsumer = System.out::println;

	private static final String localDate = "localDate";
	private static final String localTime = "localTime";
	private static final String localDateTime = "localDateTime";

	public static void main(String args[]) {
		String testCase = localDate;
		switch (testCase) {
		case localDate:
			localDateUseCases();
			break;
		case localTime:
			localTimeUseCases();
			break;
		case localDateTime:
			break;
		default:
			break;
		}

	}

	private static void localDateUseCases() {
		sout("Min Date is :" + LocalDate.MIN);
		soutNextLine();
		sout("Max Date is :" + LocalDate.MAX);
		soutNextLine();
		sout("Current Date is :" + LocalDate.now());
		soutNextLine();
		sout("Available zone Ids are :" + ZoneId.getAvailableZoneIds());
		soutNextLine();
		sout("Current Date in America marigot is :" + LocalDate.now(ZoneId.of("America/Marigot")));
		soutNextLine();
		sout("Current Date in a country running in +12:30 timezone is :" + LocalDate.now(ZoneId.of("+12:30")));
		soutNextLine();
		sout("11/05/2019 Date is :" + LocalDate.of(2019, 5, 11));
		soutNextLine();
		sout("11/05/2019 Date is :" + LocalDate.of(2019, Month.MAY, 11));
		soutNextLine();
		sout("Date on 10th epoch day is :" + LocalDate.ofEpochDay(10));
		soutNextLine();
		sout("Date on 10th epoch day is :" + LocalDate.ofYearDay(5, 1));
		soutNextLine();
		sout("12/12/2012 date parsed with LocalDate parse method is :" + LocalDate.parse("2012-10-11").toString());
		soutNextLine();
		sout("2018/11/20 date parsed with LocalDate parse method is :"

				+ LocalDate.parse("Tue , 2018/11/20", DateTimeFormatter.ofPattern("E , uuuu/MM/dd")).toString());
		soutNextLine();
		// ##Examples which use non static methods .
		LocalDate myBirthDay = LocalDate.of(1990, Month.DECEMBER, 30);
		LocalDate myMothersBirthDay = LocalDate.of(1990, Month.DECEMBER, 30);
		LocalDate newYear1991 = LocalDate.of(1991, Month.JANUARY, 01);
		LocalDate deepawali1990 = LocalDate.of(1990, Month.NOVEMBER, 10);
		LocalDate mySisterBirthDay = LocalDate.of(1993, Month.SEPTEMBER, 19);
		sout("My Borthday was at start of the Day: " + myBirthDay.atStartOfDay());
		soutNextLine();
		sout("My Borthday was 08:57 the Day: " + myBirthDay.atTime(LocalTime.of(8, 57)));
		soutNextLine();
		sout("My Borthday was 08:57 the Day: " + myBirthDay.atTime(8, 57, 10, 20000));
		soutNextLine();
		sout("My Birthday formatted as E,dd-MMM-uuuu is: "
				+ myBirthDay.format(DateTimeFormatter.ofPattern("EEEE,dd-MMMM-uuuu")));
		soutNextLine();
		sout("Day of month on my birthday is :" + myBirthDay.getDayOfMonth());
		soutNextLine();
		sout("Day of week on my birthday is :" + myBirthDay.getDayOfWeek());
		soutNextLine();
		sout("Day of year on my birthday is :" + myBirthDay.getDayOfYear());
		soutNextLine();
		sout("era on my birthday is :" + myBirthDay.getEra());
		soutNextLine();
		sout("month on my birthday is :" + myBirthDay.getMonth());
		soutNextLine();
		sout("month value on my birthday is :" + myBirthDay.getMonthValue());
		soutNextLine();
		sout("year on my birthday is :" + myBirthDay.getYear());
		soutNextLine();
		sout("Comparing My Birtyhday to new Year 1991:" + myBirthDay.compareTo(newYear1991));
		soutNextLine();
		sout("Is My birthday after new year:" + myBirthDay.isAfter(newYear1991));
		soutNextLine();
		sout("Is my birthday after deepawali:" + myBirthDay.isAfter(deepawali1990));
		soutNextLine();
		sout("Is My Birthday before deepawali :" + myBirthDay.isBefore(deepawali1990));
		soutNextLine();
		sout("Is My Birthday before New Year:" + myBirthDay.isBefore(newYear1991));
		soutNextLine();
		sout("Is me and my mothers birthday on the same day:" + myBirthDay.isEqual(myMothersBirthDay));
		soutNextLine();
		sout("Is my birth year is leap:" + myBirthDay.isLeapYear());
		soutNextLine();
		sout("Length of my birth month is :" + myBirthDay.lengthOfMonth());
		soutNextLine();
		sout("Length of my birth year is :" + myBirthDay.lengthOfYear());
		soutNextLine();
		sout("My Sister's birth which is Date after 5 year , 12 months , 1 week and 1 day after my borthday is:"
				+ myBirthDay.plusDays(20).plusMonths(8).plusYears(2));
		soutNextLine();
		sout("My Birthday derived from my sister's birthday is :"
				+ mySisterBirthDay.minusDays(20).minusMonths(8).minusYears(2));
		soutNextLine();
		sout("Epoch day on my birthday is: " + myBirthDay.toEpochDay());
		soutNextLine();
		sout("7668th day from wpoch is :" + LocalDate.ofEpochDay(7668));
		soutNextLine();
		sout("Period between my and my sister's birth is :" + myBirthDay.until(mySisterBirthDay));
		soutNextLine();
		sout("Period in years between me and my sister's birthday is :"
				+ myBirthDay.until(mySisterBirthDay, ChronoUnit.YEARS));
		soutNextLine();
		myBirthDay = myBirthDay.withYear(1991).withMonth(10).withDayOfMonth(20);
		sout("My Birthday altered to 20/10/1991 is: " + myBirthDay);
		soutNextLine();
		myBirthDay = myBirthDay.withYear(1990).withMonth(12).withDayOfMonth(30);
		sout("My Birthday altered to 30/12/1990 again is: " + myBirthDay);
	}

	private static void localTimeUseCases() {
		sout("Max Local Time is :" + LocalTime.MAX);
		soutNextLine();
		sout("Min Local Time is :" + LocalTime.MIN);
		soutNextLine();
		sout("Mindnight is :" + LocalTime.MIDNIGHT);
		soutNextLine();
		sout("Noon Time is :" + LocalTime.NOON);
		soutNextLine();
		sout("Local Time of 10.10.10.10 is :" + LocalTime.of(10, 10, 10, 1000001));
		sout("Now the time is :" + LocalTime.now());
		soutNextLine();
		sout("Now the time in zone +0500 is :" + LocalTime.now(ZoneId.of("+0600")));
		soutNextLine();
		sout("Local Time parsed from 10:15:03.999 is " + LocalTime.parse("10:15:03.1111"));
		soutNextLine();
		sout("Local Time parsed from 23:10:10 is "
				+ LocalTime.parse("08:57:19.001", DateTimeFormatter.ofPattern("HH:mm:ss.SSS")));
		soutNextLine();
		LocalTime myBirthTime = LocalTime.of(8, 57, 30, 1);
		soutNextLine();
		LocalTime myWifeBirthTime = LocalTime.of(9, 33, 0, 516);
		soutNextLine();
		sout("My Birth  Time is : " + myBirthTime);
		soutNextLine();
		sout("My Birth  Time set to 29/11/1991 is : " + myBirthTime.atDate(LocalDate.of(1991, 11, 29)));
		soutNextLine();
		sout("My Birth  Time again set to 30/12/1990 is : " + myBirthTime.atDate(LocalDate.of(1990, 12, 30)));
		soutNextLine();
		sout("Birth Time comparing to my wife is :" + myBirthTime.compareTo(myWifeBirthTime));
		soutNextLine();
		sout("My Birth Time Hour is :" + myBirthTime.getHour());
		soutNextLine();
		sout("My Birth Time Minute is :" + myBirthTime.getMinute());
		soutNextLine();
		sout("My Birth Time second is :" + myBirthTime.getSecond());
		soutNextLine();
		sout("My Birth Time nano is :" + myBirthTime.getNano());
		sout("Is my birth time after my wife :" + myBirthTime.isAfter(myWifeBirthTime));
		soutNextLine();
		sout("Is my birth time before my wife  :" + myBirthTime.isBefore(myWifeBirthTime));
		soutNextLine();
		sout("0 hour 35 min 30 sec and 515 nanos after my birth is :"
				+ myBirthTime.plusHours(0).plusMinutes(35).plusSeconds(30).plusNanos(515));
		soutNextLine();
		sout("0 hour 35 min 30 sec and 515 nanos before my birth is :"
				+ myWifeBirthTime.minusHours(0).minusMinutes(35).minusSeconds(30).minusNanos(515));
		soutNextLine();
		sout("Changing my birth time with 9th hour , 32 min and 515 nanos is:"
				+ myBirthTime.withHour(9).withMinute(32).withNano(515));
		soutNextLine();
		sout("No Of Nanos between me and my Wife's birthday : " + myBirthTime.until(myWifeBirthTime, ChronoUnit.NANOS));
		soutNextLine();
		sout(ChronoUnit.NANOS.between(myBirthTime, myWifeBirthTime) + "");
		soutNextLine();
		sout("No Of Micros between me and my Wife's birthday : "
				+ myBirthTime.until(myWifeBirthTime, ChronoUnit.MICROS));
		soutNextLine();
		sout("No Of Millis between me and my Wife's birthday : "
				+ myBirthTime.until(myWifeBirthTime, ChronoUnit.MILLIS));
		soutNextLine();
		sout("No Of Seconds between me and my Wife's birthday : "
				+ myBirthTime.until(myWifeBirthTime, ChronoUnit.SECONDS));
		soutNextLine();
		sout(ChronoUnit.SECONDS.between(myBirthTime, myWifeBirthTime) + "");
		soutNextLine();
		sout("No Of Minutes between me and my Wife's birthday : "
				+ myBirthTime.until(myWifeBirthTime, ChronoUnit.MINUTES));
		soutNextLine();
		sout(ChronoUnit.MINUTES.between(myBirthTime, myWifeBirthTime) + "");
		soutNextLine();
		sout("No Of Hours between me and my Wife's birthday : " + myBirthTime.until(myWifeBirthTime, ChronoUnit.HOURS));
		soutNextLine();
		sout(ChronoUnit.HOURS.between(myBirthTime, myWifeBirthTime) + "");
		soutNextLine();

	}

	private static void soutNextLine() {
		messageConsumer.accept("\n");
	}

	private static void sout(String message) {
		messageConsumer.accept(message);
	}

}
