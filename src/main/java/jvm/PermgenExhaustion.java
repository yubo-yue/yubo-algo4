package jvm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yubo on 12/8/15.
 * <p>
 * This example show what happens when you load the same class with different class loader,
 * and keep doing so.
 */
public class PermgenExhaustion {

    public static class MyClass {
        //this class will be load many times.
    }

    private static class MyClassLoader extends URLClassLoader {
        String className;
        byte[] classData;

        public MyClassLoader(Class<?> clazz, byte[] data) {
            super(new URL[0]);
            this.className = clazz.getName();
            this.classData = data;
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            if (!name.equals(this.className))
                return super.loadClass(name);
            return defineClass(null, classData, 0, classData.length);
        }
    }

    private static byte[] loadClassFile(Class<?> clazz) throws IOException {
        String className = clazz.getName();
        if (className.indexOf('.') > 0) {
            className = className.substring(className.lastIndexOf('.') + 1);
        }

        String fileName = className + ".class";
        try (InputStream in = MyClass.class.getResourceAsStream(fileName)) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int b;
            while ((b = in.read()) >= 0) {
                out.write(b);
            }

            return out.toByteArray();
        }

    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        byte[] classData = loadClassFile(MyClass.class);
        List<ClassLoader> loaders = new ArrayList<ClassLoader>();

        for (int ii = 0 ; ii < 1000000 ; ii++)
        {
            MyClassLoader loader = new MyClassLoader(MyClass.class, classData);
            loaders.add(loader);
            loader.loadClass(MyClass.class.getName());
        }
    }
}
