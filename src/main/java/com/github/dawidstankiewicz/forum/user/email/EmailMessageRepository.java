package com.github.dawidstankiewicz.forum.user.email;

import com.github.dawidstankiewicz.forum.model.entity.EmailMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailMessageRepository extends JpaRepository<EmailMessage, Long> {
}
