package sw.test.application;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 并发请求工具类
 */
public class HttpConcurrentTest {
    //计数
    private static int count = 1;
    //默认并发数
    private static int num = 2;
    //线程阻塞，模拟并发
    private static CountDownLatch cdl = new CountDownLatch(num);

    public static void request(String url, Method method, Map<String, Object> paramMap, Integer n) {
        if (n != null) {
            num = n;
        }
        for (int i = 0; i < num; i++) {
            new Thread(new UserRequest(url, method, paramMap)).start();
            cdl.countDown();
        }
    }

    public static class UserRequest implements Runnable {
        private Method method;
        private String url;
        private Map<String, Object> paramMap;

        public UserRequest(String url, Method method, Map<String, Object> paramMap) {
            this.url = url;
            this.method = method;
            this.paramMap = paramMap;
        }

        @Override
        public void run() {
            try {
                cdl.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            doPost(url, paramMap);
        }

        public void doPost(String url, Map<String, Object> paramMap) {
            HttpRequest request = HttpUtil.createRequest(method, url);
            String jsonString = JSON.toJSONString(paramMap);
            //json参数格式
            request.body(jsonString);
            //表单请求
            //request.form(paramMap);
            request.setConnectionTimeout(60 * 1000);
            String response = request.execute().body();
            System.out.println(count+ "-响应结果:" + response);
            count ++;
        }
    }

    public static void main(String[] args) {
        String url = "http://localhost:9000/auth/user/info?access_token=d0ca7395-d3e1-4ee6-92f6-101f13d3049f";
        HttpConcurrentTest.request(url, Method.GET, null, 10000);
    }
}
