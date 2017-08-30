package br.com.andresguedes.calculadora;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNumber1, edtNumber2;
    private TextView txtResult;
    private Button btnSum, btnSubtract, btnMultiply, btnDivider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNumber1 = (EditText) findViewById(R.id.edtNumber1);
        edtNumber2 = (EditText) findViewById(R.id.edtNumber2);
        txtResult = (TextView) findViewById(R.id.txtResult);
        btnSum = (Button) findViewById(R.id.btnSum);
        btnSubtract = (Button) findViewById(R.id.btnSubtract);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnDivider = (Button) findViewById(R.id.btnDivider);

        btnSum.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivider.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSum:
                txtResult.setText(String.valueOf(Calculator.sum(getEdtNumber1(), getEdtNumber2())));
                break;
            case R.id.btnSubtract:
                txtResult.setText(String.valueOf(Calculator.subtract(getEdtNumber1(), getEdtNumber2())));
                break;
            case R.id.btnMultiply:
                txtResult.setText(String.valueOf(Calculator.multiply(getEdtNumber1(), getEdtNumber2())));
                break;
            case R.id.btnDivider:
                txtResult.setText(String.valueOf(Calculator.divider(getEdtNumber1(), getEdtNumber2())));
                break;
            default:
                break;
        }
    }

    public double getEdtNumber1() {
        return Double.parseDouble(edtNumber1.getText().toString());
    }

    public double getEdtNumber2() {
        return Double.parseDouble(edtNumber2.getText().toString());
    }

}