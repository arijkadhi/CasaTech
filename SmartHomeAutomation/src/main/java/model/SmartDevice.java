/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Abstract class representing a generic Smart Device.
 */
public abstract class SmartDevice {
    protected String id;
    protected String name;
    protected boolean isOn;
    protected String location;

    public SmartDevice(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.isOn = false;
    }

    public abstract void turnOn();
    public abstract void turnOff();

    public String getStatus() {
        return name + " (" + location + ") is " + (isOn ? "ON" : "OFF");
    }

    // --- THESE ARE THE MISSING METHODS YOU NEED ---
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}