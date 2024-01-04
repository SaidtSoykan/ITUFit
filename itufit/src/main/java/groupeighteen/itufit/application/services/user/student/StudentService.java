package groupeighteen.itufit.application.services.user.student;

import java.util.List;

import groupeighteen.itufit.application.services.user.student.login.StudentLoginRequest;
import groupeighteen.itufit.application.services.user.student.login.StudentLoginResponse;
import groupeighteen.itufit.application.services.user.student.physicalinfo.StudentGetPhysicalInfoRequest;
import groupeighteen.itufit.application.services.user.student.physicalinfo.StudentGetPhysicalInfoRespond;
import groupeighteen.itufit.application.services.user.student.physicalinfo.StudentSetPhysicalInfoRequest;
import groupeighteen.itufit.application.services.user.student.ranking.StudentRankingResponse;
import groupeighteen.itufit.application.services.user.student.register.StudentRegisterRequest;
import groupeighteen.itufit.application.shared.response.DataResponse;
import groupeighteen.itufit.application.shared.response.IDataResponse;
import groupeighteen.itufit.application.shared.response.IResponse;
import groupeighteen.itufit.domain.user.Student;

public interface StudentService {
    IResponse register(StudentRegisterRequest studentRegisterRequest);
    IDataResponse<StudentLoginResponse> login(StudentLoginRequest studentLoginRequest);
    IResponse setPhysicalInfo(StudentSetPhysicalInfoRequest studentSetPhysicalInfoRequest);
    IDataResponse<StudentGetPhysicalInfoRespond> getPhysicalInfo(StudentGetPhysicalInfoRequest studentGetPhysicalInfoRequest);
    Student findById(Long id);
    void decreaseScore(Long id);
    void increaseScore(Long id);
    public DataResponse<List<StudentRankingResponse>> listRankings();
}
