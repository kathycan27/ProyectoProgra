import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ingresoproductos {
    private JTextField txtproducto;
    private JTextField txtstock;
    private JTextField txtprecio;
    private JButton registrarButton;
    private JTextField txtcodigo;
    JPanel panelp;
    private JButton salirButton;
    private JButton buscarButton;
    private JButton actualizarButton;
    Connection connection;
    Statement statement;
    PreparedStatement ps;
    ResultSet rs;

    public ingresoproductos() {

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connection = getConection();
                if (txtcodigo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Uno varios campos vacios");
                } else {
                    try {

                        statement = connection.createStatement();
                        rs = statement.executeQuery("select * from prfrutas where codigo=" + txtcodigo.getText() + ";");

                        if (rs.next()) {

                            JOptionPane.showMessageDialog(null, "Codigo ya ocupado||Producto ya registrado");
                        } else {
                            try {

                                ps = connection.prepareStatement("INSERT INTO prfrutas (codigo,producto,stock,precio) VALUES (?,?,?,?)");


                                ps.setString(1, txtcodigo.getText());
                                ps.setString(2, txtproducto.getText());
                                ps.setString(3, txtstock.getText());
                                ps.setString(4, txtprecio.getText());


                                int res = ps.executeUpdate();

                                if (res > 0) {
                                    JOptionPane.showMessageDialog(null, "Se creo de manera correta");


                                }

                            } catch (HeadlessException | SQLException f) {
                                System.out.println(f);

                            }
                        }
                    } catch (Exception s) {
                        System.out.println(s);
                    }
                }
            }


        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    JFrame frame=new JFrame("Login");
                    frame.setContentPane(new administrador().panel3);
                    frame.setSize(900,900);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);

            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connection = getConection();
                if (txtcodigo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Uno varios campos vacios");
                } else {
                    try {

                        statement = connection.createStatement();
                        rs = statement.executeQuery("select * from prfrutas where codigo=" + txtcodigo.getText() + ";");


                            if(rs.next()){
                                do{
                                    txtcodigo.setText(rs.getString("codigo"));
                                    txtproducto.setText(rs.getString("producto"));
                                    txtstock.setText(rs.getString("stock"));
                                    txtprecio.setText(rs.getString("precio"));


                                }while (rs.next());
                        } else {


                            System.out.println("e");
                        }
                    } catch (Exception s) {
                        System.out.println(s);
                    }
                }
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    connection=getConection();
                    ps = connection.prepareStatement("UPDATE prfrutas SET stock = ?, precio=?  WHERE codigo =" + txtcodigo.getText());

                    ps.setString(1, txtstock.getText());
                    ps.setString(2, txtprecio.getText());

                    int res = ps.executeUpdate();

                    if(res > 0 ){
                        JOptionPane.showMessageDialog(null,"La actualización se realizado con EXITO!");
                        txtcodigo.setText("");
                        txtproducto.setText("");
                        txtprecio.setText("");
                        txtstock.setText("");

                    }else{
                        JOptionPane.showMessageDialog(null,"Error, producto no registrado");
                    }
                    connection.close();


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
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
            System.out.println("si se conecto");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return con;


    }

}
