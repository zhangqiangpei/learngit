var vm = new Vue({
    el: '#wrap',
    data: {       
        dialogEditTextVisible:false,
		dialogEditGridVisible:false,
        dialogEditChartVisible:false,
        curItemCnt:'',//当前编辑对象内容
        itemTit:'',
        titDisplay:'1',
        editor:'',
        defDatasource:'',
		dataOptions:[{value:'/common/json/grid1.json',label:'国家数据'},{value:'/common/json/grid1.json',label:'企业数据'},{value:'/common/json/grid1.json',label:'项目数据'}],
		checkedColumns:['id'],
		columns:[],
		gridData:[],
        status:'0',
        thematicId:'',
        thematicTit:'',//专题名称
        thematicItemIds:[],//专题模块项id集合
        thematicItemDatas:[]//专题模块数据
    },
    methods: {
        saveText:function(){
            var sHTML = $('#keFrame').get(0).contentWindow.editor.html();
            this.curItemCnt = sHTML;
            $('#keFrame').get(0).contentWindow.editor.html('<strong>请输入内容<strong>');
            this.dialogEditTextVisible = false;
            if(typeof(fnAfterSaveEdit)=='function')fnAfterSaveEdit();
        },
		saveGrid:function(){
			var sHTML = $('#gdFrame').get(0).contentWindow.$('#divGrid').html();
            this.curItemCnt = sHTML;
			this.dialogEditGridVisible = false;
            if(typeof(fnAfterSaveEdit)=='function')fnAfterSaveEdit();
		},
        saveChart:function(){
            
        },
		handleSrhGrid:function(value){
			var sURL = '/common/json/grid1.json';
			if(value!='')sURL = value;
			z.get(sURL,{},function(r){
				if(r.code==0){
					var jo = r.data.list[0];
					var arr = [];
					for (var key in jo){
						arr.push(key);
					}
					$('#divDesc').css('display','block');
					vm.checkedColumns = arr;
					vm.columns = arr;
					vm.gridData = r.data.list;
					vm.handleShowGrid();
				}
			})
		},
		handleShowGrid:function(){
			if(this.gridData.length==0)return;
			$('#divGrid').html('');
			var oPar = document.createElement('table');
			oPar.id = 'grid';
			oPar.border=1; oPar.cellPadding=3; oPar.cellSpacing=0;oPar.className='Grid';
			var tr  = oPar.insertRow(0);

			for (var i=0; i<vm.checkedColumns.length; i++){
				var td = tr.insertCell(-1);
				td.className = 'GridHead';
				td.innerHTML = vm.checkedColumns[i];
			}


			for (var i=0; i<this.gridData.length; i++){
				tr  = oPar.insertRow(i+1);
				for (var key in vm.gridData[i]){
					if(z.isInArray(this.checkedColumns,key)){
						tr.insertCell(-1).innerHTML = this.gridData[i][key];
					}
				}
				
			}
			$('#divGrid').append(oPar);
		},
		handleCheckedChange:function(){
			this.handleShowGrid();
		},
        saveThematic:function(){
            if(z.isNullOrEmpty(this.thematicTit)){
                z.error('专题名称不能为空！');
                return;
            }
            var param = {};
            param.thematicName = this.thematicTit;
            param.thematicLayout = z.Obj2str(hJson);
            param.status = this.status;
            if(z.isNullOrEmpty(this.thematicId)){
                //新增
                z.msServiceAsync("user", "IisThematicApi/save",param,function(r){
                    if(null != r && r.code == 0){
                        z.success('保存专题布局成功！');
                        //保存专题数据
                        if(vm.thematicItemDatas.length>0&&z.isNotNullOrEmpty(r.data.id)){
                            vm.thematicId = r.data.id;
                            for(var i=0;i<vm.thematicItemDatas.length;i++){
                                vm.thematicItemDatas[i].thematicId = r.data.id;
                            }
                                z.msServiceAsync("user", "IisThematicItemApi/saveBatch",{items:z.Obj2str(vm.thematicItemDatas)},function(r){
                                    if(null != r && r.code == 0)z.success('保存专题数据成功！');
                            })
                        }
                    }
                });
                
            }else{
                //更新
                param.id = this.thematicId;
                z.msServiceAsync("user", "IisThematicApi/update",param,function(r){
                    if(null != r && r.code == 0){
                        z.success('更新专题布局成功！');
                        //先删除专题数据再保存专题数据
                        var result;
                        if(vm.thematicItemIds.length>0){
                            result = z.msService("user", "IisThematicItemApi/delete",{ids:vm.thematicItemIds.toString()});
                        }
                        if(vm.thematicItemDatas.length>0){
                            result = z.msService("user", "IisThematicItemApi/saveBatch",{items:z.Obj2str(vm.thematicItemDatas)});
                            if(null != result && result.code == 0)z.success('保存专题数据成功！');
                        }
                    }
                });
            }
            
        	
        },
        publicThematic:function(status){
            if(z.isNullOrEmpty(this.thematicId)){
                z.error('专题ID为空！');
                return;
            }
            var param = {};
            param.id = this.thematicId;
            param.status = status;
            z.msServiceAsync("user", "IisThematicApi/update",param,function(r){
                if(null != r && r.code == 0){
                    z.success(status?'发布专题成功！':'取消发布成功');
                    vm.status = status;
                    return;
                }
                z.error('操作失败');
            })
        }
	},
    mounted: function () {
    }
})