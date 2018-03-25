package in.prabhoo.android.progressbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

public class DeterminateFragment extends Fragment {

    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_determinate, container, false);
        progressBar = view.findViewById(R.id.progress);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new ProgressAsyncTask(progressBar).execute(0);
    }

    private static class ProgressAsyncTask extends AsyncTask <Integer, Integer, Integer> {

        private WeakReference<ProgressBar> progressBar;

        ProgressAsyncTask(ProgressBar progressBar) {
            this.progressBar = new WeakReference<>(progressBar);
        }


        @Override
        protected Integer doInBackground(Integer... integers) {

            for (int i=1; i < 20; i++) {
                try {
                    publishProgress(20+i*4);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Log.e("Task", "Interrupted", e);
                }
            }
            publishProgress(100);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (progressBar.get() != null) {
                progressBar.get().setProgress(values[0]);
            }
        }
    }


}
