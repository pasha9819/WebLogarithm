package servlets;

import database.DatabaseConnector;
import model.Task;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/html/result.html")
public class Executor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Task task = (Task)session.getAttribute("task");
        double arg = task.getArgument(),
               base = task.getBase();

        Double result;
        try {
            result = DatabaseConnector.select(arg, base);
        } catch (Exception e) {
            result = null;
        }

        if (result == null){
            result = Math.log(arg) / Math.log(base);
            result = Math.round(result * 1e6) / 1e6;
            try {
                DatabaseConnector.insert(arg, base, result);
            } catch (Exception ignored) { }
        }

        task.setResult(result);
        session.setAttribute("task", task);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/result.jsp");
        if (dispatcher != null)
            dispatcher.forward(req, resp);
    }
}
