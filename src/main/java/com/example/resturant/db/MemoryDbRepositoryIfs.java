package com.example.resturant.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> {

    //index를 받아 타입에 대한 entity를 찾아 리턴해주는
    Optional<T> findById(int index);


    //저장해주는 메소드
    T save(T entity);

    //삭제 메소드
    void deleteById(int index);


    //전체를 리턴시키는
    List<T> listAll();

}
