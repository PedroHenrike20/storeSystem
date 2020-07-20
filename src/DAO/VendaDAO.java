/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sistemaloja.Compras;

/**
 *
 * @author Jefferson César
 */
public class VendaDAO {
    private Connection conecta;
    public VendaDAO(){
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
    
    public void addVendas(Compras compra){
        
        String sql = "INSERT INTO tb_vendas(dt_venda, descricao_venda, valor_venda, situação_venda, nm_cliente, telefone_cliente, nm_vendedor)"
                + "VALUES(?,?,?,'PENDENTE',?,?,?)";
        
                
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
        
            stmt.setString(1, compra.getDt_venda());
            stmt.setString(2, compra.getDescricao());
            stmt.setDouble(3, compra.getValor_venda());
            stmt.setString(4, compra.getNome_cliente());
            stmt.setString(5, compra.getTelefone_cliente());
            stmt.setString(6, compra.getNm_vendedor());
            
            stmt.execute();
            stmt.close();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
            
        }
    }
    
    public void editarVendas(Compras compra){
        
        String sql = "UPDATE tb_vendas SET dt_venda=?, descricao_venda=?, valor_venda=? WHERE nome_cliente=? AND telefone_cliente=?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, compra.getDt_venda());
            stmt.setString(2, compra.getDescricao());
            stmt.setDouble(3, compra.getValor_venda());
            stmt.setString(4, compra.getNome_cliente());
            stmt.setString(5, compra.getTelefone_cliente());
            
            stmt.execute();
            stmt.close();            
        }
        catch(SQLException e){
            throw new RuntimeException(e);
            
        }
    }
    
    public List<Compras> listarCompras(String nome, String telefone){
        
        String sql = "SELECT id_vendas, dt_venda, descricao_venda,"
                + " valor_venda, situação_venda, nm_vendedor FROM tb_vendas "
                + "WHERE nm_cliente =? AND telefone_cliente =? ORDER BY situação_venda DESC";
        
        ResultSet rs;
        List<Compras> compras = new ArrayList<Compras>();
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Compras compra = new Compras();
                compra.setId_venda(rs.getInt("id_vendas"));
                compra.setDt_venda(rs.getString("dt_venda"));
                compra.setDescricao(rs.getString("descricao_venda"));
                compra.setValor_venda(rs.getDouble("valor_venda"));
                compra.setSituacao(rs.getString("situação_venda"));
                compra.setNm_vendedor(rs.getString("nm_vendedor"));
                
                compras.add(compra);
                
            }
            rs.close();
            stmt.close();            
        }
        catch(SQLException e){
            System.out.println(e);
            
        }
        return compras;
    }
    
    public void resolveCompras(Compras compra){
        
        String sql = "UPDATE tb_vendas SET situação_venda = ? WHERE id_vendas = ?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, compra.getSituacao());
            stmt.setInt(2, compra.getId_venda());
            
            stmt.execute();
            stmt.close();            
        }
        catch(SQLException e){
            throw new RuntimeException(e);
            
        }
    }
    
    public void resolveAcordoCompras(Compras compra){
        
        String sql = "UPDATE tb_vendas SET valor_venda =?, situação_venda =? WHERE id_vendas =? ";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setDouble(1, compra.getValor_venda());
            stmt.setString(2, compra.getSituacao());
            stmt.setInt(3, compra.getId_venda());
            
            stmt.execute();
            stmt.close();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
            
        }
    }
    
    public List<Compras> listarTodas(){
        
        String sql = "SELECT nm_cliente, telefone_cliente,  dt_venda, valor_venda, descricao_venda, situação_venda, nm_vendedor FROM tb_vendas";
        ResultSet rs;
        List<Compras> compras = new ArrayList<Compras>();
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Compras compra = new Compras();
                compra.setNome_cliente(rs.getString("nm_cliente"));
                compra.setTelefone_cliente(rs.getString("telefone_cliente"));
                compra.setDt_venda(rs.getString("dt_venda"));
                compra.setValor_venda(rs.getDouble("valor_venda"));
                compra.setDescricao(rs.getString("descricao_venda"));
                compra.setSituacao(rs.getString("situação_venda"));
                compra.setNm_vendedor(rs.getString("nm_vendedor"));
                
                compras.add(compra);
            }
            rs.close();
            stmt.close();
            return compras;            
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return compras;
    }
    
    public List<Compras> pesquisaCompras(int tipo, String valor){
        
        String where;
        
        if(tipo == 1){
            where = " WHERE nm_cliente ilike ?";
        }else{
            where = " WHERE dt_venda ilike ?";
        }
        
        String sql = "SELECT nm_cliente, telefone_cliente, dt_venda, valor_venda, descricao_venda, situação_venda, nm_vendedor FROM tb_vendas "+where;
        ResultSet rs;
        
        List<Compras> compras = new ArrayList<Compras>();
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, valor+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Compras compra = new Compras();
                compra.setNome_cliente(rs.getString("nm_cliente"));
                compra.setTelefone_cliente(rs.getString("telefone_cliente"));
                compra.setDt_venda(rs.getString("dt_venda"));
                compra.setValor_venda(rs.getDouble("valor_venda"));
                compra.setDescricao(rs.getString("descricao_venda"));
                compra.setSituacao(rs.getString("situação_venda"));
                compra.setNm_vendedor(rs.getString("nm_vendedor"));
                
                compras.add(compra);
            }
            rs.close();
            stmt.close();            
        }
        catch(SQLException e){
            System.out.println(e);
            
        }
        return compras;
        
    }
    
    public List<Compras> pesquisaPendente(){
        
        String where = "WHERE situação_venda = 'PENDENTE'";
        
        String sql = "SELECT nm_cliente, telefone_cliente, dt_venda, valor_venda, descricao_venda, situação_venda, nm_vendedor FROM tb_vendas "+where;
        ResultSet rs;
        
        List<Compras> compras = new ArrayList<Compras>();
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Compras compra = new Compras();
                compra.setNome_cliente(rs.getString("nm_cliente"));
                compra.setTelefone_cliente(rs.getString("telefone_cliente"));
                compra.setDt_venda(rs.getString("dt_venda"));
                compra.setValor_venda(rs.getDouble("valor_venda"));
                compra.setDescricao(rs.getString("descricao_venda"));
                compra.setSituacao(rs.getString("situação_venda"));
                compra.setNm_vendedor(rs.getString("nm_vendedor"));
                
                compras.add(compra);
            }
            
            rs.close();
            stmt.close();
        }
        catch(SQLException e){
            System.out.println(e);
            
        }
        return compras;
        
        
    }
    
}
