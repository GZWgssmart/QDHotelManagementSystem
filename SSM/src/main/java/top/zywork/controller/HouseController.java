package top.zywork.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import top.zywork.common.Message;
import top.zywork.common.PagingBean;
import top.zywork.enums.ActiveStatusEnum;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQuery;
import top.zywork.service.*;
import top.zywork.vo.HouseVo;
import top.zywork.vo.Select2Vo;
import top.zywork.vo.UserVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by chenfeilong on 2017/11/12.
 */
@Controller
@RequestMapping("house")
public class HouseController {
    @Resource
    private HouseService houseService;
    @RequestMapping("houseList")
    @ResponseBody
    public PagingBean houseList(int pageSize, int pageIndex) throws  Exception{
        PagingBean pagingBean = new PagingBean();
        pagingBean.setTotal(houseService.count());
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pagingBean.setrows(houseService.listPage(new PageQuery(pagingBean.getStartIndex(),pagingBean.getPageSize())));
        return pagingBean;
    }
    @RequestMapping("/houseAddSave")
    @ResponseBody
    public Message addSaveHouse(HouseVo house) throws  Exception {
        try{
            String card[] = house.getCardTitle().split(",");
            for (String str: card) {
                house.setCardTitle(str);
                house.setIsActive(ActiveStatusEnum.ACTIVE.getValue().byteValue());
                houseService.save(house);
            }
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }

    }
    @RequestMapping("/findHouse/{id}")
    @ResponseBody
    public HouseVo findhouse(@PathVariable("id") long id){
        HouseVo house = houseService.getById(id);
        return house;
    }
    @RequestMapping("/houseUpdateSave")
    @ResponseBody
    public Message updatehouse(HouseVo house) throws  Exception{
        try{
            houseService.update(house);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyHouse")
    @ResponseBody
    public Message deleteManyhouse(@Param("manyId") String manyId) throws  Exception{
        try{
            String str[] = manyId.split(",");
            for (String s: str) {
                houseService.removeById(Long.parseLong(s));
            }
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("删除失败!");
        }
    }
    @RequestMapping("/deleteHouse/{id}")
    @ResponseBody
    public Message deletehouse(@PathVariable("id") long id) throws  Exception{
        try{
            houseService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/housePage")
    public String table() throws  Exception{
        return "house/houseList";
    }
    @RequestMapping("/getHouseCardTitle/{id}")
    @ResponseBody
    public HouseVo getHouseCardTitle(@PathVariable("id") long id) throws  Exception{
        String card = houseService.getHouseCardTitle(id);
        HouseVo houseVo = new HouseVo();
        houseVo.setCardTitle(card);
        return houseVo;
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status) throws  Exception{
        try{
            houseService.updateStatus(new StatusQuery(id,status));
            return Message.success("ok");
        }catch (Exception e){
            return  Message.fail("fail");
        }
    }
    @RequestMapping("addHousePage")
    public String addHousePage(){

        return  "house/houseAdd";
    }
    @RequestMapping("updateHouse/{id}")
    public ModelAndView updateHouse(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("house/houseUpdate");
        modelAndView.addObject("id",id);
        return  modelAndView;
    }
    //获取房间类型列表，返回select2对象的数据
    @RequestMapping("getTypeList")
    @ResponseBody
    public List<Select2Vo> getTypeList(HttpSession session){
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        List<Select2Vo> typeList=houseService.houseTypeList(userVo.getCompanyId());
        return  typeList;
    }
    @RequestMapping("getHotelList")
    @ResponseBody
    public List<Select2Vo> getHotelList(HttpSession session){
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        List<Select2Vo> hotelList=houseService.hotelList(userVo.getCompanyId());
        return  hotelList;
    }
    @RequestMapping("getUserList")
    @ResponseBody
    public List<Select2Vo> getUserList(HttpSession session) {
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        List<Select2Vo> userList = houseService.userList(userVo.getCompanyId());
        return userList;
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
