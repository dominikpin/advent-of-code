package year2015.day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Part1 {
    public Part1() throws FileNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        String key = myReader.nextLine();
        int i = 0;
        String hashText = "";
        while (32 - hashText.length() != 5) {
            i++;
            byte[] bytesOfMessage = (key + "" + i).getBytes("UTF-8");

            byte[] theMD5digest = MessageDigest.getInstance("MD5").digest(bytesOfMessage);
            BigInteger bigInt = new BigInteger(1, theMD5digest);
            hashText = bigInt.toString(16);
        }
        System.out.println(i);
        myReader.close();
    }
}
