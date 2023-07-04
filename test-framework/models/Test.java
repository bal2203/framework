package models;

import etu1463.framework.annotation.Url;
import etu1463.framework.renderer.ModelView;

public class Test {

    @Url("/test")
    public ModelView helloWorld() {
        ModelView modelView = new ModelView("test.jsp");
        modelView.add("testVariable", "Coucou enao, milay ilay Java");
        modelView.add("testVariable2", new String[] { "Tableau OK" });
        return modelView;
    }
}