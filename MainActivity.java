package id.ac.id.telkomuniversity.tass.praktikactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button buttonPindah;
    TextView inputanText1;
    String our_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPindah = findViewById(R.id.btn_pindah);
        inputanText1 = findViewById(R.id.et_kirimText);


        buttonPindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                our_type = inputanText1.getText().toString();

                if(our_type.isEmpty()){
                    pesan("Isian tidak boleh kosong!");

                }
                else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("Perpindahan Halaman");
                    builder.setMessage("Yakin ingin pindah?");
                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            notif();
                            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                            intent.putExtra("our_type", our_type);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Tidak", null);
                    builder.setIcon(R.drawable.man_u);
                    builder.show();


                }
            }
        });

    }

    public void pesan(String isi){
        Toast.makeText(this, isi, Toast.LENGTH_LONG).show();
    }

    public void notif(){

        int NOTIFICATION_ID = 234;
        String CHANNEL_ID   = "my_channel_01";


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name           = CHANNEL_ID;
            String description          = CHANNEL_ID;
            int importance              = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.man_u);
        builder.setContentTitle("Ada notif nih!");
        builder.setContentText("Berhasil pindah ke activity dua");
        builder.setStyle(new NotificationCompat.BigTextStyle()
                .bigText("Berhasil pindah ke activity dua"));
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(NOTIFICATION_ID, builder.build());



    }










}
