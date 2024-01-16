package sw.api.common.pc;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sw.biz.srv.entity.Carousel;
import sw.biz.srv.service.ICarouselService;
import sw.common.util.UploadImgUtils;
import sw.core.web.Message;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dwg
 * Date: 2023/12/7
 * 轮播图
 */
@RestController
@CrossOrigin
@RequestMapping("/web/admin/carousel")
public class PcCarouselController {
    @Resource
    private ICarouselService iCarouselService;

    @PostMapping("/upload")
    public Message upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        try {
            ArrayList<Map<String, String>> imgList = new ArrayList<>();

            Map<String, String> imgMap = new HashMap<>();
            imgMap.put("filePath", UploadImgUtils.Updateimg(file));
            imgMap.put("fileName", file.getOriginalFilename());
            imgList.add(imgMap);
            return Message.success(imgList);
        } catch (Exception e) {
            return Message.error("上传图片错误");
        }
    }
    @PostMapping("/add")
    public Message add(@RequestBody List<Carousel> carouselList){
            return iCarouselService.saveAll(carouselList);

    }
    @GetMapping("/list")
    public Message list(){
        return iCarouselService.findAll();
    }
}
