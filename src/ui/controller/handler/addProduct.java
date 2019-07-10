package ui.controller.handler;

import domain.db.DbException;
import domain.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class addProduct implements RequestHandler
{
    private ShopService service;

    private String name;
    private String description;
    private String price;
    private List<String> errors;

    public addProduct()
    {
        this.errors = new ArrayList<>(4);
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Person loggedInUser = (Person) request.getSession().getAttribute("user");
        boolean authorized =
                loggedInUser != null
                && loggedInUser.getRole().equals(AuthorizationRole.Administrator);
        request.setAttribute("authorized", authorized);

        if (authorized && !allEmpty(request))
        {
            Product newProduct = makeNewProduct();
            if(this.errors.isEmpty())
            {
                try
                {
                    this.service.addProduct(newProduct);
                }
                catch (DbException e)
                {
                    this.errors.add(e.getMessage());
                }
            }

            if (this.errors.isEmpty())
            {
                try { response.sendRedirect("Controller?action=productOverview"); }
                catch (IOException e) { e.printStackTrace();}
            }
            else
            {
                updateRequest(request);
                request.getRequestDispatcher("addProduct.jsp").forward(request, response);
            }
        }
        else
        {
            request.getRequestDispatcher("addProduct.jsp").forward(request, response);
        }

//        try { response.sendRedirect("Controller?action=addProduct"); }
//        catch (IOException e) { e.printStackTrace();}
    }

    private void updateRequest(HttpServletRequest request)
    {
        request.setAttribute("name", this.name);
        request.setAttribute("description", this.description);
        request.setAttribute("price", this.price);
        request.setAttribute("errors", this.errors);
    }

    private Product makeNewProduct()
    {
        Product newProduct = new Product();
        try { newProduct.setName(this.name); }
        catch (DomainException e) {this.errors.add(e.getMessage());}
        try { newProduct.setDescription(this.description); }
        catch (DomainException e) {this.errors.add(e.getMessage());}
        try { newProduct.setPrice(this.price); }
        catch (Exception e) {this.errors.add(e.getMessage());}

        return newProduct;
    }

    private void readRequest(HttpServletRequest request)
    {
        this.name = request.getParameter("name");
        this.description = request.getParameter("description");
        this.price = request.getParameter("price");
    }

    private boolean allEmpty(HttpServletRequest request)
    {
        readRequest(request);
        boolean empty = (this.name == null || this.name.trim().isEmpty())
                        && (this.description == null || this.description.trim().isEmpty())
                        && (this.price == null || this.price.trim().isEmpty());
        return empty;
    }

    @Override
    public void setModel(ShopService model)
    {
        this.service = model;
    }
}
