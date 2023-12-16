package seoultech.library.service;

import seoultech.library.model.Reservation;
import seoultech.library.model.ReservationRepository;
import seoultech.library.model.User;

public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservRepository;

    public ReservationServiceImpl(ReservationRepository reservRepository) {
        this.reservRepository = reservRepository;
    }
    @Override
    public Reservation save(Reservation reservation, User actioner) {

        return null;
    }


}