package com.example.loginpluschat.utility;

public class Constant {
    public static final String BASE_URL = "https://assignment.medimetry.in/api/v1/users/";
    public static String ID = "id";
    public static CharSequence loading_msg = "Please wait...";

    public interface RetrofitConstants {
        int CONNECT_TIMEOUT = 1;
        long READ_TIMEOUT = 30;
        long WRITE_TIMEOUT = 25;
    }

    public interface EndPointConstants {
        String GET = "get";
        String GET_USER_CHAT = "{id}/chats";
        String SEND_MSG_TO_USER = "chat";
    }
}
