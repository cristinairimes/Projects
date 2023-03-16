package ro.utcn.cristina.model;

public class Client implements Comparable<Client>{
    private int ID;
    private int timpSosire;
    private int timpProcesare;


    public Client(int ID, int timpSosire, int timpProcesare) {
        this.ID = ID;
        this.timpSosire = timpSosire;
        this.timpProcesare = timpProcesare;
    }

    @Override
    public int compareTo(Client t) {
        if(this.getTimpSosire()>t.getTimpSosire())
            return -1;
        else if(this.getTimpSosire()<t.getTimpSosire())
            return 1;
        else
        return 0;
    }

    public int getTimpProcesare() {
        return timpProcesare;
    }

    public void setTimpProcesare(int timpProcesare) {
        this.timpProcesare = timpProcesare;
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTimpSosire() {
        return timpSosire;
    }

    public void setTimpSosire(int timpSosire) {
        this.timpSosire = timpSosire;
    }
}
