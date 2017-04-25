/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.ir.RuntimeNode;

/**
 *
 * @author ra21503516
 */
public class CalculaCompraServlet extends HttpServlet {

    
    
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            double p1 = 22.76;
            double p2 = 18.00;
            double p3 = 25.00;
            double p4 = 8.76;
            double p5 = 17.54;
            double p6 = 35.00;
            int totalP1 = 0;
            int totalP2 = 0;
            double imp = 8 / 100;
            int Cod1 = Integer.parseInt(request.getParameter("tfCodProduto1"));
            int Cod2 = Integer.parseInt(request.getParameter("tfQtdProduto2"));
            int Qtd1 = Integer.parseInt(request.getParameter("tfQtdProduto1"));
            int Qtd2 = Integer.parseInt(request.getParameter("tfQtdProduto2"));
            
            switch (Cod1){
                case 1 : Cod1 = (int) p1;
                break;
                case 2 : Cod1 = (int) p2;
                break;
                case 3 : Cod1 = (int) p3;
                break;
                case 4 : Cod1 = (int) p4;
                break;
                case 5 : Cod1 = (int) p5;
                break;
                case 6 : Cod1 = (int) p6;
                break;
            }
            
            totalP1 = (int) (p1 * Qtd1);
            totalP2 = (int) (p1 * Qtd2);
               
               
                            
                
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalculaCompraServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Total das compras</h1>");
            out.println("<tr>");
            out.println("<td>Produto 1:" + totalP1 + "</td><br>");
            out.println("<td>Produto 2:" + totalP2 + " </td><br>");
            out.println("<td>Imposto:" + imp + " </td><br>");
            out.println("</tr>");
            out.println("<br><input type='submit' value='voltar' />");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
