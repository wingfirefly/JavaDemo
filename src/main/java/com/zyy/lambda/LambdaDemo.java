package com.zyy.lambda;

import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class LambdaDemo {

    // https://docs.oracle.com/javase/8/docs/technotes/guides/language/lambda_api_jdk8.html
    // http://www.cnblogs.com/figure9/p/java-8-lambdas-insideout-language-features.html

    private Integer[] intArray = { 1, 2, 3, 4, 5, 6, 7, 8 };
    private String[] strArray = { "xiyang1", "xiyang2", "xiyang3", "yangxi4" };

    @Test
    public void testFunction() {
        // FileFilter filter = (File f) -> f.getName().endsWith("*.java"); // is
        // ok
        FileFilter filter = f -> f.getName().endsWith("*.java");

        Comparator<String> comparator = (str1, str2) -> str1.compareTo(str2);

        final int count = 2;
        Runnable run = () -> System.out.println(count);
        new Thread(run).start();

        LambdaDemoCloseable closeable = () -> {
            String message = "close";
            System.out.println(message);
        };
        closeable.aa();

        Predicate<String> p = String::isEmpty;
        p.test("");

        Function<String, String> f = "s"::concat;
        String sb = f.apply("b");

        System.out.println(sb);

        Function<String, String> f2 = str -> str + "b";
        f2.apply("s");

        System.out.println(filter);
        System.out.println(comparator);

        invoke(System.out::println);

        invoke(Math::addExact);
    }

    private void invoke(InvokeA o) {
        o.aa("hh");
    }

    private void invoke(InvokeB o) {
        int sum = o.bb(1, 2);
        System.out.println(sum);
    }

    interface InvokeA {
        void aa(Object x);
    }

    interface InvokeB {
        int bb(int a, int b);
    }

    @Test
    public void testReturnList() {
        List<String> listOfStrings = Arrays.asList(strArray);
        List<Integer> ids = listOfStrings.stream().map(str -> str.length()).collect(Collectors.toList());
        System.out.println(Arrays.toString(ids.toArray()));
    }

    @Test
    public void testStreamParallelStream() {
        List<String> listOfStrings = Arrays.asList(strArray);
        listOfStrings.parallelStream().forEach(x -> System.out.println(x));
    }

    @Test
    public void testForEach() {
        List<Integer> listOfIntegers = Arrays.asList(intArray);
        // void java.util.function.Consumer.accept(T t)
        listOfIntegers.stream().forEach(x -> System.out.println(x));
    }

    @Test
    public void testFilter() {
        List<String> listOfStrings = Arrays.asList(strArray);
        // boolean java.util.function.Predicate.test(T t)
        listOfStrings.stream().filter(str -> str.startsWith("xiyang")).forEach(x -> System.out.println(x));

        Predicate<? super String> p = new Predicate<String>() {

            @Override
            public boolean test(String t) {
                return false;
            }
        };

        listOfStrings.stream().filter(p).forEach(str -> System.out.println(str));
    }

    @Test
    public void testReduce() {
        List<Integer> listOfIntegers = Arrays.asList(intArray);
        // R java.util.function.BiFunction.apply(T t, U u)
        int sum = listOfIntegers.stream().reduce(Integer::sum).get();
        System.out.println(sum);

        List<String> listOfStrings = Arrays.asList(strArray);

        String result = listOfStrings.stream().reduce((x, y) -> x + "," + y).get();
        System.out.println(result);

        BinaryOperator<String> operator = new BinaryOperator<String>() {
            @Override
            public String apply(String t, String u) {
                System.out.println(t + "," + u);
                return t + "," + u;
            }
        };

        Optional<String> reduce = listOfStrings.stream().reduce(operator);
        String result2 = reduce.get();
        System.out.println(result2);

        // xiyang1,xiyang2 => xiyang2; xiyang2,xiyang3 => xiyang3
        String result3 = listOfStrings.stream().reduce(String::join).get();
        System.out.println(result3); // xiyang3

        System.out.println(Arrays.toString(listOfStrings.toArray()));

        String sql = listOfStrings.stream()
                .reduce((name1, name2) -> String.format("%s or name = %s", name1, name2)).get();
        System.out.println(sql);
    }

    @Test
    public void testMap() {
        List<String> listOfStrings = Arrays.asList(strArray);
        Stream<String> mapStream = listOfStrings.stream().map(x -> x + ".java");
        mapStream.forEach(x -> System.out.println(x));

        Stream<Integer> hashCodeListStream = listOfStrings.stream().map(x -> x.hashCode());
        hashCodeListStream.forEach(x -> System.out.println(x));
    }

    @Test
    public void testCollect() {
        List<Integer> allList = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5 });
        List<Integer> activeList = Arrays.asList(new Integer[] { 2, 4, 5 });
        List<Integer> groupList = Arrays.asList(new Integer[] { 1, 1, 2, 3, 3, 4, 4, 5 });

        List<Integer> suspendedList = allList.stream().filter(id -> !activeList.contains(id)).collect(Collectors.toList());
        System.out.println(Arrays.toString(suspendedList.toArray()));

        Function<Integer, String> classifier = new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                String result = String.valueOf(input);
                return result;
            }
        };

        Map<String, List<Integer>> groupMap = groupList.stream().collect(Collectors.groupingBy(classifier));
        groupMap.entrySet().forEach(entry -> {
            System.out.print(entry.getKey() + " : ");
            System.out.println(Arrays.toString(entry.getValue().toArray()));
        });

    }

}

@FunctionalInterface
interface LambdaDemoCloseable {
    void close();

    @Override
    boolean equals(Object obj);

    // void close2();
    // int hashcode();

    default void aa() {
        System.out.println("default aa");
    }
}
