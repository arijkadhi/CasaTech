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
import exceptions.InvalidTemperatureException;

public class Thermostat extends SmartDevice
        implements Controllable, EnergyConsumer, Schedulable {

    private double temperature;

    public Thermostat(String id, String name, String location, double temp) {
        super(id, name, location);
        this.temperature = temp;
    }

    public void setTemperature(double temp) {
        if (temp < 5 || temp > 35) {
            throw new InvalidTemperatureException("Temperature must be between 5 and 35");
        }
        this.temperature = temp;
        System.out.println("Temperature set to " + temp);
    }

    @Override
    public double getEnergyConsumption() {
        return isOn ? temperature * 0.2 : 0;
    }

    @Override
    public void schedule(String time) {
        System.out.println("Thermostat scheduled at " + time);
    }

    public void activate() { turnOn(); }
    public void deactivate() { turnOff(); }

    public void turnOn() { isOn = true; }
    public void turnOff() { isOn = false; }
}
