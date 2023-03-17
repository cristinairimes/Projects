package ro.utcn.cristina.operations;

import ro.utcn.cristina.model.Monom;
import ro.utcn.cristina.model.Polinom;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Operatii {

    public static Polinom adunare(Polinom p1, Polinom p2) {
        ArrayList<Monom> list = new ArrayList<Monom>();
        for (Monom m1 : p1.getM()) {
            int k = 1;
            for (Monom m2 : p2.getM()) {
                if (m1.getGrad() == m2.getGrad()) {
                    list.add(new Monom(m1.getGrad(), m1.getCoef() + m2.getCoef()));
                    k = 0;
                }
            }
            if (k == 1)
                list.add(m1);
        }
        for (Monom m2 : p2.getM()) {
            int k = 1;
            for (Monom m1 : p1.getM()) {
                if (m1.getGrad() == m2.getGrad()) {
                    k = 0;
                }
            }
            if (k == 1) {
                list.add(new Monom(m2.getGrad(), m2.getCoef()));
            }
        }
        return new Polinom(list);
    }

    public static Polinom scadere(Polinom p1, Polinom p2) {
        ArrayList<Monom> list = new ArrayList<Monom>();
        for (Monom m1 : p1.getM()) {
            int k = 1;
            for (Monom m2 : p2.getM()) {
                if (m1.getGrad() == m2.getGrad()) {
                    list.add(new Monom(m1.getGrad(), m1.getCoef() - m2.getCoef()));
                    k = 0;
                }
            }
            if (k == 1)
                list.add(m1);
        }
        for (Monom m2 : p2.getM()) {
            int k = 1;
            for (Monom m1 : p1.getM()) {
                if (m1.getGrad() == m2.getGrad()) {
                    k = 0;
                }
            }
            if (k == 1) {
                list.add(new Monom(m2.getGrad(), -1 * m2.getCoef()));
            }
        }
        return new Polinom(list);
    }

    public static Polinom inmultire(Polinom p1, Polinom p2) {
        ArrayList<Monom> list = new ArrayList<Monom>();
        Polinom p = new Polinom(list);
        int k = 0;
        for (Monom m1 : p1.getM()) {
            for (Monom m2 : p2.getM()) {
                int grad = m1.getGrad() + m2.getGrad();
                float coef = m1.getCoef() * m2.getCoef();
                for (Monom s : list) {
                    if (grad == s.getGrad()) {
                        s.setCoef(coef + s.getCoef());
                        k = 1;
                    }
                }
                if (k == 0) {
                    list.add(new Monom(grad, coef));
                }
                k = 0;
            }
        }
        p = new Polinom(list);
        return p;
    }

    public static ArrayList<Polinom> impartire(Polinom p1, Polinom p2) {
        if (p2.toString().equals("0")) {
            JOptionPane.showMessageDialog(null, "Nu se poate face impartire");
            return null;
        } else {
            Collections.sort(p1.getM());
            Collections.sort(p2.getM());

            ArrayList<Monom> list1 = new ArrayList<Monom>();
            Polinom cat = new Polinom(list1);
            Polinom rest = p1;

            while (!rest.getM().isEmpty() && rest.getM().get(0).getGrad() >= p2.getM().get(0).getGrad()) {
                float coef = rest.getM().get(0).getCoef() / p2.getM().get(0).getCoef();
                int exp = rest.getM().get(0).getGrad() - p2.getM().get(0).getGrad();
                ArrayList<Monom> list = new ArrayList<Monom>();
                list.add(new Monom(exp, coef));
                Polinom newPol = new Polinom(list);
                cat = Operatii.adunare(cat, newPol);
                rest = Operatii.scadere(rest, Operatii.inmultire(newPol, p2));
                rest.getM().get(0).setGrad(exp);
                if(p2.getM().get(0).getGrad()==0)
                    break;
            }
            ArrayList<Polinom> arr = new ArrayList<Polinom>();
            arr.add(cat);
            arr.add(rest);
            return arr;

        }
    }

    public static Polinom derivare(Polinom p1) {
        ArrayList<Monom> list = new ArrayList<Monom>();
        Polinom p = null;
        for (Monom m1 : p1.getM()) {
            list.add(new Monom(m1.getGrad() - 1, m1.getCoef() * m1.getGrad()));
        }
        p = new Polinom(list);
        return p;
    }

    public static Polinom integrare(Polinom p1) {
        ArrayList<Monom> list = new ArrayList<Monom>();
        Polinom p = null;
        for (Monom m1 : p1.getM()) {
            list.add(new Monom(m1.getGrad() + 1, m1.getCoef() / (m1.getGrad() + 1)));
        }
        p = new Polinom(list);
        return p;
    }
}
