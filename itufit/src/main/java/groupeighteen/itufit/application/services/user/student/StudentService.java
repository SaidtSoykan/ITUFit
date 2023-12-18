package groupeighteen.itufit.application.services.user.student;

import groupeighteen.itufit.application.services.user.student.login.StudentLoginRequest;
import groupeighteen.itufit.application.services.user.student.login.StudentLoginResponse;
import groupeighteen.itufit.application.services.user.student.physicalinfo.StudentSetPhysicalInfoRequest;
import groupeighteen.itufit.application.services.user.student.register.StudentRegisterRequest;
import groupeighteen.itufit.application.shared.response.IDataResponse;
import groupeighteen.itufit.application.shared.response.IResponse;
import groupeighteen.itufit.domain.user.Student;

public interface StudentService {
    IResponse register(StudentRegisterRequest studentRegisterRequest);
    IDataResponse<StudentLoginResponse> login(StudentLoginRequest studentLoginRequest);
    IResponse setPhysicalInfo(StudentSetPhysicalInfoRequest studentSetPhysicalInfoRequest);
    Student findById(Long id);

}
