package io.github.mat3e.Hello;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Hello", urlPatterns = {"/api"})
public class HelloServlet extends HttpServlet {

    private static final String NAME_PARAM = "name";
    private static final String LANG_PARAM = "lang";
    private final Logger LOGGER = LoggerFactory.getLogger(HelloServlet.class);

    private HelloService service;

    /**
     * Servlet container needs it.
     */
    @SuppressWarnings("unused")
    public HelloServlet() {
        this(new HelloService());
    }

    HelloServlet(HelloService service) {
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.info("Got request with parameters " + req.getParameterMap());

        String name = req.getParameter(NAME_PARAM);
        String lang = req.getParameter(LANG_PARAM);
        Integer langId = null;
        try {
            langId = Integer.valueOf(lang);
        }catch (NumberFormatException exc){
            LOGGER.warn("Non-numeric lang id used");
        }
        String greeting = service.prepareGreeting(name, langId);
        resp.getWriter().write(greeting);

//        String queryParameter = req.getParameter("name");
//        if (queryParameter==null){
//            LOGGER.info("Request got");
//            resp.getWriter().write("Hello world!");
//        }else {
//            LOGGER.info("Request got with parameter");
//            String message = String.format("Hello %s!", queryParameter);
//            resp.getWriter().write(message);
//        }


    }
}
