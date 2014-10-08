<%@ page import="com.zigui.domain.UserBase" %>
<%@ page import="com.zigui.service.impl.UserServiceImpl" %>
<%@ page import="com.zigui.dao.UserBaseDao" %>
<%@ page import="com.zigui.dao.UserDao" %>
<%@page contentType="text/html; UTF-8"  pageEncoding="UTF-8" %>
<%@include file="/comm/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>我的积分-子贵网用户中心</title>
    <link href="/css/uccss.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/userCenter/ucPublic/pageQuery.js"></script>

</head>

<body>
<jsp:include page="ucPublic/head.jsp"></jsp:include>



<div class="uc_midd">

    <div class="points_t">
        <span>我的积分 </span><span class="res_en">MY POINTS</span>
    </div>

    <div class="points_spe">
        <div class="spe_tip">
            您当前的积分：<b>${user.points}</b>分；积分在操作状态成功后10分钟内发至账户，积分有效期为积入之日起至次年年底。如：2012年6月1日获得10积分，有效期至2013年12月31日。
        </div>
        <form action="/userCenter/myPoint.action" method="post" id="queryPoint">
        <div class="spe_con">
            <table border="0">
                <tr>
                    <td colspan="4" style="	color:#fa6f00;font-weight:bold;text-align:left;height:38px;border:none;">积分明细查询：</td>
                </tr>
                <tr>
                    <th>日期</th>
                    <th>来源/用途</th>
                    <th>积分</th>

                    <th>剩余积分</th>
                </tr>
                <c:forEach items="${pointsList}" var="point">
                    <tr>
                        <td>${point.formatedChangeTime}</td>
                        <td>${point.typeStr}</td>
                        <td>${point.pointsChange}</td>
                        <td>${user.points}</td>
                    </tr>
                </c:forEach>

            </table>
        </div>
            <input type="hidden" id="gotoPage" name="gotoPage">
        <div class="points_p">
            <p>
                <c:forEach items="${pageList}" var="page">
                    <a href="javascript:document.getElementById('gotoPage').setAttribute('value','${page}');queryPoint.submit();">${page}</a>
                </c:forEach>
                <input id=maxPage type=hidden value="${totalPage}">
                转到 <input id ="inputPage" type="text"  size="2"/> 页
                <input onclick="changePage();" type="button" id="page" value="Go"/>
            </p>
        </div>
        </form>


    </div>

    <div class="points_spe">
        <div class="spe_how">
            <table border="0">
                <tr>
                    <td colspan="4" style="	color:#fa6f00;font-weight:bold;text-align:left;height:38px;border:none;padding:0;">如何获得积分：</td>
                </tr>
                <tr>
                    <th>积分来源</th>
                    <th>获得积分</th>
                    <th>备注</th>
                </tr>
                <tr>
                    <td>注册成功</td>
                    <td>10分</td>
                    <td></td>
                </tr>
                <tr>
                    <td>完善用户信息 </td>
                    <td>10分</td>
                    <td>提示完善信息成功即可（给予信息完善成功界面提示） </td>
                </tr>
                <tr>
                    <td>每登录一次 </td>
                    <td>1分</td>
                    <td>每日获得的积分上限为5分</td>
                </tr>
                <tr>
                    <td>连续登录5天后 </td>
                    <td>10分</td>
                    <td>每天登录，可额外获得每天领取5积分的权利。若中断，回到第3条</td>
                </tr>
                <tr>
                    <td>每成功提问一次</td>
                    <td>5分</td>
                    <td></td>
                </tr>
                <tr>
                    <td>邀请朋友注册并确认 </td>
                    <td>10分</td>
                    <td>提供朋友的邮箱地址即可邀请</td>
                </tr>
                <tr>
                    <td>被朋友邀请成功注册 </td>
                    <td>5分</td>
                    <td>上传教育资源</td>
                </tr>
                <tr>
                    <td>每登录一次 </td>
                    <td>20分</td>
                    <td></td>
                </tr>
                <tr>
                    <td>成功发起活动</td>
                    <td>50分</td>
                    <td></td>
                </tr>
                <tr>
                    <td>被编辑采纳投稿/篇</td>
                    <td>20分</td>
                    <td></td>
                </tr>
                <tr>
                    <td>写日记</td>
                    <td>5分</td>
                    <td>每日获得的积分上限为10分；被编辑推荐可额外获得10分； </td>
                </tr>
                <tr>
                    <td>成功发贴/条 </td>
                    <td>5分</td>
                    <td>帖子每被点击一次可以得到0.5点积分。由点击引起的积分变化每小时（最多1天）变化1次 </td>
                </tr>
                <tr>
                    <td>成功回贴/条 </td>
                    <td>5分</td>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="3" style="border-bottom:1px solid #474747;color:#fa6f00;font-weight:bold;padding:0;height:32px;margin-bottom:5px;">信息发送积分</td>
                </tr>
                <tr>
                    <td width="160" class="mess">公共信息（通知、留言、公告、作业等）的发送（群发多人，只计1条） </td>
                    <td class="mess">10分/条</td>
                    <td></td>
                </tr>
                <tr>
                    <td width="160" class="mess">成绩、个性评语发送</td>
                    <td class="mess">5分/条</td>
                    <td></td>
                </tr>
                <tr>
                    <td width="160" class="mess">回复家长/学生信息</td>
                    <td class="mess">5分/条</td>
                    <td></td>
                </tr>                    <tr>
                <td colspan="3" style=""></td>
            </tr>
            </table>
            <div class="points_note">
                注：以上所有操作，若未被审核通过，或被管理员删除则自动扣减所得积分；同时，子贵网还会在各个栏目不断地进行各种用户可以参与的活动，比如征集主题日记、征集照片、同城活动、论坛活动等，每个活动都有可能有一定数目的积分奖励。请大家多多留意网站的通知和各栏目的最新动态，就能更加快速地获得积分啦。
            </div>
        </div>
    </div>

    <div class="points_spe">
        <div class="points_rule">
            <p><b>积分规则：</b></p>
            <p>1、子贵网积分专属子贵网用户，用于在子贵网站内的使用；在使用积分时，优先消耗旧积分；</p>
            <p>2、积分可以累积，有效期至少为1年，即从获得开始至次年年底，逾期自动作废；</p>
            <p>3、10积分相关于价值1元人民币的商品。</p>
        </div>
        <div class="points_use">
            <p><b>积分使用方式：</b></p>
            <p>积分使用方式一：在子贵网教育资源栏目用于资源的下载；</p>
            <p>积分使用方式二：在子贵网服务中心参与积分兑换活动，获得超值商品。</p>
        </div>
    </div>

    <div class="clear"></div>


    <div class="uc_noti">
        <p>注：退出本系统前请先注销用户,否则在系统默认的帐号登录时间内，同名的管理员帐号无法从另一IP登录,同时也防止管理员权限贮留，产生安全瘾患！</p>
    </div>

</div>



<jsp:include page="/inc/templateFoot.jsp"/>