package bot.usersdata;

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
}
