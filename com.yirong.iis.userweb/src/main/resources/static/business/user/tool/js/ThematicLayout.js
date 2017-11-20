if(typeof(window._)!='function'){
    window._= function(expr,context){
        context=context||document;
        //   #id
		if(/^#([\w-\.]+)$/.test(expr)) return context.getElementById(expr.substr(1))||document.getElementsByName(expr.substr(1))[0];
    }
} 
////////////////////////////
var isIE=/msie/i.test(navigator.userAgent);
//var isAdmin=true;
//var isAdminEdit=true;

var tabMainCount=0;
function setAdminEdit(){
	isAdminEdit=!isAdminEdit;
	_('#tabTemplate').style.display=isAdminEdit?'':'none';
	_('#modifyBtn').innerHTML=isAdminEdit?'':'[个性定制]';
	//_('#modifyBtn').innerHTML='';
	for (var i=0; i<tabMainCount; i++){
		var oTab=_('#tabMain_'+i);
		oTab.style.border=isAdminEdit?'1px dotted #BBBBBB':'0px dotted #BBBBBB';
	}
}
var cstURL_text = '';
var cstURL_text_more = '';
var cstURL_table = '';
var cstURL_table_more = '';
var cstURL_list = '';
var cstURL_list_more = '';
var cstURL_chart = '';
var cstURL_chart_more = '';
var cstURL_tabs = '';
var cstURL_tabs_0 = '';
var cstURL_tabs_1 = '';
var cstURL_tabs_2 = '';
var cstURL_tabs_more = '';


var arrTemplate=[
	{'type':'text', 'title':'文本框', 'norepeat':false},
	{'type':'table', 'title':'表格', 'norepeat':false},
	{'type':'list', 'title':'列表','showCount':5, 'keyword':'', 'searchTitle':'', 'norepeat':false},
	{'type':'chart', 'title':'图表', 'norepeat':false},
	{'type':'tabs', 'title':'选项卡', 'tabs':['tab01','tab02','tab03'], 'norepeat':false}
];

var arrItem_NoBorder=['uploadDoc'];//['unitRank','topics'];

var hJsonIdxMax=0;

function getNewArrTemplateItem(sType){
	var item={}
	for (var i=0; i<arrTemplate.length; i++){
		if (arrTemplate[i].type==sType) {
			item = arrTemplate[i];
			break;
		}
	}
	var newItem = new Object();
	for(var i in item) {
		if (i=='norepeat') continue;
		newItem[i] = item[i];
	}
	return newItem;
}

function getJsonItem(idx){
	for (var i=0; i<hJson.items.length; i++){
		if (hJson.items[i].idx==idx) return hJson.items[i];
	}
}

// var arrTemplateLayout=['260,*,270','33%,*,33%','30%,*','25%,25%,25%,*','*|260,*,270'];
// var arrTemplateLayout_=['20%,*,20%','33%,*,33%','30%,*','25%,25%,25%,*','*|20%,*,20%'];
var arrTemplateLayout=['260,*,270','33%,*,33%','30%,*','25%,25%,25%,*'];
var arrTemplateLayout_=['20%,*,20%','33%,*,33%','30%,*','25%,25%,25%,*'];

var divIndexItemTemp=null; //占位DIV
function initHomeDoc(){
	try{
		initKabseSetting(settingJSON,'home-document','homeDocument');//初始化arrTemplate
	}catch(e){}
	
	if (typeof(hJson.cols)=='undefined') hJson.cols=arrTemplateLayout[0];
	if (hJson.cols=='') hJson.cols=arrTemplateLayout[0];
	//布局属性
	_('#txtPageLayout').value=hJson.cols;
	var sHTML='';
	for (var i=0; i<arrTemplateLayout_.length; i++){
		sHTML+='<div onmouseover="this.style.backgroundColor=\'#C6D880\'" onmouseout="this.style.backgroundColor=\'\'" onclick="_(\'#txtPageLayout\').value=\''+arrTemplateLayout[i]+'\';changePageLayout();">';
		sHTML+='<table BORDER="1" BorderColor="#6699CC" CELLSPACING="0" CELLPADDING="0"><tr>';
		if (arrTemplateLayout_[i]=='*|20%,*,20%'){
				sHTML+='<td colspan="3" height="4px;"></td></tr><tr>';
				sHTML+='<td width="20%"></td><td></td><td width="20%"></td>';
		}else{
			var arrTem=arrTemplateLayout_[i].split(',');
			for (var j=0; j<arrTem.length; j++){
				sHTML+='<td width="'+arrTem[j]+'"></td>';
			}
		}
		sHTML+='</tr></table>';
		sHTML+='</div>';
	}
	_('#divTemplateLayout').innerHTML=sHTML;

	//占位DIV
	divIndexItemTemp=document.getElementById('divIndexItemTemp');
	if (!divIndexItemTemp){
		divIndexItemTemp = document.createElement("div");
		divIndexItemTemp.id='divIndexItemTemp';
		divIndexItemTemp.name='divIndexItemTemp';
		divIndexItemTemp.className='divIndexItemTemp';
		document.body.appendChild(divIndexItemTemp);
		divIndexItemTemp.style.display='none';
	}

	//初始化可添加项
	sHTML='';
	for (var i=0; i<arrTemplate.length; i++){
		sHTML+='<div class="miiTemplate_Item" id="miiTemplate_Item_'+arrTemplate[i].type+'" onmousedown="if (!this.disabled){divDragSrc=this;startDrag(event);}" TemplateType="'+arrTemplate[i].type+'">';
		sHTML+=arrTemplate[i].title;
		sHTML+='</div>';	
	}
	sHTML+='<div style="clear:both"></div>';	
	_('#divTemplate').innerHTML=sHTML;
	if (isAdminEdit) _('#tabTemplate').style.display='';

	//初始化内容
	sHTML='';
	var arrTemTab=hJson.cols.split('|');
	tabMainCount=arrTemTab.length;
	for (var j=0; j<arrTemTab.length; j++){
		sHTML+='<table id="tabMain_'+j+'" style="table-layout:fixed;border:0px dotted #BBBBBB;" width="100%" border="0" cellspacing="0" cellpadding="0">';
		var arrTem=arrTemTab[j].split(',')
		for (var i=0; i<arrTem.length; i++) sHTML+='	<col width="'+arrTem[i]+'">';
		sHTML+='	<tr valign="top">';
		for (var i=0; i<arrTem.length; i++){
			sHTML+='		<td style="'+(isIE?'min-':'')+'height:30px;"></td>';
		}
		sHTML+='	</tr>';
		sHTML+='</table>';
	}
	/*
	sHTML+='<table id="tabMain" style="table-layout:fixed;border:1px dotted #EEEEEE;" width="100%" border="1" cellspacing="0" cellpadding="0">';
	var arrTem=hJson.cols.split(',')
	for (var i=0; i<arrTem.length; i++) sHTML+='	<col width="'+arrTem[i]+'">';
	sHTML+='	<tr valign="top">';
	for (var i=0; i<arrTem.length; i++){
		sHTML+='		<td style="min-height:30px;"></td>';
	}
	sHTML+='	</tr>';
	sHTML+='</table>';
	*/

	_('#divMain').innerHTML=sHTML;
	//var oTab=_('#tabMain');
	for (var i=0; i<hJson.items.length; i++){
		hJson.items[i].idx=i;
		if (typeof(hJson.items[i].tab)!='number') hJson.items[i].tab=0;
		var oTab=_('#tabMain_'+ hJson.items[i].tab);
		if (!oTab) oTab=_('#tabMain_0');
		var showBorder=!z.isInArray(arrItem_NoBorder,hJson.items[i].type);
		var iCol=hJson.items[i].col;
		if (iCol>=oTab.rows[0].cells.length) iCol=oTab.rows[0].cells.length-1;
		oTab.rows[0].cells[iCol].innerHTML+='<div id="MainIndexItem_'+i.toString()+'" class="MainIndexItem"  onmouseover="showOperation('+i+')" onmouseout="hideOperation('+i+')" style="'+(showBorder?'':'border:none;padding:0px;')+'">'+hJson.items[i].title+'</div>';
	}
	hJsonIdxMax=hJson.items.length;
	for (var i=0; i<hJson.items.length; i++) reloadItem(i);
	initTemplate_Item();
	//alert(Obj2str(hJson))
}

//设置备用项目是否可用
function initTemplate_Item(){
	for (var i=0; i<arrTemplate.length; i++){
		if (!arrTemplate[i].norepeat) continue;
		var obj=_('#miiTemplate_Item_'+arrTemplate[i].type);
		if (obj.disabled) {
			obj.disabled=false;
			obj.style.backgroundColor='white';
		}
		for (var j=0; j<hJson.items.length; j++){
			if (hJson.items[j].type==arrTemplate[i].type){
				obj.disabled=true;
				obj.style.backgroundColor='#EEEEEE';
				break;
			}
		}
	}
}
	//(divDragSrc.getAttribute('TemplateType'))


function reloadItem(idx){
	var item=getJsonItem(idx);
	var oDiv=_('#MainIndexItem_'+idx);
	//文本框
	if (item.type=='text') reloadItem_Text(oDiv,item);
	//表格
	if (item.type=='table') reloadItem_Table(oDiv,item);
	//列表
	if (item.type=='list') reloadItem_List(oDiv,item);
	//图表
	if (item.type=='chart') reloadItem_Chart(oDiv,item);
	//选项卡
	if (item.type=='tabs') reloadItem_Tabs(oDiv,item);	

}


function saveJSON(){
	//alert(Obj2str(hJson));
	//提交保存JSON数据
	//alert(Obj2str(hJson));
	var sURL='/home/0/saveJSON';
	//alert(Obj2str(hJson))
	//jsfw.Ajax.postArrDataHttp(sURL, {'homejson':Obj2str(hJson)}, true, function (r){
		//alert(r.responseText);
	//});

}

//保存设置Item参数
function saveItem(idx){
	var oDiv=_('#MainIndexItemEdit_'+idx);
	var aForm=oDiv.getElementsByTagName('form')[0];
	var item=getJsonItem(idx);
	for(var i=0;i<aForm.elements.length;i++){
		if (aForm.elements[i].name=='') continue;
		item[aForm.elements[i].name]=aForm.elements[i].value;
	}
	reloadItem(idx);
	saveJSON();
}

//删除Item
function deleteItem(idx){
	if (!confirm('确实要删除此模块？')) return;
	for (var i=0; i<hJson.items.length; i++){
		if (hJson.items[i].idx==idx) {
			hJson.items.splice(i,1);
			break;
		}
	}
	var oDiv=_('#MainIndexItem_'+idx);
	if (oDiv){
		oDiv.parentNode.removeChild(oDiv);
	}
	saveJSON();
	initTemplate_Item();
}
//
function moveItemUp(idx){
	var oDiv=_('#MainIndexItem_'+idx);
	var oDivOther=oDiv.previousSibling;
	if (!oDivOther) return;
	oDiv.parentNode.insertBefore(oDiv,oDivOther);
	saveItemSite()
}
function moveItemDown(idx){
	var oDiv=_('#MainIndexItem_'+idx);
	var oDivOther=oDiv.nextSibling;
	if (!oDivOther) return;
	oDiv.parentNode.insertBefore(oDivOther,oDiv);
	saveItemSite()
}

//保存设置Item位置
function saveItemSite(){
	for (var k=0; k<tabMainCount; k++){
		var oTab=_('#tabMain_'+k);
		for (var i=0; i<oTab.rows[0].cells.length; i++){
			var iCol = i;
			var divs=oTab.rows[0].cells[iCol].childNodes;
			for (var j=0; j<divs.length; j++){
				if (divs[j].className!='MainIndexItem') continue;
				var sid=divs[j].id.substr(14);
				var item=getJsonItem(sid);
				item.tab=k;
				item.col=i;
				item.row=j;
			}
		}
	}
	saveJSON();
}

function changePageLayout(){
	hJson.cols=_('#txtPageLayout').value;
	saveJSON();
	hJson.items.sort(function(a, b){return a.row-b.row});
	initHomeDoc();
}

var timOpt;
var oldObjOpt;
function showOperation(idx){
	if (!isAdminEdit) return;
	clearTimeout(timOpt);
	try{ if (_('#MainIndexItemOpt_'+idx)!=oldObjOpt) oldObjOpt.style.display='none'; }catch(e){}
	var obj=_('#MainIndexItemOpt_'+idx);
	obj.style.width=_('#MainIndexItem_'+idx).offsetWidth - (isIE?0:2);
	obj.style.display='';
}
function hideOperation(idx){
	oldObjOpt=_('#MainIndexItemOpt_'+idx);
	clearTimeout(timOpt);
	timOpt = setTimeout(function (){
		try{
			_('#MainIndexItemOpt_'+idx).style.display='none';
		}catch(e){}
	},500);
}

//生成操作条
function getItemHTML_Operation(item){
	var sHTML='';
	sHTML += '<div class="miiOperation" id="MainIndexItemOpt_'+item.idx+'" style="display:none" onmouseover="showOperation('+item.idx+')" onmouseout="hideOperation('+item.idx+')">';
	sHTML += '<table width="100%%" border="0" cellspacing="0" cellpadding="0">';
	sHTML += '<tr>';
	sHTML += '		<td height="22" onmousedown="divDragSrc=this.parentNode.parentNode.parentNode.parentNode.parentNode;startDrag(event);" style="cursor:move;">&nbsp;</td>';
	sHTML += '		<td class="miiTitle_btn" width="65px" align="right" id="MainIndexItemBtn_'+item.idx+'">';
	sHTML += '<a class="miiTitle_btn_up" onclick="moveItemUp('+item.idx+')" onmouseover="this.className=\'miiTitle_btn_up_\'" onmouseout="this.className=\'miiTitle_btn_up\'" title="上移"> </a>';
	sHTML += '<a class="miiTitle_btn_down" onclick="moveItemDown('+item.idx+')" onmouseover="this.className=\'miiTitle_btn_down_\'" onmouseout="this.className=\'miiTitle_btn_down\'" title="下移"> </a>';
	sHTML += '<a class="miiTitle_btn_edit" onclick="showItemEdit('+item.idx+')" onmouseover="this.className=\'miiTitle_btn_edit_\'" onmouseout="this.className=\'miiTitle_btn_edit\'" title="编辑"> </a>';
	sHTML += '<a class="miiTitle_btn_close" onclick="deleteItem('+item.idx+')" onmouseover="this.className=\'miiTitle_btn_close_\'" onmouseout="this.className=\'miiTitle_btn_close\'" title="删除"></a>';
	sHTML += '</td>';
	sHTML += '';
	sHTML += '<tr>';
	sHTML += '</table>';
	sHTML += '</div>';
	return sHTML;
}
//生成标题
function getItemHTML_Title(item,sMore,sCSS,rename){
	var sTit = rename||item.title;
	sMore = sMore||'';
	var sHTML='';
	sHTML += '<div class="miiTitle" style="width:100%;"><table width="100%" border="0" cellspacing="0" cellpadding="0">';
	sHTML += '	<tr>';
	sHTML += '		<td>'+sTit+'</td>';
	if (sMore!=''){
		//sHTML += '		<td class="miiTitle_more" align="right" id="MainIndexItemMore_'+item.idx+'">'+sMore+'</td>';
	}
	sHTML += '	</tr>';
	sHTML += '</table></div>';
	return sHTML;
}
function getItemHTML_TabTitle(item,sMore,sCSS,iSyn){
	sMore = sMore||'';
	if(typeof(iSyn)!='boolean')iSyn = true;
	var sHTML='';
	sHTML += '<div class="miiTitle" style="width:100%;"><table class="miiTab" width="100%" border="0" cellspacing="0" cellpadding="0">';
	sHTML += '	<tr>';
	sHTML += '		<td>';
	for (var i=0; i<item.tabs.length; i++){
		sHTML += '		<div onmouseover="TabTitleSelect(this,'+item.idx+','+i+','+iSyn+',\''+item+'\')" class="miiTabItem'+(i==0?'Sel':'')+'" style1="border:0px solid red;" >'+item.tabs[i]+'</div>';
	}
	sHTML += '</td>';
	if (sMore!=''){
		//sHTML += '		<td class="miiTitle_more" align="right" id="MainIndexItemMore_'+item.idx+'">'+sMore+'</td>';
	}
	sHTML += '	</tr>';
	sHTML += '</table></div>';
	return sHTML;
}
function TabTitleSelect(obj,itemIdx,tabIdx,iSyn,item){
	var objs=obj.parentNode.childNodes;
	for (var i=0; i<objs.length; i++){
		if (objs[i].className=='miiTabItemSel') objs[i].className='miiTabItem';
	}
	obj.className='miiTabItemSel';
	var item=getJsonItem(itemIdx);
	for (var i=0; i<item.tabs.length; i++){
		_('#MainIndexItemCnt_'+itemIdx+'_'+i).style.display='none';
	}
	_('#MainIndexItemCnt_'+itemIdx+'_'+tabIdx).style.display='';
	//alert(objs.length)
	if(!iSyn){
		HttpToDiv(getTabItemURL(item,tabIdx),_('#MainIndexItemCnt_'+item.idx+'_'+tabIdx),true);
	}
}

//生成编辑
function getItemHTML_Edit(item,arr){
	var sHTML='';
	sHTML += '<div id="MainIndexItemEdit_'+item.idx+'" class="miiEdit" style="display:none;">';
	sHTML += '<form onsubmit="saveItem('+item.idx+');return false;">';
	for (var i=0; i<arr.length; i++){
		sHTML += '<div class="miiEdit_item">';
		sHTML += '<span class="cap">';
		sHTML += arr[i][0];
		sHTML += '</span>';
		if (arr[i][1]=='title'){
			sHTML += '<input name="title" value="' + item.title + '" class="input">';
		}
		if (arr[i][1]=='searchTitle'){
			//sHTML += '<input name="searchTitle" value="' + item.searchTitle + '" class="input">';
		}
		if (arr[i][1]=='keyword'){
			//sHTML += '<input name="keyword" value="' + item.keyword + '" class="input">';
		}
		if (arr[i][1]=='folderId'){
			//sHTML += '<input name="folderId" id="folderId__'+item.idx+'" value="' + item.folderId + '" type="hidden">';
			//sHTML += '<input name="folderName" id="folderName__'+item.idx+'" value="' + item.folderName + '" class="input" readonly="readonly" onclick="top.selectFlds(_(\'folderId__'+item.idx+'\'),_(\'folderName__'+item.idx+'\'),\'root\',\'\');">&nbsp;&nbsp;<font color="red">请点击输入框选择知识目录</font>';
		}
		if (arr[i][1]=='orgId'){
			//sHTML += '<input name="orgId" id="orgId__'+item.idx+'" value="' + item.orgId + '" type="hidden">';
			//sHTML += '<input name="orgName" id="orgName__'+item.idx+'" value="' + item.orgName + '" class="input" readonly="readonly" onclick="top.selectOrgs(_(\'orgId__'+item.idx+'\'),_(\'orgName__'+item.idx+'\'),\'\',\'org,area,corp,group,user\',\'org,area,corp,group,user\');">&nbsp;&nbsp;<font color="red">请点击输入框选择组织机构</font>';
		}
		//'folderId':'', 'folderName':'', 'orgId':'', 'orgName':''
		if (arr[i][1]=='showCount'){
			sHTML += '<select name="showCount">';
			sHTML += '<option value="3"  '+(item.showCount==3?'selected':'')+'>3</option>';
			sHTML += '<option value="5"  '+(item.showCount==5?'selected':'')+'>5</option>';
			sHTML += '<option value="10" '+(item.showCount==10?'selected':'')+'>10</option>';
			sHTML += '<option value="15" '+(item.showCount==15?'selected':'')+'>15</option>';
			sHTML += '<option value="20" '+(item.showCount==20?'selected':'')+'>20</option>';
			sHTML += '</select>';
		}
		sHTML += '</div>';
	}
	sHTML += '<div class="miiEdit_item" style="padding-left:50px;">';
	sHTML += '<input type="submit" class="button" value=" 确定 " > ';
	sHTML += '<input type="button" class="button" value=" 取消 " onclick="showItemEdit('+item.idx+')">';
	sHTML += '</div>';
	sHTML += '</form>';
	sHTML += '</div>';
	return sHTML
}
//获取URL
function getItemURL(item,isMore){
	if ((typeof(isMore)=='boolean') && (isMore)){
		var sURL=eval('cstURL_'+item.type+'_more');
	}else{
		var sURL=eval('cstURL_'+item.type);
	}
	for (var str in item){
		var strTem='{'+str+'}';
		sURL = sURL.replace(strTem,item[str]);
	}
	return z.getURLWithTime(sURL);
}
//获取URL
function getTabItemURL(item,idx,isMore){
	if ((typeof(isMore)=='boolean') && (isMore)){
		var sURL=eval('cstURL_'+item.type+'_'+idx+'_more');
	}else{
		var sURL=eval('cstURL_'+item.type+'_'+idx);
	}
	for (var str in item){
		var strTem='{'+str+'}';
		sURL = sURL.replace(strTem,item[str]);
	}
	return z.getURLWithTime(sURL);
}


//文本
function reloadItem_Text(oDiv,item){
	var sHTML='';
	var sMore='<a href="javascript:void(0);" onclick="top.showDialog(\'公告信息\',\''+getItemURL(item,true)+'\',\'WinItemTask\',720,400);return false;">更多</a>'
	sHTML += getItemHTML_Operation(item);
	sHTML += getItemHTML_Title(item,sMore,'tit2');
	sHTML += getItemHTML_Edit(item,[['标题','title']]);
	sHTML += '<div id="MainIndexItemCnt_'+item.idx+'" class="miiContent"></div>';
	oDiv.innerHTML=sHTML;
	HttpToDiv(getItemURL(item),_('#MainIndexItemCnt_'+item.idx));
}

//表格
function reloadItem_Table(oDiv,item){
	var sHTML='';
	sHTML += getItemHTML_Operation(item);
	sHTML += getItemHTML_Title(item);
	sHTML += getItemHTML_Edit(item,[['标题','title'],['显示条数','showCount']]);
	sHTML += '<div id="MainIndexItemCnt_'+item.idx+'" class="miiContent">正在加载，请稍候...</div>';
	oDiv.innerHTML=sHTML;
}

//列表
function reloadItem_List(oDiv,item){
	var sHTML='';
	var sMore='<a href="javascript:void(0);" onclick="top.showDialog(\'排行信息\',\''+getItemURL(item,true)+'\',\'WinItemUnitRank\',800,540);return false;">更多</a>'
	sHTML += getItemHTML_Operation(item);
	sHTML += getItemHTML_Title(item,sMore,'tit2');
	sHTML += getItemHTML_Edit(item,[['标题','title'],['显示条数','showCount']]);
	sHTML += '<div id="MainIndexItemCnt_'+item.idx+'" class="miiContent">正在加载，请稍候...</div>';
	oDiv.innerHTML=sHTML;
	HttpToDiv(getItemURL(item),_('#MainIndexItemCnt_'+item.idx));
}

//图表
function reloadItem_Chart(oDiv,item){
	var sHTML='';
	sHTML += getItemHTML_Operation(item);
	sHTML += getItemHTML_Title(item);
	sHTML += getItemHTML_Edit(item,[['标题','title']]);
	sHTML += '<div id="MainIndexItemCnt_'+item.idx+'" class="miiContent">正在加载，请稍候...</div>';
	oDiv.innerHTML=sHTML;
	HttpToDiv(getItemURL(item),_('#MainIndexItemCnt_'+item.idx));
}

//选项卡
function reloadItem_Tabs(oDiv,item){
	var sHTML='';
	var sMore='<a href="javascript:void(0);" onclick="top.showDialog(\'热点文档\',\''+getItemURL(item,true)+'\',\'WinItemHotDoc\',720,540);return false;">更多</a>'
	// var sMore='<a href="javascript:void(0);" onclick="funMore(\''+item+'\')">更多</a>'
	sHTML += getItemHTML_Operation(item);
	sHTML += getItemHTML_TabTitle(item,sMore,'',false);
	sHTML += getItemHTML_Edit(item,[['标题','title'],['显示条数','showCount']]);
	for (var i=0; i<item.tabs.length; i++){
		sHTML += '<div id="MainIndexItemCnt_'+item.idx+'_'+i+'" class="miiContent" style="'+(i>0?'display:none;':'')+';padding-top:3px;">正在加载，请稍候...</div>';
	}
	oDiv.innerHTML=sHTML;
	//for (var i=0; i<item.tabs.length; i++){
		HttpToDiv(getTabItemURL(item,0),_('#MainIndexItemCnt_'+item.idx+'_0'),true);
	//}
}



//获取URL
function getMoreURL(item,idx){
	var sURL=eval('cstURL_'+item.type+'_'+idx+'_more');
	for (var str in item){
		var strTem='{'+str+'}';
		sURL = sURL.replace(strTem,item[str]);
	}
	return z.getURLWithTime(sURL);
}

function HttpToDiv(sURL,oDiv,isPost){
	try{
		var obj = (typeof(oDiv)=='string')?_(oDiv):oDiv;
		if ((isPost) && (sURL.indexOf('?')>-1)){
			var oArr='';
			var strURL=sURL.substr(0,sURL.indexOf('?'));
			var strParam=sURL.substr(sURL.indexOf('?')+1);
			var arrTem=strParam.split('&');
			var arr={};
			for (var i=0; i<arrTem.length; i++){
				var sKey=arrTem[i].substr(0,arrTem[i].indexOf('='));
				var sVal=arrTem[i].substr(arrTem[i].indexOf('=')+1);
				arr[sKey]=sVal;
			}
			jsfw.Ajax.postArrDataHttp(strURL, arr, true, function (r){
				oDiv.innerHTML = r.responseText;
			});
		}else{
			jsfw.Ajax.loadHttpToObj(sURL,oDiv);
		}
	}catch(e){}
}

function showItemEdit(idx){
	//var oDiv=_('#MainIndexItemEdit_'+idx);
	//oDiv.style.display = oDiv.style.display=='none'?'':'none';
	var oDiv=_('#MainIndexItemCnt_'+idx);
	top.curEditObj = oDiv;
    vm.curItemIdx = idx;
	var item = getJsonItem(idx);
	//文本框
	if (item.type=='text'){
		vm.dialogEditTextVisible = true;
        vm.itemTit = item.title;
        vm.titDisplay = '1';
        if(z.isNullOrEmpty(item.title))vm.titDisplay = '0';
		var sHTML = oDiv.innerHTML;
        if(sHTML)setTimeout(function(){$('#keFrame').get(0).contentWindow.editor.html(sHTML)},100);
	}
	//表格
	if (item.type=='table'){
		vm.dialogEditGridVisible = true;
	}
	//列表
	if (item.type=='list') reloadItem_List(oDiv,item);
	//图表
	if (item.type=='chart') reloadItem_Chart(oDiv,item);
	//选项卡
	if (item.type=='tabs') reloadItem_Tabs(oDiv,item);
}


var chaLeft=0;
var chaTop=0;
var divDragSrc=null;	//拖拽源DIV
var divDragItem=null;

/////////////////////////////////////
function startDrag(e){
	divDragItem=document.getElementById('divDragItem');
	if (!divDragItem){
		divDragItem = document.createElement("div");
		divDragItem.id='divDragItem';
		divDragItem.name='divDragItem';
		divDragItem.className='divDragItem';
		document.body.appendChild(divDragItem);
	}
	e = e || event;
	chaLeft = (e.offsetX||10); 
	chaTop = (e.offsetY||10);
	if (divDragSrc.className=='MainIndexItem') chaTop=chaTop-22
	divDragItem.style.left = (e.clientX||0) - chaLeft;
	divDragItem.style.top  = (e.clientY||0)  - chaTop + document.body.scrollTop;
	divDragItem.style.display='';
	divDragItem.innerHTML=divDragSrc.innerHTML;
	divDragItem.style.width = divDragSrc.offsetWidth;
	divIndexItemTemp.style.height = divDragItem.offsetHeight;
	if (divDragSrc.className=='MainIndexItem') {
		divDragSrc.style.display='none';
	}
	if(divDragItem.setCapture)
		divDragItem.setCapture();
	else if(window.captureEvents)
		window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);

	document.onselectstart = function(){return false;};
	document.onmousemove = funDrag;
	document.onmouseup   = endDrag;
}
function funDrag(e){
	e = e || event;
	var eX=e.clientX;//+10;
	var eY=e.clientY;//+15;
	divDragItem.style.left=eX+document.body.scrollLeft - chaLeft;
	divDragItem.style.top=eY+document.body.scrollTop - chaTop;
	Draw_Item_Temp(e.clientX,e.clientY+document.body.scrollTop);
}
function endDrag(){
	if(divDragItem.releaseCapture)
		divDragItem.releaseCapture();
	else if(window.captureEvents)
		window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);
	document.onmousemove = document.onmouseup = document.onselectstart = null;
	divDragItem.style.display='none';
	if (divIndexItemTemp.parentNode.nodeName=='BODY') {
		if (divDragSrc.className=='MainIndexItem') divDragSrc.style.display='';
		divIndexItemTemp.style.display='none';
		return;
	}
	if (divDragSrc.className=='MainIndexItem') {
		divDragSrc.style.display='';
		divIndexItemTemp.parentNode.insertBefore(divDragSrc,divIndexItemTemp);
	}else{
		var newidx=hJsonIdxMax;
		hJsonIdxMax++;
		var divTem = document.createElement("div");
		divTem.id='MainIndexItem_'+newidx.toString();
		divTem.className='MainIndexItem';
		divTem.onmouseover = new Function('showOperation('+newidx+')');
		divTem.onmouseout = new Function('hideOperation('+newidx+')');
		// onmouseover="showOperation('+i+')" onmouseout="hideOperation('+i+')"
		divIndexItemTemp.parentNode.insertBefore(divTem,divIndexItemTemp);
		var intTem=hJson.items.length;
		hJson.items[intTem]=getNewArrTemplateItem(divDragSrc.getAttribute('TemplateType'));
		hJson.items[intTem].idx=newidx;
		var showBorder=!z.isInArray(arrItem_NoBorder,hJson.items[intTem].type);
		if (!showBorder){
			divTem.style.border='none';
			divTem.style.padding=0;
		}
		reloadItem(newidx);
	}
	document.body.appendChild(divIndexItemTemp);
	divIndexItemTemp.style.display='none';
	saveItemSite();
	initTemplate_Item();
}
function Draw_Item_Temp(iX,iY){
	var intTab=-1;
	for (var i=0; i<tabMainCount; i++){
		var temTab=_('#tabMain_'+i);
		var iTop=z.getAbsTop(temTab);
		if ((iY>iTop)&&(iY<iTop+temTab.offsetHeight)){
			intTab=i;
			break;
		}
	}
	if (intTab==-1){
		if (iY<z.getAbsTop(_('#tabMain_'+0))){
			intTab=0;
		}else{
			intTab=tabMainCount-1;
		}
	}
	var oTab=_('#tabMain_'+intTab);
	//var oTab=_('#tabMain');
	var iCol = -1;
	var iTem = 0;
	for (var i=0; i<oTab.rows[0].cells.length; i++){
		if ((iX >= iTem) && (iX < (iTem+oTab.rows[0].cells[i].offsetWidth))){
			iCol=i;
			break;
		}
		iTem += oTab.rows[0].cells[i].offsetWidth;
	}
	if (iCol==-1) iCol=(iX>10)?(oTab.rows[0].cells.length-1):0;
	
	var divs=oTab.rows[0].cells[iCol].childNodes;

	var iRow=divs.length;
	var iBase=z.getAbsTop(oTab);//TableDefaultPage.rows[0].offsetHeight+TableDefaultPage.rows[1].offsetHeight;
	iTem = iBase;
	for (var i=0; i<divs.length; i++){
		//if (divs[i]==curDragItem) continue;
		//if (divs[i]==divIndexItemTemp) continue;
		if (iY < (iTem + divs[i].offsetHeight/2)) {
			iRow = i;
			break;
		}
		iTem += divs[i].offsetHeight;
	}
	if (iRow>=divs.length){
		oTab.rows[0].cells[iCol].appendChild(divIndexItemTemp);
	}else{
		oTab.rows[0].cells[iCol].insertBefore(divIndexItemTemp,divs[iRow]);
	}
	divIndexItemTemp.style.display='';
}