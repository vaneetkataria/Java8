package com.katariasoft.technologies.Java8.jodatime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.function.Consumer;

public class JodaTimeUseCases {

	private static Consumer<String> messageConsumer = System.out::println;
	private static LocalDate myBirthDay = LocalDate.of(1990, Month.DECEMBER, 30);
	private static LocalDate myMothersBirthDay = LocalDate.of(1990, Month.DECEMBER, 30);
	private static LocalDate newYear1991 = LocalDate.of(1991, Month.JANUARY, 01);
	private static LocalDate deepawali1990 = LocalDate.of(1990, Month.NOVEMBER, 10);
	private static LocalDate mySisterBirthDay = LocalDate.of(1993, Month.SEPTEMBER, 19);
	private static LocalTime myBirthTime = LocalTime.of(8, 57, 30, 1);
	private static LocalTime myWifeBirthTime = LocalTime.of(9, 33, 0, 516);
	private static LocalDateTime myBirthDateAndTime = LocalDateTime.of(myBirthDay, myBirthTime);
	private static LocalDateTime mySisterBirthDateAndTime = LocalDateTime.of(mySisterBirthDay, myBirthTime);

	private static final String localDate = "localDate";
	private static final String localTime = "localTime";
	private static final String localDateTime = "localDateTime";

	public static void main(String args[]) {
		String testCase = localDateTime;
		switch (testCase) {
		case localDate:
			localDateUseCases();
			break;
		case localTime:
			localTimeUseCases();
			break;
		case localDateTime:
			localDateTimeUseCases();
			break;
		default:
			break;
		}

	}

	private static void localDateUseCases() {
		sout("Min Date is :" + LocalDate.MIN);

		sout("Max Date is :" + LocalDate.MAX);

		sout("Current Date is :" + LocalDate.now());

		sout("Available zone Ids are :" + ZoneId.getAvailableZoneIds());

		sout("Current Date in America marigot is :" + LocalDate.now(ZoneId.of("America/Marigot")));

		sout("Current Date in a country running in +12:30 timezone is :" + LocalDate.now(ZoneId.of("+12:30")));

		sout("11/05/2019 Date is :" + LocalDate.of(2019, 5, 11));

		sout("11/05/2019 Date is :" + LocalDate.of(2019, Month.MAY, 11));

		sout("Date on 10th epoch day is :" + LocalDate.ofEpochDay(10));

		sout("Date on 10th epoch day is :" + LocalDate.ofYearDay(5, 1));

		sout("12/12/2012 date parsed with LocalDate parse method is :" + LocalDate.parse("2012-10-11").toString());

		sout("2018/11/20 date parsed with LocalDate parse method is :"

				+ LocalDate.parse("Tue , 2018/11/20", DateTimeFormatter.ofPattern("E , uuuu/MM/dd")).toString());

		sout("My Borthday was at start of the Day: " + myBirthDay.atStartOfDay());

		sout("My Borthday was 08:57 the Day: " + myBirthDay.atTime(LocalTime.of(8, 57)));

		sout("My Borthday was 08:57 the Day: " + myBirthDay.atTime(8, 57, 10, 20000));

		sout("My Birthday formatted as E,dd-MMM-uuuu is: "
				+ myBirthDay.format(DateTimeFormatter.ofPattern("EEEE,dd-MMMM-uuuu")));

		sout("Day of month on my birthday is :" + myBirthDay.getDayOfMonth());

		sout("Day of week on my birthday is :" + myBirthDay.getDayOfWeek());

		sout("Day of year on my birthday is :" + myBirthDay.getDayOfYear());

		sout("era on my birthday is :" + myBirthDay.getEra());

		sout("month on my birthday is :" + myBirthDay.getMonth());

		sout("month value on my birthday is :" + myBirthDay.getMonthValue());

		sout("year on my birthday is :" + myBirthDay.getYear());

		sout("Comparing My Birtyhday to new Year 1991:" + myBirthDay.compareTo(newYear1991));

		sout("Is My birthday after new year:" + myBirthDay.isAfter(newYear1991));

		sout("Is my birthday after deepawali:" + myBirthDay.isAfter(deepawali1990));

		sout("Is My Birthday before deepawali :" + myBirthDay.isBefore(deepawali1990));

		sout("Is My Birthday before New Year:" + myBirthDay.isBefore(newYear1991));

		sout("Is me and my mothers birthday on the same day:" + myBirthDay.isEqual(myMothersBirthDay));

		sout("Is my birth year is leap:" + myBirthDay.isLeapYear());

		sout("Length of my birth month is :" + myBirthDay.lengthOfMonth());

		sout("Length of my birth year is :" + myBirthDay.lengthOfYear());

		sout("My Sister's birth which is Date after 5 year , 12 months , 1 week and 1 day after my borthday is:"
				+ myBirthDay.plusDays(20).plusMonths(8).plusYears(2));

		sout("My Birthday derived from my sister's birthday is :"
				+ mySisterBirthDay.minusDays(20).minusMonths(8).minusYears(2));

		sout("Epoch day on my birthday is: " + myBirthDay.toEpochDay());

		sout("7668th day from wpoch is :" + LocalDate.ofEpochDay(7668));

		sout("Period between my and my sister's birth is :" + myBirthDay.until(mySisterBirthDay));

		sout("Period in years between me and my sister's birthday is :"
				+ myBirthDay.until(mySisterBirthDay, ChronoUnit.YEARS));

		myBirthDay = myBirthDay.withYear(1991).withMonth(10).withDayOfMonth(20);
		sout("My Birthday altered to 20/10/1991 is: " + myBirthDay);

		myBirthDay = myBirthDay.withYear(1990).withMonth(12).withDayOfMonth(30);
		sout("My Birthday altered to 30/12/1990 again is: " + myBirthDay);
	}

	private static void localTimeUseCases() {
		sout("Max Local Time is :" + LocalTime.MAX);

		sout("Min Local Time is :" + LocalTime.MIN);

		sout("Mindnight is :" + LocalTime.MIDNIGHT);

		sout("Noon Time is :" + LocalTime.NOON);

		sout("Local Time of 10.10.10.10 is :" + LocalTime.of(10, 10, 10, 1000001));
		sout("Now the time is :" + LocalTime.now());

		sout("Now the time in zone +0500 is :" + LocalTime.now(ZoneId.of("+0600")));

		sout("Local Time parsed from 10:15:03.999 is " + LocalTime.parse("10:15:03.0011111"));

		sout("Local Time parsed from 23:10:10 is "
				+ LocalTime.parse("08:57:19.001", DateTimeFormatter.ofPattern("HH:mm:ss.SSS")));

		sout("My Birth  Time is : " + myBirthTime);

		sout("My Birth  Time set to 29/11/1991 is : " + myBirthTime.atDate(LocalDate.of(1991, 11, 29)));

		sout("My Birth  Time again set to 30/12/1990 is : " + myBirthTime.atDate(LocalDate.of(1990, 12, 30)));

		sout("Birth Time comparing to my wife is :" + myBirthTime.compareTo(myWifeBirthTime));

		sout("My Birth Time Hour is :" + myBirthTime.getHour());

		sout("My Birth Time Minute is :" + myBirthTime.getMinute());

		sout("My Birth Time second is :" + myBirthTime.getSecond());

		sout("My Birth Time nano is :" + myBirthTime.getNano());
		sout("Is my birth time after my wife :" + myBirthTime.isAfter(myWifeBirthTime));

		sout("Is my birth time before my wife  :" + myBirthTime.isBefore(myWifeBirthTime));

		sout("0 hour 35 min 30 sec and 515 nanos after my birth is :"
				+ myBirthTime.plusHours(0).plusMinutes(35).plusSeconds(30).plusNanos(515));

		sout("0 hour 35 min 30 sec and 515 nanos before my birth is :"
				+ myWifeBirthTime.minusHours(0).minusMinutes(35).minusSeconds(30).minusNanos(515));

		sout("Changing my birth time with 9th hour , 32 min and 515 nanos is:"
				+ myBirthTime.withHour(9).withMinute(32).withNano(515));

		sout("No Of Nanos between me and my Wife's birthday : " + myBirthTime.until(myWifeBirthTime, ChronoUnit.NANOS));

		sout(ChronoUnit.NANOS.between(myBirthTime, myWifeBirthTime) + "");

		sout("No Of Micros between me and my Wife's birthday : "
				+ myBirthTime.until(myWifeBirthTime, ChronoUnit.MICROS));

		sout("No Of Millis between me and my Wife's birthday : "
				+ myBirthTime.until(myWifeBirthTime, ChronoUnit.MILLIS));

		sout("No Of Seconds between me and my Wife's birthday : "
				+ myBirthTime.until(myWifeBirthTime, ChronoUnit.SECONDS));

		sout(ChronoUnit.SECONDS.between(myBirthTime, myWifeBirthTime) + "");

		sout("No Of Minutes between me and my Wife's birthday : "
				+ myBirthTime.until(myWifeBirthTime, ChronoUnit.MINUTES));

		sout(ChronoUnit.MINUTES.between(myBirthTime, myWifeBirthTime) + "");

		sout("No Of Hours between me and my Wife's birthday : " + myBirthTime.until(myWifeBirthTime, ChronoUnit.HOURS));

		sout(ChronoUnit.HOURS.between(myBirthTime, myWifeBirthTime) + "");

	}

	private static void localDateTimeUseCases() {
		sout("Max Local DateTime is" + LocalDateTime.MAX);
		sout("Min Local DateTime is" + LocalDateTime.MIN);
		sout("Now Date Time is " + LocalDateTime.now());
		sout("Available zones are " + ZoneId.getAvailableZoneIds());
		sout("Date Time in +0630 zone is  " + LocalDateTime.now(ZoneId.of("+0630")));
		sout("My Birth date and time is : " + LocalDateTime.of(1990, 12, 30, 8, 57, 30, 1));
		sout("My Birth date and time with LocalDate and LocalTime objects is : "
				+ LocalDateTime.of(myBirthDay, myBirthTime));
		sout("My Birth Date and Time parsed from 1990-12-30T08:57:30.000000001 is: "
				+ LocalDateTime.parse("1990-12-30T08:57:30.000000001"));
		sout("My Birth Date and Time parsed from 1990-12-30T08:57:30.000000001 is: " + LocalDateTime
				.parse("1990/12/30T08:57:30.001", DateTimeFormatter.ofPattern("uuuu/MM/dd'T'HH:mm:ss.SSS")));
		sout("My Birth Date and Time in zone +0630 is :" + myBirthDateAndTime.atZone(ZoneId.of("+0630")));
		sout("My Comparison with my sister's birthDate and Time is :"
				+ myBirthDateAndTime.compareTo(mySisterBirthDateAndTime));
		sout(myBirthDateAndTime.format(DateTimeFormatter.ofPattern("E,d-MM-uu'T'HH:mm:ss.SSS")));
		sout("Date Time Two months before my birth is : " + myBirthDateAndTime.minus(Period.ofMonths(11)));
		sout("Date Time Two Hours 3 min and 4 seconds before my birth is : " + myBirthDateAndTime
				.minus(Duration.ofHours(2)).minus(Duration.ofMinutes(3)).minus(Duration.ofSeconds(4)));
		sout("Date Time after 2 hours , 3 minutes and 3 seconds after my birth day is " + myBirthDateAndTime
				.plus(Duration.ofHours(2).plus(Duration.ofMinutes(3)).plus(Duration.ofSeconds(3)).plusNanos(4)));

		sout("Date Time after 10 days , 2 hours , 3 minutes , 3 seconds and 4 nanno seconds after my birth is "
				+ myBirthDateAndTime.plus(Period.ofDays(10)).plus(Duration.ofHours(2))
						.plus(Duration.ofMinutes(3).plus(Duration.ofSeconds(3)).plus(Duration.ofNanos(4))));

		sout("My Birthday truncated to day is" + myBirthDateAndTime.truncatedTo(ChronoUnit.DAYS));
		sout("My Birthday truncated to Hour is" + myBirthDateAndTime.truncatedTo(ChronoUnit.HOURS));
		sout("My Birthday truncated to Minute is" + myBirthDateAndTime.truncatedTo(ChronoUnit.MINUTES));
		sout("My Birthday truncated to seconds is" + myBirthDateAndTime.truncatedTo(ChronoUnit.SECONDS));
		sout("My Birthday truncated to micros is" + myBirthDateAndTime.truncatedTo(ChronoUnit.MICROS));
		sout("My Birthday truncated to nano is" + myBirthDateAndTime.truncatedTo(ChronoUnit.NANOS));

		sout("Years between me and my sister birth are:"
				+ myBirthDateAndTime.until(mySisterBirthDateAndTime, ChronoUnit.YEARS));
		sout("Years between me and my sister birth are:"
				+ ChronoUnit.YEARS.between(myBirthDateAndTime, mySisterBirthDateAndTime));
		sout("Years between me and my sister birth are:"
				+ myBirthDateAndTime.until(mySisterBirthDateAndTime, ChronoUnit.MONTHS));
		sout("Years between me and my sister birth are:"
				+ ChronoUnit.MONTHS.between(myBirthDateAndTime, mySisterBirthDateAndTime));

		sout("Adjusting my birth date time with day of week as 6 "
				+ myBirthDateAndTime.with(ChronoField.DAY_OF_WEEK, 1));

	}

	private static void sout(String message) {
		messageConsumer.accept(message + "\n");
	}

}
