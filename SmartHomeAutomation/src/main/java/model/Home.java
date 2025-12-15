/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class Home {
    // A Home is just a list of Rooms
    private List<Room> rooms;

    // Constructor: Initializes the empty list
    public Home() {
        this.rooms = new ArrayList<>();
    }

    // Method to add a room (like "Kitchen" or "Bedroom") to the house
    public void addRoom(Room room) {
        rooms.add(room);
        System.out.println("Room '" + room.getName() + "' added to the house.");
    }

    // Getter: Allows the CentralController to see the rooms
    public List<Room> getRooms() {
        return rooms;
    }
}