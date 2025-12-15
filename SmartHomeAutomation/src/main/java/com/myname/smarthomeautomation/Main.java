/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.myname.smarthomeautomation;

import model.*;                     // For Home, Room, SmartLight, etc.
import exceptions.*;                // For DeviceNotFoundException, etc.
import java.time.LocalTime;         // For time-based scheduling

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SMART HOME AUTOMATION SYSTEM ===\n");
        System.out.println("Role: Member 3 - The Logic Engineer\n");
        
        try {
            // 1. CREATE HOME STRUCTURE
            System.out.println("🏠 Step 1: Building Home Structure...");
            Home home = new Home();
            
            Room livingRoom = new Room("Living Room");
            Room bedroom = new Room("Bedroom");
            Room kitchen = new Room("Kitchen");
            
            home.addRoom(livingRoom);
            home.addRoom(bedroom);
            home.addRoom(kitchen);
            
            // 2. ADD DEVICES (using your team's actual classes)
            System.out.println("\n💡 Step 2: Adding Smart Devices...");
            SmartLight livingRoomLight = new SmartLight("L1", "Main Light", "Living Room");
            SmartLight bedroomLight = new SmartLight("L2", "Bedside Lamp", "Bedroom");
            Thermostat livingRoomThermo = new Thermostat("T1", "Living Room Thermostat", "Living Room", 20.0);
            Sensor motionSensor = new Sensor("S1", "Motion Sensor", "Living Room");
            
            livingRoom.addDevice(livingRoomLight);
            livingRoom.addDevice(livingRoomThermo);
            livingRoom.addDevice(motionSensor);
            bedroom.addDevice(bedroomLight);
            
            // 3. CREATE CONTROLLER AND ENGINE
            System.out.println("\n⚙️ Step 3: Initializing Control Systems...");
            CentralController controller = new CentralController(home);
            ScenarioEngine engine = new ScenarioEngine(home, controller);
            
            // 4. ADD AUTOMATION RULES (YOUR LOGIC ENGINEER WORK)
            System.out.println("\n🤖 Step 4: Programming Automation Rules...");
            
            // RULE 1: Motion Detection Scenario
            System.out.println("   • Adding Rule: Motion Detection");
            engine.addScenario(new Scenario(
                "Motion in Living Room → Turn on Light",
                () -> {
                    // Simulate random motion detection
                    boolean motionDetected = Math.random() > 0.7; // 30% chance
                    if (motionDetected) {
                        System.out.println("     👁️ Motion detected in Living Room!");
                    }
                    return motionDetected;
                },
                () -> {
                    livingRoomLight.turnOn();
                    System.out.println("     💡 Main Light turned ON (motion trigger)");
                }
            ));
            
            // RULE 2: Scheduled Lighting
            System.out.println("   • Adding Rule: Scheduled Lighting");
            LocalTime now = LocalTime.now();
            engine.addScheduledEvent(new ScheduledEvent(
                "Evening Lights ON",
                now.plusMinutes(1), // 1 minute from now
                () -> {
                    System.out.println("     🌆 Evening time - Turning on lights");
                    livingRoomLight.turnOn();
                    bedroomLight.turnOn();
                }
            ));
            
            engine.addScheduledEvent(new ScheduledEvent(
                "Night Lights OFF", 
                now.plusMinutes(2), // 2 minutes from now
                () -> {
                    System.out.println("     🌙 Night time - Turning off lights");
                    livingRoomLight.turnOff();
                    bedroomLight.turnOff();
                }
            ));
            
            // RULE 3: Temperature Control (Morning)
            System.out.println("   • Adding Rule: Morning Temperature");
            engine.addScheduledEvent(new ScheduledEvent(
                "Morning Warm-up",
                LocalTime.of(7, 0), // 7:00 AM
                () -> {
                    System.out.println("     ☀️ Morning routine - Setting comfortable temperature");
                    try {
                        livingRoomThermo.setTemperature(22.0); // Comfortable 22°C
                    } catch (Exception e) {
                        System.out.println("     ⚠️ " + e.getMessage());
                    }
                }
            ));
            
            // 5. DEMONSTRATE THE SYSTEM
            System.out.println("\n" + "=".repeat(60));
            System.out.println("🚀 DEMONSTRATION: SMART HOME IN ACTION");
            System.out.println("=".repeat(60));
            
            // Show system status
            engine.showStatus();
            
            System.out.println("\n--- Initial Device Status ---");
            controller.listAllDevices();
            
            // FIRST AUTOMATION CYCLE
            System.out.println("\n--- Automation Cycle 1 (Immediate) ---");
            engine.runAutomationCycle();
            
            // Wait for scheduled events
            System.out.println("\n⏳ Waiting 70 seconds for scheduled events...");
            for (int i = 1; i <= 7; i++) {
                System.out.print(".");
                try {
                    Thread.sleep(10000); // 10 seconds
                } catch (InterruptedException e) {}
            }
            System.out.println();
            
            // SECOND AUTOMATION CYCLE (after scheduled events)
            System.out.println("\n--- Automation Cycle 2 (After Scheduled Events) ---");
            engine.runAutomationCycle();
            
            // Show final status
            System.out.println("\n--- Final Device Status ---");
            controller.listAllDevices();
            
            // DEMONSTRATE INPUT VALIDATION (Your responsibility)
            System.out.println("\n--- Input Validation Demo (Member 3's Job) ---");
            try {
                System.out.println("Testing temperature validation...");
                livingRoomThermo.setTemperature(40.0); // Should throw exception
            } catch (Exception e) {
                System.out.println("✅ VALIDATION WORKING: " + e.getMessage());
            }
            
            // SUMMARY
            System.out.println("\n" + "=".repeat(60));
            System.out.println("📋 DEMONSTRATION SUMMARY");
            System.out.println("=".repeat(60));
            System.out.println("✓ Home created with " + home.getRooms().size() + " rooms");
            System.out.println("✓ " + engine.getScenarios().size() + " automation scenarios active");
            System.out.println("✓ " + engine.getScheduledEvents().size() + " scheduled events configured");
            System.out.println("✓ Input validation implemented");
            System.out.println("✓ All team members' code integrated");
            System.out.println("\n✅ Member 3 (Logic Engineer) tasks COMPLETED!");
            
        } catch (Exception e) {
            System.out.println("\n❌ ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}