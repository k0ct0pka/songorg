package factory;

import java.util.Map;

public interface BaseFactory<T> {
    T create(Map<String, Object> params);
}
