package com.geekbrains.a1l1_helloworld;

import android.content.Context;

import java.util.Date;

class GreetingsBuilder {
    String getGreetings(Context context) {
        int currentHour = new Date(System.currentTimeMillis()).getHours();
        String result;
        // час сейчас
        if (5 <= currentHour && currentHour < 12 ) {      // Если утро
            result = context.getString(R.string.good_morning);
        } else if (12 <= currentHour && currentHour < 18) {  // Если день
            result = context.getString(R.string.good_afternoon);
        } else if (18 <= currentHour && currentHour < 21) {    // Если вечер
            result = context.getString(R.string.good_evening);
        } else {
            result = context.getString(R.string.good_night);   // Если поздний вечер или ночь
        }

        return result;
    }
}
