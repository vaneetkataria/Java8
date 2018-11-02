package com.katariasoft.technologies.Java8.interfaces.funtional.predefined.pridicate.primitive;

import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;

public class PrimitivePredicates {

	private IntPredicate isEvenHouseNumber = n -> n % 2 == 0;
	private IntPredicate isInOldHouseNumbersRange = n -> n < 10000;
	private IntPredicate isEligibleForHousingPlate = isEvenHouseNumber.and(isInOldHouseNumbersRange);
	private IntPredicate isEligibleForSecurityGuard = isEvenHouseNumber.negate().and(isInOldHouseNumbersRange.negate());
	private IntPredicate isEligibleForDog = isEvenHouseNumber.negate().or(isInOldHouseNumbersRange);

	private LongPredicate isEvenMobileNumber = n -> n % 2 == 0;
	private LongPredicate isInOlfMObileNumberRange = n -> n > 8_999_999_999L;
	private LongPredicate giveFiftyRupeesRechqarge = isEvenMobileNumber.and(isInOlfMObileNumberRange);
	private LongPredicate giveHundredRupeesRechqarge = isEvenMobileNumber.negate()
			.or(isInOlfMObileNumberRange.negate());

	private DoublePredicate isRadiusGreterThan100m = r -> r > 100.00;
	private DoublePredicate isLessThan125m = n -> n < 125.00;
	private DoublePredicate canBeParkWheel = isRadiusGreterThan100m.and(isLessThan125m);

	public static void main(String args[]) {
		PrimitivePredicates predicates = new PrimitivePredicates();
		System.out.println("4000 eligible for housing plate : " + predicates.isEligibleForHousingPlate.test(4000));
		System.out.println("4011 eligible for housing plate : " + predicates.isEligibleForHousingPlate.test(4011));
		System.out.println("10000 eligible for housing plate : " + predicates.isEligibleForHousingPlate.test(10000));
		System.out.println("10001 eligible for housing plate : " + predicates.isEligibleForHousingPlate.test(10001));

		System.out.println("4000 eligible for security guard : " + predicates.isEligibleForSecurityGuard.test(4000));
		System.out.println("4011 eligible for security guard : " + predicates.isEligibleForSecurityGuard.test(4011));
		System.out.println("10000 eligible for security guard : " + predicates.isEligibleForSecurityGuard.test(10000));
		System.out.println("10001 eligible for security guard : " + predicates.isEligibleForSecurityGuard.test(10001));

		System.out.println("4000 eligible for dog : " + predicates.isEligibleForDog.test(4000));
		System.out.println("4011 eligible for dog : " + predicates.isEligibleForDog.test(4011));
		System.out.println("10000 eligible for dog : " + predicates.isEligibleForDog.test(10000));
		System.out.println("10001 eligible for dog : " + predicates.isEligibleForDog.test(10001));

		System.out.println(
				"9086721908 eligible for 50 recharge : " + predicates.giveFiftyRupeesRechqarge.test(9086721908L));
		System.out.println(
				"9086721909 eligible for 50 recharge : " + predicates.giveFiftyRupeesRechqarge.test(9086721909L));
		System.out.println(
				"8086721908 eligible for 50 recharge : " + predicates.giveFiftyRupeesRechqarge.test(8086721908L));
		System.out.println(
				"8086721909 eligible for 50 recharge : " + predicates.giveFiftyRupeesRechqarge.test(8086721909L));

		System.out.println(
				"8086721908 eligible for 100 recharge : " + predicates.giveHundredRupeesRechqarge.test(8086721908L));
		System.out.println(
				"8086721909 eligible for 100 recharge : " + predicates.giveHundredRupeesRechqarge.test(8086721909L));
		System.out.println(
				"9086721909 eligible for 100 recharge : " + predicates.giveHundredRupeesRechqarge.test(9086721909L));

		System.out.println("99.00 can be park wheel: " + predicates.canBeParkWheel.test(99.00));
		System.out.println("999.00 can be park wheel: " + predicates.canBeParkWheel.test(999.00));
		System.out.println("109.00 can be park wheel: " + predicates.canBeParkWheel.test(109.00));

	}

}
