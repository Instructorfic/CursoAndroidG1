package com.fic.cursoandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {

    private ImageButton btnPopUp;
    private ActionMode actionMode;
    private Button btnAlert;
    private EditText etAlertMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*Uri webSiteURL = getIntent().getData();

        Toast.makeText(getApplicationContext(),webSiteURL.toString(),Toast.LENGTH_LONG).show();*/

        /*String getStringMessage = getIntent().getStringExtra("message");
        Toast.makeText(getApplicationContext(),getStringMessage,Toast.LENGTH_LONG).show();*/

        Bundle extras = getIntent().getExtras();

        String message = extras.getString("message","Error en el mensaje");
        int error = extras.getInt("error",-1);
        String name = extras.getString("name","No existe un nombre de usuario");

        TextView txtMessage = (TextView) findViewById(R.id.txtMessage);
        TextView txtError = (TextView) findViewById(R.id.txtError);
        TextView txtName = findViewById(R.id.txtName);

        /*Menú para contexto*/
        TextView txtMenuContext = findViewById(R.id.txtMenuContext);

        //registerForContextMenu(txtMenuContext);

        txtMenuContext.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(actionMode != null) return false;
                actionMode = MainActivity2.this.startActionMode(actionModeCallback);
                txtMenuContext.setSelected(true);
                return true;
            }
        });


        btnPopUp = findViewById(R.id.btnPopUp);


        btnPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity2.this,btnPopUp);
                popup.getMenuInflater().inflate(R.menu.menu_popup, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });
                popup.show();
            }
        });

        btnAlert = findViewById(R.id.btnAlert);
        etAlertMessage = findViewById(R.id.etAlertMessage);
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = etAlertMessage.getText().toString();
                if(validateEditText(etAlertMessage)){
                    showAlert(message);
                }

            }
        });

        txtMessage.setText(message);
        txtError.setText(String.valueOf(error));
        txtName.setText(name);

    }

    public void showPage(MenuItem item){
        Uri uri = Uri.parse("http://www.google.com");
        Intent showWebPageIntent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(showWebPageIntent);
    }

    public void dialPhoneNumber(MenuItem item){
        Uri dialUri = Uri.parse("tel:6677000000");
        Intent dialIntent = new Intent(Intent.ACTION_DIAL,dialUri);
        startActivity(dialIntent);
    }

    public void showSettings(MenuItem item){
        Intent settingsIntent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(settingsIntent);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.mnuOption1:
                showPage(item);
                return true;
            case R.id.mnuOption2:
                dialPhoneNumber(item);
                return true;
            case R.id.mnuOption3:
                showSettings(item);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void onCreateContextMenu(ContextMenu menu,View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context,menu);
    }

    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.mnuContextEdit:
                Toast.makeText(getApplicationContext(),"Editar",Toast.LENGTH_LONG).show();
                return true;
            case R.id.mnuContextShare:
                Toast.makeText(getApplicationContext(),"Compartir",Toast.LENGTH_LONG).show();
                return true;
            default :
                    return super.onContextItemSelected(item);
        }
    }

    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_context,menu);
            mode.setTitle("Seleccione una opción");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.mnuContextEdit:
                    Toast.makeText(getApplicationContext(),"Editar Action Mode",Toast.LENGTH_LONG).show();
                    mode.finish();
                    return true;
                case R.id.mnuContextShare:
                    Toast.makeText(getApplicationContext(),"Compartir Action Mode",Toast.LENGTH_LONG).show();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };

    public void showAlert(String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity2.this);
        alertDialog.setTitle(R.string.alert_title);
        alertDialog.setMessage(message);
        alertDialog.show();
    }

    public boolean validateEditText(EditText editText){
        boolean valid = true;
        if(editText.length() == 0){
            editText.requestFocus();
            editText.setError(getText(R.string.required_field));
            valid = false;
        }
        return valid;
    }
}