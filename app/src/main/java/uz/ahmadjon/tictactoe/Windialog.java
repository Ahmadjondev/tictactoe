package uz.ahmadjon.tictactoe;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class Windialog  extends Dialog {

    private String message;
    private final MainActivity activity;

    public Windialog(@NonNull Context context,String message,MainActivity activity) {
        super(context);
        this.message = message;
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_dialog);
        final TextView messageText  = findViewById(R.id.winDialog_text);
        final AppCompatButton startAgain = findViewById(R.id.start_again);
        final AppCompatButton mainMenu = findViewById(R.id.main_menu);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        messageText.setText(message);

        startAgain.setOnClickListener(v -> {
            activity.restartMatch();
            dismiss();
        });
        mainMenu.setOnClickListener(v -> {
            Intent i = new Intent(activity,AddPlayers.class);
            activity.startActivity(i);
            activity.finish();
        });
    }
}
