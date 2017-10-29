/**
 * Created by Favio on 6/6/2017.
 */
public class Owl extends Nocturnal {
    int id = 2;
    protected boolean isBlind() {
        return false;
    }

    public static void main(String[] args) {
        Nocturnal nocturnal = new Owl();
        System.out.println(nocturnal.isBlind());
        System.out.println(nocturnal.id);
    }
}

class Nocturnal {
    int id = 1;
     protected boolean isBlind() {
        return true;
    }
}
