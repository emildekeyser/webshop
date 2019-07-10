package domain;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DomainUtilities
{
    public static String sha512(String password)
    {
        MessageDigest crypt = null;
        try
        {
            crypt = MessageDigest.getInstance("SHA-512");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Hashing failure");
        }
        crypt.reset();
        byte[] passwordBytes = new byte[0];
        try
        {
            passwordBytes = password.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Hashing failure");
        }
        crypt.update(passwordBytes);
        byte[] digest = crypt.digest();
        return new BigInteger(1, digest).toString(16);
    }

}
