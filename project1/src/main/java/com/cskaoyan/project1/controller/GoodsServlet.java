package com.cskaoyan.project1.controller;

import com.cskaoyan.project1.model.Message;
import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.GoodsBO;
import com.cskaoyan.project1.model.bo.ReplyBO;
import com.cskaoyan.project1.model.bo.TypeBO;
import com.cskaoyan.project1.model.vo.GoodChangeVO;
import com.cskaoyan.project1.model.vo.TypeGoodsVO;
import com.cskaoyan.project1.service.GoodsService;
import com.cskaoyan.project1.service.GoodsServiceImpl;
import com.cskaoyan.project1.utils.DruidUtils;
import com.cskaoyan.project1.utils.FileUploadUtils;
import com.cskaoyan.project1.utils.HttpUtils;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpPrincipal;
import org.omg.PortableInterceptor.INACTIVE;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/api/admin/goods/*")
public class GoodsServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsServiceImpl();
    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/","" );
        if("imgUpload".equals(action)){
            imgUpload(request,response);
        }else if("addGoods".equals(action)){
            addGoods(request,response);
        }else if("reply".equals(action)){
            reply(request,response);
        }else if("addType".equals(action)){
            addType(request,response);
        }
    }
    //未实现，和add差不多添加了个BO中添加个id
    private void updateGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        GoodsBO goodsBO = gson.fromJson(requestBody, GoodsBO.class);
        goodsService.addGoods(goodsBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void addType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        TypeBO typeBO = gson.fromJson(requestBody, TypeBO.class);
        goodsService.addType(typeBO);
        response.getWriter().println(gson.toJson(Result.ok(0)));
    }

    /**
     * @Description :保存商品的业务逻辑
     * 1.处理请求体参数
     * 2.处理业务逻辑
     * 3.作出响应
       @param request
	 * @param response
     * @Return : void
     */
    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        GoodsBO goodsBO = gson.fromJson(requestBody, GoodsBO.class);
        goodsService.addGoods(goodsBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * @Description :新增商品图片的业务逻辑
     * commonns-fileUpload
     * 1.执行具体的业务逻辑，上传图片
     * 2.响应---抓服务器上的响应报文
       @param request
	 * @param response
     * @Return : void
     */
    private void imgUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = FileUploadUtils.parseRequest(request);
        String file =(String)map.get("file");
        System.out.println("file:"+file);
        //服务器上的路径没有携带域名端口号，就说明图片就从当前端口号去取
        //如果你的图片和页面不在一个域内，应当指明你文件所在的域
        //全路径
        String domain = (String)getServletContext().getAttribute("domain");
        System.out.println("domain:"+domain);
        response.getWriter().println(gson.toJson(Result.ok(domain+file)));
        System.out.println("domain+file:"+domain+file);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/","" );
        if("getType".equals(action)){
            getType(request,response);
        }else if("getGoodsByType".equals(action)){
            getGoodsByType(request,response);
        }else if("noReplyMsg".equals(action)){
            noReplyMsg(request,response);
        }else if("repliedMsg".equals(action)){
            repliedMsg(request,response);
        }else if("deleteType".equals(action)){
            deleteType(request,response);
        }else if("getGoodsInfo".equals(action)){
            getGoodsInfo(request,response);
        }
    }

    private void getGoodsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        GoodChangeVO goodChangeVO = goodsService.getGoodsInfo(id);
        Result result = new Result();
        result.setCode(0);
        result.setData(goodChangeVO);
        response.getWriter().println(gson.toJson(result));
    }

    private void deleteType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("typeId");
        Integer Id = Integer.valueOf(id);
        goodsService.deleteType(Id);
        response.getWriter().println(gson.toJson(Result.ok(0)));
    }

    private void reply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        ReplyBO replyBO = gson.fromJson(requestBody,ReplyBO.class);
        goodsService.reply(replyBO);
        response.getWriter().println(gson.toJson(Result.ok(0)));
    }

    private void repliedMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Message> messageList = goodsService.replicedMsg();
        Result result = new Result();
        result.setCode(0);
        result.setData(messageList);
        response.getWriter().println(gson.toJson(result));
    }

    /**
     * @Description :未回复
       @param request
	 * @param response
     * @Return : void
     */
    private void noReplyMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Message> messageList = goodsService.noReplyMsg();
        Result result = new Result();
        result.setCode(0);
        result.setData(messageList);
        response.getWriter().println(gson.toJson(result));
    }

    /**
     * @Description :获取某个分类下的商品信息
     * 1.获取分类id
     * 2.数据库查询
     * 3.按照一定的数据结构返回
       @param request
    	 * @param response
     * @Return : void
     */
    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String typeId = request.getParameter("typeId");
        List<TypeGoodsVO> goodsVOS = goodsService.getGoodsByType(typeId);
        response.getWriter().println(gson.toJson(Result.ok(goodsVOS)));
    }

    private void getType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Type> typeList = goodsService.getType();
        response.getWriter().println(gson.toJson(Result.ok((typeList))));
    }
}
