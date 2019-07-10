package ui.controller.handler;

import domain.model.Product;
import domain.model.ShopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class cart implements RequestHandler
{
    private ShopService service;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60*60);
        ArrayList<Integer> cart = (ArrayList<Integer>) session.getAttribute("cart");
        if (cart != null)
        {
            ArrayList<Product> productsInCart = new ArrayList<>();
            for (int product : cart)
            {
                productsInCart.add(this.service.getProduct(product));
            }
            request.setAttribute("productsInCart", productsInCart);
        }
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    public void setModel(ShopService model)
    {
        this.service = model;
    }
}
