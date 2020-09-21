package util;

import java.lang.ClassNotFoundException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

public class MyClassLoader {

    public static ArrayList<Class<?>> getClasses(String targetPackage) {
        /**
         * 指定パッケージ以下のクラスをすべて返す
         * @param targetPackage パッケージ指定
         * @return クラスを詰めたArrayList
         */
        targetPackage = targetPackage.replace("/", ".");

        // リソース全取得
        Enumeration<URL> enu = getAllResources(targetPackage);
        if(enu == null) {
            return new ArrayList<Class<?>>();
        }

        // リスト準備
        ArrayList<Class<?>> classList = new ArrayList<Class<?>>();

        // すべてのリソースについて調べる
        final String targetPackagef = targetPackage;
        enu.asIterator().forEachRemaining(url -> {
            // 走査準備
            File dir = new File(url.getPath());

            // ディレクトリ内の要素について調べる
            for(String path : dir.list()) {
                if(path.endsWith(".class")) {   // .classファイル発見
                    try {
                        classList.add(Class.forName(targetPackagef+"."+path.substring(0, path.length()-6)));
                    } catch (ClassNotFoundException e){}
                }
            }
        });
        return classList;

    }

    private static Enumeration<URL> getAllResources(String targetPackage) {
        /**
         * 指定パッケージに存在する全てのリソースを返す
         * @param targetPackage パッケージ指定
         * @return 見つかったURLのリスト
         */

        // クラスローダ準備 -> リソース取得
        ClassLoader cloader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> enu;
        try {
            enu = cloader.getResources(targetPackage);
        } catch(IOException e) {
            System.err.println(e);
            return null;
        }
        return enu;
    }

}
