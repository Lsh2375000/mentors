package kr.nomadlab.progrow_project.common;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EditorUploadDTO {
    private MultipartFile filedata; // 파일 데이터
    private String callback; // 콜백
    private String callback_func; // 콜백 작동
}
