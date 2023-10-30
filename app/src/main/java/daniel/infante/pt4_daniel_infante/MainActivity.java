package daniel.infante.pt4_daniel_infante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button alarma;
    private Button mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarma = findViewById(R.id.alarma);
        mail = findViewById(R.id.mail);

        alarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlarm();
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Correo();
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

    public void Correo(){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:infantegarciadaniel@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Email de prueba");
        intent.putExtra(Intent.EXTRA_TEXT, "Este es el cuerpo del correo de prueba.");
        startActivity(intent);
    }


}