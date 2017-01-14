package book.clonable_test;

/**
 * Created by lagger on 09.01.2017.
 */
public class Apartment {

    private int apartmentNumber;
    private int numberOfRooms;
    private String name;

    public Apartment(int apartmentNumber, int numberOfRooms, String name) {
        this.apartmentNumber = apartmentNumber;
        this.numberOfRooms = numberOfRooms;
        this.name = name;
    }

    public Apartment() {
        this(0,0,new String(""));
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public String getName() {
        return name;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    @Override
    public boolean equals(Object obj){
        if (obj != null && obj instanceof Apartment) {
            Apartment a = (Apartment) obj;
            return a.name.equals(this.name) && a.numberOfRooms == this.numberOfRooms && a.apartmentNumber == this.apartmentNumber;
        } else throw new IllegalArgumentException("ass");
    }

}
