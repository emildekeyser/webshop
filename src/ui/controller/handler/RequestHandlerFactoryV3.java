package ui.controller.handler;

import domain.model.ShopService;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class RequestHandlerFactoryV3
{
    private Map<String, RequestHandler> handlers = new HashMap<>();

    public RequestHandlerFactoryV3(Properties handlerNames, ShopService model)
    {
        for (Object key : handlerNames.keySet())
        {
            RequestHandler handler = null;
            String handlerName = handlerNames.getProperty((String) key);
            try
            {
                Class<?> handlerClass = Class.forName(handlerName);
                Object handlerObject = handlerClass.getConstructor().newInstance();
                handler = (RequestHandler) handlerObject;
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
            {
                System.out.println(e.getMessage() + "<-----------------------");
                e.printStackTrace();
            }
            handler.setModel(model);
            handlers.put((String) key, handler);
        }
    }

    public RequestHandler getRequestHandler(String action)
    {
        return handlers.get(action);
    }
}
