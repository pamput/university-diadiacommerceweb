<%-- 
    Document   : adminPage
    Created on : 20-giu-2009, 11.48.05
    Author     : Kimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./diadiacommerce.css" />
        <%@ page language="java" %>
        <%@ page import="modello.Ordine" %>

        <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
        <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
        <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

        <title>Pannello amministratore</title>
    </head>

    <body>
        <menu-top><jsp:include page="/menu.jsp" /></menu-top><br><br>
        <div class="main-frame">
           <h3>Benvenuto nel pannello di amministrazione!</h3>

            <b>Questa è la lista degli ordini:</b><br><br>
            <center>
            <catalogo>
                <intestazione-catalogo>
                        <cella-intestazione-catalogo class="codice">Codice</cella-intestazione-catalogo>
                        <cella-intestazione-catalogo class="cliente">Cliente</cella-intestazione-catalogo>
                        <cella-intestazione-catalogo class="data">Data</cella-intestazione-catalogo>
                        <cella-intestazione-catalogo class="stato">Stato</cella-intestazione-catalogo>
                        <cella-intestazione-catalogo class="evadi">Evadi</cella-intestazione-catalogo>
                </intestazione-catalogo>

                <!--Inizializza a 0 il contarore del logic iterate-->
                <% int i=0; %>

                <logic:iterate id="ordine" name="ordini">
                    <!--Scrive il corpo della lista di ordini-->
                    <corpo-catalogo>
                        <cella-corpo-catalogo class="codice"><bean:write name="ordine" property="codice" /></cella-corpo-catalogo>
                        <cella-corpo-catalogo class="cliente"><bean:write name="ordine" property="cliente" /></cella-corpo-catalogo>
                        <cella-corpo-catalogo class="data"><bean:write name="ordine" property="data" /></cella-corpo-catalogo>
                        <cella-corpo-catalogo class="stato"><bean:write name="ordine" property="stato" /></cella-corpo-catalogo>
                        <cella-corpo-catalogo class="stato">
                            <logic:equal value="chiuso" name="ordine" property="stato">
                                <html:link page="/richiestaEvadiOrdine.do" paramId="codiceOrdine" paramName="ordine" paramProperty="codice">Evadi</html:link>
                            </logic:equal>
                        </cella-corpo-catalogo>
                    </corpo-catalogo>

                    <!--Incrementa il contatore del logic iterate-->
                    <% i++; %>
                </logic:iterate>

            </catalogo>
            </center><br><br>
            
            <!--Rimuove la lista degli ordini dalla sessione-->
            <%session.removeAttribute("ordini");%>
        </div>
    </body>
</html>
