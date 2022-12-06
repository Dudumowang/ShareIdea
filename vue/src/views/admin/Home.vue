<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 60px">
      <el-col :span="6">
        <el-card style="color: #409EFF">
          <div><i class="el-icon-user-solid" /> 用户总数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{this.userNum}}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #F56C6C">
          <div><i class="el-icon-chat-line-round" /> 总创意数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
             {{this.ideaNum}}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #67C23A">
          <div><i class="el-icon-chat-dot-round" /> 总评论数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
             {{this.commentNum}}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #E6A23C">
          <div><i class="el-icon-user-solid" /> 管理员总数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{this.adminNum}}
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <div id="main" style="width: 500px; height: 450px"></div>
      </el-col>

      <el-col :span="12">
        <div id="pie" style="width: 500px; height: 400px"></div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: "Home",
  data() {
    return {
      adminNum:"",
      userNum:"",
      ideaNum:"",
      commentNum:""
    }
  },
  created() {
    this.request.get("/admin/total").then(res=>{
      // console.log(res);
      if(res.code==='200'){
        console.log(res)
        this.adminNum=res.data;
      }
      else{
        this.$message.error(res.msg);
      }
    })
    this.request.get("/user/total").then(res=>{
      // console.log(res);
      if(res.code==='200'){
        console.log(res)
        this.userNum=res.data;
      }
      else{
        this.$message.error(res.msg);
      }
    })
    this.request.get("/idea/total").then(res=>{
      // console.log(res);
      if(res.code==='200'){
        console.log(res)
        this.ideaNum=res.data;
      }
      else{
        this.$message.error(res.msg);
      }
    })
    this.request.get("/comment/total").then(res=>{
      // console.log(res);
      if(res.code==='200'){
        console.log(res)
        this.commentNum=res.data;
      }
      else{
        this.$message.error(res.msg);
      }
    })
  },
  mounted() {  // 页面元素渲染之后再触发
    var option = {
      title: {
        text: '各季度用户数量统计',
        subtext: '趋势图',
        left: 'center'
      },
      xAxis: {
        type: 'category',
        data: ["第一季度", "第二季度", "第三季度", "第四季度"]
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name: "用户",
          data: [31,12,27,24],
          type: 'bar'
        },
        {
          name: "用户",
          data: [31,12,27,24],
          type: 'line'
        }
      ]
    };

    // 饼图

    var pieOption = {
      title: {
        text: '各季度用户数量统计',
        subtext: '比例图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [

        {
          name: "用户数量",
          type: 'pie',
          radius: '50%',
          center: ['50%', '50%'],
          label:{            //饼图图形上的文本标签
            normal:{
              show:true,
              position:'inner', //标签的位置
              textStyle : {
                fontWeight : 300 ,
                fontSize : 14,    //文字的字体大小
                color: "#fff"
              },
              formatter:'{d}%'
            }
          },
          data: [
            {name: "第一季度", value: 31},
            {name: "第二季度", value: 12},
            {name: "第三季度", value: 27},
            {name: "第四季度", value: 24}
          ],  // 填空
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ],

    };


    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);

    var pieDom = document.getElementById('pie');
    var pieChart = echarts.init(pieDom);
    myChart.setOption(option);
    pieChart.setOption(pieOption);
  }
}
</script>

<style scoped>

</style>
