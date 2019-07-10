package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class setShowQuote implements RequestHandler
{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String setQuoteValue = request.getParameter("showQuoteChoice");
        setQuoteValue = setQuoteValue == null ? "no" : setQuoteValue.toLowerCase();
        setQuoteValue = setQuoteValue.equals("yes") || setQuoteValue.equals("no") ? setQuoteValue : "no";
        Cookie cookie = new Cookie("showQuoteCookie", setQuoteValue);
        response.addCookie(cookie);
//        return new index().handleRequest(request,response);
        try { response.sendRedirect("Controller?action=index"); }
        catch (IOException e) { e.printStackTrace();}
    }

    @Override
    public void setModel(ShopService model){}
}
