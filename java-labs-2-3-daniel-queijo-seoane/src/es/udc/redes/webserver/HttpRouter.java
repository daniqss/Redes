package es.udc.redes.webserver;

import es.udc.redes.webserver.controllers.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

public class HttpRouter implements Iterable<Map.Entry<String, HttpController>>{
    final private Map<String, HttpController> routes;

    public HttpRouter() {
        this.routes = new HashMap<>();
    }

    public HttpRouter(HttpRouter router) {
        this.routes = new HashMap<>(router.routes);
    }

    public void addRoute(String path, HttpController controller) {
        this.routes.put(path, controller);
    }

    public HttpController getController(String path) throws Exception {
        HttpController controller = this.routes.get(path);

        if (controller == null) {
            throw new Exception("Not found controller for path: " + path);
        }
        return controller;
    }

    @NotNull
    @Override
    public Iterator<Map.Entry<String, HttpController>> iterator() {
        return routes.entrySet().iterator();
    }

    @Override
    public void forEach(Consumer<? super Map.Entry<String, HttpController>> action) {
        routes.entrySet().forEach(action);
    }
}
