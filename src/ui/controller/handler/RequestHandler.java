package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface RequestHandler
{
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    public void setModel(ShopService model);
}
