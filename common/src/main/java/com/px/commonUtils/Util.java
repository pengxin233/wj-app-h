package com.px.commonUtils;

import java.util.*;

/**
 * @author pengxin
 */
public class Util {
    private static final List<Character> STR_LIST = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',

            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',

            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',

            '~', '!', '@', '#', '$', '%', '^', '-', '+');
    private static final Random RANDOM = new Random();

    public static String getRandomStr(int len){
        StringBuilder sb = new StringBuilder();

        Collections.shuffle(STR_LIST);

        for (int i=0;i<len;i++){
            sb.append(STR_LIST.get(RANDOM.nextInt(STR_LIST.size())));
        }

        return sb.toString();
    }

}
