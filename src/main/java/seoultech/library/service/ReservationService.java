package seoultech.library.service;

import seoultech.library.model.Reservation;
import seoultech.library.model.User;

public interface ReservationService {
    Reservation save(Reservation reservation, User actioner);
}
