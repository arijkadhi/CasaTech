/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.myname.smarthomeautomation;

import model.*;
import exceptions.DeviceNotFoundException;
import exceptions.InvalidTemperatureException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The ScenarioEngine handles all automation logic in the smart home.
 * It manages IF-THEN rules and scheduled events.
 * Member 3: The Logic Engineer
 */
public class ScenarioEngine {
    
    private List<Scenario> scenarios;
    private List<ScheduledEvent> scheduledEvents;
    private Home home;
    private CentralController controller;
    
    public ScenarioEngine(Home home, CentralController controller) {
        this.home = home;
        this.controller = controller;
        this.scenarios = new ArrayList<>();
        this.scheduledEvents = new ArrayList<>();
        System.out.println("✅ ScenarioEngine initialized");
    }
    
    public ScenarioEngine(Home home) {
        this(home, new CentralController(home));
    }
    
    // ==================== SCENARIO MANAGEMENT ====================
    
    public void addScenario(Scenario scenario) {
        scenarios.add(scenario);
        System.out.println("📋 Added scenario: " + scenario.getDescription());
    }
    
    public void addScheduledEvent(ScheduledEvent event) {
        scheduledEvents.add(event);
        System.out.println("⏰ Added scheduled event: " + event.getDescription() + " at " + event.getTriggerTime());
    }
    
    // ==================== PRE-BUILT SCENARIOS ====================
    
    /**
     * SIMPLIFIED: Add motion detection scenario (without accessing private fields)
     */
    public void addMotionDetectionScenario(String sensorName, String lightName) {
        addScenario(new Scenario(
            "Motion Detection: " + sensorName + " → " + lightName,
            () -> {
                // Simulate motion detection (since we can't access private 'detected' field)
                boolean simulatedMotion = Math.random() > 0.7; // 30% chance
                if (simulatedMotion) {
                    System.out.println("   🚨 Simulated motion detected by " + sensorName);
                    
                    // Try to call detect() method if sensor exists
                    try {
                        SmartDevice device = findDevice(sensorName);
                        if (device instanceof Sensor) {
                            ((Sensor) device).detect(); // Call public detect() method
                        }
                    } catch (Exception e) {
                        // Ignore if device not found
                    }
                }
                return simulatedMotion;
            },
            () -> {
                try {
                    SmartDevice light = findDevice(lightName);
                    if (light instanceof SmartLight) {
                        ((SmartLight) light).turnOn();
                        System.out.println("   🔦 " + lightName + " turned ON (motion trigger)");
                    }
                } catch (Exception e) {
                    System.out.println("   ⚠️ Could not turn on light: " + e.getMessage());
                }
            }
        ));
    }
    
    /**
     * SIMPLIFIED: Add temperature control scenario
     */
    public void addTemperatureControlScenario(String thermostatName, double minTemp, double maxTemp) {
        addScenario(new Scenario(
            "Temperature Control: Maintain " + minTemp + "-" + maxTemp + "°C",
            () -> {
                // Simulate temperature check (Thermostat doesn't have getTemperature())
                System.out.println("   🌡️ Simulating temperature check for " + thermostatName);
                double simulatedTemp = 15 + Math.random() * 15; // Random 15-30°C
                boolean needsAdjustment = simulatedTemp < minTemp || simulatedTemp > maxTemp;
                
                if (needsAdjustment) {
                    System.out.println("   ❗ Temperature out of range: " + simulatedTemp + "°C");
                }
                return needsAdjustment;
            },
            () -> {
                try {
                    SmartDevice device = findDevice(thermostatName);
                    if (device instanceof Thermostat) {
                        double targetTemp = (minTemp + maxTemp) / 2; // Average
                        ((Thermostat) device).setTemperature(targetTemp);
                        System.out.println("   🔥 " + thermostatName + " adjusted to " + targetTemp + "°C");
                    }
                } catch (InvalidTemperatureException e) {
                    System.out.println("   ⚠️ Invalid temperature: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("   ❌ Error: " + e.getMessage());
                }
            }
        ));
    }
    
    /**
     * Add time-based light control (SIMPLIFIED - works with your structure)
     */
    public void addScheduledLightControl(String lightName, LocalTime time, boolean turnOn) {
        addScheduledEvent(new ScheduledEvent(
            (turnOn ? "Turn on " : "Turn off ") + lightName + " at " + time,
            time,
            () -> {
                try {
                    SmartDevice device = findDevice(lightName);
                    if (device instanceof SmartLight) {
                        if (turnOn) {
                            device.turnOn();
                            System.out.println("   💡 Scheduled: " + lightName + " turned ON");
                        } else {
                            device.turnOff();
                            System.out.println("   💡 Scheduled: " + lightName + " turned OFF");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("   ⚠️ Scheduled action failed: " + e.getMessage());
                }
            }
        ));
    }
    
    /**
     * Add morning routine (SIMPLIFIED)
     */
    public void addMorningRoutine(LocalTime wakeUpTime) {
        addScheduledEvent(new ScheduledEvent(
            "Morning Routine at " + wakeUpTime,
            wakeUpTime,
            () -> {
                System.out.println("   🌅 GOOD MORNING! Starting morning routine...");
                
                // Turn on all lights
                int lightsTurnedOn = 0;
                for (Room room : home.getRooms()) {
                    for (SmartDevice device : room.getDevices()) {
                        if (device instanceof SmartLight) {
                            device.turnOn();
                            lightsTurnedOn++;
                        }
                    }
                }
                System.out.println("   💡 Turned on " + lightsTurnedOn + " light(s)");
                
                // Adjust thermostats
                int thermostatsAdjusted = 0;
                for (Room room : home.getRooms()) {
                    for (SmartDevice device : room.getDevices()) {
                        if (device instanceof Thermostat) {
                            try {
                                ((Thermostat) device).setTemperature(22.0);
                                thermostatsAdjusted++;
                            } catch (InvalidTemperatureException e) {
                                System.out.println("   ⚠️ " + e.getMessage());
                            }
                        }
                    }
                }
                System.out.println("   🔥 Adjusted " + thermostatsAdjusted + " thermostat(s) to 22°C");
            }
        ));
    }
    
    /**
     * Add night routine (SIMPLIFIED)
     */
    public void addNightRoutine(LocalTime bedTime) {
        addScheduledEvent(new ScheduledEvent(
            "Night Routine at " + bedTime,
            bedTime,
            () -> {
                System.out.println("   🌙 GOOD NIGHT! Starting night routine...");
                
                // Turn off all lights
                int lightsTurnedOff = 0;
                for (Room room : home.getRooms()) {
                    for (SmartDevice device : room.getDevices()) {
                        if (device instanceof SmartLight) {
                            device.turnOff();
                            lightsTurnedOff++;
                        }
                    }
                }
                System.out.println("   💡 Turned off " + lightsTurnedOff + " light(s)");
                
                // Set thermostats to night temperature
                int thermostatsAdjusted = 0;
                for (Room room : home.getRooms()) {
                    for (SmartDevice device : room.getDevices()) {
                        if (device instanceof Thermostat) {
                            try {
                                ((Thermostat) device).setTemperature(18.0);
                                thermostatsAdjusted++;
                            } catch (InvalidTemperatureException e) {
                                System.out.println("   ⚠️ " + e.getMessage());
                            }
                        }
                    }
                }
                System.out.println("   🔥 Adjusted " + thermostatsAdjusted + " thermostat(s) to 18°C");
            }
        ));
    }
    
    // ==================== EXECUTION METHODS ====================
    
    public void checkScenarios() {
        if (scenarios.isEmpty()) {
            System.out.println("ℹ️ No scenarios to check");
            return;
        }
        
        System.out.println("\n🔍 Checking " + scenarios.size() + " scenario(s)...");
        int executed = 0;
        
        for (Scenario scenario : scenarios) {
            if (scenario.isConditionMet()) {
                System.out.println("⚡ Executing: " + scenario.getDescription());
                scenario.execute();
                executed++;
            }
        }
        
        if (executed > 0) {
            System.out.println("✅ Executed " + executed + " scenario(s)");
        } else {
            System.out.println("ℹ️ No scenarios triggered");
        }
    }
    
    public void checkScheduledEvents() {
        if (scheduledEvents.isEmpty()) {
            System.out.println("ℹ️ No scheduled events to check");
            return;
        }
        
        LocalTime now = LocalTime.now();
        System.out.println("\n⏰ Checking " + scheduledEvents.size() + " scheduled event(s) at " + now + "...");
        int triggered = 0;
        
        for (ScheduledEvent event : scheduledEvents) {
            if (event.shouldTriggerAt(now)) {
                System.out.println("🔔 Triggering: " + event.getDescription());
                event.execute();
                triggered++;
            }
        }
        
        if (triggered > 0) {
            System.out.println("✅ Triggered " + triggered + " scheduled event(s)");
        } else {
            System.out.println("ℹ️ No scheduled events due");
        }
    }
    
    public void runAutomationCycle() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🤖 RUNNING AUTOMATION CYCLE");
        System.out.println("=".repeat(50));
        
        checkScenarios();
        checkScheduledEvents();
    }
    
    // ==================== HELPER METHODS ====================
    
    public SmartDevice findDevice(String deviceName) throws DeviceNotFoundException {
        for (Room room : home.getRooms()) {
            for (SmartDevice device : room.getDevices()) {
                if (device.getName().equalsIgnoreCase(deviceName)) {
                    return device;
                }
            }
        }
        throw new DeviceNotFoundException("Device '" + deviceName + "' not found in any room");
    }
    
    public List<SmartDevice> findAllDevices() {
        List<SmartDevice> allDevices = new ArrayList<>();
        for (Room room : home.getRooms()) {
            allDevices.addAll(room.getDevices());
        }
        return allDevices;
    }
    
    public void listAllDevices() {
        controller.listAllDevices();
    }
    
    // ==================== GETTERS & STATISTICS ====================
    
    public List<Scenario> getScenarios() { 
        return new ArrayList<>(scenarios); 
    }
    
    public List<ScheduledEvent> getScheduledEvents() { 
        return new ArrayList<>(scheduledEvents); 
    }
    
    public Home getHome() { 
        return home; 
    }
    
    public CentralController getController() { 
        return controller; 
    }
    
    public void showStatus() {
        System.out.println("\n📊 ScenarioEngine Status:");
        System.out.println("   • Active Scenarios: " + scenarios.size());
        System.out.println("   • Scheduled Events: " + scheduledEvents.size());
        System.out.println("   • Rooms: " + home.getRooms().size());
        
        int totalDevices = 0;
        int lights = 0, thermostats = 0, sensors = 0, others = 0;
        
        for (Room room : home.getRooms()) {
            totalDevices += room.getDevices().size();
            for (SmartDevice device : room.getDevices()) {
                if (device instanceof SmartLight) lights++;
                else if (device instanceof Thermostat) thermostats++;
                else if (device instanceof Sensor) sensors++;
                else others++;
            }
        }
        
        System.out.println("   • Total Devices: " + totalDevices);
        if (lights > 0) System.out.println("     └─ Lights: " + lights);
        if (thermostats > 0) System.out.println("     └─ Thermostats: " + thermostats);
        if (sensors > 0) System.out.println("     └─ Sensors: " + sensors);
        if (others > 0) System.out.println("     └─ Other devices: " + others);
    }
    
    /**
     * Clear all automation rules (for testing)
     */
    public void clearAllRules() {
        scenarios.clear();
        scheduledEvents.clear();
        System.out.println("🧹 All automation rules cleared");
    }
    
    /**
     * Validate device control (Member 3's responsibility)
     */
    public void validateDeviceControl(String deviceName, Object value) throws IllegalArgumentException {
        if (deviceName == null || deviceName.trim().isEmpty()) {
            throw new IllegalArgumentException("Device name cannot be empty");
        }
        
        if (value instanceof Integer) {
            int intValue = (Integer) value;
            // Example validation for brightness
            if (deviceName.toLowerCase().contains("light")) {
                if (intValue < 0 || intValue > 100) {
                    throw new IllegalArgumentException("Brightness must be 0-100");
                }
            }
        }
        
        System.out.println("✅ Validation passed for " + deviceName + " = " + value);
    }
}