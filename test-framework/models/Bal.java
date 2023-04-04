package models;

import etu001463.framework.annotation.Url;
import etu001463.framework.renderer.ModelView;

public class Bal {

    @Url("/Bal")
    public ModelView helloWorld() {
        return new ModelView("views/bal.jsp");
    }
}