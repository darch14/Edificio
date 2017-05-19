package com.example.david.edificio;

import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Reportes extends AppCompatActivity {
    private ListView ls;
    private String[] opc;
    private ArrayAdapter adapter;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        res=this.getResources();
        ls=(ListView)findViewById(R.id.lsReportes);
        opc=getResources().getStringArray(R.array.opciones_reportes);
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,opc);
        ls.setAdapter(adapter);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        new AlertDialog.Builder(Reportes.this).setMessage(sombraBalcon()).setCancelable(true).show();
                        break;
                    case 1:
                        new AlertDialog.Builder(Reportes.this).setMessage(masCaro()).setCancelable(true).show();
                        break;
                    case 2:
                        new AlertDialog.Builder(Reportes.this).setMessage(mayorTamaño()).setCancelable(true).show();
                        break;
                    case 3:
                        new AlertDialog.Builder(Reportes.this).setMessage(promedio()).setCancelable(true).show();
                        break;
                }
            }
        });
    }

    public String sombraBalcon(){
        ArrayList<Apartamento> a=Datos.traerApartamentos(getApplicationContext());
        String mensaje,sombra,balcon,b,s;
        int contb=0,conts=0,cont=0;
        for (int i=0;i<a.size();i++){
            b=res.getString(R.string.tiene_balcon);
            s=res.getString(R.string.encuentra_sombra);
            if (a.get(i).getBalcon().equals(b)){
                contb=contb+1;
            }
            if (a.get(i).getSombra().equals(s)){
                conts=conts+1;
            }
            if (a.get(i).getBalcon().equals(b) && a.get(i).getSombra().equals(s)){
                cont=cont+1;
            }
        }
        balcon=res.getString(R.string.con_balcon)+" "+String.valueOf(contb);
        sombra=res.getString(R.string.con_sombra)+" "+String.valueOf(conts);
        mensaje=balcon+"\n"+sombra+"\n"+res.getString(R.string.con_balcon_sombra)+" "+String.valueOf(cont);
        return mensaje;
    }

    public String masCaro(){
        String mensaje,piso;
        int precio1,precio2;
        ArrayList<Apartamento> a=Datos.traerApartamentos(getApplicationContext());
        precio1=Integer.parseInt(a.get(0).getPrecio());
        piso=a.get(0).getPiso();
        for (int i=1;i<a.size();i++){
            precio2=Integer.parseInt(a.get(i).getPrecio());
            if (precio1<precio2){
                piso=a.get(i).getPiso();
            }
        }
        mensaje=res.getString(R.string.apartamento_caro)+" "+piso;
        return mensaje;
    }

    public String mayorTamaño(){
        String mensaje,nom;
        int metros,metros2;
        ArrayList<Apartamento> a=Datos.traerApartamentos(getApplicationContext());
        metros=Integer.parseInt(a.get(0).getMetros());
        nom=a.get(0).getNomenclatura();
        for (int i=1;i<a.size();i++){
            metros2=Integer.parseInt(a.get(i).getMetros());
            if (metros<metros2){
                metros=metros2;
                nom=a.get(i).getNomenclatura();
            }
        }
        mensaje=res.getString(R.string.nomenclatura)+": "+nom+"\n"
                +res.getString(R.string.metros_cuadrados)+": "+String.valueOf(metros);
        return mensaje;
    }

    public String promedio(){
        String mensaje;
        int metros=0,promedio;
        ArrayList<Apartamento> a=Datos.traerApartamentos(getApplicationContext());
        for (int i=0;i<a.size();i++){
            metros=metros+Integer.parseInt(a.get(i).getMetros());
        }
        promedio=metros/a.size();
        mensaje=res.getString(R.string.promedio)+": "+String.valueOf(promedio);
        return mensaje;
    }

}
