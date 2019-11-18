package contacts;

abstract class MultipleChoiceCommand extends Command {
    MultipleChoiceCommand(Contacts contacts) {
        super(contacts);
    }

    void removeContact(int record) {
        contacts.contacts.remove(record);
        System.out.println("The record removed!\n");
    }

    void editContact(int record) {
        System.out.printf("Select a field (%s): ",
                contacts.contacts.get(record).getFields());
        String field = contacts.scanner.next();
        System.out.printf("Enter %s: ", field);
        contacts.scanner.nextLine();
        String value = contacts.scanner.nextLine();
        contacts.contacts.get(record).setField(field, value);

        System.out.println("Saved.");
        contacts.contacts.get(record).print();
    }

    boolean isNumber(String num) {
        return num.matches("-?\\d+(\\.\\d+)?");
    }

    abstract void printList();
}