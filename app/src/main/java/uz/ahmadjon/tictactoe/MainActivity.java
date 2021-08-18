package uz.ahmadjon.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<int[]> combinationList = new ArrayList<>();

    private int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    private int playerTurn = 1;

    private int totalSelectedBoxes = 1;

    private static int playerOneBall = 0;

    private static int playerTwoBall = 0;

    ImageView btn1;
    ImageView btn2;
    ImageView btn3;
    ImageView btn4;
    ImageView btn5;
    ImageView btn6;
    ImageView btn7;
    ImageView btn8;
    ImageView btn9;

    ImageView imgPlayerTurn;

    CardView playerOneLayout;
    CardView playerTwoLayout;
    TextView playerOneName;
    TextView playerTwoName;

    TextView playerOneBallName;
    TextView playerTwoBallName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        imgPlayerTurn = findViewById(R.id.playerTurn);

        playerOneName = findViewById(R.id.player_one);
        playerTwoName = findViewById(R.id.player_two);

        playerOneLayout = findViewById(R.id.player_one_layout);
        playerTwoLayout = findViewById(R.id.player_two_layout);

        playerOneBallName = findViewById(R.id.player_1ball);
        playerTwoBallName = findViewById(R.id.player_2ball);

        btn1 = findViewById(R.id.game_btn1);
        btn2 = findViewById(R.id.game_btn2);
        btn3 = findViewById(R.id.game_btn3);
        btn4 = findViewById(R.id.game_btn4);
        btn5 = findViewById(R.id.game_btn5);
        btn6 = findViewById(R.id.game_btn6);
        btn7 = findViewById(R.id.game_btn7);
        btn8 = findViewById(R.id.game_btn8);
        btn9 = findViewById(R.id.game_btn9);

        combinationList.add(new int[]{0, 1, 2});
        combinationList.add(new int[]{3, 4, 5});
        combinationList.add(new int[]{6, 7, 8});
        combinationList.add(new int[]{0, 3, 6});
        combinationList.add(new int[]{1, 4, 7});
        combinationList.add(new int[]{2, 5, 8});
        combinationList.add(new int[]{2, 4, 6});
        combinationList.add(new int[]{0, 4, 8});

        playerOneBallName.setText(playerOneBall + "");
        playerTwoBallName.setText(playerTwoBall + "");

        final String getPlayerOneName = getIntent().getStringExtra("one");
        final String getPlayerTwoName = getIntent().getStringExtra("two");

        playerOneName.setText(getPlayerOneName);
        playerTwoName.setText(getPlayerTwoName);
        playerOneLayout.setCardBackgroundColor(Color.parseColor("#FF018786"));
        playerTwoLayout.setCardBackgroundColor(Color.parseColor("#FFFFFF"));

        btn1.setOnClickListener(v -> {
            if (isBoxselectable(0)) {
                performAction((ImageView)v,0);
            }
        });
        btn2.setOnClickListener(v -> {
            if (isBoxselectable(1)) {
                performAction((ImageView)v,1);
            }
        });
        btn3.setOnClickListener(v -> {
            if (isBoxselectable(2)) {
                performAction((ImageView)v,2);
            }
        });
        btn4.setOnClickListener(v -> {
            if (isBoxselectable(3)) {
                performAction((ImageView)v,3);
            }
        });
        btn5.setOnClickListener(v -> {
            if (isBoxselectable(4)) {
                performAction((ImageView)v,4);
            }
        });
        btn6.setOnClickListener(v -> {
            if (isBoxselectable(5)) {
                performAction((ImageView)v,5);
            }
        });
        btn7.setOnClickListener(v -> {
            if (isBoxselectable(6)) {
                performAction((ImageView)v,6);
            }
        });
        btn8.setOnClickListener(v -> {
            if (isBoxselectable(7)) {
                performAction((ImageView)v,7);
            }
        });
        btn9.setOnClickListener(v -> {
            if (isBoxselectable(8)) {
                performAction((ImageView) v,8);
            }
        });
    }

    private void performAction(ImageView image, int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = playerTurn;
        if (playerTurn == 1) {

            image.setImageResource(R.drawable.new_x);

            if (checkPlayerWin()) {
                playerOneBall++;
                playerOneBallName.setText(playerOneBall + "");
                Windialog windialog = new Windialog(this, playerOneName.getText().toString() + " has Won the Match", MainActivity.this);
                windialog.setCancelable(false);
                windialog.show();

            } else if (totalSelectedBoxes == 9) {
                Windialog windialog = new Windialog(this, "It is a draw!", MainActivity.this);
                windialog.setCancelable(false);
                windialog.show();
            } else {
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        } else {
            image.setImageResource(R.drawable.new_o);

            if (checkPlayerWin()) {
                playerTwoBall++;
                playerTwoBallName.setText(playerTwoBall + "");
                Windialog windialog = new Windialog(this, playerTwoName.getText().toString() + " has Won the Match", MainActivity.this);
                windialog.setCancelable(false);
                windialog.show();
            } else if (selectedBoxPosition == 9) {
                Windialog windialog = new Windialog(this, "It is a draw!", MainActivity.this);
                windialog.setCancelable(false);
                windialog.show();
            } else {

                changePlayerTurn(1);
                totalSelectedBoxes++;

            }
        }
    }

    private void changePlayerTurn(int currectPlayerTurn) {

        playerTurn = currectPlayerTurn;

        if (playerTurn == 1) {
            playerOneLayout.setCardBackgroundColor(Color.parseColor("#FF018786"));
            playerTwoLayout.setCardBackgroundColor(Color.WHITE);
            imgPlayerTurn.setRotationY(0f);
        } else {
            playerOneLayout.setCardBackgroundColor(Color.WHITE);
            playerTwoLayout.setCardBackgroundColor(Color.parseColor("#FF018786"));
            imgPlayerTurn.setRotationY(180f);
        }
    }

    private boolean checkPlayerWin() {
        boolean response = false;

        for (int i = 0; i < combinationList.size(); i++) {
            final int[] combination = combinationList.get(i);

            if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn && boxPositions[combination[2]] == playerTurn) {
                response = true;
            }
        }
        return response;
    }

    private boolean isBoxselectable(int boxPosition) {

        boolean response = false;

        if (boxPositions[boxPosition] == 0) {
            response = true;
        }
        return response;
    }

    public void restartMatch() {
        boxPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        playerTurn = 1;

        totalSelectedBoxes = 1;
        playerOneLayout.setCardBackgroundColor(Color.parseColor("#FF018786"));
        playerTwoLayout.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        btn1.setImageResource(0);
        btn2.setImageResource(0);
        btn3.setImageResource(0);
        btn4.setImageResource(0);
        btn5.setImageResource(0);
        btn6.setImageResource(0);
        btn7.setImageResource(0);
        btn8.setImageResource(0);
        btn9.setImageResource(0);

    }
}
