package contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Contacts implements Serializable {
    List<Entry> contacts = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private String filename = "file.ser";

    private void serialize() {
        try {
            List<Entry> list = contacts;
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("MyData.ser"));
            out.writeObject(list);
            out.close();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(-1);
        }
    }

    private void deserialize() throws Exception{
        FileInputStream fileIn = new FileInputStream("MyData.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);

        contacts = (List<Entry>) in.readObject();
    }

    void start() throws Exception {
        File f = new File("MyData.ser");
        if(f.exists() && !f.isDirectory()) {
            deserialize();
        }

        Command command;
        String choice;

        contacts:
        while (true) {
            System.out.print("Enter action (add, remove, edit, count, info, exit): ");
            choice = scanner.next();
            switch (choice) {
                case "add":
                    command = new AddContactCommand(this);
                    command.execute();
                    break;
                case "remove":

                    break;
                case "edit":
                    command = new EditContactCommand(this);
                    command.execute();
                    break;
                case "count":
                    System.out.printf("The phone book has %s records.\n",
                            contacts.size());
                    break;
                case "info":
                    command = new ShowInfoCommand(this);
                    command.execute();
                    break;
                case "exit":
                    break contacts;
            }
        }

        serialize();
    }
}
