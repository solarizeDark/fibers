package ru.fedusiv.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.fedusiv.models.Fiber;
import ru.fedusiv.models.File;
import ru.fedusiv.services.FiberGsonSerializer;
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

@WebServlet("/fibers")
@MultipartConfig
public class MainPage extends HttpServlet {

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

        String requestType = request.getHeader("Type");

        if (requestType != null && requestType.equals("ajax")) {
            fibersService.loadLastFibers(request, response);
            return;
        }

        List<Fiber> openingFibers = fibersService.findAllOpeningFibers();
        request.setAttribute("fibers", openingFibers);
        request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        fibersService.save(request, this.storage);
    }

}
