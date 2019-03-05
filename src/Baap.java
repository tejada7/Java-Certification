/**
 * Created by Favio on 10/9/2017.
 */
public class Baap {
    int h = 4;
    public int getH() {
        System.out.println("Baap:" + h);
        return h;
    }

    public static void main(String[] args) {
        Baap b = new Beta();
        System.out.println(b.h + " " + b.getH()); // 4 beta 44 44
        Beta bb = (Beta) b;
        System.out.println(bb.h + " " + bb.getH()); // 44 beta 44 44
    }
}
class Beta extends Baap {
    public int h = 44;
    public int getH() {
        System.out.println("Beta:" + h);
        return h;
    }
}

