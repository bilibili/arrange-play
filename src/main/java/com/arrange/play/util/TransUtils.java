package com.arrange.play.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransUtils {

    public static Splitter.MapSplitter stringToMap = Splitter.on("&").withKeyValueSeparator("-");

    public static Splitter.MapSplitter uriToMap = Splitter.on("&").withKeyValueSeparator("=");

    public static Joiner.MapJoiner mapToUri = Joiner.on("&").withKeyValueSeparator("=");

    public static Map<String, String> transUriToMap(String uri) {
        if (StringsUtils.isEmptyOrNull(uri)) {
            return null;
        }
        return uriToMap.split(uri);
    }

    public static Map<String,String> beanToMap(Object bean){
        HashMap<String,String> map = Maps.newHashMap();
        if(null == bean){
            return map;
        }
        Class<?> clazz = bean.getClass();
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        for(PropertyDescriptor descriptor : descriptors){
            String propertyName = descriptor.getName();
            if(!"class".equals(propertyName)){
                Method method = descriptor.getReadMethod();
                Object result;
                try {
                    result = method.invoke(bean);
                    if(null != result){
                        map.put(propertyName, result.toString());
                    }else{
                        map.put(propertyName, "");
                    }
                } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return map;
    }

    public static Object mapToBean(Class clazz,Map map){
        Object object = null;
        try {
            object = clazz.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);

            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for(PropertyDescriptor descriptor : descriptors){
                String propertyName = descriptor.getName();
                if(map.containsKey(propertyName)){
                    Object value = map.get(propertyName);
                    Object[] args = new Object[1];
                    args[0] = value;
                    descriptor.getWriteMethod().invoke(object, args);
                }
            }

        } catch (InstantiationException | InvocationTargetException | IllegalArgumentException | IntrospectionException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return object;
    }

    public static List<String> beanToList(Object bean) {
        List<String> list = Lists.newArrayList();
        if(null == bean){
            return list;
        }
        Class<?> clazz = bean.getClass();
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        for(PropertyDescriptor descriptor : descriptors) {
            String propertyName = descriptor.getName();
            if (!"class".equals(propertyName)) {
                list.add(propertyName);
            }
        }

        return list;
    }

    public static Map<String,String> ctomap (Object obj) throws IllegalAccessException {

        Map<String,String> map = new HashMap<>();
        Field[]  fie1 = obj.getClass().getDeclaredFields();
        for(int i=0; i<fie1.length;i++) {
            fie1[i].setAccessible(true);
            if(fie1[i].get(obj)==null) {
                map.put(fie1[i].getName(),"null");
            }else{
                map.put(fie1[i].getName(),fie1[i].get(obj).toString());
            }
        }
        return map;
    }

    public static Object[][] mapTo2Array(Map map) {
        Object[][] objects = new Object[map.size()][2];
        int i = 0;
        for (Object key : map.keySet()) {
            objects[i][0] = key;
            objects[i][1] = map.get(key);
            i++;
        }
        return objects;
    }

    public static Map<String, String> jsonToMap(JSONObject object) {
        Map<String, String> map = Maps.newHashMap();
        for (String key : object.keySet()) {
            map.put(key, object.getString(key));
        }
        return map;
    }

    public static JSONObject mapToJson(Map<String, String> map) {
        return JSON.parseObject(JSON.toJSONString(map));
    }

    public static void main(String[] args) {
        List<Integer> payLimits = new ArrayList<Integer>();
        payLimits.add(5);
        payLimits.add(10);
        payLimits.add(20);
        payLimits.add(30);

        payLimits.remove("30");
        payLimits.remove("20");

        System.out.println(JSON.toJSONString(payLimits));
        String test = "sid=${id}&category=1&state=1&attribute=1&stime=${stime}&etime=${etime}&tags=${tags}";
        Map<String, String> map = uriToMap.split(test);
        JSONObject jsonObject = mapToJson(map);
        System.out.println(jsonObject);
        System.out.println(mapToUri.join(jsonObject));
    }




}
