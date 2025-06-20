package com.example.telegramdipbot.bot.handler.MessageHandler;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

public interface MessageHandler {
    void handleMessage(Update update, Long chatId, String messageText, AbsSender bot);
}