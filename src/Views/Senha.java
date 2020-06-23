/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.*;
import com.sun.glass.events.KeyEvent;
import java.awt.CardLayout;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import sistemaloja.Clientes;
import sistemaloja.Compras;
import sistemaloja.Pagamentos;
import sistemaloja.Vendedor;

/**
 *
 * @author Jefferson César
 */
public class Senha extends javax.swing.JFrame {
    
    //AreaUsuario au = new AreaUsuario();

    

    /**
     * Creates new form Senha
     */
    public Senha() {
        initComponents();
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        //jLabelSenhaConfirm.setText(acao);
    }
    
    String nm_user = "";
    
    String nome_geral = "";
    String nome_alternativo = "";
    String nome_usuario = "";
    String senha_usuario = "";
    String cargo_usuario = "";
    //
    String telefone_cliente = "";
    String endereco_cliente = "";
    double limite_cliente = 0;
    //
    String data_compra = "";
    double valor_compra = 0;
    double valorPg_Compraa = 0;
    double valor_restanteCompra = 0;
    String descricao_compra = "";
    int idCompra = 0;
    String data_secundaria = "";
    boolean autenticaçao = false;
    
    
    public void getDadosCompra(String nome_cliente, String telefoneCliente, String dt_compra, double valorCompra, String descricao, String nm_vendedor, double limite){
        nome_geral = nome_cliente;
        telefone_cliente = telefoneCliente;
        data_compra = dt_compra;
        valor_compra = valorCompra;
        descricao_compra = descricao;
        nome_alternativo = nm_vendedor;
        limite_cliente = limite;
    }
    
    public void getDadosCompra(String nome_cliente, String telefoneCliente, String dt_compra, double valorCompra, String descricao, double valorPgCompra ,String nm_vendedor, double limite, String dt_pagamento, int id_Compra){
        nome_geral = nome_cliente;
        telefone_cliente = telefoneCliente;
        data_compra = dt_compra;
        valor_compra = valorCompra;
        valorPg_Compraa = valorPgCompra ;
        descricao_compra = descricao;
        nome_alternativo = nm_vendedor;
        limite_cliente = limite;
        data_secundaria = dt_pagamento;
        idCompra = id_Compra;
        
    }
    
    public void getDadosCompras(String nome_cliente, String telefoneCliente, String dt_compra, double valorCompra ,double valorPgCompra, double valorRestanteCompra, String descricao, String nm_vendedor, double limite, String dt_pagamento, int id_Compra){
        nome_geral = nome_cliente;
        telefone_cliente = telefoneCliente;
        data_compra = dt_compra;
        valor_compra = valorCompra;
        valor_restanteCompra = valorRestanteCompra;
        descricao_compra = descricao;
        valorPg_Compraa = valorPgCompra;
        nome_alternativo = nm_vendedor;
        limite_cliente = limite;
        data_secundaria = dt_pagamento;
        idCompra = id_Compra;
        
        
        
        
    }
    
    public void getDadosClientePk(String nome, String telefone){
        nome_geral = nome;
        telefone_cliente = telefone;
    }

    
    
    
    public void setLabel(String acao, String nome, String usuario){
        jLabelSenhaConfirm.setText(acao);
        jLabelNomeSenha.setText(nome);
        nm_user = usuario;
    }
    public void getDadosVendedor(String nome, String nm_usuario, String senha ){
        nome_geral = nome;
        nome_usuario = nm_usuario;
        senha_usuario = senha;
        
    }
    public void getDadosClientes(String nome, String endereco, String telefone, double limite){
        nome_geral = nome;
        endereco_cliente = endereco;
        telefone_cliente = telefone;
        limite_cliente = limite;
        
    }
    public void getDadosClientes(String nome, String endereco, String telefone, double limite, String nm_vendedor){
        nome_geral = nome;
        endereco_cliente = endereco;
        telefone_cliente = telefone;
        limite_cliente = limite;
        nome_alternativo = nm_vendedor;
        
    }
    public void getInfoFuncionario(String nome, String cargo){
        
        nome_geral = nome;
        cargo_usuario = cargo;
    }
    
    public void limparVariaveis(){
        
        nome_geral = "";
        nome_alternativo = "";
        nome_usuario = "";
        senha_usuario = "";
        cargo_usuario = "";
        //
        telefone_cliente = "";
        endereco_cliente = "";
        limite_cliente = 0;
        //
        data_compra = "";
        valor_compra = 0;
        valor_restanteCompra = 0;
        valorPg_Compraa = 0;
        descricao_compra = "";
        idCompra = 0;
        data_secundaria = "";

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelSenhaConfirm = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jBtnCancelarPassCad = new javax.swing.JButton();
        jBtnFinalizarAcao = new javax.swing.JButton();
        jPassFinalCadFun = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelNomeSenha = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(450, 300));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(450, 300));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Digite a senha para");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));

        jLabelSenhaConfirm.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        jBtnCancelarPassCad.setText("Cancelar");
        jBtnCancelarPassCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarPassCadActionPerformed(evt);
            }
        });

        jBtnFinalizarAcao.setText("Finalizar");
        jBtnFinalizarAcao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnFinalizarAcaoActionPerformed(evt);
            }
        });

        jPassFinalCadFun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPassFinalCadFunKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Senha");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/loja-virtual-icone.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.setPreferredSize(new java.awt.Dimension(35, 15));

        jLabelNomeSenha.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelNomeSenha.setText("jLabel4");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("Usuario:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnCancelarPassCad)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnFinalizarAcao))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabelNomeSenha))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jPassFinalCadFun, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(109, 109, 109))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPassFinalCadFun, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnFinalizarAcao)
                            .addComponent(jBtnCancelarPassCad))
                        .addGap(37, 37, 37))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelNomeSenha)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(57, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSenhaConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jLabelSenhaConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x-mx, y-my);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        mx = evt.getX();
        my = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseDragged

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MousePressed

    private void jBtnFinalizarAcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnFinalizarAcaoActionPerformed
        // TODO add your handling code here:
       // vendedor.setNome(jLabelNomeSenha.getText());
        UsuarioDAO usDAO = new UsuarioDAO();
        char[] pass = jPassFinalCadFun.getPassword();
        String senha = String.valueOf(pass);
        if(usDAO.validarLogin(nm_user, senha )== true){
            autenticaçao = true;
            
          
            
            if(jLabelSenhaConfirm.getText().equals("finalizar o cadastro de um novo vendedor.")){
                

                Vendedor vendedor = new Vendedor();
                vendedor.setNome(nome_geral);
                vendedor.setUsuario(nome_usuario);
                vendedor.setSenha(senha_usuario);
                usDAO.salvarUsuarios(vendedor);
                JOptionPane.showMessageDialog(this, "Usuário Cadastrado Com Sucesso!", "Atenção!",1);
                limparVariaveis();
                this.dispose();
                
            }else if(jLabelSenhaConfirm.getText().equals("finalizar o cadastro de um novo cliente.")){
                ClienteDAO clDAO = new ClienteDAO();

                Clientes cliente = new Clientes();
                cliente.setNome(nome_geral);
                cliente.setTelefone(telefone_cliente);
                cliente.setEndereco(endereco_cliente);
                cliente.setLimite(limite_cliente);
                cliente.setNm_vendedor(nome_alternativo);
                
                clDAO.salvar(cliente);
                JOptionPane.showMessageDialog(this, "Cliente Cadastrado Com Sucesso!", "Atenção!",1);
     
                limparVariaveis();
                this.dispose();
            }else if(jLabelSenhaConfirm.getText().equals("promover o funcionário.")){
                
               
                Vendedor vendedor = new Vendedor();
                vendedor.setNome(nome_geral);
                vendedor.setCargo(cargo_usuario);
                usDAO.alteraCargo(vendedor);
                
                JOptionPane.showMessageDialog(this, "Cargo Promovido com Sucesso!","Atenção!",1);
                limparVariaveis();
                this.dispose();
            }else if(jLabelSenhaConfirm.getText().equals("finalizar a compra do cliente")){
        
                Compras compra = new Compras();
                compra.setNome_cliente(nome_geral);
                compra.setDescricao(descricao_compra);
                compra.setDt_venda(data_compra);
                compra.setNm_vendedor(nome_alternativo);
                compra.setTelefone_cliente(telefone_cliente);
                compra.setValor_venda(valor_compra);
                
                VendaDAO vnDAO = new VendaDAO();
                vnDAO.addVendas(compra);
                
                Clientes cliente = new Clientes();
                
                cliente.setNome(nome_geral);
                cliente.setTelefone(telefone_cliente);
                cliente.setLimite(limite_cliente);
                
                ClienteDAO cl = new ClienteDAO();
                cl.setLimite(cliente);
         
                
                JOptionPane.showMessageDialog(this, "Compra Adicionada Com Sucesso!","Atenção",1);
                limparVariaveis();
                this.dispose();
            }else if(jLabelSenhaConfirm.getText().equals("finalizar a alteração do cliente.")){
                Clientes cliente = new Clientes();
                cliente.setNome(nome_geral);
                cliente.setTelefone(telefone_cliente);
                cliente.setEndereco(endereco_cliente);
                cliente.setLimite(limite_cliente);
                
                ClienteDAO clDAO = new ClienteDAO();
                clDAO.alterarCliente(cliente);
                JOptionPane.showMessageDialog(this, "Alteração Realizada Com Sucesso!","Atenção!",1);
                limparVariaveis();
                this.dispose();
            }else if(jLabelSenhaConfirm.getText().equals("finalizar o recebimento da compra.")){
                Pagamentos pagamento = new Pagamentos();
                pagamento.setNome_cliente(nome_geral);
                pagamento.setTelefone_cliente(telefone_cliente);
                pagamento.setId_vendas(idCompra);
                pagamento.setDt_pagamento(data_secundaria);
                pagamento.setDt_compra(data_compra);
                pagamento.setDescricao(descricao_compra);
                pagamento.setTipo_venda("À Vista");
                pagamento.setValor_pago(valor_compra);
                pagamento.setNm_vendedor(nome_alternativo);
                pagamento.setValor_compra(valor_compra);
                
                PagamentosDAO pgDAO = new PagamentosDAO();
                pgDAO.salvarPagamentos(pagamento);
                
                Clientes cliente = new Clientes();
                cliente.setLimite(limite_cliente);
                cliente.setNome(nome_geral);
                cliente.setTelefone(telefone_cliente);
                ClienteDAO clDAO = new ClienteDAO();
                clDAO.setLimite(cliente);
                
                Compras compra = new Compras();
                VendaDAO vnDAO = new VendaDAO();
                compra.setId_venda(idCompra);
                compra.setSituacao("PAGO");
                vnDAO.resolveCompras(compra);
                
                JOptionPane.showMessageDialog(this, "Compra Recebida Com Sucesso!", "Atenção!",1);
                limparVariaveis();
                this.dispose();
                
            }else if(jLabelSenhaConfirm.getText().equals("tornar o cliente inativo.")){
                Clientes cliente = new Clientes();
                
                cliente.setNome(nome_geral);
                cliente.setTelefone(telefone_cliente);
                cliente.setSituacao("AUSENTE");
                
                ClienteDAO clDAO = new ClienteDAO();
                clDAO.setStatus(cliente);
       
                JOptionPane.showMessageDialog(this, "Cliente Indisponível Temporariamente!","Atenção!",1);
                limparVariaveis();
                this.dispose();
                
            }else if(jLabelSenhaConfirm.getText().equals("ativar o cliente.")){
                Clientes cliente = new Clientes();
                cliente.setNome(nome_geral);
                cliente.setTelefone(telefone_cliente);
                cliente.setSituacao("Ativo");
                
                ClienteDAO clDAO = new ClienteDAO();
                clDAO.setStatus(cliente);
                JOptionPane.showMessageDialog(this, "Cliente Disponível Para Novas Compras!","Atenção!",1);
                limparVariaveis();
                this.dispose();
                
            }else if(jLabelSenhaConfirm.getText().equals("finalizar o acordo de compras do cliente.")){
                Pagamentos pagamento = new Pagamentos();
                pagamento.setDescricao(descricao_compra);
                pagamento.setDt_compra(data_compra);
                pagamento.setDt_pagamento(data_secundaria);
                pagamento.setId_vendas(idCompra);
                pagamento.setNm_vendedor(nome_alternativo);
                pagamento.setNome_cliente(nome_geral);
                pagamento.setTelefone_cliente(telefone_cliente);
                pagamento.setTipo_venda("Acordo");
                pagamento.setValor_compra(valor_compra);
                pagamento.setValor_pago(valorPg_Compraa);
                
                PagamentosDAO pgDAO = new PagamentosDAO();
                pgDAO.salvarPagamentos(pagamento);
                
                Clientes cliente = new Clientes();
                cliente.setLimite(limite_cliente);
                cliente.setNome(nome_geral);
                cliente.setTelefone(telefone_cliente);
                
                ClienteDAO clDAO = new ClienteDAO();
                clDAO.setLimite(cliente);
                
                Compras compra = new Compras();
                if(valor_restanteCompra == 0){
                    compra.setSituacao("PAGO");
                }else{
                    compra.setSituacao("PENDENTE");
                }
                compra.setValor_venda(valor_restanteCompra);
                compra.setId_venda(idCompra);
                VendaDAO vnDAO = new VendaDAO();
                vnDAO.resolveAcordoCompras(compra);
                
                JOptionPane.showMessageDialog(this, "Acordo Realizado Com Sucesso!", "Alerta!",1);
                limparVariaveis();
                this.dispose();
            }else if(jLabelSenhaConfirm.getText().equals("finalizar a alteração de um funcionário.")){
                Vendedor vendedor = new Vendedor();
                vendedor.setNome(nome_geral);
                vendedor.setUsuario(nome_usuario);
                vendedor.setSenha(senha_usuario);
                
                usDAO.alterarUsuario(vendedor);
                JOptionPane.showMessageDialog(this, "Os Dados Foram Atualizados Com Sucesso!","Alerta!",1);
                limparVariaveis();
                this.dispose();   
                
            }
            
        }else{
            JOptionPane.showMessageDialog(this, "Senha Incorreta, tente novamente!", "Alerta!",1);
        }
            
    }//GEN-LAST:event_jBtnFinalizarAcaoActionPerformed

    private void jBtnCancelarPassCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarPassCadActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jBtnCancelarPassCadActionPerformed

    private void jPassFinalCadFunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPassFinalCadFunKeyPressed
        // TODO add your handling code here:
        UsuarioDAO usDAO = new UsuarioDAO();
        char[] pass = jPassFinalCadFun.getPassword();
        String senha = String.valueOf(pass);
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(usDAO.validarLogin(nm_user, senha )== true){
            
                if(jLabelSenhaConfirm.getText().equals("finalizar o cadastro de um novo vendedor.")){
                

                    Vendedor vendedor = new Vendedor();
                    vendedor.setNome(nome_geral);
                    vendedor.setUsuario(nome_usuario);
                    vendedor.setSenha(senha_usuario);
                    usDAO.salvarUsuarios(vendedor);
                    JOptionPane.showMessageDialog(this, "Usuário Cadastrado Com Sucesso!", "Atenção!",1);
                    limparVariaveis();
                    this.dispose();
                
                }else if(jLabelSenhaConfirm.getText().equals("finalizar o cadastro de um novo cliente.")){
                    ClienteDAO clDAO = new ClienteDAO();

                    Clientes cliente = new Clientes();
                    cliente.setNome(nome_geral);
                    cliente.setTelefone(telefone_cliente);
                    cliente.setEndereco(endereco_cliente);
                    cliente.setLimite(limite_cliente);
                    cliente.setNm_vendedor(nome_alternativo);

                    clDAO.salvar(cliente);
                    JOptionPane.showMessageDialog(this, "Cliente Cadastrado Com Sucesso!", "Atenção!",1);
                    limparVariaveis();
                    this.dispose();
                
                }else if(jLabelSenhaConfirm.getText().equals("promover o funcionário.")){
                
                    Vendedor vendedor = new Vendedor();
                    vendedor.setNome(nome_geral);
                    vendedor.setCargo(cargo_usuario);
                    usDAO.alteraCargo(vendedor);

                    JOptionPane.showMessageDialog(this, "Cargo Promovido com Sucesso!","Atenção!",1);
                    limparVariaveis();
                    this.dispose();
               
                }else if(jLabelSenhaConfirm.getText().equals("finalizar a compra do cliente")){
            
                    //double limiteCliente = limite_cliente - valor_compra;
                    //String limiteArredondado = new DecimalFormat("#,##0.00").format(limiteCliente);
                    //String limiteA = limiteArredondado.replace(",", ".");
                    //double limiteFinal = Double.valueOf(limiteA);

                    Compras compra = new Compras();


                    compra.setNome_cliente(nome_geral);
                    compra.setDescricao(descricao_compra);
                    compra.setDt_venda(data_compra);
                    compra.setNm_vendedor(nome_alternativo);
                    compra.setTelefone_cliente(telefone_cliente);
                    compra.setValor_venda(valor_compra);

                    VendaDAO vnDAO = new VendaDAO();
                    vnDAO.addVendas(compra);

                    Clientes cliente = new Clientes();

                    cliente.setNome(nome_geral);
                    cliente.setTelefone(telefone_cliente);
                    cliente.setLimite(limite_cliente);

                    ClienteDAO cl = new ClienteDAO();
                    cl.setLimite(cliente);

                    JOptionPane.showMessageDialog(this, "Compra Adicionada Com Sucesso!","Atenção",1);
                    limparVariaveis();
                    this.dispose();
                
           
                }else if(jLabelSenhaConfirm.getText().equals("finalizar o recebimento da compra")){
                    Pagamentos pagamento = new Pagamentos();
                    pagamento.setNome_cliente(nome_geral);
                    pagamento.setTelefone_cliente(telefone_cliente);
                    pagamento.setId_vendas(idCompra);
                    pagamento.setDt_pagamento(data_secundaria);
                    pagamento.setDt_compra(data_compra);
                    pagamento.setDescricao(descricao_compra);
                    pagamento.setTipo_venda("À Vista");
                    pagamento.setValor_pago(valorPg_Compraa);
                    pagamento.setValor_compra(valor_compra);
                    pagamento.setNm_vendedor(nome_alternativo);

                    PagamentosDAO pgDAO = new PagamentosDAO();
                    pgDAO.salvarPagamentos(pagamento);

                    Clientes cliente = new Clientes();
                    cliente.setLimite(limite_cliente);
                    cliente.setNome(nome_geral);
                    cliente.setTelefone(telefone_cliente);
                    ClienteDAO clDAO = new ClienteDAO();
                    clDAO.setLimite(cliente);

                    Compras compra = new Compras();
                    VendaDAO vnDAO = new VendaDAO();
                    compra.setId_venda(idCompra);
                    compra.setSituacao("PAGO");
                    vnDAO.resolveCompras(compra);

                    JOptionPane.showMessageDialog(this, "Compra Recebida Com Sucesso!", "Atenção!",1);
                    limparVariaveis();
                    this.dispose();
                }else if(jLabelSenhaConfirm.getText().equals("tornar o cliente inativo.")){
                    Clientes cliente = new Clientes();

                    cliente.setNome(nome_geral);
                    cliente.setTelefone(telefone_cliente);
                    cliente.setSituacao("AUSENTE");

                    ClienteDAO clDAO = new ClienteDAO();
                    clDAO.setStatus(cliente);
                    JOptionPane.showMessageDialog(this, "Cliente Indisponível Temporariamente!","Atenção!",1);
                    limparVariaveis();
                    this.dispose();
            
                }else if(jLabelSenhaConfirm.getText().equals("ativar o cliente.")){
                    Clientes cliente = new Clientes();
                    cliente.setNome(nome_geral);
                    cliente.setTelefone(telefone_cliente);
                    cliente.setSituacao("Ativo");

                    ClienteDAO clDAO = new ClienteDAO();
                    clDAO.setStatus(cliente);
                    JOptionPane.showMessageDialog(this, "Cliente Disponível Para Novas Compras!","Atenção!",1);
                    limparVariaveis();
                    this.dispose();
                }else if(jLabelSenhaConfirm.getText().equals("finalizar o acordo de compras do cliente.")){
                    Pagamentos pagamento = new Pagamentos();
                    pagamento.setDescricao(descricao_compra);
                    pagamento.setDt_compra(data_compra);
                    pagamento.setDt_pagamento(data_secundaria);
                    pagamento.setId_vendas(idCompra);
                    pagamento.setNm_vendedor(nome_alternativo);
                    pagamento.setNome_cliente(nome_geral);
                    pagamento.setTelefone_cliente(telefone_cliente);
                    pagamento.setTipo_venda("Acordo");
                    pagamento.setValor_compra(valor_compra);
                    pagamento.setValor_pago(valorPg_Compraa);

                    PagamentosDAO pgDAO = new PagamentosDAO();
                    pgDAO.salvarPagamentos(pagamento);

                    Clientes cliente = new Clientes();
                    cliente.setLimite(limite_cliente);
                    cliente.setNome(nome_geral);
                    cliente.setTelefone(telefone_cliente);

                    ClienteDAO clDAO = new ClienteDAO();
                    clDAO.setLimite(cliente);

                    Compras compra = new Compras();
                    if(valor_restanteCompra == 0){
                        compra.setSituacao("PAGO");
                    }else{
                        compra.setSituacao("PENDENTE");
                    }
                    compra.setValor_venda(valor_restanteCompra);
                    compra.setId_venda(idCompra);
                    VendaDAO vnDAO = new VendaDAO();
                    vnDAO.resolveAcordoCompras(compra);

                    JOptionPane.showMessageDialog(this, "Acordo Realizado Com Sucesso!", "Alerta!",1);
                    limparVariaveis();
                    this.dispose();
                }else if(jLabelSenhaConfirm.getText().equals("finalizar a alteração de um funcionário.")){
                    Vendedor vendedor = new Vendedor();
                    vendedor.setNome(nome_geral);
                    vendedor.setUsuario(nome_usuario);
                    vendedor.setSenha(senha_usuario);
                
                    usDAO.alterarUsuario(vendedor);
                    JOptionPane.showMessageDialog(this, "Os Dados Foram Atualizados Com Sucesso!","Alerta!",1);
                    limparVariaveis();
                    this.dispose();   
                }
            
                
            
            }else{               
                 JOptionPane.showMessageDialog(this, "Senha Incorreta, tente novamente!", "Alerta!",1);
            }
        }       
    }//GEN-LAST:event_jPassFinalCadFunKeyPressed

    int mx;
    int my;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Senha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Senha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Senha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Senha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Senha().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCancelarPassCad;
    private javax.swing.JButton jBtnFinalizarAcao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelNomeSenha;
    private javax.swing.JLabel jLabelSenhaConfirm;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPassFinalCadFun;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
