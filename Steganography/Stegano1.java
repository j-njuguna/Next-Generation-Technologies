package com.company;
/**
 * Skeleton code for Steganography assignment.
 *
 * @author Michael Schukat
 * @version 1.0
 */

import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.FileReader;
        import java.io.FileWriter;
        import java.io.IOException;

public class Stegano1
{
    /**
     * Constructor for objects of class inpFile
     */
    public Stegano1()
    {
    }

    public static void main(String[] args) {
        String arg1, arg2, arg3, arg4;
        Boolean err = false;

        if (args != null && args.length > 1) { // Check for minimum number of arguments
            arg1 = args[0];
            arg2 = args[1];

            if (arg2 == "") {
                err = true;
            }
            else if ((arg1 == "A") && (args.length > 3)){
                // Get other arguments
                arg3 = args[2];
                arg4 = args[3];
                if (arg3 == "" || arg4 == "") {
                    err = true;
                }
                else {
                    // Hide bitstring
                    hide(arg2, arg3, arg4);
                }
            }
            else if (arg1 == "E"){
                // Extract bitstring from text
                retrieve(arg2);
            }
            else {
                err = true;
            }
        }
        else {
            err = true;
        }

        if (err == true) {
            System.out.println();
            System.out.println("Use: Stegano1 <A:E><Input File><OutputFile><Bitstring>");
            System.out.println("Example: Stegano1 A inpFile.txt out.txt 0010101");
            System.out.println("Example: Stegano1 E outFile.txt");

        }
    }

    static void hide(String inpFile, String outFile, String binString) {
        //
        BufferedReader reader;
        BufferedWriter writer;

        try {
            reader = new BufferedReader(new FileReader(inpFile));
            writer = new BufferedWriter(new FileWriter(outFile));
            String line = reader.readLine();
            while (line != null) {
                // Your code starts here
                String bitvector ="0010101";
                String zero = " "; //space to hide the zero
                String one = "  "; //double space to hide the one
                for (int i=0; i<bitvector.length();i++){
                    char c = bitvector.charAt(i);
                    if(c=='0'){
                        line = line+zero;
                    }
                    else {
                        line = line+one;
                    }
                    //Process char
                }

                // Store amended line in output file
                writer.write(line);
                writer.newLine();
                // read next line
                line = reader.readLine();
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void retrieve(String inpFile) {
        BufferedReader reader;

        int i=0;
        try {
            reader = new BufferedReader(new FileReader(inpFile));
            String line = reader.readLine();
            while (line != null) {
                // Your code starts here
                if((line.charAt(i)==32)) {
                    System.out.println("0");
                }else if((line.charAt(i)==32 && line.charAt(i+1)==32)){
                    System.out.println("1");
                }
                System.out.println(line);


                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
