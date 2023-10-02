package by.teachmeskills.linkedlist;

import java.util.LinkedList;

public class MyLinkedList {
    public static void main(String[] args) {

        LinkedList<String> states = new LinkedList<String>();

        states.add("Table");
        states.add("Chair");
        states.addLast("Wardrobe");
        states.addFirst("Armchair");
        states.add(1, "Mirror");

        System.out.printf("List has %d elements \n", states.size());
        System.out.println(states.get(1));
        states.set(1, "Lamp");
        for (String state : states) {

            System.out.println(state);
        }
        if (states.contains("Table")) {

            System.out.println("List contains Table");
        }
        states.remove("Table");
        states.removeFirst();
        states.removeLast();
    }
}