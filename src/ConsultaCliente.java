import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;

/**
 *
 * @author luciana
 */
public class ConsultaCliente extends JFrame {

    private ClientesDAO clientes;
    private ResultSet objetoResultSet;
    private JLabel label1;
    private JTextField TextFieldSQL;
    private JButton btExec, btCodigoCliente, btSair;
    private JScrollPane scrollTable;
    private JTable table;
    //private JPanel panel;
    private PreparedStatement objetoPreparedStatement;
    private String vSQL;
    public JFrame frame1;

    public void inicializacomp() {
        JFrame frame1 = new ConsultaCliente();

        JPanel panel = new JPanel();
        frame1.add(panel);

        setLayout(null);

        frame1.setSize(600, 600);
        setBounds(300, 10, 750, 600);
        frame1.setTitle("Consultas");

        frame1.setResizable(true);

        btExec = new JButton("Ordem alfabética");
        btCodigoCliente = new JButton("Ordem do Código do Cliente");
        btSair = new JButton("Sair");
        scrollTable = new JScrollPane();

        panel.add(scrollTable);
        panel.add(btExec);
        panel.add(btCodigoCliente);
        panel.add(btSair);
        frame1.pack();
        frame1.setVisible(true);
    }

    public void windowOpened() {
        try {
            clientes = new ClientesDAO();
            if (!clientes.objetoConexaoBD.getConnection()) {
                JOptionPane.showMessageDialog(null, "Falha na conexão!");
                System.exit(0);

            }

            vSQL = "select * from clientes";

            objetoPreparedStatement = clientes.objetoConexaoBD.objetoConnection.prepareStatement(vSQL);
            objetoResultSet = objetoPreparedStatement.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();

            int qtcolunas = objetoResultSet.getMetaData().getColumnCount();
            for (int indice = 1; indice <= qtcolunas; indice++) {
                tableModel.addColumn(objetoResultSet.getMetaData().getColumnName(indice));
            }
            table = new JTable(tableModel);

            table.getColumnModel().getColumn(0).setPreferredWidth(100);
            table.getColumnModel().getColumn(1).setPreferredWidth(800);

            while (objetoResultSet.next()) {
                try {
                    String[] dados = new String[qtcolunas];
                    for (int i = 1; i <= qtcolunas; i++) {
                        dados[i - 1] = objetoResultSet.getString(i);
                    }
                    tableModel.addRow(dados);
                    System.out.println();

                } catch (SQLException erro) {

                }
                scrollTable.setViewportView(table);

            }
            objetoResultSet.close();
            objetoPreparedStatement.close();
            clientes.objetoConexaoBD.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Comando SQL inválido!" + erro.toString());
        }

    }

    public void defineEvento() {

        btExec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vSQL = "select * from clientes order by Nome";
                carregaTable();
            }
        });
        btCodigoCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vSQL = "select * from clientes order by codcli";
                carregaTable();
            }
        });

        btSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                //clientes.bd.close();
                //setVisible(false);
                // dispose();
                //Runtime.getRuntime().exit(0);
            }
        });
        /*        try{
                         clientes = new ClientesDAO();
                    if (!clientes.bd.g0etConnection()){
                         JOptionPane.showMessageDialog(null,"Falha na conexão!");
                         System.exit(0);  
                              
                    }
                    
                  
                    vSQL = "select * from cliente order by RA";
                   
                    statement = clientes.bd.connection.prepareStatement(vSQL);
                    resultSet = statement.executeQuery();
                                     
                    DefaultTableModel tableModel = new DefaultTableModel();
                  
                    int qtcolunas = resultSet.getMetaData().getColumnCount();
                    for (int indice=1;indice<=qtcolunas;indice++){
                        tableModel.addColumn(resultSet.getMetaData().getColumnName(indice));
                    }
                    table = new JTable(tableModel);
                    
                    table.getColumnModel().getColumn(0).setPreferredWidth(10);
                    table.getColumnModel().getColumn(1).setPreferredWidth(50);
                    
                    
                    while (resultSet.next()){
                        try{
                        String[] dados = new String[qtcolunas];
                        for (int i=1; i<=qtcolunas;i++){
                        dados[i-1]= resultSet.getString(i);                                             
                        }
                        tableModel.addRow(dados);
                        System.out.println();
                        
                    }
                        catch (SQLException erro){
                            
                        }
                    scrollTable.setViewportView(table);
                    
                    
                    }
                    resultSet.close();
                    statement.close();
                    clientes.bd.close();
                    
                    }
                    catch (Exception erro){
                      JOptionPane.showMessageDialog(null,"Comando SQL inválido!"+erro.toString());  
                    }
                }
            });
        
     
 }*/
    }

    public void carregaTable() {

        try {
            clientes = new ClientesDAO();
            if (!clientes.objetoConexaoBD.getConnection()) {
                JOptionPane.showMessageDialog(null, "Falha na conexão!");
                System.exit(0);

            }

            //vSQL = "select * from cliente order by Nome";
            objetoPreparedStatement = clientes.objetoConexaoBD.objetoConnection.prepareStatement(vSQL);
            objetoResultSet = objetoPreparedStatement.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();

            int qtcolunas = objetoResultSet.getMetaData().getColumnCount();
            for (int indice = 1; indice <= qtcolunas; indice++) {
                tableModel.addColumn(objetoResultSet.getMetaData().getColumnName(indice));
            }
            table = new JTable(tableModel);

            table.getColumnModel().getColumn(0).setPreferredWidth(100);
            table.getColumnModel().getColumn(1).setPreferredWidth(800);

            while (objetoResultSet.next()) {
                try {
                    String[] dados = new String[qtcolunas];
                    for (int i = 1; i <= qtcolunas; i++) {
                        dados[i - 1] = objetoResultSet.getString(i);
                    }
                    tableModel.addRow(dados);
                    System.out.println();

                } catch (SQLException erro) {

                }
                scrollTable.setViewportView(table);

            }
            objetoResultSet.close();
            objetoPreparedStatement.close();
            clientes.objetoConexaoBD.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Comando SQL inválido!" + erro.toString());
        }
    }
}
