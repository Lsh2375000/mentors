package kr.nomadlab.progrow_project.board;

import kr.nomadlab.progrow_project.common.EditorUploadDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Controller
@Log4j2
@RequestMapping("/editor")
public class EditorController {
    @Value("${com.example.upload.path}")
    private String uploadPath;

    @GetMapping("/view/{dataFolder}/{fileName}")
    public ResponseEntity<Resource> viewFile(@PathVariable String dataFolder, @PathVariable String fileName) {
        // 이미지 파일 출력
        log.info(fileName);
        fileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8);
        Resource resource = new FileSystemResource(uploadPath + File.separator
                + dataFolder + File.separator + fileName);

        // String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @PostMapping(value = "/upload")
    public String upload(EditorUploadDTO editorUploadDTO) throws UnsupportedEncodingException {
        // 이미지 파일 업로드
        log.info("upload");
        log.info(editorUploadDTO);

        String callback = editorUploadDTO.getCallback();
        String callback_func = editorUploadDTO.getCallback_func();
        String fileResult = "";
        String result = "";
        MultipartFile multipartFile = editorUploadDTO.getFiledata();
        try {
            String originalName = multipartFile.getOriginalFilename();
            log.info(multipartFile.getOriginalFilename());

            String uuid = UUID.randomUUID().toString();
            String newName = uuid + "_" + originalName;

            // 오늘 날짜로 폴더 생성
            LocalDate currentDate = LocalDate.now(); // 오늘 날짜 가져오기
            // 날짜 포맷 지정 (원하는 형식으로)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateString = currentDate.format(formatter); // 날짜를 문자열로 변환

            String path = uploadPath + "/" + dateString;
            File file = new File(path);
            if (!file.exists()) { // 폴더가 존재하지 않으면
                file.mkdirs(); // 폴더 생성
            }
            Path savePath = Paths.get(path, newName);
            multipartFile.transferTo(savePath); // 실제 파일 저장

            // 한글로 된 파일명 때문에 URLEncoding 처리
            originalName = URLEncoder.encode(originalName, StandardCharsets.UTF_8);
            newName = URLEncoder.encode(newName, StandardCharsets.UTF_8);
            fileResult += "&bNewLine=true&sFileName=" + originalName +
                            "&sFileURL=/editor/view/" + dateString + "/" + newName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 저장된 경로와 파일이름을 반환
        result = "redirect:" + callback +
                "?callback_func=" + URLEncoder.encode(callback_func, StandardCharsets.UTF_8) + fileResult;
        return result;
    }
}
