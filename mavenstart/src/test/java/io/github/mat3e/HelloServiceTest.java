package io.github.mat3e;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {


    private final static String WELCOME = "Hello";

    @Test
    public void test_PrepareGreeting_nullName_returnsGreetingWithFallback() {
        //given
        var mockreposiotry = alwaysReturningHelloRepository(WELCOME);
        var SUT = new HelloService(mockreposiotry); //system under test

        //when
        var result = SUT.prepareGreeting(null, "-1");

        //then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }


    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {
        //given

        var mockreposiotry = alwaysReturningHelloRepository(WELCOME);
        var SUT = new HelloService(); //system under test
        String name = "test";

        //when
        var result = SUT.prepareGreeting(name, "-1");

        //then
        assertEquals(WELCOME + " " + name + "!", result);
    }

    @Test
    public void test_PrepareGreeting_nullLang_returnsGreetingWithFallbackIdLang() {
        //given
        var fallbackIdWelcome = "Hola";
        var mockreposiotry = new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, fallbackIdWelcome, null));
                }
                return Optional.empty();
            }
        };
        var SUT = new HelloService(mockreposiotry); //system under test

        //when
        var result = SUT.prepareGreeting(null, null);

        //then
        assertEquals(fallbackIdWelcome+ " " + HelloService.FALLBACK_NAME + "!", result);
    }


    private LangRepository alwaysReturningHelloRepository(String WELCOME) {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }
}
