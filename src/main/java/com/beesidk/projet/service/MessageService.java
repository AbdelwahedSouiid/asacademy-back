package com.beesidk.projet.service;


import com.beesidk.projet.entity.Message;
import com.beesidk.projet.interfaces.IService;
import com.beesidk.projet.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class MessageService implements IService<Message> {

    MessageRepository repository;

    @Override
    public List<Message> retrieveAll() {
        return repository.findAll();
    }

    @Override
    public Message retrieve(String id) {
        return null;
    }

    @Override
    public Message add(Message message) {
        message.setMessageDate(LocalDateTime.now());
        return repository.save(message);
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public Message modify(Message message) {
        return null;
    }
}
