package utp.edu.proybanco;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragAdicion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragAdicion extends Fragment implements Button.OnClickListener {
    BancoDao obj;
    EditText edcli, edsaldo;
    Button bt1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragAdicion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragAdicion.
     */
    // TODO: Rename and change types and number of parameters
    public static fragAdicion newInstance(String param1, String param2) {
        fragAdicion fragment = new fragAdicion();
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
        View v= inflater.inflate(R.layout.fragment_frag_adicion, container, false);
        edcli=v.findViewById(R.id.edcli);
        edsaldo=v.findViewById(R.id.edsaldo);
        bt1=v.findViewById(R.id.btnadicion);
        bt1.setOnClickListener(this);
        obj=new BancoDao(getContext());
        obj.abreConexion();
        return v;

    }

    @Override
    public void onClick(View v) {
    Banco b=new Banco();
    b.setCliente(edcli.getText().toString());
    b.setSaldo(Double.parseDouble(edsaldo.getText().toString()));
    int res=obj.adicion(b);
        Toast.makeText(getContext(),"Usuario Registrado : "+res, Toast.LENGTH_SHORT).show();
    }
}