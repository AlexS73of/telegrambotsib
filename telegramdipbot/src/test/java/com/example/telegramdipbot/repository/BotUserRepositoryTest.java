package com.example.telegramdipbot.repository;

import com.example.telegramdipbot.model.BotUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BotUserRepositoryTest {

    @Autowired
    private BotUserRepository botUserRepository;

    @Test
    public void testSaveAndFindByChatId() {
        Long chatId = 123456789L;

        BotUser user = BotUser.builder()
                .chatId(chatId)
                .username("test_user")
                .firstName("Иван")
                .lastName("Иванов")
                .registrationDate(LocalDateTime.of(2024, 5, 1, 12, 0))
                .build();

        botUserRepository.save(user);

        Optional<BotUser> foundUser = botUserRepository.findByChatId(chatId);

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("test_user");
        assertThat(foundUser.get().getFirstName()).isEqualTo("Иван");
        assertThat(foundUser.get().getLastName()).isEqualTo("Иванов");
        assertThat(foundUser.get().getRegistrationDate()).isEqualTo(LocalDateTime.of(2024, 5, 1, 12, 0));
    }
}