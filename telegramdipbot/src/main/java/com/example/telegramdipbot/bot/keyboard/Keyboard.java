package com.example.telegramdipbot.bot.keyboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Component
public class Keyboard implements KeyboardInterface {

    @Override
    public InlineKeyboardMarkup createKeyboard(KeyboardType type) {
        switch (type) {
            case MAIN_MENU:
                return createMainMenuKeyboard();
            case NECESSARY_STEPS:
                return createNecessaryStepsKeyboard();
            case REC_STEPS:
                return createRecStepsKeyboard();
            case USEFUL_INFO:
                return createUsefulInfoKeyboard();
            case CONTACTS:
                return createContactsKeyboard();        
             default:
                throw new IllegalArgumentException("❌ Unknown keyboard type: " + type);
        }
    }

    @Override
    public SendMessage createKeyboardMessage(Long chatId, String text, KeyboardType keyboardType) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        message.setReplyMarkup(createKeyboard(keyboardType));
        return message;
    }

    // 1. Главное меню
    private InlineKeyboardMarkup createMainMenuKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder().text("🔹 Обязательные шаги").callbackData("unnecessaryStepsButton").build());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text("🔸 Рекомендуемые шаги").callbackData("recommendedStepsButton").build());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(InlineKeyboardButton.builder().text("ℹ️ Полезная информация").callbackData("usefulInformationButton").build());

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(InlineKeyboardButton.builder().text("📞 Контакты").callbackData("contactsButton").build());

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);

        markup.setKeyboard(keyboard);
        return markup;
    }

    // 2. Обязательные шаги
    private InlineKeyboardMarkup createNecessaryStepsKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder().text("📁 Документы для оформления").callbackData("button11").build());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text("📌 Информационная безопасность").callbackData("button12").build());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(InlineKeyboardButton.builder().text("🎓 ПБОТОС (охрана труда, экология)").callbackData("button13").build());

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(InlineKeyboardButton.builder().text("🔑 Учётные записи и доступы").callbackData("button14").build());

        List<InlineKeyboardButton> row5 = new ArrayList<>();
        row5.add(InlineKeyboardButton.builder().text("🧾 Оформление в 1С и СЭД").callbackData("button15").build());

        List<InlineKeyboardButton> row6 = new ArrayList<>();
        row6.add(InlineKeyboardButton.builder().text("🧑‍💻 Дистанционная работа").callbackData("button16").build());

        List<InlineKeyboardButton> row7 = new ArrayList<>();
        row7.add(InlineKeyboardButton.builder().text("⬅️ Назад в меню").callbackData("buttonMainMenu").build());

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        keyboard.add(row5);
        keyboard.add(row6);
        keyboard.add(row7);

        markup.setKeyboard(keyboard);
        return markup;
    }

    // 3. Рекомендуемые шаги
    private InlineKeyboardMarkup createRecStepsKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder().text("📬 Корпоративная почта").callbackData("button21").build());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text("👥 Вступление в профсоюз").callbackData("button22").build());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(InlineKeyboardButton.builder().text("📚 Обучение и адаптация").callbackData("button23").build());

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(InlineKeyboardButton.builder().text("🎁 Соц-поддержка и бонусы").callbackData("button24").build());

        List<InlineKeyboardButton> row5 = new ArrayList<>();
        row5.add(InlineKeyboardButton.builder().text("⬅️ Назад в меню").callbackData("buttonMainMenu").build());

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        keyboard.add(row5);

        markup.setKeyboard(keyboard);
        return markup;
    }
    
    // 4. Полезная информация
    private InlineKeyboardMarkup createUsefulInfoKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder().text("📑 Часто задаваемые вопросы").callbackData("button21").build());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text("🏢 Кадровые администраторы").callbackData("button22").build());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(InlineKeyboardButton.builder().text("📃 Шаблоны заявлений").callbackData("button22").build());

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(InlineKeyboardButton.builder().text("📎 Инструкции и методички").callbackData("button22").build());

        List<InlineKeyboardButton> row5 = new ArrayList<>();
        row5.add(InlineKeyboardButton.builder().text("⬅️ Назад в меню").callbackData("buttonMainMenu").build());

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        keyboard.add(row5);

        markup.setKeyboard(keyboard);
        return markup;
    }

    // 5. Контакты
    private InlineKeyboardMarkup createContactsKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder().text("📍 Офисы и адреса").callbackData("button41").build());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text("📞 Телефоны отделов").callbackData("button42").build());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(InlineKeyboardButton.builder().text("📧 Email-адреса").callbackData("button43").build());

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(InlineKeyboardButton.builder().text("👤 Руководители подразделений").callbackData("button45").build());

        List<InlineKeyboardButton> row5 = new ArrayList<>();
        row5.add(InlineKeyboardButton.builder().text("⬅️ Назад в меню").callbackData("buttonMainMenu").build());

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        keyboard.add(row5);

        markup.setKeyboard(keyboard);
        return markup;
    }
}