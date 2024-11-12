package com.example.proyectologinbasico;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//Librerias de SQLite
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Admin extends AppCompatActivity {

    //Reclarar variables.
    Spinner spSpinner;
    String[] genero = new String[]{"SandBox", "Shooters", "RPG", "Fighters", "Carreras", "Aventura", "Puzzle", "Ritmo"};
    EditText edtID, edtNom, edtCS;
    ListView lista;

    //Metodo onCreate, llamada cuando la actividad se crea por primera vez.
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        //Llama al metodo onCreate de la clase base para inicializar la actividad correctamente.
        super.onCreate(saveInstanceState);
        //Establece el diseño de la actividad con el archivo XML correspondiente.
        setContentView(R.layout.activity_admin);

        //Defino los campos del formulario
        edtID = (EditText) findViewById(R.id.edtID);
        edtNom = (EditText) findViewById(R.id.edtNom);
        edtCS = (EditText) findViewById(R.id.edtCS);
        spSpinner = (Spinner) findViewById(R.id.spSpinner);
        lista = (ListView) findViewById(R.id.lstLista);

        //Poblamos Spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genero);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSpinner.setAdapter(spinnerAdapter);
        CargarLista();
    }

    public void CargarLista(){
        DataHelper dh = new DataHelper(this, "juegos.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        Cursor c = bd.rawQuery("Select id, nombre, plataforma, genero from juegos", null);
        String[] arr = new String[c.getCount()];
        if(c.moveToFirst() == true){
            int i = 0;
            do{
                String linea = "||" + c.getInt(0) + "||" + c.getString(1) + "||" + c.getString(2) + "||" + c.getString(3) + "||";
                arr[i] = linea;
                i++;
            } while (c.moveToNext() == true);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_expandable_list_item_1, arr);
            lista.setAdapter(adapter);
            bd.close();
        }
    }


    public void onClickAgregar (View view) {
        DataHelper dh = new DataHelper(this, "juegos.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        ContentValues reg = new ContentValues();
        reg.put("id", edtID.getText().toString());
        reg.put("nombre", edtNom.getText().toString());
        reg.put("plataforma", edtCS.getText().toString());
        reg.put("genero", spSpinner.getSelectedItem().toString());
        long resp = bd.insert("juegos", null, reg);
        bd.close();
        if (resp==-1){
            Toast.makeText(this, "No se pudo ingresar el Videojuego :(", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Videojuego Agregado papu :v", Toast.LENGTH_LONG).show();
        }
        CargarLista();
        limpiar();
    }

    //Metodo para limpiar los campos de texto.
    public void limpiar(){
        edtID.setText("");
        edtNom.setText("");
        edtCS.setText("");
    }

    //Metodo para modificar un campo.
    public void onClickModificar(View view){
        //Conexion a la BDD para manipular los registros.
        DataHelper dh = new DataHelper(this, "juegos.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        ContentValues reg = new ContentValues();
        //Envio los datos a modificar.
        reg.put("id", edtID.getText().toString());
        reg.put("nombre", edtNom.getText().toString());
        reg.put("plataforma", edtCS.getText().toString());
        reg.put("genero", spSpinner.getSelectedItem().toString());
        //Actualizo el registro de la BDD por el ID.
        long respuesta = bd.update("juegos", reg, "id=?", new String[]{edtID.getText().toString()});
        bd.close();
        //Envio respuesta al Usuario.
        if (respuesta == -1){
            Toast.makeText(this, "Dato no modificado :(", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Dato Modificado :P", Toast.LENGTH_LONG).show();
        }
        limpiar();
        CargarLista();
    }

    //Metodo para Eliminar un registro.
    public void onClickEliminar(View view){
        //Conecto la bdd para eliminar un registro en la tabla de Juegos.
        DataHelper dh = new DataHelper(this, "juegos.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        String IDEliminar = edtID.getText().toString();
        //Query para eliminar el registro.
        long respuesta = bd.delete("juegos", "id=" + IDEliminar, null);
        bd.close();
        //Verificamos que el registro se elimine correctamente.
        if (respuesta == -1){
            Toast.makeText(this, "Dato no eliminado >:(", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Dato Eliminado :D", Toast.LENGTH_LONG).show();
        }
        limpiar();
        CargarLista();
    }




}
