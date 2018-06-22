<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<c:url value='/'/>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="cache-control" content="no-store">
		<meta http-equiv="expires" content="0">
		<!-- 为了确保适当的绘制和触屏缩放，需要在 <head> 之中添加 viewport元数据标签。 -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- 若IE安装了Google Frame则使用Google Frame渲染，否则让 IE 浏览器运行最新的渲染模式下 -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<!-- 让部分国产浏览器默认采用高速模式渲染页面 -->
		<meta name="renderer" content="webkit">
		<title>${title}</title>
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>">
		<c:if test="${not empty view}">
			<link href="<c:url value='/resources/css/views/${view}.css'/>"
				rel="stylesheet" type="text/css" />
		</c:if>
		<link rel="icon" href="<c:url value='/resources/icon/favicon.ico'/>" type="image/x-icon" />
		<link rel="shortcut icon" href="<c:url value='/resources/icon/favicon.ico'/>" type="image/x-icon" />
		<script type="text/javascript"
			src="<c:url value='/resources/jquery/jquery-1.11.2.min.js'/>"></script>
		<c:if test="${not empty view}">
			<script type="text/javascript"
				src="<c:url value='/resources/js/views/${view}.js'/>"></script>
		</c:if>
	</head>
	<body>
		<div class="clear" id="app">
			<div class="left">
				<div class="head">
					<div class="floatLeft">
						<span id="deleteFilesOrFolders" class="delete_all">删除所选</span>
					</div>
					<div class="floatRight">
					    <div class="toggle_show">
	                        <span class="fa fa-bars active"></span>
	                        <span class="fa fa-th"></span>
	                    </div>
						<span>按类型排序</span>
						<div class="icon floatRight">
							<img src="resources/icon/sort.png" />
							<ul class="show">
								<li class="active" sort="name"><span>文件名</span></li>
								<li sort="size"><span>大小</span></li>
								<li sort="lastModified"><span>修改日期</span></li>
								<li sort="type"><span>MIME类型</span></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="list_wrap">
					<span folderPath="">全部文件</span>
				</div>
				<ul class="table_list active">
					<li>
						<!-- active决定是在哪个，up和dowm决定是上还是下 -->
						<div class="active">
							<div class="form-cr">
								<input type="checkbox" name="" id="all_" value=""> 
								<label for="all_">
								    <span class="box-icon"></span>
								    <span class="form-checkbox-inset-lable"> </span>
								</label>
							</div>
							<div class="text-ellipsis-1">
								<span>文件名</span> 
								<span id="nameOrder" class="up_down up">
								    <img src="resources/icon/s.png" /> 
								    <img src="resources/icon/x.png" />
								</span>
							</div>
						</div>
						<div class="indent active">
							<span>大小</span>
							<span id="sizeOrder" class="up_down">
							    <img src="resources/icon/s.png" />
							    <img src="resources/icon/x.png" />
							</span>
						</div>
						<div class="indent active">
							<span>修改日期</span>
							<span id="lastModifiedOrder" class="up_down"> 
							    <img src="resources/icon/s.png" />
							    <img src="resources/icon/x.png" />
							</span>
						</div>
						<div class="indent active">
							<span>类型</span>
							<span id="typeOrder" class="up_down">
							    <img src="resources/icon/s.png" />
							    <img src="resources/icon/x.png" />
							</span>
						</div>
					</li>
					<c:forEach items="${fileInfos }" var="fileInfo" varStatus="status">
		                <li class="list_li">
		                    <div>
		                        <div class="form-cr">
		                            <input type="checkbox" name="" id="list_${status.index + 1}" value=""> 
		                            <label for="list_${status.index + 1}">
		                                <span class="box-icon"></span>
		                                <span class="form-checkbox-inset-lable"> </span>
		                            </label>
		                        </div>
		                        <c:choose>
			                        <c:when test="${fileInfo.isFile}">
			                            <div class="img_">
	                                        <img src="resources/icon/picture.png" />
	                                    </div>
			                        </c:when>
			                        <c:otherwise>
				                        <div class="img_">
			                                <img src="resources/icon/folder.png" />
			                            </div>
			                        </c:otherwise>
	                            </c:choose>
	                            <p class="text-ellipsis-1" title="${fileInfo.name}">
	                                <a href="javascript:void(0);" isFile="${fileInfo.isFile}" filePath="<c:url value='/files/${fileInfo.relativePath}'/>">${fileInfo.name}</a>
	                            </p>
		                    </div>
		                    <div>${fileInfo.size}</div>
		                    <div>${fileInfo.lastModified}</div>
		                    <div>
		                        <span>${fileInfo.type}</span> 
		                        <span class="delete">X</span>
		                    </div>
		                </li>
	                </c:forEach>
				</ul>
				<div class="thumbNailImage_wrapper">
	                <div class="choose_all">
	                    <div class="form-cr">
	                        <input type="checkbox" name="" id="all_1" value="">
	                        <label for="all_1"><span class="box-icon"></span><span class="form-checkbox-inset-lable"> </span></label>
	                    </div>
	                </div>
	                <ul class="thumbNailImage clear">
	                    <c:forEach items="${fileInfos }" var="fileInfo" varStatus="status">
		                    <li>
		                        <c:choose>
	                                <c:when test="${fileInfo.isFile}">
				                        <div class="img_c_wrapper" isFile="${fileInfo.isFile}" filePath="<c:url value='/files/${fileInfo.relativePath}'/>">
				                            <div class="img_c">
				                                <img src="<c:url value='/files/${fileInfo.relativePath}'/>" alt="">
				                            </div>
				                        </div>
	                                </c:when>
	                                <c:otherwise>
				                        <div class="img_c_wrapper" isFile="${fileInfo.isFile}" filePath="<c:url value='/files/${fileInfo.relativePath}'/>" fileName="${fileInfo.name}">
				                            <div class="img_c">
				                                <img src="resources/icon/folder.png" alt="">
				                            </div>
				                        </div>
	                                </c:otherwise>
	                            </c:choose>
		                        <div class="text text-ellipsis-1" title="${fileInfo.name}">
		                            ${fileInfo.name}
		                        </div>
		                        <div class="form-cr">
		                            <input type="checkbox" name="" id="img_${status.index + 1}" value="">
		                            <label for="img_${status.index + 1}"><span class="box-icon"></span><span class="form-checkbox-inset-lable"> </span></label>
		                        </div>
		                    </li>
	                    </c:forEach>
	                </ul>
	            </div>
			</div>
		</div>
		<div id="modal">
	        <div class="close">
	            <span class="delete" title="关闭模态框">X</span>
	        </div>
	        <div class="show_img">
	            <img id="thumbnail" src="" alt="">
	        </div>
	    </div>
	</body>
</html>