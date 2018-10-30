public class Punkt3D extends Punkt2D{
    protected double z;
    public Punkt3D(double _x, double _y, double _z){
        super(_x,_y);
        z = _z;
    }
    public double getZ() {
        return z;
    }
    public void setZ(double _z){
        z = _z;
    }
    public double distance(Punkt3D p3d) {
        double x1 = p3d.getX();
        double y1 = p3d.getY();
        double z1 = p3d.getZ();
        double dist = Math.sqrt(Math.pow((x - x1),2) + Math.pow((y - y1),2) + Math.pow((z - z1),2));
        return dist;
    }
}
