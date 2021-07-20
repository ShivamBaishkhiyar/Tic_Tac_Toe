package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

/*
     Player Representation
     0 - Player with X
     1 - Player with O

     State Meaning
     0 - X
     1 - O
     2 - Null
*/

    boolean gameActive = true;
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
                           {0,3,6}, {1,4,7}, {2,5,8},
                           {0,4,8}, {2,4,6} };

    public void onTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            resetGame(view);
        }
        if(gameState[tappedImage]==2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.textView2);
                status.setText("O's Turn");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.textView2);
                status.setText("X's Turn");
            }
            img.animate().translationYBy(1000f).setDuration(250);
        }

//      Check if any player has won
        for(int[] winPosition : winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]]!=2){
                //Anyone player won the game
                gameActive=false;
                String winnerStr;
                if(gameState[winPosition[0]]==0){
                    winnerStr = "X has won";
                }
                else{
                    winnerStr = "O has won";
                }
                TextView status = findViewById(R.id.textView2);
                status.setText(winnerStr);
            }
        }
        boolean emptySquare = false;
        for (int squareState : gameState) {
            if (squareState == 2) {
                emptySquare = true;
                break;
            }
        }
        if (!emptySquare && gameActive) {
            // Game is a draw
            gameActive = false;
            String winnerStr;
            winnerStr = "No one won";
            TextView status = findViewById(R.id.textView2);
            status.setText(winnerStr);
        }

    }

    public void resetGame(View view){
        gameActive = true;
        activePlayer = 0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);

        TextView status = findViewById(R.id.textView2);
        status.setText("Tap to Play Again");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}