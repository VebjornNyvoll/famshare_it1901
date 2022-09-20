package gr2260.famshare.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Booking {
    // Class responsible for holding start and end date of a booking, as well as which user created said booking.
    private Item bookedObject;
    private User booker;
    private LocalDate startDate;
    private LocalDate endDate;
    @JsonIgnore
    private List<LocalDate> allDates;
    
    public Booking(Item bookedObject, User booker, LocalDate startDate, LocalDate endDate) {
        this.bookedObject = bookedObject;
        this.booker = booker;
        this.startDate = startDate;
        this.endDate = endDate;
        Stream<LocalDate> dates = startDate.datesUntil(endDate.plusDays(1));
        this.allDates = dates.collect(Collectors.<LocalDate>toList());
    }

    public Item getBookedObject() {
        return bookedObject;
    }

    public User getBooker() {
        return booker;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<LocalDate> getAllDates() {
        return allDates;
    }
    

    
    


}
