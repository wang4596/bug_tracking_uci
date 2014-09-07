/*
 * PortalInitServlet.java
 *
 * Created on December 18, 2007, 12:59 PM
 */
package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import com.util.PropFileUtil;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 *
 * @author Saurabh Pandit
 * @version
 */
public class BugTrackerInitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(BugTrackerInitServlet.class);

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    	try {
    		PropFileUtil.load();
    		log.info("BugTracker Initialization Completed");
            
        } catch (Exception ex) {
            log.error("Exception occured while Initializing BugTracker Application ", ex);
            throw new ServletException(ex);
        }
    }
}