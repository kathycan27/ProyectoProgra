import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class compra{
    JPanel panel1;
    private JTextField txtdinero;
    private JLabel txttotalp;
    private JLabel txtsubtotal;
    private JLabel txtvuelto;


    public compra() {
        cajero venta= new cajero();

        txttotalp.setText(String.valueOf(venta.pasoproducto));
        System.out.println(txttotalp.getText());

        txttotalp.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        txtsubtotal.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

            }
        });
        txtdinero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtdinero.getText();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame=new JFrame("Fin compra");
        frame.setContentPane(new compra().panel1);
        frame.setSize(900,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
