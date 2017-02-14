package addis.ali;

import java.util.*;

/**
 * Created by huangfeifeng on 14/02/2017.
 */
public class TradeDayUtilTest {

    public static void main(String... args) {
        HashMap<Long, String> testCase = new HashMap<Long, String>();

        List<Long> testCases = new ArrayList<Long>();


        long hourStamp = 60 * 60 * 1000;
        long timeStamp = 1467648000000l;

        for (int i = 0; i < 97; i++) {
            testCases.add(timeStamp);
            timeStamp += hourStamp;
        }

        TradeDayUtil.addHolidy(Collections.singletonList(1467734400000l));

        for (Long time : testCases) {
            System.out.println(new Date(time) + " : " + TradeDayUtil.getTradeDay(time, 0));
        }

//        testCase.put(1467331200000l, "20160701");
//        testCase.put(1467363600000l, "20160704");

//        TradeDayUtil.addHolidy(Collections.singletonList(1467363600000l));
//        for (Long time : testCase.keySet()) {
//            System.out.println(new Date(time) + " : " + TradeDayUtil.getTradeDay(time, 0));
//        }

    }
}
