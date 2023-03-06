import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class cajero {
    JPanel panel2;
    private JTextField txtbuscar;
    private JComboBox comboBox1;
    private JTextField txtcantidad;
    private JButton registrarButton;
    private JButton buscarButton;
    private JTextField txtcedulacli;
    private JTextField txtdireccion;
    private JTextField txtsexo;
    private JLabel txtnombre;
    private JTable table1;
    private JButton comprarButton;
    private JTextField txttel;
    private JTextField txtapellido;
    private JTextField txtproducto;
    private JLabel codigo;
    private JTextField txtcodigo;
    private JTextField txtdisponible;
    private JTextField precio;
    private JButton btnbuscar;
    private JTextField txtstock;
    private JButton verStockButton;
    ResultSet rs;
    Statement st;
    PreparedStatement ps;
    ResultSetMetaData rmd;
    DefaultTableModel model;
    Connection con;
    int columnas;
    Productos productos=new Productos();
    login login=new login();
    int stocktotal;
ArrayList pListaProductos;
private  ArrayList<Productos> productosp;
    public void llenarProductos() {
        con=getConection();

        comboBox1.removeAllItems();
        pListaProductos=getListProductos();

        Iterator iterator = pListaProductos.iterator();
        while (iterator.hasNext()) {
            Productos pProducto = (Productos) iterator.next();
            comboBox1.addItem(pProducto);
        }


    }



    public ArrayList getListProductos()
    {

        ArrayList pProductoP=new ArrayList();
        Productos pProductos=null;
        Statement consulta;
        ResultSet resultado;
        con=getConection();
        try {
            consulta= con.createStatement();
            resultado=consulta.executeQuery("select * from prfrutas");
            while (resultado.next())
            {
                pProductos=new Productos();
                pProductos.setStock(resultado.getInt("stock"));
                pProductos.setProducto(resultado.getString("producto"));
                pProductos.setCodigo(resultado.getInt("codigo"));
                pProductoP.add(pProductos);
            }

        }catch (SQLException e)
        {

        }
        return pProductoP;
    }


    public cajero() {
con=getConection();
pListaProductos=new ArrayList();
llenarProductos();

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox1.getSelectedItem().toString();

            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                 try {
                    con = getConection();
                    st = con.createStatement();

                    rs = st.executeQuery("select * from clientes where cedula=" + txtbuscar.getText() + ";");
                    if(rs.next()){
                        do{
                            txtnombre.setText(rs.getString("nombre"));
                            txtapellido.setText(rs.getString("apellido"));
                            txttel.setText(rs.getString("telefono"));
                            txtdireccion.setText(rs.getString("direccion"));
                            txtsexo.setText(rs.getString("sexo"));
                        }while (rs.next());
                        JOptionPane.showMessageDialog(null,"cliente registrado");
                    }else {
                        JOptionPane.showMessageDialog(null,"cliente aun no ha sido registrado || No se encuentra en la base de datos");
                    }
                } catch (Exception s) {

                }
            }
        });

        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtstock.getText();
                int cantidad;
                int venta= Integer.parseInt(txtcantidad.getText());
                cantidad=stocktotal-venta;
                try{
                    con = getConection();
                    st = con.prepareStatement("UPDATE prfrutas SET stock = ? WHERE producto ="+productos.getCodigo());




                    System.out.println(st);
                    int res = st.executeUpdate();

                    if(res > 0 ){
                        JOptionPane.showMessageDialog(null,"La actualización se realizado con EXITO!");


                    }else{
                        JOptionPane.showMessageDialog(null,"Error, producto no registrado");
                    }
                    con.close();
                }catch (SQLException f){
                    System.out.println(f);
                }
            }
        });


        btnbuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    con = getConection();
                    st = con.createStatement();

                    rs = st.executeQuery("select * from prfrutas where producto=" + productos.getCodigo() + ";");
                    if(rs.next()){
                        do{
                            txtcodigo.setText(rs.getString("codigo"));
                            txtproducto.setText(rs.getString("producto"));
                            txtdisponible.setText(rs.getString("stock"));
                            precio.setText(rs.getString("precio"));
stocktotal= Integer.parseInt(txtstock.getText());
                        }while (rs.next());
                        JOptionPane.showMessageDialog(null,"cliente registrado");
                    }else {
                        JOptionPane.showMessageDialog(null,"cliente aun no ha sido registrado || No se encuentra en la base de datos");
                    }
                } catch (Exception s) {
                    System.out.println(s);
                }
            }
        });
        txtcantidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static Connection getConection() {
        Connection con = null;
        String base = "proyectot";
        String url = "jdbc:mysql://localhost:3307/" + base;
        String user = "root";
        String password = "Luchito2724";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            //System.out.println("si se conecto");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return con;
    }
    public static void main(String[] args) {
        JFrame frame=new JFrame("Login");
        frame.setContentPane(new cajero().panel2);
        frame.setSize(900,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
