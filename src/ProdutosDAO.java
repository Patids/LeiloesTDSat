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
import java.util.List;


public class ProdutosDAO {
    private conectaDAO conectaDAO;
    private Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public ProdutosDAO(){
    this.conectaDAO = new conectaDAO();
    this.conn = this.conectaDAO.connectDB();
  }
    
    public int cadastrarProduto (ProdutosDTO produto){
                              
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
    
    public List<ProdutosDTO> listarProdutos(){
        
        conn = new conectaDAO().connectDB();
        
        String sql = "SELECT * FROM produtos";
        try{
           PreparedStatement stmt = this.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
          // stmt.setString(1, "%" + nome + "%");
           ResultSet rs = stmt.executeQuery();
           
           List<ProdutosDTO> listaProdutos = new ArrayList<>();
           
           while(rs.next()) {           
           ProdutosDTO produto = new ProdutosDTO();
                      
           produto.setId(rs.getInt("id"));
           produto.setNome(rs.getString("nome"));
           produto.setValor(rs.getInt("valor"));
           produto.setStatus(rs.getString("status")); 
           listaProdutos.add(produto);
                   }
           return listaProdutos;
           
        }catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
          return null;
            
        }
       // return listagem;
    }
    
    
    
        
}

