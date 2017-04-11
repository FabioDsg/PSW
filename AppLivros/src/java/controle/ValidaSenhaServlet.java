/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ALIOMAR
 */
public class ValidaSenhaServlet extends HttpServlet {

    Connection con;

    public void conexao() {
        String driver = "org.apache.derby.jdbc.ClientDriver";
        String url = "jdbc:derby://localhost:1527/biblioteca";
        String usr = "adm";
        String senha = "123";
        try {
            // carregando o driver do banco de dados
            Class.forName(driver);
            System.out.println("Driver carregado com sucesso");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro no driver: " + ex.getMessage());
            return;
        }
        try {
            // criando a conexão com o banco de dados
            con = DriverManager.getConnection(url, usr, senha);
            System.out.println("banco de dados pronto");
        } catch (SQLException ex) {
            System.out.println("Erro na conexão com o banco de dados "
                    + ex.getMessage());
        }
    }

    public boolean validaUsuario(String usr, String senha) {
        boolean rcode = false;
        String comando = "SELECT COUNT(*) FROM USUARIO WHERE LOGIN='"
                + usr + "' AND SENHA='" + senha + "'";
        Statement st;
        ResultSet rs;
        try {
            st = con.createStatement();
            rs = st.executeQuery(comando);
            rs.next();
            if (rs.getInt(1) > 0) {
                rcode = true;
            }
        } catch (SQLException ex) {
            System.out.println("Comando inválido!" + ex.getMessage());
        }
        return rcode;
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
        if (con == null) {
            conexao();
        }
        ServletContext sc = getServletConfig().getServletContext();
        String usr = request.getParameter("tfUsuario");
        String senha = request.getParameter("jpSenha");
        if (validaUsuario(usr, senha)) {
            sc.setAttribute("conexao", con);
           response.sendRedirect("ListaLivrosServlet");
        } else {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ValidaSenhaServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<form name='f2' action='index.html'>");
                out.println("<h2>Usuário/Senha inválidos</h2>");
                out.println("<input type='submit' value='Tela de login' />");
                out.println("</form></body>");
                out.println("</html>");
            }
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
