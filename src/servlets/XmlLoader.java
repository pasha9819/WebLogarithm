package servlets;

import model.Task;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/result.xml")
public class XmlLoader extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("xml");
        HttpSession session = req.getSession();
        Task task = (Task) session.getAttribute("task");

        if (task == null){
            resp.setStatus(HttpServletResponse.SC_FOUND);//302
            resp.setHeader("Location", "/index.jsp");
        }

        try(OutputStream out = resp.getOutputStream()) {
            JAXBContext jaxbContext = JAXBContext.newInstance(Task.class);
            Marshaller m = jaxbContext.createMarshaller();
            m.marshal(task, out);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}
