/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import persistenza.postgresql.AccountsHandlerpostgresql;
import web.form.RegistrazioneForm;

/**
 *
 * @author pamput
 */
public class SActionRegistrati extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "successoRegistrazione";
    private final static String FAIL = "fallimentoRegistrazione";
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
        AccountsHandlerpostgresql handler = new AccountsHandlerpostgresql();
        RegistrazioneForm registerForm = (RegistrazioneForm) form;
        try{
            handler.addAccount(registerForm.getCodicecliente(),registerForm.getUsername(), registerForm.getPassword(), "user");
        }catch(Exception ex){
            return mapping.findForward(FAIL);
        }
        return mapping.findForward(SUCCESS);
    }
}