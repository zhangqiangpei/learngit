var mainVue = new Vue({
    el: '#main',
    data: {
    	edidName:'',
       menuList:[],
       defOpens:["1","2","3","4","5"],
       countryName:'',
       countryList:[],
       defContinent:'021001',//默认大洲
       continentList:[{name:'亚洲',id:'021001'},
                      {name:'欧洲',id:'021002'},
                      {name:'非洲',id:'021003'},
                      {name:'北美洲',id:'021004'},
                      {name:'南美洲',id:'021005'},
                      {name:'大洋洲',id:'021006'}
                      ],//大洲列表
    },
    methods: {
        initMenu:function(){
            var sURL = '/common/json/menu_country.json';
            z.get(sURL,null,function(r){
                if(r.code==0){
                    if(r.data.length>0){
                        mainVue.menuList = r.data;
                        if(z.isNotNullOrEmpty(r.data[0].children)&&r.data[0].children.length>0){
                            var id = r.data[0].children[0].id;
                             // 选择默认页
                            setTimeout(function () {$('#item_'+id).click();}, 100);
                        }
                    }
                }
            })
        },
        fnSrhCountry:function(){
            alert('search');
        },
        continentChange:function(){
        	//查询州下的所有国家
        	var param={
        			"englishName":this.countryName,
        			"chineseName":this.countryName,
        			"continentCode":this.defContinent
        		}
        		var result = ak.msService("user","IisCountryInfoApi/queryList",param).data;
        		//循环判断国家所属的英文字母的排序
        	this.countryList=[];	
        	for (var i = 0; i < result.length; i++) {
        			this.countryList.push(
        				{"chineseName":result[i].chineseName,"englishName":result[i].englishName}
        			);
    			}
        },
        querySerch:function(){
        	this.continentChange();
        },
        getIFrameBodyHeight:function(sIFrame){
            if(z.isNullOrEmpty(sIFrame))return;
            var oIFrame = document.getElementById(sIFrame);
            var iHeight = 0;
            iHeight = $(oIFrame.contentWindow.document.body).height();
            return iHeight;
        },
        loadPageToIFrame:function(sURL){
            var iframe = document.getElementById('contentCenter');
            iframe.src = "/forward.do?viewPath="+sURL+"&eName="+this.edidName;
            var iHeight = $(document).height();
            if (iframe.attachEvent){
                iframe.attachEvent("onload", function(){
                    iHeight = mainVue.getIFrameBodyHeight('contentCenter')?mainVue.getIFrameBodyHeight('contentCenter'):iHeight;
                    $('#contentCenter').height(iHeight+'px'); 
                });
            } else {    
                iframe.onload = function(){
                    iHeight = mainVue.getIFrameBodyHeight('contentCenter')?mainVue.getIFrameBodyHeight('contentCenter'):iHeight;
                    $('#contentCenter').height(iHeight+'px');
                };
            }
        },
        GetQueryString:function (name){
        	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        	var r = window.location.search.substr(1).match(reg);
        	if(r!=null)return  unescape(r[2]); return null;
        }
    },
    mounted: function() {
    	this.edidName=this.GetQueryString("eName");
        this.initMenu();
        this.continentChange();
    }
})

