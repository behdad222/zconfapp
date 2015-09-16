package ir.zconf.zconfapp;

import android.app.Application;
import android.content.res.Configuration;

import java.util.Locale;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Locale locale = new Locale("fa");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
}
