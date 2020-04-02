package com.example.recipecookbook;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.recipecookbook.databinding.StepDetailFragmentBinding;
import com.example.recipecookbook.model.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class StepDetailFragment extends Fragment {

    private StepDetailFragmentBinding binding;
    private View rootView;

    private Step currentStep;

    private SimpleExoPlayerView playerView;
    private SimpleExoPlayer exoPlayer;
    private PlaybackStateListener playbackStateListener;

    public StepDetailFragment(Step currentStep) {
        this.currentStep = currentStep;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = StepDetailFragmentBinding.inflate(getLayoutInflater());
        rootView = binding.getRoot();
        playerView = binding.playerView;
        playbackStateListener = new PlaybackStateListener();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initializeExoPlayer(Uri.parse(currentStep.getVideoURL()));
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        releasePlayer();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }

    private void initializeExoPlayer(Uri videoUrl) {
        if (exoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();

            exoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            playerView.setPlayer(exoPlayer);

            String userAgent = Util.getUserAgent(getActivity(), getString(R.string.app_name));

            MediaSource mediaSource = new ExtractorMediaSource(videoUrl, new DefaultDataSourceFactory(getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
            exoPlayer.addListener(playbackStateListener);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.removeListener(playbackStateListener);
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    private class PlaybackStateListener implements Player.EventListener {
        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            if((playbackState == ExoPlayer.STATE_READY) && playWhenReady){
                Log.d("SNIPER", "onPlayerStateChanged: PLAYING");
            } else if((playbackState == ExoPlayer.STATE_READY)){
                Log.d("SNIPER", "onPlayerStateChanged: PAUSED");
            }
        }
    }
}
