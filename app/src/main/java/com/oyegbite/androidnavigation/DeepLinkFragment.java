package com.oyegbite.androidnavigation;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DeepLinkFragment extends Fragment {

    public DeepLinkFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deep_link, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.text);
        textView.setText(getArguments().getString("myarg"));

        Button notificationButton = view.findViewById(R.id.send_notification_button);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editArgs = view.findViewById(R.id.args_edit_text);
                Bundle args = new Bundle();
                args.putString("myarg", editArgs.getText().toString());
;
                NavDeepLinkBuilder deepLinkBuilder = NavHostFragment.findNavController(DeepLinkFragment.this).createDeepLink();
                deepLinkBuilder.setDestination(R.id.deeplink_dest);
                deepLinkBuilder.setArguments(args);

                PendingIntent pendingIntent = deepLinkBuilder.createPendingIntent();

                NotificationManager notificationManager =
                        (NotificationManager) getContext().getSystemService(getContext().NOTIFICATION_SERVICE);

                // Create the NotificationChannel, but only on API 26+ because
                // the NotificationChannel class is new and not in the support library
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // create android channel
                    NotificationChannel androidChannel =
                            new NotificationChannel(
                                    "deeplink",
                                    "Deep Links",
                                    NotificationManager.IMPORTANCE_HIGH // Make the notification sound.
                            );
                    notificationManager.createNotificationChannel(androidChannel);
                }

                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(getContext(), "deeplink");
                notificationBuilder.setContentTitle("Navigation");
                notificationBuilder.setContentText("Deep link to Android");
                notificationBuilder.setSmallIcon(R.drawable.ic_android_black);
                notificationBuilder.setContentIntent(pendingIntent);
                notificationBuilder.setAutoCancel(true);
                notificationManager.notify(0, notificationBuilder.build());

            }
        });
    }

}