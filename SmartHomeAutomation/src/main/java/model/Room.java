/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private List<SmartDevice> devices;

    public Room(String name) {
        this.name = name;
        this.devices = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addDevice(SmartDevice device) {
        devices.add(device);
        // Uses the getName() method we just added to SmartDevice
        System.out.println(device.getName() + " added to " + this.name);
    }

    public void removeDevice(SmartDevice device) {
        if (devices.remove(device)) {
            System.out.println(device.getName() + " removed from " + this.name);
        } else {
            System.out.println("Device not found in " + this.name);
        }
    }

    public List<SmartDevice> getDevices() {
        return devices;
    }
}