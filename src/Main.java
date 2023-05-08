//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Hotel h1 = new Hotel("Bellevue");
        Hotel h2 = new Hotel("Hermes");
        Hotel h3 = new Hotel("CityHotel");
        Hotel h4 = new Hotel("Panorama");
        ArrayList<Hotel> hotels = new ArrayList();
        hotels.add(h1);
        hotels.add(h2);
        hotels.add(h3);
        hotels.add(h4);

        try {
            FileOutputStream fileOut = new FileOutputStream("hotels.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(hotels);

            out.close();
            fileOut.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        new InputFrame();
    }
}
