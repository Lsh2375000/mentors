package kr.nomadlab.progrow_project.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.nomadlab.progrow_project.board.UploadFileDTO;
import kr.nomadlab.progrow_project.board.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@Log4j2
public class UpDownController {

    @Value("${com.example.uploadTmp.path}")
    private String uploadTmpPath;

    @Value("${com.example.uploadReal.path}")
    private String uploadRealPath;



    @Operation(summary = "Upload Post", description = "POST 방식으로 파일 등록")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO){
        log.info(uploadFileDTO);
        if(uploadFileDTO.getFiles() != null){
            List<UploadResultDTO> list = new ArrayList<>();
            for (MultipartFile multipartFile : uploadFileDTO.getFiles()){
                String originalName = multipartFile.getOriginalFilename();
                log.info(originalName);
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadTmpPath, originalName);
                boolean image = false;
                try {
                    multipartFile.transferTo(savePath); //실제 파일 저장
                    //ㅇㅣ미지 파일이면 섬네일을 생성한다.
                    if(Files.probeContentType(savePath).startsWith("image")){
                        image = true;
                        File thumbFile = new File(uploadRealPath, originalName);
                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);

                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
                list.add(UploadResultDTO.builder()
                        .uuid(uuid)
                        .files(originalName)
                        .isImage(image).build());
            }
            return list;
        }
        return null;
    }


    //viewFileGet() 메서드를 추가
    //임시 파일 저장을 도와주는 메서드
    @Operation(summary = "view 파일", description = "GET방식으로 첨부파일 조회")
    @GetMapping("/viewTmp/{fileNames}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileNames){
        Resource resource = new FileSystemResource(uploadTmpPath + File.separator + fileNames);
        log.info("resource : " + resource);
        // resource : file [/Users/ieunseo/Desktop/upload/s_91d55099-99cc-4298-918b-7dc3b35b36d1_푸바오.jpeg

        String resourceName = resource.getFilename();
        log.info("resourceName :" + resourceName);
        //resourceName :s_91d55099-99cc-4298-918b-7dc3b35b36d1_푸바오.jpeg


        HttpHeaders headers = new HttpHeaders();
        log.info("headers : " + headers);

        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    //viewFileGet() 메서드를 추가
    // real 파일에 저장을 도와주는 메서드!
    @Operation(summary = "view 파일", description = "GET방식으로 첨부파일 조회")
    @GetMapping("/viewReal/{fileNames}")
    public ResponseEntity<Resource> viewRealFileGet(@PathVariable String fileNames){
        Resource resource = new FileSystemResource(uploadRealPath + File.separator + fileNames);
        log.info("resource : " + resource);
        // resource : file [/Users/ieunseo/Desktop/upload/s_91d55099-99cc-4298-918b-7dc3b35b36d1_푸바오.jpeg

        String resourceName = resource.getFilename();
        log.info("resourceName :" + resourceName);
        //resourceName :s_91d55099-99cc-4298-918b-7dc3b35b36d1_푸바오.jpeg


        HttpHeaders headers = new HttpHeaders();
        log.info("headers : " + headers);

        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }


    @Operation(summary = "remove 파일", description = "DELETE 방식으로 파일 삭제")
    @DeleteMapping("/remove/{fileNames}")
    public Map<String, Boolean> removeFile(@PathVariable String fileNames){
        Resource resource = new FileSystemResource(uploadTmpPath + File.separator + fileNames);
        String resourceName = resource.getFilename();

        Map<String, Boolean> resultMap = new HashMap<>();
        boolean remove = true;
        try{
            String contentType = Files.probeContentType(resource.getFile().toPath());
            remove = resource.getFile().delete();

            //섬네일이 존재한다면
            // contentType이 null인지 확인한 후 null이 아닌 경우에만 startWith 메서드를 호출!
            // 이렇게 하면 null인 경우에는 startsWith 메서드를 호출하지 않으므로 오류가 발생하지 않는다!!
            if (contentType!= null && contentType.startsWith("fileNames")){
                File thumbFile = new File(uploadTmpPath + File.separator + "s_" + fileNames);
                thumbFile.delete();
            }
        } catch (IOException e){
            log.error(e.getMessage());
        }
        resultMap.put("result", remove);
        return resultMap;
    }




}
