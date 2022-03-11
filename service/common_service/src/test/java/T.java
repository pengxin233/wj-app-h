import cn.hutool.core.date.*;

import java.util.Date;


public class T {
    public static void main(String[] args) {
        DateTime dateTime = DateUtil.date();

        DateTime parse = DateUtil.parse("2022-02-10 14:58:03");
        Date date = parse.toJdkDate();

        System.out.println(dateTime);

        long between = dateTime.between(date, DateUnit.DAY);

        System.out.println(between);

        DateTime offset = dateTime.offset(DateField.DAY_OF_MONTH, 1);
        System.out.println(offset);
        final String s1 = offset.toDateStr();
        System.out.println(s1);
    }

}
