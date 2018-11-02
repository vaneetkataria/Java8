package com.katariasoft.technologies.Java8.interfaces.funtional.predefined.function;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;
import static com.katariasoft.technologies.Java8.interfaces.funtional.predefined.pridicate.MiscPredicates.*;

public class MathematicalFunctions {

	public Function<BigDecimal, BigDecimal> scale2RoundingOffFunction() {
		return b -> b.setScale(2, RoundingMode.HALF_UP);
	}

	public Function<BigDecimal, BigDecimal> cubeRootFunction() {
		return scale2RoundingOffFunction().compose(
				v -> bigDecimalNullOrZero().test(v) ? BigDecimal.ZERO : BigDecimal.valueOf(Math.cbrt(v.doubleValue())));
	}

	public Function<BigDecimal, BigDecimal> squareRootFunction() {
		return scale2RoundingOffFunction().compose(
				v -> bigDecimalNullOrZero().test(v) ? BigDecimal.ZERO : BigDecimal.valueOf(Math.sqrt(v.doubleValue())));
	}

	// volume = 1/2 pi r cube
	// r = cube root of ((2 * volume)/pi)
	public Function<BigDecimal, BigDecimal> radiusOfHalfSphereProviderFunction() {
		return scale2RoundingOffFunction()
				.compose(cubeRootFunction().compose(v -> bigDecimalNullOrZero().test(v) ? BigDecimal.ZERO
						: ((v.multiply(BigDecimal.valueOf(2.0))).divide(BigDecimal.valueOf(Math.PI), 2,
								RoundingMode.HALF_UP))));
	}

	public Function<BigDecimal, BigDecimal> cirleAreaProviderFunction() {
		return scale2RoundingOffFunction().compose(b -> bigDecimalNullOrZero().test(b) ? BigDecimal.ZERO
				: (BigDecimal.valueOf(Math.PI).multiply(b).multiply(b)));
	}

	public Function<BigDecimal, BigDecimal> hemispherePlateAreaFinder() {
		return cirleAreaProviderFunction().compose(radiusOfHalfSphereProviderFunction());
	}

	public Function<BigDecimal, BigDecimal> paintChargesCalculationFunction() {
		return scale2RoundingOffFunction().compose(b -> bigDecimalNullOrZero().test(b) ? BigDecimal.ZERO
				: b.multiply(BigDecimal.valueOf(5.0).multiply(BigDecimal.valueOf(2.0))));
	}

	public Function<BigDecimal, BigDecimal> paintChargesForHemispherePlateFunction() {
		return paintChargesCalculationFunction()
				.compose(hemispherePlateAreaFinder().compose(radiusOfHalfSphereProviderFunction()));
	}

	public static void main(String args[]) {
		MathematicalFunctions functions = new MathematicalFunctions();

		System.out.println("Scale 2 Value of 570.0190876 is : "
				+ functions.scale2RoundingOffFunction().apply(BigDecimal.valueOf(570.0190876)));

		System.out.println("Radius of half hemisphere having volume 862.48 is : "
				+ functions.radiusOfHalfSphereProviderFunction().apply(BigDecimal.valueOf(862.48)));

		System.out.println("CubeRoot of 627 is : " + functions.cubeRootFunction().apply(BigDecimal.valueOf(627)));
		System.out.println("squareRoot of 627 is : " + functions.squareRootFunction().apply(BigDecimal.valueOf(627)));

		System.out.println("Area Of the circle having radius of 8.19 is : "
				+ functions.cirleAreaProviderFunction().apply(BigDecimal.valueOf(8.19)));

		System.out.println("Area Of the plate having volume  of hemisphere 862.48 is : "
				+ functions.hemispherePlateAreaFinder().apply(BigDecimal.valueOf(862.48)));

		System.out.println("Paint chnarges for a palte for a hemisphere having volume 862.48 is : "
				+ functions.paintChargesForHemispherePlateFunction().apply(BigDecimal.valueOf(862.48)));

	}

}
