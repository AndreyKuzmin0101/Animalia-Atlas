package ru.kpfu.itis.kuzmin.animalswebapp.repository;


public interface CrudRepository<T>{
    void save(T model);
}
