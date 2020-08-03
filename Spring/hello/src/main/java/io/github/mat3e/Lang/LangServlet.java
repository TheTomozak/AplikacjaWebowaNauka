package io.github.mat3e.Lang;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mat3e.HelloApplicationWithSpringApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Lang", urlPatterns = {"/api/langs"})
public class LangServlet extends HttpServlet {


    private final Logger LOGGER = LoggerFactory.getLogger(HelloApplicationWithSpringApplication.class);

    private LangService service;
    private ObjectMapper mapper;

    /**
     * Servlet container needs it.
     */
    @SuppressWarnings("unused")
    public LangServlet() {
        this(new LangService(), new ObjectMapper());
    }

    LangServlet(LangService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.info("Got request with parameters " + req.getParameterMap());
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), service.findAll());


    }
}
