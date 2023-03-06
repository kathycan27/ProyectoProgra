import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;

public class login {
    private JPanel panel1;
    private JTextField txtuser;
    private JPasswordField txtclave;
    private JComboBox comboBox;
    private JLabel titulo;
    private JLabel jlpassword;
    private JLabel user;
    private JButton ingresarButton;
    Connection con;
    Statement statement;
    ResultSet rs;
    int acces;
    String clave;
    String clavetxt ;

    public login() {

        panel1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Image image=new ImageIcon(getClass().getResource("")).getImage();
                ImageIcon imageIcon=new ImageIcon(image.getScaledInstance(300,300,Image.SCALE_SMOOTH));

            }
        });
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection;
                if(txtuser.getText().equals("")|| txtclave.equals("") )
                {
                    JOptionPane.showMessageDialog(null,"Los campos estan vacios");

                }
                else {
                    if(comboBox.getSelectedItem().equals("Administrador"))
                    {

                        try {
                            con = getConection();

                            statement = con.createStatement();

                            rs = statement.executeQuery("SELECT * FROM usuario WHERE codigo ="  + txtuser.getText()+";");
                            if(rs.next()){
                                acces= Integer.parseInt(rs.getString("acceso"));
                                clave=rs.getString("clave");
                                clavetxt = txtclave.getText();
                               // System.out.println(clavetxt);

                                System.out.println(clave);
                                if(clavetxt.equals(clave))
                                {
                                    if(acces==0)
                                    {
                                        JFrame frame=new JFrame("Cajero");
                                        frame.setContentPane(new cajero().panel2);
                                        frame.setSize(300,300);
                                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        frame.pack();
                                        frame.setVisible(true);
                                    }else {
                                        JOptionPane.showMessageDialog(null,"usuario no registrado");
                                    }
                                }
                                else {
                                   JOptionPane.showMessageDialog(null,"clave incorrecta");
                                }

                            }else {
                                JOptionPane.showMessageDialog(null,"No se encuentra en la base de datos");
                            }
                        } catch (Exception s) {
                            System.out.println("ERROE "+ s);
                        }


                    }else
                        if(comboBox.getSelectedItem().equals("Cajero"))
                        {

                            try {
                                con = getConection();

                                statement = con.createStatement();

                                rs = statement.executeQuery("SELECT * FROM prfrutas WHERE producto ="  + txtuser.getText()+";");
                                if(rs.next()){
                                    acces= Integer.parseInt(rs.getString("acceso"));
                                    clave=rs.getString("clave");
                                    clavetxt = txtclave.getText();
                                    // System.out.println(clavetxt);

                                    //System.out.println(clave);
                                    if(clavetxt.equals(clave))
                                    {
                                        if(acces==1)
                                        {
                                            System.out.println("bienvenido");
                                        }else {
                                            JOptionPane.showMessageDialog(null,"usuario no registrado");
                                        }
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(null,"clave incorrecta");
                                    }

                                }else {
                                    JOptionPane.showMessageDialog(null,"No se encuentra en la base de datos");
                                }
                            } catch (Exception s) {
                                System.out.println("ERROE "+ s);
                            }


                        } else {
                            JOptionPane.showMessageDialog(null,"DEBE SELECCIONAR UN CARGO");
                        }
                }}

        });
        txtuser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        comboBox.addActionListener(new ActionListener() {
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
            System.out.println("si se conecto");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return con;
    }
    public static void main(String[] args) {
        JFrame frame=new JFrame("Login");
        frame.setContentPane(new login().panel1);
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
