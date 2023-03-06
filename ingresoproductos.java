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
    private JPanel panelp;
    private JButton salirButton;
    Connection connection;
    Statement statement;
    PreparedStatement st;
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

                                st = connection.prepareStatement("INSERT INTO prfrutas (codigo,producto,stock,precio) VALUES (?,?,?,?)");


                                st.setString(1, txtcodigo.getText());
                                st.setString(2, txtproducto.getText());
                                st.setString(3, txtstock.getText());
                                st.setString(4, txtprecio.getText());


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
