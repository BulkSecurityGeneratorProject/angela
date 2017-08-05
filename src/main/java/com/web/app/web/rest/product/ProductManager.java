package com.web.app.web.rest.product;

import com.codahale.metrics.annotation.Timed;
import com.web.app.service.util.Constant;
import com.web.app.web.rest.T_case_infoResource;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * User: lr
 * Date: 2017-08-05
 * Time: 13:43
 */
@RestController
@RequestMapping("/api/product")
public class ProductManager {
    private final Logger log = LoggerFactory.getLogger(ProductManager.class);


    /**
     * 获取产品详细
     * @param request
     * @return
     */
    @GetMapping("/getProductInfo")
    @ResponseBody
    public JSONObject getProductInfo(@RequestBody JSONObject jsnObj, HttpServletRequest request){

        return null;
    }


    /**
     * 图片文件上传
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/uploadPictures")
    @Timed
    public JSONObject uploadPicture(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws JSONException {
        log.info("===========upload Picture Begin===========");
        JSONObject jsonObj = new JSONObject();
        if(file.isEmpty()){
            jsonObj.put(Constant.MESSAGE, "文件不存在,请检查参数!");
            jsonObj.put(Constant.CODE, "500");
            return jsonObj;
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径  本地
        File fileUrl =new File(request.getServletContext().getRealPath("/upload"));
        // 文件上传后的路径  服务器
        //File fileUrl =new File(Constant.FILE_ADDRESS);
        File files = new File(fileUrl ,fileName);
        log.info("===========Media File Path :{}===========",fileUrl);
        // 检测是否存在目录
        if(!files.getParentFile().exists()) {
            files.getParentFile().mkdirs();
        }
        try{
            file.transferTo(files);
            jsonObj.put(Constant.MESSAGE, "上传成功!");
            jsonObj.put(Constant.CODE, "200");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //删除文件
        files.delete();
        return jsonObj;
    }

}
