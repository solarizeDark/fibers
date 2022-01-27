package ru.fedusiv.servlets;

import ru.fedusiv.models.Fiber;
import ru.fedusiv.services.FibersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/fibers")
public class MainPage extends HttpServlet {

    private FibersService fibersService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        this.fibersService = (FibersService) context.getAttribute("fibersRepository");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Fiber> of = fibersService.findAllOpeningFibers();
        request.setAttribute("fibers", of);
        request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
    }

}
