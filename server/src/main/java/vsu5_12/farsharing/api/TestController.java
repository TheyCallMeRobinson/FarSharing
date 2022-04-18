package vsu5_12.farsharing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vsu5_12.farsharing.model.entity.AdminEntity;
import vsu5_12.farsharing.model.entity.UserEntity;
import vsu5_12.farsharing.model.entity.UserRoleEntity;
import vsu5_12.farsharing.model.key.AdminId;
import vsu5_12.farsharing.repository.AdminRepository;
import vsu5_12.farsharing.repository.UserRepository;
import vsu5_12.farsharing.repository.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class TestController {

    private UserRoleRepository userRoleRepository;

    private UserRepository userRepository;

    private AdminRepository adminRepository;

    @Autowired
    public TestController(UserRoleRepository userRoleRepository, UserRepository userRepository, AdminRepository adminRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @GetMapping("/")
    void test1() {
        this.userRoleRepository.save(UserRoleEntity.builder().name("Admin").build());
        //return userRoleRepository.findById("Admin").orElseThrow(RuntimeException::new);
        this.userRepository.save(UserEntity.builder()
                .email("qeqwe")
                .password("eqweqwe")
                .role(this.userRoleRepository.findById("Admin").orElseThrow(RuntimeException::new))
                .build());
        UUID uid = this.userRepository.findAll().get(0).getUid();
        this.adminRepository.save(AdminEntity.builder()
                .uid(
                        AdminId.builder()
                                .user(
                                        this.userRepository
                                                .findAll()
                                                .get(0))
                                .build())
                .build());
    }

    @GetMapping("/api")
    List<UserEntity> test() {
        return new ArrayList<>();
    }
}
