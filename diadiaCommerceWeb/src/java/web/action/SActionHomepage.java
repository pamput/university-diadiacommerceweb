/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modello.Cliente;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import persistenza.Facade;
import persistenza.postgresql.Facadepostgresql;

/**
 *
 * @author Kimo
 */
public class SActionHomepage extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String USER = "mostraHomepage";
    private final static String ADMIN = "mostraAdminpage";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forwardString = null;
        if(request.getSession().getAttribute("role").equals("user")){
            if(request.getSession().getAttribute("cliente") != null){
                Cliente cliente = (Cliente)request.getSession().getAttribute("cliente");
                request.getSession().setAttribute("ordini", cliente.getOrdini());
            }
            forwardString = USER;
        }else if(request.getSession().getAttribute("role").equals("admin")){
            //Inserisce in sessione i dati necessari per la pagina personale dell'admin
            Facade facade = new Facadepostgresql();
            request.getSession().setAttribute("ordini",facade.getOrdini());
            forwardString = ADMIN;
        }
        return mapping.findForward(forwardString);
    }
}
