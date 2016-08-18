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
            "<h1>Statistics about answers:</h1>" +
            "<h2>Which interfaces extend a Collection interface?</h2>" +
            "<h3>Map: %d</h3>" +
            "<h3>Queue: %d</h3>" +
            "<h3>List: %d</h3>" +
            "<h3>Set: %d</h3>\n\n" +

            "<h2>Can the main method be declared as final?</h2>" +
            "<h3>Yes, it can: %d</h3>" +
            "<h3>No, it can't: %d</h3>\n\n" +

            "<form action=\"/\" method=\"post\">\n" +
            "    <input type=\"submit\" value=\"Return to main page\"/>\n" +
            "</form>" +
            "</body></html>";

    private int mapCounter = 0;
    private int queueCounter = 0;
    private int listCounter = 0;
    private int setCounter = 0;

    private int yesCounter = 0;
    private int noCounter = 0;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] values = req.getParameterValues("check");

        if (values != null) {
            for (String value : values) {
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

        values = req.getParameterValues("mainMethod");

        if (values != null) {
            for (String value : values) {
                if (value.equalsIgnoreCase("yes")) {
                    yesCounter++;
                } else if (value.equalsIgnoreCase("no")) {
                    noCounter++;
                }
            }
        }

        resp.getWriter().println(String.format(TEMPLATE,
                mapCounter, queueCounter, listCounter, setCounter,
                yesCounter,noCounter));
    }
}
