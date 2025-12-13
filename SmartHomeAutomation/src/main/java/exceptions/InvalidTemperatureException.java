/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author ASUS-PC
 */
public class InvalidTemperatureException extends RuntimeException {
    public InvalidTemperatureException(String message) {
        super(message);
    }
}