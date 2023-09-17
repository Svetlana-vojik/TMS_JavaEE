package by.teachmeskills.prototype;

public class Robot implements Prototype {
    public Prototype reproduce() {
        Prototype robot = null;
        try {
            robot = (Prototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.getMessage();
        }
        return robot;
    }

    public String toString() {
        return "Hello! I'm a new robot.";
    }
}