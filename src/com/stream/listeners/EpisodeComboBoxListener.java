package com.stream.listeners;

import com.stream.viewmodels.MediaDetailsViewModel;
import com.stream.views.MediaDetailsView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EpisodeComboBoxListener implements ActionListener {

    private MediaDetailsView view;
    private MediaDetailsViewModel viewModel;

    public EpisodeComboBoxListener(MediaDetailsView view, MediaDetailsViewModel viewModel) {
        this.view = view;
        this.viewModel = viewModel;
    }

    /**
     * Sets the selected season episode and updates the text area
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox episodeComboBox = (JComboBox) e.getSource();
        int selectedEpisode = 1;
        if (episodeComboBox.getSelectedItem() != null) {
            selectedEpisode = (int) episodeComboBox.getSelectedItem();
        }
        viewModel.setSelectedEpisode(selectedEpisode);
        view.updateEpisodeTextArea(viewModel.getSampleText(), viewModel.getSelectedSeason(), viewModel.getSelectedEpisode());
    }
}
