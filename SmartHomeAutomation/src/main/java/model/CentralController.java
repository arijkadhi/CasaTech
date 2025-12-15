/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

// Import the exception Member 1 created in the 'exceptions' folder
import exceptions.DeviceNotFoundException;
import java.util.List;

public class CentralController {
    private Home home;

    // Constructor: Connects the controller to a specific house
    public CentralController(Home home) {
        this.home = home;
    }

    // Requirement 3.3: List all devices and their statuses
    public void listAllDevices() {
        System.out.println("\n--- [Central Controller] Device Report ---");
        
        // Loop 1: Go through every Room
        for (Room room : home.getRooms()) {
            System.out.println("Room: " + room.getName());
            
            List<SmartDevice> devices = room.getDevices();
            if (devices.isEmpty()) {
                System.out.println("   (No devices found)");
            } else {
                // Loop 2: Go through every Device in that room
                for (SmartDevice device : devices) {
                    // This calls the getStatus() method Member 1 wrote
                    System.out.println("   -> " + device.getStatus());
                }
            }
        }
        System.out.println("------------------------------------------");
    }

    // Requirement 3.2: Search for a device by ID (String)
    public SmartDevice findDeviceById(String id) throws DeviceNotFoundException {
        // Look through every room...
        for (Room room : home.getRooms()) {
            // Look at every device...
            for (SmartDevice device : room.getDevices()) {
                // Check if IDs match (Using .equals because it's a String)
                if (device.getId().equals(id)) {
                    return device; // Found it!
                }
            }
        }
        // If we finish all loops and find nothing, throw the error
        throw new DeviceNotFoundException("Device with ID '" + id + "' was not found in the home.");
    }

    // Requirement 3.3: Global Action - Turn everything OFF
    public void switchOffAllDevices() {
        System.out.println("\n[Central Controller] Turning OFF all devices...");
        for (Room room : home.getRooms()) {
            for (SmartDevice device : room.getDevices()) {
                device.turnOff();
            }
        }
    }

    // Global Action - Turn everything ON
    public void switchOnAllDevices() {
        System.out.println("\n[Central Controller] Turning ON all devices...");
        for (Room room : home.getRooms()) {
            for (SmartDevice device : room.getDevices()) {
                device.turnOn();
            }
        }
    }
}