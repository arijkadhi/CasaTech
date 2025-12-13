/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS-PC
 */
public class Sensor extends SmartDevice {

    private boolean detected;

    public Sensor(String id, String name, String location) {
        super(id, name, location);
    }

    public void detect() {
        detected = true;
        System.out.println(name + " detected motion!");
    }

    @Override
    public void turnOn() {
        isOn = true;
    }

    @Override
    public void turnOff() {
        isOn = false;
    }
}
