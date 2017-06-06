package cn.edu.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 多层结构调试工具类
 *
 * @author Fsx
 */
public class ToStringUtil {
    private static int recursionLevel = 0;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat simpleSqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat simpleSqlTimeFormat = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat simpleSqlTimestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ms");

    private static final String NULL_VALUE = "『未设置属性』";
    private static final String EMPTY_CHAR = "『空字符』";
    private static final String EMPTY_STRING = "『空字符串』";
    private static final String SPACE = "『空格』";
    private static final String RETURN = "『回车符\\r』";
    private static final String NEW_LINE = "『换行符\\n』";

    /**
     * 调用本类的toString静态方法来打印出属性 代替System.out.print使用
     *
     * @param o 需要打印属性的对象
     */
    public static void print(Object o) {
        System.out.print(toString(o));
    }

    /**
     * 调用本类的toString静态方法来打印出属性 并按照指定规则排序 代替System.out.print使用
     *
     * @param o        需要打印属性的对象
     * @param sortRule 排序规则
     */
    public static void print(Object o, SORT_RULE sortRule) {
        setSortRule(sortRule);
        System.out.print(toString(o));
        resetSortRule();
    }

    /**
     * 调用本类的toString静态方法来打印出属性 代替System.out.println使用
     *
     * @param o 需要打印属性的对象
     */
    public static void println(Object o) {
        System.out.println(toString(o));
    }

    /**
     * 调用本类的toString静态方法来打印出属性 并按照指定规则排序 代替System.out.println使用
     *
     * @param o        需要打印属性的对象
     * @param sortRule 排序规则
     */
    public static void println(Object o, SORT_RULE sortRule) {
        setSortRule(sortRule);
        System.out.println(toString(o));
        resetSortRule();
    }

    /**
     * 对类和Map中的字段的排序规则
     */
    private static SORT_RULE sortRule = SORT_RULE.DEFAULT;

    /**
     * 排序规则
     */
    public static enum SORT_RULE {
        /**
         * 排序规则 - 默认不排序
         */
        DEFAULT,
        /**
         * 排序规则 - 升序
         */
        ASC,
        /**
         * 排序规则 - 降序
         */
        DESC
    }

    /**
     * Field排序比较器 - 升序
     */
    private static Comparator<Field> fieldComparatorAsc = new Comparator<Field>() {
        @Override
        public int compare(Field field1, Field field2) {
            if (field1 == null && field2 == null) {
                return 0;
            } else if (field1 == null && field2 != null) {
                return -1;
            } else if (field1 != null && field2 == null) {
                return 1;
            } else {
                return field1.getName().compareTo(field2.getName());
            }
        }
    };

    /**
     * Field排序比较器 - 降序
     */
    private static Comparator<Field> fieldComparatorDesc = new Comparator<Field>() {
        @Override
        public int compare(Field field1, Field field2) {
            return -fieldComparatorAsc.compare(field1, field2);
        }
    };

    /**
     * Map.Entry排序比较器 - 升序
     */
    private static Comparator<Map.Entry<Object, Object>> mapEntryComparatorAsc = new Comparator<Map.Entry<Object, Object>>() {
        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override
        public int compare(Map.Entry<Object, Object> mapEntry1, Map.Entry<Object, Object> mapEntry2) {
            if (mapEntry1 == null && mapEntry2 == null) {
                return 0;
            } else if (mapEntry1 == null && mapEntry2 != null) {
                return -1;
            } else if (mapEntry1 != null && mapEntry2 == null) {
                return 1;
            } else {
                Object key1 = mapEntry1.getKey();
                Object key2 = mapEntry2.getKey();
                if (key1 == null && key2 == null) {
                    return 0;
                } else if (key1 == null && key2 != null) {
                    return -1;
                } else if (key1 != null && key2 == null) {
                    return 1;
                } else {
                    if (key1.getClass() == key2.getClass() && key1 instanceof Comparable) {
                        Comparable key1Comparable = (Comparable) key1;
                        return key1Comparable.compareTo(key2);
                    } else {
                        return 0;
                    }
                }
            }
        }
    };

    /**
     * Map.Entry排序比较器 - 降序
     */
    private static Comparator<Map.Entry<Object, Object>> mapEntryComparatorDesc = new Comparator<Map.Entry<Object, Object>>() {
        public int compare(Map.Entry<Object, Object> mapEntry1, Map.Entry<Object, Object> mapEntry2) {
            return -mapEntryComparatorAsc.compare(mapEntry1, mapEntry2);
        }
    };

    /**
     * 重写Object的toString()方法 完全显示所有属性 使更容易查看多级属性类的详细属性
     *
     * @param o 需要得到属性的对象
     * @return 该对象中每一个属性详细内容的递归表示
     */
    @SuppressWarnings("unchecked")
    public static String toString(Object o) {
        StringBuilder stringBuilder = new StringBuilder();
        if (o == null) {
            return toStringFinal(null);
        } else if (Integer.class.isInstance(o) || int.class.isInstance(o)) {
            /** 基本类 下同 */
            return stringBuilder.append(String.valueOf(o)).toString();
        } else if (Long.class.isInstance(o) || long.class.isInstance(o)) {
            return stringBuilder.append(String.valueOf(o)).toString();
        } else if (Short.class.isInstance(o) || short.class.isInstance(o)) {
            return stringBuilder.append(String.valueOf(o)).toString();
        } else if (Byte.class.isInstance(o) || byte.class.isInstance(o)) {
            return stringBuilder.append(String.valueOf(o)).toString();
        } else if (Double.class.isInstance(o) || double.class.isInstance(o)) {
            return stringBuilder.append(String.valueOf(o)).toString();
        } else if (Float.class.isInstance(o) || float.class.isInstance(o)) {
            return stringBuilder.append(String.valueOf(o)).toString();
        } else if (Boolean.class.isInstance(o) || boolean.class.isInstance(o)) {
            return stringBuilder.append(String.valueOf(o)).toString();
        } else if (Character.class.isInstance(o) || char.class.isInstance(o)) {
            /** 实际上通过反射生成的类只可能是Character 所以不会需要去调用toStringFinal(char)方法 */
            return stringBuilder.append(toStringFinal(o)).toString();
        } else if (java.sql.Date.class.isInstance(o)) {
            /** SQL类 下同(因为java.sql.Date是java.util.Date的子类 所以只能把这个判断放在前面) */
            return stringBuilder.append(simpleSqlDateFormat.format(o)).toString();
        } else if (java.sql.Time.class.isInstance(o)) {
            return stringBuilder.append(simpleSqlTimeFormat.format(o)).toString();
        } else if (java.sql.Timestamp.class.isInstance(o)) {
            return stringBuilder.append(simpleSqlTimestampFormat.format(o)).toString();
        } else if (String.class.isInstance(o)) {
            /** 近似于基本的类 下同 */
            return stringBuilder.append(toStringFinal(o)).toString();
        } else if (java.util.Date.class.isInstance(o)) {
            return stringBuilder.append(simpleDateFormat.format(o)).toString();
        } else if (BigInteger.class.isInstance(o)) {
            /** 数学类 下同 */
            return stringBuilder.append(String.valueOf(o)).toString();
        } else if (BigDecimal.class.isInstance(o)) {
            return stringBuilder.append(String.valueOf(o)).toString();
        } else if (Class.class.isInstance(o)) {
            /** 反射类 下同 */
            return stringBuilder.append("<Class类>").append(((Class<? extends Object>) o).getName()).toString();
        } else if (Field.class.isInstance(o)) {
            return stringBuilder.append("<Field类>").append(((Field) o).getName()).toString();
        } else if (o.getClass().isArray()) {
            /** 数组类 */
            recursionLevel++;
            /** 如果是基础类型(8种之一)就直接每一个取值以后直接append(x) 而不再递归调用 */
            /** 同时也是为了避免(Object[]) somePrimitiveType[] 的ClassCastException */
            Class<?> componentClass = o.getClass().getComponentType();
            if (componentClass.isPrimitive()) {
                if (componentClass == int.class) {
                    int[] array = (int[]) o;
                    stringBuilder.append("【数组元素】");
                    if (array.length > 0) {
                        stringBuilder.append("(length=").append(array.length).append(")");
                    } else {
                        stringBuilder.append("(空)");
                    }
                    stringBuilder.append("\n");
                    for (int perElement : array) {
                        stringBuilder.append(getTab()).append(perElement).append("\n");
                    }
                } else if (componentClass == long.class) {
                    long[] array = (long[]) o;
                    stringBuilder.append("【数组元素】");
                    if (array.length > 0) {
                        stringBuilder.append("(length=").append(array.length).append(")");
                    } else {
                        stringBuilder.append("(空)");
                    }
                    stringBuilder.append("\n");
                    for (long perElement : array) {
                        stringBuilder.append(getTab()).append(perElement).append("\n");
                    }
                } else if (componentClass == short.class) {
                    short[] array = (short[]) o;
                    stringBuilder.append("【数组元素】");
                    if (array.length > 0) {
                        stringBuilder.append("(length=").append(array.length).append(")");
                    } else {
                        stringBuilder.append("(空)");
                    }
                    stringBuilder.append("\n");
                    for (short perElement : array) {
                        /** StringBuilder没有append(short)方法 所以仍然调用append(int) */
                        stringBuilder.append(getTab()).append((int) perElement).append("\n");
                    }
                } else if (componentClass == byte.class) {
                    byte[] array = (byte[]) o;
                    stringBuilder.append("【数组元素】");
                    if (array.length > 0) {
                        stringBuilder.append("(length=").append(array.length).append(")");
                    } else {
                        stringBuilder.append("(空)");
                    }
                    stringBuilder.append("\n");
                    for (byte perElement : array) {
                        /** StringBuilder没有append(byte)方法 所以仍然调用append(int) */
                        stringBuilder.append(getTab()).append((int) perElement).append("\n");
                    }
                } else if (componentClass == double.class) {
                    double[] array = (double[]) o;
                    stringBuilder.append("【数组元素】");
                    if (array.length > 0) {
                        stringBuilder.append("(length=").append(array.length).append(")");
                    } else {
                        stringBuilder.append("(空)");
                    }
                    stringBuilder.append("\n");
                    for (double perElement : array) {
                        stringBuilder.append(getTab()).append(perElement).append("\n");
                    }
                } else if (componentClass == float.class) {
                    float[] array = (float[]) o;
                    stringBuilder.append("【数组元素】");
                    if (array.length > 0) {
                        stringBuilder.append("(length=").append(array.length).append(")");
                    } else {
                        stringBuilder.append("(空)");
                    }
                    stringBuilder.append("\n");
                    for (float perElement : array) {
                        stringBuilder.append(getTab()).append(perElement).append("\n");
                    }
                } else if (componentClass == boolean.class) {
                    boolean[] array = (boolean[]) o;
                    stringBuilder.append("【数组元素】");
                    if (array.length > 0) {
                        stringBuilder.append("(length=").append(array.length).append(")");
                    } else {
                        stringBuilder.append("(空)");
                    }
                    stringBuilder.append("\n");
                    for (boolean perElement : array) {
                        stringBuilder.append(getTab()).append(perElement).append("\n");
                    }
                } else if (componentClass == char.class) {
                    char[] array = (char[]) o;
                    stringBuilder.append("【数组元素】");
                    if (array.length > 0) {
                        stringBuilder.append("(length=").append(array.length).append(")");
                    } else {
                        stringBuilder.append("(空)");
                    }
                    stringBuilder.append("\n");
                    for (char perElement : array) {
                        /** 这里调用toStringFinal(char)方法 来表示这是否一个空字符 */
                        stringBuilder.append(getTab()).append(toStringFinal(perElement)).append("\n");
                    }
                } else {
                    System.out.println("8种以外-无法识别的基本类型??");
                    stringBuilder.append(getTab()).append(o).append("\n");
                }
            } else {
                /** 不是基础类型(8种之一)则直接对里面的每个元素递归 */
                Object[] array = (Object[]) o;
                stringBuilder.append("【数组元素】");
                if (array.length > 0) {
                    stringBuilder.append("(length=").append(array.length).append(")");
                } else {
                    stringBuilder.append("(空)");
                }
                stringBuilder.append("\n");
                for (Object perObject : array) {
                    stringBuilder.append(getTab()).append(toString(perObject)).append("\n");
                }
            }
            cutLastIndexReturn(stringBuilder);
            recursionLevel--;
            return stringBuilder.toString();
        } else if (Collection.class.isInstance(o)) {
            /** 集合类 */
            recursionLevel++;
            Collection<Object> collection = (Collection<Object>) o;
            stringBuilder.append("◆列表元素◆");
            if (collection.size() > 0) {
                stringBuilder.append("(size=").append(collection.size()).append(")");
            } else {
                stringBuilder.append("(空)");
            }
            stringBuilder.append("\n");
            for (Object perObject : collection) {
                stringBuilder.append(getTab()).append(toString(perObject)).append("\n");
            }
            cutLastIndexReturn(stringBuilder);
            recursionLevel--;
            return stringBuilder.toString();
        } else if (Map.class.isInstance(o)) {
            /** 字典类 */
            recursionLevel++;
            Map<Object, Object> map = (Map<Object, Object>) o;
            stringBuilder.append("■字典元素■");
            if (map.size() > 0) {
                stringBuilder.append("(size=").append(map.size()).append(")");
            } else {
                stringBuilder.append("(空)");
            }
            stringBuilder.append("\n");
            List<Map.Entry<Object, Object>> mapEntryList = new ArrayList<Map.Entry<Object, Object>>(map.entrySet());
            if (sortRule != SORT_RULE.DEFAULT) {
                Collections.sort(mapEntryList, sortRule == SORT_RULE.ASC ? mapEntryComparatorAsc : mapEntryComparatorDesc);
            }
            for (Map.Entry<Object, Object> mapEntry : mapEntryList) {
                Object key = mapEntry.getKey();
                Object value = mapEntry.getValue();
                /** 如果是基本类型和近似于基本的类型 则直接调用toString()方法 否则只显示类名 */
                /** 以后再添加对项目中需要的其他特殊类的支持(如果该类的属性确实需要在Map的key中完全显示出来) */
                if (key == null) {
                    stringBuilder.append(toKeyToValue(toStringFinal(key), toString(value)));
                } else if (Integer.class.isInstance(key) || int.class.isInstance(key)) {
                    /** 基本类 下同 */
                    stringBuilder.append(toKeyToValue(String.valueOf(key), toString(value)));
                } else if (Long.class.isInstance(key) || long.class.isInstance(key)) {
                    stringBuilder.append(toKeyToValue(String.valueOf(key), toString(value)));
                } else if (Short.class.isInstance(key) || short.class.isInstance(key)) {
                    stringBuilder.append(toKeyToValue(String.valueOf(key), toString(value)));
                } else if (Byte.class.isInstance(key) || byte.class.isInstance(key)) {
                    stringBuilder.append(toKeyToValue(String.valueOf(key), toString(value)));
                } else if (Double.class.isInstance(key) || double.class.isInstance(key)) {
                    stringBuilder.append(toKeyToValue(String.valueOf(key), toString(value)));
                } else if (Float.class.isInstance(key) || float.class.isInstance(key)) {
                    stringBuilder.append(toKeyToValue(String.valueOf(key), toString(value)));
                } else if (Boolean.class.isInstance(key) || boolean.class.isInstance(key)) {
                    stringBuilder.append(toKeyToValue(String.valueOf(key), toString(value)));
                } else if (Character.class.isInstance(key) || char.class.isInstance(key)) {
                    /** 实际上Map的key只可能是Character 所以不会需要去调用toStringFinal(char)方法 */
                    stringBuilder.append(toKeyToValue(toStringFinal(key), toString(value)));
                } else if (String.class.isInstance(key)) {
                    /** 近似于基本的类 下同 */
                    stringBuilder.append(toKeyToValue(toStringFinal(key), toString(value)));
                } else if (java.util.Date.class.isInstance(key)) {
                    stringBuilder.append(toKeyToValue(simpleDateFormat.format(key), toString(value)));
                } else {
                    /** 其他类则直接显示类名 */
                    String className = new StringBuilder().append(!key.getClass().isMemberClass() ? "★类" : "★内部类").append(key.getClass().getSimpleName()).toString();
                    stringBuilder.append(toKeyToValue(className, toString(value)));
                }
            }
            cutLastIndexReturn(stringBuilder);
            recursionLevel--;
            return stringBuilder.toString();
        } else {
            /** 如果以上都不是 则对类(及其所有父类)中的所有成员变量递归调用此方法 */
            recursionLevel++;
            /** 类名 */
            Class<? extends Object> clazz = o.getClass();
            stringBuilder.append("★");
            if (recursionLevel > 1) {
                stringBuilder.append(recursionLevel - 1).append("级子类");
            } else {
                stringBuilder.append(!clazz.isMemberClass() ? "类" : "内部类");
            }
            stringBuilder.append(o.getClass().getSimpleName());
            stringBuilder.append("的");
            stringBuilder.append("属性");
            stringBuilder.append("★");
            stringBuilder.append("\n");
            /** 变量名 = 值 */
            Field[] fieldArray = getAllFields(clazz);
            if (sortRule != SORT_RULE.DEFAULT) {
                Arrays.sort(fieldArray, sortRule == SORT_RULE.ASC ? fieldComparatorAsc : fieldComparatorDesc);
            }
            for (Field field : fieldArray) {
                String fieldName = field.getName();
                String methodName = new StringBuilder().append("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
                try {
                    Method method;
                    try {
                        method = clazz.getMethod(methodName, new Class[0]);
                    } catch (NoSuchMethodException e) {
                        /** 添加对JavaBean规范的支持 同时兼容MyEclipse最新版本(8.5+)自动生成的方法 */
                        /** 即如果第2个字是大写字母 则第1个字母不会改变 如URL->getURL xMin->getxMin这样 */
                        /** 但是仍然兼容以前的getXMin方法(大概不会有两个变量故意这么命名来混淆吧..) */
                        if (fieldName.length() >= 2 && Character.isUpperCase(fieldName.charAt(1))) {
                            String fixedMethodName = new StringBuilder().append("get").append(fieldName).toString();
                            try {
                                method = clazz.getMethod(fixedMethodName, new Class[0]);
                            } catch (NoSuchMethodException fixedE) {
                                method = null;
                            }
                        } else {
                            method = null;
                        }
                    }
                    if (method != null) {
                        method.setAccessible(true);
                        Object result = method.invoke(o, new Object[0]);
                        /** 对成员变量进行递归调用 */
                        stringBuilder.append(toKeyEqualValue(fieldName, toString(result)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            cutLastIndexReturn(stringBuilder);
            recursionLevel--;
            return stringBuilder.toString();
        }
    }

    /**
     * 在文本前添加\t 为了显示值时突出层次感
     *
     * @return 在前面添加了\t的文本
     */
    private static StringBuilder getTab() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < recursionLevel; i++) {
            stringBuilder.append("\t");
        }
        return stringBuilder;
    }

    /**
     * 得到每一条基本记录数据(递归栈底元素)的表示形式(如☆x=1)
     *
     * @param key   键
     * @param value 值
     * @return 每一条基本记录数据(递归栈底元素)的表示形式(如☆x=1)
     */
    private static StringBuilder toKeyEqualValue(String key, Object value) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTab());
        stringBuilder.append("☆");
        stringBuilder.append(key);
        stringBuilder.append(" = ");
        stringBuilder.append(toStringFinal(value));
        stringBuilder.append("\n");
        return stringBuilder;
    }

    /**
     * 得到每一条基本记录数据(递归栈底元素)的表示形式(如m=>3)
     *
     * @param key   键
     * @param value 值
     * @return 每一条基本记录数据(递归栈底元素)的表示形式(如m=>3)
     */
    private static StringBuilder toKeyToValue(String key, Object value) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTab());
        stringBuilder.append(key);
        stringBuilder.append(" => ");
        stringBuilder.append(toStringFinal(value));
        stringBuilder.append("\n");
        return stringBuilder;
    }

    /**
     * 得到属性值最终展示的字符串 为null则显示NULL值 否则调用Object.toString()方法
     *
     * @param value 属性值
     * @return 属性值最终展示的字符串 为null则显示NULL值 否则调用Object.toString()方法
     */
    private static String toStringFinal(Object value) {
        if (value == null) {
            /** null */
            return NULL_VALUE;
        }
        if (Character.class.isInstance(value)) {
            /** 对字符调用toStringFinal(char)方法进行处理 */
            return toStringFinal(((Character) value).charValue());
        }
        if (String.class.isInstance(value)) {
            if (value.equals("")) {
                /** 空字符串 */
                return EMPTY_STRING;
            } else if (value.equals(" ")) {
                /** 空格 */
                return SPACE;
            }
        }
        /** 如果都不是 则返回最终的Object.toString()方法 */
        return value.toString();
    }

    /**
     * 得到字符值最终展示的字符串 为null则显示空值 否则将之本身转化为String方法
     *
     * @param c 字符值
     * @return 字符值最终展示的字符串 为null则显示空值 否则将之本身转化为String方法
     */
    private static String toStringFinal(char c) {
        if (c == '\0') {
            /** 空字符 */
            return EMPTY_CHAR;
        }
        if (c == ' ') {
            /** 空格 */
            return SPACE;
        }
        if (c == '\r') {
            /** 回车符 */
            return RETURN;
        }
        if (c == '\n') {
            /** 换行符 */
            return NEW_LINE;
        }
        /** 如果都不是 则返回一个包括且仅包括该字符的字符串 */
        return Character.valueOf(c).toString();
    }

    /**
     * （因为toKeyValue中已经有了一个\n） 删除末尾的\n
     *
     * @param stringBuilder 可变字符串
     */
    private static void cutLastIndexReturn(StringBuilder stringBuilder) {
        if (stringBuilder == null) {
            return;
        }
        int lastIndexOf = stringBuilder.lastIndexOf("\n");
        if (lastIndexOf != -1) {
            stringBuilder.delete(lastIndexOf, stringBuilder.length());
        }
    }

    /**
     * 得到所有成员变量(包括自己及所有父类的)
     *
     * @param clazz 类
     * @return 所有成员变量(包括自己及所有父类的)
     */
    private static Field[] getAllFields(Class<? extends Object> clazz) {
        ArrayList<Field> fieldList = new ArrayList<Field>();
        Class<? extends Object> initClass = clazz;
        Field[] perFieldArray = null;
        while (initClass != null) {
            perFieldArray = initClass.getDeclaredFields();
            if (perFieldArray.length > 0) {
                for (Field field : perFieldArray) {
                    fieldList.add(field);
                }
            }
            initClass = initClass.getSuperclass();
        }
        Field[] fieldArray = new Field[fieldList.size()];
        for (int i = 0; i < fieldList.size(); i++) {
            fieldArray[i] = fieldList.get(i);
        }
        return fieldArray;
    }

    /**
     * 将排序规则重设为默认枝
     */
    public static void resetSortRule() {
        ToStringUtil.sortRule = SORT_RULE.DEFAULT;
    }

    public static SORT_RULE getSortRule() {
        return sortRule;
    }

    public static void setSortRule(SORT_RULE sortRule) {
        ToStringUtil.sortRule = sortRule;
    }
}