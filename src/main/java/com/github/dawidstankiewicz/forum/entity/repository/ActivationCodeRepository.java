package com.github.dawidstankiewicz.forum.entity.repository;

import com.github.dawidstankiewicz.forum.entity.ActivationCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dawid Stankiewicz on 10.08.2017.
 */
public interface ActivationCodeRepository extends JpaRepository<ActivationCode, String> {

    ActivationCode findByUsername(String username);

}
