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
            case INSTRUCTIONS_ABOUT_REGISTRATION:
                return createInstructionsAboutRegistrationKeyboard();
            case PKZI_KEY:
                return createPKZIKeyKeyboard();
            case PBOTOS_EDUCATION:
                return createPBOTOSEducationKeyboard();
            case INFORMATION_SECURITY:
                return createInformationSecurityKeyboard();
            case CONTACTS_CDS:
                return createContactsCDSKeyboard();
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

    private InlineKeyboardMarkup createMainMenuKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder().text("🔹 Обязательные шаги").callbackData("unnecessaryStepsButton").build());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text("🔸 Рекомендуемые шаги").callbackData("recommendedStepsButton").build());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(InlineKeyboardButton.builder().text("📄 Инструкции по оформлению заявок").callbackData("instructionRegistrationButton").build());

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(InlineKeyboardButton.builder().text("🔑 ПКЗИ (ключ)").callbackData("pkziKeyButton").build());

        List<InlineKeyboardButton> row5 = new ArrayList<>();
        row5.add(InlineKeyboardButton.builder().text("🎓 ПБОТОС (обучение)").callbackData("pbtosEducationButton").build());

        List<InlineKeyboardButton> row6 = new ArrayList<>();
        row6.add(InlineKeyboardButton.builder().text("🛡️ Информационная безопасность").callbackData("informationSecurityButton").build());

        List<InlineKeyboardButton> row7 = new ArrayList<>();
        row7.add(InlineKeyboardButton.builder().text("🆘 Связь с ЦДС").callbackData("contactCDSButton").build());

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

    private InlineKeyboardMarkup createNecessaryStepsKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder().text("📁 Документы для оформления").callbackData("button14").build());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text("📊 1С ЕИСУП (1С ДО, 1С ВРМ)").callbackData("button11").build());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(InlineKeyboardButton.builder().text("✉️ SAP RNP (защищенная почта)").callbackData("button12").build());

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(InlineKeyboardButton.builder().text("🔑 ПКЗИ (ключ)").callbackData("button13").build());

        List<InlineKeyboardButton> row5 = new ArrayList<>();
        row4.add(InlineKeyboardButton.builder().text("⬅️ Назад в меню").callbackData("buttonMainMenu").build());

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        keyboard.add(row5);

        markup.setKeyboard(keyboard);
        return markup;
    }

    private InlineKeyboardMarkup createRecStepsKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder().text("📋 Оформить заявку на буфер обмена").callbackData("button21").build());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text("⚙️ Настройка терминала СИБИНТЕК").callbackData("button22").build());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(InlineKeyboardButton.builder().text("⬅️ Назад в меню").callbackData("buttonMainMenu").build());

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);

        markup.setKeyboard(keyboard);
        return markup;
    }

    private InlineKeyboardMarkup createInstructionsAboutRegistrationKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder().text("📝 Типовая заявка (образец)").callbackData("button31").build());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text("📞 Контакты ЦДС").callbackData("button32").build());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(InlineKeyboardButton.builder().text("👨‍💼 Согласование с руководителем").callbackData("button33").build());

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(InlineKeyboardButton.builder().text("⬅️ Назад в меню").callbackData("buttonMainMenu").build());

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);

        markup.setKeyboard(keyboard);
        return markup;
    }

    private InlineKeyboardMarkup createPKZIKeyKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder().text("📋 Инструкция по получению").callbackData("button41").build());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text("📞 Контакты поддержки").callbackData("button42").build());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(InlineKeyboardButton.builder().text("❓ Частые вопросы").callbackData("button43").build());

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(InlineKeyboardButton.builder().text("⬅️ Назад в меню").callbackData("buttonMainMenu").build());

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);

        markup.setKeyboard(keyboard);
        return markup;
    }

    private InlineKeyboardMarkup createPBOTOSEducationKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder().text("🎥 Пройти инструктаж").callbackData("button51").build());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text("📝 Записаться на обучение").callbackData("button52").build());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(InlineKeyboardButton.builder().text("🧪 Тестирование").callbackData("button53").build());

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(InlineKeyboardButton.builder().text("⬅️ Назад в меню").callbackData("buttonMainMenu").build());

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);

        markup.setKeyboard(keyboard);
        return markup;
    }

    private InlineKeyboardMarkup createInformationSecurityKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder().text("📹 Инструктаж").callbackData("button61").build());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text("📝 Тестирование").callbackData("button62").build());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(InlineKeyboardButton.builder().text("📜 Политика ИБ").callbackData("button63").build());

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(InlineKeyboardButton.builder().text("⬅️ Назад в меню").callbackData("buttonMainMenu").build());

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);

        markup.setKeyboard(keyboard);
        return markup;
    }

    private InlineKeyboardMarkup createContactsCDSKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder().text("💬 Чат с ЦДС").callbackData("button71").build());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text("❓ FAQ").callbackData("button72").build());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(InlineKeyboardButton.builder().text("⬅️ Назад в меню").callbackData("buttonMainMenu").build());

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);

        markup.setKeyboard(keyboard);
        return markup;
    }
}