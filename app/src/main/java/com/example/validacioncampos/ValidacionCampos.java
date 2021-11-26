package com.example.validacioncampos;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class ValidacionCampos extends AppCompatActivity {
    private EditText nombreLugar;
    private Spinner dropdown;
    private EditText palabraClave;
    private EditText etiquetaClave;
    private EditText ubicacion;
    private Button registrar;
    private EditText keyTag;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dropdown=findViewById(R.id.spinner2);
        String[] tipoPatrimonio =new String[]{"Patrimonio inmaterial", "Patrimonio Tangible"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,tipoPatrimonio);
        dropdown.setAdapter(adapter);

        nombreLugar = findViewById(R.id.NombreL);
        palabraClave=findViewById(R.id.palabrasC);
        etiquetaClave=findViewById(R.id.etiquetaC);
        ubicacion=findViewById(R.id.ubicacion);
        registrar=findViewById(R.id.button2);
        keyTag = findViewById(R.id.Et_m2);


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreLugarValor = nombreLugar.getText().toString();
                String dropdownValor = dropdown.getSelectedItem().toString();
                String palabraClaveValor = palabraClave.getText().toString();
                String etiquetaClaveValor = etiquetaClave.getText().toString();
                String ubicacionValor = ubicacion.getText().toString();
                String etiquetaValor = keyTag.getText().toString();

                formRegistros(nombreLugarValor, dropdownValor, palabraClaveValor, etiquetaClaveValor, ubicacionValor);
            }

            private void formEtiqueta(String etiquetaValor) {
                formEtiqueta(etiquetaValor);
            }
        });

    }

    // Funciones validacion
    public boolean campoVacio (String dato){
        return TextUtils.isEmpty(dato);
    }

    //Funcion minimo 3 caracteres

    public boolean minTresCaracteres (String dato){
        int cantidadCaracteres = dato.length();
        if (cantidadCaracteres >= 3){
            return true;
        }
        else {
            return false;
        }
    }



    public boolean formRegistros(String nombreLugar, String tipoPatrimonio, String keyWords, String keyTag, String ubicacion) {
        boolean isValid = true;

        // comprobar campos vacios
        if (campoVacio(nombreLugar) || campoVacio(tipoPatrimonio) || campoVacio(keyWords) || campoVacio(keyTag) || campoVacio(ubicacion)) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Ningun campo puede estar vacio");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            isValid = false;
        }

        //validar campo nombre lugar

        String onlyTextRegex = "^[a-zA-Z]*$";
        if (!nombreLugar.matches(onlyTextRegex)) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("El campo nombre lugar no puede contener numeros ni caracteres especiales");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

            isValid = false;
        }

        //Validar cantidad caracteres

        if (!minTresCaracteres(nombreLugar) || !minTresCaracteres(tipoPatrimonio) || !minTresCaracteres(keyWords) || !minTresCaracteres(keyTag) || !minTresCaracteres(ubicacion)) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Ningun campo debe tener menos de tres caracteres");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            isValid = false;
        }
        return isValid;
    }
    public boolean formEtiqueta(String keyTag) {
        boolean isValid = true;

        if(campoVacio(keyTag)){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Ningun campo puede estar vacio");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            isValid = false;
        }
        String onlyTextRegex = "^[a-zA-Z]*$";
        if (!keyTag.matches(onlyTextRegex)) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("El campo nombre lugar no puede contener numeros ni caracteres especiales");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            isValid=false;
        }
        if (!minTresCaracteres(keyTag)){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Ningun campo debe tener menos de tres caracteres");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    (dialog, which) -> dialog.dismiss());
            alertDialog.show();
            isValid = false;
        }
        return isValid;
    }
}