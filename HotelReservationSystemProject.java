import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static java.lang.System.exit;

public class HotelReservationSystemProject {
    public static String url = "jdbc:mysql://localhost:3306/hotel_db";
    public static String username  = "root";
    public static String password = "umair@7379";
    static Scanner sc = new Scanner(System.in);
    static int[] totalNoOfRooms = new int[51];

    public static void main(String[] args) {
        System.out.println("Room number 0-50");
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            int choice;
            while(true){
                System.out.println("HOTEL RESERVATION SYSTEM");
                System.out.println("1 : Reserve a room");
                System.out.println("2 : Cancel  reservation");
                System.out.println("3 : Check reserved room");
                System.out.println("4 : Check available room");
                System.out.println("5 : All Guest Information");
                System.out.println("6 : Update reservation");
                System.out.println("7 : Check  room number of guest");
                System.out.println("8 : Exit");

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        newReservation(connection);
                        break;
                    case 2:
                        cancelReservation(connection);
                        break;
                    case 3:
                        checkReservedRoom(connection);
                        break;
                    case 4:
                        checkAvailableRoom(connection);
                        break;
                    case 5:
                        getGuestDetails(connection);
                        break;
                    case 6:
                        updateReservation(connection);
                        break;
                    case 7:
                        int roomNumber = getRoomNumber(connection);
                        System.out.println("Room Number : " + roomNumber);
                        break;
                    case 8:
                        System.out.println("Thanks for using Hotel Reservation System");
                        exit(0);
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid Choice! please try again");
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void newReservation(Connection connection){
        System.out.println("Enter room number");
        int room_number = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter Guest name");
        String guest_name = sc.nextLine();

        System.out.println("Enter contact number");
        String contact_number = sc.nextLine();

        if(!isValidContactNumber(contact_number)){
            return;
        }

        try(Statement stmt = connection.createStatement()){
            if(totalNoOfRooms[room_number] == 0){
                totalNoOfRooms[room_number] = 1;
                String query =  "INSERT INTO reservation(guest_name, room_number, contact_number)VALUES('"+ guest_name+ "',  " + room_number +",  '"+ contact_number +"')";
                int insertRowsAffected = stmt.executeUpdate(query);

                if(insertRowsAffected > 0){
                    System.out.println("Room reserved successfully");
                }else{
                    System.out.println("Something went wrong");
                }
            }else {
                System.out.println("Room is already booked by someone");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static boolean isValidContactNumber(String contactNumber){
        int len = contactNumber.length();
        if(len == 10){
            for(char ch : contactNumber.toCharArray()){
                if(!Character.isDigit(ch)){
                    System.out.println("Invalid number");
                    return false;
                }
            }
        }else{
            System.out.println("Invalid number");
            return false;
        }
        return true;
    }

    static void cancelReservation(Connection connection){
        System.out.println("Enter reservation id");
        int reservationId = sc.nextInt();
        sc.nextLine();

        String query = "DELETE FROM reservation WHERE reservation_id = "+reservationId;

        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT room_number FROM reservation WHERE reservation_id = " + reservationId);
            int room_number = -1;
            if(rs.next()){
                room_number = rs.getInt("room_number");
            }
            if(room_number >=0 && room_number<=50){
                totalNoOfRooms[room_number] = 0;
                int deleteRowAffected = stmt.executeUpdate(query);
                if(deleteRowAffected > 0){
                    System.out.println("Reserved room Cancelled/deleted");
                }else{
                    System.out.println("Sorry! you haven't reserved room yet");
                }
            }else{
                System.out.println("Sorry! you haven't reserved room yet");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static void checkAvailableRoom(Connection connection){
        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT room_number FROM reservation");
            while(rs.next()){
                int roomNumber = rs.getInt(1);
                if(roomNumber >=0 && roomNumber<=50){
                    totalNoOfRooms[roomNumber] = 1;
                }
            }

            int n = totalNoOfRooms.length;
            for(int i=0; i<n; i++){
                if(totalNoOfRooms[i] == 0){
                    System.out.println("Room Number "+ i +" is available");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static void checkReservedRoom(Connection connection){
        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM reservation");
            while(rs.next()){
                int roomNumber = rs.getInt("room_number");
                if(roomNumber >=0 && roomNumber<=50){
                    totalNoOfRooms[roomNumber] = 1;
                }
            }

            int n = totalNoOfRooms.length;
            for(int i=0; i<n; i++){
                if(totalNoOfRooms[i] != 0){
                    System.out.println("Room Number "+ i +" is reserved");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static int getRoomNumber(Connection connection){
        System.out.println("Enter reservation id");
        int id = sc.nextInt();

        String query = "SELECT room_number FROM reservation WHERE reservation_id = "+id;
        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                return rs.getInt(1);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Room are not reserved by guest");
        return -1;
    }

    static void updateReservation(Connection connection){
        System.out.println("Enter reservation id");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter Guest name");
        String guest_name = sc.nextLine();

        System.out.println("Enter room number");
        int room_number = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter contact number");
        String contact_number = sc.nextLine();

        if(!isValidContactNumber(contact_number)){
            return;
        }

        String checkQuery = "SELECT COUNT(*) FROM reservation WHERE reservation_id = " + id;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(checkQuery)) {

            rs.next();
            int count = rs.getInt(1);
            if (count == 0) {
                System.out.println("Reservation ID does not exist.");
                return;
            }

            String query = "UPDATE reservation SET guest_name = '" + guest_name + "', room_number = " + room_number +
                    ", contact_number = '" + contact_number + "', reservation_date = CURRENT_TIMESTAMP WHERE reservation_id = " + id;

            int updateRowsAffected = stmt.executeUpdate(query);
            if(updateRowsAffected > 0){
                System.out.println("Update Successfully");
            } else {
                System.out.println("Update failed.");
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    static void getGuestDetails(Connection connection){
        try(Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM reservation");

            if (!rs.isBeforeFirst()) {
                System.out.println("Guest information are not available");
                return;
            }

            while (rs.next()) {
                int reservationId = rs.getInt("reservation_id");
                String guestName = rs.getString("guest_name");
                int roomNumber = rs.getInt("room_number");
                String contactNumber = rs.getString("contact_number");
                Timestamp timeStamp = rs.getTimestamp("reservation_date");
                System.out.println();
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("Reservation ID : " + reservationId + " |  ");
                System.out.print("Guest Name : " + guestName + "   |  ");
                System.out.print("Room Number : " + roomNumber + "  |  ");
                System.out.print("Contact Number : " + contactNumber + "  |  ");
                System.out.print("Date and Time : " + timeStamp + "  |  \n");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
            }
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

