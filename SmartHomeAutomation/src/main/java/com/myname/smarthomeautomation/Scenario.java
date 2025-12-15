package com.myname.smarthomeautomation;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class Scenario {
    private String description;
    private Condition condition;
    private Action action;
    
    public Scenario(String description, Condition condition, Action action) {
        this.description = description;
        this.condition = condition;
        this.action = action;
    }
    
    public boolean isConditionMet() { return condition.check(); }
    public void execute() { action.perform(); }
    public String getDescription() { return description; }
    
    @Override
    public String toString() {
        return "Scenario: " + description;
    }
}