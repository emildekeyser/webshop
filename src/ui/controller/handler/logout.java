package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class logout implements RequestHandler
{
//    private ShopService service;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        request.getSession().invalidate();
        try { response.sendRedirect("Controller?action=index"); }
        catch (IOException e) { e.printStackTrace();}
    }

    @Override
    public void setModel(ShopService model)
    {
//        this.service = model;
    }
}
