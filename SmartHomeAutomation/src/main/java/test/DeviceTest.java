/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

/**
 *
 * @author ASUS-PC
 */
import model.*;
import exceptions.*;

public class DeviceTest {

    public static void main(String[] args) {

        SmartLight light = new SmartLight("L1", "Living Light", "Living Room");
        Thermostat thermo = new Thermostat("T1", "Thermostat", "Bedroom", 22);
        SmartTV tv = new SmartTV("TV1", "Samsung TV", "Living Room");
        Sensor sensor = new Sensor("S1", "Motion Sensor", "Entrance");

        light.turnOn();
        tv.turnOn();
        thermo.turnOn();

        System.out.println(light.getStatus());
        System.out.println(tv.getStatus());
        System.out.println(thermo.getStatus());

        try {
            thermo.setTemperature(40);
        } catch (InvalidTemperatureException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        sensor.turnOn();
        sensor.detect();

        System.out.println("✅ DEVICE TEST COMPLETED SUCCESSFULLY");
    }
}
