/*
    AUTOR: Delgado Mar Jose Manuel
    VERSIÓN: 1.0 
    DESCRIPCIÓN: Clase de java que hace una consulta a la base datos
    OBSERVACIONES: Completar consulta
    COMPILACIÓN: Se compila en tiempo de ejecución. 
    EJECUCIÓN: Ejecutando la pagina con GlashFish server y subiendo una base de datos MYSQL.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 * @version v1.0.1
 * @author Monroy Gonzalez Juan Ignacio
 * @since 30/08/2017
 */
@WebServlet(name = "consu", urlPatterns = {"/consu"})
public class consu extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //El nombre y la contraseña son guardadas en variables que se utilizaran al conectar con BD
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("pass");
        //Crea un objeto del tipo cUsuario que se encargara de buscar en BD el usuario
        controlador.cUsuario obj = new controlador.cUsuario(usuario, contra);
        //valida que exista el usuario
        if(obj.VALIDAUSR()) {
            response(response, "BIENVENIDO ", usuario.toUpperCase(), 1);
        } else {
            response(response, "ALGO SALIO MAL ", usuario.toUpperCase(), 0);
        }

    }
    
    private void response(HttpServletResponse resp, String msg, String neim, int cont)
			throws IOException {
        PrintWriter out = resp.getWriter();
        if(cont == 1) {
            out.println("<html>");
            out.println("<body>");
            out.println("<h1>" + msg + neim +"</h1>");
            out.println("</body>");
            out.println("</html>");
            }
        else {
            out.println("<html>");
            out.println("<body>");
            out.println("<h1>" + msg + neim + " NO SE ENCONTRO" + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
