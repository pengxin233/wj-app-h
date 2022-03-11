import cn.hutool.core.date.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class T {
    public static void main(String[] args) {
        List<Integer> array = new ArrayList<Integer>();
        List<Integer> link = new LinkedList<Integer>();

        long startTime = 0;
        long endTime = 0;
        int num = 150;

        startTime=System.currentTimeMillis();
        for(int i=0; i<num; i++) {
            array.add(i);
        }
        endTime=System.currentTimeMillis();
        System.out.println("ArrayList add 花费时间: " + (endTime - startTime));

        startTime=System.currentTimeMillis();
        for(int i=0; i<num; i++) {
            link.add(i);
        }
        endTime=System.currentTimeMillis();
        System.out.println("LinkedList add 花费时间: " + (endTime - startTime));

        startTime=System.currentTimeMillis();
        for(int i=0; i<num; i++) {
            array.get(i);
        }
        endTime=System.currentTimeMillis();
        System.out.println("for 遍历  ArrayList get 花费时间: " + (endTime - startTime));

        startTime=System.currentTimeMillis();
        for(int i=0; i<num; i++) {
            link.get(i);
        }
        endTime=System.currentTimeMillis();
        System.out.println("for 遍历 LinkedList get 花费时间: " + (endTime - startTime));

        startTime=System.currentTimeMillis();
        for(int i : array) {
//            System.out.print(i+"\r");
        }
        endTime=System.currentTimeMillis();
        System.out.println("forEach 遍历  ArrayList get 花费时间: " + (endTime - startTime));

        startTime=System.currentTimeMillis();
        for(int i : link) {
//            System.out.print(i+"\r");
        }
        endTime=System.currentTimeMillis();
        System.out.println("forEach 遍历 LinkedList get 花费时间: " + (endTime - startTime));

        startTime=System.currentTimeMillis();
        array.stream().forEach(item -> {});
        endTime=System.currentTimeMillis();
        System.out.println("stream 遍历 LinkedList get 花费时间: " + (endTime - startTime));

        startTime=System.currentTimeMillis();
        link.stream().forEach(item -> {});
        endTime=System.currentTimeMillis();
        System.out.println("stream 遍历 LinkedList get 花费时间: " + (endTime - startTime));
    }

}
