/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
import DAO.ClienteDAO;
import DAO.PagamentosDAO;
import DAO.UsuarioDAO;
import DAO.VendaDAO;
import java.awt.CardLayout;
import Views.Senha;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import sistemaloja.Clientes;
import sistemaloja.Compras;
import sistemaloja.Pagamentos;
import sistemaloja.Vendedor;
/**
 *
 * @author Jefferson César
 */
public class AreaUsuario extends javax.swing.JFrame {
    Vendedor sessao;
    double valorLimite = 0;
    
    public void listarTabelaCompra(String nome, String telefone){
        DefaultTableModel valor = (DefaultTableModel) jTableComprasCliente.getModel();
        
        valor.getDataVector().removeAllElements();
        VendaDAO vnDAO = new VendaDAO();                
        List<Compras> compraas = vnDAO.listarCompras(nome, telefone);
        
        int i = 0;
        while(compraas.size()>i){
            valor.addRow(new Object[]{compraas.get(i).getId_venda(), compraas.get(i).getDt_venda(), compraas.get(i).getDescricao(), compraas.get(i).getValor_venda(), compraas.get(i).getSituacao(), compraas.get(i).getNm_vendedor()});
            i++;
        }
        
    }
    
    
    public double setValorDouble(String valor){
        String valorA = valor.replace(".", "");
        String valorB = valorA.replace(",", ".");
        
        double valorFinal = Double.valueOf(valorB);
        return valorFinal;
    }
    
    public String arredondaValores(double valor){
        DecimalFormat decimal = new DecimalFormat("#,##0.00");
        decimal.setRoundingMode(RoundingMode.DOWN);
        String valorArredondado = decimal.format(valor);
        return valorArredondado;
    }
    public String setDt(String data){
        String dt = data.replace(" ", "");
        return dt;
    }
    
    public void listarTabelaPagamentos(){
        DefaultTableModel valor = (DefaultTableModel) jTableRelatorioPagamento.getModel();
        
        valor.getDataVector().removeAllElements();
        PagamentosDAO pgDAO = new PagamentosDAO();
        
        List<Pagamentos> pagamentos = pgDAO.listarPagamentos();
        
        int i = 0;
        while(pagamentos.size()>i){
            valor.addRow(new Object[]{pagamentos.get(i).getNome_cliente(), pagamentos.get(i).getTelefone_cliente(), pagamentos.get(i).getDt_compra(), pagamentos.get(i).getValor_compra(), pagamentos.get(i).getDescricao(), pagamentos.get(i).getValor_pago(), pagamentos.get(i).getTipo_venda(), pagamentos.get(i).getDt_pagamento(), pagamentos.get(i).getNm_vendedor()});
            i++;
        }
    }
    public void listarTabelaFuncionario(){
        DefaultTableModel valor = (DefaultTableModel) jTableFuncionario.getModel();
        
        valor.getDataVector().removeAllElements();
        UsuarioDAO usDAO = new UsuarioDAO();
        
        List<Vendedor> vendedor = usDAO.listarTodos();
        
        int i = 0;
        while(vendedor.size()>i){
            valor.addRow(new Object[]{vendedor.get(i).getNome()});
            i++;
        }
        
    }
    
    public void listaTabelaCompras(){
        DefaultTableModel valor = (DefaultTableModel) jTableRelatorioVendas.getModel();
        
        valor.getDataVector().removeAllElements();
        VendaDAO vnDAO = new VendaDAO();
        
        List<Compras> compras = vnDAO.listarTodas();
        
        int i = 0;
        while(compras.size()>i){
            
            valor.addRow(new Object[]{compras.get(i).getNome_cliente(), compras.get(i).getTelefone_cliente(), compras.get(i).getDt_venda(), compras.get(i).getValor_venda(), compras.get(i).getDescricao(), compras.get(i).getSituacao(), compras.get(i).getNm_vendedor()});
            i++;
        }
    }
    
    public void listarTabelaCliente(){
        DefaultTableModel valor = (DefaultTableModel) jTableClientes.getModel();
        
        valor.getDataVector().removeAllElements();
        ClienteDAO clDAO = new ClienteDAO();
        
        List<Clientes> cliente = clDAO.listarTodos();
        
        int i = 0;
        while(cliente.size()>i){
            
            valor.addRow(new Object[]{cliente.get(i).getNome(), cliente.get(i).getTelefone(), cliente.get(i).getEndereco(), cliente.get(i).getSituacao(), cliente.get(i).getNm_vendedor()});
            i++;
        }
        
    }
    
    public String somaTable(){
        double valor = 0;
        String valorA = "";
        String valorB = "";
        double valores = 0;
        for(int i = 0; i<jTableComprasCliente.getRowCount(); i++){
            
            if(jTableComprasCliente.getModel().getValueAt(i, 4).equals("PENDENTE")){
                valor = (double) jTableComprasCliente.getModel().getValueAt(i, 3);
                valorA = String.valueOf(valor);
                valorB = valorA.replace(",", ".");
                valores += Double.valueOf(valorB);
            }else{
                i++;
            }   
            
        }
        //String valorA = String.valueOf(valor);
        DecimalFormat decimal = new DecimalFormat("#,##0.00");
        decimal.setRoundingMode(RoundingMode.DOWN);
        String ResultFinal = decimal.format(valores);
        return ResultFinal;
        
    }
    
    public int countMarcados(String nome){
        int j = 0;
        for(int i = 0; i<jTableRelatorioVendas.getRowCount(); i++){
            if(jTableRelatorioVendas.getModel().getValueAt(i, 6).equals(nome)){
                j++;
            }else{
                i++;
            }
        }
        return j;
    }
    
    public int countMarcadosPg(String nome){
        int j = 0;
        for(int i = 0; i<jTableRelatorioVendas.getRowCount(); i++){
            if(jTableRelatorioVendas.getModel().getValueAt(i, 5).equals("PAGO") && jTableRelatorioVendas.getModel().getValueAt(i, 6).equals(nome)){
                j++;
            }else{
                i++;
            }
        }
        return j;
    }
    public int countPgRealizados(String nome){
        int j = 0;
        for(int i = 0; i<jTableRelatorioPagamento.getRowCount();i++){
            if(jTableRelatorioPagamento.getModel().getValueAt(i, 8).equals(nome)){
                j++;
            }else{
                i++;
            }
        }
        return j;
    }
    
    
   
    /**
     * Creates new form AreaUsuario
     */
    public AreaUsuario() {
        initComponents();
        setCamposValor();
        jPanelFazerAcordo.setVisible(false);
        jPanelRecebimentoCompra.setVisible(false);
        jPanelMostrarSituação.setVisible(false);
        jPanelCampoNome.setVisible(false);
        jPanelCampoData.setVisible(false);
        jPanelBtnPesquisar.setVisible(false);
        jPanelBtnVerDetalhes.setVisible(false);
        jPanelBtnReceberTudo.setVisible(false);
        jPanelFazerAcordo.setVisible(false);
        jPanelValorAreceber.setVisible(false);
        jPanelBtns.setVisible(false);
        jPanelLabelSenha.setVisible(false);
        jPanelSenha.setVisible(false);
        jPanelBtnAcaoCliente.setVisible(false);
        listarTabelaPagamentos();
        listaTabelaCompras();
        
        
        
        
    }
    
    public void setCamposValor(){
        DecimalFormat decimal = new DecimalFormat("#,###.00");
        NumberFormatter numFormatter = new NumberFormatter(decimal);
        numFormatter.setFormat(decimal);
        numFormatter.setAllowsInvalid(false);
        DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
        jFormattedValorCadCliente.setFormatterFactory(dfFactory);
        jFormattedAlterarDados.setFormatterFactory(dfFactory);
        jFormattedTxtValorCompra.setFormatterFactory(dfFactory);
        jFormattedTxtValor.setFormatterFactory(dfFactory);
        jFormattedTxtValorAReceber.setFormatterFactory(dfFactory);
        
    }
            
  
    public void setUsLogin(Vendedor vendedor){
        UsuarioDAO usDAO = new UsuarioDAO();
        usDAO.dadosUsuarioLogin(vendedor);
        sessao = vendedor;
    }
    
    
    
    
    public void bemVindo(){
        jLabelNomeUsuario.setText(sessao.getNome());
        jLabelCargoUsuario.setText(sessao.getCargo());
        jLabelNomePerfil.setText(sessao.getNome());
        jLabelUsuarioPerfil.setText(sessao.getUsuario());
        jLabelSenhaUsuario.setText(sessao.getSenha());
        jLabelCargoPerfil.setText(sessao.getCargo());
        
        if(sessao.getCargo().equals("Funcionário")){
            jPanelBtnNvCliente.setVisible(false);
            jPanelBtnGerenciamento.setVisible(false);
        }
        
    }
    
   
       

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelBtnNvCliente = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jBtnNvCliente = new javax.swing.JButton();
        jPanelBtnGerenciamento = new javax.swing.JPanel();
        jBtnGerenciar = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jPanelBtnLogout = new javax.swing.JPanel();
        jBtnLogout = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanelMeuPerfil = new javax.swing.JPanel();
        jBtnMeuPerfil = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jPanelBtnConsulta = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jBtnConsulta = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanelHome = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelNomeUsuario = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel17 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelCargoUsuario = new javax.swing.JLabel();
        jPanelCadastro = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jTxtNomeCadCliente = new javax.swing.JTextField();
        jTxtEnderecoCadCliente = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jFormattedValorCadCliente = new javax.swing.JFormattedTextField();
        jLabel32 = new javax.swing.JLabel();
        jFormattTelCadCliente = new javax.swing.JFormattedTextField();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jBtnCadastrarCliente = new javax.swing.JButton();
        jBtnLimparCadCliente = new javax.swing.JButton();
        jBtnVoltarCad = new javax.swing.JButton();
        jPanelConsulta = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTxtFiltro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jBtnPesquisarConsulta = new javax.swing.JButton();
        jBtnVoltaConsultar = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        jLabelSaldoDisp = new javax.swing.JLabel();
        jPanelBtnAcaoCliente = new javax.swing.JPanel();
        jBtnAlterar = new javax.swing.JButton();
        jBtnVerDetalhes = new javax.swing.JButton();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jPanelAlterar = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jTxtNomeAlterar = new javax.swing.JTextField();
        jTxtEnderecoAlterarClientes = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanelMostrarSituação = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jCheckAtivarCliente = new javax.swing.JCheckBox();
        jBtnAtivar = new javax.swing.JButton();
        jLabel93 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jFormattedAlterarDados = new javax.swing.JFormattedTextField();
        jLabel43 = new javax.swing.JLabel();
        jFormattedTxtTelefone = new javax.swing.JFormattedTextField();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jBtnVoltarAlterarDadosClientes = new javax.swing.JButton();
        jPanelBtnSalvar = new javax.swing.JPanel();
        jBtnSalvarDadosAlterados = new javax.swing.JButton();
        jPanelGerenciamento = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jBtnVoltarGerenciador = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableFuncionario = new javax.swing.JTable();
        jPanelBtns = new javax.swing.JPanel();
        jPanelBtnApagar = new javax.swing.JPanel();
        jBtnApagar = new javax.swing.JButton();
        jBtnVerDetalhesFuncionario = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jTxtNomeFuncCad = new javax.swing.JTextField();
        jTxtUserFunCad = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPassFunCad = new javax.swing.JPasswordField();
        jBtnCadastroGerencia = new javax.swing.JButton();
        jBtnLimpaCamposGeren = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanelPerfil = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanelLabelSenha = new javax.swing.JPanel();
        jLabelSenhaUsuario = new javax.swing.JLabel();
        jLabelNomePerfil = new javax.swing.JLabel();
        jLabelUsuarioPerfil = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabelCargoPerfil = new javax.swing.JLabel();
        jCheckSenha = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabelQntMarcados = new javax.swing.JLabel();
        jLabelQntMarcadosRec = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jBtnVoltarPerfil = new javax.swing.JButton();
        jPanelBtnRelatorios = new javax.swing.JPanel();
        jBtnRelatorioPg = new javax.swing.JButton();
        jBtnRelatorioVenda = new javax.swing.JButton();
        jPanelDetalhes = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabelNomeDetalhes = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabelTelefoneDetalhesCliente = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabelEndereçoDetalhesCliente = new javax.swing.JLabel();
        jLabelLimiteDisponivelDetalhesClientes = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabelStatusCliente = new javax.swing.JLabel();
        jPanelExcluirCliente = new javax.swing.JPanel();
        jBtnExcluirCliente = new javax.swing.JButton();
        jLabel101 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableComprasCliente = new javax.swing.JTable();
        jBtnMarcarVenda = new javax.swing.JButton();
        jBtnVoltarDetalhes = new javax.swing.JButton();
        jPanelBtnVerDetalhes = new javax.swing.JPanel();
        jBtnVerDetalhesCompras = new javax.swing.JButton();
        jLabel88 = new javax.swing.JLabel();
        jLabelValorTotal = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jPanelInfoFuncionario = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabelNomeFun = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabelNomeFuncionario = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabelUsuarioFuncionario = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabelCargoFuncionario = new javax.swing.JLabel();
        jPanelSenha = new javax.swing.JPanel();
        jLabelSenhaFuncionario = new javax.swing.JLabel();
        jCheckSenhaColaborador = new javax.swing.JCheckBox();
        jPanelPromover = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jRadioSub = new javax.swing.JRadioButton();
        jBtnPromover = new javax.swing.JButton();
        jRadioADM = new javax.swing.JRadioButton();
        jRadioFunc = new javax.swing.JRadioButton();
        jLabel62 = new javax.swing.JLabel();
        jLabelVendasMarcadasInfoFun = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabelVendasRecInfoFun = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jBtnVoltarTelaFuncionario = new javax.swing.JButton();
        jPanelAddCompraCliente = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jPanel = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jFormattedTxtDataCompra = new javax.swing.JFormattedTextField();
        jLabel58 = new javax.swing.JLabel();
        jFormattedTxtValorCompra = new javax.swing.JFormattedTextField();
        jLabel68 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPanelDescriçaoCompra = new javax.swing.JTextPane();
        jLabel67 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jBtnAdicionarCompras = new javax.swing.JButton();
        jBtnCancelarCompra = new javax.swing.JButton();
        jLabel60 = new javax.swing.JLabel();
        jLabelNomeVendedor = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jPanelAlterarDadosFunc = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jTxtNomeFunEditar = new javax.swing.JTextField();
        jTxtNmUsuarioEditar = new javax.swing.JTextField();
        jPassSenhaFuncEditar = new javax.swing.JPasswordField();
        jCheckMostrarSenha = new javax.swing.JCheckBox();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jBtnAlterarFun = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanelDetalhesCompra = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jTxtNomeDetalhesCompra = new javax.swing.JTextField();
        jFormattedTxtValor = new javax.swing.JFormattedTextField();
        jLabel80 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jFormattedTxtDtVenda = new javax.swing.JFormattedTextField();
        jLabel84 = new javax.swing.JLabel();
        jTxtIDCompra = new javax.swing.JTextField();
        jPanelRecebimentoCompra = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jFormattedTxtDtRecibo = new javax.swing.JFormattedTextField();
        jPanelValorAreceber = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jFormattedTxtValorAReceber = new javax.swing.JFormattedTextField();
        jPanelBtnReceberTudo = new javax.swing.JPanel();
        jBtnReceberTudo = new javax.swing.JButton();
        jPanelFazerAcordo = new javax.swing.JPanel();
        jBtnFazerAcordo = new javax.swing.JButton();
        jPanelMetodoPg = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jRadioAVista = new javax.swing.JRadioButton();
        jRadioAcordo = new javax.swing.JRadioButton();
        jPanelDescricao = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTtxtDescricaoDetalhes = new javax.swing.JTextArea();
        jLabel81 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabelNomeCliente = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jBtnVoltarComprasDetalhes = new javax.swing.JButton();
        jPanelRelatorios = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jLabelTituloRelatorio = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jPanel19 = new javax.swing.JPanel();
        jPanelRelatorioTables = new javax.swing.JPanel();
        jPanelTableVendas = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableRelatorioVendas = new javax.swing.JTable();
        jPanelTablePg = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableRelatorioPagamento = new javax.swing.JTable();
        jPanelRelatorioInfo = new javax.swing.JPanel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jTextNomeClienteRel = new javax.swing.JTextField();
        jTextTelefoneRel = new javax.swing.JTextField();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jTextDtCompra = new javax.swing.JTextField();
        jLabel119 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jTextValorCompraRel = new javax.swing.JTextField();
        jPanelPg = new javax.swing.JPanel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jTextDtPgRel = new javax.swing.JTextField();
        jLabel122 = new javax.swing.JLabel();
        jTextValorPgRel = new javax.swing.JTextField();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel123 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextDescRel = new javax.swing.JTextPane();
        jBtnVoltarRelatorio1 = new javax.swing.JButton();
        jRadioBtnNmCliente = new javax.swing.JRadioButton();
        jLabel104 = new javax.swing.JLabel();
        jPanelCampoNome = new javax.swing.JPanel();
        jTextNomePesquisa = new javax.swing.JTextField();
        jPanelCampoData = new javax.swing.JPanel();
        jFormattedTxtDt = new javax.swing.JFormattedTextField();
        jPanelBtnPesquisar = new javax.swing.JPanel();
        jBtnPesquisar = new javax.swing.JButton();
        jPanelComprasPendentes = new javax.swing.JPanel();
        jRadioBtnComprasPendentes = new javax.swing.JRadioButton();
        jRadioBtnDtVenda = new javax.swing.JRadioButton();
        jPanelDtPg = new javax.swing.JPanel();
        jRadioBtnDtPg = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(400, 100));
        setMinimumSize(new java.awt.Dimension(690, 530));
        setPreferredSize(new java.awt.Dimension(716, 500));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/loja-virtual-icone_1.png"))); // NOI18N

        jPanelBtnNvCliente.setBackground(new java.awt.Color(255, 51, 51));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-adicionar-grupo-de-usuários-homem-homem-50.png"))); // NOI18N

        jBtnNvCliente.setText("Novo Cliente");
        jBtnNvCliente.setMaximumSize(new java.awt.Dimension(79, 23));
        jBtnNvCliente.setMinimumSize(new java.awt.Dimension(79, 23));
        jBtnNvCliente.setPreferredSize(new java.awt.Dimension(79, 23));
        jBtnNvCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnNvClienteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelBtnNvClienteLayout = new javax.swing.GroupLayout(jPanelBtnNvCliente);
        jPanelBtnNvCliente.setLayout(jPanelBtnNvClienteLayout);
        jPanelBtnNvClienteLayout.setHorizontalGroup(
            jPanelBtnNvClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBtnNvClienteLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnNvCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelBtnNvClienteLayout.setVerticalGroup(
            jPanelBtnNvClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jBtnNvCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelBtnGerenciamento.setBackground(new java.awt.Color(255, 51, 51));
        jPanelBtnGerenciamento.setPreferredSize(new java.awt.Dimension(164, 50));

        jBtnGerenciar.setText("Gerenciar");
        jBtnGerenciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnGerenciarMouseClicked(evt);
            }
        });
        jBtnGerenciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGerenciarActionPerformed(evt);
            }
        });

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-gerente-50.png"))); // NOI18N

        javax.swing.GroupLayout jPanelBtnGerenciamentoLayout = new javax.swing.GroupLayout(jPanelBtnGerenciamento);
        jPanelBtnGerenciamento.setLayout(jPanelBtnGerenciamentoLayout);
        jPanelBtnGerenciamentoLayout.setHorizontalGroup(
            jPanelBtnGerenciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBtnGerenciamentoLayout.createSequentialGroup()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnGerenciar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );
        jPanelBtnGerenciamentoLayout.setVerticalGroup(
            jPanelBtnGerenciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBtnGerenciamentoLayout.createSequentialGroup()
                .addGroup(jPanelBtnGerenciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnGerenciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanelBtnLogout.setBackground(new java.awt.Color(255, 51, 51));
        jPanelBtnLogout.setPreferredSize(new java.awt.Dimension(164, 50));

        jBtnLogout.setText("Logout");
        jBtnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLogoutActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-logout-arredondado-50.png"))); // NOI18N

        javax.swing.GroupLayout jPanelBtnLogoutLayout = new javax.swing.GroupLayout(jPanelBtnLogout);
        jPanelBtnLogout.setLayout(jPanelBtnLogoutLayout);
        jPanelBtnLogoutLayout.setHorizontalGroup(
            jPanelBtnLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBtnLogoutLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelBtnLogoutLayout.setVerticalGroup(
            jPanelBtnLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBtnLogoutLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanelBtnLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnLogout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        jPanelMeuPerfil.setBackground(new java.awt.Color(255, 51, 51));
        jPanelMeuPerfil.setPreferredSize(new java.awt.Dimension(164, 50));

        jBtnMeuPerfil.setText("Meu Perfil");
        jBtnMeuPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnMeuPerfilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMeuPerfilLayout = new javax.swing.GroupLayout(jPanelMeuPerfil);
        jPanelMeuPerfil.setLayout(jPanelMeuPerfilLayout);
        jPanelMeuPerfilLayout.setHorizontalGroup(
            jPanelMeuPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jBtnMeuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelMeuPerfilLayout.setVerticalGroup(
            jPanelMeuPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jBtnMeuPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-usuário-homem-com-círculo-50.png"))); // NOI18N

        jPanelBtnConsulta.setBackground(new java.awt.Color(255, 51, 51));
        jPanelBtnConsulta.setPreferredSize(new java.awt.Dimension(164, 50));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-grupos-de-usuários-50.png"))); // NOI18N

        jBtnConsulta.setText("Consultar");
        jBtnConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnConsultaMouseClicked(evt);
            }
        });
        jBtnConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBtnConsultaLayout = new javax.swing.GroupLayout(jPanelBtnConsulta);
        jPanelBtnConsulta.setLayout(jPanelBtnConsultaLayout);
        jPanelBtnConsultaLayout.setHorizontalGroup(
            jPanelBtnConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBtnConsultaLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelBtnConsultaLayout.setVerticalGroup(
            jPanelBtnConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBtnConsultaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanelBtnConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBtnConsulta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanelBtnGerenciamento, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanelBtnLogout, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanelBtnNvCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jPanelMeuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelBtnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelBtnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelMeuPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanelBtnNvCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelBtnGerenciamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jPanelBtnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 500));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new java.awt.CardLayout());

        jPanelHome.setBackground(new java.awt.Color(153, 153, 153));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/caixas-plásticas-do-alimento-na-loja-106735863 (4).jpg"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/caixas-plásticas-do-alimento-na-loja-106735863 (3).jpg"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Seja Bem-Vindo");

        jLabelNomeUsuario.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelNomeUsuario.setText("jLabel3");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));

        jPanel17.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Essa é sua área de usuário");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Atualmente seu nível de acesso é como");

        jLabelCargoUsuario.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelCargoUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCargoUsuario.setText("jLabel9");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(67, 67, 67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jLabelCargoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelCargoUsuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelHomeLayout = new javax.swing.GroupLayout(jPanelHome);
        jPanelHome.setLayout(jPanelHomeLayout);
        jPanelHomeLayout.setHorizontalGroup(
            jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHomeLayout.createSequentialGroup()
                .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHomeLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelHomeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelNomeUsuario))
                    .addGroup(jPanelHomeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanelHomeLayout.setVerticalGroup(
            jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHomeLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelNomeUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel10)
                .addGap(23, 23, 23)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(52, 52, 52))
        );

        jPanel2.add(jPanelHome, "TelaHome");

        jPanelCadastro.setBackground(new java.awt.Color(153, 153, 153));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setText("Cadastro de Clientes");

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(51, 51, 51));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Nome");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Telefone");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Endereço");

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel31.setText("Limite");

        jFormattedValorCadCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("R$#,##0.###"))));
        jFormattedValorCadCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedValorCadClienteKeyPressed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("R$");

        try {
            jFormattTelCadCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)# ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-usuário-homem-com-círculo-50.png"))); // NOI18N

        jLabel97.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-telefone-sem-uso-50.png"))); // NOI18N

        jLabel98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-marcador-de-mapa-50.png"))); // NOI18N

        jLabel99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-dólar-americano-50.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel96, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel97, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jFormattTelCadCliente)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTxtNomeCadCliente, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTxtEnderecoCadCliente, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedValorCadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(141, 141, 141))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtNomeCadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel96, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattTelCadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtEnderecoCadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedValorCadCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jBtnCadastrarCliente.setText("Cadastrar");
        jBtnCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCadastrarClienteActionPerformed(evt);
            }
        });

        jBtnLimparCadCliente.setText("Limpar");
        jBtnLimparCadCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimparCadClienteActionPerformed(evt);
            }
        });

        jBtnVoltarCad.setText("Voltar");
        jBtnVoltarCad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnVoltarCadMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelCadastroLayout = new javax.swing.GroupLayout(jPanelCadastro);
        jPanelCadastro.setLayout(jPanelCadastroLayout);
        jPanelCadastroLayout.setHorizontalGroup(
            jPanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCadastroLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jBtnVoltarCad)
                .addGap(18, 18, 18)
                .addComponent(jBtnLimparCadCliente)
                .addGap(18, 18, 18)
                .addComponent(jBtnCadastrarCliente)
                .addGap(28, 28, 28))
            .addGroup(jPanelCadastroLayout.createSequentialGroup()
                .addGroup(jPanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCadastroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCadastroLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCadastroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanelCadastroLayout.setVerticalGroup(
            jPanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnVoltarCad)
                    .addComponent(jBtnLimparCadCliente)
                    .addComponent(jBtnCadastrarCliente))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jPanel2.add(jPanelCadastro, "TelaCadastro");

        jPanelConsulta.setBackground(new java.awt.Color(153, 153, 153));
        jPanelConsulta.setPreferredSize(new java.awt.Dimension(495, 500));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("Filtrar");

        jTxtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtFiltroKeyPressed(evt);
            }
        });

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nome", "Telefone", "Endereço", "Situação", "Nome_Consultor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableClientes);
        if (jTableClientes.getColumnModel().getColumnCount() > 0) {
            jTableClientes.getColumnModel().getColumn(3).setPreferredWidth(35);
        }

        jBtnPesquisarConsulta.setText("Pesquisar");
        jBtnPesquisarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPesquisarConsultaActionPerformed(evt);
            }
        });

        jBtnVoltaConsultar.setText("Voltar");
        jBtnVoltaConsultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnVoltaConsultarMouseClicked(evt);
            }
        });
        jBtnVoltaConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnVoltaConsultarActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel21.setText("Consultar Clientes");

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(51, 51, 51));

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel34.setText("Saldo Disponível");

        jLabelSaldoDisp.setBackground(new java.awt.Color(153, 153, 153));

        jPanelBtnAcaoCliente.setBackground(new java.awt.Color(153, 153, 153));

        jBtnAlterar.setText("Alterar");
        jBtnAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnAlterarMouseClicked(evt);
            }
        });
        jBtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAlterarActionPerformed(evt);
            }
        });

        jBtnVerDetalhes.setText("Ver Detalhes");
        jBtnVerDetalhes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnVerDetalhesMouseClicked(evt);
            }
        });
        jBtnVerDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnVerDetalhesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBtnAcaoClienteLayout = new javax.swing.GroupLayout(jPanelBtnAcaoCliente);
        jPanelBtnAcaoCliente.setLayout(jPanelBtnAcaoClienteLayout);
        jPanelBtnAcaoClienteLayout.setHorizontalGroup(
            jPanelBtnAcaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBtnAcaoClienteLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jBtnAlterar)
                .addGap(18, 18, 18)
                .addComponent(jBtnVerDetalhes))
        );
        jPanelBtnAcaoClienteLayout.setVerticalGroup(
            jPanelBtnAcaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBtnAcaoClienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelBtnAcaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnAlterar)
                    .addComponent(jBtnVerDetalhes))
                .addContainerGap())
        );

        jLabel94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-pesquisar-50.png"))); // NOI18N

        jLabel95.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-dólar-americano-50.png"))); // NOI18N

        javax.swing.GroupLayout jPanelConsultaLayout = new javax.swing.GroupLayout(jPanelConsulta);
        jPanelConsulta.setLayout(jPanelConsultaLayout);
        jPanelConsultaLayout.setHorizontalGroup(
            jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaLayout.createSequentialGroup()
                .addGroup(jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConsultaLayout.createSequentialGroup()
                        .addGroup(jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelConsultaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel21))
                            .addGroup(jPanelConsultaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelConsultaLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTxtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBtnPesquisarConsulta)))
                        .addGap(0, 120, Short.MAX_VALUE))
                    .addGroup(jPanelConsultaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanelConsultaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnVoltaConsultar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBtnAcaoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(jPanelConsultaLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(jLabelSaldoDisp, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelConsultaLayout.setVerticalGroup(
            jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConsultaLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnPesquisarConsulta)))
                    .addGroup(jPanelConsultaLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelConsultaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelBtnAcaoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(152, Short.MAX_VALUE))
                    .addGroup(jPanelConsultaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jBtnVoltaConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelConsultaLayout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(9, 9, 9)
                                .addComponent(jLabelSaldoDisp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(41, 41, 41))))
        );

        jPanel2.add(jPanelConsulta, "TelaConsulta");

        jPanelAlterar.setBackground(new java.awt.Color(153, 153, 153));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel17.setText("Alterando os Dados do Cliente");

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(51, 51, 51));

        jPanel4.setPreferredSize(new java.awt.Dimension(351, 256));

        jTxtNomeAlterar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtNomeAlterarKeyPressed(evt);
            }
        });

        jTxtEnderecoAlterarClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtEnderecoAlterarClientesKeyPressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Nome");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setText("Telefone");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setText("Endereço");

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel42.setText("Situação");

        jCheckAtivarCliente.setText("Ativar Cliente");

        jBtnAtivar.setText("Ativar");
        jBtnAtivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAtivarActionPerformed(evt);
            }
        });

        jLabel93.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-usuário-verificado-masculino-50.png"))); // NOI18N

        javax.swing.GroupLayout jPanelMostrarSituaçãoLayout = new javax.swing.GroupLayout(jPanelMostrarSituação);
        jPanelMostrarSituação.setLayout(jPanelMostrarSituaçãoLayout);
        jPanelMostrarSituaçãoLayout.setHorizontalGroup(
            jPanelMostrarSituaçãoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMostrarSituaçãoLayout.createSequentialGroup()
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelMostrarSituaçãoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(jCheckAtivarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnAtivar)
                .addContainerGap())
        );
        jPanelMostrarSituaçãoLayout.setVerticalGroup(
            jPanelMostrarSituaçãoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMostrarSituaçãoLayout.createSequentialGroup()
                .addGroup(jPanelMostrarSituaçãoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMostrarSituaçãoLayout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMostrarSituaçãoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnAtivar)
                            .addComponent(jCheckAtivarCliente)))
                    .addGroup(jPanelMostrarSituaçãoLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel40.setText("Limite");

        jFormattedAlterarDados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedAlterarDadosKeyPressed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("R$");

        try {
            jFormattedTxtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)# ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTxtTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTxtTelefoneKeyPressed(evt);
            }
        });

        jLabel89.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-usuário-homem-com-círculo-50.png"))); // NOI18N

        jLabel90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-telefone-sem-uso-50.png"))); // NOI18N

        jLabel91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-marcador-de-mapa-50.png"))); // NOI18N

        jLabel92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-dólar-americano-50.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelMostrarSituação, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                            .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel91, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel40)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jFormattedAlterarDados, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel19)
                                    .addComponent(jTxtNomeAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                    .addComponent(jTxtEnderecoAlterarClientes)))
                            .addComponent(jFormattedTxtTelefone))))
                .addGap(130, 130, 130))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtNomeAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtEnderecoAlterarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedAlterarDados)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelMostrarSituação, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBtnVoltarAlterarDadosClientes.setText("Voltar");
        jBtnVoltarAlterarDadosClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnVoltarAlterarDadosClientesActionPerformed(evt);
            }
        });

        jPanelBtnSalvar.setBackground(new java.awt.Color(153, 153, 153));

        jBtnSalvarDadosAlterados.setText("Salvar");
        jBtnSalvarDadosAlterados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSalvarDadosAlteradosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBtnSalvarLayout = new javax.swing.GroupLayout(jPanelBtnSalvar);
        jPanelBtnSalvar.setLayout(jPanelBtnSalvarLayout);
        jPanelBtnSalvarLayout.setHorizontalGroup(
            jPanelBtnSalvarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBtnSalvarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnSalvarDadosAlterados)
                .addContainerGap())
        );
        jPanelBtnSalvarLayout.setVerticalGroup(
            jPanelBtnSalvarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBtnSalvarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jBtnSalvarDadosAlterados))
        );

        javax.swing.GroupLayout jPanelAlterarLayout = new javax.swing.GroupLayout(jPanelAlterar);
        jPanelAlterar.setLayout(jPanelAlterarLayout);
        jPanelAlterarLayout.setHorizontalGroup(
            jPanelAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlterarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAlterarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnVoltarAlterarDadosClientes)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelBtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(jPanelAlterarLayout.createSequentialGroup()
                        .addGroup(jPanelAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(22, Short.MAX_VALUE))))
        );
        jPanelAlterarLayout.setVerticalGroup(
            jPanelAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAlterarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnVoltarAlterarDadosClientes)
                    .addComponent(jPanelBtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        jPanel2.add(jPanelAlterar, "TelaAlterar");

        jPanelGerenciamento.setBackground(new java.awt.Color(153, 153, 153));

        jLabel23.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel23.setText("Gerenciamento de Funcionários");

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(51, 51, 51));

        jBtnVoltarGerenciador.setText("Voltar");
        jBtnVoltarGerenciador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnVoltarGerenciadorMouseClicked(evt);
            }
        });
        jBtnVoltarGerenciador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnVoltarGerenciadorActionPerformed(evt);
            }
        });

        jTableFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFuncionarioMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableFuncionario);

        jBtnApagar.setText("Apagar");
        jBtnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnApagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBtnApagarLayout = new javax.swing.GroupLayout(jPanelBtnApagar);
        jPanelBtnApagar.setLayout(jPanelBtnApagarLayout);
        jPanelBtnApagarLayout.setHorizontalGroup(
            jPanelBtnApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBtnApagarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnApagar)
                .addContainerGap())
        );
        jPanelBtnApagarLayout.setVerticalGroup(
            jPanelBtnApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBtnApagarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jBtnApagar))
        );

        jBtnVerDetalhesFuncionario.setText("Ver Detalhes");
        jBtnVerDetalhesFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnVerDetalhesFuncionarioMouseClicked(evt);
            }
        });
        jBtnVerDetalhesFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnVerDetalhesFuncionarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBtnsLayout = new javax.swing.GroupLayout(jPanelBtns);
        jPanelBtns.setLayout(jPanelBtnsLayout);
        jPanelBtnsLayout.setHorizontalGroup(
            jPanelBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBtnsLayout.createSequentialGroup()
                .addComponent(jPanelBtnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnVerDetalhesFuncionario)
                .addGap(0, 36, Short.MAX_VALUE))
        );
        jPanelBtnsLayout.setVerticalGroup(
            jPanelBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBtnsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelBtnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnVerDetalhesFuncionario))
                .addGap(2, 2, 2))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanelBtns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanelBtns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel24.setText("Nome");

        jLabel25.setText("Usuario");

        jLabel26.setText("Senha");

        jPassFunCad.setPreferredSize(new java.awt.Dimension(111, 30));
        jPassFunCad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPassFunCadKeyPressed(evt);
            }
        });

        jBtnCadastroGerencia.setText("Cadastrar");
        jBtnCadastroGerencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCadastroGerenciaActionPerformed(evt);
            }
        });

        jBtnLimpaCamposGeren.setText("Limpar");
        jBtnLimpaCamposGeren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimpaCamposGerenActionPerformed(evt);
            }
        });

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-usuário-50 (1).png"))); // NOI18N

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-usuário-homem-com-círculo-50.png"))); // NOI18N

        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-particular-2-50.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jBtnLimpaCamposGeren)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnCadastroGerencia))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel26)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24)
                            .addComponent(jTxtNomeFuncCad)
                            .addComponent(jTxtUserFunCad)
                            .addComponent(jPassFunCad, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))))
                .addGap(68, 68, 68))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtNomeFuncCad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtUserFunCad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPassFunCad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnCadastroGerencia)
                            .addComponent(jBtnLimpaCamposGeren))))
                .addContainerGap())
        );

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator6.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanelGerenciamentoLayout = new javax.swing.GroupLayout(jPanelGerenciamento);
        jPanelGerenciamento.setLayout(jPanelGerenciamentoLayout);
        jPanelGerenciamentoLayout.setHorizontalGroup(
            jPanelGerenciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGerenciamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGerenciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGerenciamentoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnVoltarGerenciador, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanelGerenciamentoLayout.createSequentialGroup()
                        .addGroup(jPanelGerenciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 130, Short.MAX_VALUE))
                    .addGroup(jPanelGerenciamentoLayout.createSequentialGroup()
                        .addGroup(jPanelGerenciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73))))
        );
        jPanelGerenciamentoLayout.setVerticalGroup(
            jPanelGerenciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGerenciamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelGerenciamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGerenciamentoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnVoltarGerenciador))
                    .addGroup(jPanelGerenciamentoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
        );

        jPanel2.add(jPanelGerenciamento, "TelaGerenciamento");

        jPanelPerfil.setBackground(new java.awt.Color(153, 153, 153));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Pessoais"));

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setText("Nome");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel28.setText("Usuario");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel29.setText("Senha");

        jPanelLabelSenha.setBackground(new java.awt.Color(204, 204, 204));

        jLabelSenhaUsuario.setText("jLabel30");

        javax.swing.GroupLayout jPanelLabelSenhaLayout = new javax.swing.GroupLayout(jPanelLabelSenha);
        jPanelLabelSenha.setLayout(jPanelLabelSenhaLayout);
        jPanelLabelSenhaLayout.setHorizontalGroup(
            jPanelLabelSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLabelSenhaLayout.createSequentialGroup()
                .addComponent(jLabelSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanelLabelSenhaLayout.setVerticalGroup(
            jPanelLabelSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLabelSenhaLayout.createSequentialGroup()
                .addComponent(jLabelSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jLabelNomePerfil.setText("jLabel31");

        jLabelUsuarioPerfil.setText("jLabel32");

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel33.setText("Cargo");

        jLabelCargoPerfil.setText("jLabel34");

        jCheckSenha.setBackground(new java.awt.Color(204, 204, 204));
        jCheckSenha.setText("Mostrar Senha");
        jCheckSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckSenhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabelNomePerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelLabelSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelCargoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel33))
                                .addContainerGap(24, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jCheckSenha)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabelUsuarioPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(3, 3, 3)
                        .addComponent(jLabelNomePerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(3, 3, 3)
                        .addComponent(jLabelCargoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabelUsuarioPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel29)
                        .addGap(12, 12, 12)
                        .addComponent(jPanelLabelSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jCheckSenha)))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/kisspng-computer-icons-icon-design-google-account-desktop-account-icon-5b1ab3f4eb5ff3.3034900015284766609641 (3).jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("Meu Perfil");

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator7.setForeground(new java.awt.Color(51, 51, 51));

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Adicionais"));

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel35.setText("Qnt. Marcados");

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel36.setText("Qnt. Marcados-Recebido");

        jLabelQntMarcados.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabelQntMarcados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelQntMarcados.setText("jLabel37");

        jLabelQntMarcadosRec.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabelQntMarcadosRec.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelQntMarcadosRec.setText("jLabel38");

        jLabel105.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel105.setText("Qnt. Pagamento-Realizado");

        jLabel106.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel106.setText("jLabel106");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelQntMarcados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabelQntMarcadosRec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel106, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addComponent(jLabel105))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelQntMarcados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelQntMarcadosRec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel106)))
        );

        jBtnVoltarPerfil.setText("Voltar");
        jBtnVoltarPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnVoltarPerfilActionPerformed(evt);
            }
        });

        jPanelBtnRelatorios.setBackground(new java.awt.Color(153, 153, 153));

        jBtnRelatorioPg.setText("Relatório de Pagamentos");
        jBtnRelatorioPg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRelatorioPgActionPerformed(evt);
            }
        });

        jBtnRelatorioVenda.setText("Relatório de Vendas");
        jBtnRelatorioVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRelatorioVendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBtnRelatoriosLayout = new javax.swing.GroupLayout(jPanelBtnRelatorios);
        jPanelBtnRelatorios.setLayout(jPanelBtnRelatoriosLayout);
        jPanelBtnRelatoriosLayout.setHorizontalGroup(
            jPanelBtnRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBtnRelatoriosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnRelatorioVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnRelatorioPg)
                .addContainerGap())
        );
        jPanelBtnRelatoriosLayout.setVerticalGroup(
            jPanelBtnRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBtnRelatoriosLayout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addGroup(jPanelBtnRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnRelatorioPg)
                    .addComponent(jBtnRelatorioVenda)))
        );

        javax.swing.GroupLayout jPanelPerfilLayout = new javax.swing.GroupLayout(jPanelPerfil);
        jPanelPerfil.setLayout(jPanelPerfilLayout);
        jPanelPerfilLayout.setHorizontalGroup(
            jPanelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPerfilLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPerfilLayout.createSequentialGroup()
                        .addGroup(jPanelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPerfilLayout.createSequentialGroup()
                        .addComponent(jPanelBtnRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnVoltarPerfil)
                        .addGap(34, 34, 34))
                    .addGroup(jPanelPerfilLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(jPanelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );
        jPanelPerfilLayout.setVerticalGroup(
            jPanelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPerfilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBtnRelatorios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPerfilLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jBtnVoltarPerfil)))
                .addGap(22, 22, 22))
        );

        jPanel2.add(jPanelPerfil, "TelaPerfil");

        jPanelDetalhes.setBackground(new java.awt.Color(153, 153, 153));

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Pessoais"));
        jPanel11.setPreferredSize(new java.awt.Dimension(525, 221));

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/download.png"))); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(204, 204, 204));
        jPanel14.setPreferredSize(new java.awt.Dimension(320, 190));

        jLabel51.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel51.setText("Nome");

        jLabelNomeDetalhes.setText("jLabel52");

        jLabel45.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel45.setText("Telefone");

        jLabelTelefoneDetalhesCliente.setText("jLabel46");

        jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel47.setText("Endereço");

        jLabel48.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel48.setText("Limite Disponível");

        jLabelEndereçoDetalhesCliente.setText("jLabel49");

        jLabelLimiteDisponivelDetalhesClientes.setText("jLabel50");

        jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel46.setText("Status");

        jLabelStatusCliente.setText("jLabel49");

        jPanelExcluirCliente.setBackground(new java.awt.Color(204, 204, 204));

        jBtnExcluirCliente.setText("Tornar Inativo");
        jBtnExcluirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExcluirClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelExcluirClienteLayout = new javax.swing.GroupLayout(jPanelExcluirCliente);
        jPanelExcluirCliente.setLayout(jPanelExcluirClienteLayout);
        jPanelExcluirClienteLayout.setHorizontalGroup(
            jPanelExcluirClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelExcluirClienteLayout.createSequentialGroup()
                .addGap(0, 19, Short.MAX_VALUE)
                .addComponent(jBtnExcluirCliente))
        );
        jPanelExcluirClienteLayout.setVerticalGroup(
            jPanelExcluirClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExcluirClienteLayout.createSequentialGroup()
                .addComponent(jBtnExcluirCliente)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jLabel101.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel101.setText("R$");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabelTelefoneDetalhesCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelNomeDetalhes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel47))
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel48)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabelStatusCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel101)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelLimiteDisponivelDetalhesClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelExcluirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))))
                    .addComponent(jLabelEndereçoDetalhesCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelNomeDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelLimiteDisponivelDetalhesClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel101, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefoneDetalhesCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelStatusCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel47)
                    .addComponent(jPanelExcluirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jLabelEndereçoDetalhesCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));
        jPanel12.setPreferredSize(new java.awt.Dimension(450, 200));

        jTableComprasCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Data_Venda", "Descrição", "Valor", "Situação", "Vendedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableComprasCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableComprasClienteMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableComprasCliente);
        if (jTableComprasCliente.getColumnModel().getColumnCount() > 0) {
            jTableComprasCliente.getColumnModel().getColumn(0).setPreferredWidth(15);
            jTableComprasCliente.getColumnModel().getColumn(3).setPreferredWidth(35);
            jTableComprasCliente.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        jBtnMarcarVenda.setText("Adicionar Compra");
        jBtnMarcarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnMarcarVendaActionPerformed(evt);
            }
        });

        jBtnVoltarDetalhes.setText("Voltar");
        jBtnVoltarDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnVoltarDetalhesActionPerformed(evt);
            }
        });

        jPanelBtnVerDetalhes.setBackground(new java.awt.Color(204, 204, 204));

        jBtnVerDetalhesCompras.setText("Ver detalhes");
        jBtnVerDetalhesCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnVerDetalhesComprasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBtnVerDetalhesLayout = new javax.swing.GroupLayout(jPanelBtnVerDetalhes);
        jPanelBtnVerDetalhes.setLayout(jPanelBtnVerDetalhesLayout);
        jPanelBtnVerDetalhesLayout.setHorizontalGroup(
            jPanelBtnVerDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBtnVerDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnVerDetalhesCompras)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanelBtnVerDetalhesLayout.setVerticalGroup(
            jPanelBtnVerDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBtnVerDetalhesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jBtnVerDetalhesCompras)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel88.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel88.setText("Total de Compras");

        jLabelValorTotal.setText("jLabel89");

        jLabel100.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel100.setText("R$");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel88)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel100)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jBtnVoltarDetalhes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnMarcarVenda)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelBtnVerDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanelBtnVerDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel88)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel100))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnMarcarVenda)
                            .addComponent(jBtnVoltarDetalhes))
                        .addGap(21, 21, 21))))
        );

        jLabel44.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel44.setText("Detalhes do Cliente");

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator8.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanelDetalhesLayout = new javax.swing.GroupLayout(jPanelDetalhes);
        jPanelDetalhes.setLayout(jPanelDetalhesLayout);
        jPanelDetalhesLayout.setHorizontalGroup(
            jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanelDetalhesLayout.setVerticalGroup(
            jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanelDetalhes, "TelaDetalhes");

        jPanelInfoFuncionario.setBackground(new java.awt.Color(153, 153, 153));

        jLabel49.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel49.setText("Colaborador");

        jLabelNomeFun.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelNomeFun.setText("jLabel50");

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator9.setForeground(new java.awt.Color(51, 51, 51));

        jPanel15.setBackground(new java.awt.Color(204, 204, 204));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações Pessoais"));

        jPanel16.setBackground(new java.awt.Color(204, 204, 204));

        jLabel66.setBackground(new java.awt.Color(204, 204, 204));
        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/pessoa-importante_318-10744.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel52.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel52.setText("Nome");

        jLabelNomeFuncionario.setText("jLabel54");

        jLabel55.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel55.setText("Usuario");

        jLabelUsuarioFuncionario.setText("jLabel56");

        jLabel57.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel57.setText("Cargo");

        jLabelCargoFuncionario.setText("jLabel58");

        jPanelSenha.setBackground(new java.awt.Color(204, 204, 204));

        jLabelSenhaFuncionario.setText("jLabel60");

        javax.swing.GroupLayout jPanelSenhaLayout = new javax.swing.GroupLayout(jPanelSenha);
        jPanelSenha.setLayout(jPanelSenhaLayout);
        jPanelSenhaLayout.setHorizontalGroup(
            jPanelSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelSenhaFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
        );
        jPanelSenhaLayout.setVerticalGroup(
            jPanelSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSenhaLayout.createSequentialGroup()
                .addComponent(jLabelSenhaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jCheckSenhaColaborador.setBackground(new java.awt.Color(204, 204, 204));
        jCheckSenhaColaborador.setText("Mostrar senha");
        jCheckSenhaColaborador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckSenhaColaboradorActionPerformed(evt);
            }
        });

        jPanelPromover.setBackground(new java.awt.Color(204, 204, 204));

        jLabel61.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel61.setText("Promover o cargo a:");

        jRadioSub.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jRadioSub);
        jRadioSub.setText("Subgerente");

        jBtnPromover.setText("Promover");
        jBtnPromover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPromoverActionPerformed(evt);
            }
        });

        jRadioADM.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jRadioADM);
        jRadioADM.setText("Administrador");

        jRadioFunc.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jRadioFunc);
        jRadioFunc.setText("Funcionário");

        javax.swing.GroupLayout jPanelPromoverLayout = new javax.swing.GroupLayout(jPanelPromover);
        jPanelPromover.setLayout(jPanelPromoverLayout);
        jPanelPromoverLayout.setHorizontalGroup(
            jPanelPromoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPromoverLayout.createSequentialGroup()
                .addComponent(jLabel61)
                .addGap(0, 47, Short.MAX_VALUE))
            .addGroup(jPanelPromoverLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPromoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPromoverLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnPromover))
                    .addGroup(jPanelPromoverLayout.createSequentialGroup()
                        .addGroup(jPanelPromoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioSub)
                            .addComponent(jRadioFunc)
                            .addComponent(jRadioADM))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelPromoverLayout.setVerticalGroup(
            jPanelPromoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPromoverLayout.createSequentialGroup()
                .addComponent(jLabel61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioADM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioFunc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioSub)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jBtnPromover))
        );

        jLabel62.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel62.setText("Vendas-Marcadas");

        jLabelVendasMarcadasInfoFun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVendasMarcadasInfoFun.setText("jLabel63");

        jLabel64.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel64.setText("Vendas-Recebidas");

        jLabelVendasRecInfoFun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVendasRecInfoFun.setText("jLabel65");

        jLabel59.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel59.setText("Senha");

        jButton1.setText("Editar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabelUsuarioFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelNomeFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel55))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanelSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel59))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton1)
                                            .addComponent(jCheckSenhaColaborador))
                                        .addGap(9, 9, 9))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelCargoFuncionario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelVendasMarcadasInfoFun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelVendasRecInfoFun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelPromover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel52)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel55))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel59)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanelSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckSenhaColaborador)))
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelUsuarioFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelCargoFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)))))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel62)
                            .addComponent(jLabel64))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelVendasMarcadasInfoFun, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                            .addComponent(jLabelVendasRecInfoFun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanelPromover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jBtnVoltarTelaFuncionario.setText("Voltar");
        jBtnVoltarTelaFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnVoltarTelaFuncionarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelInfoFuncionarioLayout = new javax.swing.GroupLayout(jPanelInfoFuncionario);
        jPanelInfoFuncionario.setLayout(jPanelInfoFuncionarioLayout);
        jPanelInfoFuncionarioLayout.setHorizontalGroup(
            jPanelInfoFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInfoFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelInfoFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jBtnVoltarTelaFuncionario)
                        .addGroup(jPanelInfoFuncionarioLayout.createSequentialGroup()
                            .addGroup(jPanelInfoFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanelInfoFuncionarioLayout.createSequentialGroup()
                                    .addComponent(jLabel49)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabelNomeFun)))
                            .addGap(53, 53, 53))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanelInfoFuncionarioLayout.setVerticalGroup(
            jPanelInfoFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInfoFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jLabelNomeFun))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnVoltarTelaFuncionario)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel2.add(jPanelInfoFuncionario, "TelaFuncionario");

        jPanelAddCompraCliente.setBackground(new java.awt.Color(153, 153, 153));

        jLabel54.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel54.setText("Adicionando uma compra ao cliente");

        jSeparator10.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator10.setForeground(new java.awt.Color(51, 51, 51));

        jPanel.setBackground(new java.awt.Color(204, 204, 204));

        jLabel56.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel56.setText("Data da Venda");

        try {
            jFormattedTxtDataCompra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##  /  ##  /  ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTxtDataCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel58.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel58.setText("Valor da Compra");

        jLabel68.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel68.setText("Descrição");

        jTextPanelDescriçaoCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextPanelDescriçaoCompraKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(jTextPanelDescriçaoCompra);

        jLabel67.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("R$");

        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-calendário-50.png"))); // NOI18N

        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-dólar-americano-50.png"))); // NOI18N

        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-lista-com-marcadores-50.png"))); // NOI18N

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jLabel67)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTxtValorCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel68, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jFormattedTxtDataCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                .addGap(101, 101, 101)))))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTxtDataCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(19, 19, 19)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTxtValorCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(jLabel68)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        jBtnAdicionarCompras.setText("Adicionar");
        jBtnAdicionarCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAdicionarComprasActionPerformed(evt);
            }
        });

        jBtnCancelarCompra.setText("Voltar");
        jBtnCancelarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarCompraActionPerformed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel60.setText("Nome do Vendedor:");

        jLabelNomeVendedor.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabelNomeVendedor.setText("jLabel67");

        jLabel72.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel72.setText("jLabel72");

        javax.swing.GroupLayout jPanelAddCompraClienteLayout = new javax.swing.GroupLayout(jPanelAddCompraCliente);
        jPanelAddCompraCliente.setLayout(jPanelAddCompraClienteLayout);
        jPanelAddCompraClienteLayout.setHorizontalGroup(
            jPanelAddCompraClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddCompraClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAddCompraClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelAddCompraClienteLayout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAddCompraClienteLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jBtnCancelarCompra)
                .addGap(18, 18, 18)
                .addComponent(jBtnAdicionarCompras)
                .addGap(70, 70, 70))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAddCompraClienteLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanelAddCompraClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelAddCompraClienteLayout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelNomeVendedor)))
                .addGap(43, 43, 43))
        );
        jPanelAddCompraClienteLayout.setVerticalGroup(
            jPanelAddCompraClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddCompraClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAddCompraClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jLabel72))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAddCompraClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(jLabelNomeVendedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelAddCompraClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnCancelarCompra)
                    .addComponent(jBtnAdicionarCompras))
                .addGap(32, 32, 32))
        );

        jPanel2.add(jPanelAddCompraCliente, "TelaAdd");

        jPanelAlterarDadosFunc.setBackground(new java.awt.Color(153, 153, 153));

        jPanel18.setBackground(new java.awt.Color(204, 204, 204));

        jLabel74.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel74.setText("Nome do Funcionário");

        jLabel75.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel75.setText("Usuario");

        jLabel76.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel76.setText("Senha");

        jTxtNomeFunEditar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtNomeFunEditarKeyPressed(evt);
            }
        });

        jTxtNmUsuarioEditar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtNmUsuarioEditarKeyPressed(evt);
            }
        });

        jPassSenhaFuncEditar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPassSenhaFuncEditarKeyPressed(evt);
            }
        });

        jCheckMostrarSenha.setBackground(new java.awt.Color(204, 204, 204));
        jCheckMostrarSenha.setText("Mostrar senha");
        jCheckMostrarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckMostrarSenhaActionPerformed(evt);
            }
        });

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-gerente-50.png"))); // NOI18N

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-usuário-homem-com-círculo-50.png"))); // NOI18N

        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-particular-2-50.png"))); // NOI18N

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckMostrarSenha)
                    .addComponent(jLabel74)
                    .addComponent(jTxtNomeFunEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtNmUsuarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75)
                    .addComponent(jPassSenhaFuncEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel74)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtNomeFunEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel75)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtNmUsuarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel76)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPassSenhaFuncEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jCheckMostrarSenha)
                .addGap(27, 27, 27))
        );

        jLabel73.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel73.setText("Alterando Os Dados Do Funcionário");

        jSeparator11.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator11.setForeground(new java.awt.Color(51, 51, 51));

        jBtnAlterarFun.setText("Alterar");
        jBtnAlterarFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAlterarFunActionPerformed(evt);
            }
        });

        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAlterarDadosFuncLayout = new javax.swing.GroupLayout(jPanelAlterarDadosFunc);
        jPanelAlterarDadosFunc.setLayout(jPanelAlterarDadosFuncLayout);
        jPanelAlterarDadosFuncLayout.setHorizontalGroup(
            jPanelAlterarDadosFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlterarDadosFuncLayout.createSequentialGroup()
                .addGroup(jPanelAlterarDadosFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAlterarDadosFuncLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelAlterarDadosFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelAlterarDadosFuncLayout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jBtnAlterarFun))
                            .addGroup(jPanelAlterarDadosFuncLayout.createSequentialGroup()
                                .addGroup(jPanelAlterarDadosFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel73)
                                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15))))
                    .addGroup(jPanelAlterarDadosFuncLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanelAlterarDadosFuncLayout.setVerticalGroup(
            jPanelAlterarDadosFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlterarDadosFuncLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelAlterarDadosFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnAlterarFun)
                    .addComponent(jButton3))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel2.add(jPanelAlterarDadosFunc, "TelaAlterarFun");

        jPanelDetalhesCompra.setBackground(new java.awt.Color(153, 153, 153));

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));

        jLabel79.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel79.setText("Nome");

        jFormattedTxtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTxtValorActionPerformed(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel80.setText("Valor");

        jLabel82.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel82.setText("R$");

        jLabel83.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel83.setText("Data");

        try {
            jFormattedTxtDtVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("      ##  /   ##   /  ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel84.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel84.setText("ID");

        jPanelRecebimentoCompra.setBackground(new java.awt.Color(204, 204, 204));
        jPanelRecebimentoCompra.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados de Recibo"));

        jLabel86.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel86.setText("Data de Recebimento");

        try {
            jFormattedTxtDtRecibo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("      ##  /   ##   /  ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTxtDtRecibo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTxtDtReciboKeyPressed(evt);
            }
        });

        jPanelValorAreceber.setBackground(new java.awt.Color(204, 204, 204));

        jLabel87.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel87.setText("Valor a Receber");

        jFormattedTxtValorAReceber.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.###"))));
        jFormattedTxtValorAReceber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTxtValorAReceberKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelValorAreceberLayout = new javax.swing.GroupLayout(jPanelValorAreceber);
        jPanelValorAreceber.setLayout(jPanelValorAreceberLayout);
        jPanelValorAreceberLayout.setHorizontalGroup(
            jPanelValorAreceberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelValorAreceberLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelValorAreceberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel87)
                    .addComponent(jFormattedTxtValorAReceber, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanelValorAreceberLayout.setVerticalGroup(
            jPanelValorAreceberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelValorAreceberLayout.createSequentialGroup()
                .addComponent(jLabel87)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTxtValorAReceber, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelRecebimentoCompraLayout = new javax.swing.GroupLayout(jPanelRecebimentoCompra);
        jPanelRecebimentoCompra.setLayout(jPanelRecebimentoCompraLayout);
        jPanelRecebimentoCompraLayout.setHorizontalGroup(
            jPanelRecebimentoCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRecebimentoCompraLayout.createSequentialGroup()
                .addGroup(jPanelRecebimentoCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRecebimentoCompraLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanelRecebimentoCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTxtDtRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel86)))
                    .addGroup(jPanelRecebimentoCompraLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanelValorAreceber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanelRecebimentoCompraLayout.setVerticalGroup(
            jPanelRecebimentoCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRecebimentoCompraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel86)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jFormattedTxtDtRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelValorAreceber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelBtnReceberTudo.setBackground(new java.awt.Color(204, 204, 204));

        jBtnReceberTudo.setText("Receber");
        jBtnReceberTudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnReceberTudoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBtnReceberTudoLayout = new javax.swing.GroupLayout(jPanelBtnReceberTudo);
        jPanelBtnReceberTudo.setLayout(jPanelBtnReceberTudoLayout);
        jPanelBtnReceberTudoLayout.setHorizontalGroup(
            jPanelBtnReceberTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBtnReceberTudoLayout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(jBtnReceberTudo))
        );
        jPanelBtnReceberTudoLayout.setVerticalGroup(
            jPanelBtnReceberTudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBtnReceberTudoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnReceberTudo)
                .addContainerGap())
        );

        jPanelFazerAcordo.setBackground(new java.awt.Color(204, 204, 204));

        jBtnFazerAcordo.setText("Fazer Acordo");
        jBtnFazerAcordo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnFazerAcordoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFazerAcordoLayout = new javax.swing.GroupLayout(jPanelFazerAcordo);
        jPanelFazerAcordo.setLayout(jPanelFazerAcordoLayout);
        jPanelFazerAcordoLayout.setHorizontalGroup(
            jPanelFazerAcordoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFazerAcordoLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jBtnFazerAcordo)
                .addContainerGap())
        );
        jPanelFazerAcordoLayout.setVerticalGroup(
            jPanelFazerAcordoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFazerAcordoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnFazerAcordo)
                .addContainerGap())
        );

        jPanelMetodoPg.setBackground(new java.awt.Color(204, 204, 204));

        jLabel85.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel85.setText("Método de Pagamento");

        jRadioAVista.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(jRadioAVista);
        jRadioAVista.setText("À vista");
        jRadioAVista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioAVistaActionPerformed(evt);
            }
        });

        jRadioAcordo.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(jRadioAcordo);
        jRadioAcordo.setText("Acordo");
        jRadioAcordo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioAcordoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMetodoPgLayout = new javax.swing.GroupLayout(jPanelMetodoPg);
        jPanelMetodoPg.setLayout(jPanelMetodoPgLayout);
        jPanelMetodoPgLayout.setHorizontalGroup(
            jPanelMetodoPgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMetodoPgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMetodoPgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioAcordo)
                    .addComponent(jRadioAVista)
                    .addComponent(jLabel85))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMetodoPgLayout.setVerticalGroup(
            jPanelMetodoPgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMetodoPgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel85)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioAVista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioAcordo)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanelDescricao.setBackground(new java.awt.Color(204, 204, 204));

        jTtxtDescricaoDetalhes.setColumns(20);
        jTtxtDescricaoDetalhes.setRows(5);
        jScrollPane5.setViewportView(jTtxtDescricaoDetalhes);

        jLabel81.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel81.setText("Descrição");

        javax.swing.GroupLayout jPanelDescricaoLayout = new javax.swing.GroupLayout(jPanelDescricao);
        jPanelDescricao.setLayout(jPanelDescricaoLayout);
        jPanelDescricaoLayout.setHorizontalGroup(
            jPanelDescricaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDescricaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDescricaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel81)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDescricaoLayout.setVerticalGroup(
            jPanelDescricaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDescricaoLayout.createSequentialGroup()
                .addComponent(jLabel81)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTxtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel79)
                            .addComponent(jTxtNomeDetalhesCompra)
                            .addComponent(jLabel80))
                        .addGap(18, 18, 18)
                        .addComponent(jPanelDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel83)
                                    .addComponent(jFormattedTxtDtVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 26, Short.MAX_VALUE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTxtIDCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel84))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                .addComponent(jPanelMetodoPg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jPanelRecebimentoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanelFazerAcordo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanelBtnReceberTudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel79)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtNomeDetalhesCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTxtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanelMetodoPg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelBtnReceberTudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelFazerAcordo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel83, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel84))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTxtDtVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(jTxtIDCompra))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelRecebimentoCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLabel77.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel77.setText("Compras de ");

        jLabelNomeCliente.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelNomeCliente.setText("jLabel78");

        jSeparator12.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator12.setForeground(new java.awt.Color(51, 51, 51));

        jBtnVoltarComprasDetalhes.setText("Voltar");
        jBtnVoltarComprasDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnVoltarComprasDetalhesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDetalhesCompraLayout = new javax.swing.GroupLayout(jPanelDetalhesCompra);
        jPanelDetalhesCompra.setLayout(jPanelDetalhesCompraLayout);
        jPanelDetalhesCompraLayout.setHorizontalGroup(
            jPanelDetalhesCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalhesCompraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDetalhesCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelDetalhesCompraLayout.createSequentialGroup()
                        .addComponent(jLabel77)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnVoltarComprasDetalhes, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanelDetalhesCompraLayout.setVerticalGroup(
            jPanelDetalhesCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalhesCompraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetalhesCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(jLabelNomeCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnVoltarComprasDetalhes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanelDetalhesCompra, "DetalhesCompra");

        jPanelRelatorios.setBackground(new java.awt.Color(153, 153, 153));

        jLabel102.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel102.setText("Relatórios de");

        jLabelTituloRelatorio.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelTituloRelatorio.setText("jLabel103");

        jSeparator13.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator13.setForeground(new java.awt.Color(51, 51, 51));

        jPanel19.setBackground(new java.awt.Color(204, 204, 204));

        jTableRelatorioVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Cliente", "Telefone", "Dt_Venda", "Valor", "Descrição", "Situação", "Vendedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableRelatorioVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableRelatorioVendasMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableRelatorioVendas);

        javax.swing.GroupLayout jPanelTableVendasLayout = new javax.swing.GroupLayout(jPanelTableVendas);
        jPanelTableVendas.setLayout(jPanelTableVendasLayout);
        jPanelTableVendasLayout.setHorizontalGroup(
            jPanelTableVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableVendasLayout.createSequentialGroup()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        jPanelTableVendasLayout.setVerticalGroup(
            jPanelTableVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTableVendasLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTableRelatorioPagamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Cliente", "Telefone", "Dt_Venda", "Valor_Venda", "Descrição", "Valor_Pg", "Tipo_Pg", "Dt_Pg", "Vendedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableRelatorioPagamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableRelatorioPagamentoMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTableRelatorioPagamento);
        if (jTableRelatorioPagamento.getColumnModel().getColumnCount() > 0) {
            jTableRelatorioPagamento.getColumnModel().getColumn(3).setPreferredWidth(70);
        }

        javax.swing.GroupLayout jPanelTablePgLayout = new javax.swing.GroupLayout(jPanelTablePg);
        jPanelTablePg.setLayout(jPanelTablePgLayout);
        jPanelTablePgLayout.setHorizontalGroup(
            jPanelTablePgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablePgLayout.createSequentialGroup()
                .addComponent(jScrollPane7)
                .addContainerGap())
        );
        jPanelTablePgLayout.setVerticalGroup(
            jPanelTablePgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTablePgLayout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanelRelatorioInfo.setBackground(new java.awt.Color(204, 204, 204));

        jLabel114.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel114.setText("Nome do Cliente");

        jLabel115.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel115.setText("Telefone do Cliente");

        jLabel116.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel116.setText("Dados do Cliente");

        jTextNomeClienteRel.setText("jTextField2");

        jTextTelefoneRel.setText("jTextField3");

        jLabel117.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel117.setText("Dados da Compra");

        jLabel118.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel118.setText("Data da Compra");

        jTextDtCompra.setText("jTextField4");

        jLabel119.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel119.setText("Valor da Compra");

        jSeparator16.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator16.setForeground(new java.awt.Color(51, 51, 51));

        jTextValorCompraRel.setText("jTextField5");

        jPanelPg.setBackground(new java.awt.Color(204, 204, 204));

        jLabel120.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel120.setText("Dados do Pagamento");

        jLabel121.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel121.setText("Data do Pagamento");

        jTextDtPgRel.setText("jTextField6");

        jLabel122.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel122.setText("Valor Pago");

        jTextValorPgRel.setText("jTextField7");

        jSeparator17.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator17.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanelPgLayout = new javax.swing.GroupLayout(jPanelPg);
        jPanelPg.setLayout(jPanelPgLayout);
        jPanelPgLayout.setHorizontalGroup(
            jPanelPgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPgLayout.createSequentialGroup()
                .addGroup(jPanelPgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel120)
                    .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelPgLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelPgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel122)
                            .addComponent(jTextDtPgRel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel121)
                            .addComponent(jTextValorPgRel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelPgLayout.setVerticalGroup(
            jPanelPgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPgLayout.createSequentialGroup()
                .addComponent(jLabel120)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel121)
                .addGap(9, 9, 9)
                .addComponent(jTextDtPgRel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel122)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextValorPgRel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel123.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel123.setText("Descrição");

        jScrollPane9.setViewportView(jTextDescRel);

        javax.swing.GroupLayout jPanelRelatorioInfoLayout = new javax.swing.GroupLayout(jPanelRelatorioInfo);
        jPanelRelatorioInfo.setLayout(jPanelRelatorioInfoLayout);
        jPanelRelatorioInfoLayout.setHorizontalGroup(
            jPanelRelatorioInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRelatorioInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRelatorioInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRelatorioInfoLayout.createSequentialGroup()
                        .addGroup(jPanelRelatorioInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel114)
                            .addComponent(jLabel115)
                            .addComponent(jLabel116)
                            .addComponent(jTextNomeClienteRel, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(jTextTelefoneRel))
                        .addGroup(jPanelRelatorioInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelRelatorioInfoLayout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel117))
                            .addGroup(jPanelRelatorioInfoLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanelRelatorioInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel118)
                                    .addGroup(jPanelRelatorioInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextValorCompraRel, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextDtCompra, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel119, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelRelatorioInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel123)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanelPg, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanelRelatorioInfoLayout.setVerticalGroup(
            jPanelRelatorioInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRelatorioInfoLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanelRelatorioInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRelatorioInfoLayout.createSequentialGroup()
                        .addGroup(jPanelRelatorioInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel116)
                            .addComponent(jLabel117))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addGroup(jPanelRelatorioInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel114)
                            .addComponent(jLabel118)
                            .addComponent(jLabel123))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelRelatorioInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelRelatorioInfoLayout.createSequentialGroup()
                                .addGroup(jPanelRelatorioInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextNomeClienteRel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextDtCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanelRelatorioInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel115)
                                    .addComponent(jLabel119))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelRelatorioInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextTelefoneRel)
                                    .addComponent(jTextValorCompraRel)))
                            .addComponent(jScrollPane9)))
                    .addComponent(jPanelPg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jBtnVoltarRelatorio1.setText("Voltar");
        jBtnVoltarRelatorio1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnVoltarRelatorio1MouseClicked(evt);
            }
        });
        jBtnVoltarRelatorio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnVoltarRelatorio1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelRelatorioTablesLayout = new javax.swing.GroupLayout(jPanelRelatorioTables);
        jPanelRelatorioTables.setLayout(jPanelRelatorioTablesLayout);
        jPanelRelatorioTablesLayout.setHorizontalGroup(
            jPanelRelatorioTablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTableVendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelTablePg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelRelatorioInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRelatorioTablesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnVoltarRelatorio1)
                .addGap(56, 56, 56))
        );
        jPanelRelatorioTablesLayout.setVerticalGroup(
            jPanelRelatorioTablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRelatorioTablesLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanelTableVendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelTablePg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelRelatorioInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jBtnVoltarRelatorio1)
                .addContainerGap())
        );

        jRadioBtnNmCliente.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup3.add(jRadioBtnNmCliente);
        jRadioBtnNmCliente.setText("Nome Cliente");
        jRadioBtnNmCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioBtnNmClienteActionPerformed(evt);
            }
        });

        jLabel104.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel104.setText("Filtrar por:");

        jPanelCampoNome.setBackground(new java.awt.Color(204, 204, 204));

        jTextNomePesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextNomePesquisaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCampoNomeLayout = new javax.swing.GroupLayout(jPanelCampoNome);
        jPanelCampoNome.setLayout(jPanelCampoNomeLayout);
        jPanelCampoNomeLayout.setHorizontalGroup(
            jPanelCampoNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCampoNomeLayout.createSequentialGroup()
                .addComponent(jTextNomePesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCampoNomeLayout.setVerticalGroup(
            jPanelCampoNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextNomePesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        jPanelCampoData.setBackground(new java.awt.Color(204, 204, 204));

        try {
            jFormattedTxtDt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##   /    ##    /   ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTxtDt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTxtDtKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCampoDataLayout = new javax.swing.GroupLayout(jPanelCampoData);
        jPanelCampoData.setLayout(jPanelCampoDataLayout);
        jPanelCampoDataLayout.setHorizontalGroup(
            jPanelCampoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCampoDataLayout.createSequentialGroup()
                .addComponent(jFormattedTxtDt, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCampoDataLayout.setVerticalGroup(
            jPanelCampoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFormattedTxtDt, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        jPanelBtnPesquisar.setBackground(new java.awt.Color(204, 204, 204));

        jBtnPesquisar.setText("Pesquisar");
        jBtnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBtnPesquisarLayout = new javax.swing.GroupLayout(jPanelBtnPesquisar);
        jPanelBtnPesquisar.setLayout(jPanelBtnPesquisarLayout);
        jPanelBtnPesquisarLayout.setHorizontalGroup(
            jPanelBtnPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBtnPesquisarLayout.createSequentialGroup()
                .addComponent(jBtnPesquisar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelBtnPesquisarLayout.setVerticalGroup(
            jPanelBtnPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBtnPesquisarLayout.createSequentialGroup()
                .addComponent(jBtnPesquisar)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanelComprasPendentes.setBackground(new java.awt.Color(204, 204, 204));

        jRadioBtnComprasPendentes.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup3.add(jRadioBtnComprasPendentes);
        jRadioBtnComprasPendentes.setText("Compras Pendentes");
        jRadioBtnComprasPendentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioBtnComprasPendentesActionPerformed(evt);
            }
        });

        jRadioBtnDtVenda.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup3.add(jRadioBtnDtVenda);
        jRadioBtnDtVenda.setText("Data Venda");
        jRadioBtnDtVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioBtnDtVendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelComprasPendentesLayout = new javax.swing.GroupLayout(jPanelComprasPendentes);
        jPanelComprasPendentes.setLayout(jPanelComprasPendentesLayout);
        jPanelComprasPendentesLayout.setHorizontalGroup(
            jPanelComprasPendentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelComprasPendentesLayout.createSequentialGroup()
                .addComponent(jRadioBtnComprasPendentes)
                .addGap(10, 10, 10)
                .addComponent(jRadioBtnDtVenda))
        );
        jPanelComprasPendentesLayout.setVerticalGroup(
            jPanelComprasPendentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelComprasPendentesLayout.createSequentialGroup()
                .addGroup(jPanelComprasPendentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioBtnComprasPendentes)
                    .addComponent(jRadioBtnDtVenda))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanelDtPg.setBackground(new java.awt.Color(204, 204, 204));

        jRadioBtnDtPg.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup3.add(jRadioBtnDtPg);
        jRadioBtnDtPg.setText("Data Pagamento");
        jRadioBtnDtPg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioBtnDtPgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDtPgLayout = new javax.swing.GroupLayout(jPanelDtPg);
        jPanelDtPg.setLayout(jPanelDtPgLayout);
        jPanelDtPgLayout.setHorizontalGroup(
            jPanelDtPgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDtPgLayout.createSequentialGroup()
                .addComponent(jRadioBtnDtPg)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanelDtPgLayout.setVerticalGroup(
            jPanelDtPgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDtPgLayout.createSequentialGroup()
                .addComponent(jRadioBtnDtPg)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelRelatorioTables, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel104)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioBtnNmCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanelComprasPendentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jPanelCampoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanelCampoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanelBtnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelDtPg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioBtnNmCliente)
                            .addComponent(jLabel104))
                        .addGap(7, 7, 7))
                    .addComponent(jPanelDtPg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelComprasPendentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelCampoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelCampoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelBtnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jPanelRelatorioTables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanelRelatoriosLayout = new javax.swing.GroupLayout(jPanelRelatorios);
        jPanelRelatorios.setLayout(jPanelRelatoriosLayout);
        jPanelRelatoriosLayout.setHorizontalGroup(
            jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 528, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                        .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                                .addComponent(jLabel102)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelTituloRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 88, Short.MAX_VALUE))))
        );
        jPanelRelatoriosLayout.setVerticalGroup(
            jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRelatoriosLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanelRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel102)
                    .addComponent(jLabelTituloRelatorio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(276, 276, 276))
        );

        jPanel2.add(jPanelRelatorios, "TelaRelatórios");

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 540, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConsultaActionPerformed
        // TODO add your handling code here:
        listarTabelaCliente();
    }//GEN-LAST:event_jBtnConsultaActionPerformed

    private void jBtnNvClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnNvClienteMouseClicked
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaCadastro");
    }//GEN-LAST:event_jBtnNvClienteMouseClicked

    private void jBtnConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnConsultaMouseClicked
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaConsulta");
    }//GEN-LAST:event_jBtnConsultaMouseClicked

    private void jBtnVoltaConsultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnVoltaConsultarMouseClicked
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaHome");
    }//GEN-LAST:event_jBtnVoltaConsultarMouseClicked

    private void jBtnVoltarCadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnVoltarCadMouseClicked
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaHome");
    }//GEN-LAST:event_jBtnVoltarCadMouseClicked

    private void jBtnAlterarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnAlterarMouseClicked
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaAlterar");
    }//GEN-LAST:event_jBtnAlterarMouseClicked

    private void jBtnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnApagarActionPerformed
        // TODO add your handling code here:

        try{
            UsuarioDAO usDAO = new UsuarioDAO();
            String nome = (String) jTableFuncionario.getModel().getValueAt(jTableFuncionario.getSelectedRow(), 0);
            Vendedor vendedor = new Vendedor();
            vendedor.setNome(nome);
            usDAO.excluirUsuario(vendedor);
            JOptionPane.showMessageDialog(this, "Funcionário excluido com sucesso!", "Aviso!", 1);
            listarTabelaFuncionario();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Impossível excluir um funcionário não existente","Alerta!",1);
            
        }
    }//GEN-LAST:event_jBtnApagarActionPerformed

    private void jBtnCadastroGerenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCadastroGerenciaActionPerformed
        // TODO add your handling code here:
        if(jTxtNomeFuncCad.getText().equals("") || jTxtUserFunCad.getText().equals("") || jPassFunCad.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Preencha todos os campos antes de prosseguir!", "Alerta!",1);
        }else{
            String nm_vendedor = jTxtNomeFuncCad.getText();
            String nm_usuario = jTxtUserFunCad.getText();
            String senha = String.valueOf(jPassFunCad.getPassword());
            String nome = jLabelNomeUsuario.getText();
            String usuario = jLabelUsuarioPerfil.getText();
            String acao = "finalizar o cadastro de um novo vendedor.";
            Senha s = new Senha();
            s.setLabel(acao, nome, usuario);
            s.getDadosVendedor(nm_vendedor, nm_usuario, senha);
            s.show();
            s.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_jBtnCadastroGerenciaActionPerformed

    private void jBtnGerenciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnGerenciarMouseClicked
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaGerenciamento");
    }//GEN-LAST:event_jBtnGerenciarMouseClicked

    private void jBtnVoltarGerenciadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnVoltarGerenciadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnVoltarGerenciadorActionPerformed

    private void jBtnVoltarGerenciadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnVoltarGerenciadorMouseClicked
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaHome");
    }//GEN-LAST:event_jBtnVoltarGerenciadorMouseClicked

    private void jBtnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLogoutActionPerformed
        // TODO add your handling code here:
        Login lg = new Login();
        lg.show();
        lg.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jBtnLogoutActionPerformed

    private void jCheckSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckSenhaActionPerformed
        // TODO add your handling code here:
        if(jCheckSenha.isSelected()){
            jPanelLabelSenha.setVisible(true);
        }else{
            jPanelLabelSenha.setVisible(false);
            
        }
        
    }//GEN-LAST:event_jCheckSenhaActionPerformed

    private void jBtnVoltarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnVoltarPerfilActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaHome");
    }//GEN-LAST:event_jBtnVoltarPerfilActionPerformed

    private void jBtnMeuPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnMeuPerfilActionPerformed
        // TODO add your handling code here:
        listarTabelaPagamentos();
        listaTabelaCompras();
        jLabel106.setText(String.valueOf(countPgRealizados(jLabelNomePerfil.getText())));
        jLabelQntMarcados.setText(String.valueOf(countMarcados(jLabelNomePerfil.getText())));
        jLabelQntMarcadosRec.setText(String.valueOf(countMarcadosPg(jLabelNomePerfil.getText())));
        if(jLabelCargoPerfil.getText().equals("Funcionário")){
            jPanelBtnRelatorios.setVisible(false);
        }else{
            jPanelBtnRelatorios.setVisible(true);
        }
        
        
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaPerfil");
        
            
    }//GEN-LAST:event_jBtnMeuPerfilActionPerformed

    private void jBtnCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCadastrarClienteActionPerformed
        // TODO add your handling code here:
        if(jTxtNomeCadCliente.getText().equals("") || jFormattTelCadCliente.getText().equals("") ||
                jTxtEnderecoCadCliente.getText().equals("") || jFormattedValorCadCliente.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Preencha os campos acima antes de prosseguir!", "Alerta!",1);
        
    }else{
            String nome = jLabelNomeUsuario.getText();
            String usuario = jLabelUsuarioPerfil.getText();
            String acao = "finalizar o cadastro de um novo cliente.";
            String nm_cliente = jTxtNomeCadCliente.getText();
            String telefone_cliente = jFormattTelCadCliente.getText();
            String endereco_cliente = jTxtEnderecoCadCliente.getText();
     
            double limiteCliente = setValorDouble(jFormattedValorCadCliente.getText());
            Senha s = new Senha();
            s.setLabel(acao, nome, usuario);
            s.getDadosClientes(nm_cliente, endereco_cliente, telefone_cliente, limiteCliente, nome);
            s.show();
            s.setLocationRelativeTo(null);
            
            
    }
    }//GEN-LAST:event_jBtnCadastrarClienteActionPerformed

    private void jBtnLimparCadClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimparCadClienteActionPerformed
        // TODO add your handling code here:
        jTxtNomeCadCliente.setText(null);
        jFormattTelCadCliente.setText(null);
        jTxtEnderecoCadCliente.setText(null);
        jFormattedValorCadCliente.setText("00");
        
    }//GEN-LAST:event_jBtnLimparCadClienteActionPerformed

    private void jFormattedValorCadClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedValorCadClienteKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(jTxtNomeCadCliente.getText().equals("") || jFormattTelCadCliente.getText().equals("") ||
                jTxtEnderecoCadCliente.getText().equals("") || jFormattedValorCadCliente.getText().equals("")){
                
                JOptionPane.showMessageDialog(this, "Preencha os campos acima antes de prosseguir!", "Alerta!",1);
        
            }else{
                String nome = jLabelNomeUsuario.getText();
                String usuario = jLabelUsuarioPerfil.getText();
                String acao = "finalizar o cadastro de um novo cliente.";
                String nm_cliente = jTxtNomeCadCliente.getText();
                String telefone_cliente = jFormattTelCadCliente.getText();
                String endereco_cliente = jTxtEnderecoCadCliente.getText();
            
                String numero = jFormattedValorCadCliente.getText();
                String numeroA = numero.replace(".", "");
                String numeroB = numeroA.replace(",", ".");
                double limite_cliente = Double.parseDouble(numeroB);
                Senha s = new Senha();
                s.setLabel(acao, nome, usuario);
                s.getDadosClientes(nm_cliente, endereco_cliente, telefone_cliente, limite_cliente, nome);
                s.show();
                s.setLocationRelativeTo(null);
            
            
    }
            
        }
        
    }//GEN-LAST:event_jFormattedValorCadClienteKeyPressed

    private void jTableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClientesMouseClicked
        // TODO add your handling code here:
        if(jTableClientes.getRowCount() > 0){
            jPanelBtnAcaoCliente.setVisible(true);
        }
        
        ClienteDAO clDAO = new ClienteDAO();
        String nome = (String) jTableClientes.getModel().getValueAt(jTableClientes.getSelectedRow(), 0);
        String telefone = (String) jTableClientes.getModel().getValueAt(jTableClientes.getSelectedRow(), 1) ;
        List<Clientes> cliente = clDAO.getLimite(nome, telefone);
        int i = 0;
        double valor = cliente.get(i).getLimite();
                
        jLabelSaldoDisp.setText("R$ "+String.valueOf(valor));
        
    }//GEN-LAST:event_jTableClientesMouseClicked

    private void jBtnVerDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnVerDetalhesActionPerformed
        // TODO add your handling code here:
        if(jLabelStatusCliente.getText().equals("AUSENTE")){
            jPanelExcluirCliente.setVisible(false);
        }else{
            jPanelExcluirCliente.setVisible(true);
        }
        String nome = (String) jTableClientes.getModel().getValueAt(jTableClientes.getSelectedRow(), 0);
        String telefone = (String) jTableClientes.getModel().getValueAt(jTableClientes.getSelectedRow(), 1) ;
        
        listarTabelaCompra(nome, telefone);
        
        ClienteDAO clDAO = new ClienteDAO();
        Clientes cliente = new Clientes();
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        
        List<Clientes> cl = clDAO.dadosClientes(cliente);
        
        int j = 0;
        jLabelNomeDetalhes.setText(cl.get(j).getNome());
        jLabelTelefoneDetalhesCliente.setText(cl.get(j).getTelefone());
        jLabelEndereçoDetalhesCliente.setText(cl.get(j).getEndereco());
        jLabelLimiteDisponivelDetalhesClientes.setText(String.valueOf(cl.get(j).getLimite()));
        jLabelStatusCliente.setText(cl.get(j).getSituacao());             
        jLabelValorTotal.setText(somaTable());
        
        if(jLabelStatusCliente.getText().equals("AUSENTE")){
            jPanelExcluirCliente.setVisible(false);
        }else{
            jPanelExcluirCliente.setVisible(true);
        }
        
    }//GEN-LAST:event_jBtnVerDetalhesActionPerformed

    private void jBtnVerDetalhesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnVerDetalhesMouseClicked
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaDetalhes");
    }//GEN-LAST:event_jBtnVerDetalhesMouseClicked

    private void jBtnVoltarDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnVoltarDetalhesActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaConsulta");
        jTableClientes.clearSelection();
        jPanelBtnAcaoCliente.setVisible(false);
        jLabelSaldoDisp.setText("");
    }//GEN-LAST:event_jBtnVoltarDetalhesActionPerformed

    private void jBtnPesquisarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesquisarConsultaActionPerformed
        // TODO add your handling code here:
        ClienteDAO clDAO = new ClienteDAO();
        List<Clientes> clientes = null;
        clientes = clDAO.pesquisaCliente(jTxtFiltro.getText());
        
        DefaultTableModel valor = (DefaultTableModel) jTableClientes.getModel();
        valor.getDataVector().removeAllElements();
        
        int i = 0;
        
        while(clientes.size()>i){
            valor.addRow(new Object[]{clientes.get(i).getNome(), clientes.get(i).getTelefone(), clientes.get(i).getEndereco(), clientes.get(i).getSituacao(), clientes.get(i).getNm_vendedor()});
            i++;
        }
        
    }//GEN-LAST:event_jBtnPesquisarConsultaActionPerformed

    private void jBtnVerDetalhesFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnVerDetalhesFuncionarioActionPerformed
        // TODO add your handling code here:

        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaFuncionario");
        String nome = (String)jTableFuncionario.getModel().getValueAt(jTableFuncionario.getSelectedRow(), 0);
        UsuarioDAO usDAO = new UsuarioDAO();
        Vendedor vendedores = new Vendedor();
        vendedores.setNome(nome);
        List<Vendedor> vendedor = usDAO.dadosUsuario(vendedores);
        int i = 0;
            
        String nmVendedor = vendedor.get(i).getNome();
        String cargoVendedor = vendedor.get(i).getCargo();
        jLabelNomeFun.setText(nmVendedor);
        jLabelNomeFuncionario.setText(nmVendedor);
        jLabelUsuarioFuncionario.setText(vendedor.get(i).getUsuario());
        jLabelCargoFuncionario.setText(cargoVendedor);
        jLabelSenhaFuncionario.setText(vendedor.get(i).getSenha());
        
        if(jLabelCargoPerfil.getText().equals("Administrador") || nmVendedor.equals(jLabelNomePerfil.getText())){
            jPanelPromover.setVisible(false);
        }else{
            jPanelPromover.setVisible(true);
        }
            
            
            
        jLabelVendasMarcadasInfoFun.setText(String.valueOf(countMarcados((String) jTableFuncionario.getModel().getValueAt(jTableFuncionario.getSelectedRow(), 0))));
        jLabelVendasRecInfoFun.setText(String.valueOf(countMarcadosPg((String) jTableFuncionario.getModel().getValueAt(jTableFuncionario.getSelectedRow(), 0))));
    }//GEN-LAST:event_jBtnVerDetalhesFuncionarioActionPerformed

    private void jCheckSenhaColaboradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckSenhaColaboradorActionPerformed
        // TODO add your handling code here:
        if(jCheckSenhaColaborador.isSelected()){
            jPanelSenha.setVisible(true);
            
        }else{
            jPanelSenha.setVisible(false);
        }
    }//GEN-LAST:event_jCheckSenhaColaboradorActionPerformed

    private void jBtnVoltarTelaFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnVoltarTelaFuncionarioActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaHome");
        jPanelBtns.setVisible(false);
        jTableFuncionario.clearSelection();
        buttonGroup1.clearSelection();
        
    }//GEN-LAST:event_jBtnVoltarTelaFuncionarioActionPerformed

    private void jBtnVerDetalhesFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnVerDetalhesFuncionarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnVerDetalhesFuncionarioMouseClicked

    private void jBtnPromoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPromoverActionPerformed
        // TODO add your handling code here:
        String cargo = "";
        if(jRadioADM.isSelected()){
            cargo = "Gerente";
        }else if(jRadioFunc.isSelected()){
            cargo = "Funcionário";
        }else if(jRadioSub.isSelected()){
            cargo = "Subgerente";
        }
        
        if(jLabelCargoFuncionario.getText().equals(cargo)){
            JOptionPane.showMessageDialog(this, "O funcionário ja possui esse tipo de cargo, tente um diferente!", "Alerta!",1);
            
        }else{
            String nome = jLabelNomePerfil.getText();
            String nm_usuario = jLabelUsuarioPerfil.getText();
            String nome_func = jLabelNomeFuncionario.getText() ;
            String nm_cargo = cargo;
            String acao = "promover o funcionário.";
            
            Senha s = new Senha();
            s.setLabel(acao, nome, nm_usuario);
            s.getInfoFuncionario(nome_func, nm_cargo);
            s.show();
            s.setLocationRelativeTo(null);
            
    
        }
        
            
        
        
        
    }//GEN-LAST:event_jBtnPromoverActionPerformed

    private void jBtnLimpaCamposGerenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimpaCamposGerenActionPerformed
        // TODO add your handling code here:
        jTxtNomeFuncCad.setText("");
        jTxtUserFunCad.setText("");
        jPassFunCad.setText("");
        
        
        
    }//GEN-LAST:event_jBtnLimpaCamposGerenActionPerformed

    private void jBtnGerenciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGerenciarActionPerformed
        // TODO add your handling code here:
        listarTabelaFuncionario();
    }//GEN-LAST:event_jBtnGerenciarActionPerformed

    private void jTableFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFuncionarioMouseClicked
        // TODO add your handling code here:String nomePerfil = jLabelNomePerfil.getText();
        if(jTableFuncionario.getSelectedRowCount()> 0){
            jPanelBtns.setVisible(true);            
        }
        
            
        String nomePerfil = jLabelNomePerfil.getText();
        String nomeTabela = (String) jTableFuncionario.getModel().getValueAt(jTableFuncionario.getSelectedRow(), 0);
        if(nomePerfil.equals(nomeTabela) || jLabelCargoPerfil.getText().equals("Administrador")){
            jPanelPromover.setVisible(false);
            jPanelBtnApagar.setVisible(false);
        }else{
            jPanelPromover.setVisible(true);
            jPanelBtnApagar.setVisible(true);
        }
        
        
    }//GEN-LAST:event_jTableFuncionarioMouseClicked

    private void jBtnMarcarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnMarcarVendaActionPerformed
        // TODO add your handling code here:
        double valor = Double.parseDouble(jLabelLimiteDisponivelDetalhesClientes.getText());
        String situaçao = jLabelStatusCliente.getText();
        if(valor > 0){
            if(situaçao.equals("Ativo")){
                jLabel72.setText(jLabelNomeDetalhes.getText());
                jLabelNomeVendedor.setText(jLabelNomePerfil.getText());
                CardLayout cl = (CardLayout) jPanel2.getLayout();
                cl.show(jPanel2, "TelaAdd");
            }else{
                JOptionPane.showMessageDialog(this, "Impossível Adicionar Uma Compra Para Esse Cliente!","Alerta!",1);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Saldo Insuficiente Para Realizar Novas Compras!","Alerta!",1);
        }
    }//GEN-LAST:event_jBtnMarcarVendaActionPerformed

    private void jBtnCancelarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarCompraActionPerformed
        // TODO add your handling code here:
        jPanelBtnAcaoCliente.setVisible(false);
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaDetalhes");
        jLabelSaldoDisp.setText("");
        jFormattedTxtDataCompra.setText("");
        jFormattedTxtValorCompra.setText("");
        jTextPanelDescriçaoCompra.setText("");
        jTableClientes.clearSelection();
        listarTabelaCompra(jLabelNomeDetalhes.getText(), jLabelTelefoneDetalhesCliente.getText());
        jLabelValorTotal.setText(somaTable());
        jTableComprasCliente.clearSelection();
       
    }//GEN-LAST:event_jBtnCancelarCompraActionPerformed

    private void jBtnAdicionarComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAdicionarComprasActionPerformed
        // TODO add your handling code here:
        if(jFormattedTxtDataCompra.getText().equals("") || jFormattedTxtValorCompra.getText().equals(null) || jTextPanelDescriçaoCompra.getText().equals("")){
            
            JOptionPane.showMessageDialog(this, "Preencha os campos acima antes de prosseguir!","Alerta!",1);
            
        }else{
            double valorCompra = setValorDouble(jFormattedTxtValorCompra.getText());  // ALTERAÇÃO FULL
            double valor_limite = Double.parseDouble(jLabelLimiteDisponivelDetalhesClientes.getText());
            double nvLimite = valor_limite - valorCompra;
            
            String nv_limite = arredondaValores(nvLimite);  // ALTERAÇÃO FULL
            double limiteAtual = setValorDouble(nv_limite);
         
            
            
            if(valorCompra <= valor_limite){
                Senha s = new Senha();
                String acao = "finalizar a compra do cliente";
                String nome_user = jLabelNomeUsuario.getText();
                String usuario = jLabelUsuarioPerfil.getText();
                s.setLabel(acao, nome_user, usuario);
                String dt_compra = setDt(jFormattedTxtDataCompra.getText());  // ALTERAÇÃO FULL
                String descrição = jTextPanelDescriçaoCompra.getText();
                String nome_cliente = jLabelNomeDetalhes.getText();
                String telefone_cliente = jLabelTelefoneDetalhesCliente.getText();  
                jLabelLimiteDisponivelDetalhesClientes.setText(String.valueOf(limiteAtual));
                s.getDadosCompra(nome_cliente, telefone_cliente, dt_compra, valorCompra, descrição, nome_user, limiteAtual);
                s.show();
                s.setLocationRelativeTo(null);
            }else{
                JOptionPane.showMessageDialog(this, "Saldo Insuficiente Para Efetuar Essa Compra!","Alerta!",1);
            }
        }
    }//GEN-LAST:event_jBtnAdicionarComprasActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaFuncionario");
        jTxtNomeFunEditar.setText("");
        jTxtNmUsuarioEditar.setText("");
        jPassSenhaFuncEditar.setText("");
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaAlterarFun");
        jTxtNomeFunEditar.setText(jLabelNomeFuncionario.getText());
        jTxtNomeFunEditar.setEditable(false);
        jTxtNmUsuarioEditar.setText(jLabelUsuarioFuncionario.getText());
        jPassSenhaFuncEditar.setText(jLabelSenhaFuncionario.getText());
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckMostrarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckMostrarSenhaActionPerformed
        // TODO add your handling code here:
        if(jCheckMostrarSenha.isSelected()){
            jPassSenhaFuncEditar.setEchoChar((char)0);
        }else{
            jPassSenhaFuncEditar.setEchoChar('*');
        }
            
    }//GEN-LAST:event_jCheckMostrarSenhaActionPerformed

    private void jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAlterarActionPerformed
        // TODO add your handling code here:
        String saldo = jLabelSaldoDisp.getText();
        String saldoA = saldo.replace(".", ",");
        
        String situaçao = (String) jTableClientes.getModel().getValueAt(jTableClientes.getSelectedRow(), 3);
        String nome = (String) jTableClientes.getModel().getValueAt(jTableClientes.getSelectedRow(), 0);
        String telefone = (String) jTableClientes.getModel().getValueAt(jTableClientes.getSelectedRow(), 1);
        String endereco = (String) jTableClientes.getModel().getValueAt(jTableClientes.getSelectedRow(), 2);
        jTxtNomeAlterar.setText(nome);
        jFormattedTxtTelefone.setText(telefone);
        jTxtEnderecoAlterarClientes.setText(endereco);
        jFormattedAlterarDados.setText(saldoA);
        
        if(situaçao.equals("Ativo")){
            jPanelMostrarSituação.setVisible(false);
            jTxtNomeAlterar.setEditable(true);
            jFormattedTxtTelefone.setEditable(true);
            jTxtEnderecoAlterarClientes.setEditable(true);
            jFormattedAlterarDados.setEditable(true);
            jPanelBtnSalvar.setVisible(true);
        }else{
            jPanelMostrarSituação.setVisible(true);
            jTxtNomeAlterar.setEditable(false);
            jFormattedTxtTelefone.setEditable(false);
            jTxtEnderecoAlterarClientes.setEditable(false);
            jFormattedAlterarDados.setEditable(false);
            jPanelBtnSalvar.setVisible(false);
        }
        
        
        
        
    }//GEN-LAST:event_jBtnAlterarActionPerformed

    private void jBtnSalvarDadosAlteradosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSalvarDadosAlteradosActionPerformed
        // TODO add your handling code here:
       
        String açao = "finalizar a alteração do cliente.";
        String vendedor = jLabelNomePerfil.getText();
        String nm_user = jLabelUsuarioPerfil.getText();
        String nome = jTxtNomeAlterar.getText();
        String telefone = jFormattedTxtTelefone.getText();
        String endereco = jTxtEnderecoAlterarClientes.getText();
        
        double limite = setValorDouble(jFormattedAlterarDados.getText());  // ALTERAÇÃO FULL
        
        Senha s = new Senha();
        s.setLabel(açao, vendedor, nm_user);
        s.getDadosClientes(nome, endereco, telefone, limite);
        s.show();
        s.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_jBtnSalvarDadosAlteradosActionPerformed

    private void jBtnVoltarAlterarDadosClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnVoltarAlterarDadosClientesActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaConsulta");
        listarTabelaCliente();
        jPanelBtnAcaoCliente.setVisible(false);
        jTableClientes.clearSelection();
        jLabelSaldoDisp.setText("");
        jTxtNomeAlterar.setText("");
        jFormattedTxtTelefone.setText("");
        jTxtEnderecoAlterarClientes.setText("");
        jFormattedAlterarDados.setText("00");
    }//GEN-LAST:event_jBtnVoltarAlterarDadosClientesActionPerformed

    private void jBtnVerDetalhesComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnVerDetalhesComprasActionPerformed
        // TODO add your handling code here:
        valorLimite = Double.valueOf(jLabelLimiteDisponivelDetalhesClientes.getText());
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "DetalhesCompra");
        jLabelNomeCliente.setText(jLabelNomeDetalhes.getText());
        
        if(jTableComprasCliente.getModel().getValueAt(jTableComprasCliente.getSelectedRow(), 4).equals("PAGO")){
            jPanelMetodoPg.setVisible(false);
        }else{
            jPanelMetodoPg.setVisible(true);
        }
        
        int id = (int) jTableComprasCliente.getModel().getValueAt(jTableComprasCliente.getSelectedRow(), 0);
        String nome = jLabelNomeDetalhes.getText();
        String data = (String) jTableComprasCliente.getModel().getValueAt(jTableComprasCliente.getSelectedRow(), 1);
        String descricao = (String) jTableComprasCliente.getModel().getValueAt(jTableComprasCliente.getSelectedRow(), 2);
        double valor = (double) jTableComprasCliente.getModel().getValueAt(jTableComprasCliente.getSelectedRow(), 3);
        
        String valores = String.valueOf(valor);
        
        String valorA = valores.replace(".", ",");
        //String valorB = valores.replace(",", ".");
        
        //double valorFinal = Double.valueOf(valores);
        
        jTxtIDCompra.setText(String.valueOf(id));
        jTxtIDCompra.setEditable(false);
        jTxtNomeDetalhesCompra.setText(nome);
        jTxtNomeDetalhesCompra.setEditable(false);
        jFormattedTxtValor.setText(valorA);
        jFormattedTxtValor.setEditable(false);
        jFormattedTxtDtVenda.setText(data);
        jFormattedTxtDtVenda.setEditable(false);
        jTtxtDescricaoDetalhes.setText(descricao);
        jTtxtDescricaoDetalhes.setEditable(false);
    }//GEN-LAST:event_jBtnVerDetalhesComprasActionPerformed

    private void jBtnVoltarComprasDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnVoltarComprasDetalhesActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaDetalhes");
        jPanelMetodoPg.setVisible(false);
        jPanelRecebimentoCompra.setVisible(false);
        jPanelBtnReceberTudo.setVisible(false);
        jPanelFazerAcordo.setVisible(false);
        buttonGroup2.clearSelection();
        jTableComprasCliente.clearSelection();
        jPanelBtnVerDetalhes.setVisible(false);
        jFormattedTxtDtRecibo.setText(null);
        jFormattedTxtValorAReceber.setText("0.00");
        String nome = jTxtNomeDetalhesCompra.getText();
        jLabelLimiteDisponivelDetalhesClientes.setText(String.valueOf(valorLimite));
        String telefone = jLabelTelefoneDetalhesCliente.getText();
        listarTabelaCompra(nome, telefone);
        jLabelValorTotal.setText(somaTable());
        
        
        
    }//GEN-LAST:event_jBtnVoltarComprasDetalhesActionPerformed

    private void jTableComprasClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableComprasClienteMouseClicked
        // TODO add your handling code here:
        
        jPanelBtnVerDetalhes.setVisible(true);
    }//GEN-LAST:event_jTableComprasClienteMouseClicked

    private void jBtnReceberTudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReceberTudoActionPerformed
        // TODO add your handling code here:
        if(jFormattedTxtDtRecibo.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Digite o campo antes de prosseguir!","Alerta!",1);
        }else{
            String dtCompra = setDt(jFormattedTxtDtVenda.getText());  //ALTERAÇÃO FULL
            String dtPagamento = setDt(jFormattedTxtDtRecibo.getText()); // ALTERAÇÃO FULL
            String acao = "finalizar o recebimento da compra.";
            String nm_user = jLabelUsuarioPerfil.getText();
            String vendedor = jLabelNomePerfil.getText();
            String nm_cliente = jTxtNomeDetalhesCompra.getText();
            String telefone_cliente = jLabelTelefoneDetalhesCliente.getText();
            String descricao = jTtxtDescricaoDetalhes.getText();
            double valorCompra = setValorDouble(jFormattedTxtValor.getText());
            double limiteCliente = Double.valueOf(jLabelLimiteDisponivelDetalhesClientes.getText());
            double nvLimite = limiteCliente + valorCompra;
            String limiteAtual = arredondaValores(nvLimite);  //ALTERAÇÃO FULL
            double limiteFinal = setValorDouble(limiteAtual);  //ALTERAÇÃO FULL
            valorLimite = limiteFinal;
            int idCompra = Integer.valueOf(jTxtIDCompra.getText()); 
         
            Senha s = new Senha();
            s.setLabel(acao, vendedor, nm_user);
            s.getDadosCompra(nm_cliente, telefone_cliente, dtCompra, valorCompra, descricao, valorCompra, vendedor, limiteFinal, dtPagamento, idCompra);
            s.show();
            s.setLocationRelativeTo(null);
       }
    }//GEN-LAST:event_jBtnReceberTudoActionPerformed

    private void jRadioAcordoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioAcordoActionPerformed
        // TODO add your handling code here:
        if(jRadioAcordo.isSelected()){
            jPanelRecebimentoCompra.setVisible(true);
            jPanelFazerAcordo.setVisible(true);
            jPanelValorAreceber.setVisible(true);
            jTtxtDescricaoDetalhes.setEditable(true);
            Color c = new Color(255,255,255);
            jPanelDescricao.setBackground(c);
            
            jPanelBtnReceberTudo.setVisible(false);
            
        }
    }//GEN-LAST:event_jRadioAcordoActionPerformed

    private void jRadioAVistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioAVistaActionPerformed
        // TODO add your handling code here:
        if(jRadioAVista.isSelected()){
            jPanelRecebimentoCompra.setVisible(true);
            jPanelBtnReceberTudo.setVisible(true);
            jPanelFazerAcordo.setVisible(false);
            jPanelValorAreceber.setVisible(false);
            jTtxtDescricaoDetalhes.setEditable(false);
            Color c = new Color(153,153,153);
            jPanelDescricao.setBackground(c);
        }
    }//GEN-LAST:event_jRadioAVistaActionPerformed

    private void jBtnFazerAcordoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnFazerAcordoActionPerformed
        // TODO add your handling code here:
        if(jFormattedTxtDtRecibo.getText().equals("") || (jFormattedTxtValorAReceber.getText().equals("")||(jFormattedTxtValorAReceber.getText().equals(",00")))){
            JOptionPane.showMessageDialog(this, "Preencha os campos antes de prosseguir!","Alerta!",1);
        }else{
            String acao = "finalizar o acordo de compras do cliente.";
            String vendedor = jLabelNomePerfil.getText();
            String nm_user = jLabelUsuarioPerfil.getText();
            String nmCliente = jTxtNomeDetalhesCompra.getText();
            String telefoneCliente = jLabelTelefoneDetalhesCliente.getText();
            String dtCompra = setDt(jFormattedTxtDtVenda.getText());  // ALTERAÇÃO FULL
            double valorCompra = setValorDouble(jFormattedTxtValor.getText());  // ALTERAÇÃO FULL
            String descricao = jTtxtDescricaoDetalhes.getText();
            int idCompra = Integer.valueOf(jTxtIDCompra.getText());
            double limite = Double.valueOf(jLabelLimiteDisponivelDetalhesClientes.getText());
            String dtRecibo = setDt(jFormattedTxtDtRecibo.getText());  // ALTERAÇÃO FULL
            double valorReceber = setValorDouble(jFormattedTxtValorAReceber.getText()); // ALTERAÇÃO FULL
            double nvLimite = limite + valorReceber;
            double nvCompra = valorCompra - valorReceber;
            String limitee = arredondaValores(nvLimite);  // ALTERAÇÃO FULL
            double limiteAtual = setValorDouble(limitee); // ALTERAÇÃO FULL
            String nvCompraAtual = arredondaValores(nvCompra);  // ALTERAÇÃO FULL
            double compraAtual = setValorDouble(nvCompraAtual);  // ALTERAÇÃO FULL
            if(valorReceber > valorCompra){
                JOptionPane.showMessageDialog(this, "Impossível Efetuar o Acordo, Verifique o Valor e Tente Novamente!");
            }else if (valorReceber == valorCompra){
                acao = "finalizar o recebimento da compra.";
                valorLimite = limiteAtual;
                Senha s = new Senha();
                s.setLabel(acao, vendedor, nm_user);
                s.getDadosCompras(nmCliente, telefoneCliente, dtCompra, valorCompra, valorReceber, compraAtual, descricao, vendedor, limiteAtual , dtRecibo, idCompra);
                s.setLocationRelativeTo(null);
                s.show();
           }else{
                valorLimite = limiteAtual;
                Senha s = new Senha();
                s.setLabel(acao, vendedor, nm_user);
                s.getDadosCompras(nmCliente, telefoneCliente, dtCompra, valorCompra, valorReceber, compraAtual, descricao, vendedor, limiteAtual , dtRecibo, idCompra);
                s.setLocationRelativeTo(null);
                s.show();
                
            }
        }
        
    }//GEN-LAST:event_jBtnFazerAcordoActionPerformed

    private void jFormattedTxtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTxtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTxtValorActionPerformed

    private void jBtnExcluirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluirClienteActionPerformed
        // TODO add your handling code here:
        String valorCompra = jLabelValorTotal.getText();
        String valor = valorCompra.replace(",", ".");
        double valorFinal = Double.valueOf(valor);
        if(valorFinal == 0){
            String acao = "tornar o cliente inativo.";
            String nmVendedor = jLabelNomePerfil.getText();
            String nmUser = jLabelUsuarioPerfil.getText();
            String nmCliente = jLabelNomeDetalhes.getText();
            String telefoneCliente = jLabelTelefoneDetalhesCliente.getText();
            
            Senha s = new Senha();
            s.setLabel(acao, nmVendedor, nmUser);
            s.getDadosClientePk(nmCliente, telefoneCliente);
            s.setLocationRelativeTo(null);
            s.show();
            
            CardLayout cl = (CardLayout) jPanel2.getLayout();
            cl.show(jPanel2, "TelaHome");
            jTableClientes.clearSelection();
            jLabelSaldoDisp.setText("");
        }else{
            JOptionPane.showMessageDialog(this, "Esse Cliente Possui Contas Pendentes, Verifique e Tente Novamente!");
        }
        
        
    }//GEN-LAST:event_jBtnExcluirClienteActionPerformed

    private void jBtnAtivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAtivarActionPerformed
        // TODO add your handling code here:
        if(jCheckAtivarCliente.isSelected()){
            
            String acao = "ativar o cliente.";
            String nmVendedor = jLabelNomePerfil.getText();
            String nmUser = jLabelUsuarioPerfil.getText();
            String nmCliente = jTxtNomeAlterar.getText();
            String telefoneCliente = jFormattedTxtTelefone.getText();
            
            Senha s = new Senha();
            s.setLabel(acao, nmVendedor, nmUser);
            s.getDadosClientePk(nmCliente,telefoneCliente);
            s.setLocationRelativeTo(null);
            s.show();
            
            
        }else{
            JOptionPane.showMessageDialog(this, "Marque a Caixa de Seleção Antes de Prosseguir!","Alerta!",1);
        }
    }//GEN-LAST:event_jBtnAtivarActionPerformed

    private void jBtnRelatorioVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRelatorioVendaActionPerformed
        // TODO add your handling code here:
        jPanelDtPg.setVisible(false);
        jPanelTableVendas.setVisible(true);
        jPanelComprasPendentes.setVisible(true);
        jPanelTablePg.setVisible(false);
        jLabelTituloRelatorio.setText("vendas");
        jPanelPg.setVisible(false);
        jPanelRelatorioInfo.setVisible(false);
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaRelatórios");
    }//GEN-LAST:event_jBtnRelatorioVendaActionPerformed

    private void jBtnRelatorioPgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRelatorioPgActionPerformed
        // TODO add your handling code here:
        jPanelTableVendas.setVisible(false);
        jPanelDtPg.setVisible(true);
        jPanelTablePg.setVisible(true);
        jPanelComprasPendentes.setVisible(false);
        jPanelRelatorioInfo.setVisible(false);
        jLabelTituloRelatorio.setText("pagamentos");
        jPanelPg.setVisible(false);
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaRelatórios");
        
    }//GEN-LAST:event_jBtnRelatorioPgActionPerformed

    private void jRadioBtnNmClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioBtnNmClienteActionPerformed
        // TODO add your handling code here:
        if(jRadioBtnNmCliente.isSelected()){
            jTextNomePesquisa.setText("");
            jFormattedTxtDt.setText(null);
            jPanelBtnPesquisar.setVisible(true);
            jPanelCampoNome.setVisible(true);
            jPanelCampoData.setVisible(false);
        }
    }//GEN-LAST:event_jRadioBtnNmClienteActionPerformed

    private void jRadioBtnComprasPendentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioBtnComprasPendentesActionPerformed
        // TODO add your handling code here:
        if(jRadioBtnComprasPendentes.isSelected()){
            jTextNomePesquisa.setText("");
            jFormattedTxtDt.setText(null);
            jPanelBtnPesquisar.setVisible(true);
            jPanelCampoNome.setVisible(false);
            jPanelCampoData.setVisible(false);
        }
    }//GEN-LAST:event_jRadioBtnComprasPendentesActionPerformed

    private void jRadioBtnDtVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioBtnDtVendaActionPerformed
        // TODO add your handling code here:
        if(jRadioBtnDtVenda.isSelected()){
            jTextNomePesquisa.setText("");
            jFormattedTxtDt.setText(null);
            jPanelBtnPesquisar.setVisible(true);
            jPanelCampoNome.setVisible(false);
            jPanelCampoData.setVisible(true);
        }
    }//GEN-LAST:event_jRadioBtnDtVendaActionPerformed

    private void jBtnVoltaConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnVoltaConsultarActionPerformed
        // TODO add your handling code here:
        jTableClientes.clearSelection();
        jPanelBtnAcaoCliente.setVisible(false);
        jLabelSaldoDisp.setText("");
    }//GEN-LAST:event_jBtnVoltaConsultarActionPerformed

    private void jTableRelatorioVendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableRelatorioVendasMouseClicked
        // TODO add your handling code here:
        jPanelRelatorioInfo.setVisible(true);
        jTextNomeClienteRel.setText((String)jTableRelatorioVendas.getModel().getValueAt(jTableRelatorioVendas.getSelectedRow(), 0));
        jTextTelefoneRel.setText((String) jTableRelatorioVendas.getModel().getValueAt(jTableRelatorioVendas.getSelectedRow(), 1));
        
        jTextDtCompra.setText((String) jTableRelatorioVendas.getModel().getValueAt(jTableRelatorioVendas.getSelectedRow(), 2));
        jTextValorCompraRel.setText("R$ "+String.valueOf((Double) jTableRelatorioVendas.getModel().getValueAt(jTableRelatorioVendas.getSelectedRow(), 3)));
        jTextDescRel.setText((String) jTableRelatorioVendas.getModel().getValueAt(jTableRelatorioVendas.getSelectedRow(), 4));
        
        jTextNomeClienteRel.setEditable(false);
        jTextTelefoneRel.setEditable(false);
        jTextDtCompra.setEditable(false);
        jTextValorCompraRel.setEditable(false);
        jTextDescRel.setEditable(false);
        
        
        
        
    }//GEN-LAST:event_jTableRelatorioVendasMouseClicked

    private void jTableRelatorioPagamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableRelatorioPagamentoMouseClicked
        // TODO add your handling code here:
        jPanelRelatorioInfo.setVisible(true);
        jPanelPg.setVisible(true);
        jTextNomeClienteRel.setText((String) jTableRelatorioPagamento.getModel().getValueAt(jTableRelatorioPagamento.getSelectedRow(), 0));
        jTextTelefoneRel.setText((String) jTableRelatorioPagamento.getModel().getValueAt(jTableRelatorioPagamento.getSelectedRow(), 1));
        
        jTextDtCompra.setText((String) jTableRelatorioPagamento.getModel().getValueAt(jTableRelatorioPagamento.getSelectedRow(), 2));
        jTextValorCompraRel.setText("R$ "+String.valueOf((Double) jTableRelatorioPagamento.getModel().getValueAt(jTableRelatorioPagamento.getSelectedRow(), 3)));
        jTextDescRel.setText((String) jTableRelatorioPagamento.getModel().getValueAt(jTableRelatorioPagamento.getSelectedRow(), 4));

        jTextDtPgRel.setText((String) jTableRelatorioPagamento.getModel().getValueAt(jTableRelatorioPagamento.getSelectedRow(), 7));
        jTextValorPgRel.setText("R$ "+String.valueOf((Double) jTableRelatorioPagamento.getModel().getValueAt(jTableRelatorioPagamento.getSelectedRow(), 5)));
        
        jTextNomeClienteRel.setEditable(false);
        jTextTelefoneRel.setEditable(false);
        jTextDtCompra.setEditable(false);
        jTextValorCompraRel.setEditable(false);
        jTextDescRel.setEditable(false);
        jTextDtPgRel.setEditable(false);
        jTextValorPgRel.setEditable(false);
        
        
        
    }//GEN-LAST:event_jTableRelatorioPagamentoMouseClicked

    private void jBtnVoltarRelatorio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnVoltarRelatorio1ActionPerformed
        // TODO add your handling code here:
        jTextNomePesquisa.setText("");
        jFormattedTxtDt.setText(null);
        listarTabelaPagamentos();
        listaTabelaCompras();
        buttonGroup3.clearSelection();
        jPanelCampoNome.setVisible(false);
        jPanelCampoData.setVisible(false);
        jPanelBtnPesquisar.setVisible(false);
    }//GEN-LAST:event_jBtnVoltarRelatorio1ActionPerformed

    private void jBtnVoltarRelatorio1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnVoltarRelatorio1MouseClicked
        // TODO add your handling code here:
        jTextNomeClienteRel.setText("");
        jTextTelefoneRel.setText("");
        jTextDtCompra.setText("");
        jTextValorCompraRel.setText("");
        jTextDescRel.setText("");
        jTextDtPgRel.setText("");
        jTextValorPgRel.setText("");
        
        jTableRelatorioVendas.clearSelection();
        jTableRelatorioPagamento.clearSelection();
        CardLayout cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, "TelaPerfil");
    }//GEN-LAST:event_jBtnVoltarRelatorio1MouseClicked

    private void jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesquisarActionPerformed
        // TODO add your handling code here:
        String tabela = jLabelTituloRelatorio.getText();
        if(tabela.equals("vendas")){
            VendaDAO vnDAO = new VendaDAO();
            List<Compras> compras = null;
            if(jRadioBtnNmCliente.isSelected()){
                compras = vnDAO.pesquisaCompras(1, jTextNomePesquisa.getText());
               
            }else if(jRadioBtnComprasPendentes.isSelected()){
                compras = vnDAO.pesquisaPendente();
                              
            }else{
                String dtCompra = jFormattedTxtDt.getText();
                String data = dtCompra.replace(" ", "");
                compras = vnDAO.pesquisaCompras(3, data);
            }
            DefaultTableModel valor = (DefaultTableModel) jTableRelatorioVendas.getModel();
            valor.getDataVector().removeAllElements();
            
            int i = 0;
            while(compras.size()>i){
                valor.addRow(new Object[]{compras.get(i).getNome_cliente(), compras.get(i).getTelefone_cliente(), compras.get(i).getDt_venda(), compras.get(i).getValor_venda(), compras.get(i).getDescricao(), compras.get(i).getSituacao(), compras.get(i).getNm_vendedor()});
                i++;
            }
        }else{
            PagamentosDAO pgDAO = new PagamentosDAO();
            List<Pagamentos> pagamentos = null;
            if(jRadioBtnNmCliente.isSelected()){
                pagamentos = pgDAO.pesquisarPagamentos(1, jTextNomePesquisa.getText());

            }else{
                String dt_Compra = jFormattedTxtDt.getText();
                String dt = dt_Compra.replace(" ", "");
                pagamentos = pgDAO.pesquisarPagamentos(2, dt);
                
            }
            DefaultTableModel valores = (DefaultTableModel) jTableRelatorioPagamento.getModel();
                
            valores.getDataVector().removeAllElements();
            
            int a = 0;
            while(pagamentos.size()>a){
                valores.addRow(new Object[]{pagamentos.get(a).getNome_cliente(), pagamentos.get(a).getTelefone_cliente(), pagamentos.get(a).getDt_compra(), pagamentos.get(a).getValor_compra(), pagamentos.get(a).getDescricao(), pagamentos.get(a).getValor_pago(), pagamentos.get(a).getTipo_venda(), pagamentos.get(a).getDt_pagamento(), pagamentos.get(a).getNm_vendedor()});
                a++;
            }
        }
    
    }//GEN-LAST:event_jBtnPesquisarActionPerformed

    private void jTextNomePesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextNomePesquisaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(jLabelTituloRelatorio.getText().equals("vendas")){
            VendaDAO vnDAO = new VendaDAO();
            List<Compras> compras = null;
            if(jRadioBtnNmCliente.isSelected()){
                compras = vnDAO.pesquisaCompras(1, jTextNomePesquisa.getText());
               
            }else if(jRadioBtnComprasPendentes.isSelected()){
                compras = vnDAO.pesquisaCompras(2, jTextNomePesquisa.getText());
                              
            }else{
                String dtCompra = setDt(jFormattedTxtDt.getText());
                compras = vnDAO.pesquisaCompras(3, dtCompra);
            }
            DefaultTableModel valor = (DefaultTableModel) jTableRelatorioVendas.getModel();
            valor.getDataVector().removeAllElements();
            
            int i = 0;
            while(compras.size()>i){
                valor.addRow(new Object[]{compras.get(i).getNome_cliente(), compras.get(i).getTelefone_cliente(), compras.get(i).getDt_venda(), compras.get(i).getValor_venda(), compras.get(i).getDescricao(), compras.get(i).getSituacao(), compras.get(i).getNm_vendedor()});
                i++;
            }
        }
            
        }
    }//GEN-LAST:event_jTextNomePesquisaKeyPressed

    private void jFormattedTxtDtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTxtDtKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(jLabelTituloRelatorio.getText().equals("vendas")){
            VendaDAO vnDAO = new VendaDAO();
            List<Compras> compras = null;
            if(jRadioBtnNmCliente.isSelected()){
                compras = vnDAO.pesquisaCompras(1, jTextNomePesquisa.getText());
               
            }else if(jRadioBtnComprasPendentes.isSelected()){
                compras = vnDAO.pesquisaCompras(2, jTextNomePesquisa.getText());
                              
            }else{
                String dtCompra = setDt(jFormattedTxtDt.getText());
                compras = vnDAO.pesquisaCompras(3, dtCompra);
            }
            DefaultTableModel valor = (DefaultTableModel) jTableRelatorioVendas.getModel();
            valor.getDataVector().removeAllElements();
            
            int i = 0;
            while(compras.size()>i){
                valor.addRow(new Object[]{compras.get(i).getNome_cliente(), compras.get(i).getTelefone_cliente(), compras.get(i).getDt_venda(), compras.get(i).getValor_venda(), compras.get(i).getDescricao(), compras.get(i).getSituacao(), compras.get(i).getNm_vendedor()});
                i++;
            }
        }
        }
    }//GEN-LAST:event_jFormattedTxtDtKeyPressed

    private void jBtnAlterarFunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAlterarFunActionPerformed
        // TODO add your handling code here:
        String açao = "finalizar a alteração de um funcionário.";
        String nm_user = jLabelUsuarioPerfil.getText();
        String nome = jLabelNomePerfil.getText();
        
        String nm_fun = jTxtNomeFunEditar.getText();
        String nm_userFun = jTxtNmUsuarioEditar.getText();
        char[] pass = jPassSenhaFuncEditar.getPassword();
        String senha = String.valueOf(pass);
        
        Senha s = new Senha();
        s.setLabel(açao, nome, nm_user);
        s.getDadosVendedor(nm_fun, nm_userFun, senha);
        s.setLocationRelativeTo(null);
        s.show();
        
        
        
        
    }//GEN-LAST:event_jBtnAlterarFunActionPerformed

    private void jRadioBtnDtPgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioBtnDtPgActionPerformed
        // TODO add your handling code here:
        if(jRadioBtnDtPg.isSelected()){
            jTextNomePesquisa.setText("");
            jPanelBtnPesquisar.setVisible(true);
            jPanelCampoData.setVisible(true);
            jPanelCampoNome.setVisible(false);
        }
    }//GEN-LAST:event_jRadioBtnDtPgActionPerformed

    private void jFormattedTxtDtReciboKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTxtDtReciboKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(jRadioAVista.isSelected() && (jFormattedTxtDtRecibo.getText().equals("          /        /      "))){
                JOptionPane.showMessageDialog(this, "Digite o campo antes de prosseguir!","Alerta!",1);
            }else if(jRadioAcordo.isSelected()){
                
                if(jFormattedTxtDtRecibo.getText().equals("          /        /      ") || (jFormattedTxtValorAReceber.getText().equals("")||(jFormattedTxtValorAReceber.getText().equals(",00")))){
                    
                    JOptionPane.showMessageDialog(this, "Preencha os campos antes de prosseguir!","Alerta!",1);
                }else{
                    
                    String acao = "finalizar o acordo de compras do cliente.";
                    String vendedor = jLabelNomePerfil.getText();
                    String nm_user = jLabelUsuarioPerfil.getText();
                    String nmCliente = jTxtNomeDetalhesCompra.getText();
                    String telefoneCliente = jLabelTelefoneDetalhesCliente.getText();
                    String dtCompra = setDt(jFormattedTxtDtVenda.getText());  // ALTERAÇÃO FULL
                    double valorCompra = setValorDouble(jFormattedTxtValor.getText());  // ALTERAÇÃO FULL
                    String descricao = jTtxtDescricaoDetalhes.getText();
                    int idCompra = Integer.valueOf(jTxtIDCompra.getText());
                    double limite = Double.valueOf(jLabelLimiteDisponivelDetalhesClientes.getText());
                    String dtRecibo = setDt(jFormattedTxtDtRecibo.getText());  // ALTERAÇÃO FULL
                    double valorReceber = setValorDouble(jFormattedTxtValorAReceber.getText()); // ALTERAÇÃO FULL
                    double nvLimite = limite + valorReceber;
                    double nvCompra = valorCompra - valorReceber;
                    String limitee = arredondaValores(nvLimite);  // ALTERAÇÃO FULL
                    double limiteAtual = setValorDouble(limitee); // ALTERAÇÃO FULL
                    String nvCompraAtual = arredondaValores(nvCompra);  // ALTERAÇÃO FULL
                    double compraAtual = setValorDouble(nvCompraAtual);  // ALTERAÇÃO FULL
                    if(valorReceber > valorCompra){
                        JOptionPane.showMessageDialog(this, "Impossível Efetuar o Acordo, Verifique o Valor e Tente Novamente!");
                    }else if (valorReceber == valorCompra){
                        acao = "finalizar o recebimento da compra.";
                        valorLimite = limiteAtual;
                        Senha s = new Senha();
                        s.setLabel(acao, vendedor, nm_user);
                        s.getDadosCompras(nmCliente, telefoneCliente, dtCompra, valorCompra, valorReceber, compraAtual, descricao, vendedor, limiteAtual , dtRecibo, idCompra);
                        s.setLocationRelativeTo(null);
                        s.show();
                    }else{
                        valorLimite = limiteAtual;
                        Senha s = new Senha();
                        s.setLabel(acao, vendedor, nm_user);
                        s.getDadosCompras(nmCliente, telefoneCliente, dtCompra, valorCompra, valorReceber, compraAtual, descricao, vendedor, limiteAtual , dtRecibo, idCompra);
                        s.setLocationRelativeTo(null);
                        s.show();
                    }
                }
            }else{        
                String dtCompra = setDt(jFormattedTxtDtVenda.getText());  //ALTERAÇÃO FULL
                String dtPagamento = setDt(jFormattedTxtDtRecibo.getText()); // ALTERAÇÃO FULL
                String acao = "finalizar o recebimento da compra.";
                String nm_user = jLabelUsuarioPerfil.getText();
                String vendedor = jLabelNomePerfil.getText();
                String nm_cliente = jTxtNomeDetalhesCompra.getText();
                String telefone_cliente = jLabelTelefoneDetalhesCliente.getText();
                String descricao = jTtxtDescricaoDetalhes.getText();
                double valorCompra = setValorDouble(jFormattedTxtValor.getText());
                double limiteCliente = Double.valueOf(jLabelLimiteDisponivelDetalhesClientes.getText());
                double nvLimite = limiteCliente + valorCompra;
                String limiteAtual = arredondaValores(nvLimite);  //ALTERAÇÃO FULL
                double limiteFinal = setValorDouble(limiteAtual);  //ALTERAÇÃO FULL
                valorLimite = limiteFinal;
                int idCompra = Integer.valueOf(jTxtIDCompra.getText()); 

                Senha s = new Senha();
                s.setLabel(acao, vendedor, nm_user);
                s.getDadosCompra(nm_cliente, telefone_cliente, dtCompra, valorCompra, descricao, valorCompra, vendedor, limiteFinal, dtPagamento, idCompra);
                s.show();
                s.setLocationRelativeTo(null);
            }
        }
    }//GEN-LAST:event_jFormattedTxtDtReciboKeyPressed

    private void jFormattedTxtValorAReceberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTxtValorAReceberKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(jFormattedTxtDtRecibo.getText().equals("") || (jFormattedTxtValorAReceber.getText().equals("")||(jFormattedTxtValorAReceber.getText().equals(",00")))){
                    JOptionPane.showMessageDialog(this, "Preencha os campos antes de prosseguir!","Alerta!",1);
                }else{
                    String acao = "finalizar o acordo de compras do cliente.";
                    String vendedor = jLabelNomePerfil.getText();
                    String nm_user = jLabelUsuarioPerfil.getText();
                    String nmCliente = jTxtNomeDetalhesCompra.getText();
                    String telefoneCliente = jLabelTelefoneDetalhesCliente.getText();
                    String dtCompra = setDt(jFormattedTxtDtVenda.getText());  // ALTERAÇÃO FULL
                    double valorCompra = setValorDouble(jFormattedTxtValor.getText());  // ALTERAÇÃO FULL
                    String descricao = jTtxtDescricaoDetalhes.getText();
                    int idCompra = Integer.valueOf(jTxtIDCompra.getText());
                    double limite = Double.valueOf(jLabelLimiteDisponivelDetalhesClientes.getText());
                    String dtRecibo = setDt(jFormattedTxtDtRecibo.getText());  // ALTERAÇÃO FULL
                    double valorReceber = setValorDouble(jFormattedTxtValorAReceber.getText()); // ALTERAÇÃO FULL
                    double nvLimite = limite + valorReceber;
                    double nvCompra = valorCompra - valorReceber;
                    String limitee = arredondaValores(nvLimite);  // ALTERAÇÃO FULL
                    double limiteAtual = setValorDouble(limitee); // ALTERAÇÃO FULL
                    String nvCompraAtual = arredondaValores(nvCompra);  // ALTERAÇÃO FULL
                    double compraAtual = setValorDouble(nvCompraAtual);  // ALTERAÇÃO FULL
                    if(valorReceber > valorCompra){
                        JOptionPane.showMessageDialog(this, "Impossível Efetuar o Acordo, Verifique o Valor e Tente Novamente!");
                    }else if (valorReceber == valorCompra){
                        
                        acao = "finalizar o recebimento da compra.";
                        valorLimite = limiteAtual;
                        Senha s = new Senha();
                        s.setLabel(acao, vendedor, nm_user);
                        s.getDadosCompras(nmCliente, telefoneCliente, dtCompra, valorCompra, valorReceber, compraAtual, descricao, vendedor, limiteAtual , dtRecibo, idCompra);
                        s.setLocationRelativeTo(null);
                        s.show();
                    }else{
                        valorLimite = limiteAtual;
                        Senha s = new Senha();
                        s.setLabel(acao, vendedor, nm_user);
                        s.getDadosCompras(nmCliente, telefoneCliente, dtCompra, valorCompra, valorReceber, compraAtual, descricao, vendedor, limiteAtual , dtRecibo, idCompra);
                        s.setLocationRelativeTo(null);
                        s.show();
                
                    }
                }
        }
    }//GEN-LAST:event_jFormattedTxtValorAReceberKeyPressed

    private void jTxtFiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtFiltroKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            ClienteDAO clDAO = new ClienteDAO();
            List<Clientes> clientes = null;
            clientes = clDAO.pesquisaCliente(jTxtFiltro.getText());
        
            DefaultTableModel valor = (DefaultTableModel) jTableClientes.getModel();
            valor.getDataVector().removeAllElements();
        
            int i = 0;
        
            while(clientes.size()>i){
                valor.addRow(new Object[]{clientes.get(i).getNome(), clientes.get(i).getTelefone(), clientes.get(i).getEndereco(), clientes.get(i).getSituacao(), clientes.get(i).getNm_vendedor()});
                i++;
            }
        }
        
    }//GEN-LAST:event_jTxtFiltroKeyPressed

    private void jFormattedAlterarDadosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedAlterarDadosKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String açao = "finalizar a alteração do cliente.";
            String vendedor = jLabelNomePerfil.getText();
            String nm_user = jLabelUsuarioPerfil.getText();
            String nome = jTxtNomeAlterar.getText();
            String telefone = jFormattedTxtTelefone.getText();
            String endereco = jTxtEnderecoAlterarClientes.getText();
        
            double limite = setValorDouble(jFormattedAlterarDados.getText());  // ALTERAÇÃO FULL
        
            Senha s = new Senha();
            s.setLabel(açao, vendedor, nm_user);
            s.getDadosClientes(nome, endereco, telefone, limite);
            s.show();
            s.setLocationRelativeTo(null);
        }
        
    }//GEN-LAST:event_jFormattedAlterarDadosKeyPressed

    private void jTxtEnderecoAlterarClientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtEnderecoAlterarClientesKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String açao = "finalizar a alteração do cliente.";
            String vendedor = jLabelNomePerfil.getText();
            String nm_user = jLabelUsuarioPerfil.getText();
            String nome = jTxtNomeAlterar.getText();
            String telefone = jFormattedTxtTelefone.getText();
            String endereco = jTxtEnderecoAlterarClientes.getText();
        
            double limite = setValorDouble(jFormattedAlterarDados.getText());  // ALTERAÇÃO FULL
        
            Senha s = new Senha();
            s.setLabel(açao, vendedor, nm_user);
            s.getDadosClientes(nome, endereco, telefone, limite);
            s.show();
            s.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_jTxtEnderecoAlterarClientesKeyPressed

    private void jFormattedTxtTelefoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTxtTelefoneKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String açao = "finalizar a alteração do cliente.";
            String vendedor = jLabelNomePerfil.getText();
            String nm_user = jLabelUsuarioPerfil.getText();
            String nome = jTxtNomeAlterar.getText();
            String telefone = jFormattedTxtTelefone.getText();
            String endereco = jTxtEnderecoAlterarClientes.getText();
        
            double limite = setValorDouble(jFormattedAlterarDados.getText());  // ALTERAÇÃO FULL
        
            Senha s = new Senha();
            s.setLabel(açao, vendedor, nm_user);
            s.getDadosClientes(nome, endereco, telefone, limite);
            s.show();
            s.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_jFormattedTxtTelefoneKeyPressed

    private void jTxtNomeAlterarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtNomeAlterarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String açao = "finalizar a alteração do cliente.";
            String vendedor = jLabelNomePerfil.getText();
            String nm_user = jLabelUsuarioPerfil.getText();
            String nome = jTxtNomeAlterar.getText();
            String telefone = jFormattedTxtTelefone.getText();
            String endereco = jTxtEnderecoAlterarClientes.getText();
        
            double limite = setValorDouble(jFormattedAlterarDados.getText());  // ALTERAÇÃO FULL
        
            Senha s = new Senha();
            s.setLabel(açao, vendedor, nm_user);
            s.getDadosClientes(nome, endereco, telefone, limite);
            s.show();
            s.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_jTxtNomeAlterarKeyPressed

    private void jPassFunCadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPassFunCadKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(jTxtNomeFuncCad.getText().equals("") || jTxtUserFunCad.getText().equals("") || jPassFunCad.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Preencha todos os campos antes de prosseguir!", "Alerta!",1);
            }else{
                String nm_vendedor = jTxtNomeFuncCad.getText();
                String nm_usuario = jTxtUserFunCad.getText();
                String senha = String.valueOf(jPassFunCad.getPassword());
                String nome = jLabelNomeUsuario.getText();
                String usuario = jLabelUsuarioPerfil.getText();
                String acao = "finalizar o cadastro de um novo vendedor.";
                Senha s = new Senha();
                s.setLabel(acao, nome, usuario);
                s.getDadosVendedor(nm_vendedor, nm_usuario, senha);
                s.show();
                s.setLocationRelativeTo(null);
            }
        }
    }//GEN-LAST:event_jPassFunCadKeyPressed

    private void jTextPanelDescriçaoCompraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPanelDescriçaoCompraKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(jFormattedTxtDataCompra.getText().equals("") || jFormattedTxtValorCompra.getText().equals(null) || jTextPanelDescriçaoCompra.getText().equals("")){
            
                JOptionPane.showMessageDialog(this, "Preencha os campos acima antes de prosseguir!","Alerta!",1);
            
            }else{
                double valorCompra = setValorDouble(jFormattedTxtValorCompra.getText());  // ALTERAÇÃO FULL
                double valor_limite = Double.parseDouble(jLabelLimiteDisponivelDetalhesClientes.getText());
                double nvLimite = valor_limite - valorCompra;
            
                String nv_limite = arredondaValores(nvLimite);  // ALTERAÇÃO FULL
                double limiteAtual = setValorDouble(nv_limite);
         
            
            
                if(valorCompra <= valor_limite){
                    Senha s = new Senha();
                    String acao = "finalizar a compra do cliente";
                    String nome_user = jLabelNomeUsuario.getText();
                    String usuario = jLabelUsuarioPerfil.getText();
                    s.setLabel(acao, nome_user, usuario);
                    String dt_compra = setDt(jFormattedTxtDataCompra.getText());  // ALTERAÇÃO FULL
                    String descrição = jTextPanelDescriçaoCompra.getText();
                    String nome_cliente = jLabelNomeDetalhes.getText();
                    String telefone_cliente = jLabelTelefoneDetalhesCliente.getText();  
                    jLabelLimiteDisponivelDetalhesClientes.setText(String.valueOf(limiteAtual));
                    s.getDadosCompra(nome_cliente, telefone_cliente, dt_compra, valorCompra, descrição, nome_user, limiteAtual);
                    s.show();
                    s.setLocationRelativeTo(null);
                }else{
                    JOptionPane.showMessageDialog(this, "Saldo Insuficiente Para Efetuar Essa Compra!","Alerta!",1);
                }
            }
        }
    }//GEN-LAST:event_jTextPanelDescriçaoCompraKeyPressed

    private void jPassSenhaFuncEditarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPassSenhaFuncEditarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String açao = "finalizar a alteração de um funcionário.";
            String nm_user = jLabelUsuarioPerfil.getText();
            String nome = jLabelNomePerfil.getText();
        
            String nm_fun = jTxtNomeFunEditar.getText();
            String nm_userFun = jTxtNmUsuarioEditar.getText();
            char[] pass = jPassSenhaFuncEditar.getPassword();
            String senha = String.valueOf(pass);
        
            Senha s = new Senha();
            s.setLabel(açao, nome, nm_user);
            s.getDadosVendedor(nm_fun, nm_userFun, senha);
            s.setLocationRelativeTo(null);
            s.show();
        }
    }//GEN-LAST:event_jPassSenhaFuncEditarKeyPressed

    private void jTxtNmUsuarioEditarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtNmUsuarioEditarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String açao = "finalizar a alteração de um funcionário.";
            String nm_user = jLabelUsuarioPerfil.getText();
            String nome = jLabelNomePerfil.getText();
        
            String nm_fun = jTxtNomeFunEditar.getText();
            String nm_userFun = jTxtNmUsuarioEditar.getText();
            char[] pass = jPassSenhaFuncEditar.getPassword();
            String senha = String.valueOf(pass);
        
            Senha s = new Senha();
            s.setLabel(açao, nome, nm_user);
            s.getDadosVendedor(nm_fun, nm_userFun, senha);
            s.setLocationRelativeTo(null);
            s.show();
        }
    }//GEN-LAST:event_jTxtNmUsuarioEditarKeyPressed

    private void jTxtNomeFunEditarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtNomeFunEditarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String açao = "finalizar a alteração de um funcionário.";
            String nm_user = jLabelUsuarioPerfil.getText();
            String nome = jLabelNomePerfil.getText();
        
            String nm_fun = jTxtNomeFunEditar.getText();
            String nm_userFun = jTxtNmUsuarioEditar.getText();
            char[] pass = jPassSenhaFuncEditar.getPassword();
            String senha = String.valueOf(pass);
        
            Senha s = new Senha();
            s.setLabel(açao, nome, nm_user);
            s.getDadosVendedor(nm_fun, nm_userFun, senha);
            s.setLocationRelativeTo(null);
            s.show();
        }
    }//GEN-LAST:event_jTxtNomeFunEditarKeyPressed

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
            java.util.logging.Logger.getLogger(AreaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AreaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AreaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AreaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AreaUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jBtnAdicionarCompras;
    private javax.swing.JButton jBtnAlterar;
    private javax.swing.JButton jBtnAlterarFun;
    private javax.swing.JButton jBtnApagar;
    private javax.swing.JButton jBtnAtivar;
    private javax.swing.JButton jBtnCadastrarCliente;
    private javax.swing.JButton jBtnCadastroGerencia;
    private javax.swing.JButton jBtnCancelarCompra;
    private javax.swing.JButton jBtnConsulta;
    private javax.swing.JButton jBtnExcluirCliente;
    private javax.swing.JButton jBtnFazerAcordo;
    private javax.swing.JButton jBtnGerenciar;
    private javax.swing.JButton jBtnLimpaCamposGeren;
    private javax.swing.JButton jBtnLimparCadCliente;
    private javax.swing.JButton jBtnLogout;
    private javax.swing.JButton jBtnMarcarVenda;
    private javax.swing.JButton jBtnMeuPerfil;
    private javax.swing.JButton jBtnNvCliente;
    private javax.swing.JButton jBtnPesquisar;
    private javax.swing.JButton jBtnPesquisarConsulta;
    private javax.swing.JButton jBtnPromover;
    private javax.swing.JButton jBtnReceberTudo;
    private javax.swing.JButton jBtnRelatorioPg;
    private javax.swing.JButton jBtnRelatorioVenda;
    private javax.swing.JButton jBtnSalvarDadosAlterados;
    private javax.swing.JButton jBtnVerDetalhes;
    private javax.swing.JButton jBtnVerDetalhesCompras;
    private javax.swing.JButton jBtnVerDetalhesFuncionario;
    private javax.swing.JButton jBtnVoltaConsultar;
    private javax.swing.JButton jBtnVoltarAlterarDadosClientes;
    private javax.swing.JButton jBtnVoltarCad;
    private javax.swing.JButton jBtnVoltarComprasDetalhes;
    private javax.swing.JButton jBtnVoltarDetalhes;
    private javax.swing.JButton jBtnVoltarGerenciador;
    private javax.swing.JButton jBtnVoltarPerfil;
    private javax.swing.JButton jBtnVoltarRelatorio1;
    private javax.swing.JButton jBtnVoltarTelaFuncionario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckAtivarCliente;
    private javax.swing.JCheckBox jCheckMostrarSenha;
    private javax.swing.JCheckBox jCheckSenha;
    private javax.swing.JCheckBox jCheckSenhaColaborador;
    private javax.swing.JFormattedTextField jFormattTelCadCliente;
    private javax.swing.JFormattedTextField jFormattedAlterarDados;
    private javax.swing.JFormattedTextField jFormattedTxtDataCompra;
    private javax.swing.JFormattedTextField jFormattedTxtDt;
    private javax.swing.JFormattedTextField jFormattedTxtDtRecibo;
    private javax.swing.JFormattedTextField jFormattedTxtDtVenda;
    private javax.swing.JFormattedTextField jFormattedTxtTelefone;
    private javax.swing.JFormattedTextField jFormattedTxtValor;
    private javax.swing.JFormattedTextField jFormattedTxtValorAReceber;
    private javax.swing.JFormattedTextField jFormattedTxtValorCompra;
    private javax.swing.JFormattedTextField jFormattedValorCadCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLabel jLabelCargoFuncionario;
    private javax.swing.JLabel jLabelCargoPerfil;
    private javax.swing.JLabel jLabelCargoUsuario;
    private javax.swing.JLabel jLabelEndereçoDetalhesCliente;
    private javax.swing.JLabel jLabelLimiteDisponivelDetalhesClientes;
    private javax.swing.JLabel jLabelNomeCliente;
    private javax.swing.JLabel jLabelNomeDetalhes;
    private javax.swing.JLabel jLabelNomeFun;
    private javax.swing.JLabel jLabelNomeFuncionario;
    private javax.swing.JLabel jLabelNomePerfil;
    private javax.swing.JLabel jLabelNomeUsuario;
    private javax.swing.JLabel jLabelNomeVendedor;
    private javax.swing.JLabel jLabelQntMarcados;
    private javax.swing.JLabel jLabelQntMarcadosRec;
    private javax.swing.JLabel jLabelSaldoDisp;
    private javax.swing.JLabel jLabelSenhaFuncionario;
    private javax.swing.JLabel jLabelSenhaUsuario;
    private javax.swing.JLabel jLabelStatusCliente;
    private javax.swing.JLabel jLabelTelefoneDetalhesCliente;
    private javax.swing.JLabel jLabelTituloRelatorio;
    private javax.swing.JLabel jLabelUsuarioFuncionario;
    private javax.swing.JLabel jLabelUsuarioPerfil;
    private javax.swing.JLabel jLabelValorTotal;
    private javax.swing.JLabel jLabelVendasMarcadasInfoFun;
    private javax.swing.JLabel jLabelVendasRecInfoFun;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelAddCompraCliente;
    private javax.swing.JPanel jPanelAlterar;
    private javax.swing.JPanel jPanelAlterarDadosFunc;
    private javax.swing.JPanel jPanelBtnAcaoCliente;
    private javax.swing.JPanel jPanelBtnApagar;
    private javax.swing.JPanel jPanelBtnConsulta;
    private javax.swing.JPanel jPanelBtnGerenciamento;
    private javax.swing.JPanel jPanelBtnLogout;
    private javax.swing.JPanel jPanelBtnNvCliente;
    private javax.swing.JPanel jPanelBtnPesquisar;
    private javax.swing.JPanel jPanelBtnReceberTudo;
    private javax.swing.JPanel jPanelBtnRelatorios;
    private javax.swing.JPanel jPanelBtnSalvar;
    private javax.swing.JPanel jPanelBtnVerDetalhes;
    private javax.swing.JPanel jPanelBtns;
    private javax.swing.JPanel jPanelCadastro;
    private javax.swing.JPanel jPanelCampoData;
    private javax.swing.JPanel jPanelCampoNome;
    private javax.swing.JPanel jPanelComprasPendentes;
    private javax.swing.JPanel jPanelConsulta;
    private javax.swing.JPanel jPanelDescricao;
    private javax.swing.JPanel jPanelDetalhes;
    private javax.swing.JPanel jPanelDetalhesCompra;
    private javax.swing.JPanel jPanelDtPg;
    private javax.swing.JPanel jPanelExcluirCliente;
    private javax.swing.JPanel jPanelFazerAcordo;
    private javax.swing.JPanel jPanelGerenciamento;
    private javax.swing.JPanel jPanelHome;
    private javax.swing.JPanel jPanelInfoFuncionario;
    private javax.swing.JPanel jPanelLabelSenha;
    private javax.swing.JPanel jPanelMetodoPg;
    private javax.swing.JPanel jPanelMeuPerfil;
    private javax.swing.JPanel jPanelMostrarSituação;
    private javax.swing.JPanel jPanelPerfil;
    private javax.swing.JPanel jPanelPg;
    private javax.swing.JPanel jPanelPromover;
    private javax.swing.JPanel jPanelRecebimentoCompra;
    private javax.swing.JPanel jPanelRelatorioInfo;
    private javax.swing.JPanel jPanelRelatorioTables;
    private javax.swing.JPanel jPanelRelatorios;
    private javax.swing.JPanel jPanelSenha;
    private javax.swing.JPanel jPanelTablePg;
    private javax.swing.JPanel jPanelTableVendas;
    private javax.swing.JPanel jPanelValorAreceber;
    private javax.swing.JPasswordField jPassFunCad;
    private javax.swing.JPasswordField jPassSenhaFuncEditar;
    private javax.swing.JRadioButton jRadioADM;
    private javax.swing.JRadioButton jRadioAVista;
    private javax.swing.JRadioButton jRadioAcordo;
    private javax.swing.JRadioButton jRadioBtnComprasPendentes;
    private javax.swing.JRadioButton jRadioBtnDtPg;
    private javax.swing.JRadioButton jRadioBtnDtVenda;
    private javax.swing.JRadioButton jRadioBtnNmCliente;
    private javax.swing.JRadioButton jRadioFunc;
    private javax.swing.JRadioButton jRadioSub;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTable jTableComprasCliente;
    private javax.swing.JTable jTableFuncionario;
    private javax.swing.JTable jTableRelatorioPagamento;
    private javax.swing.JTable jTableRelatorioVendas;
    private javax.swing.JTextPane jTextDescRel;
    private javax.swing.JTextField jTextDtCompra;
    private javax.swing.JTextField jTextDtPgRel;
    private javax.swing.JTextField jTextNomeClienteRel;
    private javax.swing.JTextField jTextNomePesquisa;
    private javax.swing.JTextPane jTextPanelDescriçaoCompra;
    private javax.swing.JTextField jTextTelefoneRel;
    private javax.swing.JTextField jTextValorCompraRel;
    private javax.swing.JTextField jTextValorPgRel;
    private javax.swing.JTextArea jTtxtDescricaoDetalhes;
    private javax.swing.JTextField jTxtEnderecoAlterarClientes;
    private javax.swing.JTextField jTxtEnderecoCadCliente;
    private javax.swing.JTextField jTxtFiltro;
    private javax.swing.JTextField jTxtIDCompra;
    private javax.swing.JTextField jTxtNmUsuarioEditar;
    private javax.swing.JTextField jTxtNomeAlterar;
    private javax.swing.JTextField jTxtNomeCadCliente;
    private javax.swing.JTextField jTxtNomeDetalhesCompra;
    private javax.swing.JTextField jTxtNomeFunEditar;
    private javax.swing.JTextField jTxtNomeFuncCad;
    private javax.swing.JTextField jTxtUserFunCad;
    // End of variables declaration//GEN-END:variables
}
