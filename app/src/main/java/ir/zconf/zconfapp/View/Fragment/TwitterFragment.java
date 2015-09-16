package ir.zconf.zconfapp.View.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import ir.zconf.zconfapp.R;

public class TwitterFragment extends Fragment implements View.OnClickListener {
    TextView gava;
    TextView salon;
    TextView gonbad;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_twitter, container, false);

        gava = (TextView) view.findViewById(R.id.gava);
        salon = (TextView) view.findViewById(R.id.salon);
        gonbad = (TextView) view.findViewById(R.id.gonbad);

        gava.setOnClickListener(this);
        salon.setOnClickListener(this);
        gonbad.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gava:
                try {
                    Uri gmmIntentUri = Uri.parse("geo: 36.714915, 48.519544?q=36.714915, 48.519544(گاوازنگ)").buildUpon().build();
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    getActivity().startActivity(mapIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(
                            getActivity(),
                            "امکان نمایش نقشه نیست",
                            Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.salon:
                try {
                    Uri gmmIntentUri = Uri.parse("geo: 36.664281, 48.516548?q=36.664281, 48.516548(محل برگزاری)").buildUpon().build();
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    getActivity().startActivity(mapIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(
                            getActivity(),
                            "امکان نمایش نقشه نیست",
                            Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.gonbad:
                try {
                    Uri gmmIntentUri = Uri.parse("geo: 36.434253, 48.796048?q=36.434253, 48.796048(گنبد سلطانیه)").buildUpon().build();
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    getActivity().startActivity(mapIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(
                            getActivity(),
                            "امکان نمایش نقشه نیست",
                            Toast.LENGTH_LONG).show();
                }
                break;
        }

    }
}