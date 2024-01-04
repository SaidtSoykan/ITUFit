package groupeighteen.itufit.application.services.notification;

import groupeighteen.itufit.application.services.notification.create.CreateNotificationRequest;
import groupeighteen.itufit.application.services.notification.delete.DeleteNotificationRequest;
import groupeighteen.itufit.application.services.notification.get.GetNotificationsRequest;
import groupeighteen.itufit.application.services.reservation.delete.ReservationDeleteRequest;
import groupeighteen.itufit.application.services.reservation.edit.ReservationEditRequest;
import groupeighteen.itufit.application.services.reservation.make.ReservationMakeRequest;
import groupeighteen.itufit.application.services.reservation.sessionAvailable.ReservationSessionAvailableRequest;
import groupeighteen.itufit.application.services.reservation.sessionAvailable.ReservationSessionAvailableResponse;
import groupeighteen.itufit.application.shared.response.DataResponse;
import groupeighteen.itufit.application.shared.response.IDataResponse;
import groupeighteen.itufit.application.shared.response.IResponse;
import groupeighteen.itufit.domain.notification.Notification;

import java.util.List;

public interface NotificationService {
    void createNotification(CreateNotificationRequest createNotificationRequest);

    void deleteNotification(DeleteNotificationRequest deleteNotificationRequest);

    IDataResponse<List<Notification>> getNotifications(GetNotificationsRequest getNotificationsRequest);
}
