package toVehicle;

public class Coordinates {
    private long x;
    private Float y; //Максимальное значение поля: 597, Поле не может быть null
    public Coordinates(long x, Float y) {
        this.x = x;
        if (y != null && y <= 597) {
            this.y = y;
        } else {
            throw new IllegalArgumentException("Недопустимое значение y");
        }
    }
    public void setY(Float y) {
        this.y = y;
    }

    public void setX(long x) {
        this.x = x;
    }
}
