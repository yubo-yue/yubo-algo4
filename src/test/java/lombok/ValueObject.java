package lombok;

@Value
public class ValueObject {
    private final int a;
    @NonNull private final String key;
    private final String s;

    public static void main(String[] args) {
        ValueObject vo = new ValueObject(1, null, "no");
        System.out.println(vo);
    }
}
