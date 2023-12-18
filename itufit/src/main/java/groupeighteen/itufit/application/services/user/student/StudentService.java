package groupeighteen.itufit.application.services.user.student;

import groupeighteen.itufit.application.services.user.student.login.StudentLoginRequest;
import groupeighteen.itufit.application.services.user.student.login.StudentLoginResponse;
import groupeighteen.itufit.application.services.user.student.register.StudentRegisterRequest;
import groupeighteen.itufit.application.shared.response.IDataResponse;
import groupeighteen.itufit.application.shared.response.IResponse;
import groupeighteen.itufit.domain.user.Student;

public interface StudentService {
    IResponse register(StudentRegisterRequest studentRegisterRequest) throws Exception;
    IDataResponse<StudentLoginResponse> login(StudentLoginRequest studentLoginRequest) throws Exception;
    public Student findById(Long id) throws Exception;
}
