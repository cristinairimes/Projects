package ro.utcn.cristina.operations;

import org.junit.Test;
import ro.utcn.cristina.model.Monom;
import ro.utcn.cristina.model.Polinom;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Test1 {
    //p1=3x^3+2x^2+1x^1+1
    //p2=2x^2+1
    Polinom p1=new Polinom(new ArrayList<Monom>() {
        {add(new Monom(3,3));
            add(new Monom(2,2));
            add(new Monom(1,1));
            add(new Monom(0,1));
        }
    });
    Polinom p2=new Polinom(new ArrayList<Monom>() {
        {
            add(new Monom(2,2));
            add(new Monom(0,1));
        }
    });
    Polinom rezultat;


    @Test

    public void testAdunare() {
        assertEquals("+3.0x^3+4.0x^2+1.0x^1+2.0x^0",Operatii.adunare(p1,p2).toString());
    }

    @Test
    public void testScadere() {
        rezultat=Operatii.scadere(p1,p2);
        assertEquals(rezultat.toString(),"+3.0x^3+1.0x^1");
    }

    @Test
    public void testInmultire() {
        assertEquals(Operatii.inmultire(p1,p2).toString(),"+6.0x^5+4.0x^4+5.0x^3+4.0x^2+1.0x^1+1.0x^0");
    }

    @Test
    public void testImpartire() {
        assertEquals(Operatii.impartire(p2,p1).get(0).toString(),"0");
        assertEquals(Operatii.impartire(p2,p1).get(1).toString(),"+2.0x^2+1.0x^0");
    }

    @Test
    public void testDerivare() {
        rezultat=Operatii.derivare(p1);
        assertEquals(rezultat.toString(),"+9.0x^2+4.0x^1+1.0x^0");
    }

    @Test
    public void testIntegrare() {
        rezultat=Operatii.integrare(p2);
        assertEquals(rezultat.toString(),"+0.6666667x^3+1.0x^1");
    }

    @Test
    public void testParsare1() {
        Polinom p=new Polinom("3x^3+2x^2+1x^1+1");
        assertEquals(p1.toString(),p.toString());
    }

    @Test
    public void testParsare2() {
        Polinom p=new Polinom("2x^2+1");
        assertEquals(p2.toString(),p.toString());
    }


}