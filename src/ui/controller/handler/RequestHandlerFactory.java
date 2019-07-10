package ui.controller.handler;

import domain.model.ShopService;

import java.lang.reflect.InvocationTargetException;

public class RequestHandlerFactory
{
    public RequestHandler getRequestHandler(String action, ShopService model)
    {
        RequestHandler handler = null;
        try
        {
//            Class<?> handlerClass = Class.forName("out.production.week01_labo1.ui.controller.handler." + action);
            Class<?> handlerClass = Class.forName("ui.controller.handler." + action);
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
        {
//            e.printStackTrace();
            handler = new index();
        }
        handler.setModel(model);
        return handler;
    }
}
