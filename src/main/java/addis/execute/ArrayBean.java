package addis.execute;

import java.util.Arrays;

/**
 * Created by huangfeifeng on 2/22/16.
 */
public class ArrayBean {
    Integer[] integers;
    int[] ints;
    long[] longs;
    byte[] byts;
    double[] doubles;
    String[] strs;
    Item[] items;

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public int[] getInts() {
        return ints;
    }

    public void setInts(int[] ints) {
        this.ints = ints;
    }

    public long[] getLongs() {
        return longs;
    }

    public void setLongs(long[] longs) {
        this.longs = longs;
    }

    public byte[] getByts() {
        return byts;
    }

    public void setByts(byte[] byts) {
        this.byts = byts;
    }

    public double[] getDoubles() {
        return doubles;
    }

    public void setDoubles(double[] doubles) {
        this.doubles = doubles;
    }

    public String[] getStrs() {
        return strs;
    }

    public void setStrs(String[] strs) {
        this.strs = strs;
    }

    @Override
    public String toString() {
        return "ArrayBean{" +
                "ints=" + Arrays.toString(ints) +
                ", longs=" + Arrays.toString(longs) +
                ", byts=" + Arrays.toString(byts) +
                ", doubles=" + Arrays.toString(doubles) +
                ", strs=" + Arrays.toString(strs) +
                ", items=" + Arrays.toString(items) +
                '}';
    }
}
