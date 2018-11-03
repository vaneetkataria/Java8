package com.katariasoft.technologies.Java8.interfaces.funtional.predefined.function;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;
import static com.katariasoft.technologies.Java8.interfaces.funtional.predefined.pridicate.BasicValidationPredicates.*;
import static com.katariasoft.technologies.Java8.interfaces.funtional.predefined.function.BasicMathematicalFunctions.*;

public class SurfaceAreaAndVolumeFunctions {

	private Function<BigDecimal, BigDecimal> radiusOfHemiSphereProviderFunction = radiusOfHalfSphereProviderFunction();

	private SurfaceAreaAndVolumeFunctions() {
	}

	public static SurfaceAreaAndVolumeFunctions getIntance() {
		return new SurfaceAreaAndVolumeFunctions();
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
		return paintChargesCalculationFunction().compose(hemispherePlateAreaFinder());
	}

	// function to find charges to paint a plate single area with radius r .
	public Function<BigDecimal, BigDecimal> singlePlateAreaPainChargesCalculationFunction() {
		return cirleAreaProviderFunction().andThen(paintChargesCalculationFunction());
	}

	public Function<Integer, String> IntegerToStringFunction() {
		return i -> i + "";
	}

	public Function<String, Double> stringToDoubleFunction() {
		return s -> Double.valueOf(s);
	}

	public Function<Double, String> doubleToStringFunction() {
		return d -> d + "";
	}

	public Function<Integer, String> integerToDoubleValStringRep() {
		// return
		// IntegerToStringFunction().andThen(stringToDoubleFunction()).andThen(doubleToStringFunction());
		return doubleToStringFunction().compose(stringToDoubleFunction()).compose(IntegerToStringFunction());
	}

	public static void main(String args[]) {
		SurfaceAreaAndVolumeFunctions functions = new SurfaceAreaAndVolumeFunctions();

		System.out.println("Scale 2 Value of 570.0190876 is : "
				+ scale2RoundingOffFunction().apply(BigDecimal.valueOf(570.0190876)));

		System.out.println("Radius of half hemisphere having volume 862.48 is : "
				+ functions.radiusOfHalfSphereProviderFunction().apply(BigDecimal.valueOf(862.48)));

		System.out.println("CubeRoot of 627 is : " + cubeRootFunction().apply(BigDecimal.valueOf(627)));
		System.out.println("squareRoot of 627 is : " + squareRootFunction().apply(BigDecimal.valueOf(627)));

		System.out.println("Area Of the circle having radius of 8.19 is : "
				+ functions.cirleAreaProviderFunction().apply(BigDecimal.valueOf(8.19)));

		System.out.println("Area Of the plate having volume  of hemisphere 862.48 is : "
				+ functions.hemispherePlateAreaFinder().apply(BigDecimal.valueOf(862.48)));

		System.out.println("Paint chnarges for a palte for a hemisphere having volume 862.48 is : "
				+ functions.paintChargesForHemispherePlateFunction().apply(BigDecimal.valueOf(862.48)));

		System.out.println("Paint chnarges on double side of a palte with radius 8.19 : "
				+ functions.singlePlateAreaPainChargesCalculationFunction().apply(BigDecimal.valueOf(8.19)));

		System.out.println("Integer value 5 represented in double as string is "
				+ functions.integerToDoubleValStringRep().apply(5));

	}

}
