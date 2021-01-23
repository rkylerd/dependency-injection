package spellcheck;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;
import java.net.URL;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.*;

class SpellingCheckerTest {

    private SpellingChecker checker;
    private Injector injector;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        injector = Guice.createInjector(new SpellingModule());
    }

    @org.junit.jupiter.api.Test
    void checkURL() {

        try {

            checker = injector.getInstance(SpellingChecker.class);

            SortedMap<String, Integer> mistakes = checker.check(null);

            assertEquals(mistakes.toString(), "{funtion=1, tere=1, varable=1}");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}