package app.core.model;

import com.google.common.base.Strings;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class JavaJobBot extends SpringWebhookBot {
    String botPath;
    String botUsername;
    String botToken;
    ReplyKeyboardMaker replyKeyboardMaker;

    @Autowired
    public JavaJobBot(SetWebhook setWebhook, ReplyKeyboardMaker replyKeyboardMaker) {
        super(setWebhook);
        this.replyKeyboardMaker = replyKeyboardMaker;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return update.getMessage() == null ?
                new SendMessage(update.getMessage().getChatId().toString(), BotMessageEnum.EMPTY_COMMAND.getMessage()) :
                answerMessage(update.getMessage());
    }

    private BotApiMethod<?> answerMessage(Message message) {
        var chatId = message.getChatId().toString();
        return Strings.isNullOrEmpty(message.getText()) ? new SendMessage(chatId, BotMessageEnum.EMPTY_COMMAND.getMessage()) :
                switch (message.getText()) {
                    case "/start" -> getStartMessage(chatId);
                    case "Основная информация" -> getMainMessage(chatId);
                    case "Записаться на звонок" -> getCallMessage(chatId);
                    case "Задать вопрос в чате" -> getQuestionMessage(chatId);
                    default -> new SendMessage(chatId, BotMessageEnum.NON_COMMAND_MESSAGE.getMessage());
                };
    }

    private SendMessage getStartMessage(String chatId) {
        var sendMessage = new SendMessage(chatId, BotMessageEnum.HELP_MESSAGE.getMessage());
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        return sendMessage;
    }

    private SendMessage getMainMessage(String chatId) {
        var sendMessage = new SendMessage(chatId, BotMessageEnum.MAIN_MESSAGE.getMessage());
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        return sendMessage;
    }

    private SendMessage getCallMessage(String chatId) {
        var sendMessage = new SendMessage(chatId, BotMessageEnum.CALL_MESSAGE.getMessage());
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        return sendMessage;
    }

    private SendMessage getQuestionMessage(String chatId) {
        var sendMessage = new SendMessage(chatId, BotMessageEnum.QUESTION_MESSAGE.getMessage());
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        return sendMessage;
    }
}