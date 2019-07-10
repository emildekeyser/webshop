package ui.controller;

import domain.model.ShopService;
import ui.controller.handler.RequestHandler;
import ui.controller.handler.RequestHandlerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

@WebServlet("/Controller")
public class Controller extends HttpServlet
{
    private ShopService service;
    private RequestHandlerFactory handlerFactory;

    @Override
    public void
    init() throws ServletException
    {
        super.init();
        ServletContext context = this.getServletContext();
        Properties properties = new Properties();
        Enumeration<String> parameterNames = context.getInitParameterNames();
        while (parameterNames.hasMoreElements())
        {
            String propertyName = parameterNames.nextElement();
            properties.setProperty(propertyName, context.getInitParameter(propertyName));
        }
        this.service = new ShopService(properties);
        this.handlerFactory = new RequestHandlerFactory();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processAction(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processAction(request, response);
    }

    private void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");
        action = (action == null ? "index" : action);
        RequestHandler handler = this.handlerFactory.getRequestHandler(action, this.service);
        handler.handleRequest(request, response);
    }
}
