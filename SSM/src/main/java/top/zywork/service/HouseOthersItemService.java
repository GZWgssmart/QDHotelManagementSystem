package top.zywork.service;

import top.zywork.vo.HouseOthersItemVo;
import top.zywork.vo.Select2Vo;

import java.util.List;

/**
 * Created by chenfeilong on 2017/12/12.
 */
public interface HouseOthersItemService extends BaseService<HouseOthersItemVo> {
    List<Select2Vo> subjectList(Long companyId);
}
