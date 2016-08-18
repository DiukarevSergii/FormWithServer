package form;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Statistics extends HttpServlet {

    static final String TEMPLATE = "<html>" +
            "<head><title>Statisticts</title></head>" +
            "<body>" +
            "<h1>Statistics about answers:" +
            "<h2>Map: %d</h2>" +
            "<h2>Queue: %d</h2>" +
            "<h2>List: %d</h2>" +
            "<h2>Set: %d</h2>" +
            "<form action=\"/\" method=\"post\">\n" +
            "    <input type=\"submit\" value=\"Return to main page\"/>\n" +
            "</form>" +
            "</body></html>";

    private int mapCounter = 0;
    private int queueCounter = 0;
    private int listCounter = 0;
    private int setCounter = 0;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] values = req.getParameterValues("check");

        if (values != null) {
            for (String value : req.getParameterValues("check")) {
                if (value.equalsIgnoreCase("map")) {
                    mapCounter++;
                } else if (value.equalsIgnoreCase("queue")) {
                    queueCounter++;
                } else if (value.equalsIgnoreCase("list")) {
                    listCounter++;
                } else if (value.equalsIgnoreCase("set")) {
                    setCounter++;
                }
            }
        }
        resp.getWriter().println(String.format(TEMPLATE, mapCounter, queueCounter, listCounter, setCounter));
    }
}
