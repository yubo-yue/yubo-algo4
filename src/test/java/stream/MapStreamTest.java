package stream;


import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapStreamTest {


    private static final Map<String, Map<String, List<String>>> MAPS = Maps.newHashMap();

    public void setUp() {
        Map<String, List<String>> map1 = new HashMap<String, List<String>>() {{
            put("SORTABLE", Arrays.asList("Sortable_Only", "IG1", "IG2"));
        }};
        Map<String, List<String>> map2 = new HashMap<String, List<String>>() {{
            put("SORTABLE", Arrays.asList("Sortable_Only", "IG3", "IG4", "IG5"));
        }};

        MAPS.put("US", map1);
        MAPS.put("CA", map2);
    }


    public void test() {

        MAPS.entrySet().stream().collect(Collectors.mapping(entry -> Maps.ent));

    }


    @Data
    @AllArgsConstructor
    public static class CountryCode {
        private String ccStr;
    }

    @Data
    @AllArgsConstructor
    public static class StorageType {
        private String stStr;
    }

    @Data
    @AllArgsConstructor
    public static class InventoryGroup {
        private String igStr;
    }
}
