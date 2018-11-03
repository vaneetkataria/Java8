package com.katariasoft.technologies.Java8.interfaces.funtional.predefined.function.primitive;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.LongFunction;
import java.util.function.LongToIntFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import com.katariasoft.technologies.Java8.beans.Employee;
import com.katariasoft.technologies.Java8.util.employee.EmployeeList;

import static com.katariasoft.technologies.Java8.interfaces.funtional.predefined.pridicate.BasicValidationPredicates.*;
import static com.katariasoft.technologies.Java8.interfaces.funtional.predefined.function.BasicMathematicalFunctions.*;

public class BasicPrimitiveFunctions {

	private List<Employee> employees = EmployeeList.get();
	// assuming special card will be given to 100000 people only.
	private long specialCardSeqStartNo = 1000_000_000L;

	// intFunction
	public IntFunction<List<Employee>> functionToReturnFirstNEmployees() {
		return i -> i <= 0 ? null : employees.size() < i ? employees : employees.subList(0, i);
	}

	// toIntFunction
	public ToIntFunction<List<Employee>> funtionToGetNumberOfEmployeesTechLead() {
		return l -> {
			int numTls = 0;
			for (Employee e : l)
				if (e.getDesignation().equalsIgnoreCase("techlead"))
					numTls++;
			return numTls;
		};
	}

	// intToDouble
	public IntToDoubleFunction getCircleAreaFunction() {
		return r -> Math.PI * r * r;
	}

	// IntToLong function
	public IntToLongFunction getEpochTimeNumDaysAheadFunction() {
		return i -> System.currentTimeMillis() + i * 24 * 60 * 60 * 1000;
	}

	// longFunction
	public LongFunction<Date> epochToDateConvertorFunction() {
		return l -> new Date(l);
	}

	// toLongFunction
	public ToLongFunction<Date> dateToEpochTimeConvertor() {
		return d -> d.getTime();
	}

	// longToInt
	public LongToIntFunction specialCardSequenceNoFinderFunction() {
		return l -> (int) (l <= 0 ? 0 : l < this.specialCardSeqStartNo ? 0 : l - this.specialCardSeqStartNo);
	}

	// longToDouble

	// double function.
	public DoubleFunction<BigDecimal> sphereVolumeFinderFunction() {
		return r -> BigDecimal.valueOf(Math.PI).multiply(BigDecimal.valueOf(r)).multiply(BigDecimal.valueOf(r))
				.multiply(BigDecimal.valueOf(r));
	}

	// toDouble function
	public ToDoubleFunction<BigDecimal> circleAreaToRadiusFunction() {
		return a -> bigDecimalNullOrZero().test(a) ? 0.0d
				: squareRootFunction().apply(a.divide(BigDecimal.valueOf(Math.PI), 2, RoundingMode.HALF_UP))
						.doubleValue();

	}

	// doubleToLong
	// doubleToint

	public static void main(String args[]) {
		BasicPrimitiveFunctions miscPrimitiveFunctions = new BasicPrimitiveFunctions();
		System.out.println(miscPrimitiveFunctions.functionToReturnFirstNEmployees().apply(10));
		System.out
				.println(miscPrimitiveFunctions.funtionToGetNumberOfEmployeesTechLead().applyAsInt(EmployeeList.get()));
		System.out.println(miscPrimitiveFunctions.getCircleAreaFunction().applyAsDouble(5));
		System.out.println(miscPrimitiveFunctions.getEpochTimeNumDaysAheadFunction().applyAsLong(5));
		System.out.println(miscPrimitiveFunctions.epochToDateConvertorFunction().apply(new Date().getTime()));
		System.out.println(miscPrimitiveFunctions.dateToEpochTimeConvertor().applyAsLong(new Date()));
		System.out.println(miscPrimitiveFunctions.specialCardSequenceNoFinderFunction().applyAsInt(1000_000_111L));
		System.out.println(miscPrimitiveFunctions.sphereVolumeFinderFunction().apply(5.0d));
		System.out.println(
				miscPrimitiveFunctions.circleAreaToRadiusFunction().applyAsDouble(BigDecimal.valueOf(210.73d)));

	}
}
