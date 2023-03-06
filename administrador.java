import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class administrador {
    JPanel panel3;
    private JButton INGRESARPRODUCTOButton;
    private JButton INGRESARCAJEROButton;
    private JButton REVISARVENTASButton;
    private JButton regresarAlInicioButton;

    public administrador() {
        INGRESARCAJEROButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame("ingresar cajeros");
                frame.setContentPane(new ingresocajeros().panel1);
                frame.setSize(300,300);

                frame.pack();
                frame.setVisible(true);
            }
        });
        INGRESARPRODUCTOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame("ingreso productos");
                frame.setContentPane(new ingresoclientes().jpanelcli);
                frame.setSize(900,900);

                frame.pack();
                frame.setVisible(true);
            }
        });
        regresarAlInicioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame("INICIO");
                frame.setContentPane(new login().panel1);
                frame.setSize(1900,1900);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame=new JFrame("Servicio de administracion");
        frame.setContentPane(new administrador().panel3);
        frame.setSize(900,900);

        frame.pack();
        frame.setVisible(true);

    }
}
