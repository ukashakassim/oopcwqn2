package com.vu.exhibition.controller;

import com.vu.exhibition.dao.ParticipantDAO;
import com.vu.exhibition.model.Participant;
import ExhibitionFrame.ExhibitionFrame;

public class ExhibitionController {
    private final ParticipantDAO participantDAO;
    private final ExhibitionFrame view;

    public ExhibitionController(ExhibitionFrame view) {
        this.view = view;
        this.participantDAO = new ParticipantDAO();
    }

    public void registerParticipant(Participant participant) {
        if (participantDAO.registerParticipant(participant)) {
            view.showMessage("Participant registered successfully!");
            view.clearForm();
        }
    }

    public Participant searchParticipant(String registrationId) {
        return participantDAO.searchParticipant(registrationId);
    }

    public void updateParticipant(Participant participant) {
        if (participantDAO.updateParticipant(participant)) {
            view.showMessage("Participant updated successfully!");
        }
    }

    public void deleteParticipant(String registrationId) {
        if (participantDAO.deleteParticipant(registrationId)) {
            view.showMessage("Participant deleted successfully!");
            view.clearForm();
        }
    }
}