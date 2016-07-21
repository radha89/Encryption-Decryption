package encrypt;

import java.io.*;
import java.util.ArrayList;

public class Decrypt {

    private static final String encr_chars
            = "qazxswedcvfrtgbnhyujmpolik0381654729+-!'\";:?.";
    private static final String reg_chars
            = "abcdefghijklmnopqrstuvwxyz1234567890,.?:;\"'!-+";

    public static void main(String[] args) throws IOException {

        try {
            File encrypted = new File("/Users/radhabhambwani/"
                    + "Desktop/School/Sem-2/Java-2/Encrypt/src", args[0]);
            File unencrypted = new File("/Users/radhabhambwani/"
                    + "Desktop/School/Sem-2/Java-2/Encrypt/src", args[1]);

            if (args[2] == null) {
                throw new ArrayIndexOutOfBoundsException();
            }

            if (!args[2].equals("password")) {
                throw new IllegalArgumentException();
            } else {

                FileReader fr = new FileReader(encrypted);
                BufferedReader encryptFile = new BufferedReader(fr);

                FileWriter fw = new FileWriter(unencrypted.getName());
                PrintWriter unencryptFile = new PrintWriter(fw);

                int value = 0;

                ArrayList<Character> encChars = new ArrayList<Character>();
                ArrayList<Character> fileChars = new ArrayList<Character>();

                while ((value = encryptFile.read()) != -1) {

                    char c = (char) value;

                    encChars.add(c);
                }

                boolean charExists;

                for (int i = 0; i < encChars.size(); i++) {
                    char currentFileChar = encChars.get(i);
                    charExists = false;
                    for (int j = 0; j < encr_chars.length(); j++) {
                        if (currentFileChar == encr_chars.charAt(j)) {
                            charExists = true;
                            fileChars.add(reg_chars.charAt(j));
                        }
                    }
                    if (charExists == false) {
                        fileChars.add(currentFileChar);
                    }
                }

                for (int i = 0; i < fileChars.size(); i++) {
                    unencryptFile.print(fileChars.get(i));
                }

                unencryptFile.close();
                encryptFile.close();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a password.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Wrong password. Please try again.");
        }
    }

}
