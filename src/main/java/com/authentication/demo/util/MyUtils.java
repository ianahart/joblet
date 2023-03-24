package com.authentication.demo.util;

import java.util.List;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

public final class MyUtils {

    private MyUtils() {

    }

    public static String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    public static String titleCase(String title) {
        List<String> titleCased = Arrays.stream(
                title.split(" "))
                .map(x -> x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase()).toList();

        return String.join(" ", titleCased);
    }

    public static String makeReadableDate(Date createdAt) {
        Date currentDate = new Date();
        String readableDate = "";
        System.out.println(createdAt.toLocaleString());
        long elapsed = (currentDate.getTime() / 1000) - (createdAt.getTime() / 1000);
        System.out.println(elapsed);

        System.out.println(createdAt);
        if (elapsed <= 60) {
            readableDate = elapsed + " seconds ago";
        } else if (elapsed <= 60 * 60) {
            readableDate = Math.round((elapsed / 60)) + " minutes ago";
        } else if (elapsed > 60 * 60 && elapsed < 60 * 60 * 24) {
            readableDate = Math.round((elapsed / 60 / 60)) + " hours ago";
        } else if (elapsed > 60 * 60 * 24) {
            readableDate = Math.round((elapsed / 60 / 60 / 24)) + " day(s) ago";
        } else {
            readableDate = "";
        }

        return readableDate;
    }
}
