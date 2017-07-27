package com.zjtravel.web.controller.management;

import com.zjtravel.util.ImageUtil;
import com.zjtravel.util.TravelFileUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * Created by hunger on 2017/3/20.
 */
@Controller
@RequestMapping(value = "/backstage/Page")
public class PageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取上传图片页面
     * @return
     */
    @RequiresPermissions("page:upload")
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String showUploadForm() {
        return "backstage/Page/upload";
    }

    /**
     * 上传图片
     * @param request
     * @param file
     * @return
     */
    @RequiresPermissions("page:upload")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public boolean upload(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) {
        //更改图片名，保证唯一
        String newName = new String(UUID.randomUUID()+"").substring(0,8)+".jpg";
        String imagePath = request.getServletContext().getRealPath("/WEB-INF/jsp/backstage/Page/image");
        String newPath =  request.getServletContext().getRealPath("/WEB-INF/jsp/backstage/Page/image/cut_image")+"/"+newName;
        try {
            if (!file.isEmpty()) {
                logger.info("Process file:{}", file.getOriginalFilename());
                File imageFile = new File(imagePath, newName);
                file.transferTo(imageFile);
                ImageUtil.cutImage(imagePath+"/"+newName,newPath);
            }else {
                return false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 图片预览
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("page:view")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public  String picturePreview(HttpServletRequest request, Model model) {
        setCommonData(model,request);
        return "backstage/Page/index";
    }

    /**
     * 图片删除
     * @param request
     * @param picture
     * @return
     */
    @RequiresPermissions("page:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public  String pictureDelete(HttpServletRequest request, @RequestParam(value = "picture") String picture) {
        String path = request.getServletContext().getRealPath("/WEB-INF/jsp/backstage/Page/image/cut_image");
        File file = new File(path,picture);
        try{
            if(file.delete()){
                System.out.println(file.getName() + " 文件已被删除！");
            }else{
                System.out.println("文件删除失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/backstage/Page/index";
    }


    private void setCommonData(Model model, HttpServletRequest request) {
        String path = request.getServletContext().getRealPath("/WEB-INF/jsp/backstage/Page/image/cut_image");
        model.addAttribute("pictures", TravelFileUtil.travelFile(path));
    }
}
