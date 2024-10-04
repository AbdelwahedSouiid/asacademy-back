package com.beesidk.projet.service;

import com.beesidk.projet.entity.Chat;
import com.beesidk.projet.interfaces.IService;
import com.beesidk.projet.repository.ChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatService implements IService<Chat> {

    ChatRepository chatRepository;

    @Override
    public List<Chat> retrieveAll() {
        return chatRepository.findAll();
    }

    @Override
    public Chat retrieve(String id) {
        return chatRepository.findById(id).orElse(null);
    }

    @Override
    public Chat add(Chat chat) {
        chat.setChatDate(LocalDateTime.now());
        return chatRepository.save(chat);
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public Chat modify(Chat chat) {
        return null;
    }
}
