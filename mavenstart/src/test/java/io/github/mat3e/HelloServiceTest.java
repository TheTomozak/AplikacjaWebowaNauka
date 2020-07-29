package io.github.mat3e;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelloServiceTest {

    private HelloService SUT = new HelloService(); //system under test

    @Test
    public void test_PrepareGreeting_null_returnsGreetingWithFallback(){
        //given
        String name = null;

        //when
        var result = SUT.prepareGreeting(name);

        //then
        assertEquals("Hello " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName(){
        //given
        String name = "test";

        //when
        var result = SUT.prepareGreeting(name);

        //then
        assertEquals("Hello " + name + "!", result);
    }
}
