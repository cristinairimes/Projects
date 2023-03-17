package ro.utcn.cristina.ui;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class View {
    private JFrame f;
    private JTextField pol1;
    private JTextField pol2;
    private JLabel rez;
    private JLabel rest;

    private Button adunare;
    private Button scadere;
    private Button inm;
    private Button imp;
    private Button deriv;
    private Button inte;
    private Button exit;

    public View() {
        f=new JFrame("Calculator de polinoame");
        f.getContentPane().setBackground(new Color(255, 235, 205));

        JLabel poli1=new JLabel("Primul polinom:");
        poli1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        poli1.setBounds(10,50,140,40);
        f.getContentPane().add(poli1);
        pol1=new JTextField("");
        pol1.setBounds(121,50,169,40);
        f.getContentPane().add(pol1);

        JLabel poli2=new JLabel("Al doilea polinom:");
        poli2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        poli2.setBounds(10,100,140,40);
        f.getContentPane().add(poli2);
        pol2=new JTextField("");
        pol2.setBounds(121,100,169,40);
        f.getContentPane().add(pol2);

        JLabel rezu=new JLabel("Rezultat:");
        rezu.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        rezu.setBounds(10,150,140,40);
        f.getContentPane().add(rezu);
        rez=new JLabel ("");
        rez.setBounds(150,150,140,40);
        f.getContentPane().add(rez);

        JLabel res=new JLabel("Rest:");
        res.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        res.setBounds(10,200,140,40);
        f.getContentPane().add(res);
        rest=new JLabel ("0");
        rest.setBounds(150,200,140,40);
        f.getContentPane().add(rest);

        adunare=new Button("Adunare");
        adunare.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        adunare.setBackground(new Color(240, 128, 128));
        adunare.setBounds(25,250,100,40);
        f.getContentPane().add(adunare);

        scadere= new Button("Scadere");
        scadere.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        scadere.setBackground(new Color(233, 150, 122));
        scadere.setBounds(150,250,100,40);
        f.getContentPane().add(scadere);

        inm= new Button("Inmultire");
        inm.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        inm.setBackground(new Color(255, 182, 193));
        inm.setBounds(25,310,100,40);
        f.getContentPane().add(inm);

        imp= new Button("Impartire");
        imp.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        imp.setBackground(new Color(216, 191, 216));
        imp.setBounds(150,310,100,40);
        f.getContentPane().add(imp);

        inte= new Button("Integrare");
        inte.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        inte.setBackground(new Color(210, 180, 140));
        inte.setBounds(25,370,100,40);
        f.getContentPane().add(inte);

        deriv= new Button("Derivare");
        deriv.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        deriv.setBackground(new Color(173, 216, 230));
        deriv.setBounds(150,370,100,40);
        f.getContentPane().add(deriv);

        exit= new Button("Exit");
        exit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        exit.setBackground(new Color(102, 205, 170));
        exit.setBounds(90,430,100,40);
        f.getContentPane().add(exit);


        f.setSize(315,520);
        f.getContentPane().setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setResizable(false);

    }

    public String getPol1() {
        return pol1.getText();
    }

    public String getPol2() {
        return pol2.getText();
    }


    public void setRez(String rez) {
        this.rez.setText(rez);
    }

    public void setRest(String rez) {
        this.rest.setText(rez);
    }

    public void adunareActionListener(ActionListener actionListener) {
        adunare.addActionListener(actionListener);
    }
    public void scadereActionListener(ActionListener actionListener) {
        scadere.addActionListener(actionListener);
    }
    public void inmActionListener(ActionListener actionListener) {
        inm.addActionListener(actionListener);
    }
    public void impActionListener(ActionListener actionListener) {
        imp.addActionListener(actionListener);
    }
    public void derivActionListener(ActionListener actionListener) {
        deriv.addActionListener(actionListener);
    }
    public void inteActionListener(ActionListener actionListener) {
        inte.addActionListener(actionListener);
    }
    public void exitActionListener(ActionListener actionListener) {
        exit.addActionListener(actionListener);
    }

    public void close() {
        f.dispose();
    }

}
