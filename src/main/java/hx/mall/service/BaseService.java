package hx.mall.service;

import java.util.List;

public interface BaseService<T> {

    int insert(T obj);

    int insert(List<T> objs);

    int delete(Long id);

    int delete(List<Long> ids);

    int update(Long id, T obj);

    int update(List<Long> ids, T obj);

    List<T> select();

    T select(Long id);

}
