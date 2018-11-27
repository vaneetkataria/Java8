package com.katariasoft.technologies.Java8.streams;

import static com.katariasoft.technologies.Java8.interfaces.funtional.predefined.unaryoperator.UnaryOperatorsUtil.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import com.katariasoft.technologies.Java8.beans.Employee;
import com.katariasoft.technologies.Java8.interfaces.funtional.predefined.pridicate.employee.EmployeeFilterWithPredicate;
import com.katariasoft.technologies.Java8.util.employee.EmployeeList;

// For correct stream execution Associative , Non Interfering , state less , no side effects 
// lambdas are must .

//1. Create streams with multiple time parallel and sequential call ......... done  
//2. Analyze various stream methods with parallel and sequential streams .......... done 
//3. Benchmark primitive arrays for loop with sequential and parallel streams with this array .......... done
//4. Check how ConcurrentHashMap gives better performance in collectors instead of HashMap and 
//   check if we can use any concurrent map which ensures order or not .......... done
//6. Check no of threads in system common fork join pool . Increase number of threads in common pool .......... done
//7. Use custom Fork Join pool .  
//8. -Djava.util.concurrent.ForkJoinPool.common.parallelism=20

public class ParallelStreamBenchMarks {

	private static EmployeeFilterWithPredicate employeeFilterWithPredicate = new EmployeeFilterWithPredicate();

	private static final String sequntial = "sequntial";
	private static final String parallel = "parallel";
	private static final String compareFiltering = "compareFiltering";
	private static final String sumComparison = "sumComparison";
	private static final String distinctComparison = "distinctComparison";
	private static final String sortingComparison = "sortingComparison";
	private static final String minMaxComparison = "minMaxComparison";
	private static final String anyMatchAllMatchNoneMatch = "anyMatchAllMatchNoneMatch";
	private static final String findFirstFindAny = "findFirstFindAny";
	private static final String concurrentCollectionComparison = "concurrentCollectionComparison";

	public static void main(String args[]) {
		String testCase = "concurrentCollectionComparison";
		switch (testCase) {
		case compareFiltering:
			executeFilteringComparisonArray(500000);
			executeFilteringComparisonList(500000);
			executeFilteringComparisonLinkedList(500000);
			executeFilteringComparisonHashSet(500000);
			executeFilteringComparisonTreeSet(500000);
			break;
		case sumComparison:
			executeSumComparison(5000000);
			break;
		case distinctComparison:
			executeDistinctComparison(500000);
			break;
		case sortingComparison:
			executeSortingComparison(500);
			break;
		case minMaxComparison:
			executeMinMaxComparison(500);
			break;
		case anyMatchAllMatchNoneMatch:
			executeAnyMatchAllMatchNoneMatch(500000);
			break;
		case findFirstFindAny:
			executeFindFirstFindAny(5000000);
			break;
		case concurrentCollectionComparison:
			executeConcurrentCollectionComparison(5000);
			break;
		default:
			break;
		}

	}

	private static void executeFilteringComparisonArray(int size) {
		Employee[] employees = EmployeeList.getLargeEmployeeListOf(size).stream().toArray(Employee[]::new);
		System.out.println("\n");
		System.out.println("######Array Source size : " + size + " Treeset sink");
		// Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is parallel .");
		Instant instantStartParallel = Instant.now();
		Arrays.stream(employees).parallel() // last one wins
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).collect(Collectors.toCollection(TreeSet::new));
		executionTimePrinter.accept(parallel, instantStartParallel);

		System.out.println("Next is sequnetial . ");
		Instant instantStartSequntial = Instant.now();
		Arrays.stream(employees).filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate())
				.map(Employee::getSalary).map(complexCpuIntensiveFunction)
				.collect(Collectors.toCollection(TreeSet::new));
		executionTimePrinter.accept(sequntial, instantStartSequntial);

		Instant instantStartLoop = Instant.now();
		Set<Double> list = new TreeSet<>();
		for (Employee e : employees)
			if (employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate().test(e)) {
				list.add(complexCpuIntensiveFunction.apply(e.getSalary()));
			}
		executionTimePrinter.accept("forEachLoop", instantStartLoop);

	}

	private static void executeFilteringComparisonList(int size) {
		System.out.println("\n");
		System.out.println("######List Source size : " + size + " List sink");
		// Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is parallel .");
		Instant instantStartParallel = Instant.now();
		EmployeeList.getLargeEmployeeListOf(size).stream().parallel() // last one wins
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).collect(Collectors.toList());
		executionTimePrinter.accept(parallel, instantStartParallel);

		System.out.println("Next is sequnetial . ");
		Instant instantStartSequntial = Instant.now();
		EmployeeList.getLargeEmployeeListOf(size).stream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).collect(Collectors.toList());
		executionTimePrinter.accept(sequntial, instantStartSequntial);

		Instant instantStartLoop = Instant.now();
		List<Double> list = new ArrayList<>();
		for (Employee e : EmployeeList.getLargeEmployeeListOf(size))
			if (employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate().test(e)) {
				list.add(complexCpuIntensiveFunction.apply(e.getSalary()));
			}
		executionTimePrinter.accept("forEachLoop", instantStartLoop);

	}

	private static void executeFilteringComparisonLinkedList(int size) {
		System.out.println("\n");
		System.out.println("######LinkedList Source size : " + size + " LinkedList sink");
		// Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is parallel .");
		Instant instantStartParallel = Instant.now();
		EmployeeList.getLargeEmployeeLinkedListOf(size).stream().parallel() // last one wins
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).collect(Collectors.toCollection(LinkedList::new));
		executionTimePrinter.accept(parallel, instantStartParallel);

		System.out.println("Next is sequnetial . ");
		Instant instantStartSequntial = Instant.now();
		EmployeeList.getLargeEmployeeLinkedListOf(size).stream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).collect(Collectors.toCollection(LinkedList::new));
		executionTimePrinter.accept(sequntial, instantStartSequntial);

		Instant instantStartLoop = Instant.now();
		List<Double> list = new LinkedList<>();
		for (Employee e : EmployeeList.getLargeEmployeeLinkedListOf(size))
			if (employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate().test(e)) {
				list.add(complexCpuIntensiveFunction.apply(e.getSalary()));
			}
		executionTimePrinter.accept("forEachLoop", instantStartLoop);

	}

	private static void executeFilteringComparisonHashSet(int size) {
		System.out.println("\n");
		System.out.println("######Hashset Source size : " + size + " Hashset sink");
		// Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is parallel .");
		Instant instantStartParallel = Instant.now();
		EmployeeList.getLargeEmployeeSetOf(size, HashSet::new).stream().parallel() // last one wins
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).collect(Collectors.toCollection(TreeSet::new));
		executionTimePrinter.accept(parallel, instantStartParallel);

		System.out.println("Next is sequnetial . ");
		Instant instantStartSequntial = Instant.now();
		EmployeeList.getLargeEmployeeSetOf(size, HashSet::new).stream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).collect(Collectors.toCollection(TreeSet::new));
		executionTimePrinter.accept(sequntial, instantStartSequntial);

		Instant instantStartLoop = Instant.now();
		Set<Double> list = new TreeSet<>();
		for (Employee e : EmployeeList.getLargeEmployeeSetOf(size, HashSet::new))
			if (employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate().test(e)) {
				list.add(complexCpuIntensiveFunction.apply(e.getSalary()));
			}
		executionTimePrinter.accept("forEachLoop", instantStartLoop);

	}

	private static void executeFilteringComparisonTreeSet(int size) {
		System.out.println("\n");
		System.out.println("######Treeset Source size : " + size + " Treeset sink");
		// Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is parallel .");
		Instant instantStartParallel = Instant.now();
		EmployeeList.getLargeEmployeeSetOf(size, TreeSet::new).stream().parallel() // last one wins
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).collect(Collectors.toCollection(TreeSet::new));
		executionTimePrinter.accept(parallel, instantStartParallel);

		System.out.println("Next is sequnetial . ");
		Instant instantStartSequntial = Instant.now();
		EmployeeList.getLargeEmployeeSetOf(size, TreeSet::new).stream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).collect(Collectors.toCollection(TreeSet::new));
		executionTimePrinter.accept(sequntial, instantStartSequntial);

		Instant instantStartLoop = Instant.now();
		Set<Double> list = new TreeSet<>();
		for (Employee e : EmployeeList.getLargeEmployeeSetOf(size, TreeSet::new))
			if (employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate().test(e)) {
				list.add(complexCpuIntensiveFunction.apply(e.getSalary()));
			}
		executionTimePrinter.accept("forEachLoop", instantStartLoop);

	}

	// ############################################################################################

	private static void executeSumComparison(int size) {
		double totalSalary = 0.0d;
		// Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is parallel .");
		Instant instantStartParallel = Instant.now();
		System.out.println("Total salary is :" + EmployeeList.getLargeEmployeeListOf(size).parallelStream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).reduce(0.0, (d1, d2) -> d1 + d2));
		executionTimePrinter.accept(parallel, instantStartParallel);

		System.out.println("Next is sequnetial . ");
		Instant instantStartSequntial = Instant.now();
		System.out.println("Total salary is :" + EmployeeList.getLargeEmployeeListOf(size).stream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).reduce(0.0, (d1, d2) -> d1 + d2));
		executionTimePrinter.accept(sequntial, instantStartSequntial);

		Instant instantStartLoop = Instant.now();
		for (Employee e : EmployeeList.getLargeEmployeeListOf(size))
			if (employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate().test(e))
				totalSalary = totalSalary + complexCpuIntensiveFunction.apply(e.getSalary());
		System.out.println("Total salary is :" + totalSalary);
		executionTimePrinter.accept("forEachLoop", instantStartLoop);

	}

	private static void executeDistinctComparison(int size) {

		System.out.println("Next is sequnetial . ");
		Instant instantStartSequntial = Instant.now();
		EmployeeList.getLargeEmployeeListOf(size).stream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).distinct().collect(Collectors.toList());
		executionTimePrinter.accept(sequntial, instantStartSequntial);

		// ordered Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is ordered parallel .");
		Instant instantStartParallel = Instant.now();
		EmployeeList.getLargeEmployeeListOf(size).parallelStream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).distinct().collect(Collectors.toList());
		executionTimePrinter.accept("parallel", instantStartParallel);

		// unordered Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is unordered parallel .");
		Instant instantStartParallelOrdered = Instant.now();
		EmployeeList.getLargeEmployeeListOf(size).parallelStream().unordered()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).distinct().collect(Collectors.toList());
		executionTimePrinter.accept("unordered_parallel", instantStartParallelOrdered);

	}

	private static void executeSortingComparison(int size) {
		System.out.println("Next is sequnetial . ");
		Instant instantStartSequntial = Instant.now();
		System.out.println(EmployeeList.getLargeEmployeeListOf(size).stream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
		executionTimePrinter.accept(sequntial, instantStartSequntial);

		// Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is parallel .");
		Instant instantStartParallel = Instant.now();
		System.out.println(EmployeeList.getLargeEmployeeListOf(size).parallelStream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
		executionTimePrinter.accept(parallel, instantStartParallel);

		// Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is parallel .");
		Instant instantStartParallelUnordered = Instant.now();
		System.out.println(EmployeeList.getLargeEmployeeListOf(size).parallelStream().unordered()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
		executionTimePrinter.accept("unordered_parallel", instantStartParallelUnordered);

	}

	private static void executeMinMaxComparison(int size) {
		System.out.println("Next is sequnetial . ");
		Instant instantStartSequntial = Instant.now();
		System.out.println("Min is" + EmployeeList.getLargeEmployeeListOf(size).stream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).reduce(Double.MAX_VALUE, (d1, d2) -> d1 <= d2 ? d1 : d2));
		executionTimePrinter.accept(sequntial, instantStartSequntial);

		// Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is parallel .");
		Instant instantStartParallel = Instant.now();
		System.out.println("Min employee is : " + EmployeeList.getLargeEmployeeListOf(size).parallelStream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).reduce(Double.MAX_VALUE, (d1, d2) -> d1 <= d2 ? d1 : d2));
		executionTimePrinter.accept(parallel, instantStartParallel);

		// Parallel unordered execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is unordered parallel .");
		Instant instantStartParallelUnordered = Instant.now();
		System.out.println("Min employee is : " + EmployeeList.getLargeEmployeeListOf(size).parallelStream().unordered()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).reduce(Double.MAX_VALUE, (d1, d2) -> d1 <= d2 ? d1 : d2));
		executionTimePrinter.accept(parallel, instantStartParallelUnordered);

	}

	private static void executeAnyMatchAllMatchNoneMatch(int size) {
		// any match
		System.out.println("Next is sequnetial . ");
		Instant instantStartSequntial = Instant.now();
		System.out.println("Any match value  is: " + EmployeeList.getLargeEmployeeListOf(size).stream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).anyMatch(d -> d < 0.0));
		executionTimePrinter.accept(sequntial, instantStartSequntial);

		// Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is parallel .");
		Instant instantStartParallel = Instant.now();
		System.out.println("Any match value  is: : " + EmployeeList.getLargeEmployeeListOf(size).parallelStream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).anyMatch(d -> d < 0.0));
		executionTimePrinter.accept(parallel, instantStartParallel);

		// unordered Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is unordered parallel .");
		Instant instantStartParallelUnordered = Instant.now();
		System.out.println("Any match value  is: : " + EmployeeList.getLargeEmployeeListOf(size).parallelStream()
				.unordered().filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate())
				.map(Employee::getSalary).map(complexCpuIntensiveFunction).anyMatch(d -> d < 0.0));
		executionTimePrinter.accept("unordered_parallel", instantStartParallelUnordered);

		System.out.println("\n");
		System.out.println("#####All match#######");

		// all match
		System.out.println("Next is sequnetial . ");
		Instant instantStartSequntialAllMatch = Instant.now();
		System.out.println("All match value  is: " + EmployeeList.getLargeEmployeeListOf(size).stream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).allMatch(d -> d < 0.0));
		executionTimePrinter.accept(sequntial, instantStartSequntialAllMatch);

		// Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is parallel .");
		Instant instantStartParallelAllMatch = Instant.now();
		System.out.println("All match value  is: : " + EmployeeList.getLargeEmployeeListOf(size).parallelStream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).allMatch(d -> d < 0.0));
		executionTimePrinter.accept(parallel, instantStartParallelAllMatch); // none match

		// Unordered Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is unordered parallel .");
		Instant instantStartParallelAllMatchUnordered = Instant.now();
		System.out.println("All match value  is: : " + EmployeeList.getLargeEmployeeListOf(size).parallelStream()
				.unordered().filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate())
				.map(Employee::getSalary).map(complexCpuIntensiveFunction).allMatch(d -> d < 0.0));
		executionTimePrinter.accept("unordered_parallel", instantStartParallelAllMatchUnordered); // none match

		System.out.println("\n");
		System.out.println("######None Match######");

		System.out.println("Next is sequnetial . ");
		Instant instantStartSequntialNoneMatch = Instant.now();
		System.out.println("None match value is : " + EmployeeList.getLargeEmployeeListOf(size).stream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).noneMatch(d -> d < 0.0));
		executionTimePrinter.accept(sequntial, instantStartSequntialNoneMatch);

		// Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is parallel .");
		Instant instantStartParallelNoneMatch = Instant.now();
		System.out.println("None match value is : " + EmployeeList.getLargeEmployeeListOf(size).parallelStream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).noneMatch(d -> d < 0.0));
		executionTimePrinter.accept(parallel, instantStartParallelNoneMatch);

		// Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is unordered parallel .");
		Instant instantStartParallelNoneMatchUnordred = Instant.now();
		System.out.println("None match value is : " + EmployeeList.getLargeEmployeeListOf(size).parallelStream()
				.unordered().filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate())
				.map(Employee::getSalary).map(complexCpuIntensiveFunction).noneMatch(d -> d < 0.0));
		executionTimePrinter.accept("unordred_parallel", instantStartParallelNoneMatchUnordred);

	}

	private static void executeFindFirstFindAny(int size) {
		// any match
		System.out.println("#####Find First#######");
		System.out.println("Next is sequnetial . ");
		Instant instantStartSequntial = Instant.now();
		EmployeeList.getLargeEmployeeListOf(size).stream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).filter(d -> d > 0.0).findFirst().ifPresent(System.out::println);
		executionTimePrinter.accept(sequntial, instantStartSequntial);

		// Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is parallel .");
		Instant instantStartParallel = Instant.now();
		EmployeeList.getLargeEmployeeListOf(size).parallelStream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).filter(d -> d > 0.0).findFirst().ifPresent(System.out::println);
		executionTimePrinter.accept(parallel, instantStartParallel);

		// unordered Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is unordered parallel .");
		Instant instantStartParallelUnordered = Instant.now();
		EmployeeList.getLargeEmployeeListOf(size).parallelStream().unordered()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).filter(d -> d > 0.0).findFirst().ifPresent(System.out::println);
		executionTimePrinter.accept("unordered_parallel", instantStartParallelUnordered);

		System.out.println("\n");
		System.out.println("#####Find Any#######");

		// all match
		System.out.println("Next is sequnetial . ");
		Instant instantStartSequntialAllMatch = Instant.now();
		EmployeeList.getLargeEmployeeListOf(size).stream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).filter(d -> d > 0.0).findAny().ifPresent(System.out::println);
		executionTimePrinter.accept(sequntial, instantStartSequntialAllMatch);

		// Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is parallel .");
		Instant instantStartParallelAllMatch = Instant.now();
		EmployeeList.getLargeEmployeeListOf(size).parallelStream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).filter(d -> d > 0.0).findAny().ifPresent(System.out::println);
		executionTimePrinter.accept(parallel, instantStartParallelAllMatch); // none match

		// Unordered Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is unordered parallel .");
		Instant instantStartParallelAllMatchUnordered = Instant.now();
		EmployeeList.getLargeEmployeeListOf(size).parallelStream().unordered()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction).filter(d -> d > 0.0).findAny().ifPresent(System.out::println);
		executionTimePrinter.accept("unordered_parallel", instantStartParallelAllMatchUnordered); // none match

	}

	private static void executeConcurrentCollectionComparison(int size) {

		System.out.println("Next is sequnetial collecting in normal hashmap . ");
		Instant instantStartSequntial = Instant.now();
		Map<Double, String> map1 = EmployeeList.getLargeEmployeeListOf(size).stream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction)
				.collect(Collectors.toMap(d -> d, d -> d + "", (d1, d2) -> String.join(",", d1, d2)));
		executionTimePrinter.accept(sequntial, instantStartSequntial);

		// ordered Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is ordered parallel collecting in hasmap .");
		Instant instantStartParallel = Instant.now();
		Map<Double, String> map2 = EmployeeList.getLargeEmployeeListOf(size).parallelStream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction)
				.collect(Collectors.toMap(d -> d, d -> d + "", (d1, d2) -> String.join(",", d1, d2)));
		executionTimePrinter.accept("parallel", instantStartParallel);

		// unordered Parallel execution
		System.out.println("Number of cpu cores are :" + Runtime.getRuntime().availableProcessors());
		System.out.println("Next is parallel collecting in concurrent Map .");
		Instant instantStartParallelOrdered = Instant.now();
		Map<Double, String> map3 = EmployeeList.getLargeEmployeeListOf(size).parallelStream()
				.filter(employeeFilterWithPredicate.getPromotionEligibleEmployeesPredicate()).map(Employee::getSalary)
				.map(complexCpuIntensiveFunction)
				.collect(Collectors.toConcurrentMap(d -> d, d -> d + "", (d1, d2) -> String.join(",", d1, d2)));
		executionTimePrinter.accept("unordered_parallel", instantStartParallelOrdered);

	}

}
