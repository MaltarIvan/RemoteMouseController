package hr.apps.maltar.remotemousecontroller.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import hr.apps.maltar.remotemousecontroller.MouseActivity;
import hr.apps.maltar.remotemousecontroller.R;
import hr.apps.maltar.remotemousecontroller.action.Action;
import hr.apps.maltar.remotemousecontroller.params.IntentKey;
import hr.apps.maltar.remotemousecontroller.services.SendDataIntentService;

/**
 * Created by Maltar on 24.11.2017..
 */

public class VolumeFragment extends Fragment{
    private View view;
    private Button volumeUpButton;
    private Button volumeDownButton;
    private Button exitButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_volume, container, false);
        setViews();
        return view;
    }

    private void setViews() {
        volumeDownButton = (Button) view.findViewById(R.id.volume_down_button);
        volumeUpButton = (Button) view.findViewById(R.id.volume_up_button);
        exitButton = (Button) view.findViewById(R.id.remove_volume_fragment);

        volumeUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MouseActivity) getActivity()).resetVolumeTimer();
                Action action = Action.makeVolumeControlAction("volume_up");
                Intent intent = new Intent(getActivity().getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                getActivity().startService(intent);
            }
        });
        volumeDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MouseActivity) getActivity()).resetVolumeTimer();
                Action action = Action.makeVolumeControlAction("volume_down");
                Intent intent = new Intent(getActivity().getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                getActivity().startService(intent);
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MouseActivity) getActivity()).removeVolumeFragment();
            }
        });
    }
}
