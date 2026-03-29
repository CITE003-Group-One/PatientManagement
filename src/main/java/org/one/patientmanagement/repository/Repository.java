package org.one.patientmanagement.repository;

public interface Repository<T> {

    T save(T model);

    default void update(T model) { }

    void delete(long id);
}
