package com.benq.demofashion.Utils;

import java.lang.reflect.Field;

public class Utils {
    public static String logObject(final Object obj) {
        final StringBuffer sb = new StringBuffer();
        final Field[] fields = obj.getClass().getFields();
        try {
            for (final Field field : fields) {
                sb.append(field.getName() + ": " + field.get(obj) + "\n");
            }
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
