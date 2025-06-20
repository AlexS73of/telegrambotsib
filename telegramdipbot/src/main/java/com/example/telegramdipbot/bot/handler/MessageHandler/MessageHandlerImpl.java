package com.example.telegramdipbot.bot.handler.MessageHandler;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.example.telegramdipbot.bot.keyboard.KeyboardInterface;
import com.example.telegramdipbot.bot.keyboard.KeyboardType;
import com.example.telegramdipbot.model.BotUser;
import com.example.telegramdipbot.repository.BotUserRepository;

@Component
public class MessageHandlerImpl implements MessageHandler {

    private final KeyboardInterface keyboard;
    private final BotUserRepository userRepository;

    private String gifResourcePath = "C:\\Something\\projects\\telegramdipbot2_single_handler_final\\telegramdipbot\\src\\main\\resources\\gifwelcome.gif"; 

    public MessageHandlerImpl(KeyboardInterface keyboard, BotUserRepository userRepository) {
        this.keyboard = keyboard;
        this.userRepository = userRepository;
    }

    @Override
    public void handleMessage(Update update, Long chatId, String messageText, AbsSender bot) {
        if ("/start".equals(messageText)) {

            // Отправка приветственной GIF-анимации
            try {
                InputFile gifFile = new InputFile(new java.io.File(gifResourcePath));
            
                SendAnimation gif = new SendAnimation();
                gif.setChatId(chatId.toString());
                gif.setAnimation(gifFile);
                gif.setCaption("Привет! 👋 Я бот СИБИНТЕК-СОФТ. Добро пожаловать в команду! 🚀");
            
                bot.execute(gif);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            Optional<BotUser> existingUser = userRepository.findByChatId(chatId);

            if (existingUser.isEmpty()) {
                var user = update.getMessage().getFrom();

                BotUser newUser = BotUser.builder()
                        .chatId(chatId)
                        .username(user.getUserName())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .registrationDate(LocalDateTime.now())
                        .build();

                userRepository.save(newUser);
            }

            SendMessage message = keyboard.createKeyboardMessage(chatId, "Главное меню", KeyboardType.MAIN_MENU);
            try {
                bot.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace(); 
            }
        }
        
        else if ("/menu".equals(messageText)) {
            SendMessage message = keyboard.createKeyboardMessage(chatId, "Главное меню:", KeyboardType.MAIN_MENU);
            try {
                bot.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;
        }
        else if ("/help".equals(messageText)) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId.toString());
            message.setText("""
            📖 Добро пожаловать в Telegram-бот компании СИБИНТЕК-СОФТ!
            
            🔹 С помощью главного меню вы можете:
            — 📄 Получить документы
            — 🛡 Пройти инструктаж по ИБ и ПБТОC
            — 💼 Узнать о дистанционной работе
            — 📞 Получить контакты сотрудников
            
            Если возникли вопросы по работе бота — напишите напрямую разработчику:
            👉 https://t.me/wefgsf
            
            🎯 Используйте кнопки внизу экрана для быстрого доступа.
            """);
                    try {
                        bot.execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    return;
                }
    }
}
