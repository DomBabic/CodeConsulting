package hr.etfos.d1babic.codeconsulting.ui.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * Created by DominikZoran on 28.03.2017..
 */
public class DownloadImageAsync extends AsyncTask<String, Void, Bitmap> {

    ImageView bitmapImage;

    public DownloadImageAsync(ImageView bitmapImage) {
        this.bitmapImage = bitmapImage;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];
        Bitmap icon = null;

        try {
            InputStream stream = new java.net.URL(url).openStream();
            icon = BitmapFactory.decodeStream(stream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return icon;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        bitmapImage.setImageBitmap(bitmap);
    }
}
