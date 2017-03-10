package addis.ali;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by huangfeifeng on 13/02/2017.
 * <p/>
 * 工具类,获取T+N交易日,类内部时间戳偏移到第8时区;
 */
public class TradeDayUtil {

    //以Unix时间存储节假日,偏移到第8时区,单位毫秒,精确到天
    private static final ConcurrentHashMap<Long, Integer> HOLIDAYS = new ConcurrentHashMap<Long, Integer>();
    //一周的时间长度,单位毫秒
    private static final long WEEK_SPAN = 7 * 24 * 60 * 60 * 1000;
    //一天的时间长度,单位毫秒
    private static final long DAY_SPAN = 24 * 60 * 60 * 1000;
    //交易日下午结束时间,单位毫秒
    private static final long PM_END_TIME = 15 * 60 * 60 * 1000;
    //第8时区时间偏移,单位毫秒
    private static final long GMT8_OFFSET = 8 * 60 * 60 * 1000;

    /***
     * 初始化,参数为Unix时间戳列表,单位毫秒
     *
     * @param holidayMills
     */
    public static void init(List<Long> holidayMills) {
        addHolidy(holidayMills);
    }

    /***
     * 添加节假日,参数为Unix时间戳列表,单位毫秒
     *
     * @param holidayMills
     */
    public static void addHolidy(List<Long> holidayMills) {
        if (holidayMills == null || holidayMills.isEmpty())
            return;

        HashMap<Long, Integer> holidayHash = new HashMap<Long, Integer>(holidayMills.size());
        for (Long holiday : holidayMills) {
            if (holiday < 0)
                continue;

            //偏移到第8时区
            holiday += GMT8_OFFSET;

            //如果是周末,舍去该假日
            if (isWeekend(holiday))
                continue;

            //去除时间尾数,精确到天级别
            long holidayTemp = (holiday / DAY_SPAN) * DAY_SPAN;

            holidayHash.put(holidayTemp, 0);
        }
        HOLIDAYS.putAll(holidayHash);
    }

    /***
     * 获取给定时间的T+n的交易日期
     *
     * @param dayMills unix时间戳,如需其他格式参数,再封装一个接口
     * @param n
     * @return
     */
    public static String getTradeDay(long dayMills, int n) {
        if (n < 0 || dayMills < 0) {
            System.out.println("param error!");
            return "";
        }

        //偏移到第8时区
        dayMills += GMT8_OFFSET;

        //所属日期
        long day = (dayMills / DAY_SPAN) * DAY_SPAN;

        //天内时间
        long dayTime = dayMills % DAY_SPAN;

        if (dayTime > PM_END_TIME)
            day += DAY_SPAN;

        int nTemp = n;
        //T+n中的n不会太大,所以直接遍历了
        while (nTemp >= 0) {
            if (isTradeDay(day)) {
                nTemp--;
                if (nTemp < 0)
                    break;
                day += DAY_SPAN;
            } else {
                day += DAY_SPAN;
            }
        }
        return getGmt8Time(day);
    }

    /***
     * 获取当前时间的T+0交易日
     *
     * @return
     */
    public static String getCurrentTradeDay() {
        return getTradeDay(System.currentTimeMillis(), 0);
    }

    /***
     * 移除节假日,参数为Unix时间戳列表,单位毫秒,非原子操作,true返回才表示全部完成
     *
     * @param holidayMills
     * @return
     */
    public static boolean removeHolidy(List<Long> holidayMills) {
        if (holidayMills == null || holidayMills.isEmpty())
            return true;

        for (Long holiday : holidayMills) {
            if (holiday < 0)
                continue;
            holiday += GMT8_OFFSET;

            //如果是周末,舍去该假日
            if (isWeekend(holiday))
                continue;

            //去除时间尾数,精确到天级别
            long holidayTemp = (holiday / DAY_SPAN) * DAY_SPAN;

            HOLIDAYS.remove(holidayTemp);
        }
        return true;
    }

    private static boolean isTradeDay(Long dayMills) {
        if (isWeekend(dayMills) || isHoliday(dayMills))
            return false;
        return true;
    }

    private static boolean isHoliday(Long dayMills) {
        long dayTime = (dayMills / DAY_SPAN) * DAY_SPAN;
        if (HOLIDAYS.containsKey(dayTime))
            return true;
        return false;
    }

    private static boolean isWeekend(Long dayMills) {
        //计算周几,并且偏移到周四(1970.1.1为周四)
        long week = ((dayMills % WEEK_SPAN) / DAY_SPAN + 4) % 7;
        if (week == 6 || week == 0)
            return true;
        return false;
    }

    private static String getGmt8Time(long dayMills) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH z");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("ETC/GMT-8"));
        Date date = new Date(dayMills - GMT8_OFFSET);
        return simpleDateFormat.format(date);
    }

    public static void main(String... args) {
        List<Long> testCases = new ArrayList<Long>();
        long hourStamp = 60 * 60 * 1000;
        long timeStamp = 1467648000000l;//2016/7/5 0:0:0

        for (int i = 0; i < 97; i++) {
            testCases.add(timeStamp);
            timeStamp += hourStamp;
        }

        TradeDayUtil.addHolidy(Collections.singletonList(1467734400000l));//2016/7/6 0:0:0

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        for (Long time : testCases) {
            System.out.println(simpleDateFormat.format(new Date(time)) + " : " + TradeDayUtil.getTradeDay(time, 0));
        }
    }
}
