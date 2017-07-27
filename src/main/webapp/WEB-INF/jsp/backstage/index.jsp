<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../tag/tag.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="header">

    <div class="dl-title">
        <!--<img src="/chinapost/Public/assets/img/top.png">-->
    </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user">${user.username}</span><a href="/logout" title="退出系统" class="dl-log-quit">[退出]</a>
        <a href="/" title="返回主页" class="dl-log-quit">[返回主页]</a>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
        <ul id="J_Nav"  class="nav-list ks-clear">
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">系统管理</div></li>
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-order">产品管理</div></li>
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-cost">优惠管理</div></li>
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-inventory">订单管理</div></li>
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-goods">网站管理</div></li>
        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
</div>
<script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="assets/js/bui-min.js"></script>
<script type="text/javascript" src="assets/js/common/main-min.js"></script>
<script type="text/javascript" src="assets/js/config-min.js"></script>
<script>
    BUI.use('common/main',function(){
        var config = [
            {
                id: '1',
                menu: [{
                    text: '系统管理',
                    items: [
                        <shiro:hasPermission name="role:view">
                        {
                            id: '3',
                            text: '角色管理',
                            href: 'Role/index'
                        },
                        </shiro:hasPermission>

                        <shiro:hasPermission name="user:view">
                        {
                            id: '4',
                            text: '用户管理',
                            href: 'User/index'
                        },
                        </shiro:hasPermission>

                        <shiro:hasPermission name="resource:view">
                        {
                            id: '6',
                            text: '菜单管理',
                            href: 'Resource/index'
                        }
                        </shiro:hasPermission>
                        ]
                }]
            },
            {
                id: '7',
                menu: [
                        {
                        text: '产品管理',
                        items: [
                     <shiro:hasPermission name="product:view">
                        {
                            id: '9',
                            text: '跟团游管理',
                            href: 'Product/groupTour/index'
                        },
                    </shiro:hasPermission>

                     <shiro:hasPermission name="product:view">
                        {
                            id: '15',
                            text: '票务管理',
                            href: 'Product/ticket/index'
                        },
                     </shiro:hasPermission>

                     <shiro:hasPermission name="product_image:view">
                        {
                            id: '16',
                            text: '图片管理',
                            href: 'Product/preview'
                        }
                     </shiro:hasPermission>
                        ]
                }]
            },
            {
                id: '13',
                menu: [{
                    text: '优惠管理',
                    items: [
                <shiro:hasPermission name="discount:view">
                    {
                        id: '14',
                        text: '打折优惠',
                        href: 'Discount/index'
                    }
                        </shiro:hasPermission>
                    ]
                }]
            },
            {
                id: '8',
                menu: [{
                    text: '订单管理',
                    items: [
                <shiro:hasPermission name="order:view">
                     {
                        id: '12',
                        text: '已完成订单',
                        href: 'Order/index/paid'
                    },
                     {
                        id: '13',
                        text: '退单处理',
                        href: 'Order/index/charge_back'
                    }
                </shiro:hasPermission>
                    ]
                }]
            },
            {
                id: '10',
                menu: [{
                    text: '网站管理',
                    items: [
                <shiro:hasPermission name="page:view">
                    {
                        id: '11',
                        text: '图片轮播',
                        href: 'Page/index'
                    }
                </shiro:hasPermission>
                    ]
                }]
            }];
        new PageUtil.MainPage({
            modulesConfig : config
        });
    });
</script>
</body>
</html>