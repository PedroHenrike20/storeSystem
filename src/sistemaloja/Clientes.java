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
public class Clientes {
    private String nome;
    private String telefone;
    private String endereco;
    private String situacao;
    private String nm_vendedor;
    private double limite;

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getNm_vendedor() {
        return nm_vendedor;
    }

    public void setNm_vendedor(String nm_vendedor) {
        this.nm_vendedor = nm_vendedor;
    }
    
    
    
}
