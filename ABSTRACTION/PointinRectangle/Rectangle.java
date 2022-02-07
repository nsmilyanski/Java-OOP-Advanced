package ABSTRACTION.PointinRectangle;

public class Rectangle {
    private final Point left;
    private final Point right;


    public Rectangle(Point left, Point right) {
        this.left = left;
        this.right = right;
    }

    public boolean contains(Point point){
        return ((point.getX() >= left.getX() && point.getX() <= right.getX()) ||
                (point.getX() <= left.getX() && point.getX() >= right.getX()))&&
                ((point.getY() >= left.getY() && point.getY() <= right.getY()) ||
                        (point.getY() <= left.getY() && point.getY() >= right.getY()));

    }
}
