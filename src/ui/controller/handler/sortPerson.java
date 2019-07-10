package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class sortPerson implements RequestHandler
{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String sortChoice = request.getParameter("sortChoice");
        sortChoice = (sortChoice == null) ? "email" : sortChoice;
        boolean valid = sortChoice.equals("email")
                        || sortChoice.equals("firstName")
                        || sortChoice.equals("name");
        if (!valid) {sortChoice = "email";}
        Cookie cookie = new Cookie("sortBy", sortChoice);
        response.addCookie(cookie);
        try { response.sendRedirect("Controller?action=personOverview"); }
        catch (IOException e) { e.printStackTrace();}
    }

    @Override
    public void setModel(ShopService model)
    {
//        this.service = model;
    }
}
