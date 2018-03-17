package generic;

public class WildcardParameterizedType {

    private static class Box<T> {
        private T t;

        public Box(T t) {
            this.t = t;
        }

        public Box(Box<? extends T> box) {
            this.t = box.t;
        }

        public void put(T t) {
            this.t = t;
        }

        public T take() {
            return this.t;
        }

        public boolean equalTo(Box<T> other) {
            return this.t.equals(other.t);
        }

        public Box<T> copy() {
            return new Box<>(t);
        }

        public int compareTo(Comparable<? super T> other) {
            return other.compareTo(t);
        }

    }

    public static void main(String[] args) {
        Box<?> box = new Box<>("abc");

        box.put(null);

        Object o = box.take();
    }
}
