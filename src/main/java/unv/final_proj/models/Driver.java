package unv.final_proj.models;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

public class Driver {

    public static MediaRental loadFile(){
        MediaRental sys2 = null;

        // Deserialization
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("RentalSystemState.save.bin");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            sys2 = (MediaRental) in.readObject();

            in.close();
            file.close();

            System.out.println("\nObject has been deserialized \n\n");
        }

        catch(FileNotFoundException ex)
        {
            System.out.println("FileNotFoundException is caught");
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
            ex.printStackTrace();
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

        if ( sys2 == null ){
            sys2 = new MediaRental();
        }

        return sys2;
    }

    public static void main(String[] args) {

        // To use data saved in the file
//        MediaRental m = loadFile();

        // To construct a new empty system
//        MediaRental m2 = new MediaRental();

        // Run Tests



    }

    @Test(expected = IllegalArgumentException.class)
    public void InvalidPlanTest() {

        String illegal_plan = "Unlimited Pro";
        new Customer("ACEB65","Some Name", "Some address", illegal_plan,"0590909454");
    }


    @Test
    public void testFileIO(){

        MediaRental sys = new MediaRental();

        sys.addAlbum("ABC321", "New Album", 6, "John", "song1,song2,song3");
        sys.addAlbum("DEF321", "Second Album", 65, "John", "the long song");
        sys.addAlbum("EEE000", "The Album", 36, "BigSam", "songA,songB,songC");

        sys.addGame("AAA321", "Call Of Duty", 2, 30.6);
        sys.addGame("AAA999", "Need For Speed", 8, 28.1);
        sys.addGame("AAA676", "Rocket League", 327, 40);

        sys.addCustomer("34AB5C","0504328765","Ahmad","Jerusalem","limited" );
        sys.addCustomer("35AB5C","0534328765","Asad","Ramallah","limited" );
        sys.addCustomer("36AB5C","0524328765","Saeed","Jerusalem","unlimited" );

        sys.addToCart("35AB5C", "Call Of Duty");
        sys.addToCart("34AB5C", "Second Album");
        sys.addToCart("34AB5C", "The Album");
        sys.addToCart("34AB5C", "Call Of Duty");
        sys.addToCart("36AB5C", "Call Of Duty");
        sys.addToCart("36AB7D", "Call Of Duty");
        sys.addToCart("35AB5C", "New Game");

        System.out.println(sys.processRequests());

        System.out.println("Data to be written >> \n");

        System.out.println(sys.getAllCustomersInfo());

        System.out.println("\n\n--------------- \n File Was Written, reading data in a new object \n---------------\n\n");

        MediaRental sys2 = loadFile();

        System.out.println("Read Data >> \n");

        System.out.println(sys2.getAllCustomersInfo());

        // Must be same data
        assert sys2.getAllCustomersInfo().equals( sys.getAllCustomersInfo() );

    }

    @Test
    public void CanRentTest() {

        (new MediaRental()).setLimitedPlanLimit(2);

        Customer c = new Customer("38DB5C","Some Name", "Some address", PlanType.LIMITED,"0594328765");

        c.RentMedia(new Game("FCF222", "COD MW2", 2, 23.2));
        assert c.canRent();
        c.RentMedia(new Game("FCF333", "COD MW3", 6, 23.2));
        assert !c.canRent();

        Customer c2 = new Customer("39DB5C","Some Name", "Some address", PlanType.UNLIMITED,"0574328765");

        c2.RentMedia(new Game("FCF333", "COD MW3", 2, 23.2));
        c2.RentMedia(new Game("FCF333", "COD MW3", 2, 23.2));
        c2.RentMedia(new Game("FCF333", "COD MW3", 2, 23.2));
        c2.RentMedia(new Game("FCF333", "COD MW3", 2, 23.2));

        assert c2.canRent();

    }

    @Test
    public void changeLimit() {

        MediaRental sys = new MediaRental();

        sys.addCustomer("4344CFA","0564328765","Ahmad","Jerusalem","limited" );
        sys.addCustomer("5344CFA","0564328765","Asad","Ramallah","limited" );

        sys.addMovie("111FFF", "London Has Fallen", 12, "RB");
        sys.addMovie("888FFF", "Angel Has Fallen", 75, "RB");
        sys.addGame("AAA321", "Call Of Duty", 90, 30.6);

        sys.addToCart("4344CFA", "111FFF");
        sys.addToCart("4344CFA", "111FFF");
        sys.addToCart("4344CFA", "AAA321"); // shouldn't be rented



        String pr1 = sys.processRequests();

        System.out.println(pr1);

        assert ("Sending London Has Fallen to Ahmad\nSending Call Of Duty to Ahmad\n").equals(pr1);

        System.out.println("\n------------------------\n" + sys.getAllCustomersInfo() + "\n\n" );
        System.out.println(sys.getAllMediaInfo() + "\n" );
        System.out.println("\n------------------------\n");

        sys.setLimitedPlanLimit(5);

        MediaRental sys2 = loadFile();

        String pr2 = sys2.processRequests();

        System.out.println(pr2);

        assert "Sending London Has Fallen to Ahmad\n".equals(pr2);

        System.out.println("\n------------------------\n" + sys2.getAllCustomersInfo() + "\n\n" );
        System.out.println(sys2.getAllMediaInfo() + "\n" );
        System.out.println("\n------------------------\n");


    }

    @Test
    public void testAddingCustomers(){
        MediaRental sys = new MediaRental();



        sys.addCustomer("1344CFB","0504328765","Ahmad","Jerusalem","limited" );
        sys.addCustomer("2344CFB","0514328765","Asad","Ramallah","limited" );
        sys.addCustomer("3344CFB","0524328765","Saeed","Jerusalem","unlimited" );
        sys.addCustomer("4344CFB","0534328765","Mohammad","Hebron","limited" );
        sys.addCustomer("5344CFB","0544328765","Rami","Yafa","unlimited" );

        System.out.println("\n------------------------\n" + sys.getAllCustomersInfo() + "\n\n" );
        System.out.println(sys.getAllMediaInfo() + "\n" );
        System.out.println("\n------------------------\n");
    }

    @Test
    public void testAddingMedia(){

        MediaRental sys = new MediaRental();

        sys.addAlbum("ABC321", "New Album", 6, "John", "song1,song2,song3");
        sys.addAlbum("DEF321", "Second Album", 65, "John", "the long song");
        sys.addAlbum("EEE000", "The Album", 36, "BigSam", "songA,songB,songC");

        sys.addGame("AAA321", "Call Of Duty", 90, 30.6);
        sys.addGame("AAA999", "Need For Speed", 8, 28.1);
        sys.addGame("AAA676", "Rocket League", 327, 40);

        sys.addMovie("333FFF", "White House Down", 50, "HQ");
        sys.addMovie("111FFF", "London Has Fallen", 12, "RB");
        sys.addMovie("888FFF", "Angel Has Fallen", 75, "RB");
        sys.addMovie("222FFF", "Hitman", 50, "LD");


        System.out.println("\n------------------------\n" + sys.getAllCustomersInfo() + "\n\n" );
        System.out.println(sys.getAllMediaInfo() + "\n" );
        System.out.println("\n------------------------\n");

    }

    @Test
    public void testingAddingToCart(){

        MediaRental sys = new MediaRental();

        sys.addAlbum("ABC321", "New Album", 6, "John", "song1,song2,song3");
        sys.addAlbum("DEF321", "Second Album", 65, "John", "the long song");
        sys.addAlbum("EEE000", "The Album", 36, "BigSam", "songA,songB,songC");

        sys.addGame("AAA321", "Call Of Duty", 90, 30.6);
        sys.addGame("AAA999", "Need For Speed", 8, 28.1);
        sys.addGame("AAA676", "Rocket League", 327, 40);

        sys.addCustomer("D34444","0564328765","Ahmad","Jerusalem","limited" );
        sys.addCustomer("D5344C","0524328765","Asad","Ramallah","limited" );
        sys.addCustomer("D934CC","0534328765","Saeed","Jerusalem","unlimited" );

        assert sys.addToCart("D5344C", "AAA321");
        assert !sys.addToCart("E53485C", "AAA321"); // non existing ID
        assert !sys.addToCart("D5344C", "0909090");

        System.out.println("\n------------------------\n" + sys.getAllCustomersInfo() + "\n\n" );
        System.out.println(sys.getAllMediaInfo() + "\n" );
        System.out.println("\n------------------------\n");

    }


    @Test
    public void testingRemovingFromCart(){

        MediaRental sys = new MediaRental();

        sys.addAlbum("ABC321", "New Album", 6, "John", "song1,song2,song3");
        sys.addAlbum("DEF321", "Second Album", 65, "John", "the long song");
        sys.addAlbum("EEE000", "The Album", 36, "BigSam", "songA,songB,songC");

        sys.addGame("AAA321", "Call Of Duty", 2, 30.6);
        sys.addGame("AAA999", "Need For Speed", 8, 28.1);
        sys.addGame("AAA676", "Rocket League", 327, 40);

        sys.addCustomer("5444CFA","0504328765","Ahmad","Jerusalem","limited" );
        sys.addCustomer("5644CFA","0578328765","Asad","Ramallah","limited" );
        sys.addCustomer("5844CFA","0535328765","Saeed","Jerusalem","unlimited" );

        sys.addToCart("5644CFA", "Call Of Duty");

        sys.removeFromCart("5644CFA", "Call Of Duty");

        sys.addToCart("5444CFA", "DEF321");
        sys.addToCart("5444CFA", "EEE000");
        sys.addToCart("5444CFA", "AAA321");
        sys.addToCart("5844CFA", "AAA321");

        sys.addToCart("0000", "AAA321");

        sys.removeFromCart("0000", "AAA321");

        sys.addToCart("5644CFA", "0909090");

        System.out.println("\n------------------------\n" + sys.getAllCustomersInfo() + "\n\n" );
        System.out.println(sys.getAllMediaInfo() + "\n" );
        System.out.println("\n------------------------\n");

        System.out.println(sys.processRequests());

        System.out.println("\n------------------------\n" + sys.getAllCustomersInfo() + "\n\n" );
        System.out.println(sys.getAllMediaInfo() + "\n" );
        System.out.println("\n------------------------\n");

    }

    @Test
    public void testProcessingRequestsOne(){

        MediaRental sys = new MediaRental();

        sys.addAlbum("ABC321", "New Album", 6, "John", "song1,song2,song3");
        sys.addAlbum("DEF321", "Second Album", 65, "John", "the long song");
        sys.addAlbum("EEE000", "The Album", 36, "BigSam", "songA,songB,songC");

        sys.addGame("AAA321", "Call Of Duty", 2, 30.6);
        sys.addGame("AAA999", "Need For Speed", 8, 28.1);
        sys.addGame("AAA676", "Rocket League", 327, 40);

        sys.addCustomer("EEC4CFA","0564428765","Ahmad","Jerusalem","limited" );
        sys.addCustomer("EEC4CFB","0564728765","Asad","Ramallah","limited" );
        sys.addCustomer("EEC4CFC","0564828765","Saeed","Jerusalem","unlimited" );

        sys.addToCart("EEC4CFB", "AAA321");
        sys.addToCart("EEC4CFA", "DEF321");
        sys.addToCart("EEC4CFA", "EEE000");
        sys.addToCart("EEC4CFA", "AAA321");
        sys.addToCart("EEC4CFC", "AAA321");
        sys.addToCart("0000000", "AAA321");
        sys.addToCart("EEC4CFB", "0909090");

        System.out.println("\n------------------------\n" + sys.getAllCustomersInfo() + "\n\n" );
        System.out.println(sys.getAllMediaInfo() + "\n" );
        System.out.println("\n------------------------\n");

        System.out.println(sys.processRequests());

        System.out.println("\n------------------------\n" + sys.getAllCustomersInfo() + "\n\n" );
        System.out.println(sys.getAllMediaInfo() + "\n" );
        System.out.println("\n------------------------\n");
    }

    @Test
    public void testProcessingRequestsTwo(){

        MediaRental sys = loadFile();

        sys.addAlbum("ABC321", "New Album", 6, "John", "song1,song2,song3");
        sys.addAlbum("DEF321", "Second Album", 65, "John", "the long song");
        sys.addAlbum("EEE000", "The Album", 36, "BigSam", "songA,songB,songC");

        sys.addGame("AAA321", "Call Of Duty", 2, 30.6);
        sys.addGame("AAA999", "Need For Speed", 8, 28.1);
        sys.addGame("AAA676", "Rocket League", 327, 40);

        sys.addCustomer("EBC4CFC","0594828765","Ahmad","Jerusalem","limited" );
        sys.addCustomer("EAC4CFC","0584828765","Asad","Ramallah","limited" );
        sys.addCustomer("ECC4CFC","0504828765","Saeed","Jerusalem","unlimited" );

        sys.addToCart("EAC4CFC", "AAA321");
        sys.addToCart("EBC4CFC", "DEF321");
        sys.addToCart("EBC4CFC", "EEE000");
        sys.addToCart("EBC4CFC", "AAA321");
        sys.addToCart("ECC4CFC", "AAA321");
        sys.addToCart("0000000", "AAA321");
        sys.addToCart("EAC4CFC", "09090909");

        System.out.println(sys.getAllCustomersInfo() + "\n\n" );

        System.out.println(sys.processRequests());

        System.out.println(sys.getAllCustomersInfo());

    }

    @Test
    public void testReturnMedia(){

        MediaRental sys = new MediaRental();

        sys.addAlbum("ABC321", "New Album", 6, "John", "song1,song2,song3");
        sys.addAlbum("DEF321", "Second Album", 65, "John", "the long song");
        sys.addAlbum("EEE000", "The Album", 36, "BigSam", "songA,songB,songC");

        sys.addGame("AAA321", "Call Of Duty", 2, 30.6);
        sys.addGame("AAA999", "Need For Speed", 8, 28.1);
        sys.addGame("AAA676", "Rocket League", 327, 40);

        sys.addCustomer("AAC4CFC","0574828765","Ahmad","Jerusalem","limited" );
        sys.addCustomer("ABC4CFC","0584828765","Asad","Ramallah","limited" );
        sys.addCustomer("BBC4CFC","0594828765","Saeed","Jerusalem","unlimited" );

        sys.addToCart("EAC4CFC", "AAA321");
        sys.addToCart("EBC4CFC", "DEF321");
        sys.addToCart("EBC4CFC", "EEE000");
        sys.addToCart("EBC4CFC", "AAA321");
        sys.addToCart("ECC4CFC", "AAA321");
        sys.addToCart("0000000", "AAA321");
        sys.addToCart("EAC4CFC", "09090909");

        System.out.println(sys.processRequests());

        System.out.println("\n------------------------\n" + sys.getAllCustomersInfo() + "\n\n" );
        System.out.println(sys.getAllMediaInfo() + "\n" );
        System.out.println("\n------------------------\n");

        sys.returnMedia("AAC4CFC", "Second Album");
        sys.returnMedia("BBC4CFC", "Call Of Duty");
        sys.returnMedia("0000000", "Second Album");

        System.out.println("\n------------------------\n" + sys.getAllCustomersInfo() + "\n\n" );
        System.out.println(sys.getAllMediaInfo() + "\n" );
        System.out.println("\n------------------------\n");


    }


    @Test
    public void testSearchMedia(){

        MediaRental sys = new MediaRental();

        sys.addAlbum("ABC321", "New Album", 6, "John", "song1,song2,song3");
        sys.addAlbum("DEF321", "Second Album", 65, "John", "the long song");
        sys.addAlbum("EEE000", "The Album", 36, "BigSam", "songA,songB,song1");

        sys.addGame("AAA321", "Call Of Duty", 90, 30.6);
        sys.addGame("AAA999", "Need For Speed", 8, 28.1);
        sys.addGame("AAA676", "Rocket League", 327, 40);

        sys.addMovie("333FFF", "White House Down", 50, "HQ");
        sys.addMovie("111FFF", "London Has Fallen", 12, "RB");
        sys.addMovie("888FFF", "Angel Has Fallen", 75, "RB");
        sys.addMovie("222FFF", "Hitman", 50, "LD");

        ArrayList<String> search = sys.searchMedia(null,null,null,null);

        assert search.size() == 10;

        ArrayList<String> search2 = sys.searchMedia("White House Down",null,null,null);

        assert search2.size() == 1;
        assert search2.get(0).equals("White House Down");

        ArrayList<String> search3 = sys.searchMedia("Non Existing thing",null,null,null);

        assert search3.size() == 0;

        ArrayList<String> search4 = sys.searchMedia(null,null,"John",null);

        assert search4.size() == 2;
        assert search4.get(1).equals("Second Album");

        ArrayList<String> search5 = sys.searchMedia(null,"L",null,null);

        assert search5.size() == 0;

        ArrayList<String> search6 = sys.searchMedia(null,"RB",null,null);

        assert search6.size() == 2;
        assert search6.get(1).equals("Angel Has Fallen");
        assert search6.get(0).equals("London Has Fallen");

        ArrayList<String> search7 = sys.searchMedia("New Album",null,"John",null);

        assert search7.size() == 1;
        assert search7.get(0).equals("New Album");

        ArrayList<String> search8 = sys.searchMedia("New Album",null,"SAM",null);

        assert search8.size() == 0;

        ArrayList<String> search9 = sys.searchMedia("New Album","RB",null,null);

        assert search9.size() == 0;

        ArrayList<String> search10 = sys.searchMedia(null,null,null,"song1");

        assert search10.size() == 2;
        assert search10.get(0).equals("New Album");

        ArrayList<String> search11 = sys.searchMedia("New Album",null,"John","song Y");

        assert search11.size() == 0;
    }
}
