function topOrElite(forumId, type ,path) {
	$.ajax({
		type : "post",
		url : path + "/admin/ajax/forum_topOrElite.action",
		dataType : 'text',
		data : '&forumId=' + forumId + '&operateType='+type,
		success : function(text) {
			if (text == 'success') {				
				alert('恭喜你已成功设置该帖子！');
				document.getElementById(type + forumId).innerHTML = '已'+document.getElementById(type + forumId).innerHTML.replace(/<[^>].*?>/g,"");
		    }
	       }
	});
   }

   
function deleteResource(id, type, path) {
	if (window.confirm('你确定要删除该资源吗？')) {
		var actionUrl;
		if (type == 'newsChannle') {
			actionUrl = path + '/admin/ajax/newsChannle_deleteAjax.action';
		} else if (type == 'knowledgeChannle') {
			actionUrl = path + '/admin/ajax/knowledgeChannle_deleteAjax.action';
		} else if (type == 'board') {
			actionUrl = path + '/admin/ajax/board_deleteAjax.action';
		}else if (type == 'ad') {
			actionUrl = path + '/admin/ajax/ad_deleteAjax.action';
		}

		$.ajax({
					type : "post",
					url : actionUrl,
					dataType : 'text',
					data : '&opIds=' + id,
					success : function(text) {
						if (text == 'success') {
							alert('恭喜你已成功删除该资源！');
							// document.getElementById("fakeDeleteid" +
							// answerId).innerHTML = "已删除"
							window.location.reload();
						} else {
							alert('删除失败，请将该资源下的其他资源删除或迁移后再试！');
						}
					}
				});
	}
}

function getDebateOpinions(debateId, path) {
	$.ajax({
		type : "post",
		// url : "/admin/ajax/getAjaxAnswerByQuestionId.action",
		url : path
				+ "/admin/ajax/debateOpinion_getAjaxDebateOpinionsByDebateId.action",

		dataType : 'json',
		data : '&debateId=' + debateId,
		success : function(json) {
			var result = json.result;
			var htmlStr = '<table width="900" border="0" cellspacing="0" cellpadding="0" style="border:1px solid #000;"><tr><th colspan="6" scope="col" height="25" bgcolor="#99CCFF" style="border-bottom:1px solid #000; padding:0 15px;"><a onclick="closeWindow();" style=" cursor:pointer;float:right; color:#000; text-decoration:none; font-size:14px; font-weight:bold; font-family: "黑体";">X</a>辩论观点管理</th></tr><tr bgcolor="#eeeeFF"><td colspan="1" style=" height:3px; line-height:3px;">支持方</td><td colspan="1" style=" height:3px; line-height:3px;">参与人昵称</td><td colspan="1" style=" height:3px; line-height:3px;">发表时间</td><td colspan="1" style=" height:3px; line-height:3px;">具体意见</td><td colspan="1" style=" height:3px; line-height:3px;">状态</td><td colspan="1" style=" height:3px; line-height:3px;">操作</td></tr>';
			if (result == null) {
				htmlStr = htmlStr
						+ '<tr bgcolor="#eeeeFF"><td>该辩论话题尚无用户参与！</td></tr>';
			} else if (result.length == 0) {
				htmlStr = htmlStr
						+ '<tr bgcolor="#eeeeFF"><td>该辩论话题尚无用户参与！</td></tr>';
			} else {
				$.each(result, function(i, value) {
					htmlStr = htmlStr + '<tr bgcolor="#eeeeFF">';
					htmlStr = htmlStr
							+ '<td colspan="1" style=" height:3px; line-height:3px;">';
					if (value.status == 1) {
						htmlStr = htmlStr + "正方";
					} else if (value.status == 2) {
						htmlStr = htmlStr + "反方";
					}
					+'</td>';
					htmlStr = htmlStr
							+ '<td colspan="1" style=" height:3px; line-height:3px;">'
							+ value.creatorNick + '</td>';
					htmlStr = htmlStr
							+ '<td colspan="1" style=" height:3px; line-height:3px;">'
							+ value.formatedCreateTime + '</td>';
					htmlStr = htmlStr
							+ '<td colspan="1" style=" height:3px; line-height:3px;">'
							+ value.opinion + '</td>';
					htmlStr = htmlStr
							+ '<td colspan="1" style=" height:3px; line-height:3px;">';
					if (value.state == 1) {
						htmlStr = htmlStr + "有效";
					} else {
						htmlStr = htmlStr + "无效";
					}
					+'</td>';
					htmlStr = htmlStr
							+ '<td colspan="1" style=" height:3px; line-height:3px;" id="fakeDeleteid'
							+ value.id
							+ '"><a href="javascript:void(0)" onclick="javascript:fakeDeleteDebateOpinion('
							+ value.id + ',\'' + path + '\')">删除</a></td>';
					htmlStr = htmlStr + '</tr">';
				})
			}

			htmlStr = htmlStr + '</table>';
			document.getElementById("newTopics").innerHTML = htmlStr;
			showdiv();

		}
	});
}

function fakeDeleteDebateOpinion(debateOpinionId, path) {
	$.ajax({
		type : "post",
		url : path + "/admin/ajax/debateOpinion_fakeDelete.action",
		dataType : 'text',
		data : '&opIds=' + debateOpinionId,
		success : function(text) {
			if (text == 'success') {
				alert('恭喜你已成功删除辩论观点！');
				document.getElementById("fakeDeleteid" + debateOpinionId).innerHTML = "已删除"
			}
		}
	});
}

function getAnswers(questionId, path) {
	$.ajax({
		type : "post",
		// url : "/admin/ajax/getAjaxAnswerByQuestionId.action",
		url : path + "/admin/ajax/answer_getAjaxAnswerByQuestionId.action",

		dataType : 'json',
		data : '&questionId=' + questionId,
		success : function(json) {
			var result = json.result;
			var htmlStr = '<table width="900" border="0" cellspacing="0" cellpadding="0" style="border:1px solid #000;"><tr><th colspan="6" scope="col" height="25" bgcolor="#99CCFF" style="border-bottom:1px solid #000; padding:0 15px;"><a onclick="closeWindow();" style=" cursor:pointer;float:right; color:#000; text-decoration:none; font-size:14px; font-weight:bold; font-family: "黑体";">X</a>答案管理</th></tr><tr bgcolor="#eeeeFF"><td colspan="1" style=" height:3px; line-height:3px;">好评次数</td><td colspan="1" style=" height:3px; line-height:3px;">回答人昵称</td><td colspan="1" style=" height:3px; line-height:3px;">回答时间</td><td colspan="1" style=" height:3px; line-height:3px;">回答内容</td><td colspan="1" style=" height:3px; line-height:3px;">状态</td><td colspan="1" style=" height:3px; line-height:3px;">操作</td></tr>';
			if (result.length == 0) {
				htmlStr = htmlStr
						+ '<tr bgcolor="#eeeeFF"><td>该问题尚无用户回答！</td></tr>';
			}

			$.each(result, function(i, value) {
				htmlStr = htmlStr + '<tr bgcolor="#eeeeFF">';
				htmlStr = htmlStr
						+ '<td colspan="1" style=" height:3px; line-height:3px;">'
						+ value.goodCommentCount + '</td>';
				htmlStr = htmlStr
						+ '<td colspan="1" style=" height:3px; line-height:3px;">'
						+ value.creatorNick + '</td>';
				htmlStr = htmlStr
						+ '<td colspan="1" style=" height:3px; line-height:3px;">'
						+ value.formatedCreateTime + '</td>';
				htmlStr = htmlStr
						+ '<td colspan="1" style=" height:3px; line-height:3px;">'
						+ value.content + '</td>';
				htmlStr = htmlStr
						+ '<td colspan="1" style=" height:3px; line-height:3px;">';
				if (value.state == 1) {
					htmlStr = htmlStr + "有效";
				} else {
					htmlStr = htmlStr + "无效";
				}
				+'</td>';
				htmlStr = htmlStr
						+ '<td colspan="1" style=" height:3px; line-height:3px;" id="fakeDeleteid'
						+ value.id
						+ '"><a href="javascript:void(0)" onclick="javascript:fakeDeleteAnswer('
						+ value.id + ',\'' + path + '\')">删除</a></td>';
				htmlStr = htmlStr + '</tr">';
			})
			htmlStr = htmlStr + '</table>';
			document.getElementById("newTopics").innerHTML = htmlStr;
			showdiv();

		}
	});
}

function showdiv() {
	var h = document.body.scrollTop || document.documentElement.scrollTop;
	document.getElementById("newTopics").style.top = h + 100 + "px";
	document.getElementById('newTopics').style.display = 'block';
}

function fakeDeleteAnswer(answerId, path) {
	$.ajax({
		type : "post",
		url : path + "/admin/ajax/answer_fakeDelete.action",
		dataType : 'text',
		data : '&opIds=' + answerId,
		success : function(text) {
			if (text == 'success') {
				alert('恭喜你已成功删除该答案！');
				document.getElementById("fakeDeleteid" + answerId).innerHTML = "已删除"
			}
		}
	});

}

function closeWindow() {
	document.getElementById('newTopics').style.display = 'none';
}