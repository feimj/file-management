/*
 * @(#)filemanagement.js    2018年6月14日
 * 
 * Copyright (c) 2018, GuangZhou MinJian Electronic Technology Co.,LTD. All rights reserved.
 * GuangZhou MinJian Electronic Technology Co.,LTD. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
var sort = "name"; // 默认排序类型
var order = "asc"; // 默认排序方式

$(function() {
	$("#deleteFilesOrFolders").on("click", function() {
		deleteFilesOrFolders();
	});
	
	$(".show > li").on("click", function() {
		$(this).addClass("active");
		$(this).siblings().removeClass("active");
		sort = $(this).attr("sort");
		order = "asc";
		$(".up_down").removeClass("up");
		$(".up_down").removeClass("down");
		if (sort == "name") {
			$("#nameOrder").addClass("up");
		} else if (sort == "size") {
			$("#sizeOrder").addClass("up");
		} else if (sort == "lastModified") {
			$("#lastModifiedOrder").addClass("up");
		} else {
			$("#typeOrder").addClass("up");
		}
		refreshFileList($(".list_wrap").children().last()
				.attr("folderPath"), sort, order); // 刷新文件列表
	});
	
	// 列表模式下全选
	$('#all_').on('click', function() {
		if ($(this).prop('checked')) {
			$(this).closest('.table_list').find('.form-cr>input').prop('checked',true);
		} else {
			$(this).closest('.table_list').find('.form-cr>input').prop('checked',false);
		}		
	});
	
	// 缩略图模式下全选
	$('#all_1').on('click',function () {
        if ($(this).prop('checked')) {
        	$(this).closest('.thumbNailImage_wrapper').find('.form-cr>input').prop('checked',true);
            $('.thumbNailImage li').addClass('active');
		} else {
            $(this).closest('.thumbNailImage_wrapper').find('.form-cr>input').prop('checked',false);
            $('.thumbNailImage li').removeClass('active');
		}
    });
	
	$(".up_down").on("click", function() {
		if ($(this).hasClass("up")) {
			$(this).removeClass("up");
			$(this).addClass("down");
			order = "desc";
			refreshFileList($(".list_wrap").children().last()
					.attr("folderPath"), sort, order); // 刷新文件列表
		} else if ($(this).hasClass("down")) {
			$(this).removeClass("down");
			$(this).addClass("up");
			order = "asc";
			refreshFileList($(".list_wrap").children().last()
					.attr("folderPath"), sort, order); // 刷新文件列表
		} else {}
	});
	
	$('.list_li').on('click', function() {
		$('.right').addClass('active');
	});
	
	$('.icon').on('mouseenter', function() {
		$(this).find('.show').addClass('active');
	});
	
	$('.head').on('mouseleave', function() {
		$(this).find('.show').removeClass('active');
	});
	
	//关闭模态框
	$('#modal .close').on('click',function() {
		$('#modal').removeClass('active');
    });
	
	// Esc键关闭模态框
	$(document).on('keydown',function(e) {
		if ($('#modal').hasClass('active') && e.keyCode == 27) {
            $('#modal').removeClass('active');
		}
    });
	
	// 点击切换样式
	$('.left .head .toggle_show').on('click',function() {
        $('.left .head .toggle_show .fa').toggleClass('active');
        $('.thumbNailImage_wrapper').toggleClass('active');
        $('#all_1').closest('.thumbNailImage_wrapper').find('.form-cr>input').prop('checked',false);
        $('.thumbNailImage').removeClass('active');
		$('.table_list').toggleClass('active');
		$('#all_').closest('.table_list').find('.form-cr>input').prop('checked',false);
    });
	
	$('.thumbNailImage .form-cr>input').on('click',function() {
		if ($(this).prop('checked')) {
			$(this).closest('li').addClass('active');
		} else {
            $(this).closest('li').removeClass('active');
		}
    });
	
	$(".text-ellipsis-1 > a").on("click", function() {
		fileListElementClick($(this));
	});
	
	$(".thumbNailImage li .img_c_wrapper").on("click", function() {
		fileListElementClick($(this));
	});
	
	$(".list_li .delete").on("click", function() {
		deleteOneFileOrFolder($(this));
	});
	
});

function fileListElementClick(element) {
	if (element.attr("isFile") == "true") {
		$("#thumbnail").attr("src", ""); // 清除旧图片
		$("#thumbnail").attr("src", element.attr("filePath"));
		$("#thumbnail").attr("alt", element.text());
		$('#modal').addClass('active');
	} else {
		$("#thumbnail").attr("src", "");
		$(".list_wrap").children().last().addClass("click_allow");
		$(".list_wrap").children().last().on("click", function() {
			refreshFileList($(this).attr("folderPath"), sort, order);
			$(this).removeClass("click_allow");
			$(this).nextAll().remove();
			$(this).off("click");
		});
		var span = $(document.createElement("span"));
		var folderPath = element.attr("filePath").replace("/file-management/files/", "");
		span.attr("folderPath", folderPath);
		if (element.hasClass("img_c_wrapper")) {
			span.text(element.attr("fileName"));
		} else {
			span.text(element.text());
		}
		$(".list_wrap").append(span);
		refreshFileList(folderPath, sort, order); 
	}
}

function refreshFileList(folderPath, sort, order) {
	var fileSystemFolderPath = folderPath.replace("/", "\\");
	var tableList = $(".table_list");
	var thumbNailImage = $(".thumbNailImage");
	var li, div, formCrDiv, imgDiv, input, label, span, img, p, a;
	li = tableList.children().first().clone(true); // 复制事件处理程序
	tableList.empty();
	thumbNailImage.empty();
	tableList.append(li);
	var param = {
			folderPath: fileSystemFolderPath,
			offset: 0,
			limit: -1,
			sort: sort,
			order: order
	};
	$.post("findFileInfosByPage", param, function(back) {
		if (back.findResult == "SUCCESS") {
			var fileInfos = back.fileInfos;
			for (var i = 0; i < fileInfos.length; i ++) {
				// 生成列表模式元素
				li = $(document.createElement("li"));
				li.addClass("list_li");
				
				div = $(document.createElement("div"));
				formCrDiv = $(document.createElement("div"));
				formCrDiv.addClass("form-cr");
				input = $(document.createElement("input"));
				input.attr("id", "list_" + (i + 1));
				input.attr("type", "checkbox");
				input.attr("name", "");
				input.attr("value", "");
				formCrDiv.append(input);
				label = $(document.createElement("label"));
				label.attr("for", "list_" + (i + 1));
				span = $(document.createElement("span"));
				span.addClass("box-icon");
				label.append(span);
				span = $(document.createElement("span"));
				span.addClass("form-checkbox-inset-lable");
				span.text(" ");
				label.append(span);
				formCrDiv.append(label);
				div.append(formCrDiv);
				imgDiv = $(document.createElement("div"));
				imgDiv.addClass("img_");
				img = $(document.createElement("img"));
				if (fileInfos[i].isFile) {
					img.attr("src", "resources/icon/picture.png");
				} else {
					img.attr("src", "resources/icon/folder.png");
				}
				imgDiv.append(img);
				div.append(imgDiv);
				p = $(document.createElement("p"));
				p.addClass("text-ellipsis-1");
				p.attr("title", fileInfos[i].name);
				a = $(document.createElement("a"));
				a.attr("href", "javascript:void(0);");
				a.attr("isFile", "" + fileInfos[i].isFile);
				a.attr("filePath", "/file-management/files/" + fileInfos[i].relativePath);
				a.text(fileInfos[i].name);
				a.on("click", function() {
					fileListElementClick($(this));
				});
				p.append(a);
				div.append(p);
				li.append(div);
				
				div = $(document.createElement("div"));
				div.text(fileInfos[i].size);
				li.append(div);
				
				div = $(document.createElement("div"));
				div.text(fileInfos[i].lastModified);
				li.append(div);
				
				div = $(document.createElement("div"));
				span = $(document.createElement("span"));
				span.text(fileInfos[i].type);
				div.append(span);
				span = $(document.createElement("span"));
				span.addClass("delete");
				span.text("X");
				span.on("click", function() {
					deleteOneFileOrFolder($(this));
				});
				div.append(span);
				li.append(div);
				
				tableList.append(li);
				
				// 生成缩略图模式元素
				li = $(document.createElement("li"));
				
				div = $(document.createElement("div"));
				div.addClass("img_c_wrapper");
				div.attr("isFile", "" + fileInfos[i].isFile);
				div.attr("filePath", "/file-management/files/" + fileInfos[i].relativePath);
				if (!(fileInfos[i].isFile)) {
					div.attr("fileName", fileInfos[i].name);
				}
				imgDiv = $(document.createElement("div"));
				imgDiv.addClass("img_c");
				img = $(document.createElement("img"));
				if (fileInfos[i].isFile) {
					img.attr("src", "files/" + fileInfos[i].relativePath);
				} else {
					img.attr("src", "resources/icon/folder.png");
				}
				imgDiv.append(img);
				div.append(imgDiv);
				div.on("click", function() {
					fileListElementClick($(this));
				});
				li.append(div);
				
				div = $(document.createElement("div"));
				div.addClass("text text-ellipsis-1");
				div.attr("title", fileInfos[i].name);
				div.text(fileInfos[i].name);
				li.append(div);
				
				formCrDiv = $(document.createElement("div"));
				formCrDiv.addClass("form-cr");
				input = $(document.createElement("input"));
				input.attr("id", "img_" + (i + 1));
				input.attr("type", "checkbox");
				input.attr("name", "");
				input.attr("value", "");
				input.on("click", function() {
					if ($(this).prop('checked')) {
						$(this).closest('li').addClass('active');
					} else {
			            $(this).closest('li').removeClass('active');
					}
				});
				formCrDiv.append(input);
				label = $(document.createElement("label"));
				label.attr("for", "img_" + (i + 1));
				span = $(document.createElement("span"));
				span.addClass("box-icon");
				label.append(span);
				span = $(document.createElement("span"));
				span.addClass("form-checkbox-inset-lable");
				span.text(" ");
				label.append(span);
				formCrDiv.append(label);
				li.append(formCrDiv);
				
				thumbNailImage.append(li);
			}
		} else {
			alert("加载数据失败！");
		}
	});
}

function deleteOneFileOrFolder(element) {
	var result = confirm("你正在删除一个文件或文件夹，是否继续？");
	if (result) {
		var deleteFileRelativePath = element.parents(".list_li").find(".text-ellipsis-1 > a").attr("filePath").replace("/file-management/files/", "").replace("/", "\\");
		var deleteFileRelativePaths = [deleteFileRelativePath];
		var param = {relativePaths: deleteFileRelativePaths};
		$.post("deleteFiles", param, function(back) {
			if (back.deleteResult == "SUCCESS") {
				alert("删除成功");
			} else {
				if (back.directoryIsEmpty != undefined) {
					if (!back.directoryIsEmpty) {
						alert("删除失败：不能删除一个非空的文件夹！");
					}
				} else {
					alert("删除失败：删除过程中发生异常！");
				}
			}
			refreshFileList($(".list_wrap").children().last()
					.attr("folderPath"), sort, order); // 刷新文件列表
		});
	} else {
		return;
	}
}

function deleteFilesOrFolders() {
	var selectedCheckBox = null;
	if ($(".table_list").hasClass("active")) {
		selectedCheckBox = $(".list_li .form-cr > input:checked");
	} else {
		selectedCheckBox = $(".thumbNailImage .form-cr > input:checked");
	}
	if (selectedCheckBox == null || selectedCheckBox.length == 0) {
		alert("请选择需要删除的文件或文件夹！");
	} else {
		var result = confirm("你正在删除文件或文件夹，是否继续？");
		if (result) {
			var selectedATag = null;
			if ($(".table_list").hasClass("active")) {
				selectedATag = selectedCheckBox.parents(".list_li").find(".text-ellipsis-1 > a");
			} else {
				selectedATag = selectedCheckBox.parents("li").find(".img_c_wrapper");
			}
			var deleteFileRelativePaths = new Array();
			for (var i = 0; i < selectedATag.length; i ++) {
				deleteFileRelativePaths[i] = $(selectedATag[i]).attr("filePath").replace("/file-management/files/", "").replace("/", "\\");
			}
			var param = {relativePaths: deleteFileRelativePaths};
			$.post("deleteFiles", param, function(back) {
				if (back.deleteResult == "SUCCESS") {
					alert("删除成功");
				} else {
					if (back.directoryIsEmpty != undefined) {
						if (!back.directoryIsEmpty) {
							alert("删除失败：删除的内容至少包含一个非空的文件夹，部分文件可能已删除！");
						}
					} else {
						alert("删除失败：删除过程中发生异常，部分文件可能已删除！");
					}
				}
				refreshFileList($(".list_wrap").children().last()
						.attr("folderPath"), sort, order); // 刷新文件列表
			});
		} else {
			return;
		}
	}
}
