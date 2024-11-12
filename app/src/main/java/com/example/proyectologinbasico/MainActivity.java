package com.example.proyectologinbasico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Definimos Atributos
    private EditText usuarioEditText;
    private EditText contrasenaEditText;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enlazo los atributos con los id correspondientes del login
        usuarioEditText = findViewById(R.id.usuario);
        contrasenaEditText = findViewById(R.id.contraseña);
        spinner = findViewById(R.id.spinnerRoles);
        //Creo un arreglo para Spinner
        String[] roles = {"Administrador","Usuario"};
        //Creamos un ArrayAdapter para poblar el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        //Asigno el ArrayManager al Spinner
        spinner.setAdapter(adapter);

    }
    //Metodo para ingresar los datos del usuario e ir a otra vista
    public void onClickAcceder(View view){
        //Declaro las variables para obtener los datos del usuario
        String user = usuarioEditText.getText().toString().trim();
        String pass = contrasenaEditText.getText().toString().trim();
        String rol = spinner.getSelectedItem().toString();
        //Si esta vacio el usuario saltara un Toast.
        if (user.isEmpty()){
            Toast.makeText(this,"Ingrese un Usuario prro >:v", Toast.LENGTH_SHORT).show();
            return;
        }
        //Si esta vacia la contraseña saltara un Toast.
        if(pass.isEmpty()){
            Toast.makeText(this,"Ingrese una Contraseña, bruh..", Toast.LENGTH_SHORT).show();
            return;
        }
        //Verifico las credenciales del usuario.
        if (user.equals("juan") && pass.equals("2005") && rol.equals("Usuario")) {
            //Inicio una actividad que se realizara en la clase Usuario
            Intent intent = new Intent(this, Usuario.class);
            startActivity(intent);
        }
        //verificamos si es un administrador.
        else if (user.equals("pepe") && pass.equals("1974") && rol.equals("Administrador")) {
            //inicio una actividad que se realizara en la clase Admin.
            Intent intent = new Intent(this, Admin.class);
            startActivity(intent);
        }
        //Si nada es valido, soltara una alerta.
        else {
            Toast.makeText(this, "Who are you?", Toast.LENGTH_SHORT).show();
        }
    }

}