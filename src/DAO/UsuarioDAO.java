/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sistemaloja.Vendedor;

/**
 *
 * @author Jefferson César
 */
public class UsuarioDAO {
    
    private Connection conecta;
    public UsuarioDAO(){
        this.conecta = new DAO().conecta();
    }
    
    //SALVANDO USUARIOS
    public void salvarUsuarios(Vendedor vendedor){
    
        String sql = "INSERT INTO tb_vendedor(nm_vendedor, nm_usuario, senha_usuario, cargo_vendedor)"
            + "VALUES(?,?,?,'Funcionário')";
    
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, vendedor.getNome());
            stmt.setString(2, vendedor.getUsuario());
            stmt.setString(3, vendedor.getSenha());
            
            stmt.execute();
            stmt.close();
    
        }
        catch(SQLException e){
            throw new RuntimeException(e);
    
        }
    
    }
    
    public void alterarUsuario(Vendedor vendedor){
        
        String sql = "UPDATE tb_vendedor SET nm_vendedor=?, nm_usuario=?, senha_usuario=?"
                + "WHERE nm_vendedor=?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, vendedor.getNome());
            stmt.setString(2, vendedor.getUsuario());
            stmt.setString(3, vendedor.getSenha());
            stmt.setString(4, vendedor.getNome());
            
            
            stmt.execute();
            stmt.close();
            
        }
        catch(SQLException e){
            throw new RuntimeException(e);
            
        }
    }
    public void alteraCargo(Vendedor vendedor){
        
        String sql = "UPDATE tb_vendedor SET cargo_vendedor =? WHERE nm_vendedor =?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, vendedor.getCargo());
            stmt.setString(2, vendedor.getNome());
            
            stmt.execute();
            stmt.close();
            
            
        }
        catch(SQLException e){
            throw new RuntimeException(e);
            
            
        }
        
    }
    
    public void excluirUsuario(Vendedor vendedor){
        
        String sql = "DELETE FROM tb_vendedor WHERE nm_vendedor=? ";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, vendedor.getNome());
            
            stmt.execute();
            stmt.close();
            
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
            
    }
    public boolean validarLogin(String usuario, String senha){
    boolean autenticacao = false;
       
       String sql = "SELECT * FROM tb_vendedor WHERE nm_usuario =? AND senha_usuario =? ";
       
       try{
           PreparedStatement stmt = conecta.prepareStatement(sql);
           
           stmt.setString(1, usuario);
           stmt.setString(2, senha);
           
           ResultSet rs;
           rs = stmt.executeQuery();
           
           if(rs.next()){
               usuario = rs.getString("nm_usuario");
               senha = rs.getString("senha_usuario");
               autenticacao = true;
           }else{
               stmt.close();
               return autenticacao;
           }
           
       }
       catch(SQLException e){
           System.out.println(e);
           
           
       }
       return autenticacao;
       
    }
    
    
    public List<Vendedor> dadosUsuario(Vendedor vendedor){
        
        String sql = "SELECT * FROM tb_vendedor WHERE nm_vendedor =?";
        ResultSet rs;
        List<Vendedor> vendedores = new ArrayList<Vendedor>();
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, vendedor.getNome());
            
           
            rs = stmt.executeQuery();
            
            if(rs.next()){
                vendedor.setNome(rs.getString("nm_vendedor"));
                vendedor.setUsuario(rs.getString("nm_usuario"));
                vendedor.setSenha(rs.getString("senha_usuario"));
                vendedor.setCargo(rs.getString("cargo_vendedor"));
                
                vendedores.add(vendedor);
                
                
            }else{
                stmt.close();
            }
            
            
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            
        }
        return vendedores;
    }
    
    
    public List<Vendedor> dadosUsuarioLogin(Vendedor vendedor){
        
        String sql = "SELECT * FROM tb_vendedor WHERE nm_usuario =?";
        ResultSet rs;
        List<Vendedor> vendedores = new ArrayList<Vendedor>();
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, vendedor.getUsuario());
            
           
            rs = stmt.executeQuery();
            
            if(rs.next()){
                vendedor.setNome(rs.getString("nm_vendedor"));
                vendedor.setUsuario(rs.getString("nm_usuario"));
                vendedor.setSenha(rs.getString("senha_usuario"));
                vendedor.setCargo(rs.getString("cargo_vendedor"));
                
                vendedores.add(vendedor);
                
                
            }else{
                stmt.close();
            }
            
            
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            
        }
        return vendedores;
    }
        
     public List<Vendedor> listarTodos(){
         
            
         String sql = "SELECT nm_vendedor, cargo_vendedor FROM tb_vendedor ORDER BY nm_vendedor";
         ResultSet rs;
         List<Vendedor> vendedor = new ArrayList<Vendedor>();
            
         try{   
             PreparedStatement stmt = conecta.prepareStatement(sql);
             rs = stmt.executeQuery();
                
             while(rs.next()){
                 
                 
                 Vendedor vendedores = new Vendedor();
                 vendedores.setNome(rs.getString("nm_vendedor"));
                 vendedores.setCargo(rs.getString("cargo_vendedor"));
                    
                 vendedor.add(vendedores);
                    
             }
             rs.close();
             stmt.close();
             return vendedor;
                
         }
         catch(SQLException e){
             
             JOptionPane.showMessageDialog(null, e);
                
         }
         return vendedor;
   }
     public List<Vendedor> pesquisarUsuarios(String nome){
         String sql = "SELECT nm_vendedor, cargo_vendedor FROM tb_vendedor WHERE nm_vendedor=? ORDER BY nm_vendedor";
         ResultSet rs;
         List<Vendedor> vendedor = new ArrayList<Vendedor>();
         
         try{
             PreparedStatement stmt = conecta.prepareStatement(sql);
             stmt.setString(1, nome+'%');
             
             rs = stmt.executeQuery();
             
             while(rs.next()){
                 
                 Vendedor vendedores = new Vendedor();
                 vendedores.setNome(rs.getString("nm_vendedor"));
                 vendedores.setCargo(rs.getString("cargo_vendedor"));
                 
                 vendedor.add(vendedores);
             }
             rs.close();
             stmt.close();
             
             return vendedor;
             
             
             
         }
         catch(SQLException e){
             System.out.println(e);
             
         }
         return vendedor;
         
     }
     /*
     public List<Vendedor>  getSenha(String nome){
         
         String sql = "SELECT senha_usuaio FROM tb_vendedor WHERE nm_vendedor = ?";
         ResultSet rs;
         List<Vendedor> vendedor = new ArrayList<Vendedor>();
         
         try{
             PreparedStatement stmt = conecta.prepareStatement(sql);
             stmt.setString(1, nome);
             
             rs = stmt.executeQuery();
             
             while(rs.next()){
                 Vendedor vendedores = new Vendedor();
                 vendedores.setSenha(rs.getString("senha_usuario"));
                 
                 vendedor.add(vendedores);
             }
             rs.close();
             stmt.close();
             
             return vendedor;
             
         }
         catch(SQLException e){
             System.out.println(e);
             
         }
         return vendedor;
         
         
         
         
     }
     
     
       */ 
        
 }
    
    
    
    
    