package com.katariasoft.technologies.Java8.interfaces.funtional.predefined.unaryoperator;

import static java.lang.Math.atan;
import static java.lang.Math.cbrt;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.function.BiConsumer;
import java.util.function.UnaryOperator;

public class UnaryOperatorsUtil {

	public static BiConsumer<String, Instant> executionTimePrinter = (s, i) -> System.out
			.println("Thread Id :" + Thread.currentThread().getId() + "Time taken in milliseconds for " + s
					+ " execution is :" + ChronoUnit.MILLIS.between(i, Instant.now()));
	public static UnaryOperator<Double> complexCpuIntensiveFunction = d -> {
		// return Math.cbrt(d);

		double one = sqrt(cbrt((d * tan(atan(tan(atan(tan(atan(tan(
				atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(123456789.123456789)))))))))))))))))))))));
		/*double two = sqrt(cbrt((d * tan(atan(tan(atan(tan(atan(tan(
				atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(123456789.123456789)))))))))))))))))))))));
		double three = sqrt(cbrt((d * tan(atan(tan(atan(tan(atan(tan(
				atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(123456789.123456789)))))))))))))))))))))));
		double four = sqrt(cbrt((d * tan(atan(tan(atan(tan(atan(tan(
				atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(123456789.123456789)))))))))))))))))))))));
		double five = sqrt(cbrt((d * tan(atan(tan(atan(tan(atan(tan(
				atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(123456789.123456789)))))))))))))))))))))));
		double six = sqrt(cbrt((d * tan(atan(tan(atan(tan(atan(tan(
				atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(123456789.123456789)))))))))))))))))))))));
		double seven = sqrt(cbrt((d * tan(atan(tan(atan(tan(atan(tan(
				atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(123456789.123456789)))))))))))))))))))))));
		double eight = sqrt(cbrt((d * tan(atan(tan(atan(tan(atan(tan(
				atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(123456789.123456789)))))))))))))))))))))));
		double nine = sqrt(cbrt((d * tan(atan(tan(atan(tan(atan(tan(
				atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(123456789.123456789)))))))))))))))))))))));
		double ten = sqrt(cbrt((d * tan(atan(tan(atan(tan(atan(tan(
				atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(123456789.123456789)))))))))))))))))))))));
		double eleven = sqrt(cbrt((d * tan(atan(tan(atan(tan(atan(tan(
				atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(123456789.123456789)))))))))))))))))))))));*/
		return sqrt(cbrt((d * tan(atan(tan(atan(tan(atan(tan(
				atan(tan(atan(one /*+ two + three + four + five + six + seven + eight + nine + ten + eleven*/)))))))))))));

	};

}
