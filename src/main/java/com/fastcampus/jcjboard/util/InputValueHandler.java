package com.fastcampus.jcjboard.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class InputValueHandler {
    /**
     * @param paramName request.getParameter에 매개변수로 들어갈 문자열 값을 넣습니다.
     */
    public static int convertToInt(String paramName, HttpServletRequest req, HttpServletResponse resp) {
        int result = -1;
        try {
            result = Integer.parseInt(req.getParameter(paramName));
        } catch (NumberFormatException e) {
            System.out.println("converToint 에러발생"+paramName);
            try {
                resp.sendRedirect("/board/error");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return result;
    }


    public static boolean isEmpty(String str1, String str2, String str3) {
        try {
            if (str1.isEmpty() || str2.isEmpty() || str3.isEmpty())
                return true;
        } catch (NullPointerException e) {
            return true;
        }

        return false;
    }

    public static boolean isEmpty(String str1, String str2, String str3, String str4) {
        try {
            if (str1.isEmpty() || str2.isEmpty() || str3.isEmpty() || str4.isEmpty())
                return true;
        } catch (NullPointerException e) {
            return true;
        }

        return false;
    }

    public static boolean isEmpty(String str1, String str2, String str3, String str4, String str5) {
        try {
            if (str1.isEmpty() || str2.isEmpty() || str3.isEmpty() || str4.isEmpty()||str5.isEmpty())
                return true;
        } catch (NullPointerException e) {
            return true;
        }

        return false;
    }
}

