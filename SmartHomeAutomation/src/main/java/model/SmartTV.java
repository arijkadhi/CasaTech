/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS-PC
 */
import interfaces.*;

public class SmartTV extends SmartDevice
        implements Controllable, EnergyConsumer, Schedulable {

    private int volume;
    private String channel;

    public SmartTV(String id, String name, String location) {
        super(id, name, location);
        this.volume = 15;
        this.channel = "Netflix";
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(name + " TV ON");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(name + " TV OFF");
    }

    @Override
    public double getEnergyConsumption() {
        return isOn ? volume * 0.1 : 0;
    }

    @Override
    public void schedule(String time) {
        System.out.println("TV scheduled at " + time);
    }

    public void activate() { turnOn(); }
    public void deactivate() { turnOff(); }
}