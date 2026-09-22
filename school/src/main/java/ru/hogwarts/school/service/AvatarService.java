package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    @Autowired
    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    public Avatar uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        Student student = studentRepository.findById(studentId).
                orElseThrow(() -> new RuntimeException("Студент не найден"));

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+ 1);
        Path filePath = Path.of(avatarsDir,studentId + "." + extension);

        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        Avatar avatar = new Avatar();
        avatar.setStudent(student);
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());

        avatar.setFilePath(filePath.toString());
        return avatarRepository.save(avatar);
    }

    public Avatar findByStudentId(Long studentId) {
        return avatarRepository.findByStudentId(studentId).orElseThrow(() -> new RuntimeException("Аватар не найден"));
    }
    public byte[] getAvatarFromDisk(Long studentId) throws IOException {
        Avatar avatar = findByStudentId(studentId);
        Path path = Path.of(avatar.getFilePath());
        return Files.readAllBytes(path);
    }

}
