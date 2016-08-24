package form;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by user on 24.08.2016.
 */
public class Authorization extends HttpServlet {
    private static final String LOGIN1 = "admin1";
    private static final String PASS1 = "admin1";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        if (login.equals(LOGIN1) && pass.equals(PASS1)){
            resp.getWriter().println(getTemplateMain());
        } else{
            resp.getWriter().print("Please try again");
            resp.getWriter().println();
        }
    }

    private String getTemplateMain() {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(
                "D:\\Program Files\\Java\\MainWorkspace\\FormWithServer\\web\\mainform.html")))) {
            String line = "";
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
