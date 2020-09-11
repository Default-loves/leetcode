package com.junyi.access.mysql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @time: 2020/8/13 9:00
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
}
