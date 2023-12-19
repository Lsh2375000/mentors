package kr.nomadlab.mentors.notify.mapper;

import kr.nomadlab.mentors.notify.vo.NotifyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotifyMapper {
    void addNotify(NotifyVO notifyVO);

    int countNotify(Long mno);

    void readNotify(Long mno);

    List<NotifyVO> getNotReadNotify(Long mno);
}
