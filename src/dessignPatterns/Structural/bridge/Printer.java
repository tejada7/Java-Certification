package dessignPatterns.Structural.bridge;

import java.util.List;

/**
 * Created by Favio on 12/11/2017.
 */
public abstract class Printer {

    public String print(Formatter formatter) {
        return formatter.format(getHeader(), getDetails());
    }

    abstract protected String getHeader();

    abstract protected List<Detail> getDetails();
}
