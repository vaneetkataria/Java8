package com.katariasoft.technologies.Java8.interfaces.funtional.predefined.bifunction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.function.BiFunction;

import com.katariasoft.technologies.Java8.interfaces.funtional.predefined.function.SurfaceAreaAndVolumeFunctions;

import static com.katariasoft.technologies.Java8.interfaces.funtional.predefined.function.BasicMathematicalFunctions.*;

public class SurfaceAreaAndVolumeBiFunctions {

	private SurfaceAreaAndVolumeFunctions areaAndVolumeFunctions = SurfaceAreaAndVolumeFunctions.getIntance();

	public BiFunction<Double, Double, BigDecimal> cylenderVolumeCalculator() {
		BiFunction<Double, Double, BigDecimal> cylenderVolume = (r, h) -> {
			Objects.requireNonNull(r);
			Objects.requireNonNull(h);
			return BigDecimal.valueOf(Math.PI * r * r * h);
		};
		return cylenderVolume.andThen(scale2RoundingOffFunction());
	}

	public BiFunction<Double, Double, BigDecimal> cylenderSurfaceAreaCalculator() {
		BiFunction<Double, Double, BigDecimal> cylnderSurfaceAreaCalculator = (r, h) -> {
			Objects.requireNonNull(r);
			Objects.requireNonNull(h);
			return BigDecimal.valueOf(2 * Math.PI * r * h);
		};
		return cylnderSurfaceAreaCalculator.andThen(scale2RoundingOffFunction());
	}

	// Given cylender volume and height , calculate cylenderRadius
	public BiFunction<Double, Double, BigDecimal> cylenderRadiusCalculator() {
		BiFunction<Double, Double, BigDecimal> cylenderRadiusCalculator = (v, h) -> {
			Objects.requireNonNull(v);
			Objects.requireNonNull(h);
			if (h <= 0d)
				throw new ArithmeticException("Hight must be a positive fiinite value.");
			return (v <= 0d) ? BigDecimal.ZERO
					: BigDecimal.valueOf(v).divide((BigDecimal.valueOf(Math.PI).multiply(BigDecimal.valueOf(h))), 2,
							RoundingMode.HALF_UP);
		};
		return cylenderRadiusCalculator.andThen(squareRootFunction()).andThen(scale2RoundingOffFunction());
	}

	// given volume and height
	public BiFunction<Double, Double, BigDecimal> lidBothSidesPaintChargesCalculator() {
		return cylenderRadiusCalculator().andThen(areaAndVolumeFunctions.cirleAreaProviderFunction())
				.andThen(areaAndVolumeFunctions.paintChargesCalculationFunction());
	}

	public static void main(String args[]) {
		SurfaceAreaAndVolumeBiFunctions functions = new SurfaceAreaAndVolumeBiFunctions();

		System.out.println("Volume of cylender having radius 5 and height 3 is :"
				+ functions.cylenderVolumeCalculator().apply(5.0d, 3.0d));
		System.out.println("Area of cylender having radius 5 and height 3 is :"
				+ functions.cylenderSurfaceAreaCalculator().apply(5.0d, 3.0d));
		System.out.println("Radius of cylender having volume 235.62  and height 3 is :"
				+ functions.cylenderRadiusCalculator().apply(235.62d, 3.0d));
		System.out.println("Paint charges for cylender lid is : "
				+ functions.lidBothSidesPaintChargesCalculator().apply(235.62d, 3.0d));

	}

}