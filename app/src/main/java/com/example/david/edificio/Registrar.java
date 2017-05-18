package com.example.david.edificio;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Registrar extends AppCompatActivity {
    private EditText cajaNomenclatura,cajaPiso,cajaMetros,cajaPrecio;
    private RadioButton rBalconSi,rBalconNo,rSombraSi,rSombraNo;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        res=this.getResources();
        cajaNomenclatura=(EditText)findViewById(R.id.txtNomenclatura);
        cajaPiso=(EditText)findViewById(R.id.txtPiso);
        cajaMetros=(EditText)findViewById(R.id.txtMetrosCuadrados);
        cajaPrecio=(EditText)findViewById(R.id.txtPrecio);
        rBalconSi=(RadioButton)findViewById(R.id.rBalconSi);
        rBalconNo=(RadioButton)findViewById(R.id.rBalconNo);
        rSombraSi=(RadioButton)findViewById(R.id.rSombraSi);
        rSombraNo=(RadioButton)findViewById(R.id.rSombraNo);
    }

    public void guardar(View v){
        if (validar()){
            if (validarNomenclatura()){
                String foto,nomenclatura,piso,metros,precio,balcon,sombra;
                Apartamento apartamento;
                foto=String.valueOf(fotoAleatoria());
                nomenclatura=cajaNomenclatura.getText().toString();
                piso=cajaPiso.getText().toString();
                metros=cajaMetros.getText().toString();
                precio=cajaPrecio.getText().toString();
                if (rBalconSi.isChecked())balcon=res.getString(R.string.tiene_balcon);
                else balcon=res.getString(R.string.no_tiene_balcon);
                if (rSombraSi.isChecked())sombra=res.getString(R.string.encuentra_sombra);
                else sombra=res.getString(R.string.no_encuentra_sombra);

                apartamento=new Apartamento(foto,nomenclatura,piso,metros,precio,balcon,sombra);
                apartamento.guardar(getApplicationContext());
                Toast.makeText(getApplicationContext(), res.getString(R.string.guardado_exitoso),Toast.LENGTH_SHORT).show();
            }
        }

    }

    public boolean validar(){
        if (cajaNomenclatura.getText().toString().isEmpty()){
            cajaNomenclatura.setError(res.getString(R.string.error_nomenclatura));
            cajaNomenclatura.requestFocus();
            return false;
        }
        if (cajaPiso.getText().toString().isEmpty()){
            cajaPiso.setError(res.getString(R.string.error_piso));
            cajaPiso.requestFocus();
            return false;
        }
        if (cajaMetros.getText().toString().isEmpty()){
            cajaMetros.setError(res.getString(R.string.error_metros));
            cajaMetros.requestFocus();
            return false;
        }
        if (cajaPrecio.getText().toString().isEmpty()){
            cajaPrecio.setError(res.getString(R.string.error_precio));
            cajaPrecio.requestFocus();
            return false;
        }
        return true;
    }

    public int fotoAleatoria(){
        int foto[]={R.drawable.imagren1,R.drawable.imagen2,R.drawable.imagen3};
        int numero=(int)(Math.random()*3);
        return foto[numero];
    }

    public boolean validarNomenclatura(){
        ArrayList<Apartamento> apartamentos= Datos.traerApartamentos(getApplicationContext());
        for (int i=0;i<apartamentos.size();i++){
            if (apartamentos.get(i).getNomenclatura().equalsIgnoreCase(cajaNomenclatura.getText().toString())){
                cajaNomenclatura.setError(res.getString(R.string.error_nomenclatura_igual));
                cajaNomenclatura.requestFocus();
                return false;
            }
        }
        return true;
    }

}
