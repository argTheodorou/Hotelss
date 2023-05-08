//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.Serializable;
import java.util.ArrayList;

public class Hotel implements Serializable , Comparable{
    private String name;
    private ArrayList<Reservation> reservations = new ArrayList<>();

    public Hotel(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Object o) {
        Hotel otherHotel = (Hotel) o;
        return this.name.compareTo(otherHotel.name);
    }

    public void addReservation(Reservation r){
        reservations.add(r);
    }

    public int calcTotalCost(){
        int sum = 0;
        for(Reservation reserve: reservations)
            sum += reserve.calculateCost();

        return sum;
    }
}
