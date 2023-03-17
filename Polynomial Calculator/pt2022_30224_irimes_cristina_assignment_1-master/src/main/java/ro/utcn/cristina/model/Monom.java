package ro.utcn.cristina.model;


public class Monom implements Comparable<Monom> {
    private int grad;
    private float coef;

    public Monom(int grad, float coef) {
        this.coef=coef;
        this.grad=grad;
    }

    public int getGrad() {
        return grad;
    }

    public void setGrad(int grad) {
        this.grad = grad;
    }

    public float getCoef() {
        return coef;
    }

    public void setCoef(float coef) {
        this.coef = coef;
    }

    public String toString() {
        if(this.coef>0)
            return "+"+this.coef+"x^"+this.grad;
        else
        if(this.coef<0)
            return this.coef+"x^"+this.grad;
        else
            return "";
    }

    @Override
    public int compareTo(Monom m) {
        return m.getGrad()-this.getGrad();
    }

}


