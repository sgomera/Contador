package com.example.contador;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    //definim fora de tots els mètodes aquestes variables per a que siguin accessibles per tothom
    public int contador;
    TextView textResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicialització de variables quan es crea l'activitat principal
        textResultat = (TextView) findViewById(R.id.contadorPolsacions);
        contador = 0;

        //s'ha de posar a l'escolta la vista corresponent, que seria n_resultado:
        EventoTeclado teclado = new EventoTeclado();
        EditText nReseteo = (EditText)findViewById(R.id.n_reseteo);
        nReseteo.setOnEditorActionListener(teclado);

    }

    public void incrementaContador(View view) {
        contador++;
        //      mostraResultat();
        textResultat.setText("" + contador);

    }

    public void decrementaContador(View view) {
        CheckBox negativos = (CheckBox) (findViewById(R.id.negativos));

        if (negativos.isChecked()) {
            contador--;
            textResultat.setText("" + contador);
        } else if (contador > 0) {
            contador--;
            textResultat.setText("" + contador);
        }
        //       mostraResultat();
    }

    public void ressetejaContador(View view) {
        EditText numero_reset = (EditText)findViewById(R.id.n_reseteo);
        try {
            contador = (int) Integer.parseInt(String.valueOf(numero_reset.getText()));
        } catch (NumberFormatException e) {
            contador = 0;
        }
        numero_reset.setText("");
        textResultat.setText("" + contador);

        //Per a fer que el teclat s'amagui directament quan premem el botó de reset:
        InputMethodManager miTeclado = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        miTeclado.hideSoftInputFromWindow(numero_reset.getWindowToken(),0);

        //      mostraResultat();
    }

/*    public void mostraResultat(){
        TextView textResultat = (TextView)findViewById(R.id.contadorPolsacions);
        textResultat.setText("" + contador);
    }*/

   //classe interna per poder implementar la interfase que volem i per poder accedir a ressetejaContador
    class EventoTeclado implements TextView.OnEditorActionListener{

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

            if (actionId == EditorInfo.IME_ACTION_DONE){
                ressetejaContador(null );
            }
            return false;
        }
    }
}
