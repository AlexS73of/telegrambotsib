package com.example.telegramdipbot.bot.handler.CallbackHandler;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CallbackHandler {
    void handleCallback(Update update, Long chatId, String callbackData, TelegramLongPollingBot bot);
}