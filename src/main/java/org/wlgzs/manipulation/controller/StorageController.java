package org.wlgzs.manipulation.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.manipulation.base.BaseController;
import org.wlgzs.manipulation.entity.Storage;
import org.wlgzs.manipulation.util.Result;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 胡亚星
 * @since 2019-03-19
 */
@RestController
@RequestMapping("/storage")
public class StorageController extends BaseController {

    //新增一条充值
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ModelAndView addStorage(Model model, Storage storage) {
        Result result = iStorageService.addStorage(storage);
        if (result.getCode() == 0) {
            model.addAttribute("msg", "添加成功！");
        } else {
            model.addAttribute("msg", "添加失败！");
        }
        return new ModelAndView("redirect:/members/details/" + storage.getMembersId());
    }

    //删除一条充值
    @RequestMapping(value = "/deleteStorage/{storageId}", method = RequestMethod.DELETE)
    public ModelAndView deleteStorage(Model model,@PathVariable("storageId") int storageId) {
        Result result = iStorageService.deleteStorage(storageId);
        if(result.getCode() == 0){
            model.addAttribute("msg","删除成功！");
        }else{
            model.addAttribute("msg","删除失败！");
            return new ModelAndView("redirect:/members/membersList/1");
        }
        Storage storage = (Storage) result.getData();
        return new ModelAndView("redirect:/members/details/" + storage.getMembersId());
    }

}
