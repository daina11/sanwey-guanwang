package sw.common.util;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Date;


/**
 * @author dwg
 * 本地测试的图片服务器，图片上传工具类，用于返回链接给前端
 * Date: 2023/12/7
 */

public class UploadImgUtils {
    public static String Updateimg(MultipartFile file) throws Exception {

        String folder = "D:/nginx-img-service/";
        File imageFolder = new File(folder);

        //获取当前时间戳和随机数拼接避免重名
        String date = String.valueOf(new Date().getTime());

        //后面-4是保留文件后缀
        File f = new File(imageFolder, NumberUtils.randomOrderNumber() + date + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost/images/" + f.getName();

            //把图片链接赋值给全局变量
            return imgURL;

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
