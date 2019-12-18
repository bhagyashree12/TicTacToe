package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView txt_view_player1;
    TextView txt_view_player2;
    Button reset;
    Button new_game;
    Button[][] buttons = new Button[3][3];
    boolean player1 = true;
    int count = 0;
    int playerone = 0;
    int playertwo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_view_player1 = findViewById(R.id.text_view_p1);
        txt_view_player2 = findViewById(R.id.text_view_p2);
        new_game = findViewById(R.id.new_game);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                String id = "btn_" + i + j;
                int res = getResources().getIdentifier(id, "id", getPackageName());
                buttons[i][j] = findViewById(res);//number of array is more so manually get the id by using getresources
                buttons[i][j].setOnClickListener(this);

            }
        }

        reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_view_player1.setText("Player 1:0");
                txt_view_player2.setText("Player 2:0");
                reset_buttons();
            }
        });


        new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {


                        buttons[i][j].setText("");
                    }
                }


            }
        });
    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals(""))
            return;//if the box already has value then simply return
        if (player1) {
            ((Button) view).setText("X");
            //player1 = false;
        } else {
            ((Button) view).setText("0");
           // player1 = true;
        }

        count++;
        if (checkforwin()) {
            if (player1 == true) {
                player1wins();
                Toast.makeText(this, "Player 1 wins", Toast.LENGTH_SHORT).show();
            } else {
                player2wins();
                Toast.makeText(this, "Player 2 wins", Toast.LENGTH_SHORT).show();
            }

        }
        else if(count==9){
            draw();
        }
        else {
            player1 = !player1;
        }
    }


    public boolean checkforwin() {

        String[][] matrix = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = buttons[i][j].getText().toString();


            }
        }
        for (int i = 0; i < 3; i++) {
            if (matrix[i][0].equals(matrix[i][1])
                    && matrix[i][0].equals(matrix[i][2])
                    && !matrix[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (matrix[0][i].equals(matrix[1][i])
                    && matrix[0][i].equals(matrix[2][i])
                    && !matrix[0][i].equals("")) {
                return true;
            }
        }

        if (matrix[0][0].equals(matrix[1][1])
                && matrix[0][0].equals(matrix[2][2])
                && !matrix[0][0].equals("")) {
            return true;
        }

        if (matrix[0][2].equals(matrix[1][1])
                && matrix[0][2].equals(matrix[2][0])
                && !matrix[0][2].equals("")) {
            return true;
        }

        return false;
    }

    public void player1wins() {
        playerone++;
        txt_view_player1.setText("Player 1 :" + playerone);

    }

    public void player2wins() {
        playertwo++;
        txt_view_player2.setText("Player 2 :" + playertwo);

    }

    public void draw() {
        Toast.makeText(this, "Ooops that was a Draw", Toast.LENGTH_SHORT).show();
        count=0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {


                buttons[i][j].setText("");
            }
        }


    }

    public void reset_buttons() {
        playerone=0;
        playertwo=0;
        count=0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {


                buttons[i][j].setText("");
            }
        }


    }

}