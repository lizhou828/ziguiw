<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="t-box">
	<div class="t-logo">
		<img src="images/index_05.jpg" />
	</div>
	<div class="t-sousuo-box">
		<div class="t-sousuo-box1">
			<div class="t-sousuo-box-shu">
				<input name="textfield" type="text" class="shurukuan-01" />
				<select class="t-sousuo-box-xia">
					<option>
						按版本查找
					</option>
					<option>
						按年级查找
					</option>
					<option>
						按科目查找
					</option>
				</select>
			</div>
			<input name="Submit" type="submit" class="t-sousuo-box-an" value="提交" />
		</div>
		<div class="t-sousuo-box2">
			<input name="radiobutton" type="radio" value="radiobutton"
				checked="checked" />
			全部
			<input type="radio" name="radiobutton" value="radiobutton" />
			TXT
			<input type="radio" name="radiobutton" value="radiobutton" />
			DOC
			<input type="radio" name="radiobutton" value="radiobutton" />
			PD
			<input type="radio" name="radiobutton" value="radiobutton" />
			PPT
			<input type="radio" name="radiobutton" value="radiobutton" />
			RAR
			<input type="radio" name="radiobutton" value="radiobutton" />
			EXE
			<input type="radio" name="radiobutton" value="radiobutton" />
			CHM
			<input type="radio" name="radiobutton" value="radiobutton" />
			FLV
		</div>
	</div>
</div>
<div class="t-link_box">
	<ul>
		<li>
			<a href="sourceInfo.action">资源首页</a>
		</li>
		<li>
			<a href="schoolInfo.action?leve=1">小学资源</a>
		</li>
		<li>
			<a href="schoolInfo.action?leve=3">初中资源</a>
		</li>
		<li>
			<a href="schoolInfo.action?leve=2">高中资源</a>
		</li>
	</ul>
</div>
<div class="t-AD-03">
	<p class="text-1-3">
		现已有近
		<span class="text-1-2-1">3,000,000</span>份教育资源可供下载
	</p>
	<p>
		<img src="images/zaixiananniu.jpg" />
	</p>
</div>