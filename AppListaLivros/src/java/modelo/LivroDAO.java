/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ALIOMAR
 */
public class LivroDAO {

    public static ArrayList<Livro> listaLivros(Connection con) 
            throws SQLException {
        String cmd = "SELECT * FROM LIVRO";
        ArrayList<Livro> livros = new ArrayList();
        Statement st;
        ResultSet rs;
        Livro livro;
        st = con.createStatement();
        rs = st.executeQuery(cmd);
        while (rs.next()){
            livro = new Livro();
            livro.setIsbn(rs.getString(1));
            livro.setTitulo(rs.getString(2));
            livro.setEditora(rs.getString(3));
            livros.add(livro);
        }
        return livros;
    }
}
