package year2016.day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        System.out.println(generatePassword(myReader.nextLine()));
        myReader.close();
    }

    private static String generatePassword(String line) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String key = line;
        String password = "";
        int i = 0;
        for (int j = 0; j < 8; j++) {
            String hashText = "";
            while (hashText == "" || 32 - hashText.length() < 5) {
                i++;
                byte[] bytesOfMessage = (key + "" + i).getBytes("UTF-8");

                byte[] theMD5digest = MessageDigest.getInstance("MD5").digest(bytesOfMessage);
                BigInteger bigInt = new BigInteger(1, theMD5digest);
                hashText = bigInt.toString(16);
            }
            hashText = "0".repeat(32 - hashText.length() - 5) + hashText;
            password += hashText.charAt(0);
            i++;
        }
        return password;
    }
}