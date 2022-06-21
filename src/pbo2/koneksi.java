/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo2;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author XXIVKVNGLRT
 */
public class koneksi implements key{
   Connection con;
    public koneksi() {
    try {
        Class.forName(driver).newInstance();
        con = DriverManager.getConnection(url, username, password);
            if (con == null) {
                    System.out.println("Koneksi Gagal...!");
            } else {
                System.out.println("Koneksi Sukses...!");
            }
    } catch (Exception e) {
        System.out.println("" + e.getMessage());
        }
    }
         public static void main(String[] args) {
        koneksi k = new koneksi();
            }
}
