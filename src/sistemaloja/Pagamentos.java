/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaloja;

/**
 *
 * @author Jefferson César
 */
public class Pagamentos {
    private String dt_pagamento;
    private String dt_compra;
    private double valor_pago;
    private String nome_cliente;
    private String telefone_cliente;
    private int id_vendas;
    private String tipo_venda;
    private String nm_vendedor;
    private String descricao;
    private double valor_compra;

    public double getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(double valor_compra) {
        this.valor_compra = valor_compra;
    }
    
    

    public String getDt_compra() {
        return dt_compra;
    }

    public void setDt_compra(String dt_compra) {
        this.dt_compra = dt_compra;
    }

    
    
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    

    public String getTipo_venda() {
        return tipo_venda;
    }

    public void setTipo_venda(String tipo_venda) {
        this.tipo_venda = tipo_venda;
    }
    

    
    
    public String getDt_pagamento() {
        return dt_pagamento;
    }

    public void setDt_pagamento(String dt_pagamento) {
        this.dt_pagamento = dt_pagamento;
    }

    public double getValor_pago() {
        return valor_pago;
    }

    public void setValor_pago(double valor_pago) {
        this.valor_pago = valor_pago;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getTelefone_cliente() {
        return telefone_cliente;
    }

    public void setTelefone_cliente(String telefone_cliente) {
        this.telefone_cliente = telefone_cliente;
    }

    public int getId_vendas() {
        return id_vendas;
    }

    public void setId_vendas(int id_vendas) {
        this.id_vendas = id_vendas;
    }

    public String getNm_vendedor() {
        return nm_vendedor;
    }

    public void setNm_vendedor(String nm_vendedor) {
        this.nm_vendedor = nm_vendedor;
    }
    
    
    
    
}
