package com.example.telegramdipbot.bot.keyboard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface KeyboardInterface {
    InlineKeyboardMarkup createKeyboard(KeyboardType type);
    SendMessage createKeyboardMessage(Long chatId, String text, KeyboardType keyboardType);
}