import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ingresoclientes {
    private JTextField txtcedula;
    private JTextField txttel;
    private JTextField txtnombre;
    private JTextField txtapellido;
    private JTextField txtdireccion;
    private JTextField txtgenero;
    private JButton guardarClienteButton;
    JPanel jpanelcli;
    Connection connection;
    Statement statement;
    PreparedStatement st;
    ResultSet rs;

    public ingresoclientes() {
        guardarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                        connection = getConection();
                        if (txtcedula.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Uno varios campos vacios");
                        } else {
                            try {

                                statement = connection.createStatement();
                                rs = statement.executeQuery("select * from clientes where cedula=" + txtcedula.getText() + ";");

                                if (rs.next()) {

                                    JOptionPane.showMessageDialog(null, "Codigo ya ocupado||Producto ya registrado");
                                } else {
                                    try {

                                        st = connection.prepareStatement("INSERT INTO clientes (cedula,nombre,apellido,telefono, direccion, sexo) VALUES (?,?,?,?,?,?)");


                                        st.setString(1, txtcedula.getText());
                                        st.setString(2, txtnombre.getText());
                                        st.setString(3, txtapellido.getText());
                                        st.setString(4, txttel.getText());
                                        st.setString(5, txtdireccion.getText());
                                        st.setString(6, txtgenero.getText());


                                        int res = st.executeUpdate();

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
