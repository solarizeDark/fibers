package ru.fedusiv.servlets;

import com.google.gson.Gson;
import ru.fedusiv.models.Fiber;
import ru.fedusiv.services.FibersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/fiber")
@MultipartConfig
public class FiberItem extends HttpServlet {

    private FibersService fibersService;
    private String storage;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        this.fibersService = (FibersService) context.getAttribute("fibersService");
        this.storage = (String) context.getAttribute("storage");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestType = request.getHeader("type");

        Long id = Long.parseLong(request.getParameter("fiber_id"));
        if (requestType != null) {
            // ajax request case
            List<Fiber> fibers = fibersService.findAllComments(id);
            Gson gson = new Gson();
            String fibersJSON = gson.toJson(fibers);
            response.setContentType("application/json");
            response.getWriter().write(fibersJSON);
        } else {
            Fiber fiber = fibersService.findById(id);
            List<Fiber> comments = fibersService.findAllComments(id);
            request.setAttribute("fiber", fiber);
            request.setAttribute("comments", comments);
            request.getRequestDispatcher("/WEB-INF/jsp/fiber.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        fibersService.save(request, this.storage);
    }

}
