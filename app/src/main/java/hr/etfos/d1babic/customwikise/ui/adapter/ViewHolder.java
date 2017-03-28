package hr.etfos.d1babic.customwikise.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.etfos.d1babic.customwikise.R;

/**
 * Created by DominikZoran on 28.03.2017..
 */
public class ViewHolder {

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.link)
    TextView link;

    @BindView(R.id.snippet)
    TextView snippet;

    @BindView(R.id.image)
    ImageView image;

    Context context;

    @OnClick
    public void openInBrowser() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(link.getText().toString()));
        context.startActivity(intent);
    }

    public ViewHolder(View view) {
        this.context = view.getContext();
        ButterKnife.bind(this, view);
    }
}
