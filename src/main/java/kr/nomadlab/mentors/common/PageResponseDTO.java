package kr.nomadlab.mentors.common;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {
    // 제네릭 <E> 쓰는이유 : 나중에 다른종류 객체를 이용하여 PageResponseDTO 를 구성할수 있게 하려고
    private int page; // 현재페이지
    private int size; // 페이징블럭 사이즈
    private int total; // 전체 게시물 숫자
    private int start;
    private int end;
    private boolean prev;
    private boolean next;
    private List<E> dtoList;

    @Builder(builderMethodName = "withAll") // 생성자
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        this.page = pageRequestDTO.getPage();   // 현재페이지
        this.size = pageRequestDTO.getSize(); // page당 게시물 개수

        this.total = total;
        this.dtoList = dtoList;

        this.end = (int) (Math.ceil(this.page / 10.0)) * 10; // 나누는 수 10은 페이지블럭수?!
        this.start = this.end - 9;
        int last = (int) (Math.ceil((this.total / (double) size)));

        this.end = end > last ? last : end;
        this.prev = this.start > 1;

        this.next = total > this.end * this.size;

    }
}
