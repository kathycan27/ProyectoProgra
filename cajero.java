import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private JButton generarReporteButton;
    private JButton finalizarCompraButton;

    private JButton verStockButton;
    ResultSet rs;
    Statement st;
    PreparedStatement ps;
    ResultSetMetaData rmd;
    DefaultTableModel model;
    int i=0;
    Connection con;
    int columnas;
    int totalproductos,pasoproducto;
    float sumaproductos, pasoventa;
    Productos productos=new Productos();
    login login=new login();
    float total=0;
    int stocktotal;
String nombrecliente, direccioncli, telefonocli, cedulacli;
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
                            txtcedula.setText(rs.getString("cedula"));
                            txtsexo.setText(rs.getString("sexo"));

                        }while (rs.next());
                        JOptionPane.showMessageDialog(null,"cliente registrado");
                        String datoscliente1=txtnombre.getText();
                        String datoscliente2=txtapellido.getText();
                        nombrecliente=datoscliente1+" "+datoscliente2;
                        cedulacli=txtcedula.getText();
                        direccioncli=txtdireccion.getText();
                        telefonocli= txttel.getText();
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
stocktotal= Integer.parseInt(txtdisponible.getText());
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
        generarReporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document= new Document();
                 int j=2;
                try {
                    String ruta=System.getProperty("user.home");
                    PdfWriter.getInstance(document, new FileOutputStream(ruta +"/Desktop/reporte.pdf"));
                    document.open();
                    int numf=1;


                    LocalDate hoy = LocalDate.now();
                    LocalTime ahora = LocalTime.now();
                    LocalDateTime fecha = LocalDateTime.of(hoy, ahora);
                    Paragraph fech= new Paragraph(String.valueOf(fecha));
                    Paragraph tienda=new Paragraph("FACTURA:"+numf+"\nMinimarket Don Gato"+"\nRuc:0000000000"+
                            "\nDireccion: Sangolqui"+"\nTelefonos: 1800Dongato "+"\n--------------------------------" +
                            ""+"\nNombre: "+nombrecliente+"\nRuc/CI: "
                             +cedulacli+"\nDireccion: "+direccioncli+"\nTelefóno: "+telefonocli);
                    tienda.setFont(FontFactory.getFont("Tahoma", 18, Font.NORMAL, BaseColor.DARK_GRAY));
                    PdfPTable encabezado= new PdfPTable(2);
                    encabezado.getDefaultCell().setBorder(Rectangle.NO_BORDER);

                    com.itextpdf.text.Image header = Image.getInstance("imagenes/dongato.png");
                    // Tamaño
                    header.scaleAbsolute(15,20);
                    fech.setAlignment(Element.ALIGN_RIGHT);
                    header.setAlignment(Element.ALIGN_CENTER);
                    tienda.setAlignment(Element.ALIGN_CENTER);
                    encabezado.addCell(tienda);
                    encabezado.addCell(header);

                        document.add(fech);
                        document.add(encabezado);
                        document.add(new Paragraph("\n"));
                    PdfPTable table= new PdfPTable(5);
                    table.addCell("codigo");
                    table.addCell("producto");
                    table.addCell("unidades");
                    table.addCell("precio_uni");
                    table.addCell("precio_total");

                    try
                    {
                      con=getConection();
                      ps=con.prepareStatement("select * from carrito");

                     rs=ps.executeQuery();
    if(rs.next())
    {
        do {


            table.addCell(rs.getString(1));
            table.addCell(rs.getString(2));
            table.addCell(rs.getString(3));
            table.addCell(rs.getString(4));
            table.addCell(rs.getString(5));


            totalproductos= Integer.parseInt(rs.getString("venta"));
            pasoproducto= pasoproducto+totalproductos;
            sumaproductos= Float.parseFloat(rs.getString("preciop"));
            pasoventa=pasoventa+sumaproductos;






        }while (rs.next());

        System.out.println(pasoproducto+"costo total"+pasoventa);
      document.add(table);
    }
}catch (SQLException f)
{
    System.out.println(f);
}
document.close();
JOptionPane.showMessageDialog(null,"creado");


                }catch (HeadlessException | DocumentException | IOException exception)
                {
                    System.out.println(exception);
                }
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
