package utp.edu.proybanco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.*;


public class BancoDao {
    SQLiteDatabase conexion;
    SqliteHelp obj;

    public BancoDao(Context contexto) {
        obj=new SqliteHelp(contexto);

    }
    public void abreConexion(){
        conexion=obj.getWritableDatabase();
    }

    public int adicion(Banco ep){
        ContentValues ct=new ContentValues();
        ct.put("cliente",ep.getCliente());
        ct.put("saldo",ep.getSaldo());
        int res=(int)conexion.insert("banco",null,ct);
        return res;
    }
    public List<Banco> listado(){
        Cursor c=null;
        List<Banco> lista=new ArrayList<>();
        c=conexion.rawQuery("select * from banco",null);
        while(c.moveToNext()){
            Banco p=new Banco();
            p.setNrocuenta(c.getInt(0));
            p.setCliente(c.getString(1));
            p.setSaldo(c.getDouble(2));

            lista.add(p);
        }
        return lista;
    }
    public Banco consulta(String id){
        Cursor c=null;
        String pa[]={id};
        Banco p=null;
        c=conexion.rawQuery("select * from banco where id=?",pa);
        while(c.moveToNext()){
            p=new Banco();
            p.setNrocuenta(c.getInt(0));
            p.setCliente(c.getString(1));
            p.setSaldo(c.getDouble(2));

        }
        return p;
    }

    public void actualizar(Banco b){
        String sql="update banco set saldo=? where id=?";
        String v[]={""+b.getSaldo(),""+b.getNrocuenta()};
        conexion.execSQL(sql,v);
    }
    //Otra forma de actualizar
    public void actualizar2(Banco ba){
        ContentValues cv=new ContentValues();
        cv.put("Saldo",ba.getSaldo());
        conexion.update("banco",cv,"id="+ba.getNrocuenta(),null);
    }

    //Aun no utilizo este metodo solo lo deje por si lo necesito luego en otro ejercicio
    //Dos formas para hacer la consulta DELETE
    //Primera
    public void eliminar(Banco b){
        String sql="delete from banco where id=?";
        String v[]={""+b.getNrocuenta()};
        conexion.execSQL(sql,v);
    }
    //Segunda
    public void eliminar2(Banco ba){
        conexion.delete("banco","id="+ba.getNrocuenta(),null);
    }

}
