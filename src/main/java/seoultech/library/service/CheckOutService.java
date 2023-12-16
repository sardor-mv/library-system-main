package seoultech.library.service;

import seoultech.library.model.CheckOut;
import seoultech.library.model.User;

public interface CheckOutService {
    CheckOut processIssue(String callNumber, User borrower);
    CheckOut processReturn(String callNumber);
}
