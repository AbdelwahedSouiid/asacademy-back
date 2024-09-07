package com.beesidk.projet.interfaces;

import java.util.List;

public interface IService<T> {


    public List<T> retrieveAll();

    public T retrieve(String id);

    public T add(T t);

    public void remove(String id);

    public T modify(T t);

}

