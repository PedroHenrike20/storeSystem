/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sistemaloja.Clientes;

/**
 *
 * @author Jefferson César
 */
public class ClienteDAO {
    private Connection conecta;
    
    public ClienteDAO(){
        this.conecta = new DAO().conecta();
    }
    
    public void finalize() throws Throwable{
        try{
            conecta.close();
        }
        catch(SQLException e){
            e.printStackTrace();
            
        }
        super.finalize();
    }
    
    public void salvar(Clientes cliente){
        
        String sql = "INSERT INTO tb_clientes(nm_cliente, telefone_cliente, endereco_cliente, situacao_cliente, nm_vendedor, valor_limite)"
                + "VALUES(?,?,?,'Ativo',?,?)";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getNm_vendedor());
            stmt.setDouble(5, cliente.getLimite());
            
            stmt.execute();
            stmt.close();
            
        }
        catch(SQLException e){
            throw new RuntimeException(e);
            
        }
        
    }
    
    public void alterarCliente(Clientes cliente){
        
        String sql = "UPDATE tb_clientes SET nm_cliente=?, telefone_cliente=?, endereco_cliente=?, valor_limite=?"
                + "WHERE nm_cliente=? AND telefone_cliente=? ";
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEndereco());
            stmt.setDouble(4, cliente.getLimite());
            stmt.setString(5, cliente.getNome());
            stmt.setString(6, cliente.getTelefone());
            
            stmt.execute();
            stmt.close();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
            
        }
    }
    
    public void excluirCliente(Clientes cliente){
        
        String sql = "UPDATE tb_clientes SET situacao_cliente='Desativado' WHERE nm_cliente=? AND telefone_cliente=?";
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTelefone());
            
            stmt.execute();
            stmt.close();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        
    }
    
    public List<Clientes> listarTodos(){
        
        String sql = "SELECT * FROM tb_clientes ORDER BY nm_cliente ";
        ResultSet rs;
        List<Clientes> clientes = new ArrayList<Clientes>();
        
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Clientes cliente = new Clientes();
                cliente.setNome(rs.getString("nm_cliente"));
                cliente.setTelefone(rs.getString("telefone_cliente"));
                cliente.setEndereco(rs.getString("endereco_cliente"));
                cliente.setSituacao(rs.getString("situacao_cliente"));
                cliente.setNm_vendedor(rs.getString("nm_vendedor"));
                cliente.setLimite(rs.getDouble("valor_limite"));
                
                
                
                clientes.add(cliente);
            }
            rs.close();
            stmt.close();
            return clientes;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            
        }
        return clientes;
    }
    
    public List<Clientes> getLimite(String nome, String telefone){
        
        String sql = "SELECT valor_limite FROM tb_clientes WHERE nm_cliente=? AND telefone_cliente=?";
        ResultSet rs;
        List<Clientes> cliente = new ArrayList<Clientes>();
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Clientes clientes = new Clientes();
                clientes.setLimite(rs.getDouble("valor_limite"));
                cliente.add(clientes);
                
            }
            rs.close();
            stmt.close();
            return cliente;
            
        }
        catch(SQLException e){
            System.out.println(e);
            
        }
        return cliente;
        
    }
    
    public List<Clientes> dadosClientes(Clientes cliente){
        
        String sql = "SELECT * FROM tb_clientes WHERE nm_cliente=? AND telefone_cliente=?";
        ResultSet rs;
        List<Clientes> clientes = new ArrayList<Clientes>();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                cliente.setNome(rs.getString("nm_cliente"));
                cliente.setTelefone(rs.getString("telefone_cliente"));
                cliente.setEndereco(rs.getString("endereco_cliente"));
                cliente.setLimite(rs.getDouble("valor_limite"));
                cliente.setSituacao(rs.getString("situacao_cliente"));
                cliente.setNm_vendedor(rs.getString("nm_vendedor"));
                
                clientes.add(cliente);
                
            }else{
                stmt.close();
            }
            
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return clientes;
        
    }
    
    public List<Clientes> pesquisaCliente(String nome){
        
        String sql = "SELECT nm_cliente, telefone_cliente, endereco_cliente, situacao_cliente, nm_vendedor FROM tb_clientes WHERE nm_cliente ilike ?";
        ResultSet rs;
        
        List<Clientes> clientes = new ArrayList<Clientes>();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, nome+'%');
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Clientes cliente = new Clientes();
                cliente.setNome(rs.getString("nm_cliente"));
                cliente.setTelefone(rs.getString("telefone_cliente"));
                cliente.setEndereco(rs.getString("endereco_cliente"));
                cliente.setSituacao(rs.getString("situacao_cliente"));
                cliente.setNm_vendedor(rs.getString("nm_vendedor"));
                
                clientes.add(cliente);
                
            }
            rs.close();
            stmt.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return clientes;
    }
    
    public void setLimite(Clientes cliente){
        
        String sql = "UPDATE tb_clientes SET valor_limite=? WHERE nm_cliente =? AND telefone_cliente =?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setDouble(1, cliente.getLimite());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTelefone());
            
            stmt.execute();
            stmt.close();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public void setStatus(Clientes cliente){
        
        String sql = "UPDATE tb_clientes SET situacao_cliente=? WHERE nm_cliente =? AND telefone_cliente =?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, cliente.getSituacao());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTelefone());
            
            stmt.execute();
            stmt.close();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
            
        }
        
    }
    
}
