package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class productOverview implements RequestHandler
{
    private ShopService service;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        showShoppingCartAmount(request);
        if (service == null)
        {
            throw new RuntimeException("Model not set");
        }
        request.setAttribute("products", this.service.getProducts());
        request.getRequestDispatcher("productOverview.jsp").forward(request, response);
    }

    private void showShoppingCartAmount(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60*60);
        ArrayList<Integer> cart = (ArrayList<Integer>) session.getAttribute("cart");
        if (cart != null)
        {
            int cartAmount = cart.size();
            request.setAttribute("cartAmount ", cartAmount);
        }
    }

    @Override
    public void setModel(ShopService model)
    {
        this.service = model;
    }
}
