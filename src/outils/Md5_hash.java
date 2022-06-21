package outils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
//0049 - String Get MD5 Hash
public class Md5_hash {
 
    public static String Run_hash(String myString ) {
            toHexadecimal(myString);
        return toMD5Hash(myString); 
    }
    
    static String toHexadecimal ( String source ) {
        
        return toHexadecimal ( source.getBytes() );
    }
    
    static String toHexadecimal ( byte[] source ) {
        
        StringBuilder sb = new StringBuilder();
        
        for ( byte b : source ) {
            
            String toAppend = String.format("%2X", b ).replace(" ", "0"); // %X Hexadecimal
            sb.append( toAppend );
        }
        
        return sb.toString();       
    }
    
    static String toMD5Hash ( String source ) {
        
        String result = "";
        
        try {
            
            MessageDigest md5 = MessageDigest.getInstance( "MD5" );
            byte[] md5HashBytes = md5.digest( source.getBytes() );
            
            result = toHexadecimal( md5HashBytes );
        } 
        catch ( NoSuchAlgorithmException e ) {
 
            e.printStackTrace();
        }
        
        return result;
    }
}
