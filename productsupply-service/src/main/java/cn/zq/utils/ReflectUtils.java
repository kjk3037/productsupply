package cn.zq.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectUtils {
    /**
     * 返回 entity 对象的所有属性，包含父类
     * @param obj
     * @return
     */
    public static List<Field> getObjectFields(Object obj){
        return getObjectFields(obj.getClass());
    }

    public static List<Field> getObjectFields(Class<?> clazz){
        List<Field> fieldList = new ArrayList<>() ;
        while (clazz != null){//当父类为null的时候说明到达了最上层的父类(Object类).
            fieldList.addAll(Arrays.asList(clazz .getDeclaredFields()));
            clazz = clazz.getSuperclass(); //得到父类,然后赋给自己
        }
        return fieldList;
    }

    /**
     * 判断 Class entity 是否存在名称为 fieldName 的属性
     * @param fieldName
     * @param entity
     * @return
     */
    public static Boolean isField(String fieldName , Object entity){
        List<Field> fieldList = getObjectFields(entity);
        for (Field f1:fieldList
        ) {
            if (fieldName.equals(f1.getName()))
                return true;
        }
        return false;
    }

    /**
     * 返回 entity 对象中的所有方法，包含父类
     * @param entity
     * @return
     */
    public static List<Method> getObjectMethods(Object entity){
        return getObjectMethods(entity.getClass());
    }

    public static List<Method> getObjectMethods(Class<?> clazz){
        List<Method> methods = new ArrayList<>();
        while (clazz != null && clazz != Object.class) {//当父类为null的时候说明到达了最上层的父类(Object类).
            methods.addAll(Arrays.asList(clazz .getDeclaredMethods()));
            clazz = clazz.getSuperclass(); //得到父类,然后赋给自己
        }
        return methods;
    }

    /**
     * 判断 Class entity 是否存在名称为 methodName 的方法
     * @param methodName
     * @param entity
     * @return
     */
    public static Boolean isMethod(String methodName , Object entity){
        List<Method> methods = getObjectMethods(entity);
        for (Method m1:methods
        ) {
            if (methodName.equals(m1.getName()))
                return true;
        }
        return false;
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredMethod
     * @param obj
     * @param methodName
     * @param parameterTypes  方法参数类型
     * @return
     */
    public static Method getDeclaredMethod(Object obj , String methodName , Class<?>...parameterTypes) {
        for (Class<?> clazz = obj.getClass(); clazz != Object.class && clazz != null; clazz = clazz.getSuperclass()) {
            try {
                return clazz.getDeclaredMethod(methodName, parameterTypes);
            } catch (Exception e) {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz=clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }
        return null;
    }

    public static Object invoke(Object object, String methodName, Class<?>[] parameterTypes,
                                Object[] parameters){
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        try {
            if (method != null){
                method.setAccessible(true);
                // 调用object 的 method 所代表的方法，其方法的参数是 parameters
                return method.invoke(object, parameters);
            }
        }catch (Exception e1){
            e1.printStackTrace();
        }
        return null;
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredField
     *
     * @param object
     *            : 子类对象
     * @param fieldName
     *            : 父类中的属性名
     * @return 父类中的属性对象
     */

    public static Field getDeclaredField(Object object, String fieldName) {
        Field field = null;

        Class<?> clazz = object.getClass();

        for (; clazz != Object.class && clazz != null; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了

            }
        }

        return null;
    }

    /**
     * 直接设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter
     *
     * @param object
     *            : 子类对象
     * @param fieldName
     *            : 父类中的属性名
     * @param value
     *            : 将要设置的值
     */

    public static void setFieldValue(Object object, String fieldName, Object value) {

        // 根据 对象和属性名通过反射 调用上面的方法获取 Field对象
        Field field = getDeclaredField(object, fieldName);

        if (field != null){
            // 抑制Java对其的检查
            field.setAccessible(true);
            try {
                // 将 object 中 field 所代表的值 设置为 value
                field.set(object, value);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 直接读取对象的属性值, 忽略 private/protected 修饰符, 也不经过 getter
     *
     * @param object
     *            : 子类对象
     * @param fieldName
     *            : 父类中的属性名
     * @return : 父类中的属性值
     */

    public static Object getFieldValue(Object object, String fieldName) {
        // 根据 对象和属性名通过反射 调用上面的方法获取 Field对象
        Field field = getDeclaredField(object, fieldName);

        if (field != null){
            // 抑制Java对其的检查
            field.setAccessible(true);
            try {
                // 获取 object 中 field 所代表的属性值
                return field.get(object);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
