/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS-PC
 */
import interfaces.Controllable;
public class SmartLight extends SmartDevice implements Controllable {
    private int brightness;
    private String color;

    public SmartLight(String id, String name, String location) {
        super(id, name, location);
        this.brightness = 100;
        this.color = "White";
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(name + " light ON");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(name + " light OFF");
    }
    @Override
    public void activate() { turnOn(); }
    @Override

    public void deactivate() { turnOff(); }
}