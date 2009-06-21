<%--
    Document   : creaOrdine
    Created on : 15-giu-2009, 17.27.43
    Author     : pamput
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

 <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creazione Nuovo Ordine</title>
        <link rel="stylesheet" type="text/css" href="./diadiacommerce.css" />
        <%@ page language="java" %>

        <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
        <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
        <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

        <%@ page import="modello.Ordine" %>
        <%@ page import="modello.RigaOrdine, modello.Prodotto, persistenza.Facade,
        persistenza.postgresql.Facadepostgresql" %>
        <%@ page import="java.util.List" %>
        <%@ page import="web.form.RigaOrdineForm" %>


    </head>


    <body>
        <menu-top><jsp:include page="/menu.jsp"/></menu-top><br><br>
        <div class="main-frame">
            <h3>Creazione nuovo ordine</h3>
            Riepilogo del nuovo ordine:<br><br>

    <center>
                
        <catalogo>
            <intestazione-catalogo>
                <cella-intestazione-catalogo class="nome">Nome</cella-intestazione-catalogo>
                <cella-intestazione-catalogo class="codice">Codice</cella-intestazione-catalogo>
                <cella-intestazione-catalogo class="descrizione">Descrizione</cella-intestazione-catalogo>
                <cella-intestazione-catalogo class="prezzo">Prezzo</cella-intestazione-catalogo>
                <cella-intestazione-catalogo class="disponibili">Disponibili</cella-intestazione-catalogo>
                <cella-intestazione-catalogo class="ordinati">Ordinati</cella-intestazione-catalogo>

            </intestazione-catalogo>

            <logic:iterate id="riga" name="listaRigaOrdine">
                <corpo-catalogo>
                    <cella-corpo-catalogo class="nome"><html:link page="/dettaglioProdotto.do" paramId="idProdotto" paramName="riga" paramProperty="prodotto.id">
                        <bean:write name="riga" property="prodotto.nome" /></html:link>
                    </cella-corpo-catalogo>
                    <cella-corpo-catalogo class="codice"><bean:write name="riga" property="prodotto.codice" /></cella-corpo-catalogo>
                    <cella-corpo-catalogo class="descrizione"><bean:write name="riga" property="prodotto.descrizione" /></cella-corpo-catalogo>
                    <cella-corpo-catalogo class="prezzo"><bean:write name="riga" property="prodotto.prezzo" />
                        <bean:message key="text.moneyvalue"/></cella-corpo-catalogo>
                    <cella-corpo-catalogo class="disponibili"><bean:write name="riga" property="prodotto.quantita" /></cella-corpo-catalogo>

                    <!-- cella del numero degli ordini -->
                    <cella-corpo-catalogo class="disponibili">
                        <bean:write name="riga" property="quantita" />
                    </cella-corpo-catalogo>
                </corpo-catalogo>
            </logic:iterate>

        </catalogo>

        <br>
            <html:link action="creaOrdine.do"><input type="button" value="Conferma l'ordine"/></html:link>
            <input type="button" onclick="javascript:history.back()" value="Indietro"/>

    </div>
    </body>
</html>
