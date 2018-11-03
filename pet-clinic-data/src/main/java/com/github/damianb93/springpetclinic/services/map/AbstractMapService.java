package com.github.damianb93.springpetclinic.services.map;

import com.github.damianb93.springpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity> {

    Map<Long, T> map = new HashMap<>();

    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    public T findById(Long id) {
        return map.get(id);
    }

    public T save(T object) {
        if (object == null) throw new RuntimeException("Object cannot be null");
        if (object.getId() == null) object.setId(getNextId());

        map.put(object.getId(), object);

        return object;
    }

    public void delete(T object) {
        map.values().removeIf(value -> value.equals(object));
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    public void deleteById(Long id) {
        map.remove(id);
    }

    private Long getNextId() {
        if (map.keySet().isEmpty()) return 1L;

        return  Collections.max(map.keySet()) + 1;
    }
}
