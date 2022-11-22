# core

**Core** contains the domain logic, with the following classes: 

**core**-pakke:
- Booking class contains all the entries in a given booking, this includes Booker, bookedobjects and dates.

- Calendar class contains all the bookings in a given calendar, it is used for rounding up all the diffrent bookings
such that the other modules have an easier time handling it.

- Item class contains all the information about a given item, this includes the name, description and an internal itemid.

- ItemList class is a container for items and contains methods for adding and removing items. 

- User class contains a the users name and an internal userid with it's items.

Core module also contains the **famshare.json** package for storage. This package consists of serializers and deserializers for all classes. These are collected in CalendarModule, which can be used by to store calendars in Json format. This module is also used in restserver module to serialize/deserialize classes from core.


![Classdiagram TODO]()