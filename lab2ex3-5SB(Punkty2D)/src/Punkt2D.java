public class Punkt2D {
    protected double x;
    protected double y;
    public Punkt2D(double _x, double _y){
        x = _x;
        y = _y;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setX(double _x){
        x = _x;
    }
    public void setY(double _y){
        y = _y;
    }
    public double distance(Punkt2D p2d){
        double x1 = p2d.getX();
        double y1 = p2d.getY();
        double dist = (x-x1) + (y -y1);
        return dist;
    }
}
