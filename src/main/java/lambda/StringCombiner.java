package lambda;

/**
 * Created by yubo on 12/22/15.
 */
public class StringCombiner {
    private String delim;
    private String prefix;
    private String suffix;
    private final StringBuilder builder;

    public StringCombiner(String delim, String prefix, String suffix) {
        this.delim = delim;
        this.suffix = suffix;
        this.prefix = prefix;
        this.builder = new StringBuilder();
    }

    public StringCombiner add(String element) {
        if (areAtStart()) {
            builder.append(prefix);
        } else {
            builder.append(delim);
        }
        builder.append(element);
        return this;
    }

    private boolean areAtStart() {
        return builder.length() == 0;
    }

    public StringCombiner merge(StringCombiner other) {
        if (other.builder.length() > 0) {
            if (areAtStart()) {
                builder.append(prefix);
            } else {
                builder.append(delim);
            }
            builder.append(other.builder, prefix.length(), other.builder.length());
        }
        return this;
    }

    @Override
    public String toString() {
        if (areAtStart()) {
            builder.append(prefix);
        }
        builder.append(suffix);
        return builder.toString();
    }
}
