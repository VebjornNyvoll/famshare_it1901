# Release 1

In this release we've implemented the first and most basic version of the program.

## Features:
 - Build runs in Gitpod and utilizes JavaFX. It's managed using Maven.
 - The GUI presents the user a list of items that can be booked.
 - Users can add a new booking from the list of items.
    - The app ensures no item can be booked more than once within any timeframe.
    - The app also ensures no items can be booked before the current date. (localtime)
- Loading previous bookings from a .json file and writing new bookings to said file.
- A list showing the user previous bookings.
    - Shows which item is booked, who booked it (currently only "dummy user"),  
    and which dates the item is booked.
- Tests utilize the Maven Surefire Report plugin and current test coverage is XX%.
