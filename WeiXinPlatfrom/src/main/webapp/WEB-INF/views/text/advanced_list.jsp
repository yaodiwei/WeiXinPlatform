<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../inc_html_header.jspf"%>
<link rel="stylesheet" href="css/chat.css?${app:version() }" />
</head>
<body>
<%@ include file="../inc_body_header.jspf"%>
<div id="breadcrumb">You are here: SETTING » <a href="<c:url value="/operator" />">Text</a></div>
<div id="main_content">
<div id="search">
<h2><a href="">Search Criteria</a></h2>
<form id="searchForm" action="<c:url value="/receive" />" method="get">
<p><label for="code">Code</label> <input type="text" name="code" id="code" value="" /></p>
<p><label for="name">Name</label> <input type="text" name="name" id="name" value="" /></p>
<p><button class="button-search" type="submit">Search</button> <button class="button button-cancel" type="button">Cancel</button></p>
<input type="hidden" name="pageIndex" id="pageIndex" value="1" />
</form>
</div>
			<div class="chat lightBorder" ><div class="chatContainer">
				<!--close button-->
				<!--single and group chat,back to chat icon-->
				<!-- singleChat groupChat backToChat -->
				<div class="rightOpBtn groupChat" id="rightOpBtn" click="toggleChatMgr" style="display:none;"><a href="javascript:;"></a></div> 
				<div class="backToChat" id="leftOpBtn" click="toggleChatMgr" style="display:none;"><a href="javascript:;"></a></div> 
				
				

				<div class="chatMainPanel" id="chatMainPanel">
					
					<div class="chatTitle">
						<div class="chatNameWrap">
							<p class="chatName" id="messagePanelTitle"></p>
						</div>
						
					</div>
					<div class="chatScorll">
						<div id="chat_chatmsglist" class="chatContent"></div>
					</div>
					
					<div id="chat_editor" class="chatOperator lightBorder">
						<div class="inputArea">
							<div class="attach">
								<a href="javascript:;" id="sendEmojiIcon" class="func expression" click="showEmojiPanel" title="Send Emoticon"></a>
																<form class="left" id="sendFileIcon" url="http://file2.wx.qq.com/cgi-bin/mmwebwx-bin/webwxuploadmedia?cgi=sendfile&t=chat&callbackfun=sendFile" enctype="multipart/form-data" method="post" target="actionFrame" style="display:none;">
									<input type="hidden" name="uploadmediarequest" value='{BaseRequest:{}}' />
									<a href="javascript:;" class="func file" style="position:relative;display:block;margin:0;" title="Send File" id="uploadFileContainer">
										<div style="position: absolute;top:0;left:0; width: 100%; height: 100%;overflow:hidden;filter:alpha(opacity=0);opacity:0;cursor:pointer;">
										<div>
											<input change="sendAppMsg@form" type="file" name="filename"  style="width:100%;height:100%;margin:0;cursor:pointer;font-size:100px;"></div>
										</div>
									</a>
								</form>
															</div>
							<textarea type="text" id="textInput" class="chatInput lightBorder"></textarea>
							<a href="javascript:;" class="chatSend" click="sendMsg@.inputArea"><b>Send</b></a>
							<div id="recordInput" class="recordInput chatInput" style="display:none;"></div>
							<div class="clr"></div>
							
						</div>
						<div class="dragUploaderPanel" id="dragPanel" style="display:none;">
							<div inTxt="Release mouse button" outTxt="Drag your file here to send" style="text-align:center;">
							</div>
						</div>
						<div class="emojiPanel" style="display:none;" id="emojiPanel"></div>
					</div>
				</div>

				<!--change main panel-->
				<div class="chatDetails" style="display:none;" id="chatDetailPanel"><div id="chat_chattingmgr">
					<div class="chatTitle">
						<div class="chatNameWrap">
							<p class="chatName">Details</p>
						</div>
					</div>

					<div class="chatDetailsContent">
						
						<div class="clr"></div>
						<div class="section participant" id="chattingmgr_list"><div style="margin-top:10px;"></div></div>
						<div class="section" style="">
							<div id="chatting_mgr_operator">
								<div class="chatDetailsTitle left">
									<span class="groupName left"></span>
									<h3 class="left">Group Chat Name:</h3>
									<div class="left" style="position:relative;">
										<p class="partiTitleName left" style="" click="modChatroomTopic" noname="Not Set"></p>
										<input class="left" value="" style="display:none;"/>
									</div>
									<div class="clr"></div>
								</div>
								<div class="exitGroup right">
									<div class="exitGroupPanel">
										<span class="exitGroupIcon left"></span>
										<a href="javascript:;" class="exitGroupWorld left" click="quitChatroom">Delete and Quit</a>
										<div class="clr"></div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div></div>
				<!--change main panel end-->
			</div>hello</div>
			
			
			hello2
</div>
<%@ include file="../inc_footer.jspf"%>
<script>
	$('a.pageNumber').click(function() {
		$('#pageIndex').val($.trim($(this).html()));
		$('#searchForm').submit();
		return false;
	});
</script>
</body>
</html>