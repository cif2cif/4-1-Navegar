package es.upm.dit.adsw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityListSimpleAdapter extends AppCompatActivity {
    private static final String TAG = ActivityListSimpleAdapter.class.getName();
    List<Map<String, Object>> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adapter);
        listItems = rellenaLista();
        ListView miListView = (ListView) findViewById(R.id.listView);

        String [] from = {"nombre","bandera", "superficie"};
        int [] to = {R.id.nombre, R.id.bandera, R.id.superficie};
        final SimpleAdapter adapter = new SimpleAdapter(this, listItems,
                R.layout.fila_layout, from, to);
        miListView.setAdapter(adapter);

        miListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                String url = (String) item.get("url");
                String msg = "Seleccionado " +item.toString();
                Log.d(TAG, msg);

                Intent intent = new Intent(ActivityListSimpleAdapter.this, WebActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }

    private List<Map<String, Object>> rellenaLista() {

        List<Pais> paises = new ArrayList<>();
        paises.add(new Pais("España", R.drawable.espanna, 504645, 34, "https://es.wikipedia.org/wiki/Espa%C3%B1a"));
        paises.add(new Pais("Portugal", R.drawable.portugal, 92391, 351, "https://es.wikipedia.org/wiki/Portugal"));
        paises.add(new Pais("Francia", R.drawable.francia, 675417, 33, "https://es.wikipedia.org/wiki/Francia"));
        paises.add(new Pais("Alemania", R.drawable.alemania, 357168, 49, "https://es.wikipedia.org/wiki/Alemania"));
        paises.add(new Pais("Italia", R.drawable.italia, 301338, 39, "https://es.wikipedia.org/wiki/Italia"));
        paises.add(new Pais("Grecia", R.drawable.grecia, 131990, 30, "https://es.wikipedia.org/wiki/Grecia"));
        paises.add(new Pais("Reino Unido", R.drawable.reinounido, 243610, 44, "https://es.wikipedia.org/wiki/Reino_Unido"));

        List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
        for (Pais pais : paises) {
            Map<String, Object> map = new HashMap<>();
            map.put("nombre", pais.getNombre());
            map.put("bandera", pais.getBandera());
            map.put("superficie", pais.getSuperficie());
            map.put("url", pais.getUrl());
            lista.add(map);
        }
        return lista;

    }


}
