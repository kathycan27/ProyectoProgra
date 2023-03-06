import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
    private JTextField txtdireccion;
    private JLabel txtsexo;
    private JLabel txtnombre;
    private JButton comprarButton;
    private JLabel txttel;
    private JLabel txtapellido;
    private JLabel txtproducto;
    private JLabel codigo;
    private JLabel txtcodigo;
    private JLabel txtdisponible;
    private JTextField precio;
    private JButton btnbuscar;
    private JLabel codigos;
    private JLabel txtcedula;
    private JLabel txtprecio;
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
    float total=0;
    int stocktotal;
    int codigogeneral;
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

String co= String.valueOf(productos.getCodigo());
    public cajero() {
con=getConection();
pListaProductos=new ArrayList();
llenarProductos();

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox1.getSelectedItem();
                //co= String.valueOf(productos.getCodigo());
                codigos.setText(String.valueOf(comboBox1.getSelectedIndex()));

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
                txtcantidad.getText();
                int cantidad= Integer.parseInt(txtdisponible.getText());
                int venta= Integer.parseInt(txtcantidad.getText());
                int cambiostock=cantidad-venta;
                float costo= Float.parseFloat(txtprecio.getText());
                total=costo*venta;
                System.out.println(total);

                System.out.println(cambiostock);
                try{
                 con = getConection();
                 if(cantidad>=venta){
                    ps = con.prepareStatement("UPDATE prfrutas SET stock = ? WHERE codigo ="+codigos.getText());

                    ps.setString(1, String.valueOf(cambiostock));
                    int res = ps.executeUpdate();
                     try {

                         ps = con.prepareStatement("INSERT INTO carrito(codigo,producto,venta,preciou,preciop) VALUES (?,?,?,?,?)");


                         ps.setString(1, txtcodigo.getText());
                         ps.setString(2, txtproducto.getText());
                         ps.setString(3, txtcantidad.getText());
                         ps.setString(4, txtprecio.getText());
                         ps.setString(5, String.valueOf(total));


                         int res1 = ps.executeUpdate();

                         if (res1 > 0) {
                             JOptionPane.showMessageDialog(null, "Se creo de manera correta");
                             txtcodigo.setText("");
                             txtnombre.setText("");
                             txtprecio.setText("");
                             txtcantidad.setText("");
                         }

                     } catch (HeadlessException | SQLException f) {
                         System.out.println(f);

                     }


                    if(res > 0 ) {
                        JOptionPane.showMessageDialog(null, "PRODUCTO AGREGADO AL CARRITO!");


                    }else{
                    }}
                 else {
                     JOptionPane.showMessageDialog(null,"NO HAY SUFICIENTE STOCK");

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

                    rs = st.executeQuery("select * from prfrutas where codigo=" + codigos.getText()+ ";");
                    if(rs.next()){
                        do{
                            txtcodigo.setText(rs.getString("codigo"));
                            txtproducto.setText(rs.getString("producto"));
                            txtdisponible.setText(rs.getString("stock"));
                            txtprecio.setText(rs.getString("precio"));
                            codigos.setText(rs.getString("codigo"));
stocktotal= Integer.parseInt(txtstock.getText());
codigogeneral= Integer.parseInt(txtcodigo.getText());
                        }while (rs.next());

                    }else {
                        JOptionPane.showMessageDialog(null," No se encuentra en la base de datos");
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
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame("REGISTRO CLIENTES");
                frame.setContentPane(new ingresoclientes().jpanelcli);
                frame.setSize(900,900);

                frame.pack();
                frame.setVisible(true);
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
