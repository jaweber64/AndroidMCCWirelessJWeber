package webersolutions.mccwireless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Offer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        final RadioButton noPhone = (RadioButton) findViewById(R.id.rbtnPhone1);
        final RadioButton basicPhone = (RadioButton) findViewById(R.id.rbtnPhone2);
        final RadioButton allStarPhone = (RadioButton) findViewById(R.id.rbtnPhone3);
        final RadioButton noTV = (RadioButton) findViewById(R.id.rbtnTV1);
        final RadioButton basicTV = (RadioButton) findViewById(R.id.rbtnTV2);
        final RadioButton allStarTV = (RadioButton) findViewById(R.id.rbtnTV3);
        final RadioButton noInternet = (RadioButton) findViewById(R.id.rbtnInternet1);
        final RadioButton regInternet = (RadioButton) findViewById(R.id.rbtnInternet2);
        final RadioButton hsInternet = (RadioButton) findViewById(R.id.rbtnInternet3);

        final TextView result = (TextView) findViewById(R.id.txtResult);
        Button estimate = (Button) findViewById(R.id.btnEst);

        estimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double pkgCost = 0;
                double discount = 1.0;
                double phoneTotal = 0, economyPhoneCost = 25, allStarPhoneCost = 50;
                double tvTotal = 0, basicTVCost = 50, allStarTVCost = 85;
                double internetTotal = 0, regIntCost = 45, hsIntCost = 70;

                DecimalFormat money = new DecimalFormat("$###.##");         // format for result string

                if ((noPhone.isChecked() && noTV.isChecked()) ||            // if only 1 chosen 0%
                        (noPhone.isChecked() && noInternet.isChecked()) ||  // if only 2 chosen 10%
                        (noTV.isChecked() && noInternet.isChecked())) {     // if all 3 chosen 15%
                    discount = 1.0;
                    Toast.makeText(Offer.this, "No Discount", Toast.LENGTH_LONG).show();
                } else {
                    if (!(noPhone.isChecked()) && !(noTV.isChecked()) && !(noInternet.isChecked())) {
                        discount = .85;
                        Toast.makeText(Offer.this, "Qualifies for 15% discount!", Toast.LENGTH_LONG).show();
                    } else {
                        discount = .90;
                        Toast.makeText(Offer.this, "Qualifies for 10% discount!", Toast.LENGTH_LONG).show();
                    }
                }

                if (noPhone.isChecked()) {
                    phoneTotal += 0;
                } else if (basicPhone.isChecked()) {
                    phoneTotal += economyPhoneCost;
                } else if (allStarPhone.isChecked()) {
                    phoneTotal += allStarPhoneCost;
                }

                if (noTV.isChecked()) {
                    tvTotal += 0;
                } else if (basicTV.isChecked()) {
                    tvTotal += basicTVCost;
                } else if (allStarTV.isChecked()) {
                    tvTotal += allStarTVCost;
                }

                if (noInternet.isChecked()) {
                    internetTotal += 0;
                } else if (regInternet.isChecked()) {
                    internetTotal += regIntCost;
                } else if (hsInternet.isChecked()) {
                    internetTotal += hsIntCost;
                }

                pkgCost = (phoneTotal + tvTotal + internetTotal) * discount;
                result.setText(money.format(pkgCost) + " per month");
            }


            });
        }
    }

