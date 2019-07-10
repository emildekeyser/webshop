package ui.controller.handler;

import domain.model.Person;
import domain.model.ShopService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class signUp implements RequestHandler
{
    private ShopService service;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userid = request.getParameter("userid");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        boolean allEmpty = (userid == null || userid.trim().length() == 0)
                           && (userid == null || userid.trim().length() == 0)
                           && (email == null || email.trim().length() == 0)
                           && (password == null || password.trim().length() == 0)
                           && (firstName == null || firstName.trim().length() == 0)
                           && (lastName == null || lastName.trim().length() == 0);

        if (allEmpty)
        {
            request.getRequestDispatcher("signUp.jsp").forward(request, response);
        }

        ArrayList<String> errors = new ArrayList<>();
        Person newPerson = new Person();
        try
        {
            newPerson.setUserid(userid);
        }
        catch (IllegalArgumentException e)
        {
            errors.add(e.getMessage());
        }
        try
        {
            newPerson.setEmail(email);
        }
        catch (IllegalArgumentException e)
        {
            errors.add(e.getMessage());
        }
        try
        {
            newPerson.setPassword(password);
        }
        catch (IllegalArgumentException e)
        {
            errors.add(e.getMessage());
        }
        try
        {
            newPerson.setFirstName(firstName);
        }
        catch (IllegalArgumentException e)
        {
            errors.add(e.getMessage());
        }
        try
        {
            newPerson.setLastName(lastName);
        }
        catch (IllegalArgumentException e)
        {
            errors.add(e.getMessage());
        }

        if (errors.isEmpty())
        {
            try
            {
                this.service.addPerson(newPerson);
            }
            catch (Exception e)
            {
                errors.add(e.getMessage());
            }
        }
        if (!errors.isEmpty())
        {
            request.setAttribute("errors", errors);
            request.setAttribute("userid", userid);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);

            request.getRequestDispatcher("signUp.jsp").forward(request, response);
        }
        else
        {
            try { response.sendRedirect("Controller?action=index"); }
            catch (IOException e) { e.printStackTrace();}
        }

    }

    @Override
    public void setModel(ShopService model)
    {
        this.service = model;
    }
}
