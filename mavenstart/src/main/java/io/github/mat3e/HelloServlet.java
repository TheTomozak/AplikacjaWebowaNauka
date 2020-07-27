package io.github.mat3e;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Hello", urlPatterns = {"/api/*"})
public class HelloServlet extends HttpServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String queryParameter = req.getParameter("name");
        if (queryParameter==null){
            LOGGER.info("Request got");
            resp.getWriter().write("Hello world!");
        }else {
            LOGGER.info("Request got with parameter");
            String message = String.format("Hello %s!", queryParameter);
            resp.getWriter().write(message);
        }

        

    }
}
