package kr.nomadlab.mentors.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    public int getSkip() {
        return (page - 1) * size;
    }

    // 검색 관련
    private String type; // 검색의 종류

    private String keyword; // 키워드

    @Builder.Default
    private String sort = "new"; // 정렬기준
    private List<String> language = new ArrayList<>(); // 언어별기준
    private String paidFree = "all"; // 무료유료기준

    public String[] getTypes() { // 검색
        if(this.type == null || type.isEmpty()) {
            return null;
        }
        return this.type.split("");
    }

    private String link; // 상세페이지 누른 후 뒤로가기나 list 버튼 누르면 1 페이지로 돌아가는 걸 막기 위해서 상세페이지에 전달해줌

    public String getLink() {
        if(link==null) {
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append("page=").append(this.page);
            stringBuilder.append("&size=").append(this.size);

            if(keyword!=null) {
                stringBuilder.append("&keyword=").append(URLEncoder.encode(this.keyword, StandardCharsets.UTF_8));
            }
            if(hashTag != null) {
                stringBuilder.append("&hashTag=").append(URLEncoder.encode(this.hashTag, StandardCharsets.UTF_8));
            }
            stringBuilder.append("&sort=").append(this.sort);

            link=stringBuilder.toString();
        }
        return link;
    }

    private String hashTag; // 해시태그

    public String[] getHashTags() {
        if(this.hashTag == null || hashTag.isEmpty()) {
            return null;
        }
        return this.hashTag.split(",");
    }
}
