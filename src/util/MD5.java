/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dto.Injection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Admin
 */
public class MD5 {
    private byte[] convertObject (Injection inj){
        String str = inj.toString();
        byte[] b = str.getBytes();
        return b;
    }
    
    public String MD5 (Injection inj) throws NoSuchAlgorithmException{
        byte[] b = convertObject(inj);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(b);
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return myHash;
    }
}
