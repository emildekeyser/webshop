package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class addToCart implements RequestHandler
{
    private ShopService service;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60*60);
        if (session.getAttribute("cart") == null) { session.setAttribute("cart", new ArrayList<Integer>());}
        try
        {
            ArrayList<Integer> cart = (ArrayList<Integer>) session.getAttribute("cart");
            int productId = Integer.parseInt(request.getParameter("productId"));
            int amount = Integer.parseInt(request.getParameter("amount"));
            if(this.service.getProduct(productId) != null && amount > 0)
            {
                cart.addAll(Collections.nCopies(amount, productId));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try { response.sendRedirect("Controller?action=productOverview"); }
        catch (IOException e) { e.printStackTrace();}
    }

    @Override
    public void setModel(ShopService model)
    {
        this.service = model;
    }
}
