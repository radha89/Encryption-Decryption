package encrypt;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UnencryptedFileManager {

    private static final String encr_chars
            = "qazxswedcvfrtgbnhyujmpolik0381654729+-!'\";:?.";
    private static final String reg_chars
            = "abcdefghijklmnopqrstuvwxyz1234567890,.?:;\"'!-+";

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                if (args[0] == null) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                if (!args[0].equals("password")) {
                    throw new IllegalArgumentException();
                } else if (args[0].equals("password")) {
                    System.out.println("\n1) Add Record");
                    System.out.println("2) Find Record");
                    System.out.println("3) Quit");
                    System.out.println("\nEnter Option Number: ");
                    int option = Integer.parseInt(input.nextLine());

                    switch (option) {
                        case 1:
                            System.out.println("Add a record to "
                                    + "unencryptedFile.txt: ");
                            String record = input.nextLine();

                            String[] decrypt_args = new String[]
                            {"encryptedFile.txt", "unencryptedFile.txt", 
                                "password"};
                            Decrypt.main(decrypt_args);

                            File unencrypted = 
                                    new File("/Users/radhabhambwani/Desktop"
                                            + "/School/Sem-2/Java-2/Encrypt/"
                                            + "src", decrypt_args[1]);
                            FileWriter fw = 
                                    new FileWriter(unencrypted.getName(), 
                                            true);
                            PrintWriter unencryptFile = new PrintWriter(fw);

                            unencryptFile.println(record);

                            unencryptFile.close();

                            String[] encrypt_args = new String[]
                            {unencrypted.getName(), "encryptedFile.txt", 
                                "password"};
                            Encrypt.main(encrypt_args);
                            break;

                        case 2:
                            System.out.println("Search string "
                                    + "in unencryptedFile.txt: ");
                            String stringSearch = input.nextLine();

                            String[] decrypted_args = new String[]
                            {"encryptedFile.txt", 
                                "unencryptedFile.txt", "password"}; 
                            Decrypt.main(decrypted_args);

                            File unencryptedFile = 
                                    new File("/Users/radhabhambwani/Desktop"
                                            + "/School/Sem-2/Java-2/Encrypt"
                                            + "/src", decrypted_args[1]);
                            FileReader fr = 
                                    new FileReader(unencryptedFile.getName());
                            BufferedReader unencryptedFileReader = 
                                    new BufferedReader(fr);

                            int value = 0;

                            ArrayList<Character> fileCharsList = 
                                    new ArrayList<>();
                            boolean stringFound = false;

                            while ((value = unencryptedFileReader.read()) 
                                    != -1) {
                                char c = (char) value;
                                fileCharsList.add(c);
                            }

                            char[] fileCharsArray = 
                                    new char[fileCharsList.size()];

                            for (int i = 0; i < fileCharsArray.length; i++) {
                                fileCharsArray[i] = fileCharsList.get(i);
                            }

                            String fileString = new String(fileCharsArray);

                            int lastIndex = 0;
                            int count = 0;

                            while ((lastIndex = 
                                    fileString.indexOf(stringSearch, 
                                            lastIndex)) != -1) {
                                count++;
                                lastIndex += stringSearch.length() - 1;
                            }

                            if ((lastIndex = 
                                    fileString.indexOf(stringSearch, 
                                            lastIndex)) == -1) {
                                System.out.println("String does not exist!");
                            }

                            int printCount = 0;
                            while (printCount < count) {
                                System.out.println(printCount + 1 + ") " 
                                        + stringSearch);
                                printCount++;
                            }

                            unencryptedFileReader.close();
                            unencryptedFile.delete();
                            break;

                        case 3:
                            return;

                        default:
                            System.out.println("Please enter a valid option.");
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You did not enter a password");
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Please enter options from 1 to 3 "
                        + "in number format only");
            } catch (IllegalArgumentException exc) {
                System.out.println("Wrong password entered");
                break;
            } 
        }
    }

}
