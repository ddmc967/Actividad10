package com.example.daniel.actividad10;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import java.util.*;

public class servicio extends Service {
    private final Binder cli = new cliente();

    public class cliente extends Binder {
        servicio getService() {
            return servicio.this;
        }
    }
    public servicio() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return cli;
    }
}
