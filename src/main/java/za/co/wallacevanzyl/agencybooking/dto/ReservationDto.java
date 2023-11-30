package za.co.wallacevanzyl.agencybooking.dto;


import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;
import java.util.Date;

@XmlRootElement(name = "reservationdto")
public class ReservationDto {

    private String firstName;

    private String lastName;

    private BigInteger contactNumber;

    private BigInteger idNumber;

    private int roomNumber;

    private Date checkIn;

    private Date checkOut;

    public ReservationDto() {
    }

    public ReservationDto(String firstName, String lastName, BigInteger contactNumber, BigInteger idNumber, int roomNumber, Date checkIn, Date checkOut) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.idNumber = idNumber;
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigInteger getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(BigInteger contactNumber) {
        this.contactNumber = contactNumber;
    }

    public BigInteger getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(BigInteger idNumber) {
        this.idNumber = idNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

}
