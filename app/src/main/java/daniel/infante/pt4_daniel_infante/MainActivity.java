package daniel.infante.pt4_daniel_infante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button alarma;
    private Button evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarma = findViewById(R.id.alarma);
        evento = findViewById(R.id.evento);

        alarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlarm();
            }
        });

        evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HalloweenEvent();
            }
        });
    }

    public void createAlarm() {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, "Alarma de prueba.")
                .putExtra(AlarmClock.EXTRA_HOUR, 13)
                .putExtra(AlarmClock.EXTRA_MINUTES, 30);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void HalloweenEvent() {
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .putExtra(CalendarContract.Events.TITLE, "Halloween Party")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "Haunted House")
                // Configura la fecha de inicio al 31 de octubre a las 00:00 horas
                .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, FechaInicio())
                // Configura la fecha de fin al 31 de octubre a las 23:59 horas
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, FechaFinal());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No se encontró ninguna aplicación de calendario", Toast.LENGTH_LONG).show();
        }
    }

    public Long FechaInicio() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTimeInMillis();
    }

    public Long FechaFinal() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        return calendar.getTimeInMillis();
    }
}