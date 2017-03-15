package com.sdajava.rwobj;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        String fileName = "c://Users//lukas//obiekty.txt";
        Person p1 = new Person("Jan", "Kowalski", 21);
        Person p2 = new Person("Lukasz", "Kowalski", 21);


        OutputStream  fileOutputStream = null;
        ObjectOutput objectOutputStream = null;
        OutputStream bufferOut  = null;
        try {

            fileOutputStream = new FileOutputStream(fileName, true);
            bufferOut = new BufferedOutputStream(fileOutputStream);
            objectOutputStream = new ObjectOutputStream(bufferOut);

            objectOutputStream.writeObject(p1);
            objectOutputStream.writeObject(p2);

            objectOutputStream.close();
        } finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            fileOutputStream.close();
            bufferOut.close();
            objectOutputStream.close();

        }

        //Thread.sleep(2000);

        try {

            FileInputStream fileInputStream = new FileInputStream(fileName);
            InputStream bufferIn = new BufferedInputStream(fileInputStream);
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(bufferIn);

            Person readCase = null;
            List<Person> recordList = new ArrayList<>();


            while (true) {
                try {
                    readCase = (Person) objectInputStream.readObject();
                    recordList.add(readCase);
                }
                catch (EOFException exc) {
                    break;
                }
            }

            recordList.forEach(s -> System.out.println(s.getName()));

            fileOutputStream.close();
            objectOutputStream.close();

        } catch (FileNotFoundException err){
            err.printStackTrace();
        }
    }
}
