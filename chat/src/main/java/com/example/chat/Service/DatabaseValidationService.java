package com.example.chat.Service;

import com.example.chat.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class DatabaseValidationService {

    @Autowired
    private MessageRepository messageRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void validateDatabaseConnection() {
        // 调试输出，检查依赖项是否正确注入
        if (messageRepository == null) {
            throw new IllegalStateException("MessageRepository not injected");
        }

        if (entityManager == null) {
            throw new IllegalStateException("EntityManager not injected");
        }

        // 尝试执行一个简单的查询来验证数据库连接
        long messageCount = messageRepository.count();
        System.out.println("Total messages in database: " + messageCount);
    }
}
