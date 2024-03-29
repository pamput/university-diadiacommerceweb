/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.action.creaOrdine;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.activation.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modello.Cliente;
import modello.Ordine;
import modello.RigaOrdine;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import persistenza.postgresql.Facadepostgresql;

/**
 *
 * @author Kimo
 */
public class SActionCreaOrdine extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "creaOrdine";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        List<RigaOrdine> listaRigaOrdine = (List<RigaOrdine>) request.getSession().getAttribute("listaRigaOrdine");

        Ordine ordine = new Ordine();
        ordine.setRigheOrdine(listaRigaOrdine);
        ordine.setCliente(cliente);
        ordine.setData(new Date(new java.util.Date().getTime()));

        Facadepostgresql facade = new Facadepostgresql();

        facade.salvaOrdine(ordine);

        return mapping.findForward(SUCCESS);

    }
}
