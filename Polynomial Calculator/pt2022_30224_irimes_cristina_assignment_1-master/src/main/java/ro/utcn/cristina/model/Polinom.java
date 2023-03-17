package ro.utcn.cristina.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polinom {
    private ArrayList<Monom> m = new ArrayList<>();

    public Polinom(ArrayList<Monom> m) {
        this.m = m;
    }

    public Polinom(String s) {
        if (s.charAt(0) != '+' || s.charAt(0) != '-')
            s = "+" + s;
        Pattern pattern = Pattern.compile("([+-][0-9]*)([xX]*([\\^][0-9]+)*)");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            if (!matcher.group(0).isEmpty()) {
                float coef = 0;
                int grad = 0;
                if (matcher.group(1).equals("+") || matcher.group(1).isEmpty()) {
                    if (!matcher.group(2).equals(""))
                        coef = 1;
                } else {
                    if (matcher.group(1).equals("-")) {
                        coef = -1;
                    } else {
                        coef = Float.parseFloat(matcher.group(1));
                    }
                }
                if (matcher.group(3) == null) {
                    if (matcher.group(2).length() == 1) {
                        grad = 1;
                    } else {
                        grad = 0;
                    }
                } else {
                    grad = Integer.parseInt(matcher.group(3).substring(1, matcher.group(3).length()));
                }
                m.add(new Monom(grad, coef));
            }
        }
    }

    public ArrayList<Monom> getM() {
        return m;
    }

    public void setM(ArrayList<Monom> m) {
        this.m = m;
    }

    public String toString() {
        int ok = 0;
        for (Monom mono : m) {
            if (mono.getCoef() != 0)
                ok = 1;
        }
        if (ok == 1) {
            String s = "";
            Collections.sort(m);
            for (Monom mono : m)
                s = s + "" + mono.toString();
            return s;
        } else
            return "0";
    }


}
