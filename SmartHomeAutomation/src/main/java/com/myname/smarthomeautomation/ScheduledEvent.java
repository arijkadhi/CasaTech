/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myname.smarthomeautomation;

import java.time.LocalTime;

public class ScheduledEvent {
    private String description;
    private LocalTime triggerTime;
    private Action action;
    
    public ScheduledEvent(String description, LocalTime triggerTime, Action action) {
        this.description = description;
        this.triggerTime = triggerTime;
        this.action = action;
    }
    
    public boolean shouldTriggerAt(LocalTime currentTime) {
        return currentTime.getHour() == triggerTime.getHour() 
            && currentTime.getMinute() == triggerTime.getMinute();
    }
    
    public void execute() { action.perform(); }
    public String getDescription() { return description; }
    public LocalTime getTriggerTime() { return triggerTime; }
    
    @Override
    public String toString() {
        return "ScheduledEvent: " + description + " at " + triggerTime;
    }
}