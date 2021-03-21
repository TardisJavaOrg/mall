package hx.mall.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface BaseController<T> {

    T select(Long id);
    List<T> select(Long[] ids);

    int insert(T obj);
    int insert(List<T> obj);

    T update(Long id, T obj);

    T delete(Long id);
}
