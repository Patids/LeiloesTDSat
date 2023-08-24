/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        
        
        conn = new conectaDAO().connectDB();
        
         int status;
     
     try{
        String sql = "INSERT INTO produtos (nome,valor,status) VALUES" + "(?,?,?)";
         PreparedStatement stmt = this.conn.prepareStatement(sql);
         stmt.setString(1,produto.getNome());
         stmt.setInt(2,produto.getValor());
         stmt.setString(3,produto.getStatus());
         status = stmt.executeUpdate();
          return status;
            } catch (SQLException e){
            System.out.println("Erro ao conectar: " + e.getMessage());
            return e.getErrorCode();
        }
             
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

