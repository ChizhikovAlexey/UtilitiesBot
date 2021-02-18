package bot.usersdata;

import org.telegram.telegrambots.meta.api.objects.Chat;

import java.util.HashMap;
import java.util.Map;

public class Chats {
    private final Map<String, BotState> chatsMap;

    public Chats() {
        chatsMap = new HashMap<>();
    }

    public Chats(Map<String, BotState> chatsMap) {
        this.chatsMap = chatsMap;
    }

    public BotState getState(String id) {
        chatsMap.putIfAbsent(id, BotState.MAIN);
        return chatsMap.get(id);
    }

    public void updateState(String id, BotState state) {
        chatsMap.put(id, state);
    }

    public BotState getState(Chat chat) {
        chatsMap.putIfAbsent(chat.getId().toString(), BotState.MAIN);
        return chatsMap.get(chat.getId().toString());
    }

    public void updateState(Chat chat, BotState state) {
        chatsMap.put(chat.getId().toString(), state);
    }
}
