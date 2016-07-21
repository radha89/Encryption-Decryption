package encrypt;

import java.io.*;
import java.util.ArrayList;

public class Encrypt {

    private static final String reg_chars
            = "abcdefghijklmnopqrstuvwxyz1234567890,.?:;\"'!-+";
    private static final String encr_chars
            = "qazxswedcvfrtgbnhyujmpolik0381654729+-!'\";:?.";

    public static void main(String[] args) throws IOException {

        try {
            File unencrypted = new File("/Users/radhabhambwani/"
                    + "Desktop/School/Sem-2/Java-2/Encrypt/src", args[0]);
            File encrypted = new File("/Users/radhabhambwani/"
                    + "Desktop/School/Sem-2/Java-2/Encrypt/src", args[1]);

            if (args[2] == null) {
                throw new ArrayIndexOutOfBoundsException();
            }

            if (!args[2].equals("password")) {
                throw new IllegalArgumentException();
            } else {
                FileReader fr = new FileReader(unencrypted);
                BufferedReader unencryptFile = new BufferedReader(fr);

                FileWriter fw = new FileWriter(encrypted.getName());
                PrintWriter encryptFile = new PrintWriter(fw);

                int value = 0;

                ArrayList<Character> fileChars = new ArrayList<Character>();
                ArrayList<Character> encChars = new ArrayList<Character>();
                while ((value = unencryptFile.read()) != -1) {

                    char c = (char) value;

                    fileChars.add(c);
                }

                boolean charExists; //char is encryptable

                for (int i = 0; i < fileChars.size(); i++) {
                    char currentFileChar = fileChars.get(i);
                    charExists = false;
                    for (int j = 0; j < reg_chars.length(); j++) {
                        if (currentFileChar == reg_chars.charAt(j)) {
                            charExists = true;
                            encChars.add(encr_chars.charAt(j));
                        }
                    }
                    if (charExists == false) {
                        encChars.add(currentFileChar);
                    }
                }

                for (int i = 0; i < encChars.size(); i++) {
                    encryptFile.print(encChars.get(i));
                }

                unencryptFile.close();
                unencrypted.delete();
                encryptFile.close();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a password.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Wrong password. Please try again.");
        }
    }

}
