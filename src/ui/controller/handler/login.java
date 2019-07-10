package ui.controller.handler;

import domain.db.DbException;
import domain.model.Person;
import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class login implements RequestHandler
{
    private ShopService service;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String id = request.getParameter("userid");
        String pw = request.getParameter("password");
        try
        {
            Person user = this.service.getUserIfAuthenticated(id, pw);
            request.getSession().setAttribute("user", user);
        }
        catch (DbException e)
        {
            request.setAttribute("loginError", "Something wrong with login: " + e.getMessage());
        }
        try { response.sendRedirect("Controller?action=index"); }
        catch (IOException e) { e.printStackTrace();}
    }

    @Override
    public void setModel(ShopService model)
    {
        this.service = model;
    }
}
