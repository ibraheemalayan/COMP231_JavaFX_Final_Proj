package unv.final_proj.models;

import java.io.Serializable;

public class Media implements Comparable<Media>, Serializable {

//    private static final long serialVersionUID = 628789568975888436L;

    protected String title, Code;
    protected int num_of_available_copies;

    public Media(String Code, String title, int num_of_available_copies) {
        this.title = title;
        this.Code = Code;
        this.num_of_available_copies = num_of_available_copies;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        this.Code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean available() {
        return num_of_available_copies > 0;
    }

    public void rent_media() {
        this.num_of_available_copies -= 1;
    }

    public void return_media() {
        this.num_of_available_copies += 1;
    }

    @Override
    public int compareTo(Media o) {
        return this.getCode().compareTo( ( (Media) o ).getCode() );
    }

    @Override
    public boolean equals(Object o){

        if (! (o instanceof Media) ){
            throw new ClassCastException("Given object cannot be compared with a media object");
        }

        return this.compareTo( (Media) o ) == 0;
    }

}
