🏨# HotelReservationSystem
A Core Java + JDBC + MySQL Console Application

This project is a console-based Hotel Reservation System designed to simplify basic hotel room management.
It allows managers to reserve rooms, cancel bookings, track availability, update guest information, and more — all through a user-friendly text-based interface.

The goal of this project is to demonstrate practical knowledge of:
        ✔ Core Java
        ✔ JDBC
        ✔ MySQL
        ✔ CRUD Operations
        ✔ Menu-driven console programs
        ✔ Basic data validation

🌟Key Features:
🛏️1. Reserve a Room
      Enter guest details (name & contact number)
      Choose room number (0–50)
      System checks availability
      Saves reservation to MySQL database

❌2. Cancel a Reservation:
      Enter reservation ID
      System deletes the booking
      Marks the room as available again

🔍3. View All Reserved Rooms:
      Displays all rooms currently occupied
      Helps manager quickly identify which rooms are booked

🟩4. View All Available Rooms:
      Shows rooms that are free (0–50)
      Helps in quick allocation for new guests

👥5. Display All Guest Details:
      Prints a detailed list containing:
          Field	Description
          Reservation ID	Unique ID for the booking
          Guest Name	Person who booked the room
          Room Number	Allocated room
          Contact Number	Guest’s phone
          Reservation Date	Timestamp of booking
          Formatted neatly for easy reading.

✏️6. Update an Existing Reservation:
        Allows modifying:
            Guest name
            Room number
            Contact number
            Updates timestamp automatically

🔎7. Find Room Number by Reservation ID:
        Simply enter a guest’s reservation ID → Retrieves room number instantly.

🚪8. Exit:
        Safely closes the system.
