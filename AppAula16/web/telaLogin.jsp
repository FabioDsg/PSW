<%-- 
    Document   : telaLogin
    Created on : 22/05/2017, 21:36:08
    Author     : ra21503516
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>teste de Login</title>
        <style>
            #msg{
                color:red;
            }
        </style>
    </head>
    <body>
        <%@ page session="false" %>
        <%@ page import="java.sql.*" %>
        <%
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                System.out.println("Driver do derby carregado");
            } catch (ClassNotFoundException e) {
                System.out.println("Erro ao carregar o driver "
                        + e.toString());
            }
        %>
    <CENTER>
        <FORM METHOD=POST ACTION="telaLogin.jsp">
            <BR><H2>Teste de login</H2>
            <BR>
            <TABLE>
                <TR>
                    <TD>Usuário:</TD>
                    <TD><INPUT TYPE="TEXT" NAME="usuario"></TD>
                </TR>
                <TR>
                    <TD>Senha :</TD>
                    <TD><INPUT TYPE="PASSWORD" NAME="senha"></TD>
                </TR>
                <TR>
                    <TD><INPUT TYPE="RESET" VALUE="limpa"></TD>
                    <TD><INPUT TYPE="SUBMIT" VALUE="Login"></TD>
                </TR>
                <div id="msg"></div>
                <%
                    try {
                        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/bdproduto",
                                "adm", "123");
                        System.out.println("Conexao com sucesso...");
                        Statement st = con.createStatement();
                        String usr = request.getParameter("usuario");
                        String pas = request.getParameter("senha");
                        if (usr != null && pas != null) {
                            String sql = "SELECT COUNT(*) FROM USUARIO"
                                    + " WHERE LOGIN = '" + usr
                                    + "' AND SENHA='" + pas + "'";
                            ResultSet rs = st.executeQuery(sql);
                            rs.next();
                            int qtd = rs.getInt(1);
                            if (qtd == 0) {
                                out.println("<script>document.getElementById('msg').innerHTML='Usuario e Senha invalidos';</script>");
                            } else {
                                out.println("<script>document.getElementById('msg').innerHTML='Usuario e Senha válidos';</script>");
                            }
                            rs.close();
                        }
                        st.close();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro na conexao com o"
                                + " banco...");
                    }
                %>
            </TABLE>
            
        </FORM>
    </CENTER>
</body>
</html>
