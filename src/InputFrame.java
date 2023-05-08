//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class InputFrame extends JFrame {
    private JPanel panel = new JPanel();
    private JPanel diamoniPanel = new JPanel();
    private JPanel allInclusivePanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel diamoniLabel;
    private JLabel hotelLabel;
    private JLabel allInclusiveLabel;
    private JList listView = new JList();
    private DefaultListModel model = new DefaultListModel();
    private JTextField daysField;
    private JTextField mealsField;
    private JButton storeButton;
    private JButton calculateCostButton;
    private JTextField costField;

    private ArrayList <Hotel> hotels;

    public InputFrame() {


        try {
            FileInputStream fileIn = new FileInputStream("hotels.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            hotels = (ArrayList<Hotel>) in.readObject();

            in.close();
            fileIn.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



//        this.model.addElement("Dummy Hotel Name 1");
//        this.model.addElement("Dummy Hotel Name 2");
//        this.model.addElement("Dummy Hotel Name 3");
//        this.model.addElement("Dummy Hotel Name 4");

        Collections.sort(hotels);

        for (Hotel hotel:hotels){
            String hotelName = hotel.getName();
            model.addElement(hotelName);
        }

        this.listView.setModel(this.model);




        this.diamoniLabel = new JLabel("Stoixeia Diamonis");
        this.hotelLabel = new JLabel("Hotel");
        this.allInclusiveLabel = new JLabel("AllInclusive");
        this.daysField = new JTextField("Hmeres Diamonis");
        this.mealsField = new JTextField("Plithos Geumatwn (1,2,3)");
        this.costField = new JTextField("Synoliko Kostos");
        this.storeButton = new JButton("Apothikeusi Kratisis");
        this.calculateCostButton = new JButton("Ypologismos Kostous");
        this.diamoniPanel.setLayout(new BoxLayout(this.diamoniPanel, 1));
        this.diamoniLabel.setAlignmentX(0.5F);
        this.diamoniPanel.add(this.diamoniLabel);
        this.hotelLabel.setAlignmentX(0.5F);
        this.diamoniPanel.add(this.hotelLabel);
        this.diamoniPanel.add(this.listView);
        this.diamoniPanel.add(this.daysField);
        this.diamoniPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.allInclusivePanel.setLayout(new GridLayout(2, 0));
        this.allInclusivePanel.add(this.allInclusiveLabel);
        this.allInclusivePanel.add(this.mealsField);
        this.allInclusivePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.buttonPanel.setLayout(new GridLayout(2, 0));
        this.buttonPanel.add(this.storeButton);
        this.buttonPanel.add(this.calculateCostButton);
        this.buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.panel.add(this.diamoniPanel);
        this.panel.add(this.allInclusivePanel);
        this.panel.add(this.buttonPanel);
        this.panel.add(this.costField);



        ButtonListener listener = new ButtonListener();
        storeButton.addActionListener(listener);
        calculateCostButton.addActionListener(listener);





        this.setContentPane(this.panel);
        this.setVisible(true);
        this.setSize(200, 320);
        this.setLocation(200, 0);
        this.setTitle("Input");
        this.setDefaultCloseOperation(3);
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String selectedHotelName = (String) listView.getSelectedValue();
            Hotel selectedHotel = null;
            for (Hotel hotel : hotels)
                if (hotel.getName().equals(selectedHotelName))
                    selectedHotel = hotel;

            if (selectedHotel == null) {
                JOptionPane.showMessageDialog(null, "No hotel selected");
            }


            if (e.getSource().equals(storeButton)) {
                //STORE BUTTON PRESSED
                String daysString = daysField.getText();
                int days = Integer.parseInt(daysString);
                String mealsString = mealsField.getText();


                Reservation reserve;
                if (mealsString.equals("")) {
                    reserve = new Reservation(days);
                } else {
                    int meals = Integer.parseInt(mealsString);
                    reserve = new AllInclusive(days, meals);

                }

                selectedHotel.addReservation(reserve);
                System.out.println("Reservation Stored to hotel: " + selectedHotelName);


            }
            else {
                //CALCULATE COST BUTTON CLICKED
                int totalCost = selectedHotel.calcTotalCost();
                String totalCostText = Integer.toString(totalCost);
                costField.setText(totalCostText);

















            }
        }
    }
}
