package com.example.david.edificio;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by david on 17/05/2017.
 */

public class AdaptadorApartamentos extends BaseAdapter{
    private Context contexto;
    private ArrayList<Apartamento> apartamentos;
    private String[] tirulos;

    public AdaptadorApartamentos(Context contexto, ArrayList<Apartamento> apartamentos, String[] tirulos) {
        this.contexto = contexto;
        this.apartamentos = apartamentos;
        this.tirulos = tirulos;
    }

    @Override
    public int getCount() {
        return apartamentos.size();
    }

    @Override
    public Object getItem(int position) {
        return apartamentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Declarar las variables
        TextView cajaNomenclatura,cajaPiso,cajaMetros,cajaPrecio,cajaBalcon,cajaSombras;
        ImageView foto;
        LayoutInflater inflater;
        View itemView;

        //uso el inflater
        inflater=(LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemView=inflater.inflate(R.layout.item_apartamento,null);

        //Capturar los objetos
        foto=(ImageView)itemView.findViewById(R.id.imgFoto);
        cajaNomenclatura=(TextView)itemView.findViewById(R.id.txtNomenclaturaP);
        cajaPiso=(TextView)itemView.findViewById(R.id.txtPisoP);
        cajaMetros=(TextView)itemView.findViewById(R.id.txtMetrosP);
        cajaPrecio=(TextView)itemView.findViewById(R.id.txtPrecioP);
        cajaBalcon=(TextView)itemView.findViewById(R.id.txtBalconP);
        cajaSombras=(TextView)itemView.findViewById(R.id.txtSombraP);

        //Pasar la informacion
        foto.setImageResource(Integer.parseInt(apartamentos.get(position).getFoto()));
        cajaNomenclatura.setText(tirulos[0]+": "+apartamentos.get(position).getNomenclatura());
        cajaPiso.setText(tirulos[1]+": "+apartamentos.get(position).getPiso());
        cajaMetros.setText(tirulos[2]+": "+apartamentos.get(position).getMetros());
        cajaPrecio.setText(tirulos[3]+": $"+apartamentos.get(position).getPrecio());
        cajaBalcon.setText(apartamentos.get(position).getBalcon());
        cajaSombras.setText(apartamentos.get(position).getSombra());

        return itemView;
    }

}
