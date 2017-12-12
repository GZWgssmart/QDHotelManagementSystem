package top.zywork.dao;

import org.springframework.stereotype.Repository;
import top.zywork.vo.HouseOthersItemVo;
import top.zywork.vo.Select2Vo;

import java.util.List;

/**
 * Created by chenfeilong on 2017/12/12.
 */
@Repository
public interface HouseOthersItemDAO extends BaseDAO<HouseOthersItemVo> {
    List<Select2Vo> subjectList(Long companyId);
}
