package ro.utcn.cristina.ui;

import ro.utcn.cristina.operations.Operatii;
import ro.utcn.cristina.model.Monom;
import ro.utcn.cristina.model.Polinom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Controller {
    private View i;
    private Operatii op;

    public Controller(View i) {
        this.i = i;
        this.op = new Operatii();

        i.adunareActionListener(new adunareActionListener());
        i.scadereActionListener(new scadereActionListener());
        i.inmActionListener(new inmActionListener());
        i.impActionListener(new impActionListener());
        i.derivActionListener(new derivActionListener());
        i.inteActionListener(new inteActionListener());
        i.exitActionListener(new exitActionListener());
    }

    public Polinom verificare(String s) throws Exception {
        Polinom p = null;
        int ok = 0;

        p = new Polinom(s);
        if (s.equals("0"))
            return p;
        for (Monom m : p.getM()) {
            if (m.getCoef() != 0)
                ok = 1;
        }
        if (ok == 0)
            throw new Exception();

        return p;
    }

    class adunareActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Polinom p1 = null;
            Polinom p2 = null;
            try {
                p1 = verificare(i.getPol1());
                p2 = verificare(i.getPol2());
                i.setRez(op.adunare(p1, p2).toString());
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Unul sau mai multe polinoame introduse necorespunzator");
                JOptionPane.showMessageDialog(null, "Nu se poate face adunarea");
            }

        }
    }

    class scadereActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Polinom p1 = null;
            Polinom p2 = null;
            try {
                p1 = verificare(i.getPol1());
                p2 = verificare(i.getPol2());
                i.setRez(op.scadere(p1, p2).toString());
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Unul sau mai multe polinoame introduse necorespunzator");
                JOptionPane.showMessageDialog(null, "Nu se poate face scaderea");
            }
        }
    }

    class inmActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Polinom p1 = null;
            Polinom p2 = null;
            try {
                p1 = verificare(i.getPol1());
                p2 = verificare(i.getPol2());
                i.setRez(op.inmultire(p1, p2).toString());
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Unul sau mai multe polinoame introduse necorespunzator");
                JOptionPane.showMessageDialog(null, "Nu se poate face inmultirea");
            }
        }
    }

    class impActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Polinom p1 = null;
            Polinom p2 = null;
            try {
                p1 = verificare(i.getPol1());
                p2 = verificare(i.getPol2());
                i.setRez(op.impartire(p1, p2).get(0).toString());
                i.setRest(op.impartire(p1, p2).get(1).toString());
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Unul sau mai multe polinoame introduse necorespunzator");
                JOptionPane.showMessageDialog(null, "Nu se poate face impartire");
            }
        }
    }

    class derivActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Polinom p1 = null;
            try {
                p1 = verificare(i.getPol1());
                i.setRez(op.derivare(p1).toString());
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Polinom introdus necorespunzator");
                JOptionPane.showMessageDialog(null, "Nu se poate face derivare");
            }
        }
    }

    class inteActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Polinom p1 = null;
            try {
                p1 = verificare(i.getPol1());
                i.setRez(op.integrare(p1).toString());
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Polinom introdus necorespunzator");
                JOptionPane.showMessageDialog(null, "Nu se poate integra");
            }
        }
    }

    class exitActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            i.close();
        }
    }

}