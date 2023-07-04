package etu1463.framework.config;

import java.io.File;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import etu1463.framework.Mapping;
import etu1463.framework.annotations.ModelController;
import etu1463.framework.annotations.UrlMapping;
import etu1463.framework.exceptions.JavaFileException;
import etu1463.framework.utils.javaObject.JavaClass;
import etu1463.framework.utils.javaObject.JavaFile;

public class FrontServletConfig {

    public static final String VIEW_DIRECTORY = "views/";
    public static final String MODEL_DIRECTORY = "modelControllers/";

    /* METHOD SECTION */
    public static Map<String, Mapping> getAllMappedMethod(String rootPath, File[] fileTree)
            throws Exception {
        // Declaring variables up here
        // and injecting their dependencies in the loop using setters
        // for better performance

        JavaFile javaFile = new JavaFile();
        JavaClass javaClass = new JavaClass();
        Mapping mapping = null;
        Map<String, Mapping> mappedMethod = new HashMap<String, Mapping>();

        for (File file : fileTree) {
            try {
                javaFile.setJavaFile(file);
                javaClass.setJavaClass(javaFile.getClassObject(rootPath));

                // all classes in the model directory must be annotated with @ModelController
                if (javaClass
                        .getJavaClass().isAnnotationPresent(ModelController.class)) {
                    for (Method method : javaClass.getMethodByAnnotation(UrlMapping.class)) {
                        mapping = new Mapping(javaClass.getJavaClass().getName(), method);
                        mappedMethod.put(javaClass.getJavaClass().getAnnotation(ModelController.class).route()
                                .concat(method.getAnnotation(UrlMapping.class).url()), mapping);
                    }
                } else {
                    throw new Exception("The class " + javaClass.getJavaClass().getName()
                            + " have to be annotated with @Controller");

                }
            } catch (JavaFileException e) {
                continue;
            }
        }

        return mappedMethod;
    }
}