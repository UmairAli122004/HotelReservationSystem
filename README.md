# 🏨 Hotel Reservation System  
### *A Core Java + JDBC + MySQL Console Application*

This project is a **console-based Hotel Reservation System** designed to simplify hotel room management. It provides features like reserving rooms, canceling bookings, checking room availability, updating reservation details, and retrieving guest information — all through a simple menu-driven interface.

The project demonstrates practical knowledge of:

- ✔️ Core Java  
- ✔️ JDBC  
- ✔️ MySQL  
- ✔️ CRUD Operations  
- ✔️ Menu-driven console applications  
- ✔️ Data validation  

---

## 🌟 Key Features

### 🛏️ 1. Reserve a Room  
- Enter guest name and contact number  
- Choose a room number (0–50)  
- System checks room availability  
- Saves reservation details to the MySQL database  

---

### ❌ 2. Cancel a Reservation  
- Enter reservation ID  
- System deletes the booking  
- Marks the room as available again  

---

### 🔍 3. View All Reserved Rooms  
- Displays all rooms currently reserved  
- Helps managers quickly see which rooms are occupied  

---

### 🟩 4. View All Available Rooms  
- Shows all free rooms (0–50)  
- Helps in quick room assignment for new guests  

---

### 👥 5. Display All Guest Details  
Shows a detailed list of all guest bookings, including:

| Field | Description |
|--------|-------------|
| **Reservation ID** | Unique ID for the booking |
| **Guest Name** | Name of the guest |
| **Room Number** | Allocated room |
| **Contact Number** | Guest’s phone number |
| **Reservation Date** | Timestamp of the booking |

---

### ✏️ 6. Update a Reservation  
Allows updating:  
- Guest name  
- Room number  
- Contact number  
- Automatically updates timestamp  

---

### 🔎 7. Find Room Number by Reservation ID  
- Enter reservation ID  
- System retrieves the corresponding room number  

---

### 🚪 8. Exit  
- Safely closes the system and terminates the program  

---

## 💾 Technologies Used  
- **Java (Core Java)**  
- **JDBC**  
- **MySQL**  
- **SQL CRUD Operations**  

---

## 📂 Database Table (reservation)
| Column Name        | Type          | Description                         |
|--------------------|---------------|-------------------------------------|
| reservation_id      | INT (PK)      | Auto-increment primary key          |
| guest_name          | VARCHAR       | Name of the guest                   |
| room_number         | INT           | Room number (0–50)                  |
| contact_number      | VARCHAR       | Phone number of the guest           |
| reservation_date    | TIMESTAMP     | Date & time of reservation          |

---
