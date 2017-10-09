package com.github.dawidstankiewicz.forum.service;

import com.github.dawidstankiewicz.forum.entity.ActivationCode;

/**
 * Created by Dawid Stankiewicz on 10.08.2017.
 */
public interface ActivationCodeService {

    ActivationCode findActivationCode(String id);

    ActivationCode findActivationCodeByUsername(String username);

    void deleteActivationCode(String username);

    ActivationCode save(ActivationCode activationCode);
}
