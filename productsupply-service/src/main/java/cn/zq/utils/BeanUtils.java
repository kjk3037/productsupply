package cn.zq.utils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BeanUtils implements ApplicationContextAware {
    static ApplicationContext context;
    public static Object getBean(String name){
        return context.getBean(name);
    }
    public static Object getBean(Class clazz){
        return context.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
    }
    public static void copy(Object source,Object target) throws Exception {
        Field[] SdeclaredFields = source.getClass().getDeclaredFields();
        Field[] TdeclaredFields = target.getClass().getDeclaredFields();
        List<Field> sfields = Arrays.asList(SdeclaredFields);
        List<Field> tfields = Arrays.asList(TdeclaredFields);
        sfields.forEach(a -> {
            loop:
            for (Field tfield : tfields) {
                if (a.getName().equals(tfield.getName())) {
                    String methodName = "set" + a.getName().substring(0, 1).toUpperCase() + a.getName().substring(1);
                    List<String> methodList = Arrays.asList(target.getClass().getMethods()).stream().map(Method::getName).collect(Collectors.toList());
                    String methodGetName = "get" + a.getName().substring(0, 1).toUpperCase() + a.getName().substring(1);
                    if (!methodList.contains(methodName)) {
                        continue loop;
                    }
                    try {
                        Method getmethod = source.getClass().getDeclaredMethod(methodGetName);
                        Object resultGet = getmethod.invoke(source);
                        if (resultGet != null) {
                            Method method = target.getClass().getDeclaredMethod(methodName, resultGet.getClass());
                            Object result = method.invoke(target, resultGet);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    public static void copyF(Object source,Object target) throws Exception {
//        Field[] SdeclaredFields = source.getClass().getDeclaredFields();
//        Field[] TdeclaredFields = target.getClass().getSuperclass().getDeclaredFields();
        List<Field> sfields = getAllField(source);
        List<Field> tfields = getAllField(target);
        sfields.forEach(a -> {
            loop:
            for (Field tfield : tfields) {
                if (a.getName().equals(tfield.getName())) {
                    String methodName = "set" + a.getName().substring(0, 1).toUpperCase() + a.getName().substring(1);
                    List<String> methodList = Arrays.asList(target.getClass().getMethods()).stream().map(Method::getName).collect(Collectors.toList());
                    String methodGetName = "get" + a.getName().substring(0, 1).toUpperCase() + a.getName().substring(1);
                    if (!methodList.contains(methodName)) {
                        continue loop;
                    }
                    try {
                        Method getmethod = getAllMethod(source).stream().filter(Method ->Method.getName().equals(methodGetName)).collect(Collectors.toList()).get(0);
                        Object resultGet = getmethod.invoke(source);
                        if (resultGet != null) {
                            Method method = getAllMethod(target).stream().filter(Method ->Method.getName().equals(methodName)).collect(Collectors.toList()).get(0);
                            Object result = method.invoke(target, resultGet);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    private static List<Field> getAllField(Object model) {
        Class clazz = model.getClass();
        List<Field> fields = new ArrayList<>();
        while (clazz != null) {
            fields.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }
    private static List<Method> getAllMethod(Object model) {
        Class clazz = model.getClass();
        List<Method> methods = new ArrayList<>();
        while (clazz != null) {
            methods.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredMethods())));
            clazz = clazz.getSuperclass();
        }
        return methods;
    }
}
