/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_venta.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class Conexion {
    
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_ventas";
    private static final String USER = "root"; // Usuario por defecto de XAMPP
    private static final String PASSWORD = ""; // Contraseña vacía por defecto en XAMPP
    
    public static Connection getConexion() {
        Connection cn = null;
        try {
            // Conectar a la base de datos usando el conector moderno que agregamos
            cn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("¡Conexion exitosa a sistema_ventas! ?");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return cn;
    }
}
