package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class index implements RequestHandler
{
    private ShopService service;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
        {
            String quote = "";
            for (Cookie cookie : cookies)
            {
                if (cookie.getName().equals("showQuoteCookie")
                    && cookie.getValue().toLowerCase().equals("yes"))
                {
                    quote = quote();
                    break;
                }
            }
            request.setAttribute("quote", quote);
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private String quote()
    {
        return "Ding dong bing bang - Gert Samson";
    }

    @Override
    public void setModel(ShopService model)
    {
        this.service = model;
    }
}
