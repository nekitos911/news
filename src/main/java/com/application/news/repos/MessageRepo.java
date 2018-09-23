package com.application.news.repos;

import com.application.news.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Репо для поиска сообщений в БД
 */
public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);
}
