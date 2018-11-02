package com.katariasoft.technologies.Java8.interfaces.funtional.predefined.pridicate;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Predicate;

public class MiscPredicates {

	public static Predicate<BigDecimal> bigDecimalNullOrZero() {
		return b -> Objects.isNull(b) || BigDecimal.ZERO.compareTo(b) == 0;
	}

}
