var vm = new Vue({
    el: '#wrap',
    data: {       
        dialogEditTextVisible:false,
		dialogEditGridVisible:false,
        curItemIdx:'',//当前编辑对象index
        itemTit:'',
        titDisplay:'1',
        editor:'',
        defDatasource:'',
		dataOptions:[{value:'/common/json/grid1.json',label:'国家数据'},{value:'/common/json/grid1.json',label:'企业数据'},{value:'/common/json/grid1.json',label:'项目数据'}],
		checkedColumns:['id'],
		columns:[],
		gridData:[]
    },
    methods: {
        saveText:function(){
            var sHTML = $('#keFrame').get(0).contentWindow.editor.html();
            top.curEditObj.innerHTML = sHTML;
            $('#keFrame').get(0).contentWindow.editor.html('<strong>请输入内容<strong>');
            vm.dialogEditTextVisible = false;
        },
		saveGrid:function(){
			var sHTML = $('#gdFrame').get(0).contentWindow.$('#divGrid').html();
			top.curEditObj.innerHTML = sHTML;
			vm.dialogEditGridVisible = false;
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
			if(vm.gridData.length==0)return;
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


			for (var i=0; i<vm.gridData.length; i++){
				tr  = oPar.insertRow(i+1);
				for (var key in vm.gridData[i]){
					if(z.isInArray(vm.checkedColumns,key)){
						tr.insertCell(-1).innerHTML = vm.gridData[i][key];
					}
				}
				
			}
			$('#divGrid').append(oPar);
		},
		handleCheckedChange:function(){
			vm.handleShowGrid();
		}
	},
    mounted: function () {
    }
})