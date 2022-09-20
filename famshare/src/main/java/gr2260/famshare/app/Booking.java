package gr2260.famshare.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Booking {
    // Class responsible for holding start and end date of a booking, as well as which user created said booking.
    private Item bookedObject;
    private User booker;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<LocalDate> allDates;

    public Booking() {
    }

    // Sets start and end dates and fills allDates list with all dates between start and end date.
    public void setDates(LocalDate startDate, LocalDate endDate){
        setStartDate(startDate);
        setEndDate(endDate);
        Stream<LocalDate> dates = startDate.datesUntil(endDate.plusDays(1));
        this.allDates = dates.collect(Collectors.<LocalDate>toList());
    }

    public void setBookedObject(Item bookedObject) {
        if(bookedObject == null){
            throw new IllegalStateException("bookedObject cannot be null");
        }
        this.bookedObject = bookedObject;
    }

    public void setBooker(User booker) {
        if(booker == null){
            throw new IllegalStateException("booker cannot be null");
        }
        this.booker = booker;
    }

    // Ensures that the startDate is not before the current date by checking users localtime.
    private void setStartDate(LocalDate startDate) {
        if(startDate.isBefore(LocalDate.now())){
            throw new IllegalStateException("Can't book dates that have passed!");
        }
        this.startDate = startDate;
    }

    private void setEndDate(LocalDate endDate) {
        if(this.startDate == null || endDate.isBefore(startDate)){
            throw new IllegalStateException("endDate must be set after startDate and must be a date equal to or later than startDate");
        }
        this.endDate = endDate;
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
        List<LocalDate> allDatesCopy = new ArrayList<>(allDates);
        return allDatesCopy;
    }
    
    


}
