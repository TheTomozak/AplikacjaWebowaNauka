package io.github.mat3e.Hello;

import io.github.mat3e.Hello.HelloService;
import io.github.mat3e.Lang.Lang;
import io.github.mat3e.Lang.LangRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {


    private final static String WELCOME = "Hello";
    private final static String FALLBACK_ID_WELCOME = "Hola";

    @Test
    public void test_PrepareGreeting_nullName_returnsGreetingWithFallback() {
        //given
        var mockreposiotry = alwaysReturningHelloRepository(WELCOME);
        var SUT = new HelloService(mockreposiotry); //system under test

        //when
        var result = SUT.prepareGreeting(null, -1);

        //then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }


    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {
        //given

        var mockreposiotry = alwaysReturningHelloRepository(WELCOME);
        var SUT = new HelloService(mockreposiotry); //system under test
        String name = "test";

        //when
        var result = SUT.prepareGreeting(name, -1);

        //then
        assertEquals(WELCOME + " " + name + "!", result);
    }

    @Test
    public void test_PrepareGreeting_nullLang_returnsGreetingWithFallbackIdLang() {
        //given

        var mockreposiotry = fallbackLangIdRepository();
        var SUT = new HelloService(mockreposiotry); //system under test

        //when
        var result = SUT.prepareGreeting(null, null);

        //then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

//    @Test
//    public void test_PrepareGreeting_textLang_returnsGreetingWithFallbackIdLang() {
//        //given
//
//        var mockreposiotry = fallbackLangIdRepository();
//        var SUT = new HelloService(mockreposiotry); //system under test
//
//        //when
//        var result = SUT.prepareGreeting(null, "abc");
//
//        //then
//        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
//    }

    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang() {
        //given

        var mockreposiotry = new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };
        var SUT = new HelloService(mockreposiotry); //system under test

        //when
        var result = SUT.prepareGreeting(null, -1);

        //then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository fallbackLangIdRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }


    private LangRepository alwaysReturningHelloRepository(String WELCOME) {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }
}
