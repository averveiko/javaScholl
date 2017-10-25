package ru.sbt.averveyko;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/hello")
public class UrlhwServlet extends HttpServlet {
    private static final String HTML =
            "<html>\n" +
                    "  <head>\n" +
                    "    <title>Get URL HW Servlet</title>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <h3>Get your url!</h3>\n" +
                    "    <p>%s<p>\n" +
                    "  </body>\n" +
                    "</html>\n";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String out = String.format(HTML, "Hello world from " + req.getMethod());

        PrintWriter writer = resp.getWriter();
        writer.append(out);
    }
}
