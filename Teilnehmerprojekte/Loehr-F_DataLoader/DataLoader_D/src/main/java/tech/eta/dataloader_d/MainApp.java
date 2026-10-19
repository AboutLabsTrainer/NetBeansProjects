package tech.eta.dataloader_d;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class MainApp {
    // We could dynamically grab the current class like this, to avoid hardcoding:
    // Class<?> THIS_CLASS = MethodHandles.lookup().lookupClass();
    private static final Class<?> THIS_CLASS = MainApp.class;
    private static final String ROOT_PACKAGE = THIS_CLASS.getPackageName();
    private static final String APP_NAME = "DataLoader";
    private static final Logger log = LogManager.getLogger(MainApp.class);
    // context.getApplicationName() is just an empty string...

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(THIS_CLASS, args); // => actual Spring Boot startup sequence
        // Runners (CommandLineRunner or ApplicationRunner) run at the end of the Spring Boot startup sequence.
        // To be detected, they must be registered as Spring beans via:
        // 1. A @Bean method inside a @Configuration class.
        // 2. A class implementing the interface, annotated with a stereotype (usually @Component).
        //    Avoid mixing responsibilities. Even though all stereotypes are components, you should only sometimes use:
        //      - @Repository: To prime an in-memory cache or load mock data.
        //      - @Service: For daemons (e.g., queue consumers, background loops, event-listeners).
        //
        // TLDR: simple => @Bean, complex => @Component, priming cache/daemon => directly in the @Repository or @Service
        // You can define their order by using @Order(1), @Order(2), ... for the first, second, n-th to run
        hello_springboot(context);
    }

    static void hello_springboot(ApplicationContext context) {
        // todo: log bean count, log actually relevant info

        // >>> Greetings:
        System.out.println("==================================================");
        System.out.println("Hello Springboot. Greetings from " + APP_NAME);
        System.out.println("==================================================");

        // >>> Show total bean count:
        System.out.println("Total Beans Loaded: " + context.getBeanDefinitionCount());
        System.out.println("==================================================");

        // >>> Show filtered for our own package:
        System.out.println("Application Beans in package [" + ROOT_PACKAGE + "]:");

        // using atomic variables we can update them safely in streams. Normal variables cant be updated in streams.
        AtomicInteger counter = new AtomicInteger(0);
        Arrays.stream(context.getBeanDefinitionNames())
                // .filter(Objects::nonNull) // => stream already is NotNull
                // Objects.requireNonNull(obj) allows a default param too...
                .filter(beanName -> isSubPackage(context, beanName).orElse(false))
                .forEach(beanName -> {
                    printBeanStatusLine(context, beanName);
                    counter.incrementAndGet();
                });

        System.out.println("==================================================");

        log.info("Started {} with {} beans. ({} defined by us.)",
                APP_NAME,
                context.getBeanDefinitionCount(),
                counter.get());
    }

    private static void printBeanStatusLine(@NonNull ApplicationContext context, @NonNull String beanName) {
        System.out.printf(" -> %s (%s)%n", beanName, context.getBean(beanName).getClass().getSimpleName());
    }

    private static Optional<Boolean> isSubPackage(@NonNull ApplicationContext context, @NonNull String beanName) {
        try {
            // Get the actual class type of the bean (this could fail, if beanName was wrong)
            Class<?> beanType = context.getType(beanName);
            // Only include it if it belongs to our application's package
            return Optional.of(beanType != null && beanType.getPackageName().startsWith(ROOT_PACKAGE));
        } catch (NoSuchBeanDefinitionException e) {
            return Optional.empty();
        }
    }

}



/*
* Atomic variablen und eigenschaften von Objekten zur lösung von streams...
* AtomicLong => .AddAndGet(5)
* ... anschauen
*
* .peak ist performance-technisch eine Katastrophe
*
* Bei streams sollte man in funktionen oder methodcalls auslagern, um den stream übersichtlich zu halten.
*
* */

// Note: Non-atomic variables can't be modified in forEach, and .count and .forEach both terminate the stream.
//      Abusing .peek as a non-terminating .forEach is horrible for performance AND might get optimized away.
//      A supplier keeps the code for a stream in one place, but builds a new stream for every .get() call.
// Supplier<Stream<String>> mySupplier = () -> Stream.of("A1", "B1", "C1", "A2", "B2", "C2", "A3", "B3", "C3")
// List<String> Bs = mySupplier.get().fiter(s->s.startsWith("B")).toList(); // first stream gets build
// List<String> Cs = mySupplier.get().fiter(s->s.startsWith("C")).toList(); // second stream gets build


/* Status:
 * 2026-09-21   Database holds user with userroles (currently as list of strings) in a single table
 * 2026-09-23   Prototype for enum-based roles, prototype for logging to the database.
 * 2026-09-24   UserConfig now capable of reading jsons with numeric values for individual roles.
 *              Note that the version coming from the endpoint cant accept directly into UserConfig
 *              ... might be solved via bettering the JSON-Decoder-Props of the Controller?
 * 2026-09-28   Users now store in one table, their roles in a separate table user_roles
 *              UserConfig is now using a better method for reading the json.
 *              ... This allowed using it directly in the endpoint. First rebuild took 17 minutes.
 * --- nothing till monday 11:30
 *
 */

/*  TODO:
 * User => in die Datenbank
 * SQL-Initialisierung: Springboot kann beim startup SQL-Code ausführen
 *
 * @NonNull und @Nullable von JSpecify einbauen. flaechendeckend.
 *
 * 0. NonNull & Nullable  ... todo: partial
 *
 * Nullable, userroles => Set, Collectiontable, damit rollen in separater Tabelle verstaut werden
 *
 * TODO: - database holds users with roles (roles not yet split to separate table. see notes at User-entity.)
 *       - user can be added to database via post-endpoint at /api/user/create (I had to resort to using JavaScript.
 *          I couldn't get it to work with thymeleaf. Also thymeleaf requires reloading the page...)
 *          I should refactor the urls to /api/users/current, /api/users/create, ...
 *          updating is basically the same operation but with other access rights. removing, etc is ... less relevant
 *  
 *
 * 1. Logging und User in die Datenbank legen => wird ca ne Woche brauchen... bis mans halt gelernt hat. dann vllt nen halben Tag.  !!!
 *   => enum mit userrolesliste ...  todo: user laufen, enum bisher nicht verwendet...  24.09. jetzt werden userroles verwendet
 *   => mit den logs anfangen ...    oops... habs vergessen  todo 24.09. sind jetzt auch drin
 *  ==>> zuerst:   H2 database für den start (anstelle von MySql) => Hybernat H2 Database springboot. (Läuft per springboot) todo: laeuft
 *
 * 2. Konsole mit parser: "create type message ..." mit eigener micro-language ==>> comando-controlled layer
 *   => kommando-interpreter. Kann man das irgendwie plugin-isieren?
 *
 */