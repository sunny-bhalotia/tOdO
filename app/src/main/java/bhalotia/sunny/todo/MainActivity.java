package bhalotia.sunny.todo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> tasks;
    ListView listview;
    Button button;
    EditText editText;
    ArrayAdapter<String> listadapters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        read();
        button=(Button)findViewById(R.id.button);
        editText=(EditText)findViewById(R.id.editText);
        listview=(ListView)findViewById(R.id.listView);
        listadapters=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tasks);
        listview.setAdapter(listadapters);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                listadapters.add(s);
                editText.setText("");
                write();
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                tasks.remove(position);
                listadapters.notifyDataSetChanged();

                Context context = getApplicationContext();
                CharSequence text = "Item Deleted!";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                write();
                return true;
            }
        });


}

    void read()
    {
        File reader=getFilesDir();
        File f=new File(reader,"savedfile.txt");
        try
        {
            tasks=new ArrayList<String>(FileUtils.readLines(f));
        }
        catch (Exception e)
        {
            tasks=new ArrayList<>();
        }
    }

    void write()
    {
        File writer =getFilesDir();
        File f=new File(writer,"savedfile.txt");
        try{
            FileUtils.writeLines(f, tasks);
        }
    catch (Exception e)
    {

    }
    }
}
