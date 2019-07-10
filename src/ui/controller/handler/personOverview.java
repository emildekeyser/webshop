package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class personOverview implements RequestHandler
{
    private ShopService service;

    public personOverview() {}
//    public personOverview(ShopService service) {this.setModel(service);}

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (this.service == null)
        {
            throw new RuntimeException("Model not defined");
        }
        String sortOrder = "email";
        for (Cookie cookie : request.getCookies())
        {
           if (cookie.getName().equals("sortBy"))
           {
                sortOrder = cookie.getValue();
                break;
           }
        }
        request.setAttribute("persons", this.service.getPersons(sortOrder));
        request.getRequestDispatcher("personOverview.jsp").forward(request, response);
    }

    @Override
    public void setModel(ShopService model)
    {
        this.service = model;
    }
}
