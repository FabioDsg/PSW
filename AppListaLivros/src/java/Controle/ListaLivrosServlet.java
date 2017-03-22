/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Livro;
import modelo.LivroDAO;

/**
 *
 * @author ALIOMAR
 */
public class ListaLivrosServlet extends HttpServlet {

    Connection con;
    
    ServletConfig config;
    public void init (ServletConfig config) throws ServletException{
    
        this.config = config;
    }

    public void conexao() {
        String driver = config.getInitParameter("driver");
        String url = config.getInitParameter("url");
        String usr = config.getInitParameter("usr");
        String senha = config.getInitParameter("senha");
        try {
            Class.forName(driver);
            System.out.println("Driver carregado com sucesso");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro no driver do bd " + ex.getMessage());
        }
        try {
            con = DriverManager.getConnection(url, usr, senha);
            System.out.println("Banco de dados pronto");
        } catch (SQLException ex) {
            System.out.println("Erro na conexão com bd " + ex.getMessage());
        }
    }

    public void listarLivros(ArrayList<Livro> livros, PrintWriter out) {
        out.println("<table border='1'>");
        out.println("<tr><th>ISBN</th><th>Título</th><th>Editora</th></tr>");
        for (Livro livro : livros) {
            out.println("<tr><td>" + livro.getIsbn() + "</td><td>"
                    + livro.getTitulo() + "</td><td>"
                    + livro.getEditora() + "</td></tr>");
        }
        out.println("</table>");
    }

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
        ArrayList<Livro> livros = null;
        if (con == null) {
            conexao();
        }
        try {
            livros = LivroDAO.listaLivros(con);
        } catch (SQLException ex) {
            System.out.println("Erro no acesso aos livros " + ex.getMessage());
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListaLivrosServlet</title>");
            out.println("</head>");
            out.println("<body><form action='index.html' method='POST'>");
            out.println("<h1>Relação de livros cadastrados</h1>");
            listarLivros(livros, out);
            out.println("<br><input type='submit' value='voltar' />");
            out.println("</form></body>");
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
        processRequest(request, response);
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
