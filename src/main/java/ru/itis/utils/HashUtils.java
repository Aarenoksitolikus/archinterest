package ru.itis.utils;

import ru.itis.dao.entities.User;
import ru.itis.logic.services.UserService;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserUtils {

    private static UserService userService;

    public static String getHash(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder resultHash = new StringBuilder();
            for (byte b : hash) {
                resultHash.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return resultHash.toString();
        } catch (NoSuchAlgorithmException e){
            throw new IllegalArgumentException(e);
        }
    }

    public static User getCurrentUser(HttpSession session) {
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        if (username != null && password != null) {
            return userService.get(username, getHash(password));
        }

        return null;
    }
}
