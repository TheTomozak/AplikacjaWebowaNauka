package io.github.mat3e.Lang;


import io.github.mat3e.HelloApplicationWithSpringApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
class LangServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloApplicationWithSpringApplication.class);

    private LangService service;

    LangServlet(LangService service) {
        this.service = service;
    }

    @GetMapping("/langs") // adontacja zeby działała na geta
    ResponseEntity<List<LangDTO>> findAllLangs() { //ResponseEntity to taki byt zwracany na zewnątrz, tak jakby opakowuje naszego httpServletReq z poprzedniego projektu
        LOGGER.info("Got request");
        return ResponseEntity.ok(service.findAll());

    }


}
