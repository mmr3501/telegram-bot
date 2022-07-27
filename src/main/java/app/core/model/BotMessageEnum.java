package app.core.model;

public enum BotMessageEnum {
    EMPTY_COMMAND("Вы ввёли пустую команду"),
    NON_COMMAND_MESSAGE("Я вас не понимаю :("),
    HELP_MESSAGE("HELP_MESSAGE"),
    MAIN_MESSAGE("MAIN_MESSAGE"),
    CALL_MESSAGE("CALL_MESSAGE"),
    QUESTION_MESSAGE("QUESTION_MESSAGE");

    private final String message;

    BotMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return String.valueOf(message);
    }
}
