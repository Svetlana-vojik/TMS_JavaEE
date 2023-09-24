package by.teachmeskills.prototype;


import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Prototype robot = new Robot();

        List< Prototype > robotList = new ArrayList<Prototype>();

        robotList.add(robot.reproduce());
        robotList.add(robot.reproduce());
        robotList.add(robot.reproduce());

        for (Prototype p : robotList)
            System.out.println(p);
    }
}
