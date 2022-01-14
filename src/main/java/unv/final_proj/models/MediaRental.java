package unv.final_proj.models;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MediaRental implements MediaRentalInt, Serializable {


//    private static final long serialVersionUID = 628789568975888036L;

    //    This ArrayList is sorted by media title ascending
    //    Do NOT add to it except throw the addMedia method
    private ArrayList<Media> media;


    //    This ArrayList is sorted by customer name ascending
    //    Do NOT add to it except throw the addCustomer method
    private ArrayList<Customer> customers;

    public MediaRental() {
        customers = new ArrayList<Customer>();
        media = new ArrayList<Media>();
    }

    private void update_file(){
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try{
            fout = new FileOutputStream("RentalSystemState.save" );
            oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(oos != null){
                try {
                    oos.close();
                    fout.close();
                } catch (IOException e) {
                    System.out.println("Unable To Close Writer");
                }
            }
        }
    }

    /**
     * Adds the specified customer to the database. The address is a physical address (not e-mail). The plan options available
     * are: LIMITED and UNLIMITED. LIMITED defines a default maximum of two media that can be rented.
     *
     * @param name
     * @param address
     * @param plan
     */
    @Override
    public void addCustomer(String id, String mobile, String name, String address, String plan) {

        Customer new_c = null;

        try {
            new_c = new Customer(id , name, address, plan, mobile);
        }catch (IllegalArgumentException e){
            System.err.println("Illegal Plan Type Given to MediaRental.addCustomer");
            return;
        }

        int index = Collections.binarySearch(customers, new_c, Comparator.comparing(Customer::getId));
        if (index < 0) {
            index = -index - 1;
        }else{
            // Customer Already Exist
            System.out.println("Customer with this code already exists");
            return;
        }

        customers.add(index, new_c);

        update_file();

    }

    private Customer searchCustomerByID(String ID){

        int index = Collections.binarySearch(customers, new Customer(ID, "", "", "limited", ""), Comparator.comparing(Customer::getId));

        if ( index < 0 ){
            return null; // not found
        }

        return customers.get(index);
    }

    private Media searchMediaByCode(String Code){

        int index = Collections.binarySearch(media, new Media(Code, "", 0), Comparator.comparing(Media::getCode));

        if ( index < 0 ){
            return null; // not found
        }

        return media.get(index);
    }

    private void addMedia(Media m) {

        int index = Collections.binarySearch(media, m, Comparator.comparing(Media::getCode));
        if (index < 0) {
            index = -index - 1;
        }

        media.add(index, m);

        update_file();

    }

    /**
     * Adds the specified movie to the database. The possible values for rating are "DR", "HR", "AC".
     *
     * @param title
     * @param copiesAvailable
     * @param rating
     */
    @Override
    public void addMovie(String code, String title, int copiesAvailable, String rating) {

        Movie new_m = new Movie(code, title, copiesAvailable, rating);

        addMedia(new_m);

    }

    /**
     * Adds the specified game to the database.
     *
     * @param title
     * @param copiesAvailable
     * @param weight
     */
    @Override
    public void addGame(String code, String title, int copiesAvailable, double weight) {

        Game new_g = new Game(code, title, copiesAvailable, weight);

        addMedia(new_g);

    }

    /**
     * Adds the specified album to the database.
     * The songs String includes a list of the title of songs on the album
     * (song titles are separated by commas).
     *
     * @param title
     * @param copiesAvailable
     * @param artist
     * @param songs
     */
    @Override
    public void addAlbum(String code, String title, int copiesAvailable, String artist, String songs) {

        Album new_a = new Album(code, title, copiesAvailable, artist, songs);

        addMedia(new_a);

    }

    /**
     * This set the number of media associated with the LIMITED plan.
     *
     * @param value
     */
    @Override
    public void setLimitedPlanLimit(int value) {
        Customer.limited_plan_limit = value;
        update_file(); // TODO check if object writer saves static fields
    }

    /**
     * @return information about the customers in the database. The information is presented sorted by customer name.
     */
    @Override
    public String getAllCustomersInfo() {

        String res = "";

        for ( Customer c : customers ) {
            res += "\n" + c ;

        }

        return res;
    }

    /**
     * @return information about all the media (movies, albums, and games) that are part of the database
     * The information is presented sorted by media title.
     */
    @Override
    public String getAllMediaInfo() {

        String res = "";

        for ( Media m : media ) {
            res += "\n" + m ;

        }

        return res;

    }

    /**
     * Adds the specified media title to the cart associated with a customer.
     *
     * @param customerID
     * @param mediaCode
     * @return false if the mediaCode is already part of the cart (it will not be added)
     */
    @Override
    public boolean addToCart(String customerID, String mediaCode) {

        Customer c = searchCustomerByID(customerID);
        if ( c == null ) {
            return false;
        }

        Media m = searchMediaByCode(mediaCode);

        if ( m == null ) {
            return false;
        }

        c.AddToCart(m);

        update_file();

        return true;
    }

    /**
     * Removes the specified media title from the customer's cart.
     *
     * @param customerID
     * @param mediaCode
     * @return false if removal failed for any reason (e.g., customerName not found)
     */
    @Override
    public boolean removeFromCart(String customerID, String mediaCode) {

        Customer c = searchCustomerByID(customerID);
        if ( c == null ) {
            return false;
        }

        if ( c.RemoveFromCart(mediaCode) ) {
            update_file();
            return true;
        }

        return false;
    }

    /**
     * Processes the requests cart of each customer.
     * The customers will be processed in alphabetical order.
     * For each customer, the requests cart will be checked and media will be added to the rented cart,
     * if the plan associated with the customer allows it, and if there is a copy of the media available.
     * For UNLIMITED plans the media will be added to the rented cart always,
     * as long as there are copies associated with the media available.
     * For LIMITED plans,
     * the number of entries moved from the requests cart to the rented cart will depend on the number of currently rented media,
     * and whether copies associated with the media are available.
     * For each media that is rented, the following message will be generated:
     * "Sending [mediaCode] to [customerName]"
     *
     * @return the message log
     */
    @Override
    public String processRequests() {

        String res = "";

        for ( Customer c : customers ) {

            res += c.proccess_requests();
        }

        update_file();

        return res;

    }

    /**
     * This is how a customer returns a rented media. This method will remove the item from the rented cart and adjust any
     * other values that are necessary (e.g., copiesAvailable)
     *
     * @param customerID
     * @param mediaCode
     * @return
     */
    @Override
    public boolean returnMedia(String customerID, String mediaCode) {


        Customer c = searchCustomerByID(customerID);
        if ( c == null ) {
            return false;
        }

        if ( c.ReturnMedia(mediaCode) ) {
            update_file();
            return true;
        }

        return false;
    }

    /**
     * @param title
     * @param rating
     * @param artist
     * @param songs
     * @return a SORTED ArrayList with media titles that satisfy the provided parameter values. If null is specified for a parameter,
     * then that parameter should be ignore in the search. Providing null for all parameters will return all media titles.
     */
    @Override
    public ArrayList<String> searchMedia(String title, String rating, String artist, String songs) {
        ArrayList<String> res = new ArrayList<String>();

        for ( Media m : media ) {

            boolean add = true;

            if ( title != null && !m.getTitle().equals(title) ){
                add = false;
            }

            if ( rating != null && !( m instanceof Movie ) ){
                add = false;
            }

            if ( rating != null && ( m instanceof Movie ) && !((Movie) m).getRating().equals(rating) ){
                add = false;
            }

            if ( artist != null && !( m instanceof Album ) ){
                add = false;
            }

            if ( artist != null && ( m instanceof Album ) && !((Album) m).getArtist().equals(artist) ){
                add = false;
            }

            if ( songs != null && !( m instanceof Album ) ){
                add = false;
            }

            if ( songs != null && ( m instanceof Album ) && ! ((Album) m).getSongs().contains(songs) ){
                add = false;
            }

            if (add){
                res.add(m.getTitle());
            }

        }


        return res;
    }
}