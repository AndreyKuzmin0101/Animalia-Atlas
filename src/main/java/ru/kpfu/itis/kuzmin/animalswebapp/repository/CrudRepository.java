package ru.kpfu.itis.kuzmin.animalswebapp.repository;

//TODO: переделать логику, DRY
public interface CrudRepository<T>{
    void save(T model);
    void update(T model);
}
