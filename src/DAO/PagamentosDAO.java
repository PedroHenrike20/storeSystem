/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sistemaloja.Pagamentos;
/**
 *
 * @author Jefferson César
 */
public class PagamentosDAO {
    private Connection conecta;
    
    public PagamentosDAO(){
        this.conecta = new DAO().conecta();
    }
    
    
    
    
    public void salvarPagamentos(Pagamentos pagamento){
        String sql = "INSERT INTO tb_pagamentos(dt_pagamentos, valor_pago, id_vendas, nm_cliente, telefone_cliente, nm_vendedor, tipo_pagamento, descricao, dt_compra, valor_compra) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, pagamento.getDt_pagamento());
            stmt.setDouble(2, pagamento.getValor_pago());
            stmt.setInt(3, pagamento.getId_vendas());
            stmt.setString(4, pagamento.getNome_cliente());
            stmt.setString(5, pagamento.getTelefone_cliente());
            stmt.setString(6, pagamento.getNm_vendedor());
            stmt.setString(7, pagamento.getTipo_venda());
            stmt.setString(8, pagamento.getDescricao());
            stmt.setString(9, pagamento.getDt_compra());
            stmt.setDouble(10, pagamento.getValor_compra());
            
            stmt.execute();
            stmt.close();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        
    }
    
    public List<Pagamentos> listarPagamentos(){
        
        String sql = "SELECT nm_cliente, telefone_cliente, dt_compra, valor_compra, descricao, valor_pago, tipo_pagamento, dt_pagamentos, nm_vendedor FROM tb_pagamentos";
        ResultSet rs;
        List<Pagamentos> pagamentos = new ArrayList<Pagamentos>();
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pagamentos pagamento = new Pagamentos();
                pagamento.setNome_cliente(rs.getString("nm_cliente"));
                pagamento.setTelefone_cliente(rs.getString("telefone_cliente"));
                pagamento.setDt_compra(rs.getString("dt_compra"));
                pagamento.setValor_compra(rs.getDouble("valor_compra"));
                pagamento.setValor_pago(rs.getDouble("valor_pago"));
                pagamento.setDescricao(rs.getString("descricao"));
                pagamento.setTipo_venda(rs.getString("tipo_pagamento"));
                pagamento.setDt_pagamento(rs.getString("dt_pagamentos"));
                pagamento.setNm_vendedor(rs.getString("nm_vendedor"));
                
                pagamentos.add(pagamento);
                
            }
            rs.close();
            stmt.close();
            return pagamentos;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            
        }
        return pagamentos;
    }
    
    
    public List<Pagamentos> pesquisarPagamentos(int tipo, String valor){
        
        String where;
        if(tipo == 1){
            where = "WHERE nm_cliente ilike ?";  
        }else{
            where = "WHERE dt_pagamentos ilike ?";
        }
        
        String sql = "SELECT nm_cliente, telefone_cliente, dt_compra, valor_compra, descricao, valor_pago, tipo_pagamento, dt_pagamentos, nm_vendedor FROM tb_pagamentos "+where;
        ResultSet rs;
        List<Pagamentos> pagamentos = new ArrayList<Pagamentos>();
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, valor+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pagamentos pagamento = new Pagamentos();
                
                pagamento.setNome_cliente(rs.getString("nm_cliente"));
                pagamento.setTelefone_cliente(rs.getString("telefone_cliente"));
                pagamento.setDt_compra(rs.getString("dt_compra"));
                pagamento.setValor_compra(rs.getDouble("valor_compra"));
                pagamento.setDescricao(rs.getString("descricao"));
                pagamento.setValor_pago(rs.getDouble("valor_pago"));
                pagamento.setTipo_venda(rs.getString("tipo_pagamento"));
                pagamento.setDt_pagamento(rs.getString("dt_pagamentos"));
                pagamento.setNm_vendedor(rs.getString("nm_vendedor"));
                
                pagamentos.add(pagamento);
            }
            rs.close();
            stmt.close();
            
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return pagamentos;
        
    }
    
    
    
}
