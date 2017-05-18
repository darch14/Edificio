package com.example.david.edificio;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
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
            if (validarNomenclaturaExistente()){
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
                limpiar();
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

    public boolean validarNomenclaturaExistente(){
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

    public boolean validarNomenclatura(){
        if (cajaNomenclatura.getText().toString().isEmpty()){
            cajaNomenclatura.setError(res.getString(R.string.error_nomenclatura));
            cajaNomenclatura.requestFocus();
            return false;
        }
        return true;
    }

    public void buscar(View v){
        Apartamento a;
        if (validarNomenclatura()){
            a=Datos.buscarApartamentos(getApplicationContext(),cajaNomenclatura.getText().toString());
            if (a!=null){
                cajaPiso.setText(a.getPiso());
                cajaMetros.setText(a.getMetros());
                cajaPrecio.setText(a.getPrecio());

                if (a.getBalcon().equalsIgnoreCase(res.getString(R.string.tiene_balcon)))rBalconSi.setChecked(true);
                else rBalconNo.setChecked(true);

                if (a.getSombra().equalsIgnoreCase(res.getString(R.string.encuentra_sombra)))rSombraSi.setChecked(true);
                else rSombraNo.setChecked(true);
            }
        }
    }

    public void eliminar(View v){
        Apartamento a;
        if (validarNomenclatura()){
            a=Datos.buscarApartamentos(getApplicationContext(),cajaNomenclatura.getText().toString());
            if (a!=null){
                AlertDialog.Builder ventana=new AlertDialog.Builder(this);
                ventana.setTitle(res.getString(R.string.confirmacion));
                ventana.setMessage(res.getString(R.string.mensaje_eliminacion));
                ventana.setPositiveButton(res.getString(R.string.confirmar),new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Apartamento a;
                        a=Datos.buscarApartamentos(getApplicationContext(),cajaNomenclatura.getText().toString());
                        a.eliminar(getApplicationContext());
                        Toast.makeText(getApplicationContext(),res.getString(R.string.eliminado_exitoso),
                                Toast.LENGTH_SHORT).show();
                        limpiar();
                    }
                });

                ventana.setNegativeButton(res.getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cajaNomenclatura.requestFocus();
                    }
                });
                ventana.show();
            }
        }
    }

    public void borrar(View v){
        limpiar();
    }

    public void limpiar(){
        cajaNomenclatura.setText("");
        cajaPiso.setText("");
        cajaMetros.setText("");
        cajaPrecio.setText("");
        rBalconSi.setChecked(true);
        rSombraSi.setChecked(true);
        cajaNomenclatura.requestFocus();
    }

    public void modificar(View v){
        Apartamento a,a2;
        String piso,metros,precio,balcon,sombra;
        if (validarNomenclatura()){
            a=Datos.buscarApartamentos(getApplicationContext(),cajaNomenclatura.getText().toString());
            if (a!=null){
                piso=cajaPiso.getText().toString();
                metros=cajaMetros.getText().toString();
                precio=cajaPrecio.getText().toString();

                if (rBalconSi.isChecked())balcon=res.getString(R.string.tiene_balcon);
                else balcon=res.getString(R.string.no_tiene_balcon);

                if (rSombraSi.isChecked())sombra=res.getString(R.string.encuentra_sombra);
                else sombra=res.getString(R.string.no_encuentra_sombra);

                a2=new Apartamento(a.getFoto(),a.getNomenclatura(),piso,metros,precio,balcon,sombra);
                a2.modificar(getApplicationContext());

                Toast.makeText(getApplicationContext(),res.getString(R.string.modificado_exitoso),
                        Toast.LENGTH_SHORT).show();
                limpiar();
            }
        }
    }

}
