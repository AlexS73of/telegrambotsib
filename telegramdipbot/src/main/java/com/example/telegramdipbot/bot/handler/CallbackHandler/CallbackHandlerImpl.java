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
                case "button11":
                    sendFile(chatId, "button14_Документы_для_оформления.pdf", bot);
                    return;

                // Стандартные кнопки
                case "unnecessaryStepsButton":
                    message = keyboard.createKeyboardMessage(chatId, "🔹 Обязательные шаги:", KeyboardType.NECESSARY_STEPS);
                    break;
                case "recommendedStepsButton":
                    message = keyboard.createKeyboardMessage(chatId, "📌 Рекомендуемые шаги:", KeyboardType.REC_STEPS);
                    break;
                case "usefulInformationButton":
                    message = keyboard.createKeyboardMessage(chatId, "ℹ️ Полезная информация", KeyboardType.USEFUL_INFO);
                    break;
                case "contactsButton":
                    message = keyboard.createKeyboardMessage(chatId, "📞 Контакты:", KeyboardType.CONTACTS);
                    break;
                case "button12":
                    message = new SendMessage(chatId.toString(), """
                            🔐 Информационная безопасность
                            
                            🔐 Каждый новый сотрудник в обязательном порядке проходит инструктаж и тестирование по информационной безопасности.

                            ⚠️ Обратите особое внимание на требования по работе с персональными данными, коммерческой и технической информацией.

                            📧 Для обмена данными используйте специальное ПО — почта S.A.P., деловая почта.

                            🖨️ Печать данной информации через сетевые устройства (МФУ, сетевой принтер) запрещена.
                            """);
                    break;
                case "button13":
                    message = new SendMessage(chatId.toString(), """
                            🎓 ПБОТОС: обучение и аттестация

                            Обязательная часть ввода нового сотрудника — прохождение тестирования по ПБОТОС:
                            • Промышленная безопасность
                            • Охрана труда
                            • Охрана окружающей среды

                            📎 Тестирование проводится через корпоративную платформу.
                            💡 После прохождения формируется отчёт и назначается ответственный за инструктаж.

                            📍 Контактное лицо:
                            Носовская Елена Александровна
                            📧 E.A.Nosovskaja@sibsoft.rosneft.ru
                            ☎ 8 (846) 277-41-70 доб. 88-13
                            """);
                    break;
                case "button14":
                    message = new SendMessage(chatId.toString(), """
                            🔐 Учётные записи и доступы

                            При приёме на работу вам предоставляются:
                            • Учётная запись в домене @sibintek.ru
                            • Учётная запись в домене @sibsoft.rosneft.ru
                            • Доступ к ROSNEFT-ресурсам
                            • Доступ к корпоративной почте и файловым хранилищам
                            • Доступ к удалённому рабочему столу

                            💡 Подключение осуществляется через сайт: rd5-int.sibintek.ru

                            ❗ Все логины и инструкции приходят на почту. Проверьте спам и обратитесь к HR при отсутствии писем.
                            """);
                    break;
                    case "button15":
                    message = new SendMessage(chatId.toString(), """
                            🗂 Оформление в 1С и СЭД

                            Необходимо зарегистрироваться и начать работу в следующих системах:
                            • 1С ЕКАМ — для учёта персонала
                            • СЭД (система электронного документооборота) — для подписания заявлений, служебных записок и т.п.

                            📝 Оформление происходит автоматически после подачи документов.
                            ❗ Не забудьте проверить, открылись ли вам доступы.

                            📍 В случае проблем — обращайтесь к кадровому администратору или в техподдержку.
                            """);
                    break;
                    case "button16":
                    message = new SendMessage(chatId.toString(), """
                            💼 Дистанционная работа (ДР)

                            В случае перехода на дистанционный режим, сотруднику предоставляется временный доступ к удалённой инфраструктуре.

                            🔹 Используется ПО «Почта ДР»
                            🔹 Сайт для входа: rd5-int.sibintek.ru
                            🔹 Доступ предоставляется сроком на 1 месяц
                            🔹 Для продления доступа — обратиться к кадровому администратору

                            📎 Инструкции направляются на корпоративную почту
                            """);
                    break;
                    
                case "button21":
                    message = new SendMessage(chatId.toString(), """
                            ✉️ *Корпоративная почта*

                            Вы получите доступ к:
                            • `@sibintek.ru` — внутренняя коммуникация
                            • `@sibsoft.rosneft.ru` — взаимодействие с заказчиком

                            🔐 Доступ к почте и удалённому рабочему столу:
                            🌐 rd5-int.sibintek.ru

                            Письма с логинами отправляются автоматически. Обратитесь в HR, если не получили.
                            """);
                    break;
                case "button22":
                    message = new SendMessage(chatId.toString(), """
                            🤝 *Вступление в профсоюз*

                            Плюсы:
                            • Материальная помощь
                            • Компенсации на спорт и отдых
                            • Подарки детям
                            • Корпоративные мероприятия

                            📍 Контакт: Вьюн Виталий  
                            📞 Тел.: (846) 277-41-70

                            📎 Заявление можно подать через кадрового администратора.
                            """);
                    break;
                case "button23":
                    message = new SendMessage(chatId.toString(), """
                            📚 *Обучение и адаптация*

                            Рекомендуется пройти:
                            • Онлайн-курсы по внутренним системам
                            • Вводные инструкции по платформам
                            • FAQ по работе в компании

                            🎯 Доступ открывается после активации учётной записи.
                            """);
                    break;
                case "button24":
                    message = new SendMessage(chatId.toString(), """
                            🎁 *Соц-поддержка и бонусы*

                            Вам доступны:
                            • Мат-помощь при рождении ребёнка
                            • Компенсации за спорт и оздоровление
                            • Подарки к праздникам
                            • Поддержка через профсоюз

                            📎 Подробности уточняйте у кадрового администратора.
                            """);
                    break;
                    case "button31":
                    message = new SendMessage(chatId.toString(), """
                    ❓ Часто задаваемые вопросы (FAQ)

                    • Где получить доступ в 1С?
                    • Как продлить удалённый доступ?
                    • К кому обратиться по больничному?
                    • Где взять справку 2-НДФЛ?
                    • Как вступить в профсоюз?

                    🔹 Ответы по этим вопросам вы можете найти в других разделах бота или у кадрового администратора.
                            """);
                    break;
                    case "button32":
                    message = new SendMessage(chatId.toString(), """
                            👩‍💼 Кадровый администратор

                            📍 Самара, ул. Мичурина, д. 52 (правое крыло), 5 этаж, кабинет 513
                            📞 Тел.: +7 (846) 255-48-12 вн. 88-12

                            📍 Самара, ул. Московское шоссе, д. 4, к. 28б, кабинет 2.5
                            📞 Тел.: +7 (846) 255-44-84 вн. 84-84

                            📍 Самара, ул. Грозненская, д. 5, к. 14, кабинет 14
                            📞 Тел.: +7 (846) 307-30-47

                            📍 Сызрань, пер. Отраслевой, д. 3, кабинет 210
                            📞 Тел.: +7 (8464) 90-87-22

                            📍 Новокуйбышевск, ул. Суворова, д. 12б, кабинет 44
                            📞 Тел.: +7 (846-35) 3-40-59

                            📍 Оренбург, ул. Чкалова, д. 43а, кабинет Б211
                            📞 Тел.: +7 (3532) 37-54-62

                            📍 Бузулук, ул. Магистральная, д. 1, кабинет 203
                            📞 Тел.: +7 (35342) 7-46-67
                            """);
                    break;
                    case "button33":
                    message = new SendMessage(chatId.toString(), """
                            📄 Доступные шаблоны заявлений:

                            • Заявление на отпуск
                            • Согласие на обработку персональных данных
                            • Справка с места работы
                            • Командировочное задание

                            📎 Все шаблоны доступны по внутренней сети или по запросу у кадрового администратора.
                            """);
                    break;
                    case "button34":
                    message = new SendMessage(chatId.toString(), """
                            📘 Инструкции и методички

                            • Памятка новому сотруднику
                            • Памятка по ИБ, ПБОТОС, 1С
                            • Гайд по подключению ДР и корпоративной почты

                            📂 Все файлы доступны в PDF по внутренней ссылке или выдаются при оформлении.
                            """);
                    break;
                    case "button41":
                    message = new SendMessage(chatId.toString(), """
                            📍 Офисы и подразделения

                            • г. Самара, ул. Московское шоссе, д. 4, к. 2ВБ, каб. 1.1
                            • г. Самара, ул. Грозненская, д. 5, каб. 32
                            • г. Новокуйбышевск, ул. Суворова, д. 12Б, каб. 43
                            • г. Сызрань, пер. Отраслевой, д. 3, (здание склада цемента) каб. 4
                            • г. Оренбург, ул. Чкалова, д. 43а, каб. С-211А
                            • г. Бузулук, ул. Магистральная, д. 1, каб. 102
                            """);
                    break;
                    case "button42":
                    message = new SendMessage(chatId.toString(), """
                            ☎️ Телефоны для связи
                            
                            Приёмная Начальника ТУ «Макрорегион Поволжье»:
                            • г. Самара, ул. Мичурина, д.52 (Левое крыло), офис 202 Марина Смирнова, Главный специалист, email: MA_smirnova4@sibsoft.rosneft.ru. Тел.: +7(846)2554245 (внутр.8245), +7(927)7108617
                            • Ольга Митрофанова, Старший специалист, г. Самара, ул. Мичурина, д.52, оффис 202 (левое крыло), тел: +7(937)6497407, OA_Mitrofanova@sibsoft.rosneft.ru

                            Сертифи
                            • Самара, Мичурина — 8(846)255-49-97
                            • Самара, Грозненская — 8(846)307-38-06 
                            • Новокуйбышевск: (84635)3-21-22
                            • Сызрань: (8464)90-80-99
                            • Оренбург: 8(932)530-05-94
                            • Бузулук: (35342)7-75-83
                            """);
                    break;
                    case "button43":
                    message = new SendMessage(chatId.toString(), """
                            📧 Важные e-mail адреса:

                            • V_Poleshchikov@sibsoft.rosneft.ru — по хозяйственным вопросам
                            • EA.Nosovskaja@sibsoft.rosneft.ru — ПБОТОС, охрана труда
                            • V.Vyun2@sibsoft.rosneft.ru — профсоюз
                            • OKhodov2@sibsoft.rosneft.ru — автоматизация
                            • M.Smirnova@sibsoft.rosneft.ru — региональные взаимодействия
                            """);
                    break;            
                    case "button44":
                    message = new SendMessage(chatId.toString(), """
                            👥 Контакты руководства

                            • Светлана Михайловна Воронцова — руководитель ТУ
                            • Тамышев Евгений Сергеевич — заместитель
                            • Хохлов Леонид Геннадьевич — автоматизация
                            • Кузнецова Екатерина Николаевна — соц-поддержка

                            📞 Общий тел. ТУ: +7 (927) 717-65-74
                            📧 Email: OKhodov2@sibsoft.rosneft.ru
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