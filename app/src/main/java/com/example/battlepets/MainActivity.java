package com.example.battlepets;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private PetDatabaseHelper dbHelper;
    private EditText editName, editType;
    private Button btnAddPet, btnBattle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Add the DB Helper
        dbHelper = new PetDatabaseHelper(this);

        //Bind to the layout
        editName = findViewById(R.id.editName);
        editType = findViewById(R.id.editType);
        btnAddPet = findViewById(R.id.btnAddPet);
        btnBattle = findViewById(R.id.btnBattle);

        //Add Event Listener
        btnAddPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String petAdded = "PET added!";
                String noPetAdded = "No PET added!! Type in some stuff!";
                String name = editName.getText().toString();
                String type = editType.getText().toString();

                if(!name.isEmpty() && !type.isEmpty()){
                    dbHelper.addPet(name, type);
                    new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this).
                            setTitle("Pet Addition!").
                            setMessage(petAdded).
                            setPositiveButton("Okay", null).
                            show();
                    editName.setText("");
                    editType.setText("");
                }else{
                    new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this).
                            setTitle("Pet Addition").
                            setMessage(noPetAdded).
                            setPositiveButton("Okay", null).
                            show();
                }

            }
        });

        //Add Event Listener
        btnBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result= ""; //blank string
                List<Pet> pets = dbHelper.getAllPets();

                if(pets.size() >= 2){
                    Random random = new Random();
                    Pet pet1 = pets.get(random.nextInt(pets.size()));
                    Pet pet2 = pets.get(random.nextInt(pets.size()));

                    while(pet1.getId() == pet2.getId()){
                        pet2 = pets.get(random.nextInt(pets.size()));
                    }

                    if(pet1.getStrength() > pet2.getStrength()){
                        result = pet1.getName() + " wins against " +
                                pet2.getName() + "!!";
                    }
                    else if(pet1.getStrength() < pet2.getStrength()){
                        result = pet2.getName() + " wins against " +
                                pet1.getName() + "!!";
                    }
                    else{
                        result = "IT IS A TIE!! BATTLE AGAIN";
                    }

                    //Display Battle Results
                    new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this).
                            setTitle("Battle Results").
                            setMessage(result).
                            setPositiveButton("Okay", null).
                            show();
                    btnBattle.startAnimation(android.view.animation.AnimationUtils.
                            loadAnimation(MainActivity.this, R.anim.shake));
                }
                else{
                    new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this).
                            setTitle("Battle Results").
                            setMessage(result).
                            setPositiveButton("Okay", null).
                            show();
                }
            }
        });
    }
}