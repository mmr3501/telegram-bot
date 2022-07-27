package app.core.config;

import app.core.model.JavaJobBot;
import app.core.model.ReplyKeyboardMaker;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@RequiredArgsConstructor
@Getter
public class AppConfig {
    @Value("${telegram.webhook-path}")
    String webhookPath;
    @Value("${telegram.bot-name}")
    String botName;
    @Value("${telegram.bot-token}")
    String botToken;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(getWebhookPath()).build();
    }

    @Bean
    public JavaJobBot springWebhookBot(SetWebhook setWebhook, ReplyKeyboardMaker replyKeyboardMaker) {
        var bot = new JavaJobBot(setWebhook, replyKeyboardMaker);
        bot.setBotPath(getWebhookPath());
        bot.setBotUsername(getBotName());
        bot.setBotToken(getBotToken());
        return bot;
    }
}