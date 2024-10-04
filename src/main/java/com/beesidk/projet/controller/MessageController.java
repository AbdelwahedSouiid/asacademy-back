package com.beesidk.projet.controller;


import com.beesidk.projet.entity.Inscription;
import com.beesidk.projet.entity.Message;
import com.beesidk.projet.service.MessageService;
import com.beesidk.projet.service.OllamaService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private OllamaService ollamaService;
    private MessageService messageService;

    @PostMapping("/add-message")
    public Message ask(@RequestBody Message message) {
        // Générer la réponse du bot en fonction de la question de l'utilisateur
        message.setBotResponse(ollamaService.ragChat(message.getUserQuestion()));
        // Sauvegarder le message avec la réponse générée
        return messageService.add(message);
    }


    @GetMapping(value = "/retrieve-all-messages")
    public List<Message> getMessages() {
        List<Message> listMessgaes = messageService.retrieveAll();
        return listMessgaes;
    }

}
