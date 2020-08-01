package io.github.mat3e.Hello;

import io.github.mat3e.Lang.Lang;
import io.github.mat3e.Lang.LangRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

class HelloService {

    static final String FALLBACK_NAME = "world";
    static final Lang FALLBACK_LANG = new Lang(1, "Hello", "en");
    private final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);


    private LangRepository repository;

    HelloService() {
        this(new LangRepository());
    }

    HelloService(LangRepository repository) {
        this.repository = repository;
    }


    String prepareGreeting(String name, Integer langID) {

        Integer langId = Optional.ofNullable(langID).orElse(FALLBACK_LANG.getId());

        var welcomeMsg = repository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
        var nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME);
        return welcomeMsg + " " + nameToWelcome + "!";
    }
}
