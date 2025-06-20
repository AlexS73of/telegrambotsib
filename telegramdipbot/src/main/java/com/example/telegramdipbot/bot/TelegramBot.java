package com.example.telegramdipbot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.example.telegramdipbot.bot.handler.CallbackHandler.CallbackHandler;
import com.example.telegramdipbot.bot.handler.MessageHandler.MessageHandler;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final String botName;
    private final MessageHandler messageHandler;
    private final CallbackHandler callbackHandler;

    public TelegramBot(@Value("${bot.token}") String botToken,
                     @Value("${bot.username}") String botName,
                     MessageHandler messageHandler,
                     CallbackHandler callbackHandler) {
        super(botToken);
        this.botName = botName;
        this.messageHandler = messageHandler;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userMessage = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            messageHandler.handleMessage(update, chatId, userMessage, this);
        }
        else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            callbackHandler.handleCallback(update, chatId, callbackData, this);
        }    
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}