package services;

import java.util.List;

public interface Service<T> {
    void add(T t);
    List<T> listAll();
}