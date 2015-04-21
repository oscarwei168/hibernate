package tw.com.oscar.jdk.v8.optional;

import tw.com.oscar.jdk.v8.optional.model.OutsourcingCompany;
import tw.com.oscar.jdk.v8.optional.model.OutsourcingStaff;
import tw.com.oscar.jdk.v8.optional.model.Person;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author oscarwei
 * @since 2015/1/11
 */
public class OptionalSample {

    public static void main(String[] args) throws Exception {

        // Generating Optional class
        Optional empty = Optional.empty();
        Optional<String> hasSomething = Optional.of("Oscar");
        Optional<String> mayHasSomething = Optional.ofNullable(null); // Optional.empty

        // Obtaining value in the Optional class
        Optional<Integer> len = hasSomething.map(String::length);
        System.out.println("String length : " + len.get());
        System.out.println(empty.orElse("Unknown..."));
        System.out.println(hasSomething.orElseGet(() -> {
            String s = "functional-style programming...";
            return s;
        }));
        hasSomething.orElseThrow(Exception::new);

        // Checking Optional class
        System.out.println(hasSomething.isPresent());
        hasSomething.ifPresent(e -> System.out.println("" + e));

        // Processing Optional class
        hasSomething.filter(e -> e.contains("o")).ifPresent(e -> System.out.println("" + e));
        hasSomething.map(String::trim).filter(e -> e.length() > 1).ifPresent(e -> System.out
                .println("" + e));

        // Some Optional class examples
        hasSomething.map(String::trim).orElse("");
        Optional<String> nullable = Optional.ofNullable(""); // Optional[]
        System.out.println("Nullable : " + nullable);
        List<String> toList = mayHasSomething.map(Collections::singletonList).orElse(Collections.emptyList());
        OptionalInt xx = OptionalInt.of(10);
        System.out.println("OptionalInt : " + xx.getAsInt());

        System.out.println("--------------------------------------");
        Function<String, OutsourcingCompany> companyFunction = OutsourcingCompany::new;
        OutsourcingCompany unknownCompany = companyFunction.apply("Unknown");

        BiFunction<String, String, Person> personFunction = Person::new;
        Person unknownPerson = personFunction.apply("Unknown", "Unknown description...");

        OutsourcingStaff oscar = genStaff("Oscar Wei");
        OutsourcingCompany company = oscar.getCompany().get();
        System.out.println("Company : " + company.getName());
        Person boss = company.getBoss();
        System.out.println("Boss : " + boss.getName());
        Person assistant = company.getAssistant().get();
//        Person assistant = company.getAssistant().orElse(unknownPerson);
        System.out.println("Assistant(1) : " + assistant.getName());

        // map()/flatMap() example
        System.out.println("Assistant(2) : " + oscar.getCompany()
                .flatMap(OutsourcingCompany::getAssistant).map(Person::getName).orElse("Unknown"));

        // Optional predicate example
        Optional<Person> beautyAssistant = findMostBeautyAssistant(Optional.of(oscar), Optional.of(company));
        System.out.println("Beauty Assistant : " + beautyAssistant.get().getName());

    }

    private static OutsourcingStaff genStaff(String username) {
        OutsourcingStaff staff = new OutsourcingStaff(username);
        OutsourcingCompany techLink = new OutsourcingCompany("Tech Link Inc.");
        Person amy = new Person("Amy", "My boss...");
        techLink.setBoss(amy);
        Person baby = new Person("Megan Fox", "My little-3...");
        techLink.setAssistant(Optional.of(baby));
        staff.setCompany(Optional.of(techLink));
        return staff;
    }

    private static Optional<Person> findMostBeautyAssistant(Optional<OutsourcingStaff> staff,
                                                            Optional<OutsourcingCompany> company) {
        if (staff.isPresent() && company.isPresent()) { // TODO not good enough
            return Optional.of(mostBeautyAssistant(staff.get(), company.get()));
        } else {
            return Optional.empty();
        }
    }

    private static Optional<Person> findMostBeautyAssistant1(Optional<OutsourcingStaff> staff,
                                                             Optional<OutsourcingCompany> company) {
        return staff.flatMap(s -> company.map(c -> mostBeautyAssistant(s, c)));
    }

    private static Person mostBeautyAssistant(OutsourcingStaff staff, OutsourcingCompany company) {
        Objects.requireNonNull(staff);
        Objects.requireNonNull(company);
        // TODO ignore implementation...
        return new Person("Megan Fox", "Oscar's little-3");
    }

}
