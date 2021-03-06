package top.zywork.service.impl;

import org.springframework.stereotype.Service;
import top.zywork.dao.HouseDAO;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQuery;
import top.zywork.service.HouseService;
import top.zywork.vo.HouseVo;
import top.zywork.vo.Select2Vo;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by chenfeilong on 2017/11/12.
 */
@Service
public class HouseServiceImpl implements HouseService {
    @Resource
    private HouseDAO houserDAO;
    @Override
    public void save(HouseVo userVo) {
        houserDAO.save(userVo);
    }

    @Override
    public void remove(HouseVo userVo) {
        houserDAO.remove(userVo);
    }

    @Override
    public void removeById(Long id) {
        houserDAO.removeById(id);
    }

    @Override
    public void update(HouseVo userVo) {
        houserDAO.update(userVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        houserDAO.updateStatus(statusQuery);
    }

    @Override
    public HouseVo getById(Long id) {
        return houserDAO.getById(id);
    }

    @Override
    public List<HouseVo> listAll() {
        return houserDAO.listAll();
    }

    @Override
    public List<HouseVo> listPage(PageQuery pageQuery) {
        return houserDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return houserDAO.count(pageQuery);
    }

    @Override
    public List<Select2Vo> houseTypeList(long companyId,Long id) {
        return houserDAO.houseTypeList(companyId,id);
    }

    @Override
    public List<Select2Vo> hotelList(long companyId) {
        return houserDAO.hotelList(companyId);
    }

    @Override
    public List<Select2Vo> userList(long companyId) {
        return houserDAO.userList(companyId);
    }

    @Override
    public String getHouseCardTitle(long companyId) {
        return houserDAO.getHouseCardTitle(companyId);
    }

    @Override
    public Long findCount(PageQuery pageQuery, HouseVo houseVo) {
        return houserDAO.findCount(pageQuery, houseVo);
    }

    @Override
    public List<HouseVo> findPages(PageQuery pageQuery, HouseVo houseVo) {
        return houserDAO.findPages(pageQuery, houseVo);
    }

    @Override
    public void updateHouseStatus(StatusQuery statusQuery,Date leaveTitle) {
        houserDAO.updateHouseStatus(statusQuery,leaveTitle);
    }
}
