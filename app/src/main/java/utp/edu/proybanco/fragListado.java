package utp.edu.proybanco;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragListado#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragListado extends Fragment {
    RecyclerView recy;
    BancoDao obj;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragListado() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragListado.
     */
    // TODO: Rename and change types and number of parameters
    public static fragListado newInstance(String param1, String param2) {
        fragListado fragment = new fragListado();
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
        View v= inflater.inflate(R.layout.fragment_frag_listado, container, false);
        recy=v.findViewById(R.id.recy1);
        obj=new BancoDao(getContext());
        obj.abreConexion();
        Adap1 d1=new Adap1(obj.listado(),getContext());
        recy.setLayoutManager(new LinearLayoutManager(getContext()));
        recy.setAdapter(d1);
        return v;
    }
}