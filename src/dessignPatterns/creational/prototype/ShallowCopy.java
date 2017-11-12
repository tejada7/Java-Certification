package dessignPatterns.creational.prototype;

import java.util.List;

/**
 * Created by Favio on 11/11/2017.
 */
public class ShallowCopy implements Cloneable {

    private String sql;
    private List<String> parameters;
    private Record record;

    public ShallowCopy(String sql, List<String> parameters, Record record) {
        this.sql = sql;
        this.parameters = parameters;
        this.record = record;
    }

    public ShallowCopy clone() {
        try {
            return (ShallowCopy) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSql() {
        return sql;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "ShallowCopy{" +
                "sql='" + sql + '\'' +
                ", parameters=" + parameters +
                ", record=" + record +
                '}';
    }
}
