package form;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Statistics extends HttpServlet {

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
        resp.getWriter().println(String.format(getTemplate(),
                mapCounter.intValue(), queueCounter.intValue(), listCounter.intValue(), setCounter.intValue(),
                yesCounter.intValue(), noCounter.intValue()));
    }

    private String getTemplate() {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(
                "D:\\JAVA\\MainWorkspace\\FormWithServer\\web\\statistics.html")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
