package com.example.automata_proj;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private EditText emailEditText;
    private TextView outputMessage;
    private String[] alphabet;
    private String[] states;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize UI components
        emailEditText = findViewById(R.id.boxemail);
        outputMessage = findViewById(R.id.output_message);
        Button signUpButton = findViewById(R.id.signupbutton);

        // Initialize alphabet and states
        alphabet = new String[]{
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "-", "_", ".", "@",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"
        };

        states = new String[]{"q1", "q2", "q3", "q4", "q5", "q6"};

        // Set up a TextWatcher for real-time validation
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Not needed for this case
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Real-time validation as the user types
                String email = charSequence.toString();
                if (email.isEmpty()) {
                    // If the input is empty, hide the output message
                    outputMessage.setVisibility(View.GONE);
                } else if (isValidEmail(email)) {
                    // Email is valid, provide feedback
                    outputMessage.setText("Valid Email");
                    outputMessage.setTextColor(getResources().getColor(R.color.validColor));
                    outputMessage.setVisibility(View.VISIBLE);
                } else {
                    // Email is invalid, provide feedback
                    outputMessage.setText("Invalid Email");
                    outputMessage.setTextColor(getResources().getColor(R.color.invalidColor));
                    outputMessage.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not needed for this case
            }
        });
    }

    // Function to validate email using the provided DFA logic
    private boolean isValidEmail(String email) {
        AFDBuilder afdBuilder = new AFDBuilder(alphabet, states);
        AFD afd = afdBuilder.build();

        AFDNode currentState = afd.getInitialState();

        for (char c : email.toCharArray()) {
            String charString = String.valueOf(c);

            // Check if the character is in the alphabet
            if (afd.inAlphabet(charString)) {
                AFDTransition validTransition = null;

                // Iterate through transitions for the current state
                for (AFDTransition transition : currentState.getTransitions()) {
                    // Check if the transition has the current character
                    if (transition.hasValue(charString)) {
                        validTransition = transition;
                        break;
                    }
                }

                // If no valid transition is found, the email is invalid
                if (validTransition == null) {
                    return false;
                }

                currentState = validTransition.getToNode();
            } else {
                // Character is not in the alphabet, so the email is invalid
                return false;
            }
        }

        // Check if the final state is an accepting state
        return currentState.isFinal();
    }
}
