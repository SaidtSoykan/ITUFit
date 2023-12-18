package groupeighteen.itufit.application.services.user.admin;

import groupeighteen.itufit.application.persistence.repositories.AdminKeyRepository;
import groupeighteen.itufit.application.persistence.repositories.AdminRepository;
import groupeighteen.itufit.application.persistence.repositories.StudentRepository;
import groupeighteen.itufit.application.security.HashService;
import groupeighteen.itufit.application.security.JwtService;
import groupeighteen.itufit.application.services.user.admin.login.AdminLoginRequest;
import groupeighteen.itufit.application.services.user.admin.login.AdminLoginResponse;
import groupeighteen.itufit.application.services.user.admin.register.AdminRegisterRequest;
import groupeighteen.itufit.application.shared.response.DataResponse;
import groupeighteen.itufit.application.shared.response.IDataResponse;
import groupeighteen.itufit.application.shared.response.IResponse;
import groupeighteen.itufit.application.shared.response.Response;
import groupeighteen.itufit.domain.user.Admin;
import groupeighteen.itufit.domain.user.AdminKey;
import groupeighteen.itufit.domain.user.Student;
import groupeighteen.itufit.domain.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
public class AdminServiceImp implements UserDetailsService, AdminService {
    private AdminRepository adminRepository;
    private AdminKeyRepository adminKeyRepository;
    private HashService hashService;
    private JwtService jwtService;

    public AdminServiceImp(AdminRepository adminRepository, AdminKeyRepository adminKeyRepository, HashService hashService, JwtService jwtService) {
        this.adminRepository = adminRepository;
        this.adminKeyRepository = adminKeyRepository;
        this.hashService = hashService;
        this.jwtService = jwtService;
    }

    public IDataResponse<AdminLoginResponse> login(AdminLoginRequest adminLoginRequest){
        var optionalAdmin = adminRepository.findByEmail(adminLoginRequest.getEmail());
        if (optionalAdmin.isEmpty())
            throw new RuntimeException();
        var admin = optionalAdmin.get();
        var hashedPassword = hashService.hashPassword(adminLoginRequest.getPassword(), admin.getPasswordSalt());
        if (Arrays.equals(hashedPassword, admin.getPasswordHash())) {
            var response = new DataResponse<>(
                    true,
                    "",
                    new AdminLoginResponse(jwtService.generateToken(admin.getEmail())));

            return response;
        }
        throw new RuntimeException("");
    }

    public IResponse register(AdminRegisterRequest adminRegisterRequest) {
        var optionalAdmin = adminRepository.findByEmail(adminRegisterRequest.getEmail());
        if (optionalAdmin.isPresent())
            throw new RuntimeException("");

        var adminKey = getAdminKey();
        var hashedKey = hashService.hashPassword(adminRegisterRequest.getAdminKey(), adminKey.getKeySalt());
        if (!Arrays.equals(hashedKey, adminKey.getKeyHash())) {
            throw new RuntimeException();
        }

        Admin adminToRegister = new Admin();
        adminToRegister.setEmail(adminRegisterRequest.getEmail());
        adminToRegister.setUserName(extractUsername(adminRegisterRequest.getEmail()));
        adminToRegister.setFirstName(adminRegisterRequest.getFirstName());
        adminToRegister.setLastName(adminRegisterRequest.getLastName());
        adminToRegister.setPasswordSalt(hashService.saltPassword(adminRegisterRequest.getPassword()));
        adminToRegister.setPasswordHash(hashService.hashPassword(adminRegisterRequest.getPassword(), adminToRegister.getPasswordSalt()));

        adminRepository.save(adminToRegister);
        return new Response<>(true, "");
    }

    public User findByEmail(String email) throws Exception {
        var optionalStudent = adminRepository.findByEmail(email);
        if (optionalStudent.isEmpty())
            throw new Exception();
        return optionalStudent.get();
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        try {
            return this.findByEmail(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private AdminKey getAdminKey() {
        var adminKey = adminKeyRepository.findAll();
        if (adminKey.size() != 1)
            throw new RuntimeException();
        return adminKey.get(0);
    }

    public IResponse changeAdminKey(String adminKey) {
        adminKeyRepository.deleteAll();
        var key = new AdminKey();
        var keySalt = hashService.saltPassword(adminKey);
        key.setKeySalt(keySalt);
        key.setKeyHash(hashService.hashPassword(adminKey,keySalt));
        adminKeyRepository.save(key);
        return new Response<>(true, "");
    }

    private String extractUsername(String email){
        return email.substring(0, email.indexOf("@"));
    }
}
