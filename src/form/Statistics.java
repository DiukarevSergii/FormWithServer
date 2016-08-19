package form;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

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

    private AtomicInteger mapCounter = new AtomicInteger(0);
    private AtomicInteger queueCounter = new AtomicInteger(0);
    private AtomicInteger listCounter = new AtomicInteger(0);
    private AtomicInteger setCounter = new AtomicInteger(0);

    private AtomicInteger yesCounter = new AtomicInteger(0);
    private AtomicInteger noCounter = new AtomicInteger(0);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] values = req.getParameterValues("check");

        if (values != null) {
            for (String value : values) {
                if (value.equalsIgnoreCase("map")) {
                    mapCounter.getAndIncrement();
                } else if (value.equalsIgnoreCase("queue")) {
                    queueCounter.getAndIncrement();
                } else if (value.equalsIgnoreCase("list")) {
                    listCounter.getAndIncrement();
                } else if (value.equalsIgnoreCase("set")) {
                    setCounter.getAndIncrement();
                }
            }
        }

        values = req.getParameterValues("mainMethod");

        if (values != null) {
            for (String value : values) {
                if (value.equalsIgnoreCase("yes")) {
                    yesCounter.getAndIncrement();
                } else if (value.equalsIgnoreCase("no")) {
                    noCounter.getAndIncrement();
                }
            }
        }

        resp.getWriter().println(String.format(TEMPLATE,
                mapCounter.intValue(), queueCounter.intValue(), listCounter.intValue(), setCounter.intValue(),
                yesCounter.intValue(),noCounter.intValue()));
    }
}
