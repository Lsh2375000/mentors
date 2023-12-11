package kr.nomadlab.progrow_project.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


@RestController
@Log4j2
public class SUpDownController {

    @Value("${com.example.upload.path}")
    private String uploadPath;

    @Operation(summary = "view 파일", description = "GET 방식으로 첨부파일 조회") // Swagger-UI의 문구
    @GetMapping({"/mentorModify/{fileName}", "/mentor/{fileName}"})
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName) {
        // ResponseEntity<Resource> = 헤더와 바디로 구성된 JSON 형태의 response로 전달되는 resource 객체
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
        log.info("resource = " + resource); // resource = file [c:\\upload\\s_0e3d357f-d683-4f1b-9819-7d37752edc5b_수달.jpg]

        String resourceName = resource.getFilename();
        log.info("resourceName = " + resourceName); // resourceName = s_0e3d357f-d683-4f1b-9819-7d37752edc5b_수달.jpg

        HttpHeaders headers = new HttpHeaders();
        log.info("headers = " + headers);

        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath())); // 콘텐츠타입 지정. (이미지면 이미지, 텍스트면 텍스트)
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().headers(headers).body(resource); // 제대로 통신됐으면 헤더랑 바디를 넘겨줌
    }

//    첨부파일 삭제
//    @ApiOperation(value = "remove 파일", notes = "DELETE 방식으로 파일 삭제")
//    @DeleteMapping("/remove/{fileName}")
//    public Map<String, Boolean> removeFile(@PathVariable String fileName) {
//        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
//        String resourceName = resource.getFilename();
//
//        Map<String, Boolean> resultMap = new HashMap<>();
//        boolean removed = false;
//        try {
//            String contentType = Files.probeContentType(resource.getFile().toPath());
//            removed = resource.getFile().delete();
//
//            // 섬네일이 존재 한다면 섬네일도 같이 삭제
//            if (contentType.startsWith("image")) {
//                File thumbFile = new File(uploadPath + File.separator + "s_" + fileName);
//                thumbFile.delete();
//            }
//        } catch (IOException e) {
//            log.info(e.getMessage());
//        }
//        resultMap.put("result", removed);
//        return resultMap;
//    }


}
