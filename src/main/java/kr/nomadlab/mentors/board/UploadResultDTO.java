package kr.nomadlab.mentors.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {
    private String uuid;
    private String files;
    private boolean isImage; // 이미지 파일 여부

    public String getLink() {
        if (isImage) { // 이미지 파일의 경우 섬네일 리턴.
            return files; // 이미지 파일의 경우
        } else {
            return files;
        }
    }
}
