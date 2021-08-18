package uz.ahmadjon.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayers extends AppCompatActivity {

   EditText playerOne, playerTwo;

   ConstraintLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        playerOne = findViewById(R.id.player_one_name);
        playerTwo = findViewById(R.id.player_two_name);

        root = findViewById(R.id.root);

    }

    public void startGame(View view) {
        final String one = playerOne.getText().toString();
        final String two = playerTwo.getText().toString();
        if (one.isEmpty() || two.isEmpty()){
            Toast.makeText(this, "Enter The Name", Toast.LENGTH_SHORT).show();
        }else{
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            i.putExtra("one",one);
            i.putExtra("two",two);
            startActivity(i);
        }
    }

    public void exitGame(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this,R.style.MaterialAlertDialog_rounded);
        dialog.setTitle("Exit Game");
        dialog.setMessage("Are you leaving the game?");

        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
}