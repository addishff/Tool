package addis.execute;

import java.lang.Integer;import java.lang.Override;import java.lang.String; /**
 * Created by huangfeifeng on 2/22/16.
 */
public class Item {
    Integer integer;
    String name;

    public Item(Integer integer, String name) {
        this.integer = integer;
        this.name = name;
    }

    public Item() {
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "integer=" + integer +
                ", name='" + name + '\'' +
                '}';
    }
}
