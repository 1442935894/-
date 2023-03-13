// 上传图片
<template>
    <el-upload
        ref="upload"
		action=""
		:http-request="upload"
		list-type="picture-card"
		:on-preview="handlePictureCardPreview"
		:on-remove="handleRemove"
		:file-list="fileList"
		multiple
		:limit="1"
		:on-exceed="handleExceed"
		>
		<!-- :auto-upload="false" -->
		<i slot="default" class="el-icon-plus"></i>
			<el-dialog :visible.sync="ImgdialogVisible" :append-to-body="true">
				<img width="100%" :src="dialogImageUrl" alt="">
			</el-dialog>
	</el-upload>
	
</template>

<script>
import {uploadProductImg} from '../../api/imgUpload'
  export default {
    name: 'ImgUpload',
    data () {
      return {
        fileList: [],
		url: '',
		dialogImageUrl: '',
		ImgdialogVisible: false,
		
      }
    },
    methods: {
         
		upload(param) {
			const formData = new FormData()
			formData.append('file', param.file)
			console.log(param)
		uploadProductImg(formData).then(response => {
			this.url = response.data.data
			this.$emit('onUpload')
			console.log('上传图片成功',response.data.data)
		}).catch(response => {
			console.log('图片上传失败')
		})
		},

		//点击删除图片
		handleRemove(file, fileList) {
			console.log(file, fileList);
			//console.log(file.url)
		},
		//触发图片预览
		handlePictureCardPreview(file) {
			this.dialogImageUrl = file.url;
			this.ImgdialogVisible = true;
		},
		
		handleExceed (files, fileList) {
			this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
		},
      clear () {
        this.$refs.upload.clearFiles()
      }
    }
  }
</script>