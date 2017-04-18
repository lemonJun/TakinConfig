package com.takin.config.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

/**
 * @author Robert HG (254963746@qq.com) on 6/23/14.
 */
public class CollectionUtil {

    private CollectionUtil() {
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(Map map) {
        return map != null && map.size() > 0;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Map map) {
        return !isNotEmpty(map);
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && collection.size() > 0;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return !isNotEmpty(collection);
    }

    @SuppressWarnings("rawtypes")
    public static int sizeOf(Collection collection) {
        if (isEmpty(collection)) {
            return 0;
        }
        return collection.size();
    }

    /**
     * 返回第一个列表中比第二个多出来的元素
     *
     * @param list1
     * @param list2
     * @return
     */
    public static <T> List<T> getLeftDiff(List<T> list1, List<T> list2) {
        if (isEmpty(list2)) {
            return list1;
        }
        List<T> list = new ArrayList<T>();
        if (isNotEmpty(list1)) {
            for (T o : list1) {
                if (!list2.contains(o)) {
                    list.add(o);
                }
            }
        }
        return list;
    }

    /**
     * 把list中满足equals的对象移除掉
     * @author:sunlingao@58.com
     * @date:2017年3月22日
     * @param list
     * @param name
     * @return
     * List<T>
     */
    public static <T> List<T> filterList(List<T> list, String[] name) {
        if (isEmpty(list) || name == null || name.length == 0)
            return list;
        List<T> result = Lists.newArrayList();
        for (T t : list) {
            boolean flag = false;
            for (String s : name) {
                if (t != null && t.equals(s)) {
                    flag = true;
                    break;
                }
                if (!flag)
                    result.add(t);
            }
        }
        return result;
    }
}
