package json;

import java.lang.reflect.Field;

interface JSONSerialiable {

    /**
     * Serializes an object to JSON
     *
     * @return String representation as JSON
     * @throws IllegalAccessException
     */
    default String toJson() throws IllegalAccessException {
        StringBuffer sb = new StringBuffer("{");

        Class cl = this.getClass();
        for (Field f : cl.getDeclaredFields()) {
            f.setAccessible(true);
            sb.append("\"" + f.getName() + "\" : ");
            if (f.getType().equals(int.class)) sb.append(f.get(this));
            else sb.append("\"" + f.get(this) + "\",");
        }

        if (sb.charAt(sb.length()-1) == ',')
            sb.deleteCharAt(sb.length() - 1);
        sb.append("}");

        return sb.toString();
    }

}
