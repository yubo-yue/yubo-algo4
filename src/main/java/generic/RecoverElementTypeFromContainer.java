package generic;

import java.util.ArrayList;
import java.util.List;

public class RecoverElementTypeFromContainer {

    private interface Contained {
    }

    private interface Container<T extends Contained> {
        void add(T element);

        List<T> elements();

        Class<T> getElementType();
    }

    private static class MyContained implements Contained {
        private final String name;

        public MyContained(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static class MyContainer implements Container<MyContained> {
        private final List<MyContained> elements = new ArrayList<>();

        @Override
        public void add(MyContained element) {
            elements.add(element);
        }

        @Override
        public List<MyContained> elements() {
            return elements;
        }

        @Override
        public Class<MyContained> getElementType() {
            return MyContained.class;
        }
    }

    private static class MetaContainer {
        private Container<? extends Contained> container;

        public void setContainer(Container<? extends Contained> container) {
            this.container = container;
        }

        public void add(Contained element) {
            add(element, container);
        }

        private <T extends Contained> void add(Contained element, Container<T> container) {
            container.add(container.getElementType().cast(element));
        }

        public List<? extends Contained> elements() {
            return container.elements();
        }
    }

}
