package utp.edu.proybanco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adap1 extends RecyclerView.Adapter<Adap1.MyHolder> {
  List<Banco> lista;
  Context contexto;

    public Adap1(List<Banco> lista, Context contexto) {
        this.lista = lista;
        this.contexto = contexto;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //va a llamar a la vista que contiene los controles
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vista_listar, viewGroup,false);

        return new MyHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int pos) {
     //enlazar  las variables del MyHolder con los datos de la lista
    final Banco lb=lista.get(pos);
     myHolder.tnro.setText(""+lb.getNrocuenta());
     myHolder.tcli.setText(""+lb.getCliente());
     myHolder.tsaldo.setText(""+lb.getSaldo());

    }
    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
     TextView tnro, tcli,tsaldo;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            //relaciona las variables con los controles
            tnro=itemView.findViewById(R.id.txtnro);
            tcli=itemView.findViewById(R.id.txtcli);
            tsaldo=itemView.findViewById(R.id.txtsaldo);

        }
    }
}
