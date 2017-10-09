package com.github.dawidstankiewicz.forum.service.impl;

import com.github.dawidstankiewicz.forum.entity.ActivationCode;
import com.github.dawidstankiewicz.forum.entity.repository.ActivationCodeRepository;
import com.github.dawidstankiewicz.forum.service.ActivationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dawid Stankiewicz on 10.08.2017.
 */

@Service
public class ActivationCodeServiceImpl implements ActivationCodeService {

    @Autowired
    private ActivationCodeRepository activationCodeRepository;

    @Override
    public ActivationCode findActivationCode(String id) {
        return activationCodeRepository.findOne(id);
    }

    @Override
    public ActivationCode findActivationCodeByUsername(String username) {
        return activationCodeRepository.findByUsername(username);
    }

    @Override
    public void deleteActivationCode(String id) {
        activationCodeRepository.delete(id);
    }

    @Override
    public ActivationCode save(ActivationCode activationCode) {
        return activationCodeRepository.save(activationCode);
    }
}
