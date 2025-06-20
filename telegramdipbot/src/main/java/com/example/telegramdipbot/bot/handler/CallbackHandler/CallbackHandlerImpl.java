package com.example.telegramdipbot.bot.handler.CallbackHandler;

import java.io.ByteArrayInputStream;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.example.telegramdipbot.bot.keyboard.KeyboardInterface;
import com.example.telegramdipbot.bot.keyboard.KeyboardType;
import com.example.telegramdipbot.model.FileEntity;
import com.example.telegramdipbot.repository.FileRepository;

@Component
public class CallbackHandlerImpl implements CallbackHandler {
    private final KeyboardInterface keyboard;
    private final FileRepository fileRepository;

    public CallbackHandlerImpl(KeyboardInterface keyboard, FileRepository fileRepository) {
        this.keyboard = keyboard;
        this.fileRepository = fileRepository;
    }

    @Override
    public void handleCallback(Update update, Long chatId, String callbackData, TelegramLongPollingBot bot) {
        SendMessage message;

        try {
            switch (callbackData) {
                // Кнопки с отправкой PDF-файлов из БД
                case "button14":
                    sendFile(chatId, "button14_Документы_для_оформления.pdf", bot);
                    return;
                case "button22":
                    sendFile(chatId, "button22_terminal_instruction", bot);
                    return;
                case "button31":
                    sendFile(chatId, "button31_application_template", bot);
                    return;
                case "button33":
                    sendFile(chatId, "button33_agreement_manager", bot);
                    return;
                case "button51":
                    sendFile(chatId, "button51_instruction_training", bot);
                    return;
                case "button61":
                    sendFile(chatId, "button61_information_security", bot);
                    return;

                // Стандартные кнопки
                case "unnecessaryStepsButton":
                    message = keyboard.createKeyboardMessage(chatId, "🔹 Обязательные шаги:", KeyboardType.NECESSARY_STEPS);
                    break;
                case "recommendedStepsButton":
                    message = keyboard.createKeyboardMessage(chatId, "📌 Рекомендуемые шаги:", KeyboardType.REC_STEPS);
                    break;
                case "instructionRegistrationButton":
                    message = keyboard.createKeyboardMessage(chatId, "📄 Инструкции по оформлению заявок:", KeyboardType.INSTRUCTIONS_ABOUT_REGISTRATION);
                    break;
                case "pkziKeyButton":
                    message = keyboard.createKeyboardMessage(chatId, "🔑 ПКЗИ (ключ):", KeyboardType.PKZI_KEY);
                    break;
                case "pbtosEducationButton":
                    message = keyboard.createKeyboardMessage(chatId, "🎓 ПБОТОС (обучение)", KeyboardType.PBOTOS_EDUCATION);
                    break;
                case "informationSecurityButton":
                    message = keyboard.createKeyboardMessage(chatId, "🛡️ Информационная безопасность", KeyboardType.INFORMATION_SECURITY);
                    break;
                case "contactCDSButton":
                    message = keyboard.createKeyboardMessage(chatId, "🆘 Contact_CDS", KeyboardType.CONTACTS_CDS);
                    break;

                case "button11":
                    message = new SendMessage(chatId.toString(), "ℹ️ Для доступа к 1С ЕИСУП обратитесь в ЦДС. Контакты: cds@sibintek.ru");
                    break;
                case "button12":
                    message = new SendMessage(chatId.toString(), "✉️ SAP RNP используется для защищенной почты. Заявку оформляйте через ЦДС.");
                    break;
                case "button13":
                    message = keyboard.createKeyboardMessage(chatId, "🔑 ПКЗИ (ключ):", KeyboardType.PKZI_KEY);
                    break;
                case "button21":
                    message = new SendMessage(chatId.toString(), "📋 Буфер обмена ускоряет работу. Оформите заявку в ЦДС через раздел 'Инструкции'.");
                    break;
                case "button32":
                    message = keyboard.createKeyboardMessage(chatId, "📞 Контакты ЦДС: ", KeyboardType.CONTACTS_CDS);
                    break;
                case "button41":
                    message = new SendMessage(chatId.toString(), 
                        "📍 Адреса офисов для получения ключей ПКЗИ:\n\n" +
                        "🏢 г. Самара, ул. Московское шоссе, д.4, к. 28Б, кабинет 1-1\n" +
                        "📞 тел: 8(846)255-49-97\n\n" +
                        "🏢 г. Самара, ул. Грозненская, д.5, кабинет 32\n" +
                        "📞 тел: 8(846)307-38-06\n\n" +
                        "🏢 г. Новокуйбышевск, ул. Суворова, д.12Б, кабинет 43\n" +
                        "📞 тел: 8(84635)3-21-22\n\n" +
                        "🏢 г. Сызрань, пер. Отраслевой, д. 3 (здание склада цемента), кабинет 4\n" +
                        "📞 тел: 8(8464)90-80-99\n\n" +
                        "🏢 г. Оренбург, ул. Чкалова, д. 43а, кабинет С-211А\n" +
                        "📞 тел: 8(932)530-05-94\n\n" +
                        "🏢 г. Бузулук, ул. Магистральная, д. 1, кабинет 102\n" +
                        "📞 тел: (35342)7-75-83\n\n" +
                        "1️⃣ Заполните заявку в ЦДС\n" +
                        "2️⃣ Получите ключ лично в одном из указанных офисов");
                    break;
                case "button42":
                    message = new SendMessage(chatId.toString(), """
                            🛡 Контакты отдела информационной безопасности

                            👤 Носовская Евгения Анатольевна  
                            📧 E.Nosovskaia@sibsoft.rosneft.ru  
                            📞 (846) 255-48-12  
                            📍 г. Самара, ул. Мичурина, 52, офис 221а

                            По вопросам инструктажей, тестирования по ПБТОС и ИБ, политик безопасности — обращайтесь по указанным контактам.
                            """);
                    break;
                    case "button43":
                    message = new SendMessage();
                    message.setChatId(chatId.toString());
                    message.setText("""
                        🔐 *Вопросы по теме: Сертификаты и ключи ПКЗИ (ПЗМ)*
                
                        • *Что такое ПКЗИ?*
                        ПКЗИ — программно-криптографические средства информации. Используются для защищённой корпоративной почты (ПЗМ), доступа к внутренним ресурсам и электронного документооборота.
                
                        • *Где получить сертификат и ключ ПКЗИ?*
                        Самара, ул. Мичурина, 52 (левое крыло), офис 202  
                        Специалист: О.А. Митрофанова  
                        Почта: MitrofanovaOA@sibsoft.rosneft.ru
                
                        • *Какие документы нужны?*
                        Паспорт (оригинал), СНИЛС, доверенность от организации (если не оформляете на себя).
                
                        • *Сколько времени занимает выпуск ключа?*
                        Обычно не более 1 рабочего дня, если предоставлены все необходимые документы.
                
                        • *Как установить ключ?*
                        Помощь оказывает специалист при получении. При необходимости — удалённая помощь через AnyDesk.
                
                        • *Что делать при утрате ключа?*
                        Немедленно сообщите в отдел по работе с ключами. Оформляется отзыв и перевыпуск нового.
                
                        • *Срок действия ключа?*
                        12 месяцев с даты выпуска. Не забывайте подавать заявку на продление заранее.
                
                        • *Какой формат почты по ПЗМ?*
                        login@sibintek.ru — для работы в защищённом сегменте. Доступ осуществляется через SAP PNP.
                        """);
                    break;                
                    case "button52":
                    message = new SendMessage();
                    message.setChatId(chatId.toString());
                    message.setText("""
                        📝 *Запись на обучение по охране труда и промышленной безопасности (ПБОТОС)*
                
                        • Обучение обязателено для всех сотрудников.
                        • Курсы проводятся по адресу: Самара, ул. Мичурина, 52 (левое крыло), каб. 208.
                        • Формат — очный либо дистанционный (по согласованию с руководителем).
                
                        📌 *Как записаться:*
                        1. Сообщите руководителю подразделения о готовности пройти обучение.
                        2. Подайте заявку через внутреннюю систему СИБИНТЕК или по электронной почте.
                        3. Ожидайте подтверждение с датой и временем.
                
                        ❗ После прохождения обучения вы получите доступ к работе с ключевыми системами компании.
                
                        Контактное лицо: Плотникова Ю.А.  
                        Тел.: +7 (846) 300-00-00 (вн. 1234)  
                        Почта: PlotnikovaYA@sibsoft.rosneft.ru
                        """);
                    message.enableMarkdown(true);
                    break;
                case "button53":
                    message = new SendMessage(chatId.toString(), """
                            🧯 Тестирование по ПБТОС (Промышленная безопасность и охрана труда)

                            Каждому новому сотруднику необходимо пройти вводный инструктаж и тестирование по ПБТОС. Это обязательная часть адаптации и допуска к работе.

                            📌 Порядок прохождения:
                            - Инструктаж проводится в очном формате или в электронном виде (уточняется на месте).
                            - По завершении сотрудник сдает тест на знание правил охраны труда и промышленной безопасности.

                            📍 Ответственное лицо:
                            👤 Носовская Евгения Анатольевна  
                            📞 (846) 255-48-12  
                            📧 E.Nosovskaia@sibsoft.rosneft.ru  
                            🏢 Самара, ул. Мичурина, 52, офис 221а
                            """);
                    break;
                case "button62":
                    message = new SendMessage(chatId.toString(), """
                            🧪 Тестирование по информационной безопасности

                            Каждый новый сотрудник обязан пройти инструктаж и тестирование по ИБ и ПБТОС в рамках программы адаптации.

                            📌 Информацию о порядке прохождения инструктажа уточняйте у ответственного лица:

                            👤 Носовская Евгения Анатольевна  
                            📍 Самара, ул. Мичурина, 52, офис 221а  
                            📞 (846) 255-48-12  
                            📧 E.Nosovskaia@sibsoft.rosneft.ru
                            """);
                    break;
                case "button63":
                    message = new SendMessage(chatId.toString(), """
                            📄 Политика информационной безопасности

                            С политикой ИБ вы можете ознакомиться в разделах корпоративного портала или при первом входе в систему.  
                            В документе описаны требования к защите информации, ответственности сотрудников и правила работы с корпоративными данными.

                            ⚠️ Напоминаем:
                            Каждый сотрудник несёт личную ответственность за сохранность информации. Нарушение политики может повлечь дисциплинарные меры.

                            📌 Вопросы и разъяснения:
                            👤 Носовская Евгения Анатольевна  
                            📧 E.Nosovskaia@sibsoft.rosneft.ru  
                            📞 (846) 255-48-12
                            """);
                    break;
                case "button71":
                    message = new SendMessage(chatId.toString(), """
                            📞 Контакты Центра дистанционного сопровождения (ЦДС):

                            🔹 Общие вопросы:  
                            👤 Полешенков Ивoнн Владимирoвич  
                            📧 V.Poleshchikov@sibsoft.rosneft.ru

                            🔹 Вопросы по расчёту заработной платы:  
                            👤 Вольнов Виталий Витальевич  
                            📧 Kac@sibintek.ru  
                            📞 (846) 255-42-70

                            🔹 Вопросы по профсоюзу:  
                            📧 Kac@sibintek.ru  
                            📞 моб.: +7 927 600-09-14
                            """);
                    break;
                case "button72":
                    message = new SendMessage(chatId.toString(), """
                            ❓ Часто задаваемые вопросы (FAQ)
                            🔹 Где находится кадровый отдел?  
                            📍 Самара, ул. Мичурина, д. 52, офис 202  
                            📞 +7 (937) 649-4107

                            🔹 Как получить доступ к удалённому рабочему столу?  
                            🌐 https://rd5-int.sibintek.ru  
                            👤 Учётная запись: ваша_учётка@sibsoft.rosneft.ru

                            🔹 Как вступить в профсоюз?  
                            Обратитесь к Виталию Вольнову — Kac@sibintek.ru

                            🔹 Контакт по общим вопросам:  
                            👤 Полешенков И.В. — V.Poleshchikov@sibsoft.rosneft.ru

                            """);
                    break;
                default:
                    message = keyboard.createKeyboardMessage(chatId, "🏠 Главное меню:", KeyboardType.MAIN_MENU);
                    break;
            }

            bot.execute(message);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendFile(Long chatId, String fileName, TelegramLongPollingBot bot) {
        Optional<FileEntity> optionalFile = fileRepository.findByName(fileName);
    
        if (optionalFile.isPresent()) {
            FileEntity file = optionalFile.get();
            try {
                InputFile inputFile = new InputFile(new ByteArrayInputStream(file.getData()), file.getName() + ".pdf");
                SendDocument document = new SendDocument();
                document.setChatId(chatId.toString());
                document.setDocument(inputFile);
                bot.execute(document);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                SendMessage error = new SendMessage(chatId.toString(), "❌ Файл не найден.");
                bot.execute(error);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}    