package by.teachmeskills.linkedlist;

import java.util.LinkedList;

public class MyLinkedList {
    public static void main(String[] args) {

        LinkedList<String> furniture = new LinkedList<String>();

        furniture.add("Table");
        furniture.add("Chair");
        furniture.addLast("Wardrobe");
        furniture.addFirst("Armchair");
        furniture.add(1, "Mirror");

        System.out.printf("List has %d elements \n", furniture.size());
        System.out.println(furniture.get(1));
        furniture.set(1, "Lamp");
        for (String state : furniture) {

            System.out.println(state);
        }
        if (furniture.contains("Table")) {

            System.out.println("List contains Table");
        }
        furniture.remove("Table");
        furniture.removeFirst();
        furniture.removeLast();
    }
}