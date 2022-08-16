package com.example.resturant.db;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T>{

    private final List<T> db = new ArrayList<>();
    private int index= 0;


    @Override
    public Optional<T> findById(int index) {
        return db.stream().filter(it -> it.getIndex() == index).findFirst();

    }

    @Override
    public T save(T entity) {
        Optional<T> optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();
        System.out.print("optionalEntity : "+optionalEntity);
        //db에 이미 데이터가 없는 경우
        if(optionalEntity.isPresent()== false){
            index++;
            entity.setIndex(index);
            db.add(entity);
            return entity;
        }else{
            //db에 데이터가 있는 경우
            int preIndex = optionalEntity.get().getIndex();
            entity.setIndex(preIndex);

            deleteById(preIndex);
            db.add(entity);
            return entity;
        }


        //db에 데이터가 있는 경우

    }

    @Override
    public void deleteById(int index) {
        Optional<T> optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst();
        if(optionalEntity.isPresent()) {
            db.remove(optionalEntity.get());
        }

    }

    @Override
    public List<T> listAll() {
        return db;
    }
}
