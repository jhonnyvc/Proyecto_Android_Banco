package utp.edu.proybanco;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.IllegalCharsetNameException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragConsulta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragConsulta extends Fragment implements Button.OnClickListener {
    EditText edcuenta,ednuevo;
    TextView resp1,resp2;
    Button bt1,bt2;
    BancoDao obj;
    RadioButton radiob1,radiob2;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragConsulta() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragConsulta.
     */
    // TODO: Rename and change types and number of parameters
    public static fragConsulta newInstance(String param1, String param2) {
        fragConsulta fragment = new fragConsulta();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_frag_consulta, container, false);
        edcuenta=v.findViewById(R.id.edcuenta);
        ednuevo=v.findViewById(R.id.ednuevo);
        bt1=v.findViewById(R.id.btnConsulta);
        resp1=v.findViewById(R.id.txtRes);
        bt1.setOnClickListener(this);

        bt2=v.findViewById(R.id.btnopera);
        resp2=v.findViewById(R.id.txtRes2);
        bt2.setOnClickListener(this);
        radiob1 =v.findViewById(R.id.rb1);
        radiob2 =v.findViewById(R.id.rb2);
        obj=new BancoDao(getContext());
        obj.abreConexion();

        return v;
    }

    @Override
    public void onClick(View v)  {

        if (v.equals(bt1)){
            consulta();
        }else if (v.equals(bt2)){
            opera();
        }
    }
    public void consulta(){
        String edn=edcuenta.getText().toString();
        Banco ep=obj.consulta(edn);
        if(ep==null){
            resp1.setText("No existe numero de Cuenta");
            return;
        }
        String cad="Nombre            : "+ep.getCliente();
        cad+="\nSaldo Actual   : "+ep.getSaldo();
        resp1.setText(cad);
       // ednuevo.setText(null);
      //  resp2.setText(" ");
    }
    public void opera(){

        Banco ep=obj.consulta(edcuenta.getText().toString());
        double mon=Double.parseDouble(ednuevo.getText().toString());
        //RadioButton Deposito
        if (radiob1.isChecked()){

                double suma=ep.getSaldo()+mon;//Realizo la suma correspondiente al deposito

                Banco b=new Banco();
                b.setNrocuenta(Integer.parseInt(edcuenta.getText().toString()));
                b.setSaldo(suma);
                obj.actualizar(b);//Una vez realizado la operacion. Actualizo el nuevo saldo

                String t="--------Deposito Correcto--------";
                t+="\nNombre Cliente        : "+ep.getCliente();
                t+="\nSaldo Anterior           : "+ep.getSaldo();
                t+="\nMonto Depositado  : "+mon;
                t+="\nNuevo Saldo Total   : "+b.getSaldo();
                resp2.setText(t);

                //ednuevo.setText(null);
                resp1.setText(" ");

        //RadioButton Retiro
        }else if(radiob2.isChecked()){

            double resta=ep.getSaldo()-mon;
            if (resta>=0){
                Banco b=new Banco();
                b.setNrocuenta(Integer.parseInt(edcuenta.getText().toString()));
                b.setSaldo(resta);
                obj.actualizar(b);

                String t="----------Retiro Correcto----------";
                t+="\nNombre Cliente      : "+ep.getCliente();
                t+="\nSaldo Anterior         : "+ep.getSaldo();
                t+="\nMonto Retirado       : "+mon;
                t+="\nNuevo Saldo Total  : "+b.getSaldo();

                resp2.setText(t);
                //ednuevo.setText(null);
                resp1.setText(" ");
            }else{

                String t="No se puede realizar el retiro.";
                t+="\nObs :";
                t+="\nSu saldo actual de : "+ep.getSaldo()+" es menor";
                t+="\nque el monto a retirar : "+mon;
                resp2.setText(t);
            }

        }
    }

}
