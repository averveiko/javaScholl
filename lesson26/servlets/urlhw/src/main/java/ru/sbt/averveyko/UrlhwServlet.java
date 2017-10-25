package ru.sbt.averveyko;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

@WebServlet(urlPatterns = "/hello")
public class UrlhwServlet extends HttpServlet {
    private static final String HTML_FORM =
            "<html>\n" +
                    "  <head>\n" +
                    "    <title>Get URL HW Servlet</title>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <h3>Get your url!</h3>\n" +
                    "    <form action='/urlhw-1.0-SNAPSHOT/hello'>\n" +
                    "      <p>URL: <input type='text' name='url'/></p>\n" +
                    "      <p><input type='submit'></p>\n" +
                    "    </form>\n"+
                    "  </body>\n" +
                    "</html>\n";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getParameter("url");

        String content = HTML_FORM; //Форма ввода url
        //Если пользователь передал url, вместо формы отдаем содержимое url
        if (url != null) {
            try {
                content = readContent(url);
            } catch (IOException e) {
                content = "Error while reading url: " + e.getMessage();
            }
        }

        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.append(content);
    }

    /**
     * Метод загружает содержимое страницы по url
     * @param pUrl url страницы
     * @return содержимое страницы
     * @throws IOException Ошибка при загрузке страницы
     */
    private String readContent(String pUrl) throws IOException{
        URL url = new URL(pUrl);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            content.append(inputLine);
        in.close();

        return content.toString();
    }
}
