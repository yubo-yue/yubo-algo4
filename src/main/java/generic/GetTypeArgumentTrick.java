package generic;

import sun.reflect.generics.tree.TypeArgument;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GetTypeArgumentTrick {
    interface GenericType<T> {
        void method(T t);

        Class<T> getTypeArgument();

    }

    class ConcreteType implements GenericType<TypeArgument> {

        @Override
        public void method(TypeArgument typeArgument) {

        }

        @Override
        public Class<TypeArgument> getTypeArgument() {
            return TypeArgument.class;
        }
    }

    class GenericUsage {
        private GenericType<?> reference;

        public void method(Object arg) {
            helper(reference, arg);
        }

        private <T> void helper(GenericType<T> reference, Object arg) {
            reference.method(reference.getTypeArgument().cast(arg));
        }
    }

    public static <E> void addAll(List<E> list, E... array) {
        for (E e : array) {
            list.add(e);
        }
    }


    interface Acceptor<V> {
        void accept(Task<? extends V> task, V v);
    }

    interface Task<U> {
        void go(Acceptor<? super U> acceptor);
    }

    class AcceptTask<U> implements Task<U> {

        @Override
        public void go(Acceptor<? super U> acceptor) {
            U result = null;
            acceptor.accept(this, result);
        }
    }

    private static final int SIZE = 10;

    public static <T> T[] createBufer(Class<T> clazz) {
        return (T[]) Array.newInstance(clazz, SIZE);
    }

    public static void main(String[] args) {
        addAll(new ArrayList<String>(), "a", "b");
        addAll(new ArrayList<LinkedList<Integer>>(), new LinkedList<Integer>(), new LinkedList<Integer>());
    }

}
