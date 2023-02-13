import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class login {
    private JPanel panel1;
    private JTextField txtuser;
    private JPasswordField password;
    private JComboBox comboBox;
    private JLabel titulo;
    private JLabel jlpassword;
    private JLabel user;
    private JButton ingresarButton;


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
                if(txtuser.getText().equals("")||password.equals("") )
                {
                    JOptionPane.showMessageDialog(null,"Los campos estan vacios");

                }
                else {
                    JOptionPane.showMessageDialog(null,"Bienvenido al sistema");
                    if(comboBox.getSelectedItem().equals("Administrador"))
                    {JOptionPane.showMessageDialog(null,"Bienvenido Administrador");
                        JFrame frame=new JFrame("Administrador");
                        frame.setContentPane(new administrador().panel3);
                        frame.setSize(300,300);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.pack();
                        frame.setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(null,"Bienvenido Cajero");
                        JFrame frame=new JFrame("Cajero");
                        frame.setContentPane(new cajero().panel2);
                        frame.setSize(300,300);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.pack();
                        frame.setVisible(true);
                    }
                }
            }
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

    public static void main(String[] args) {
        JFrame frame=new JFrame("Login");
        frame.setContentPane(new login().panel1);
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
