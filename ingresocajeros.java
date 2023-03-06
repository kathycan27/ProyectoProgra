import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ingresocajeros {
    private JTextField txtcedula;
    private JTextField txtnombre;
    private JTextField txtapellido;
    private JTextField txtusuario;
    private JTextField txtclave;
    private JButton crearButton;
    private JTextField txtacceso;
    JPanel panel1;
Connection connection;
Statement statement;
PreparedStatement st;
ResultSet rs;
    public ingresocajeros() {
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connection = getConection();
               if(txtcedula.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Uno varios campos vacios");
                }else {
                    try {

                        statement = connection.createStatement();
                        rs = statement.executeQuery("select * from usuario where codigo=" + txtcedula.getText() + ";");

                        if(rs.next()){

                            JOptionPane.showMessageDialog(null,"Codigo ya ocupado||Producto ya registrado");
                        }else {
                            try {

                                st = connection.prepareStatement("INSERT INTO usuario (acceso,codigo,nombre,apellido,usert,clave) VALUES (?,?,?,?,?,?)");


                                st.setString(1, txtacceso.getText());
                                st.setString(2, txtcedula.getText());
                                st.setString(3, txtnombre.getText());
                                st.setString(4, txtapellido.getText());
                                st.setString(5, txtusuario.getText());
                                st.setString(6, txtclave.getText());


                                int res = st.executeUpdate();

                                if (res > 0) {
                                    JOptionPane.showMessageDialog(null, "Se creo de manera correta");


                                }

                            } catch (HeadlessException | SQLException f) {
                                System.out.println(f);

                            }}}
                    catch (Exception s) {
                        System.out.println(s);
                    }    }}





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
