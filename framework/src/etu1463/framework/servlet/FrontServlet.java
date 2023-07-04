package etu1463.framework.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import etu1463.framework.Mapping;
import etu1463.framework.config.FrontServletConfig;
import etu1463.framework.utils.FrontRequestHandler;

import fileActivity.Executor;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FrontServlet extends HttpServlet {

    private FrontRequestHandler requestHandler;
    private Map<String, Mapping> mappingUrls;

    /* SETTERS SECTION */
    private void setRequestHandler(FrontRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    private void setMappingUrls(Map<String, Mapping> mappingUrls) {
        this.mappingUrls = mappingUrls;
    }

    /* GETTERS SECTION */
    private FrontRequestHandler getRequestHandler() {
        return this.requestHandler;
    }

    private Map<String, Mapping> getMappingUrls() {
        return this.mappingUrls;
    }

    /* SERVLET INIT SECTION */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            this.setRequestHandler(new FrontRequestHandler());
            String rootPath = config.getServletContext().getRealPath(this.getServletInfo())
                    .concat("WEB-INF/classes/");
            File[] files = Executor
                    .getSubFiles(new File(rootPath.concat(FrontServletConfig.MODEL_DIRECTORY)));
            this.setMappingUrls(FrontServletConfig.getAllMappedMethod(rootPath, files));
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /* METHODS SECTION */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        this.getRequestHandler().process(req, resp, this.getMappingUrls());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.processRequest(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.processRequest(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
