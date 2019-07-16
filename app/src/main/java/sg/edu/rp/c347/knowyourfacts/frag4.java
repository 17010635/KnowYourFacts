package sg.edu.rp.c347.knowyourfacts;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class frag4 extends Fragment {

    ImageView iv;
    Button btnChangeColor;
    LinearLayout layout;
    public frag4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_4, container, false);

        iv = view.findViewById(R.id.iv);
        btnChangeColor = view.findViewById(R.id.btnChangeColour);
        layout = view.findViewById(R.id.layout);

        String imageUrl = "https://wtffunfact.com/wp-content/uploads/2019/06/fun-facts-how-rain-looks-like-when-you-are-in-a-plane.png";
        Picasso.with(getContext()).load(imageUrl).into(iv);

        btnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                layout.setBackgroundColor(color);
            }
        });

        return view;
    }

}
