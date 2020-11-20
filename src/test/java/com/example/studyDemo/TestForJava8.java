package com.example.studyDemo;

import com.google.common.cache.RemovalListener;
import org.assertj.core.util.Strings;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.SystemTray;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-03-25
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestForJava8 {

    @Test
    public void test1() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        Long aLong = list.stream().collect(Collectors.counting());
        System.out.println(aLong);
    }

//    参考的java8文章
//    https://blog.csdn.net/icarusliu/article/details/79495534

    @Test
    public void test2() {
        Consumer f = System.out::println;
        f.accept("test1--------");
        Consumer f2 = n -> System.out.println(n + "-F2");
//        先执行f的accept，再执f2的accept
        f.andThen(f2).accept("test");
        f2.accept("test2====");
//accept 接受一个输入的参数
// andthen：调用当前Consumer后是否还要调用其它的Consumer,接受一个consumer
        f.andThen(f).andThen(f).andThen(f).accept("ddd");
    }

    @Test
    public void test3() {
        Function<Integer, Integer> f = s -> s + 1;
        Function<Integer, Integer> g = s -> s * 2;
//        apply：表示输入所传的，并且输出对应的
        System.out.println(f.apply(1));

        /**
         * 下面表示: 在执行f的时候，先执行g，然后再将g的输出当作f的输入
         * 相当于以下代码：
         * Integer a = g.apply(1);
         * System.out.println(f.apply(a));
         */
        System.out.println(f.compose(g).apply(2));


        /**
         * 表示：先执行f的apply，然后将其返回值作为g的输入值，再执行g的apply
         */
        System.out.println(f.andThen(g).apply(3));

        /**
         * identity方法会返回一个不进行任何处理的Function，即输入和输出相等
         */
        System.out.println(Function.identity().apply("a"));
    }

    @Test
    public void test4() {
        Predicate<String> p = s -> s.equals("test");
        Predicate<String> g = s -> s.equals("admin");
        System.out.println("======");
        /**       test：表示传入值进行运算
         *       negate：表示 对原来的Predicate做取反处理
         */
        System.out.println(p.negate().test("rest"));

        /**
         * and: 针对同一输入值，多个Predicate都返回true时才返回true，否则返回false
         */
        System.out.println(p.and(g).test("test"));

        /**
         * or：针对同一输入值，多个Predicate中只要有一个返回true，就返回true，全部返回false时才返回false
         */
        System.out.println(p.or(g).test("test"));

    }

    @Test
    public void test5() {
        //创建一个空的Stream对象
        Stream stream = Stream.empty();

        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
//      获得串行流
        Stream<String> listStream = list.stream();
//        获得并行流
        Stream<String> parallelStream = list.parallelStream();

//        通过Stream的of方法创建
        Stream<String> test = Stream.of("test");
        Stream<String> test2 = Stream.of("a", "d", "t");


//        下面的那个方法java8好像不支持,需要高版本的,以后再学
//        Stream.iterate(1, s -> s + 1).limit(10).forEach(System.out::println);
// public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f);
//public static<T> Stream<T> iterate(T seed, Predicate<? super T> hasNext, UnaryOperator<T> next)

//        Stream.iterate(1, new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer integer) {
//
//                return integer <= 10;
//            }
//        }, new UnaryOperator<Integer>() {
//            @Override
//            public Integer apply(Integer integer) {
//                return integer + 1;
//            }
//        }).forEach(System.out::println);


        Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        }).limit(10).forEach(System.out::println);
//      上面的简写方式
        Stream.generate(() -> Math.random() * 10).limit(10).forEach(System.out::println);
    }

    @Test
    public void test6() {
        Stream<String> stringStream = Stream.of("test", "a", "be", "c");
        //查找所有包含t的元素并进行打印,,下面两种写法都可以
//        stringStream.filter(s -> s.contains("e")).forEach(s -> System.out.println(s));
        stringStream.filter(s -> s.contains("e")).forEach(System.out::println);

//        将一个String类型的Stream对象中的每个元素添加相同的后缀.txt，如a变成a.txt，
        Stream<String> s2 = Stream.of("test", "a", "be", "c");
        s2.map(s -> s.concat(".txt")).forEach(System.out::println);

//        要对一个String类型的Stream进行处理，将每一个元素的拆分成单个字母，
        Stream<String> s3 = Stream.of("test", "a", "be", "c");
        s3.flatMap(s -> Stream.of(s.split(""))).forEach(System.out::println);

//        Java9的语法
//        如果Stream是有序的（Ordered），那么返回最长命中序列（符合传入的Predicate的最长命中序列）组成的Stream；
//        如果是无序的，那么返回的是所有符合传入的Predicate的元素序列组成的Stream。
//        Stream<String> s4 = Stream.of("test", "t1", "t2", "teeeee", "aaaa", "taaa");
//(因为是无序的)以下结果将打印： "test", "t1", "t2", "teeeee"，最后的那个taaa不会进行打印
//        s.takeWhile(n -> n.contains("t")).forEach(System.out::println);

    }

    @Test
    public void test7() {
//        创建一个空的Optional对象；其value属性为Null。
        Optional<Object> empty = Optional.empty();
        System.out.println(empty + "---");
//        根据传入的值构建一个Optional对象;
//传入的值必须是非空值，否则如果传入的值为空值，则会抛出空指针异常。
        Optional<String> test = Optional.of("test");
        System.out.println(test + "====");
        System.out.println(test.get());
        Optional<Object> nullOptional = Optional.ofNullable("sss");
//     ifPresent   当Value不为空时，执行传入的Consumer；
//        nullOptional.ifPresent(s-> System.out.println(s));
    }

    @Test
    public void test8() {
        Stream<Integer> s = Stream.of(1, 2, 3, 4, 5, 6);
        /**
         * 一个参数的reduce
         * 求和:可以简写成下面的式子:
         * Integer sum=s.reduce((a,b)->a+b).get()
         */
        Integer sum = s.reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).orElse(0);
//        或者可以写成.orElseGet(()->0);
        System.out.println(sum);

        /**
         * 两个参数的reduce
         * 一个参数的方法来说，它多了一个T类型的参数；实际上就相当于需要计算的值在Stream的基础上多了一个初始化的值
         * 不同点就是初始化的值先和第一个元素二合运算,之后再和其他元素二合运算
         */
        Stream<String> s2 = Stream.of("test", "t1", "t2", "teeeee", "aaaa", "taaa");
        System.out.println(s2.reduce("first", (a, b) -> a.concat(b)));

        /**
         * 三个参数的reduce.
         * 定义:
         * <U> U reduce(U identity,
         *                  BiFunction<U, ? super T, U> accumulator,
         *                  BinaryOperator<U> combiner)
         *  三个参数:
         *  identity: 一个初始化的值；这个初始化的值其类型是泛型U，与Reduce方法返回的类型一致；
         *              注意此时Stream中元素的类型是T，与U可以不一样也可以一样，
         *  accumulator: 其类型是BiFunction，输入是U与T两个类型的数据，而返回的是U类型；
         *              也就是说返回的类型与输入的第一个参数类型是一样的，而输入的第二个参数类型与Stream中元素类型是一样的。
         *  combiner: 其类型是BinaryOperator，支持的是对U类型的对象进行操作；
         *
         * 第三个参数combiner主要是使用在并行计算的场景下；如果Stream是非并行时，第三个参数实际上是不生效的。
         * 因此针对这个方法的分析需要分并行与非并行两个场景。
         */
//        非并行
//        如果Stream是非并行的，combiner不生效；
//        其计算过程与两个参数时的Reduce基本是一致的。
//        如假设U的类型是ArrayList，那么可以将Stream中所有元素添加到ArrayList中再返回了，
        Stream<String> s3 = Stream.of("aa", "ab", "c", "ad");
        System.out.println(s3.reduce(new ArrayList<String>(),
                new BiFunction<ArrayList<String>, String, ArrayList<String>>() {
                    @Override
                    public ArrayList<String> apply(ArrayList<String> u, String s) {
                        u.add(s);
                        return u;
                    }
                }, new BinaryOperator<ArrayList<String>>() {
                    @Override
                    public ArrayList<String> apply(ArrayList<String> strings, ArrayList<String> strings2) {
                        return strings;
                    }
                }));
//也可以进行元素过滤，即模拟Stream中的Filter函数：
        Stream<String> s4 = Stream.of("aa", "ab", "c", "ad");
        Predicate<String> predicate = t -> t.contains("a");
        s4.reduce(new ArrayList<String>(), new BiFunction<ArrayList<String>, String, ArrayList<String>>() {
                    @Override
                    public ArrayList<String> apply(ArrayList<String> strings, String s) {
                        if (predicate.test(s)) strings.add(s);
                        return strings;
                    }
                },
                new BinaryOperator<ArrayList<String>>() {
                    @Override
                    public ArrayList<String> apply(ArrayList<String> strings, ArrayList<String> strings2) {
                        return strings;
                    }
                }).stream().forEach(System.out::println);

    }
}