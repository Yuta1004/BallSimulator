package util;

public class Parse {

    /**
     * String 2 Double
     * ただし、パースに失敗した場合は指定された値を返す
     *
     * @param strVal パースする文字列
     * @param faultVal 失敗時に返す値
     * @return Double
     */
    public static double toDouble(String strVal, double faultVal) {
        try {
            return Double.parseDouble(strVal);
        } catch (Exception e) {
            return faultVal;
        }
    }

}