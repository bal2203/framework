package models;

import etu001463.framework.annotation.Url;
import etu001463.framework.renderer.ModelView;

public class Test {

    @Url("/test")
    public ModelView helloWorld() {
        return new ModelView("views/test.jsp");
    }
}