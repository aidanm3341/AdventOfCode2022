package org.aidan;

import java.util.List;

public interface Output<T> {
    T execute(List<String> input);
}
