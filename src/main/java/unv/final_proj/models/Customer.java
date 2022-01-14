package unv.final_proj.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

enum PlanType {
    LIMITED,
    UNLIMITED
}

public class Customer implements Serializable {

    public static int limited_plan_limit = 2;

    private String name, address;
    private PlanType plan;
    private String id, mobile;

    private ArrayList<Media> rented, cart;

    public Customer(String id, String name, String address, PlanType plan, String mobile){
        this.name = name;
        this.address = address;
        this.plan = plan;
        this.id = id;
        this.mobile = mobile;

        this.rented = new ArrayList<Media>();
        this.cart = new ArrayList<Media>();
    }

    public Customer(String id, String name, String address, String plan, String mobile) throws IllegalArgumentException {
        this.name = name;
        this.address = address;
        this.plan = PlanType.valueOf(plan.toUpperCase());
        this.id = id;
        this.mobile = mobile;

        this.rented = new ArrayList<Media>();
        this.cart = new ArrayList<Media>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean canRent(){
        if( plan.equals(PlanType.LIMITED) ) {
            return rented.size() < limited_plan_limit;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public PlanType getPlan() {
        return plan;
    }

    public void RentMedia(Media m){
        if ( this.canRent() && m.available() ){
            rented.add(m);
        }
//        TODO throw exception on failure
    }

    public void AddToCart(Media m){
        cart.add(m);
    }
    public boolean RemoveFromCart(String media_code){

        int index = searchMediaByCode(media_code, this.cart);
        if ( index >= 0 ){
            this.cart.remove(index);
            return true;
        }
        return false;

    }

    public String proccess_requests(){

        String res = "";
        for (int i = 0; i < cart.size(); i++) {
            Media m = cart.get(i);
            if ( m.available() && this.canRent() ){
                m.rent_media();
                this.rented.add(m);
                res += "Sending " + m.getTitle() + " to " + this.getName() + "\n";
                this.cart.remove(m);
            }

        }
        return res;
    }

    public boolean ReturnMedia(String media_code){

        int index = searchMediaByCode(media_code, this.rented);
        if ( index >= 0 ){
            this.rented.get(index).return_media();
            this.rented.remove(index);
            return true;
        }
        return false;


    }

    private int searchMediaByCode(String Code, ArrayList<Media> media){

        return Collections.binarySearch(media, new Media(Code, "", 0), Comparator.comparing(Media::getCode));

    }

    public int count_rented(){
        return rented.size();
    }

    @Override
    public String toString() {
        return "Customer{\n\t" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", plan=" + plan +
                ", \n\trented=" + rented +
                ", \n\tcart=  " + cart +
                "}\n";
    }
}
