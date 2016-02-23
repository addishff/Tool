package addis.execute;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Boolean;
import java.lang.Object;
import java.lang.String;
import java.lang.System;
import java.util.HashMap;
import java.util.Scanner;
import java.util.SortedMap;

/**
 * Created by huangfeifeng on 2/23/16.
 */
public class CompareProperties {
    public static HashMap readProperties(String path) throws FileNotFoundException {
        File file = new File(path);
        if (!file.exists())
            return null;
        Scanner scan = new Scanner(file);
        HashMap<String, String> properties = new HashMap<String, String>();
        while (scan.hasNext()) {
            properties.put(scan.next(), scan.next());
        }
        return properties;
    }

    public static Boolean compare(HashMap map1, HashMap map2) {
        boolean result = true;
        for (Object o : map1.keySet()) {
            if (map2.containsKey(o) && map1.get(o).equals(map2.get(o)))
                continue;
            else {
                System.out.println();
                System.out.println("map1->" + o + "><" + map1.get(o));
                System.out.println("map2->" + o + "><" + map2.get(o));
                result = false;
            }
        }
        return result;
    }

    public static void main(String... args) throws FileNotFoundException {
        if (args.length < 2) {
            System.out.println("参数不够");
            return;
        }
        HashMap compare1 = readProperties(args[0]);
        HashMap compare2 = readProperties(args[1]);
        if (compare1 == null || compare2 == null) {
            System.out.println("文件不存在！");
            return;
        }
        System.out.println(compare(compare1, compare2));

    }
}
