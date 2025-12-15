/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 * @author ASUS-PC
 */
public class Sensor extends SmartDevice {
    private boolean detected;
    
    public Sensor(String id, String name, String location) {
        super(id, name, location);
        this.detected = false;
    }
    
    public void detect() {
        detected = true;
        System.out.println(name + " detected motion!");
    }
    
    // ADD THIS GETTER METHOD (for your ScenarioEngine to work)
    public boolean isDetected() {
        return detected;
    }
    
    // OPTIONAL: Add method to reset detection
    public void resetDetection() {
        detected = false;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(name + " sensor activated");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(name + " sensor deactivated");
    }
    
    @Override
    public String getStatus() {
        return super.getStatus() + ", Motion detected: " + detected;
    }
}
