package util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RestUtil {
    private static RestTemplate restTemplate = new RestTemplate();
    /**
     * Get方法
     *
     * @param  url:url
     * @param  returnClassName:返回对象类型,如:String.class
     * @param  parameters:parameter参数
     * @return 返回结果
     */
    public static <T> T get(String url, Class<T> returnClassName, Map<String, Object> parameters){
        if (parameters == null) {
            return restTemplate.getForObject(url, returnClassName);
        }
        return restTemplate.getForObject(url, returnClassName, parameters);
    }

    /**
     * post请求,包含了路径,返回类型,Header,Parameter
     *
     * @param  url:地址
     * @param  returnClassName:返回对象类型,如:String.class
     * @param  inputHeader:请求header
     * @param  inputParameter:请求参数
     * @param  jsonBody:请求的消息体
     * @return 返回结果
     */
    public static <T> T post(String url, Class<T> returnClassName, Map<String,Object> inputHeader, Map<String,Object> inputParameter, String jsonBody){
        //请求Header
        HttpHeaders httpHeaders = new HttpHeaders();
        //拼接Header
        if (inputHeader != null) {
            Set<String> keys = inputHeader.keySet();
            for (String key : keys) {
                httpHeaders.add(key, inputHeader.get(key).toString());
            }
        }
        //设置请求的类型及编码
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        httpHeaders.setContentType(type);
        httpHeaders.add("Accept", "application/json");
        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.ALL);
        httpHeaders.setAccept(acceptableMediaTypes);

        HttpEntity<String> formEntity = new HttpEntity<>(jsonBody, httpHeaders);
        if (inputParameter==null) {
            return restTemplate.postForObject(url, formEntity, returnClassName);
        }
        return restTemplate.postForObject(url, formEntity, returnClassName, inputParameter);
    }

    /**
     * put请求，content-type为json
     *
     * @param url:地址
     * @param jsonBody:请求的消息体
     */
    public static void putJson(String url, String jsonBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
        restTemplate.put(url, entity);
    }
}
