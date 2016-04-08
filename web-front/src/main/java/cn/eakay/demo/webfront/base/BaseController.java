package cn.eakay.demo.webfront.base;


import cn.eakay.commons.base.ResultDO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Data
public class BaseController {

    public final static String CODE_KEY = "code";
    public final static String DATA_KEY = "data";
    public final static String MESSAGE_KEY = "message";

    public final static int CODE_SUCCESS = 1;
    public final static int CODE_FAIL = 0;

    public static final String RESPONSE_CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
    public static final String RESPONSE_CONTENT_TYPE_HTML = "text/html;charset=UTF-8";
    public static final String RESPONSE_CHARACTER_ENCODING = "UTF-8";
    public static final String JSON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;


    /**
     * 引用Model中的数据
     * @param request
     * @param response
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    protected String json(ResultDO resultDO){
        Gson gson = new GsonBuilder().setDateFormat(JSON_DATE_FORMAT).create();
        String json = gson.toJson(resultDO);
        return json;
    }

    @Deprecated
    protected void jsonResponse(HttpServletResponse response, ResultDO resultDO) {
        PrintWriter out = null;
        response.setContentType(RESPONSE_CONTENT_TYPE_JSON);
        try {
            out = response.getWriter();
            out.print(json(resultDO));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("jsonResponse method exception:", e);
        } finally {
            out.close();
        }
    }

}
