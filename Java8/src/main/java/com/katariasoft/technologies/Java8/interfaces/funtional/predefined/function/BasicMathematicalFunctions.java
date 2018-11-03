package com.katariasoft.technologies.Java8.interfaces.funtional.predefined.function;

import static com.katariasoft.technologies.Java8.interfaces.funtional.predefined.pridicate.BasicValidationPredicates.bigDecimalNullOrZero;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

public class BasicMathematicalFunctions {

	public static Function<BigDecimal, BigDecimal> scale2RoundingOffFunction() {
		return b -> bigDecimalNullOrZero().test(b) ? BigDecimal.ZERO : b.setScale(2, RoundingMode.HALF_UP);
	}

	public static Function<BigDecimal, BigDecimal> cubeRootFunction() {
		return scale2RoundingOffFunction().compose(
				v -> bigDecimalNullOrZero().test(v) ? BigDecimal.ZERO : BigDecimal.valueOf(Math.cbrt(v.doubleValue())));
	}

	public static Function<BigDecimal, BigDecimal> squareRootFunction() {
		return scale2RoundingOffFunction().compose(
				v -> bigDecimalNullOrZero().test(v) ? BigDecimal.ZERO : BigDecimal.valueOf(Math.sqrt(v.doubleValue())));
	}

}
